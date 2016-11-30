package gent.timdemey.visuals.core.serial;

import gent.timdemey.visuals.core.key.ExternalKey;

/**
 * Simplifies the conversion between a String (external form) and an object of the type of an
 * (implementation of) {@link ExternalKey} (internal form).
 * <p>
 * The implementation requires that the internal form is an enum, as it relies on {@code Enum.values()} to find the
 * correct internal instance.
 * @author Timmos
 * @param <T>
 */
public class ExternalKeyConverter<T extends Enum<T> & ExternalKey> implements Converter<T, String> {

    private final Class<T> keyclazz;

    private ExternalKeyConverter(Class<T> keyclazz) {
        this.keyclazz = keyclazz;
    }

    public static <T extends Enum<T> & ExternalKey> ExternalKeyConverter<T> create(Class<T> keyclazz) {
        return new ExternalKeyConverter<T>(keyclazz);
    }

    @Override
    public String externalize(T value) {
        return value.getExternalIdentifier();
    }

    @Override
    public T internalize(String s) throws ConversionException {
        T[] values = keyclazz.getEnumConstants();
        for (T value : values) {
            if (value.getExternalIdentifier().equals(s)) {
                return value;
            }
        }
        throw new ConversionException(s, keyclazz);
    }

    @Override
    public Class<T> getInternalClass() {
        return keyclazz;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }

}
