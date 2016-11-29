package core.log.elements;

import java.util.Collections;
import java.util.Map;

import core.log.Debuggable;
import core.log.printers.DebugPrinter;

/**
 * A {@link DebugElement debug element} composed of other debug elements. A custom class implementing {@link Debuggable}
 * will probably return an instance of this class.
 * @author Timmos
 */
public final class CompositeDebugElement implements DebugElement {
    
    private final Map<String, DebugElement> children;
    
    /**
     * Package private constructor - this class can only be instantiated via the {@link DebugBuilder}
     * class.
     * @param children the child {@link DebugElement} of which this composite is composed
     */
    CompositeDebugElement(Map<String, DebugElement> children) {
        this.children = Collections.unmodifiableMap(children);
    }

    /**
     * Gets the child debug elements.
     * @return the child debug elements
     */
    public Map<String, DebugElement> getChildren (){
        return children;
    }
    
    @Override
    public void accept(DebugPrinter<?> printer) {
        printer.visit(this);
    }
}
