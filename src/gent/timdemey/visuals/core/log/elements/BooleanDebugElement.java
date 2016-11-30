package gent.timdemey.visuals.core.log.elements;

import gent.timdemey.visuals.core.log.printers.DebugPrinter;

/**
 * A debug element representing a Boolean.
 * @author Timmos
 */
public final class BooleanDebugElement extends AbstractDebugElement<Boolean> {

    /**
     * Constructor.
     * @param value the boolean value
     */
    public BooleanDebugElement(Boolean value) {
        super(value);
    }

    @Override
    public void accept(DebugPrinter<?> printer) {
        printer.visit(this);
    }
}
