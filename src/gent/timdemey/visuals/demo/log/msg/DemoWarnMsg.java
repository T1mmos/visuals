package gent.timdemey.visuals.demo.log.msg;

import gent.timdemey.visuals.core.log.msg.LogMessage;


public enum DemoWarnMsg implements LogMessage {

    UNRECOGNIZED_LOCALIZATION_ID("The localization abbreviation '%s' is not recognized, taking default language '%s'");

    private final String msg;

    private DemoWarnMsg (String msg) {
        this.msg = msg;
    }

    @Override
    public String getFormat() {
        return msg;
    }
}
