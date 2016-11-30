package gent.timdemey.visuals.core.log.printers;

import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.elements.BooleanDebugElement;
import gent.timdemey.visuals.core.log.elements.CompositeDebugElement;
import gent.timdemey.visuals.core.log.elements.DebugElement;
import gent.timdemey.visuals.core.log.elements.IntegerDebugElement;
import gent.timdemey.visuals.core.log.elements.NullDebugElement;
import gent.timdemey.visuals.core.log.elements.StringDebugElement;
import gent.timdemey.visuals.core.log.elements.UnknownDebugElement;

/**
 * Traverses a tree of {@link DebugElement}s and prints them out. The cooperation between
 * {@link DebugElement} and {@link DebugPrinter} is based on the Visitor design pattern.
 * @author Timmos
 * @param <T> the type of the print result
 */
public interface DebugPrinter<T> {

    /**
     * Visits a node in the debug tree that represents an composite debug element.
     * @param element the node
     */
    public void visit (CompositeDebugElement element);

    /**
     * Visits a leaf node in the debug tree that represents a debug element holding a String.
     * @param element the leaf node
     */
    public void visit (StringDebugElement element);

    /**
     * Visits a leaf node in the debug tree that represents a debug element holding a Integer.
     * @param element the leaf node
     */
    public void visit (IntegerDebugElement element);

    /**
     * Visits a leaf node in the debug tree that represents a debug element holding a Boolean.
     * @param element the leaf node
     */
    public void visit(BooleanDebugElement element);

    /**
     * Visits a leaf node in the debug tree that represents a debug element holding {@code null}.
     * @param element the leaf node
     */
    public void visit (NullDebugElement element);

    /**
     * Visits a leaf node in the debug tree that represents a debug element holding an object
     * of an unsupported type (not a String, Integer, ... and also not implementing {@link Debuggable}).
     * Such objects cannot be converted into a more suitable debug element, hence they are wrapped
     * in a fallback element.
     * <p>Debug Printers can fall back on gener {@link #toString()} method.
     * @param element the leaf node
     */
    public void visit (UnknownDebugElement element);

    /**
     * Prints what has been visited to a final output object.
     * @return the output of visiting the different elements
     */
    public T print ();
}
