package core.serial;

/**
 * Thrown when converting an external resource into an internal value failed.
 * @author Timmos
 */
public class ConversionException extends Exception {

    /**
     * Creates a conversion exception.
     * @param external the external object that could not be converted
     * @param internal the type that the external should have been converted to
     */
    public ConversionException(Object external, Class<?> internal) {
        this(createMsg(external, internal));
    }

    /**
     * Creates a conversion exception.
     * @param external the external object that could not be converted
     * @param internal the type that the external should have been converted to
     * @param cause the lower-level exception, thrown during conversion
     */
    public ConversionException(Object external, Class<?> internal, Throwable cause) {
        this(createMsg(external, internal), cause);
    }

    private ConversionException (String msg) {
        super(msg);
    }

    private ConversionException (String msg, Throwable cause) {
        super(msg, cause);
    }

    private static String createMsg (Object external, Class<?> internal) {
        if (external == null) {
            return "Undefined externalized object (null), cannot convert into " + internal.getSimpleName();
        }
        return "Failed to convert class " + external.getClass().getSimpleName() + " into " + internal.getSimpleName()
                        + ": " + external;
    }
}
