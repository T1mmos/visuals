package core.log.printers;

import core.log.elements.BooleanDebugElement;
import core.log.elements.CompositeDebugElement;
import core.log.elements.DebugElement;
import core.log.elements.IntegerDebugElement;
import core.log.elements.NullDebugElement;
import core.log.elements.StringDebugElement;
import core.log.elements.UnknownDebugElement;


/**
 * Debug printer, printing {@link DebugElement}s in pretty-printed JSON format.
 * @author Timmos
 */
public final class JsonDebugPrinter implements DebugPrinter<String> {

    private final StringBuilder builder;

    private int indentLvl = 0;

    /**
     * Constructor.
     */
    public JsonDebugPrinter () {
        this.builder = new StringBuilder();
    }

    @Override
    public void visit(CompositeDebugElement element) {
        write("{");
        newline(+1);
        boolean comma = false;
        for (String key : element.getChildren().keySet()) {
            if (comma) {
                builder.append(",");
                newline(0);
            } else {
                comma = true;
            }
            DebugElement subel = element.getChildren().get(key);
            builder.append(key);
            builder.append(" = ");
            subel.accept(this);

        }
        newline(-1);
        builder.append("}");

    }

    private void newline (int indentIncr) {
        builder.append("\n");
        indentLvl += indentIncr;
        for (int i = 0; i < indentLvl; i++) {
            builder.append("    ");
        }
    }

    private void write (String text) {
        builder.append(text);
    }

    @Override
    public String print() {
        return builder.toString();
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
        builder.append(element.getValue().getClass().getSimpleName());
        builder.append("@");
        builder.append(element.getValue().hashCode());
    }
}
