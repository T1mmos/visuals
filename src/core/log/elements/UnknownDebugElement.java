package core.log.elements;

import core.log.Debuggable;
import core.log.printers.DebugPrinter;

/**
 * A debug element representing an object that is not a primitive (String, Integer, ...)
 * nor implementing {@link Debuggable}.
 * @author Timmos
 */
public final class UnknownDebugElement extends AbstractDebugElement<Object> {

    /**
     * Package private constructor.
     * @param value the object having an unsupported type
     */
    UnknownDebugElement(Object value) {
        super(value);
    }

    @Override
    public void accept(DebugPrinter<?> printer) {
        printer.visit(this);
    }
}
