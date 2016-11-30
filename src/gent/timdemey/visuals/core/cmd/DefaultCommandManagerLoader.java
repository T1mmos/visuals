package gent.timdemey.visuals.core.cmd;

import gent.timdemey.visuals.core.kernel.AppLoader;
import gent.timdemey.visuals.core.kernel.DefaultAppBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;

/**
 * Installs a {@link DefaultCommandManager}.
 * @author Timmos
 */
public class DefaultCommandManagerLoader implements AppLoader {

    @Override
    public void load(DefaultAppBuilder builder) throws LoadingFailedException {
        builder.setCommandManager(new DefaultCommandManager());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Command Manager";
    }

}
