package gent.timdemey.visuals.demo.ui;

import gent.timdemey.visuals.core.kernel.ExitCallback;
import gent.timdemey.visuals.core.ui.ChoiceDialog;
import gent.timdemey.visuals.core.ui.DefaultButton;
import gent.timdemey.visuals.core.ui.DialogButton;
import gent.timdemey.visuals.core.ui.DialogOutput;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.loc.DemoLocKey;

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
