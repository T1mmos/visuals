package core.cmd;

import core.log.Debuggable;
import core.log.elements.DebugBuilder;
import core.log.elements.DebugElement;
import core.util.App;

/**
 * An abstract base class representing task units, processable by a {@link CommandManager}.
 * @author Timmos
 * @param <T> the type of the return value (use {@link Void} if this command should not return a meaningful
 * value)
 */
public abstract class Command<T> implements Debuggable {

    /**
     * Indicates the priority of this command. A {@link CommandManager} may use this
     * property to allow certain commands to be processed with priority over other
     * commands.
     * @return the priority of this command
     */
    public abstract Priority getPriority ();

    /**
     * Encapsulates the work that this command represents. This method should not be called
     * directly, and is only to be invoked by a {@link CommandManager}.
     * @return the result of the execution
     * @throws Exception any exception that makes it impossible to execute further, so the command
     * managing system can be notified of the failure
     */
    public abstract T execute () throws Exception;

    /**
     * A convenience method for {@code App.getCommandManager().submit(this)}. This
     * method posts {@code this} on the Command Manager, which in turn will make
     * sure that this command is executed on one of the Command Manager's
     * execution threads. This method is therefore thread to call on any thread -
     * it won't execute on the invoking thread.
     */
    public final void post() {
        App.get().getCommandManager().submit(this);
    }

    @Override
    public DebugElement getDebugInfo() {
        return new DebugBuilder().add("name", this.getClass().getSimpleName()).add("priority", getPriority()).build();
    }
}
