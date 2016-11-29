package core.kernel;

import core.cfg.Configuration;
import core.res.ResourceManager;
import core.serial.ConverterFactory;


public class DefaultBootBuilder implements Buildable {

    /** The plugin. */
    private final Plugin        plugin;
    private final Configuration metaCfg;
    private final Configuration argsCfg;

    private Configuration config = null;
    /** The resource manager. */
    protected ResourceManager   resMan = null;
    /** The converter factory. */
    protected ConverterFactory  convFact = null;

    public DefaultBootBuilder(Plugin plugin, Configuration metaCfg, Configuration argsCfg) {
        this.plugin = plugin;
        this.metaCfg = metaCfg;
        this.argsCfg = argsCfg;
    }

    /**
     * Gets the plugin's implementation of {@link Plugin}. This method
     * should only be called only by the core's {@link InternalMainLoader}.
     * @return the plugin's implementation of Plugin
     */
    Plugin getPlugin() {
        return plugin;
    }

    public Configuration getArgsConfiguration() {
        return argsCfg;
    }

    public Configuration getMetaConfiguration() {
        return metaCfg;
    }

    public void setConfiguration(Configuration config) {
        this.config = config;
    }

    /**
     * Sets the resource manager.
     * @param manager the resource manager.
     */
    public void setResourceManager(ResourceManager manager) {
        this.resMan = manager;
    }

    /**
     * Sets the converter factory.
     * @param convFact the converter factory
     */
    public void setConverterFactory(ConverterFactory convFact) {
        this.convFact = convFact;
    }

    @Override
    public DefaultBoot build() {
        return new DefaultBoot(metaCfg, config, resMan, convFact);
    }

}
