package demo.cmd;

import core.cfg.DefaultConfigKey;
import core.cmd.Command;
import core.cmd.Priority;
import demo.action.ConfigureAction.ConfigParam;

public class StorePrefsCommand extends Command<Void> {

    private final ConfigParam param;

    public StorePrefsCommand(ConfigParam param) {
        this.param = param;
    }

    @Override
    public Priority getPriority() {
        return Priority.BACKGROUND;
    }

    @Override
    public Void execute() throws Exception {

        DefaultConfigKey.LANGUAGE.set(param.lang);
        DefaultConfigKey.LOG_LEVEL.set(param.loglvl);
        DefaultConfigKey.GUI_SKIN.set(param.uiskin);
        return null;
    }

}
