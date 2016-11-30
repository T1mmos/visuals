package gent.timdemey.visuals.core.serial;

public class BooleanConverter implements Converter<Boolean,String> {


    @Override
    public String externalize(Boolean value) {
        return value.toString();
    }

    @Override
    public Boolean internalize(String s) throws ConversionException {
        // don't use built-in Boolean class methods: it doesn't throw exceptions and we want exact matches
        String low = s.toLowerCase();
        if ("false".equals(low) || "0".equals(low)) {
            return Boolean.FALSE;
        }
        if ("true".equals(low) || "1".equals(low)) {
            return Boolean.TRUE;
        }

        throw new ConversionException("no exact boolean match found", Boolean.class);
    }

    @Override
    public Class<Boolean> getInternalClass() {
        return Boolean.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }
}
