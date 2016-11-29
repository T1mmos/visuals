package demo.action;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.ui.ChoiceDialog;
import core.ui.DefaultMessageType;
import core.ui.DialogButton;
import core.ui.DialogInput;
import core.ui.DialogOutput;
import core.ui.GuiSystem;
import core.ui.IconKey;
import core.ui.StaticDialogInput;
import core.util.Gui;
import demo.loc.DemoLocKey;

public class ShowButtonDialog implements GuiAction {

    public static class ShowButtonParam {

        private final String         title;
        private final String         tooltip;
        private final String         msg;
        private final IconKey        key;
        private final DialogButton[] choices;

        public ShowButtonParam(String title, String tooltip, String msg, IconKey key, DialogButton ... choices) {
            this.title = title;
            this.tooltip = tooltip;
            this.msg = msg;
            this.key = key;
            this.choices = choices;
        }
    }

    private final ShowButtonParam param;

    public ShowButtonDialog(ShowButtonParam param) {
        this.param = param;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GuiSystem gs = Gui.getGuiSystem();

        DialogInput<Void> input = new StaticDialogInput(param.msg);
        Set<DialogButton> buttons = new HashSet<>(Arrays.asList(param.choices));
        ChoiceDialog<Void> dialog = gs.createCustomDialog(input, param.title, param.key, buttons);
        DialogOutput<Void> output = dialog.showEDT();

        String notmsg = DemoLocKey.NOTIFICATION_BUTTONCHOICE.get(output.getChoice().getLocKey().get());
        gs.showNotification(notmsg, DefaultMessageType.INFO);
    }

    @Override
    public String getTitle() {
        return param.title;
    }

    @Override
    public String getTooltip() {
        return param.tooltip;
    }

    @Override
    public IconKey getIconKey() {
        return param.key;
    }

    @Override
    public KeyStroke getAccelerator() {
        return null;
    }

}
