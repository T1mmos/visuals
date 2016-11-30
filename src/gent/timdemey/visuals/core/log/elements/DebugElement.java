package gent.timdemey.visuals.core.log.elements;

import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.printers.DebugPrinter;

/**
 * Represents a {@link Debuggable} in its raw format, used in a hierarchy tree of debug elements
 * which a {@link DebugPrinter} can act upon. The cooperation of
 * {@link DebugElement} and {@link DebugPrinter} is implemented using the
 * Visitor design pattern.
 * <p>A debug element is the raw form of an object
 * @author Timmos
 *
 */
public interface DebugElement {

    /**
     * Accepts a {@link DebugPrinter} according to the Visitor design pattern.
     * @param printer the printer that will print the hierarchy tree of debug elements
     */
    public void accept (DebugPrinter<?> printer);
}
