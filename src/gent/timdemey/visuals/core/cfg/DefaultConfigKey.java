package gent.timdemey.visuals.core.cfg;

import java.lang.ref.SoftReference;
import java.util.List;

import gent.timdemey.visuals.core.loc.DefaultLanguageKey;
import gent.timdemey.visuals.core.loc.LanguageKey;
import gent.timdemey.visuals.core.log.LogLevel;
import gent.timdemey.visuals.core.log.msg.ErrorMsg;
import gent.timdemey.visuals.core.log.msg.InfoMsg;
import gent.timdemey.visuals.core.serial.ConversionException;
import gent.timdemey.visuals.core.serial.Converter;
import gent.timdemey.visuals.core.util.App;
import gent.timdemey.visuals.core.util.Log;

/**
 * Final implementation of {@link ConfigKey}.
 * @param <V> the type of the value
 * @author Timmos
 */
public final class DefaultConfigKey<V> implements ConfigKey<V> {

    /** The width of the main application window. */
    public static final DefaultConfigKey<Integer> GUI_MAINFRAME_WIDTH = intkey ("cfg.gui.window.main.width", CfgKeyType.Normal, 800);
    /** The height of the main application window. */
    public static final DefaultConfigKey<Integer> GUI_MAINFRAME_HEIGHT = intkey ("cfg.gui.window.main.height", CfgKeyType.Normal, 600);

    public static final DefaultConfigKey<LanguageKey>  LANGUAGE             = of("cfg.app.lang", CfgKeyType.Normal, LanguageKey.class, DefaultLanguageKey.ENGLISH);

    public static final DefaultConfigKey<LogLevel> LOG_LEVEL            = of("cfg.app.logging.level", CfgKeyType.Normal, LogLevel.class, LogLevel.TRACE);

    /**
     * Creates a config key which value is a {@code String}, with the given properties.
     * @param innerKey the inner key, e.g. used for persistence
     * @param type the type of configuration the key belongs to
     * @param defValue the default value of the config key - can be used by a configuration
     * when the value was not found in the persistence
     * @return a config key
     */
    public static DefaultConfigKey<String> strkey (String innerKey, CfgKeyType type, String defValue){
        return of (innerKey, type, String.class, defValue);
    }

    /**
     * Creates a config key which value is an {@code Integer}, with the given properties.
     * @param innerKey the inner key, e.g. used for persistence
     * @param type the type of configuration the key belongs to
     * @param defValue the default value of the config key - can be used by a configuration
     * when the value was not found in the persistence
     * @return a config key
     */
    public static DefaultConfigKey<Integer> intkey (String innerKey, CfgKeyType type, Integer defValue){
        return of (innerKey, type, Integer.class, defValue);
    }

    /**
     * Creates a config key which value is a {@code Class}, with the given properties.
     * @param innerKey the inner key, e.g. used for persistence
     * @param type the type of configuration the key belongs to
     * @param defValue the default value of the config key - can be used by a configuration
     * when the value was not found in the persistence
     * @return a config key
     */
    @SuppressWarnings("rawtypes")
    public static DefaultConfigKey<Class> clskey (String innerKey, CfgKeyType type, Class<?> defValue){
        return of (innerKey, type, Class.class, defValue);
    }

    private static <V> DefaultConfigKey<V> of (String key, CfgKeyType type, Class<V> clazz, V defValue){
        return new DefaultConfigKey<V>(key, type, clazz, defValue);
    }

    private final String key;
    private final CfgKeyType type;
    private final Class<V> clazz;
    private final V defValue;

    private SoftReference<V> cached;

    private DefaultConfigKey (String key, CfgKeyType type, Class<V> clazz, V defValue){
        this.key = key;
        this.type = type;
        this.clazz = clazz;
        this.defValue = defValue;

        this.cached = new SoftReference<>(null);
    }

    @Override
    public String getExternalIdentifier() {
        return key;
    }

    @Override
    public CfgKeyType getType() {
        return type;
    }

    @Override
    public Class<V> getValueClass() {
        return clazz;
    }

    @Override
    public V getDefaultValue() {
        return defValue;
    }

    @Override
    public String toString() {
        return "[\"" + getExternalIdentifier() + "\", class=" + getValueClass().getSimpleName() + "]";
    }

    @Override
    public V get() {
        V value = cached.get();
        if (value != null) {
            return value;
        }

        String extern = this.getExternalIdentifier();
        String clz = getValueClass().getSimpleName();
        String raw = App.getConfiguration().getValue(extern);

        if (raw != null) {
            List<Converter<V, String>> convs = App.getConverterFactory().getConverters(getValueClass(), String.class);

            for (Converter<V, String> conv : convs) {
                String actInClz = conv.getInternalClass().getSimpleName();
                Log.trace(InfoMsg.CONFIG_INTERNALIZATION_ATTEMPT, raw, actInClz, "String", clz);
                try {
                    value = conv.internalize(raw);
                    Log.info(InfoMsg.CONFIG_INTERNALIZATION_SUCCESS, raw, extern, actInClz, clz);
                    break;
                } catch (ConversionException ex) {
                    // we make several attempts. if no attempt succeeds, we log.
                }
            }
        }

        if (value == null) {
            value = getDefaultValue();
            Log.error(ErrorMsg.CONFIG_INTERNALIZATION_FAIL, raw, extern, value);
        }

        cached = new SoftReference<>(value);
        return value;
    }

    @Override
    public void set(V value) {
        String rawkey = this.getExternalIdentifier();
        Converter<? super V, String> conv = App.getConverterFactory().getConverter((Class<? super V>) value.getClass(), String.class);
        String rawvalue = conv.externalize(value);
        App.getConfiguration().setValue(rawkey, rawvalue);
        cached = new SoftReference<>(value);

        Log.trace(InfoMsg.CONFIG_WRITTEN_KEY, rawkey, rawvalue);
    }
}
