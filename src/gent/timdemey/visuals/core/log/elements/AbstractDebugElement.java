package gent.timdemey.visuals.core.log.elements;

/**
 * Abstract base class for debug elements that hold a value.
 * @author Timmos
 *
 * @param <T> the value type
 */
public abstract class AbstractDebugElement<T> implements DebugElement {

    private final T value;
    
    /**
     * Package private constructor.
     * @param value the wrapped value
     */
    AbstractDebugElement(T value) {
        this.value = value;
    }
    
    /**
     * Gets the wrapped value.
     * @return the wrapped value
     */
    public T getValue () {
        return value;
    }
}
