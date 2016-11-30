package gent.timdemey.visuals.core.kernel;

import com.google.common.base.Preconditions;

import gent.timdemey.visuals.core.cfg.Configuration;
import gent.timdemey.visuals.core.cfg.DefaultConfiguration;
import gent.timdemey.visuals.core.res.ResourceManager;
import gent.timdemey.visuals.core.serial.ConverterFactory;
import gent.timdemey.visuals.core.util.Unchecked;


public final class DefaultBoot {

    private final Configuration meta;
    private final Configuration normal;
    private final ResourceManager        resMan;
    private final ConverterFactory convFact;

    public DefaultBoot(Configuration meta, Configuration normal, ResourceManager resMan, ConverterFactory convFact) {
        Unchecked.nullPtr(meta, normal, resMan, convFact);
        this.meta = meta;
        this.normal = normal;
        this.resMan = resMan;
        this.convFact = convFact;
    }

    private DefaultBoot() {
        this.meta = DefaultConfiguration.INSTANCE;
        this.normal = DefaultConfiguration.INSTANCE;
        this.resMan = null;
        this.convFact = null;
    }

    static DefaultBoot dummy() {
        return new DefaultBoot();
    }

    /**
     * Gets the meta configuration.
     * @return the meta configuration
     */
    public Configuration getMetaConfiguration() {
        return meta;
    }

    /**
     * Gets the configuration.
     * @return the configuration
     */
    public Configuration getConfiguration() {
        return normal;
    }

    /**
     * Gets the resource manager.
     * @return the resource manager
     */
    public ResourceManager getResourceManager() {
        return resMan;
    }

    /**
     * Gets the converter factory.
     * @return the converter factory
     */
    public ConverterFactory getConverterFactory() {
        return convFact;
    }

    private static DefaultBoot boot = null;

    /**
     * Installs the boot singleton. The backbone cannot be uninstalled.
     * This method must be called only from the main loader.
     * @param backbone the application backbone - an instance of {@link DefaultAppBuilder} or a subclass of it
     * @throws IllegalStateException when attempting to install a backbone more than once
     * @throws NullPointerException when {@code backbone} is {@code null}
     */
    static void install(DefaultBoot backbone) {
        Preconditions.checkNotNull(backbone);
        if (isSet()) {
            throw new IllegalStateException("Boot already set up");
        }
        DefaultBoot.boot = backbone;
    }

    /**
     * Gets the backbone singleton.
     * @return The boot backbone
     * @throws IllegalStateException when invoking this method while the backbone
     * singleton is not (yet) set
     */
    public static DefaultBoot instance() {
        if (!isSet()) {
            throw new IllegalStateException("Application not initialized");
        }
        return DefaultBoot.boot;
    }

    private static boolean isSet() {
        return boot != null;
    }
}
