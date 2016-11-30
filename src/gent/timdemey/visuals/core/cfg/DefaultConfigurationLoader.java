package gent.timdemey.visuals.core.cfg;

import java.io.File;

import gent.timdemey.visuals.core.kernel.BootLoader;
import gent.timdemey.visuals.core.kernel.DefaultBootBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.log.msg.InfoMsg;
import gent.timdemey.visuals.core.util.Log;

/**
 * A loadable that reads the user configuration file and installs the configuration the
 * boot builder.
 * @author Timmos
 */
public class DefaultConfigurationLoader implements BootLoader {

    @Override
    public void load(DefaultBootBuilder builder) throws LoadingFailedException {
        Configuration metaCfg = builder.getMetaConfiguration();
        String filename = metaCfg.getValue(MetaConfigKey.CFG_FILE.getExternalIdentifier());
        if (filename == null)
            filename = MetaConfigKey.CFG_FILE.getDefaultValue();
        ConfigurationStream stream = new FileConfigurationStream(new File (filename), true);
        Configuration conf = new ConcreteConfiguration(stream);
        conf.load();
        builder.setConfiguration(new CompositeConfiguration(builder.getArgsConfiguration(), conf, DefaultConfiguration.INSTANCE));

        Log.info(InfoMsg.CONFIG_NORMAL, conf);
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }

    @Override
    public String getInternalName() {
        return "Default Configuration Loader";
    }
}
