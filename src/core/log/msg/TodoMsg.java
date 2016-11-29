package core.log.msg;


public enum TodoMsg implements LogMessage {

    UNSUPPORTED("Unsupported operation: %s");

    private final String msg;

    private TodoMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getFormat() {
        return msg;
    }
}
