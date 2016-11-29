package core.serial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;

import core.log.msg.InfoMsg;
import core.util.Log;

public class DefaultConverterFactory implements ConverterFactory {

    private final Map<Class<?>, Map<Class<?>, Converter<?,?>>> converters;

    private DefaultConverterFactory (Map<Class<?>, Map<Class<?>, Converter<?,?>>> converters) {
        this.converters = converters;
    }

    @Override
    public <I, O> Converter<I, O> getConverter(Class<I> inner, Class<O> outer) {
        Map<Class<?>, Converter<?,?>> outers = converters.get(inner);
        Preconditions.checkNotNull(outers, "No converters installed for: " + inner.getSimpleName());

        @SuppressWarnings("unchecked") // compile-time checked by the builder
        Converter<I,O> conv = (Converter<I, O>) outers.get(outer);
        Preconditions.checkNotNull(conv, "No converter installed for: " + inner.getSimpleName() + " <=> " + outer.getSimpleName());

        return conv;
    }

    @Override
    public <I, O> List<Converter<I, O>> getConverters(Class<I> root, Class<O> outer) {
        Set<Class<?>> clzz = converters.keySet();
        List<Converter<I, O>> descendants = new ArrayList<>();
        for (Class<?> cls : clzz) {
            if (!root.isAssignableFrom(cls)) {
                continue;
            }

            Map<Class<?>, Converter<?, ?>> outers = converters.get(cls);
            @SuppressWarnings("unchecked")
            Converter<I, O> conv = (Converter<I, O>) outers.get(outer);
            if (conv == null) {
                continue;
            }

            descendants.add(conv);
        }
        return descendants;
    }

    public static final class Builder implements ConverterFactoryBuilder {

        private final Map<Class<?>, Map<Class<?>, Converter<?,?>>> converters;

        public Builder () {
            this.converters = new LinkedHashMap<>();
        }

        @Override
        public <I, O> void install(Class<I> inner, Class<O> outer, Converter<I, O> converter) {
            Map<Class<?>, Converter<?,?>> outers;
            if ((outers = converters.get(inner)) == null) {
                outers = new HashMap<Class<?>, Converter<?,?>>();
                converters.put(inner, outers);
            }

            Preconditions.checkState(!outers.containsKey(outer),
                            "Converter already installed: " + inner.getSimpleName() + " <=> " + outer.getSimpleName());

            String clzIn = inner.getSimpleName();
            String clzOut = outer.getSimpleName();
            Log.info(InfoMsg.CONVERTER_INSTALLED, clzIn, clzOut);
            outers.put(outer, converter);
        }

        @Override
        public ConverterFactory build() {
            return new DefaultConverterFactory(converters);
        }
    }
}
