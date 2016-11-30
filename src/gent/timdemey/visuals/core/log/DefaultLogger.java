package gent.timdemey.visuals.core.log;

import java.io.PrintStream;

import com.google.common.base.Strings;

import gent.timdemey.visuals.core.cfg.DefaultConfigKey;
import gent.timdemey.visuals.core.log.elements.DebugUtils;
import gent.timdemey.visuals.core.log.printers.StringDebugPrinter;


/**
 * A simple logger with output to the command line, using
 * {@code System.out.println} calls, and support for {@link Debuggable}.
 * If an object implementing {@link Debuggable} is encountered, the object's
 * {@link Debuggable#getDebugInfo()} method is used; otherwise, the object's
 * {@link #toString()} method is used instead.
 * @author Timmos
 */
public final class DefaultLogger implements Logger {

    private static final String FORMAT = "%-6s :: %s";

    /** Allows to temporarily switch off the logger, to cut off unbounded recursion. */
    private boolean             enabled = true;

    @Override
    public void log(LogLevel lvl, Object obj) {
        if (obj instanceof Debuggable) {
            StringDebugPrinter printer = new StringDebugPrinter();
            ((Debuggable) obj).getDebugInfo().accept(printer);
            log(lvl, printer.print());
        } else {
            log(lvl, obj.toString());
        }
    }

    @Override
    public void log(LogLevel lvl, Throwable t) {
        log(lvl, "Throwable message: \"" + t.getMessage() + "\"");
        log(LogLevel.STACK, "Stack trace:");
        Throwable curr = t;
        int indent = 1;
        while (curr != null) {
            printStackTrace(curr, indent);

            curr = curr.getCause();
            if (curr != null) {
                indent++;
                log(LogLevel.STACK, "Caused by:");
            }
        }
    }

    @Override
    public void log(LogLevel lvl, String format, Object... args) {
        Object[] prettier = DebugUtils.prettify(args);

        String msg;
        try {
            msg = String.format(format, prettier);
        } catch (RuntimeException ex) { // we don't want bugs in functionality just because of a misformed logging message
            String clz = ex.getClass().getSimpleName();
            msg = "[ LOG MSG ERROR - " + clz + " ] - " + format;
        }

        log(lvl, msg);
    }

    private void log(LogLevel lvl, String msg) {
        if (!enabled) {
            return; // cuts off recursion
        }
        enabled = false;
        LogLevel loglvl = DefaultConfigKey.LOG_LEVEL.get();
        enabled = true;

        if (lvl.ordinal() < loglvl.ordinal()) {
            return;
        }
        String formatted = String.format(FORMAT, lvl, msg);

        @SuppressWarnings("resource")
        PrintStream stream = lvl == LogLevel.STACK ? System.err : System.out;
        stream.println(formatted);
    }

    private void printStackTrace(Throwable t, int indent) {
        if (!enabled) {
            return; // cuts off recursion.
        }
        LogLevel loglvl = DefaultConfigKey.LOG_LEVEL.get();
        if (LogLevel.STACK.ordinal() < loglvl.ordinal()) {
            return;
        }
        for (StackTraceElement e : t.getStackTrace()) {
            String line = Strings.padStart("", indent * 4, ' ') + e.toString();
            log(LogLevel.STACK, line);
        }
    }
}
