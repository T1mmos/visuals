package core.cmd;

import core.cfg.ConfigurationStreamException;
import core.kernel.DefaultApp;
import core.kernel.ExitCallback;
import core.kernel.ExitCodes;
import core.log.msg.ErrorMsg;
import core.util.App;
import core.util.Log;

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
