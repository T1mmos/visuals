package core.kernel;

/**
 * Listens to the core loader's load progress. 
 * @author Timmos
 */
public interface LoadingListener {
    
    /**
     * Informs the listener that the core loader started with loading the next {@link Loader}.
     * @param e the loading event giving an update about the core loader's state 
     */
    void loadingStarted (LoadingEvent e);
    
    /**
     * Informs the listener that the core loader ended the loading of the current {@link Loader}.
     * @param e the loading event giving an update about the core loader's state 
     */
    void loadingEnded (LoadingEvent e);
    
    /**
     * Informs the listener that the core loader finished the entire load procedure.
     */
    void loadingFinished ();
}
