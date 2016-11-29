package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;
import core.action.GuiAction;
import core.ui.ChoiceDialog;
import core.ui.DefaultButton;
import core.ui.DialogInput;
import core.ui.DialogOutput;
import core.ui.IconKey;
import core.ui.TextDialogInput;
import core.util.Gui;
import demo.loc.DemoLocKey;
import demo.ui.DemoIconKey;

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
