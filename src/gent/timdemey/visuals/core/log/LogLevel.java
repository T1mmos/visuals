package gent.timdemey.visuals.core.log;

/**
 * Indicates the level of importance for a logging statement.
 * @author Timmos
 */
public enum LogLevel {
    /** A message that allows to trace what is happening, but is generally not
     * required due to low importance or added value. */
    TRACE,
    /** A temporary message, useful e.g. during development. Similar to the trace statement,
     * with the difference that this message should not be kept in official builds. */
    TEMP,
    /** A message telling that some feature is left unfinished, and that furter
     * development work is required. */
    TODO,

    /** An informative statement. */
    INFO,
    /** A warning. This is mostly used for checked exceptions that have low impact (e.g.,
     * not being able to read the application's configuration, hence continuing with a
     * default configuration). */
    WARN,
    /** An error or unexpected external event has occured. This is mostly used for checked
     * exceptions that may limit the application's features (e.g., not being able to connect
     * to a server in a client-server architecture). */
    ERROR,    
    /**
     * Used to print a stack trace.
     */
    STACK,
    /** An error has occured that possibly makes the system unstable, or the
     * system is in such state where normal execution cannot continue. This can be used for
     * {@link Error}s. */
    FATAL,
    /** A (hopefully) temporary message, which indicates that a programming error was discovered but
     * not at the location of the root cause. This level should be used for unchecked exceptions,
     * e.g. in an exception handler or some framework code that is subject to unknown,
     * eventually buggy code that is using the framework. It is used to draw a developer's attention
     * during his developments. Do not use this level in code where the root cause of a bug is situated:
     * if the root cause can't be fixed, use {@link #FIXME} statements instead. */
    BUG,
    /**
     * Indicates a programming error or bug that is known, but is deliberately not being fixed
     * (yet) because of low impact. This level should be used at the location of the root cause of the bug,
     * in contrary to {@link #BUG}.
     */
    FIXME,



}
