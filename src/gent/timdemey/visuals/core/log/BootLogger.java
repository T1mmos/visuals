package gent.timdemey.visuals.core.log;

import gent.timdemey.visuals.core.log.elements.DebugUtils;

/**
 * Logger used during the boot sequence of the framework, until a final logger is installed. The boot logger uses
 * System.out.
 * @author Timmos
 */
public class BootLogger implements Logger {

    @Override
    public void log(LogLevel lvl, Object obj) {
        log(lvl, "%s", obj.toString());
    }

    @Override
    public void log(LogLevel lvl, String format, Object ... args) {
        Object[] prettier = DebugUtils.prettify(args);
        String msg = String.format(format, prettier);
        System.out.println(" [ BOOT LOGGER ] " + lvl + " :: " + msg);
    }

    @Override
    public void log(LogLevel lvl, Throwable t) {
        t.printStackTrace();
    }

}
