package gent.timdemey.visuals.demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;
import gent.timdemey.visuals.core.action.GuiAction;
import gent.timdemey.visuals.core.ui.ChoiceDialog;
import gent.timdemey.visuals.core.ui.DefaultButton;
import gent.timdemey.visuals.core.ui.DialogInput;
import gent.timdemey.visuals.core.ui.DialogOutput;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.ui.TextDialogInput;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.loc.DemoLocKey;
import gent.timdemey.visuals.demo.ui.DemoIconKey;

/**
 * Lets a user enter his name and shows that the input can be delivered to the code
 * that created and showed the dialog, by showing his name inside a localized, parameterized
 * localization string.
 * @author Timmos
 */
public class EnterNameAction implements GuiAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = DemoLocKey.DIALOG_CONTENT_ENTERNAME.get();
        String title = DemoLocKey.DIALOG_TITLE_ENTERNAME.get();
        DialogInput<String> input = new TextDialogInput(msg);

        ChoiceDialog<String> dialog = Gui.getGuiSystem().createCustomDialog(input, title, getIconKey(),
                        DefaultButton.SET_OK_CANCEL);
        DialogOutput<String> output = dialog.showEDT();
        if (output.getChoice() == DefaultButton.CANCEL) {
            return;
        }

        String result = DemoLocKey.DIALOG_CONTENT_BUTTONCHOICE.get(output.getInput(), output.getChoice());
        Gui.getGuiSystem().createMessage(result).showEDT();
    }

    @Override
    public String getTitle() {
        return DemoLocKey.MENU_ITEM_ENTERNAME.get();
    }

    @Override
    public IconKey getIconKey() {
        return DemoIconKey.NAME;
    }

    @Override
    public KeyStroke getAccelerator() {
        return KeyStroke.getKeyStroke("control M");
    }

    @Override
    public String getTooltip() {
        return DemoLocKey.TOOLTIP_ENTERNAME.get();
    }
}
