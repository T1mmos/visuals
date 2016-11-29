package core.serial;

import java.util.List;

public interface ConverterFactory {
    public <I,O> Converter<I,O> getConverter (Class<I> inner, Class<O> outer);

    public <I, O> List<Converter<I, O>> getConverters(Class<I> iface, Class<O> outer);
}
