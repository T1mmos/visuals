package demo.serial;

import core.serial.ConverterFactoryBuilder;
import core.serial.DefaultConverterFactoryLoader;
import demo.loc.DemoLanguageKey;


public class DemoConverterFactoryLoader extends DefaultConverterFactoryLoader {

    @Override
    protected void installConverters(ConverterFactoryBuilder builder) {
        super.installConverters(builder);
        builder.install(DemoLanguageKey.class, String.class, new DemoLanguageConverter());
    }
}
