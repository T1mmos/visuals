package gent.timdemey.visuals.core.kernel;

import java.util.Map;

import gent.timdemey.visuals.core.action.DefaultActionFactoryLoader;
import gent.timdemey.visuals.core.cfg.Configuration;
import gent.timdemey.visuals.core.cfg.DefaultConfigurationLoader;
import gent.timdemey.visuals.core.cmd.DefaultCommandManagerLoader;
import gent.timdemey.visuals.core.loc.DefaultLocalizatorLoader;
import gent.timdemey.visuals.core.log.DefaultLoggerLoader;
import gent.timdemey.visuals.core.res.DefaultResourceManagerLoader;
import gent.timdemey.visuals.core.serial.DefaultConverterFactoryLoader;
import gent.timdemey.visuals.core.ui.DefaultGuiSystemLoader;
import gent.timdemey.visuals.core.ui.DefaultIconFactoryLoader;
import gent.timdemey.visuals.core.ui.DefaultLookAndFeelLoader;
import gent.timdemey.visuals.core.ui.DefaultRenderFactoryLoader;
import gent.timdemey.visuals.core.ui.RedCrossDrawable;
import gent.timdemey.visuals.core.util.Gui;

/**
 * The core's default plugin. This class is intended to be overridden and provides a minimalistic
 * showcase for using the framework. Hence, using this plugin directly does work but results
 * in an application with no features.
 * <p>This implementation starts the GUI system when loading was successful, resulting in a
 * window without any features.
 * @author Timmos
 */
public class DefaultPlugin implements Plugin {

    @Override
    public Splash getSplash () {
        return new DefaultSplash(new RedCrossDrawable("TEST").getIcon());
    }

    @Override
    public void onLoadingSucceeded() {
        Gui.getGuiSystem().start();
    }

    @Override
    public DefaultBootBuilder newBootBuilder(Configuration meta, Configuration args) {
        return new DefaultBootBuilder(this, meta, args);
    }

    @Override
    public DefaultAppBuilder newAppBuilder() {
        return new DefaultAppBuilder();
    }

    @Override
    public DefaultGuiBuilder newGuiBuilder() {
        return new DefaultGuiBuilder();
    }

    @Override
    public DefaultInitBuilder newInitBuilder() {
        return new DefaultInitBuilder();
    }


    @Override
    public void putBootLoaders(Map<LoaderKey, BootLoader> loaders) {
        loaders.put(DefaultLoaderKey.CONFIGURATION, new DefaultConfigurationLoader());
        loaders.put(DefaultLoaderKey.RESOURCE_MANAGER, new DefaultResourceManagerLoader());
        loaders.put(DefaultLoaderKey.CONVERTERS, new DefaultConverterFactoryLoader());
    }

    @Override
    public void putAppLoaders(Map<LoaderKey, AppLoader> loaders) {
        loaders.put(DefaultLoaderKey.LOGGER, new DefaultLoggerLoader());
        loaders.put(DefaultLoaderKey.LOCALIZATOR, new DefaultLocalizatorLoader());
        loaders.put(DefaultLoaderKey.COMMAND_MANAGER, new DefaultCommandManagerLoader());
        loaders.put(DefaultLoaderKey.EXIT_CALLBACK, new DefaultExitCallbackLoader());
    }

    @Override
    public void putGuiLoaders(Map<LoaderKey, GuiLoader> loaders) {
        loaders.put(DefaultLoaderKey.LOOK_AND_FEEL, new DefaultLookAndFeelLoader());
        loaders.put(DefaultLoaderKey.ACTION_FACTORY, new DefaultActionFactoryLoader());
        loaders.put(DefaultLoaderKey.GUI_SYSTEM, new DefaultGuiSystemLoader());
        loaders.put(DefaultLoaderKey.ICON_FACTORY, new DefaultIconFactoryLoader());
        loaders.put(DefaultLoaderKey.UI_RENDERERS, new DefaultRenderFactoryLoader());
    }

    @Override
    public void putInitLoaders(Map<LoaderKey, InitLoader> loaders) {
        // there is nothing to initialize
    }
}
