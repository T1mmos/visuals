package core.serial;

import core.ui.DefaultUISkin;

public class DefaultUISkinConverter implements Converter<DefaultUISkin, String> {

    @Override
    public Class<DefaultUISkin> getInternalClass() {
        return DefaultUISkin.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }

    @Override
    public String externalize(DefaultUISkin value) {
        return value.getName();
    }

    @Override
    public DefaultUISkin internalize(String s) throws ConversionException {
        for (DefaultUISkin skin : DefaultUISkin.values()) {
            if (skin.getName().equals(s)) {
                return skin;
            }
        }
        throw new ConversionException(s, getInternalClass());
    }
}
