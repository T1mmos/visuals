package core.serial;

public class ClassConverter implements Converter<Class, String> {

    @Override
    public String externalize(Class value) {
        return value.getName();
    }

    @Override
    public Class internalize(String s) throws ConversionException {
        try {
            return Class.forName(s);
        } catch (ClassNotFoundException ex) {
            throw new ConversionException(s, Class.class, ex);
        }
    }

    @Override
    public Class<Class> getInternalClass() {
        return Class.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }
}
