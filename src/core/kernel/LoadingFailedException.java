package core.kernel;

/**
 * Thrown by the core loader or a {@link Loader} when an underlying exception occured during application startup.
 * @author Timmos
 */
public class LoadingFailedException extends Exception {

    LoadingFailedException(LoadFailedMessage msg) {
        super(msg.getMessage());
    }


    /**
     * Creates a new load exception, which is induced by the givne cause.
     * @param cause the actual exception that caused the load operation to fail
     */
    public LoadingFailedException (Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new load exception, which is induced by the givne cause.
     * @param cause the actual exception that caused the load operation to fail
     * @param msg an additional message
     */
    public LoadingFailedException (Throwable cause, String msg) {
        super(msg, cause);
    }
}
