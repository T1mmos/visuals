package core.ui;

import core.kernel.DefaultGuiBuilder;
import core.kernel.GuiLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

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
