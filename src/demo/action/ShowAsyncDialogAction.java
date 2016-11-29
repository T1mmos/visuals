package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.ui.ChoiceDialog;
import core.ui.DefaultButton;
import core.ui.DefaultIconKey;
import core.ui.DefaultMessageType;
import core.ui.DialogOutput;
import core.ui.GuiSystem;
import core.ui.IconKey;
import core.ui.TextDialogInput;
import core.util.Gui;
import demo.cmd.AsyncDialogCommand;
import demo.loc.DemoLocKey;
import demo.ui.DemoIconKey;


public class ShowAsyncDialogAction implements GuiAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        TextDialogInput input = new TextDialogInput(DemoLocKey.DIALOG_ASYNC_INPUT.get());
        GuiSystem gs = Gui.getGuiSystem();

        do {
            ChoiceDialog<String> dialog = gs.createCustomDialog(input, "test", DefaultIconKey.INFO,
                            DefaultButton.SET_OK_CANCEL);
            DialogOutput<String> user = dialog.showEDT();
            if (DefaultButton.CANCEL.equals(user.getChoice())) {
                return;
            }
            String answer = user.getInput();

            int seconds = 0;
            try {
                seconds = Integer.parseInt(answer);
            } catch (NumberFormatException nfe) {
                gs.createMessage(DemoLocKey.NOT_A_NUMBER.get(answer)).showEDT();
                continue;
            }
            if (!(0 < seconds && seconds <= 10)) {
                gs.createMessage(DemoLocKey.NUMBER_BETWEEN_X_AND_Y.get(1, 10)).showEDT();
                continue;
            }

            gs.showNotification(DemoLocKey.WAITING_FOR.get(seconds), DefaultMessageType.INFO);

            // Now we did everything UI-related. We stripped off bad input and notified the user if that
            // were the case. We now have everything to kick off the background work. This is all you need:
            new AsyncDialogCommand(seconds).post();
            break;
        } while (true);
    }

    @Override
    public String getTitle() {
        return DemoLocKey.DIALOG_ASYNC_TITLE.get();
    }

    @Override
    public String getTooltip() {
        return DemoLocKey.DIALOG_ASYNC_TOOLTIP.get();
    }

    @Override
    public IconKey getIconKey() {
        return DemoIconKey.HOURGLASS;
    }

    @Override
    public KeyStroke getAccelerator() {
        return null;
    }

}
