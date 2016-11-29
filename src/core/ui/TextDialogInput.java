package core.ui;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;



/**
 * An implementation of {@link DialogInput} for textual input via a textfield.
 * @author Timmos
 */
public class TextDialogInput extends AbstractDialogInput<String> {

    // private final AtomicReference<String> text;
    private final WebTextField field;
    private final WebLabel     lab_msg;

    /**
     * Creates a {@code TextDialogInput} with a label having
     * the given text and an empty textfield.
     * @param msg the text for the label
     */
    public TextDialogInput (String msg) {
        this(msg, null);
    }

    /**
     * Creates a {@code TextDialogInput} with a label and a textfield.
     * @param msg the text for the label
     * @param text the initial text for the textfield
     */
    public TextDialogInput (String msg, String text) {
        this.lab_msg = new WebLabel(msg);
        this.field = new WebTextField(text, 20);
    }

    @Override
    public WebPanel getContent(ChoiceDialog<String> dialog) {
        WebPanel panel = new WebPanel(new MigLayout("insets 0"));

        panel.add(lab_msg, "pushx, grow, wrap");
        panel.add(field, "pushx, grow, wrap");

        return panel;
    }

    @Override
    public String getInput() {
        return field.getText(); // TODO is this safe? as getInput() is typically called from non-EDT threads
    }
}
