package core.kernel;


/**
 * An event raised by the {@link InternalMainLoader main loader} during application load.
 * @author Timmos
 */
public class LoadingEvent {
    private final int progress;
    private final int max;
    private final Loader<?> source;

    private static final class LoaderWrapper<T extends Buildable> implements Loader<T> {

        private final Loader<T> other;

        private LoaderWrapper(Loader<T> other) {
            this.other = other;
        }

        @Override
        public void load(T builder) throws LoadingFailedException {
            throw new IllegalAccessError("Cannot load a Loader from a LoadingEvent!");
        }

        @Override
        public LoadingWeight getWeight() {
            return other.getWeight();
        }

        private static <T extends Buildable> LoaderWrapper<T> wrap(Loader<T> other) {
            return new LoaderWrapper<T>(other);
        }

        @Override
        public String getInternalName() {
            return other.getInternalName();
        }
    }

    /**
     * Creates a new loading event, having the progress value,
     * maximum progress value and the {@link Loader} that is
     * currently being handled by the main loader.
     * @param progress the progress value
     * @param max the currently known maximum value
     * @param src the Loader being handled
     */
    LoadingEvent(int progress, int max, Loader<?> src) {
        this.progress = progress;
        this.max = max;
        this.source = src;
    }

    /**
     * Gets the maximum progress value. This value can change over time,
     * because of unknown plugin information at initial load time. In a single
     * event however, the following statement is always true:
     * <code>
     * <pre>
     * 0 <= {@link #getProgress()} <= {@link #getMaximum()}.
     * </pre>
     * </code>
     * @return the currently known maximum progress value
     */
    public int getMaximum() {
        return max;
    }

    /**
     * Gets the progress value.
     * @return the progress value
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Gets the {@link Loader} currently being handled by the main loader.
     * @return the Loader currently being handled
     */
    public Loader<?> getSource() {
        return LoaderWrapper.wrap(source);
    }
}
