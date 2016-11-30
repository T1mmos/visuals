package gent.timdemey.visuals.core.kernel;

import java.util.Map;

import gent.timdemey.visuals.core.cfg.Configuration;
import gent.timdemey.visuals.core.util.App;
import gent.timdemey.visuals.core.util.Gui;

/**
 * An implementation of this class customizes the application run by the core framework.
 * <p>
 * The first thing that a plugin provides to the core framework is the splash screen that is shown during application
 * load. The splash screen is requested to the plugin after the core has loaded the program arguments and the meta
 * configuration, and after the plugin instance has been located (based on program arguments or meta configuration) and
 * instantiated. The core then shows the splash screen and starts the load procedure, where all {@link Loader}s have
 * their chance to load.
 * <p>
 * A plugin has the capability of injecting custom {@link Buildable}s - also called builders - into the core loader and
 * {@link Loader}s that act upon these builders. As such, the load procedure is chopped into pieces of loading work
 * (Loaders) which are injecting object instances (application handles) into a handful of application building data
 * structures (builders). A builder is being injected with handles by one or more Loaders, but a Loader can only
 * inject to exactly one builder. Thanks to chopping the load procedure in a pluggable pieces, the user can be informed
 * about the progress of the entire load procedure.
 * <p>
 * Notes for implementors: to successfully use a plugin, the following items are mandatory:
 * <ul>
 * <li>The implementing class must be accessible from the core framework, so the class must be public and should provide
 * a public constructor.
 * <li>It must be possible for the framework to instantiate the implementing class, so a nullary constructor must be
 * provided.
 * <li>The class must be added to the classpath. The same is valid for all classes used directly or indirectly by the
 * plugin class.
 * <li>Of course, the class must implement the {@code Plugin} interface.
 * <li>The constructor should not throw any exceptions.
 * </ul>
 * @author Timmos
 */
public interface Plugin {

    /**
     * Gets the name of the image file to show in the splash screen.
     * @return the image file name, or {@code null} if no splash screen should
     * show up
     */
    public Splash getSplash ();

    public DefaultBootBuilder newBootBuilder(Configuration meta, Configuration args);

    /**
     * Creates a new application builder. A plugin may choose to return a subclass if the need
     * arises, which in turn can and logically will return a subclass of {@link DefaultApp} when the framework calls
     * that custom application builder's {@link DefaultAppBuilder#build() build} method.
     * <p>
     * The framework guarantees that only one object of the type, or subtype of the type {@code DefaultApp} can
     * be set, so all plugin code can rely on the fact that the application's type is equal to the type of the object
     * that the returned builder will create via its {@link DefaultAppBuilder#build() build} method. It is thus safe to
     * downcast - however, a utility method exists that does it for you via type inference: {@link App#get()}.
     * @return a {@link DefaultAppBuilder} or a subclass of it
     */
    public DefaultAppBuilder newAppBuilder();

    /**
     * Creates a new GUI builder. A plugin may choose to return a subclass if the need
     * arises, which in turn can and logically will return a subclass of {@link DefaultGui}
     * when the framework calls that custom GUI builder's {@link DefaultGuiBuilder#build() build}
     * method.
     * <p>The framework guarantees that only one object of the type, or subtype of the type
     * {@code DefaultGui} can be set, so all plugin code can rely on the fact that the GUI's
     * type is equal to the type of the object that the returned builder will create via its
     * {@link DefaultGuiBuilder#build() build} method. It is thus safe to downcast - however, a utility
     * method exists that does it for you via type inference: {@link Gui#get()}.
     * @return a {@link DefaultGuiBuilder} or a subclass of it
     */
    public DefaultGuiBuilder newGuiBuilder ();

    /**
     * Creates a new initializator builder. A plugin may choose to return a subclass if the need
     * arises. The framework does not call {@link DefaultInitBuilder#build()} as the returned builder
     * is only used as a common data structure for initializator Loaders. The result of this
     * builder is naturally non-existing, it is only the Loaders that act upon this builder that
     * have effect on the final application.
     * <p>
     * Unlike {@link DefaultAppBuilder} and {@link DefaultGuiBuilder}, this builder will not create a root handler.
     * @return a {@link DefaultInitBuilder} or a subclass of it
     */
    public DefaultInitBuilder newInitBuilder ();

    public void putBootLoaders(Map<LoaderKey, BootLoader> loaders);

    /**
     * Adds the application Loaders. This method is called by the core:
     * <ul>
     * <li>to estimate the amount of work the loading procedure will take;
     * <li>to get the application Loaders that must be injected with a common {@link DefaultAppBuilder} instance
     * </ul>
     * <p>
     * The core's default {@link LoaderKey}s are defined in {@link DefaultLoaderKey}.
     * <p>
     * A subclass of a class that implements this method may override the superclass method in order to extend or modify
     * the set of Loaders provided by the superclass. A subclass may replace a Loader by putting a value (a
     * {@link Loader}) in the map for a key (a {@link LoaderKey}) that was already added to the map by the
     * super method. As the given {@code Map} is <i>insertion-ordered</i>, the order of the Loaders is unaffected if a
     * Loader inserted by the superclass is replaced by one of the subclass.
     * @param loaders an empty, <i>insertion-ordered</i> {@code Map}
     */
    public void putAppLoaders(Map<LoaderKey, AppLoader> loaders);

    /**
     * Adds the GUI Loaders. The method is analogue to {@link #putAppLoaders(Map)}.
     * @param loaders an empty, <i>insertion-ordered</i> {@code Map}
     * @see #putAppLoaders(Map)
     */
    public void putGuiLoaders(Map<LoaderKey, GuiLoader> loaders);

    /**
     * Adds the initializator Loaders. The method is analogue to {@link #putAppLoaders(Map)}.
     * @param loaders an empty, <i>insertion-ordered</i> {@code Map}
     * @see #putAppLoaders(Map)
     */
    public void putInitLoaders(Map<LoaderKey, InitLoader> loaders);

    /**
     * Informs this plugin that loading was successful. The root handles are installed, and the plugin may take appropriate action
     * to start the application.
     */
    public void onLoadingSucceeded ();
}
