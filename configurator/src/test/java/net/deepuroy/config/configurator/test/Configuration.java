package net.deepuroy.config.configurator.test;

/**
 * Configuration Interface for tests.
 * @author Deepu Roy
 *
 */
public interface Configuration {
	
	/**
	 * Should return the value configured for key "string.value"
	 * @return
	 */
	@Bind("string.value")
	String getStringValue();

	/**
	 * Should return the value configured for key "integer.value"
	 * @return
	 */
	@Bind("integer.value")
	Integer getIntegerValue();

	/**
	 * Should return the value configured for key "boolean.value"
	 * @return
	 */
	@Bind("boolean.value")
	Boolean getBooleanValue();

	/**
	 * Should return the value configured for key "float.value"
	 * @return
	 */
	@Bind("float.value")
	Float getFloatValue();
	
	/**
	 * Should return the value configured for key "long.value"
	 * @return
	 */
	@Bind("long.value")
	Long getLongValue();
	
	/**
	 * Should return the value configured for key "p.int.value"
	 * @return
	 */
	@Bind("p.int.value")
	int getIntValue();
	
}
