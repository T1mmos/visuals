package core.cmd;

import core.kernel.AppLoader;
import core.kernel.DefaultAppBuilder;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

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
