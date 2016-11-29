package core.kernel;

import java.lang.Thread.UncaughtExceptionHandler;

import core.log.msg.ErrorMsg;
import core.util.Log;

/**
 * The framework's implementation of {@link UncaughtExceptionHandler}. It redirects all
 * unchecked exceptions to the currently installed {@link InternalExceptionModel}:
 * <ul>
 * <li>{@link RuntimeException}: are only forwarded
 * <li>{@link Error}: are logged and forwarded
 * </ul>
 * <p>Remark that unchecked exceptions that occur on the Event Dispatching Thread are not
 * caught here. (It is uncertain whether this is related to the JVM version) Whenever the
 * EDT crashes due to an exception, it
 * @author Timmos
 */
class InternalUncaughtExceptionHandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof RuntimeException) {
            e.printStackTrace();
            Log.error(ErrorMsg.EXCEPTION_HANDLER_UNCAUGHT);
            Log.error(e);
            DefaultApp.instance().getExceptionModel().add((RuntimeException) e, t);
        } else if (e instanceof Error) {
            Log.fatal(ErrorMsg.EXCEPTION_HANDLER_ERROR);
            Log.fatal(e);
            DefaultApp.instance().getExceptionModel().add((Error) e, t);
        }
        // else: must be instanceof Exception, but this cannot happen: all subclasses of
        // Exception that are not subclasses of RuntimeException are checked exceptions,
        // so we cannot arrive here

    }
}
