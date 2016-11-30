package gent.timdemey.visuals.demo.cmd;

import gent.timdemey.visuals.core.cmd.Command;
import gent.timdemey.visuals.core.cmd.Priority;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.loc.DemoLocKey;

public class AsyncDialogCommand extends Command<Void> {

    private final int seconds;

    public AsyncDialogCommand(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public Priority getPriority() {
        return Priority.BACKGROUND;
    }

    @Override
    public Void execute() throws Exception {
        // do some heavy work
        Thread.sleep(1000 * seconds);

        // prepare the translations
        String msg = DemoLocKey.DIALOG_ASYNC_RESULT.get(seconds);

        // show the dialog via the GUI system, call showAsync (as we are a background thread),
        // the system makes sure that everything shows up on the EDT.
        Gui.getGuiSystem().createMessage(msg).showAsync();
        return null;
    }

}
