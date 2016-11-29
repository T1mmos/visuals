package core.serial;

import core.loc.DefaultLanguageKey;

public class DefaultLanguageConverter implements Converter<DefaultLanguageKey, String> {

    @Override
    public String externalize(DefaultLanguageKey value) {
        return value.getExternalIdentifier();
    }

    @Override
    public DefaultLanguageKey internalize(String s) throws ConversionException {
        for (DefaultLanguageKey key : DefaultLanguageKey.values()) {
            if (key.getExternalIdentifier().equals(s)) {
                return key;
            }
        }
        throw new ConversionException(s, DefaultLanguageKey.class);
    }

    @Override
    public Class<DefaultLanguageKey> getInternalClass() {
        return DefaultLanguageKey.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }

}
