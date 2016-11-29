package core.kernel;

/**
 * Lists all the possible exit codes of the application.
 * @author Timmos
 */
public enum ExitCodes {
    /** Indicates a normal shutdown of the application. */
    NORMAL (0),
    /** Indicates that the application shut down due to a thrown {@link Error} within the execution of 
     * the application. */
    ERROR (1),
    /** Indicates that the application cannot start due to a loading error in the main loader. 
     * After some possible action cleanup action, the application should exit with this code.
     */
    LOADING_ERROR (2);
    
    private final int code;
    
    private ExitCodes (int code) {
        this.code = code;
    }
    
    /**
     * Gets the exit code to return to the OS.
     * @return the exit code
     */
    public int getCode () {
        return code;
    }
}
