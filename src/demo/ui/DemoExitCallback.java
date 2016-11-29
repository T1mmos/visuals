package demo.ui;

import core.kernel.ExitCallback;
import core.ui.ChoiceDialog;
import core.ui.DefaultButton;
import core.ui.DialogButton;
import core.ui.DialogOutput;
import core.util.Gui;
import demo.loc.DemoLocKey;

public class DemoExitCallback implements ExitCallback {

    @Override
    public boolean isQuitAllowed() {
        ChoiceDialog<Void> dialog = Gui.getGuiSystem().createYesNoQuestion(DemoLocKey.QUIT_SURE.get(),
                        DemoLocKey.QUIT_TITLE.get(), DemoIconKey.EXIT_APPLICATION);

        DialogOutput<Void> output;
        try {
            output = dialog.showInterruptably();
        } catch (InterruptedException ex) {
            // cannot happen
            return false;
        }
        DialogButton button = output.getChoice();
        if (button == DefaultButton.YES) {
            return true;
        }

        return false;
    }

}
