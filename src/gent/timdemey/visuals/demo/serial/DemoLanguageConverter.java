package gent.timdemey.visuals.demo.serial;

import gent.timdemey.visuals.core.serial.ConversionException;
import gent.timdemey.visuals.core.serial.Converter;
import gent.timdemey.visuals.demo.loc.DemoLanguageKey;

public class DemoLanguageConverter implements Converter<DemoLanguageKey, String> {

    @Override
    public String externalize(DemoLanguageKey value) {
        return value.getExternalIdentifier();
    }

    @Override
    public DemoLanguageKey internalize(String s) throws ConversionException {
        for (DemoLanguageKey key : DemoLanguageKey.values()) {
            if (key.getExternalIdentifier().equals(s)) {
                return key;
            }
        }
        throw new ConversionException(s, DemoLanguageKey.class);
    }

    @Override
    public Class<DemoLanguageKey> getInternalClass() {
        return DemoLanguageKey.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }
}
