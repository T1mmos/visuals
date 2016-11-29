package core.log.msg;

public enum ErrorMsg implements LogMessage {

    COMMAND_UNCHECKED_EXCEPTION         ("An unchecked exception was thrown in a command!"),

    CONFIG_INTERNALIZATION_FAIL         ("Could not internalize config value '%s' for key '%s'; taking default value '%s'"),
    CONFIG_SAVE_FAIL                    ("Failed to persist the configuration on application exit"),
    CONFIG_STREAM_READ_FAIL             ("Failed to read the configuration stream: %s"),

    DND_UNEXPECTED_FLAVOR_FILELISTS     ("Unexpected DataFlavor exception: file lists should be supported"),
    DND_UNEXPECTED_NULL                 ("Unexpected drop: object is null"),

    EXCEPTION_HANDLER_ERROR             ("An Error was received in the exception handler!"),
    EXCEPTION_HANDLER_UNCAUGHT          ("An uncaught exception was received in the exception handler!"),

    PLUGIN_LOAD_NO_PLUGIN_CLASS         ("no plugin class was specified"),
    PLUGIN_LOAD_INVALID_CLASS           ("%s doesn't point to a valid class. Falling back onto: %s"),

    RESOURCE_CLOSE_FAIL                 ("Failed to close input stream: %s"),
    RESOURCE_ICON_MISSING               ("Wrong or missing icon resource: %s (class = %s)"),
    RESOURCE_SKINNED_ICON_NAME          ("Malformed name or missing skinned icon resource: %s"),
    RESOURCE_SKINNED_ICON_LOAD          ("Failed to read skinned icon resource: %s"),
    RESOURCE_FALLBACK_ICON_MISSING      ("Missing the fallback icon. How dare you to delete it! (%s)"),

    SPLASH_RETRIEVAL_FAIL               ("The main loader failed to obtain a splash screen. Ignoring this, as not fatal; no splash screen will show up."),

    TRACKABLE_FUTURE                    ("Future of Trackable threw an Exception. %s"),

    APPLICATION_QUIT_RTE                ("A %s was thrown while stopping the application"),
    ;






    private final String msg;

    private ErrorMsg (String msg) {
        this.msg = msg;
    }

    @Override
    public String getFormat() {
        return msg;
    }

}
