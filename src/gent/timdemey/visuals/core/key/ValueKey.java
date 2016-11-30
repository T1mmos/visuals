package gent.timdemey.visuals.core.key;


/**
 * Defines an key where the value is fetched from external resources.
 *
 * @param <V> the type of the value
 * @author Timmos
 */
public interface ValueKey<V> extends ExternalKey {

    /**
     * Gets the default value of this external key, used when the
     * external resource is unavailable.
     * <p>It is not recommended to call this method directly - in most situations,
     * one should request a value via the factory for this type of key
     * (which may or may not use this {@code key}'s default value, if no
     * value can be found).
     * @return the default value of this {@code key}
     */
    public V getDefaultValue();

    /**
     * Gets the value mapped for this external key.
     * @return the value
     */
    public V get();

    /**
     * Gets the class of a value that can be associated with this key, used
     * to convert from the external source to the type that the returned class
     * represents, and vica versa.
     * @return the class that represents the type of a value associated to this
     * key
     */
    public Class<V> getValueClass ();
}
