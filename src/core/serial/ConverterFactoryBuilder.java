package core.serial;

import core.kernel.Buildable;

public interface ConverterFactoryBuilder extends Buildable {

    public <I, O> void install(Class<I> inner, Class<O> outer, Converter<I, O> converter);

    @Override
    public ConverterFactory build();
}
