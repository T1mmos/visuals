package core.cfg;

import core.key.ValueKey;

/**
 * Defines a type to identify configuration entries in a {@link Configuration}.
 * @author Timmos
 *
 * @param <V> the type of the value for a config key, to allow automatic conversion of
 * raw values (mostly strings) to actual types
 */
public interface ConfigKey<V> extends ValueKey<V> {

    /**
     * Gets the configuration type of this config key. This is used
     * to check at runtime whether requesting a value from a configuration makes
     * sense for a given key - if the type of the configuration isn't equal to
     * the key's type then the request is not allowed.
     * <p>For example, a meta configuration can only be asked to return a value
     * for a key with type {@link CfgKeyType#Meta}, but not for keys with other
     * types.
     * @return the type of this config key
     */
    public CfgKeyType getType ();

    public void set(V value);
}
