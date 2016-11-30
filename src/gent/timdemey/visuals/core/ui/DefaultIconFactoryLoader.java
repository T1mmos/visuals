package gent.timdemey.visuals.core.ui;

import gent.timdemey.visuals.core.kernel.DefaultGuiBuilder;
import gent.timdemey.visuals.core.kernel.GuiLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;

/**
 * Instantiates a {@link DefaultIconFactory} and installs it on a {@link DefaultGuiBuilder}.
 * @author Timmos
 */
public class DefaultIconFactoryLoader implements GuiLoader {

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        // TODO: read icon factory from configuration
        builder.setIconFactory(new DefaultIconFactory());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Default Icon Factory Loader";
    }
}
