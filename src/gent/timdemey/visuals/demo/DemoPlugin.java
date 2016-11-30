package gent.timdemey.visuals.demo;

import java.util.Map;

import gent.timdemey.visuals.core.kernel.AppLoader;
import gent.timdemey.visuals.core.kernel.BootLoader;
import gent.timdemey.visuals.core.kernel.DefaultLoaderKey;
import gent.timdemey.visuals.core.kernel.DefaultPlugin;
import gent.timdemey.visuals.core.kernel.GuiLoader;
import gent.timdemey.visuals.core.kernel.InitLoader;
import gent.timdemey.visuals.core.kernel.LoaderKey;
import gent.timdemey.visuals.core.kernel.Splash;
import gent.timdemey.visuals.demo.loading.DemoActionFactoryLoader;
import gent.timdemey.visuals.demo.loading.DemoLoaderKey;
import gent.timdemey.visuals.demo.loading.DemoMenuLoader;
import gent.timdemey.visuals.demo.loading.DemoSmallThingsLoader;
import gent.timdemey.visuals.demo.loading.DemoSplash;
import gent.timdemey.visuals.demo.loc.DemoLocalizatorLoader;
import gent.timdemey.visuals.demo.res.DemoResourceManagerLoader;
import gent.timdemey.visuals.demo.serial.DemoConverterFactoryLoader;
import gent.timdemey.visuals.demo.ui.DemoContentPanelLoader;
import gent.timdemey.visuals.demo.ui.DemoExitCallbackLoader;
import gent.timdemey.visuals.demo.ui.DemoStatusbarLoader;
import gent.timdemey.visuals.demo.ui.DemoToolbarLoader;

/**
 * The plugin for running the gent.timdemey.visuals.demo.
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
