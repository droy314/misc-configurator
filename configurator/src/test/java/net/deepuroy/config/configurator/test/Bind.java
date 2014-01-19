package net.deepuroy.config.configurator.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binds an interface method to a configuration key. The Configurator will
 * return the value corresponding to the key when the method is invoked.
 * 
 * @author Deepu Roy
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bind {

	/**
	 * The configuration key
	 * 
	 * @return
	 */
	String value();

}
