package demo;

import java.util.Map;

import core.kernel.AppLoader;
import core.kernel.BootLoader;
import core.kernel.DefaultLoaderKey;
import core.kernel.DefaultPlugin;
import core.kernel.GuiLoader;
import core.kernel.InitLoader;
import core.kernel.LoaderKey;
import core.kernel.Splash;
import demo.loading.DemoActionFactoryLoader;
import demo.loading.DemoLoaderKey;
import demo.loading.DemoMenuLoader;
import demo.loading.DemoSmallThingsLoader;
import demo.loading.DemoSplash;
import demo.loc.DemoLocalizatorLoader;
import demo.res.DemoResourceManagerLoader;
import demo.serial.DemoConverterFactoryLoader;
import demo.ui.DemoContentPanelLoader;
import demo.ui.DemoExitCallbackLoader;
import demo.ui.DemoStatusbarLoader;
import demo.ui.DemoToolbarLoader;

/**
 * The plugin for running the demo.
 * @author Timmos
 */
public class DemoPlugin extends DefaultPlugin {

    @Override
    public Splash getSplash() {
        return new DemoSplash();
    }

    @Override
    public void putBootLoaders(Map<LoaderKey, BootLoader> loaders) {
        super.putBootLoaders(loaders);
        loaders.put(DefaultLoaderKey.RESOURCE_MANAGER, new DemoResourceManagerLoader());
        loaders.put(DefaultLoaderKey.CONVERTERS, new DemoConverterFactoryLoader());
    }

    @Override
    public void putAppLoaders(Map<LoaderKey, AppLoader> loaders) {
        super.putAppLoaders(loaders);
        loaders.put(DefaultLoaderKey.LOCALIZATOR, new DemoLocalizatorLoader());
        loaders.put(DefaultLoaderKey.EXIT_CALLBACK, new DemoExitCallbackLoader());
    }

    @Override
    public void putGuiLoaders(Map<LoaderKey, GuiLoader> loaders) {
        super.putGuiLoaders(loaders);
        loaders.put(DefaultLoaderKey.ACTION_FACTORY, new DemoActionFactoryLoader());
    }

    @Override
    public void putInitLoaders(Map<LoaderKey, InitLoader> loaders) {
        super.putInitLoaders(loaders);
        loaders.put(DemoLoaderKey.MENU, new DemoMenuLoader());
        loaders.put(DemoLoaderKey.CONTENT_PANEL, new DemoContentPanelLoader());
        loaders.put(DemoLoaderKey.SMALL_THINGS, new DemoSmallThingsLoader());
        loaders.put(DemoLoaderKey.STATUSBAR, new DemoStatusbarLoader());
        loaders.put(DemoLoaderKey.TOOLBAR, new DemoToolbarLoader());
    }
}
