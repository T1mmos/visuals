package gent.timdemey.visuals.core.kernel;

/**
 * Expresses in an abstract manner the amount of work that a {@link Loader} executes.
 * @author Timmos
 */
public enum LoadingWeight {
    /** Relatively little to do. For example: no file I/O, no network I/O, no heavy computations, etc. */
    SMALL(1),
    /** Between small and big. There is quite some work, but it won't take ages. */
    MEDIUM(6),
    /** There is a considerable amount of work. For example: a slow computation, some network I/O (
     * includes waiting for an answer), ... */
    BIG(15);

    private final int weight;

    private LoadingWeight (int weight) {
        this.weight = weight;
    }

    /**
     * Gets the weight expressed as an integer, used by the core loader to
     * convert the weight abstractions into actual portions.
     * @return the weight expressed as an integer
     */
    int getWeight () {
        return weight;
    }
}
