package core.action;

import core.kernel.DefaultGuiBuilder;
import core.kernel.GuiLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

/**
 * Default action factory loader.
 * @author Timmos
 */
public class DefaultActionFactoryLoader implements GuiLoader {

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        // TODO: determine action factory from configuration!
        builder.setActionFactory(new DefaultActionFactory());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Action Factory Loader";
    }
}
