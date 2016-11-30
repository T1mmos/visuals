package gent.timdemey.visuals.core.util;

import com.google.common.base.Preconditions;

import gent.timdemey.visuals.core.kernel.DefaultApp;
import gent.timdemey.visuals.core.log.BootLogger;
import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.LogLevel;
import gent.timdemey.visuals.core.log.Logger;
import gent.timdemey.visuals.core.log.msg.InfoMsg;
import gent.timdemey.visuals.core.log.msg.LogMessage;

/**
 * Provides abstracted access to the installed logger's logging methods.
 * The installed logger is determined using {@code DefaultApp.instance().getLogger()}.
 * @author Timmos
 * @see Logger
 */
public final class Log {

    private static Logger BOOT_LOGGER = new BootLogger();

    private Log (){}

    /**
     * Logs the object with {@link LogLevel#INFO} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void info(Debuggable obj) {
        Log.log(LogLevel.INFO, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#INFO}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void info(LogMessage msg, Object ... args) {
        Log.log(LogLevel.INFO, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#INFO} level on the installed logger.
     * @param t the throwable
     */
    public static void info (Throwable t) {
        Log.log(LogLevel.INFO, t);
    }

    /**
     * Logs the object with {@link LogLevel#WARN} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void warn(Debuggable obj) {
        Log.log(LogLevel.WARN, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#WARN}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void warn(LogMessage msg, Object ... args) {
        Log.log(LogLevel.WARN, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#WARN} level on the installed logger.
     * @param t the throwable
     */
    public static void warn (Throwable t) {
        Log.log(LogLevel.WARN, t);
    }

    /**
     * Logs the object with {@link LogLevel#ERROR} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void error(Debuggable obj) {
        Log.log(LogLevel.ERROR, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#ERROR}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void error(LogMessage msg, Object ... args) {
        Log.log(LogLevel.ERROR, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#ERROR} level on the installed logger.
     * @param t the throwable
     */
    public static void error (Throwable t) {
        Log.log(LogLevel.ERROR, t);
    }

    /**
     * Logs the object with {@link LogLevel#FATAL} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void fatal(Debuggable obj) {
        Log.log(LogLevel.FATAL, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#FATAL}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void fatal(LogMessage msg, Object ... args) {
        Log.log(LogLevel.FATAL, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#FATAL} level on the installed logger.
     * @param t the throwable
     */
    public static void fatal (Throwable t) {
        Log.log(LogLevel.FATAL, t);
    }

    /**
     * Logs the object with {@link LogLevel#TRACE} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void trace(Debuggable obj) {
        Log.log(LogLevel.TRACE, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#TRACE}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void trace(LogMessage msg, Object ... args) {
        Log.log(LogLevel.TRACE, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#TRACE} level on the installed logger.
     * @param t the throwable
     */
    public static void trace (Throwable t) {
        Log.log(LogLevel.TRACE, t);
    }

    /**
     * Logs the object with {@link LogLevel#TODO} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void todo(Debuggable obj) {
        Log.log(LogLevel.TODO, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#TODO}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void todo(LogMessage msg, Object ... args) {
        Log.log(LogLevel.TODO, msg.getFormat(), args);
    }

    public static void todo(String msg) {
        Log.log(LogLevel.TODO, msg);
    }

    /**
     * Logs the throwable with {@link LogLevel#TODO} level on the installed logger.
     * @param t the throwable
     */
    public static void todo (Throwable t) {
        Log.log(LogLevel.TODO, t);
    }

    /**
     * Logs the object with {@link LogLevel#TEMP} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void temp(Debuggable obj) {
        Log.log(LogLevel.TEMP, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#TEMP}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void temp(LogMessage msg, Object ... args) {
        Log.log(LogLevel.TEMP, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#TEMP} level on the installed logger.
     * @param t the throwable
     */
    public static void temp (Throwable t) {
        Log.log(LogLevel.TEMP, t);
    }

    /**
     * Logs the object with {@link LogLevel#BUG} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void bug(Debuggable obj) {
        Log.log(LogLevel.BUG, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#BUG}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void bug(LogMessage msg, Object ... args) {
        Log.log(LogLevel.BUG, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#BUG} level on the installed logger.
     * @param t the throwable
     */
    public static void bug (Throwable t) {
        Log.log(LogLevel.BUG, t);
    }

    /**
     * Logs the object with {@link LogLevel#FIXME} level on the installed logger.
     * @param obj the object (may be as simple as a string)
     */
    public static void fixme(Debuggable obj) {
        Log.log(LogLevel.FIXME, obj);
    }

    /**
     * Logs a format string using the given argument objects with {@link LogLevel#FIXME}
     * level on the installed logger.
     * @param format the format string
     * @param args the arguments (may be strings)
     */
    public static void fixme(LogMessage msg, Object ... args) {
        Log.log(LogLevel.FIXME, msg.getFormat(), args);
    }

    /**
     * Logs the throwable with {@link LogLevel#FIXME} level on the installed logger.
     * @param t the throwable
     */
    public static void fixme (Throwable t) {
        Log.log(LogLevel.FIXME, t);
    }

    private static void log (LogLevel lvl, Throwable t){
        if (BOOT_LOGGER != null) {
            BOOT_LOGGER.log(lvl, t);
        } else {
            DefaultApp.instance().getLogger().log(lvl, t);
        }
    }

    private static void log (LogLevel lvl, Object obj){
        if (BOOT_LOGGER != null) {
            BOOT_LOGGER.log(lvl, obj);
        } else {
            DefaultApp.instance().getLogger().log(lvl, obj);
        }
    }

    private static void log (LogLevel lvl, String format, Object ... args){
        if (BOOT_LOGGER != null) {
            BOOT_LOGGER.log(lvl, format, args);
        } else {
            DefaultApp.instance().getLogger().log(lvl, format, args);
        }
    }

    /**
     * Called by the core loader to drop the raw logger, used during application load when a
     * better logger isn't installed yet.
     */
    public static void dropRawLogging () {
        Preconditions.checkState(BOOT_LOGGER != null,
                        "Raw logging can be switched off only once, by the core's main loader!");
        Log.info(InfoMsg.RAW_LOGGING_END);
        BOOT_LOGGER = null;
        Log.info(InfoMsg.LOAD_LOGGING_STARTED);
    }
}
