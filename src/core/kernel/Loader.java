package core.kernel;


/**
 * Represents a unit of loading work during application startup, executed in the background.
 * <p>
 * This interface is not to be used directly, but is only a common super interface of {@link AppLoader},
 * {@link GuiLoader} and {@link InitLoader}.
 * <p>
 * A Loader typically injects an object into a central object, a {@link Buildable} - or just "builder". Each Loader
 * constructs a specific application handle and installs it on the builder, or initializes (a part of) an application
 * handle that is already constructed. After all Loaders for a given builder have executed their work, the builder is
 * requested to construct a final, immutable root handle that represents a complete, coherent subsystem of an
 * application. Root handles are thus the entry-point for finding handles to specific responsabilities of an
 * application, and each of them makes abstraction of specific functionality. Examples of handles are:
 * <ul>
 * <li>A logging handle: responsible for actually logging some output destination, e.g. a file, but makes abstraction of
 * this destination and the actual framework being used.
 * <li>A localization handle: responsible for localizing the application
 * <li>An icon factory: responsible for finding the correct images and icons throughout the application based on
 * identifiers, thereby taking away hardcoded filenames from the rest of the application and centralizing image resource
 * code.
 * </ul>
 * @param <T> the type of the builder that the Loader is working on
 * @author Timmos
 */
public interface Loader<T extends Buildable> {

    /**
     * Loads or initializes some part of the to-be-built application, eventually by injecting an application
     * handle into the given builder.
     * @param builder the builder
     * @throws LoadingFailedException when loading fails
     */
    public void load(T builder) throws LoadingFailedException;

    /**
     * Gets the estimated amount of work that this Loader will execute, expressed in
     * abstracted weight units.
     * <p>This weight can be used to indicate load progress given the weight
     * of other Loaders.
     * @return the estimated amount of work
     */
    public LoadingWeight getWeight ();

    /**
     * Gets a human readable name for this Loader. The returned string
     * may be used in a GUI and should be concise yet informative.
     * @return the name of this Loader
     */
    public String getInternalName ();
}
