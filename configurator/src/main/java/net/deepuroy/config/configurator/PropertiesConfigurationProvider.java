package net.deepuroy.config.configurator;

import java.util.Properties;

/**
 * Adapts a Properties object as a ConfigurationProvider.
 * 
 * @author Deepu Roy
 * 
 */
public class PropertiesConfigurationProvider extends
		AbstractConfigurationProvider {

	private final Properties properties;

	public PropertiesConfigurationProvider(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String getString(String key) {
		if (key == null) {
			return null;
		}
		return properties.getProperty(key);
	}

}
