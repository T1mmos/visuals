package gent.timdemey.visuals.demo.log.msg;

import gent.timdemey.visuals.core.log.msg.LogMessage;


public enum DemoErrorMsg implements LogMessage {

    RESOURCE_LOCALIZATION_LOAD("Failed to load demo localization resource file: %s");

    private final String msg;

    private DemoErrorMsg (String msg) {
        this.msg = msg;
    }

    @Override
    public String getFormat() {
        return msg;
    }
}
