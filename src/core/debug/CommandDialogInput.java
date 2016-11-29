package core.debug;

import javax.swing.DefaultListModel;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.list.WebList;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;

import core.cmd.CommandTracker;
import core.concurrent.AbstractEDTListener;
import core.concurrent.ConcurrentEntryListener;
import core.ui.AbstractDialogInput;
import core.ui.ChoiceDialog;
import core.util.App;

/**
 * Lists all executed commands in a list.
 * @author Timmos
 */
public class CommandDialogInput extends AbstractDialogInput<Void> {

    private final DefaultListModel<CommandTracker<?>>        model;
    private final ConcurrentEntryListener<CommandTracker<?>> listener;

    /**
     * Creates a command dialog input.
     */
    public CommandDialogInput() {
        this.model = new DefaultListModel<>();
        this.listener = new PrivListener();
    }

    @Override
    public WebPanel getContent(ChoiceDialog<Void> dialog) {
        WebPanel panel = new WebPanel(new MigLayout("insets 0"));
        WebList list = new WebList(model);
        panel.add(new WebScrollPane(list), "wmin 500, push, grow, wrap");
        return panel;
    }

    @Override
    public void onShow() {
        App.get().getCommandManager().add(listener);
    }

    @Override
    public void onHide() {
        App.get().getCommandManager().remove(listener);
    }

    private class PrivListener extends AbstractEDTListener<CommandTracker<?>> {

        @Override
        protected void entryAddedEdt(CommandTracker<?> ct) {
            model.addElement(ct);
        }
    }
}
