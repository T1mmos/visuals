package gent.timdemey.visuals.core.kernel;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Keeps track of thrown unchecked exceptions in the lifespan of the application.
 * Listeners can be registered on the model, e.g. to notify a user via a GUI that an
 * internal error occured.
 * @author Timmos
 */
public final class InternalExceptionModel {

    private final List<ExceptionListener> listeners;
    private final List<RuntimeException> exceptions;

    /**
     * Creates a new exception model. An exception model can only
     * be constructed from within the framework core.
     */
    InternalExceptionModel () {
        this.listeners = new ArrayList<>();
        this.exceptions = new ArrayList<>();
    }

    /**
     * Adds a {@link RuntimeException} for the given thread to this model, and notifies the
     * listeners.
     * <p>This method should only be invoked within the framework core, by the installed
     * {@link UncaughtExceptionHandler}.
     * @param rte the runtime exception
     * @param t the thread where the runtime exception was thrown
     */
    void add(RuntimeException rte, Thread t) {
        exceptions.add(rte);
        for (ExceptionListener listener : listeners) {
            listener.exceptionCaught(rte, t);
        }
    }

    /**
     * Adds an {@link Error} for the given thread to this model, and notifies the
     * listeners.
     * <p>{@code Error}s are not tracked, for the reason that it is recommended to exit
     * the application after such exception was thrown.
     * <p>This method should only be invoked within the framework core, by the installed
     * {@link UncaughtExceptionHandler}.
     * @param error the {@code Error}
     * @param t the thread where the runtime exception was thrown
     */
    void add(Error error, Thread t) {
        for (ExceptionListener listener : listeners) {
            listener.errorCaught(error, t);
        }
    }

    /**
     * Gets all {@link RuntimeException}s that were uncaught since the application was started.
     * An exception model does not provide access to {@link Error}s, as it is discouraged to
     * continue running the application after such exception is thrown.
     * @return a list of all s that occured since the application was started
     */
    public List<RuntimeException> getAll (){
        return Collections.unmodifiableList(exceptions);
    }

    /**
     * Adds a listener to the model. A listener is notified when a {@link RuntimeException} or
     * an {@link Error} was added to this model.
     * @param listener the listener
     */
    public void addListener (ExceptionListener listener) {
        listeners.add(listener);
    }
}
