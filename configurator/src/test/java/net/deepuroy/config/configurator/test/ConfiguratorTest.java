package net.deepuroy.config.configurator.test;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import net.deepuroy.config.configurator.Configurator;
import net.deepuroy.config.configurator.PropertiesConfigurationProvider;


public class ConfiguratorTest {

	Properties properties = new Properties();
	PropertiesConfigurationProvider provider = new PropertiesConfigurationProvider(
			properties);
	Configurator configurator = new Configurator(provider);

	Configuration configuration = configurator.get(Configuration.class);

	@Test
	public void testDirectRetrieves() {
		// Late bound configuration.
		properties.put("string.value", "Hello!");
		properties.put("integer.value", "1");
		
		assertEquals("Hello!", configuration.getStringValue());
		assertEquals((Integer) 1, configuration.getIntegerValue());
	}
	
}
