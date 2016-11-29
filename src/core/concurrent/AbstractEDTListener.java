package core.concurrent;

import javax.swing.SwingUtilities;

/**
 * An implementation of {@link ConcurrentEntryListener} where the callbacks are redirected to run on the EDT.
 * The callbacks are implemented final so they cannot be overridden; instead, two new callbacks are defined
 * in this class that are called on the EDT.
 * @author Timmos
 * @param <A> the type of objects that can be added to the concurrent model
 */
public abstract class AbstractEDTListener<A> implements ConcurrentEntryListener<A> {

    @Override
    public final void entryAdded(final A obj) {
        SwingUtilities.invokeLater(new EdtCallback(obj));
    }

    private final class EdtCallback implements Runnable {

        private final A obj;

        private EdtCallback(A obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            entryAddedEdt(obj);
        }
    }

    /**
     * The same as {@link ConcurrentEntryListener#entryAdded(Object)}, but invoked on the EDT.
     * @param obj the entry that was added
     */
    protected abstract void entryAddedEdt(A obj);
}
