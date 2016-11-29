package core.serial;


/**
 * Defines a two-way conversion between an internal object and an external type used for persistence or
 * serialization.
 * <p>A value of type {@code I} can always be converted into its externalized form, but the
 * reverse is not true. On the other side, in the following code, {@code equal} should always
 * evaluate to {@code true}:
 * <pre>
 * {@code
 * Converter<I,O> converter = getSomeConverter();
 * I someObject = getSomeKey();
 * I conv2 = converter.internalize(converter.externalize(someObject)); // omitted try-catch
 * boolean equal = someObject.equals(conv2);
 * }
 * </pre>
 *
 * @param <I> the internal type
 * @param <O> the external type
 * @author Timmos
 */
public interface Converter<I, O> {

    public Class<I> getInternalClass();

    public Class<O> getExternalClass();

    /**
     * Converts the given value of type {@code I} to its representation in form {@code O}.
     * @param value the value to convert
     * @return the string representation of the given value
     */
    public O externalize (I value);

    /**
     * Converts the given object to an object of type {@code I}.
     * @param s the string to convert
     * @return the object of type {@code V} of the given string
     * @throws ConversionException when conversion is not possible
     */
    public I internalize (O s) throws ConversionException;
}
