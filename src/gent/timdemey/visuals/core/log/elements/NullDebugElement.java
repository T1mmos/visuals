package gent.timdemey.visuals.core.log.elements;

import gent.timdemey.visuals.core.log.printers.DebugPrinter;

/**
 * Represents a node in a tree of {@link DebugElement}s that is {@code null}.
 * @author Timmos
 *
 */
public final class NullDebugElement implements DebugElement {

    @Override
    public void accept(DebugPrinter<?> printer) {
        printer.visit(this);
    }
}
