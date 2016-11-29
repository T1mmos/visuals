package core.log.msg;


public enum InfoMsg implements LogMessage {

    CONFIG_ARGS("Arguments configuration: %s"),
    CONFIG_META("Meta configuration: %s"),
    CONFIG_WRITTEN_KEY("Wrote %s=%s"),
    CONFIG_PERSISTED("Application Configuration persisted to %s"),
    CONFIG_INTERNALIZATION_SUCCESS("Internalized '%s' for key '%s' into object of class '%s' (for requested ancestor '%s')"),
    CONFIG_INTERNALIZATION_ATTEMPT("Trying to internalize value '%s' using converter %s<=>%s (for requested ancestor '%s')"),
    CONFIG_NORMAL("Loaded Application Configuration: %s"),

    CONVERTER_INSTALLED("Installed converter %s<=>%s"),


    LOAD_LOADER("Loading: %-20s==> %-40s"),
    LOAD_PHASE("Loading Phase %s: %s"),
    LOAD_FINISHED("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Application Loading finished ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"),
    LOAD_STARTED ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Application Loading started ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"),
    LOAD_LOGGING_STARTED("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Now logging on installed logger ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"),

    LOGGING_SET_LEVEL("Logging level set to %s (%s)"),

    RAW_LOGGING_END("Raw logging will now end. Logging should continue on the installed logger."),
    RESOURCE_SEARCHING("Loaded [%s]/%s"),
    RESOURCE_LOCATION_TABLE_TITLE("  Resource Locations for Resource Category %s:"),
    RESOURCE_LOCATION_TABLE_HEADER("     %-20s   %-20s"),
    RESOURCE_LOCATION_TABLE_SEPARATOR("     ---------------------------- "),
    RESOURCE_LOCATION_TABLE_ENTRY(" %2d. %-20s   %-20s"),
    TRACKABLE_OK("Trackable OK: %s"),
    TRACKABLE_CANCELED("Future of Trackable was cancelled. %s"),
    THREAD_STARTED("Thread '%s' started."),
    THREAD_FINISHED("Thread '%s' finished.");

    private final String msg;

    private InfoMsg(String msg) {
        this.msg= msg;
    }

    @Override
    public java.lang.String getFormat() {
        return msg;
    }
}
