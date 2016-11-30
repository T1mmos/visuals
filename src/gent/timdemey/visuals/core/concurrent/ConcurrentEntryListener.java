package gent.timdemey.visuals.core.concurrent;

/**
 * Listens to additions and removals of entries in a {@link ConcurrentEntryModel}.
 * @author Timmos
 * @param <A> the type of objects that can be processed by the concurrent model
 */
public interface ConcurrentEntryListener<A> {

    /**
     * Informs this listener that an entry was added to the concurrent model where this listener
     * is registered.
     * @param obj the added object
     */
    public void entryAdded(A obj);
}
