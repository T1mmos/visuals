package core.concurrent;

import java.util.LinkedList;
import java.util.List;

/**
 * A thread-safe wrapper around a list where entry listeners can be registered at any time by any thread. Upon
 * registration of a listener, it will be notified of all existing entries (in the order of addition) and
 * any entries that were added later on. Due to the tread-safetiness, listeners won't miss any event,
 * even if a different thread is adding entries at the time that the listener is being registered.
 * <p>
 * Because this class is to be used in concurrent environments, there is no such thing as "current state". This means
 * that there are no methods for fetching the number of entries, fetching all entries, etc.
 * <p>
 * Listener implementation callbacks that need to run on the EDT may find extending {@link AbstractEDTListener} useful.
 * <p>
 * Implementation note: the implementation is poor when the amount of entries grows, as the entire structured is locked
 * when an entry or a listener is added. There is room for a lot of improvement (or use an external library). It's also
 * worth looking at an entry limit, so locking time is limited as well.
 * @author Timmos
 * @param <A> the type of objects processed in this model
 */
public class ConcurrentEntryModel<A> {

    private final List<A>           objs;
    private final List<ConcurrentEntryListener<A>> listeners;

    private final Object            stateLock;

    /**
     * Creates a concurrent entry model.
     */
    public ConcurrentEntryModel() {
        this.objs = new LinkedList<>();
        this.listeners = new LinkedList<>();
        this.stateLock = new Object();
    }

    /**
     * Adds an entry to the entry list.
     * @param obj the object to add
     */
    public void addEntry(A obj) {
        synchronized (stateLock) {
            objs.add(obj);
            for (ConcurrentEntryListener<A> l : listeners) {
                l.entryAdded(obj);
            }
        }
    }

    /**
     * Adds a listener. The listener will be notified of every entry.
     * @param listener the listener to add
     */
    public void addListener(ConcurrentEntryListener<A> listener) {
        synchronized (stateLock) {
            listeners.add(listener);
            for (A obj : objs) {
                listener.entryAdded(obj);
            }
        }
    }

    /**
     * Removes a listener.
     * @param listener the listener to remove
     */
    public void removeListener(ConcurrentEntryListener<A> listener) {
        synchronized (stateLock) {
            listeners.remove(listener);
        }
    }
}
