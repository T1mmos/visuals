package core.cfg;

import core.kernel.Plugin;


/**
 * Defines all {@link CfgKeyType#Meta Meta} configuration keys. To specify a key in the program arguments,
 * one uses the raw strings defined in this class. Within code, one should NOT use the raw strings but rather
 * the rich type ({@link DefaultConfigKey}).
 * @author Timmos
 */
public class MetaConfigKey {

    /** Refers to the (non-meta) configuration file. */
    public static final String                 RAW_CFG_FILE      = "cfg.meta.cfgfile";
    /** Refers to the location of the meta configuration file (to be used only in the program arguments). */
    public static final String                 RAW_META_CFG_FILE = "cfg.meta.metacfgfile";
    /** Refers to the plugin. */
    public static final String                 RAW_PLUGIN        = "cfg.meta.plugin";

    /** Points to the meta configuration file. */
    public static final DefaultConfigKey<String> META_CFG_FILE     = strkey(RAW_META_CFG_FILE, "meta.cfg");
    /** Points to the configuration file. */
    public static final DefaultConfigKey<String> CFG_FILE          = strkey(RAW_CFG_FILE, "app.cfg");
    /** Points to the plugin class, which should implement {@link Plugin}. */
    @SuppressWarnings("rawtypes")
    public static final DefaultConfigKey<Class>  PLUGIN            = clskey(RAW_PLUGIN, null);

    private static DefaultConfigKey<String> strkey (String innerKey, String defValue){
        return DefaultConfigKey.strkey(innerKey, CfgKeyType.Meta, defValue);
    }

    @SuppressWarnings("rawtypes")
    private static DefaultConfigKey<Class> clskey (String innerKey, Class<?> defValue){
        return DefaultConfigKey.clskey(innerKey, CfgKeyType.Meta, defValue);
    }
}
