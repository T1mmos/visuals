package core.serial;

public class StringConverter implements Converter<String, String>{

    @Override
    public String externalize(String value) {
        return value;
    }

    @Override
    public String internalize(String s) throws ConversionException {
        if (s == null) {
            throw new ConversionException(s, String.class);
        }
        return s;
    }

    @Override
    public Class<String> getInternalClass() {
        return String.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }
}
