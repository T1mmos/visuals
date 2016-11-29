package core.cfg;

import java.io.File;

import core.kernel.BootLoader;
import core.kernel.DefaultBootBuilder;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.log.msg.InfoMsg;
import core.util.Log;

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
