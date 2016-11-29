package core.kernel;

/**
 * A application exit callback, which can cancel the exit.
 * @author Timmos
 *
 */
public interface ExitCallback {
    
    /**
     * Called by the framework when the application is requested to exit. This method
     * is invoked on a worker thread.
     * @return {@code true} when the application has to close, {@code false} otherwise
     */
    public boolean isQuitAllowed ();
}
