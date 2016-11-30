package gent.timdemey.visuals.core.ui;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;


/**
 * A dialog input plugin with a simple message, and no input components.
 * @author Timmos
 */
public class StaticDialogInput extends AbstractDialogInput<Void> {

    private final String msg;

    /**
     * Creates a new static dialog input with the given message.
     * @param msg the message to show
     */
    public StaticDialogInput (String msg) {
        this.msg = msg;
    }

    @Override
    public WebPanel getContent(ChoiceDialog<Void> dialog) {
        WebPanel panel = new WebPanel(new MigLayout("insets 0"));
        WebLabel lab_msg = new WebLabel(msg);

        panel.add(lab_msg, "pushx, grow, wrap");
        return panel;
    }
}
