package gent.timdemey.visuals.core.log.printers;

import gent.timdemey.visuals.core.log.elements.BooleanDebugElement;
import gent.timdemey.visuals.core.log.elements.CompositeDebugElement;
import gent.timdemey.visuals.core.log.elements.DebugElement;
import gent.timdemey.visuals.core.log.elements.IntegerDebugElement;
import gent.timdemey.visuals.core.log.elements.NullDebugElement;
import gent.timdemey.visuals.core.log.elements.StringDebugElement;
import gent.timdemey.visuals.core.log.elements.UnknownDebugElement;

/**
 * Debug printer, printing {@link DebugElement}s in one-line strings.
 * @author Timmos
 */
public final class StringDebugPrinter implements DebugPrinter<String> {

    private final StringBuilder builder;

    /**
     * Constructor.
     */
    public StringDebugPrinter () {
        this.builder = new StringBuilder();
    }

    @Override
    public void visit(CompositeDebugElement element) {
        builder.append("{");
        boolean comma = false;
        for (String key : element.getChildren().keySet()) {
            if (comma) {
                builder.append(",");
            } else {
                comma = true;
            }
            DebugElement subel = element.getChildren().get(key);
            builder.append(key);
            builder.append("=");
            subel.accept(this);

        }
        builder.append("}");
    }

    @Override
    public void visit(StringDebugElement element) {
        builder.append("\"");
        builder.append(element.getValue());
        builder.append("\"");
    }

    @Override
    public void visit(IntegerDebugElement element) {
        builder.append(element.getValue());
    }

    @Override
    public void visit(BooleanDebugElement element) {
        builder.append(element.getValue());
    }

    @Override
    public void visit(NullDebugElement element) {
        builder.append("<null>");
    }

    @Override
    public void visit(UnknownDebugElement element) {
        builder.append("### UNKNOWN_DEBUG_ELEMENT (please implement Debuggable!) [toString=\"");
        builder.append(element.getValue().toString());
        builder.append("\", class=");
        builder.append(element.getValue().getClass().getSimpleName());
        builder.append("] ###");
    }

    @Override
    public String print() {
        return builder.toString();
    }

}
