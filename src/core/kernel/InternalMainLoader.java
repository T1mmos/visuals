package core.kernel;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import core.cfg.ArgumentsConfigurationStream;
import core.cfg.CompositeConfiguration;
import core.cfg.ConcreteConfiguration;
import core.cfg.Configuration;
import core.cfg.ConfigurationStream;
import core.cfg.DefaultConfiguration;
import core.cfg.FileConfigurationStream;
import core.cfg.MetaConfigKey;
import core.log.msg.ErrorMsg;
import core.log.msg.InfoMsg;
import core.log.msg.WarnMsg;
import core.ui.RedCrossDrawable;
import core.util.Log;
import core.util.Unchecked;

/**
 * Root loader, delegating loading operations to underlying loaders in the correct order.
 * <p>First, the program arguments are parsed and the meta configuration is read. Then,
 * the plugin can be determined - the program arguments have higher priority than the meta
 * configuration. After instantiating the plugin, the application can be built, using the
 * application structure and application loaders provided by the plugin. After that, the same
 * can be done for the GUI - GUI structure and GUI loaders are requested from the plugin.
 * In the final step, we have an Application and a GUI context ready, available application-wide
 * through singleton accessors, and they may both be initialized in a fine-grained manner by
 * the initialization loaders.
 * <p>The order of loading is thus:
 * <ul>
 * <li>Parse the program arguments and build the arguments configuration
 * <li>Read the meta configuration file and build the meta configuration
 * <li>Plugin (is determined from arguments or meta configuration)
 * <li>Application (base without GUI)
 * <li>GUI (depends on Application)
 * <li>Initializators (may depend on both Application and GUI instance)
 * </ul>
 *
 * @author Timmos
 */
final class InternalMainLoader {

    private final DefaultBootBuilder    bootBuilder;
    private final DefaultAppBuilder     appBuilder;
    private final DefaultGuiBuilder     guiBuilder;
    private final DefaultInitBuilder    initBuilder;
    private final List<LoadingListener> listeners;

    private int loaded = 0;
    private int max = 0;

    /**
     * Initializes the different builders and loads the command line arguments,
     * the meta configuration and the plugin.
     * @param args the program arguments
     */
    InternalMainLoader(String[] args) {
        ConfigurationStream reader = new ArgumentsConfigurationStream(args);
        Configuration argCfg = new ConcreteConfiguration(reader);
        argCfg.load();
        Log.info(InfoMsg.CONFIG_ARGS, argCfg);

        Configuration cfg = loadMetaConfiguration(argCfg);
        Configuration metaCfg = new CompositeConfiguration(argCfg, cfg, DefaultConfiguration.INSTANCE);
        Log.info(InfoMsg.CONFIG_META, metaCfg);

        String rawClazz = metaCfg.getValue(MetaConfigKey.PLUGIN.getExternalIdentifier());
        Plugin plugin = findPlugin(rawClazz);

        this.bootBuilder = plugin.newBootBuilder(metaCfg, argCfg);
        this.appBuilder = plugin.newAppBuilder();
        this.guiBuilder = plugin.newGuiBuilder();
        this.initBuilder = plugin.newInitBuilder();
        this.listeners = new ArrayList<>();

        Unchecked.nullPtr(appBuilder, "App Builder is required, the plugin should return an actual instance.");
        Unchecked.nullPtr(guiBuilder, "Gui Builder is required, the plugin should return an actual instance.");
        Unchecked.nullPtr(initBuilder, "Init Builder is required, the plugin should return an actual instance.");
    }

    /**
     * Finds the class with the given class name implementing the {@link Plugin} interface, and tries
     * to load it. Whenever an error occurs, an instance of {@link InternalNullPlugin} will be
     * returned. In all cases, no exception should be thrown from this method.
     * @param rawClazz the full class name (e.g. read from a configuration file, or the command line)
     * @return the loaded {@link Plugin} object, or an {@link InternalNullPlugin} if something went wrong
     */
    private static Plugin findPlugin(String rawClazz) {
        Plugin plugin = null;
        try {
            plugin = loadPlugin(rawClazz);
        } catch (LoadingFailedException e) {
            Log.error(e);
            String nllClazz = InternalNullPlugin.class.getSimpleName();
            Log.warn(WarnMsg.PLUGING_LOAD_FAILED, nllClazz);
            return new InternalNullPlugin(rawClazz, e.getMessage());
        }

        return plugin;
    }

    /**
     * Given a class, this method tries to reflectively load it. Whenever an exception
     * is thrown, it is wrapped in a {@link LoadingFailedException} and upthrown as such.
     * @param rawClazz the name of the class to instantiate reflectively
     * @return an object implementing {@link Plugin}
     * @throws LoadingFailedException when instantiating the class failed, or the
     */
    private static Plugin loadPlugin(String rawClazz) throws LoadingFailedException {
        if (rawClazz == null) {
            Log.error(ErrorMsg.PLUGIN_LOAD_NO_PLUGIN_CLASS);
            throw new LoadingFailedException(LoadFailedMessage.NOT_SPECIFIED);
        }

        String nllClazz = InternalNullPlugin.class.getSimpleName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(rawClazz);
        } catch (ClassNotFoundException ex) {
            Log.error(ErrorMsg.PLUGIN_LOAD_INVALID_CLASS, MetaConfigKey.PLUGIN.getExternalIdentifier(), nllClazz);
            throw new LoadingFailedException(LoadFailedMessage.INVALID_CLASS);
        }

        if (!Plugin.class.isAssignableFrom(clazz)) {
            throw new LoadingFailedException(LoadFailedMessage.NO_INTERFACE);
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException ex) { // yes, we do want to be extremely robust here as we know nothing about
            // the class
            throw new LoadingFailedException(ex, "the class " + clazz.getSimpleName()
                            + " could not be instantiated using a nullary constructor");
        } catch (IllegalAccessException ex) {
            throw new LoadingFailedException(ex, "the class is inaccessible");
        } catch (RuntimeException ex) {
            throw new LoadingFailedException(ex, "a runtime exception was thrown while instantiating the class");
        }

        Plugin plugin = (Plugin) obj;
        return plugin;
    }

    /**
     * Gets the splash loading screen. The splash screen is determined via the plugin only, using
     * {@link Plugin#getSplash()}. The handling of the returned value of that method is
     * handled as follows:
     * <ul>
     * <li>if {@code null} was returned, this method will also return {@code null};
     * <li>if a non-{@code null} {@code String} was returned, and it points to a valid image file, then this method
     * returns a {@code Splash} object that uses that image;
     * <li>if a non-{@code null} {@code String} was returned which does not point to a valid image file, then this
     * method returns a {@code Splash} object that uses a programmatically generated image.
     * </ul>
     * The programmatically generated image, if used, is implemented by {@link RedCrossDrawable}. The intent
     * of the image is to track image references that cannot be found, by providing an eye-catching
     * image, rather than showing nothing and silently accepting the error.
     * @return the splash which can be shown while the application is loading in the background, or {@code null} if no
     * splash should be shown
     */
    InternalSplashWrapper getSplash() {
        Splash splash = bootBuilder.getPlugin().getSplash();
        if (splash == null) {
            return null;
        }

        return new InternalSplashWrapper(splash, this);
    }

    /**
     * Adds a loading listener to the main loader, that is notified whenever a {@link Loader} starts
     * or stops loading, or when the complete load operation finishes.
     * @param listener the loading listener
     */
    void addLoadingListener(LoadingListener listener) {
        listeners.add(listener);
    }

    private <T extends Buildable> void addLoaderWeights(Map<LoaderKey, ? extends Loader<T>> loaders) {
        for (LoaderKey loadkey : loaders.keySet()) {
            Loader<?> ldb = loaders.get(loadkey);
            LoadingWeight weight = ldb.getWeight();
            if (weight == null) {
                throw new NullPointerException("Loader weight cannot be null: " + ldb.getClass().getSimpleName());
            }
            max += weight.getWeight();
        }
    }

    /**
     * Loads all Loaders in the given map, injecting the given builder into each one of them.
     * @param <T> the type of the builder
     * @param builder the builder
     * @param loaders the Loaders working on the builder
     * @throws LoadingFailedException when a loader throws a {@code LoadingFailedException} or
     * {@code RuntimeException}, it will be rewrapped in a new {@code LoadingFailedException} and upthrown as such
     */
    private <T extends Buildable> void loadLoaders(T builder, Map<LoaderKey, ? extends Loader<T>> loaders)
                    throws LoadingFailedException {
        for (LoaderKey id : loaders.keySet()) {
            Loader<T> ldb = loaders.get(id);
            Log.info(InfoMsg.LOAD_LOADER, id, ldb.getClass().getSimpleName());
            fireLoadingStarted(ldb);
            try {
                ldb.load(builder);
            } catch (LoadingFailedException e) {
                throw new LoadingFailedException(e, "load failure in " + ldb.getInternalName());
            } catch (RuntimeException e) {
                throw new LoadingFailedException(e, "uncaught exception in " + ldb.getInternalName() + ": "
                                + e.getMessage());
            }
            fireLoadingEnded(ldb);
        }
    }

    /**
     * Starts the load procedure.
     * @throws LoadingFailedException when a loader fails due to an deeper {@link LoadingFailedException} or a
     * {@link RuntimeException}
     */
    void load() throws LoadingFailedException {
        try {
            loadImpl();
        } catch (RuntimeException e) {
            throw new LoadingFailedException(e, "programming error.");
        } catch (Error e) {
            throw new LoadingFailedException(e, "an unrecoverable error occured.");
        }
    }

    private void loadImpl () throws LoadingFailedException {
        Plugin plugin = bootBuilder.getPlugin();

        // LinkedHashMap default constructor uses insertion-order, which is what we want, because if a plugin
        // overrules an existing loader, we don't want the order to change!
        Map<LoaderKey, BootLoader> bootLoaders = new LinkedHashMap<>();
        plugin.putBootLoaders(bootLoaders);
        addLoaderWeights(bootLoaders);

        Map<LoaderKey, AppLoader> appLoaders = new LinkedHashMap<>();
        plugin.putAppLoaders(appLoaders);
        addLoaderWeights(appLoaders);

        Map<LoaderKey, GuiLoader> guiLoaders = new LinkedHashMap<>();
        plugin.putGuiLoaders(guiLoaders);
        addLoaderWeights(guiLoaders);

        Map<LoaderKey, InitLoader> initLoaders = new LinkedHashMap<>();
        plugin.putInitLoaders(initLoaders);
        addLoaderWeights(initLoaders);

        max++; // we start progress at 1
        loaded = 1;

        Log.info(InfoMsg.LOAD_PHASE, "I", "Boot Loaders");
        loadLoaders(bootBuilder, bootLoaders);
        DefaultBoot boot = bootBuilder.build();
        DefaultBoot.install(boot);

        Log.info(InfoMsg.LOAD_PHASE, "II", "Core Loaders");
        loadLoaders(appBuilder, appLoaders);
        DefaultApp application = appBuilder.build();
        DefaultApp.install(application);

        Log.info(InfoMsg.LOAD_PHASE, "III", "GUI Loaders");
        loadLoaders(guiBuilder, guiLoaders);
        DefaultGui gui = guiBuilder.build();
        DefaultGui.install(gui);

        Log.info(InfoMsg.LOAD_PHASE, "IV", "Initializers");
        loadLoaders(initBuilder, initLoaders);

        fireLoadingFinished();

        plugin.onLoadingSucceeded ();
    }

    private void fireLoadingStarted(Loader<?> loader) {
        LoadingEvent ev = new LoadingEvent(loaded, max, loader);
        for (LoadingListener listener : listeners) {
            listener.loadingStarted(ev);
        }
    }

    private void fireLoadingEnded(Loader<?> loader) {
        loaded += loader.getWeight().getWeight();
        LoadingEvent ev = new LoadingEvent(loaded, max, loader);
        for (LoadingListener listener : listeners) {
            listener.loadingEnded(ev);
        }
    }

    private void fireLoadingFinished() {
        for (LoadingListener listener : listeners) {
            listener.loadingFinished();
        }
    }

    private static Configuration loadMetaConfiguration (Configuration args) {
        String cfgFilename = args.getValue(MetaConfigKey.META_CFG_FILE.getExternalIdentifier());
        if (cfgFilename == null)
            cfgFilename = MetaConfigKey.META_CFG_FILE.getDefaultValue();
        ConfigurationStream stream = new FileConfigurationStream(new File (cfgFilename), false);
        Configuration fileCfg = new ConcreteConfiguration(stream);
        fileCfg.load();
        Thread.setDefaultUncaughtExceptionHandler(new InternalUncaughtExceptionHandler());
        return fileCfg;
    }
}
