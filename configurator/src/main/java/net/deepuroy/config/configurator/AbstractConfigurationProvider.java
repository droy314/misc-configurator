package net.deepuroy.config.configurator;

/**
 * Abstract class that handles basic conversions.
 * 
 * @author Deepu Roy
 * 
 */
public abstract class AbstractConfigurationProvider implements
		ConfigurationProvider {

	/**
	 * {@inheritDoc}
	 */
	public abstract String getString(String key);
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean getBoolean(final String key) {
		String value = getString(key);
		if (value != null) {
			return Boolean.valueOf(value.trim());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer getInteger(final String key) {
		String value = getString(key);
		if (value != null) {
			return Integer.valueOf(value.trim());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Float getFloat(final String key) {
		String value = getString(key);
		if (value != null) {
			return Float.valueOf(value.trim());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Double getDouble(final String key) {
		String value = getString(key);
		if (value != null) {
			return Double.valueOf(value.trim());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Long getLong(final String key) {
		String value = getString(key);
		if (value != null) {
			return Long.valueOf(value.trim());
		}
		return null;
	}
	
}
