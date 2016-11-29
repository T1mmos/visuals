package core.log;

/**
 * Allows to log application events.
 * @author Timmos
 */
public interface Logger {
    
    /**
     * Logs an object (which may be a simple String). The implementation may decide
     * how to fetch printable information from the object, but a decent logger
     * should check whether the given object implements {@link Debuggable}.
     * @param lvl the log level
     * @param obj the object to log
     */
    public void log (LogLevel lvl, Object obj);
    
    /**
     * Logs a message with the given formatting. As with {@link #log(LogLevel, Object)}, the objects
     * may be implementing {@link Debuggable}; the implementation decides whether the interface
     * will be used.
     * @param lvl the log level
     * @param format the format string
     * @param args the objects to use in the format string
     */
    public void log (LogLevel lvl, String format, Object ... args);
    
    /**
     * Logs a {@link Throwable}.
     * @param lvl the log level
     * @param t the throwable
     */
    public void log (LogLevel lvl, Throwable t);
}
