package gent.timdemey.visuals.core.kernel;

/**
 * Represents a {@link Loader} that further initializes the application.
 * <p>
 * Initializing the application may depend on the core systems (e.g. application and GUI backbones). For example,
 * settings the application frame's icon is impossible until the icon factory has been set (which distributes
 * icons/images based on icon IDs), and this factory is typically installed as part of the GUI backbone - loaded by a
 * {@link GuiLoader}. Therefore, all {@link AppLoader}s and {@link GuiLoader}s must have loaded, and the builders on
 * which they execute must have build the respective core systems. The initializing loaders can then do all necessary
 * startup work, using the core systems.
 * @author Timmos
 */
public interface InitLoader extends Loader<DefaultInitBuilder> {
    // no extra methods
}
