package core.util;

/**
 * A pair of two objects.
 * @param <O1> the type of value 1
 * @param <O2> the type of value 2
 * @author Timmos
 */
public class Pair<O1, O2> {

    private final O1 first;
    private final O2 second;

    /**
     * Creates a pair.
     * @param first the first object
     * @param second the second object
     */
    public Pair(O1 first, O2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.first == null && other.first != null) {
            return false;
        }
        if (this.second == null && other.second != null) {
            return false;
        }
        if (!this.first.equals(other.first)) {
            return false;
        }
        if (!this.second.equals(other.second)) {
            return false;
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int ret = 17;
        ret = 31 * ret + first.hashCode();
        ret = 31 * ret + second.hashCode();
        return ret;
    }
}
