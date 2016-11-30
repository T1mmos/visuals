package gent.timdemey.visuals.core.cmd;

import gent.timdemey.visuals.core.cfg.ConfigurationStreamException;
import gent.timdemey.visuals.core.kernel.DefaultApp;
import gent.timdemey.visuals.core.kernel.ExitCallback;
import gent.timdemey.visuals.core.kernel.ExitCodes;
import gent.timdemey.visuals.core.log.msg.ErrorMsg;
import gent.timdemey.visuals.core.util.App;
import gent.timdemey.visuals.core.util.Log;

/**
 * Commands the application to exit, if the currently installed {@link ExitCallback} approves.
 * @author Timmos
 */
public class ExitApplicationCommand extends Command<Void> {

    @Override
    public Void execute() {
        // catch all RTE's, to prevent that the app cannot be closed
        try {
            handleQuit();
        } catch (RuntimeException re) {
            Log.bug(ErrorMsg.APPLICATION_QUIT_RTE, re.getClass().getSimpleName());
            Log.bug(re);
            System.exit(ExitCodes.ERROR.getCode());
        }

        return null;
    }

    private static void handleQuit() {
        ExitCallback listener = DefaultApp.instance().getExitCallback();
        if (listener.isQuitAllowed()) {
            try {
                App.getConfiguration().save();
            } catch (ConfigurationStreamException ex) {
                Log.error(ErrorMsg.CONFIG_SAVE_FAIL);
            }
            System.exit(ExitCodes.NORMAL.getCode()); // thank you come again
        }
    }

    @Override
    public Priority getPriority() {
        return Priority.RESPONSIVE;
    }
}
