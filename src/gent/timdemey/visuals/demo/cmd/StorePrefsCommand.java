package gent.timdemey.visuals.demo.cmd;

import gent.timdemey.visuals.core.cfg.DefaultConfigKey;
import gent.timdemey.visuals.core.cmd.Command;
import gent.timdemey.visuals.core.cmd.Priority;
import gent.timdemey.visuals.demo.action.ConfigureAction.ConfigParam;

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
        return null;
    }

}
