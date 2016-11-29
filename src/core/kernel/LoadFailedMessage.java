package core.kernel;

/**
 * Enumerates the reasons why the framework can't load a plugin. The specified messages are to
 * be inserted in a sentence, as in "... failed to load because <message>", to create a
 * well written English sentence. The full string is then used in {@link InternalNullPlugin}.
 * @author Timmos
 */
enum LoadFailedMessage {

    /** No plugin was specified whatsoever. */
    NOT_SPECIFIED ("no plugin class was specified"),
    /** The plugin configuration key was set, but it's not pointing to a class (or the class cannot be found, due to a classpath problem, ...). */
    INVALID_CLASS("the string doesn't represent a valid class"),
    /** The plugin configuration was set and points to a class, but it's not implementing the Plugin interface. */
    NO_INTERFACE("the class doesn't implement the Plugin interface");

    private final String msg;

    private LoadFailedMessage (String msg) {
        this.msg = msg;
    }

    String getMessage () {
        return msg;
    }
}
