package core.kernel;

/**
 * Represents a structure that can build an unmodifiable object, composed of
 * objects that are being attached by various {@link Loader} during application loading.
 * <p>Subinterfaces or implementing classes should add setter methods to allow {@code Loader}s to attach 
 * objects to the builder.
 * @author Timmos
 */
public interface Buildable {
    
    /**
     * Builds an unmodifiable object that bundles all objects that were set by {@link Loader}s
     * on this builder during application load. Implementations should return a subclass of 
     * {@code Object}.
     * @return an object that is the result of this builder after various {@link Loader}s attached
     * objects to it
     */
    public Object build ();
}
