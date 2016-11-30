package gent.timdemey.visuals.core.log.elements;

import gent.timdemey.visuals.core.log.printers.DebugPrinter;

/**
 * A debug element representing an Integer.
 * @author Timmos
 */
public final class IntegerDebugElement extends AbstractDebugElement<Integer> {

    /**
     * Constructor.
     * @param value the integer value
     */
    public IntegerDebugElement(Integer value) {
        super(value);
    }

    @Override
    public void accept(DebugPrinter<?> printer) {
        printer.visit(this);
    }
}