package gent.timdemey.visuals.core.serial;

import gent.timdemey.visuals.core.kernel.BootLoader;
import gent.timdemey.visuals.core.kernel.DefaultBootBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.loc.DefaultLanguageKey;
import gent.timdemey.visuals.core.log.LogLevel;

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
    }
}
