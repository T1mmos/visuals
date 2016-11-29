package core.key;

/**
 * Each class implementing this interface represents a key for some factory. Different instances of one such 
 * class are all known at compile time and can be used as keys throughout the
 * application code. The factories map these keys to the objects they 
 * should instantiate, thereby providing a runtime binding between key and value. In effect,
 * a plugin can overrule the value for each key in the entire application, which
 * establishes a very powerful mechanism.
 * This interface is specifically designed to achieve better type safety 
 * by avoiding the use of lower level types (e.g. {@code String}) for keys in client code.
 * <p>Some classes implementing this interface may want to couple each instance to lower level 
 * keys. For example, an {@code key} used for configuration keys want to link each 
 * instance to a {@code String} which will represent the configuration key, used in a 
 * configuration file. Each instance should then point to a unique {@code String} (application-wide), 
 * in order not to let keys interfere with each other. For such {@code key}s, it is 
 * recommended that a custom {@link Object#equals(Object) equals} and {@link Object#hashCode() hashCode}
 * implementation is provided, using these low level properties - factories can use them to detect
 * duplicates and should fail early whenever such condition is encountered.
 * @author Timmos
 */
public interface Key {	
	// marker interface
}
