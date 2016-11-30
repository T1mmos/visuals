package gent.timdemey.visuals.core.kernel;

/**
 * Allows to be notified of caught runtime exceptions and errors.
 * @author Timmos
 */
public interface ExceptionListener {
    
    /**
     * Notifies this listener that a runtime exception was caught on the
     * given thread.
     * @param rte the runtime exception
     * @param t the thread where the runtime exception was un
     */
    public void exceptionCaught (RuntimeException rte, Thread t);
    
    /**
     * Notifies this listener that an error was caught on the
     * given thread. It is recommended to close the application when this
     * occurs, as the application may be left unstable.
     * @param error the runtime exception
     * @param t the thread where the runtime exception was un
     */
    public void errorCaught (Error error, Thread t);
}
