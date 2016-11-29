package core.log.msg;


public enum WarnMsg implements LogMessage {


    PLUGING_LOAD_FAILED("Loading plugin failed. Falling back onto: %s"),
    RESOURCE_NOWHERE_FOUND("Resource '%s' not found in any resource location"),
    RESOURCE_LOAD_FAIL("Could not find %s"),

    WRONG_LOG_LEVEL("Log level out of bounds (%s, should be in range %s-%s), taking default: %s"),

    ;

    private final String msg;

    private WarnMsg (String msg) {
        this.msg = msg;

    }

    @Override
    public String getFormat() {
        return msg;
    }
}
