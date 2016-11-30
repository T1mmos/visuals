package gent.timdemey.visuals.core.serial;


public class IntegerConverter implements Converter<Integer, String> {

    @Override
    public String externalize(Integer value) {
        return "" + value.intValue();
    }

    @Override
    public Integer internalize(String s) throws ConversionException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ConversionException(s, Integer.class);
        }
    }

    @Override
    public Class<Integer> getInternalClass() {
        return Integer.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }
}
