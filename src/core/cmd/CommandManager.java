package core.cmd;

import core.concurrent.ConcurrentEntryListener;



/**
 * A framework for execution of {@link Command}s and managing of {@link CommandTracker}s.
 * <p>A {@code CommandManager} accepts and executes a {@link Command} and allows the calling code to track this
 * asynchronous command execution by returning a {@link CommandTracker} to the caller.
 * @author Timmos
 */
public interface CommandManager {

    /**
     * Submits the given {@link Command} to this command manager,
     * marks it as a background command and adds it to the work
     * queue.
     * @param <T> the type of the return value of the command
     * @param cmd the command
     * @return a command tracker
     */
    public <T> CommandTracker<T> submit(Command<T> cmd);

    /**
     * Submits the given {@link Command} marks it as a visible command with the
     * given name and adds it to the work queue
     * @param <T> the type of the return value of the command
     * @param cmd the command
     * @param name the command name
     * @return a command tracker
     */
    public <T> CommandTracker<T> submit(Command<T> cmd, String name);

    /**
     * Adds a command manager listener to this manager.
     * @param listener the command event listener
     */
    public void add(ConcurrentEntryListener<CommandTracker<?>> listener);

    /**
     * Removes a command manager listener from this manager.
     * @param listener the command event listener to remove
     */
    public void remove(ConcurrentEntryListener<CommandTracker<?>> listener);
}
