package core.cmd;

import java.util.concurrent.Future;

import com.google.common.util.concurrent.ListenableFuture;

import core.log.Debuggable;
import core.log.elements.DebugBuilder;
import core.log.elements.DebugElement;
import core.log.printers.StringDebugPrinter;

/**
 * Tracks a {@link Command} that was previously posted to a {@link CommandManager}.
 * <p>A command tracker can be used to get the name, ID and other properties related
 * to the command it is tracking. Additionally, the command execution can be cancelled, or
 * a caller can wait for the command result to become available, or register a result
 * listener, ... Various synchronous and asynchronous methods are available through the use
 * of a {@link Future}, which can be obtained by calling {@link #getFuture()}.
 * @author Timmos
 * @param <T> the type of the result of the asynchronous command execution
 */
public final class CommandTracker<T> implements Debuggable {

    private final Future<T> future;
    private final int id;
    private final String name;
    private final Command<T> origCmd;

    /**
     * Creates a new trackable.
     * @param id the unique ID assigned to the command
     * @param name the name of the command
     * @param origCmd the original command that was submitted to the command manager
     * @param future the {@link Future} that allows to track the asynchronous command execution
     */
    public CommandTracker (int id, String name, Command<T> origCmd, Future<T> future) {
        this.id = id;
        this.name = name;
        this.origCmd = origCmd;
        this.future = future;
    }

    /**
     * Gets the ID assigned to the command.
     * @return the command ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name assigned to the command.
     * @return the command name
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates whether the command can be shown to the user.
     * @return {@code true} when this command is useful to show to a user, {@code false} otherwise
     */
    public boolean isVisible() {
        return name != null && !name.isEmpty();
    }

    /**
     * Returns the {@link ListenableFuture} that allows to get the result and manipulate
     * the execution of the command.
     * @return the actual tracker of the command, a {@code ListenableFuture}
     */
    public Future<T> getFuture() {
        return future;
    }

    @Override
    public DebugElement getDebugInfo() {
        return new DebugBuilder()
        .add("name", name)
        .add("visible", isVisible())
        .add("cmd", origCmd)
        .build();
    }

    @Override
    public String toString() {
        StringDebugPrinter pr = new StringDebugPrinter();
        origCmd.getDebugInfo().accept(pr);
        String cmd = pr.print();
        return (isVisible() ? name : "<no name given> ") + "(" + cmd + ")";
    }
}
