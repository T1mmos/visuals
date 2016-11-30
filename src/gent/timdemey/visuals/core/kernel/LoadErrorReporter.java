package gent.timdemey.visuals.core.kernel;


/**
 * Reports an application loading error to the user, and then terminates the application. 
 * @author Timmos
 */
public interface LoadErrorReporter {
    /**
     * Informs that an exception occured during application load.
     * @param e the {@link LoadingFailedException} that wraps around the source exception
     */
	void report (LoadingFailedException e);
}
