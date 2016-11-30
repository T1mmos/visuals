package gent.timdemey.visuals.demo.serial;

import gent.timdemey.visuals.core.serial.ConverterFactoryBuilder;
import gent.timdemey.visuals.core.serial.DefaultConverterFactoryLoader;
import gent.timdemey.visuals.demo.loc.DemoLanguageKey;


public class DemoConverterFactoryLoader extends DefaultConverterFactoryLoader {

    @Override
    protected void installConverters(ConverterFactoryBuilder builder) {
        super.installConverters(builder);
        builder.install(DemoLanguageKey.class, String.class, new DemoLanguageConverter());
    }
}
