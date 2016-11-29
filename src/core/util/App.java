package core.util;

import core.cfg.Configuration;
import core.kernel.DefaultApp;
import core.kernel.DefaultBoot;
import core.res.ResourceManager;
import core.serial.ConverterFactory;

/**
 * Bundles various shortcut methods that give access to application handles.
 * <p>Normally, users would access the application handles by visiting {@link DefaultApp} (or a
 * subclass). This class provides shortcut methods so clients are able to write concise code.
 * @author Timmos
 * @see Gui
 */
public final class App {

    private App () {}

    /**
     * Returns the application configuration handle as set in {@link DefaultBoot}.
     * @return the application configuration
     */
    public static Configuration getConfiguration() {
        return DefaultBoot.instance().getConfiguration();
    }

    /**
     * Returns the meta configuration handle as set in {@link DefaultBoot}.
     * @return the meta configuration
     */
    public static Configuration getMetaConfiguration() {
        return DefaultBoot.instance().getConfiguration();
    }

    /**
     * Returns the resource manager handle as set in {@link DefaultBoot}.
     * @return the resource manager
     */
    public static ResourceManager getResourceManager() {
        return DefaultBoot.instance().getResourceManager();
    }

    public static ConverterFactory getConverterFactory() {
        return DefaultBoot.instance().getConverterFactory();
    }

    /**
     * Returns the application root handle as set in {@link DefaultApp}.
     * This method downcasts to the requested subtype internally by type inference,
     * so callers may use the method as follows:
     * <pre>{@code FooDefaultApp foo = App.get(); } </pre>
     * Of course, the plugin should have installed a {@code FooDefaultApp} (or subtype) during application load,
     * otherwise a {@code ClassCastException} will be thrown.
     * @param <T> the actual subtype of {@link DefaultApp}
     * @return A {@code DefaultApp} downcasted to {@code T}
     * @throws ClassCastException when downcasting to {@code T} fails, which may be the result of the plugin
     * not having installed an object of type {@code T} during application load
     */
    public static <T extends DefaultApp> T get (){
        return Various.downcast(DefaultApp.instance());
    }
}
