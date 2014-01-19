package net.deepuroy.config.configurator;

/**
 * Interface used by the Configurator that returns configured values for keys.
 * 
 * @author Deepu Roy
 * 
 */
public interface ConfigurationProvider {

	/**
	 * Returns a configured value as a String.
	 * @param key
	 * @return
	 */
	String getString(String key);

	/**
	 * Returns a configured value as an Integer.
	 * @param key
	 * @return
	 */
	Integer getInteger(String key);

	/**
	 * Returns a configured value as a Float.
	 * @param key
	 * @return
	 */
	Float getFloat(String key);

	/**
	 * Returns a configured value as a Long.
	 * @param key
	 * @return
	 */
	Long getLong(String key);

	/**
	 * Returns a configured value as a Double.
	 * @param key
	 * @return
	 */
	Double getDouble(String key);

	/**
	 * Returns a configured value as a Boolean.
	 * @param key
	 * @return
	 */
	Boolean getBoolean(String key);

}
