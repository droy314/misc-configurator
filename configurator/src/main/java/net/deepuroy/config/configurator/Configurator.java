package net.deepuroy.config.configurator;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Adapts a ConfigurationProvider to any interface that is annotated with the
 * bind annotations.
 * 
 * @author Deepu Roy
 * 
 */
public class Configurator {

	private final ConfigurationHandler handler;

	public Configurator(ConfigurationProvider provider) {
		this.handler = new ConfigurationHandler(provider);
	}

	public <T> T get(Class<T> type) {
		ensureValidConfigurationType(type);
		return createConfigurationProxy(type);
	}

	private <T> T createConfigurationProxy(Class<T> type) {
		return type.cast(Proxy.newProxyInstance(getClassLoader(),
				new Class[] { type }, handler));
	}

	private ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	private void ensureValidConfigurationType(Class<?> type) {
		if (type == null) {
			throw new IllegalArgumentException(
					"Configuration type cannot be null");
		}
		if (!type.isInterface()) {
			// Dynamic proxy works with interfaces only.
			// Is it possible that a configuration can be a class?
			throw new IllegalArgumentException(
					"A configuration type must be an interface.");
		}
	}

	/**
	 * Invocation handler that handles proxy method calls. The handler inspects
	 * the @Bind annotation for the configuration key. Using the key and the
	 * return type of the method, the handler gets the appropriate value.
	 * 
	 * @author Deepu Roy
	 * 
	 */
	private static class ConfigurationHandler implements InvocationHandler {

		private final ConfigurationProvider provider;

		public ConfigurationHandler(ConfigurationProvider provider) {
			this.provider = provider;
		}

		public Object invoke(Object proxy, Method method, Object[] arguments)
				throws Throwable {
			if (!isConfigurationMethod(method)) {
				return null;
			}
			String key = getBindKey(method);
			if (key != null) {
				return getValue(key, method.getReturnType());
			}
			return null;
		}

		private boolean isConfigurationMethod(Method method) {
			return method.getReturnType() != Void.class
					&& method.getParameterTypes().length == 0;
		}

		private Object getValue(String key, Class<?> returnType) {
			Object value = null;
			if (String.class.equals(returnType)) {
				value = provider.getString(key);
			} else if (Integer.class.equals(returnType)) {
				value = provider.getInteger(key);
			} else if (Long.class.equals(returnType)) {
				value = provider.getLong(key);
			} else if (Float.class.equals(returnType)) {
				value = provider.getFloat(key);
			} else if (Double.class.equals(returnType)) {
				value = provider.getDouble(key);
			} else if (Boolean.class.equals(returnType)) {
				value = provider.getBoolean(key);
			} else {
				throw new UnsupportedOperationException(
						"No conversion available to get value as "
								+ returnType.getCanonicalName());
			}
			return value;
		}

		private String getBindKey(Method method) {
			String key = null;
			Object bind = getBindAnnotation(method);
			if (bind != null) {
				key = getValue(bind);
				key = (key == null) ? null : key.trim();
				if (key != null && key.isEmpty()) {
					key = null;
				}
			}
			return key;
		}

		private String getValue(Object bind) {
			Method method = getValueMethod(bind);
			try {
				return (String) method.invoke(bind, new Object[0]);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException("Could not execute method "
						+ method, e);
			} catch (InvocationTargetException e) {
				throw new IllegalStateException("Could not execute method "
						+ method, e);
			}
		}

		private Method getValueMethod(Object bind) {
			Class<?> type = bind.getClass();
			Method method = null;
			try {
				method = type.getMethod("value", new Class[0]);
			} catch (NoSuchMethodException ex) {
				throw new IllegalStateException(
						"Cannot find a method for value.", ex);
			}
			return method;
		}

		private Object getBindAnnotation(Method method) {
			if (method != null) {
				for (Annotation annotation : method.getAnnotations()) {
					if (annotation.annotationType().getSimpleName()
							.equals("Bind")) {
						return annotation;
					}
				}
			}
			return null;
		}

	}

}
