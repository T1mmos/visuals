package gent.timdemey.visuals.core.log.elements;

import gent.timdemey.visuals.core.log.printers.DebugPrinter;


/**
 * A debug element representing a String.
 * @author Timmos
 */
public final class StringDebugElement extends AbstractDebugElement<String> {

    /**
     * Constructor.
     * @param value the integer value
     */
    public StringDebugElement(String value) {
        super(value);
    }

    @Override
    public void accept(DebugPrinter<?> printer) {
        printer.visit(this);
    }
}
