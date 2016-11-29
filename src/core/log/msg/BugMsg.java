package core.log.msg;


public enum BugMsg implements LogMessage {

    TRACKABLE_TRACER_INTERRUPT("A CallLoggingTracer was interrupted: %s");

    private final String msg;

    private BugMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getFormat() {
        return msg;
    }
}
