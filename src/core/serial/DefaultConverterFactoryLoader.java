package core.serial;

import core.kernel.BootLoader;
import core.kernel.DefaultBootBuilder;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.loc.DefaultLanguageKey;
import core.log.LogLevel;
import core.ui.DefaultUISkin;

/**
 * Loads the default {@link ConverterFactory converter factory}, and installs the
 * default {@link Converter converters} on it.
 * @author Timmos
 */
public class DefaultConverterFactoryLoader implements BootLoader {

    @Override
    public void load(DefaultBootBuilder builder) throws LoadingFailedException {
        DefaultConverterFactory.Builder factBuilder = new DefaultConverterFactory.Builder();

        installConverters(factBuilder);
        ConverterFactory convFact = factBuilder.build();

        builder.setConverterFactory(convFact);
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }

    @Override
    public String getInternalName() {
        return "Convertables";
    }

    protected void installConverters (ConverterFactoryBuilder builder) {
        builder.install (Integer.class, String.class, new IntegerConverter());
        builder.install (String.class, String.class, new StringConverter());
        builder.install (Class.class, String.class, new ClassConverter());
        builder.install (int.class, String.class, new IntegerConverter());
        builder.install (Boolean.class, String.class, new BooleanConverter());
        builder.install (boolean.class, String.class, new BooleanConverter());
        builder.install(DefaultLanguageKey.class, String.class, new DefaultLanguageConverter());
        builder.install(LogLevel.class, String.class, new LogLevelConverter());
        builder.install(DefaultUISkin.class, String.class, new DefaultUISkinConverter());
    }
}
