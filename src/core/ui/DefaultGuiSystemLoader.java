package core.ui;

import core.kernel.DefaultGuiBuilder;
import core.kernel.GuiLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

/**
 * Loads the {@link GuiSystem GUI system} into the GUI builder.
 * @author Timmos
 */
public class DefaultGuiSystemLoader implements GuiLoader {

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        GuiSystem system = new DefaultGuiSystem();
        builder.setGuiSystem(system);
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }

    @Override
    public String getInternalName() {
        return "Default Gui System Loader";
    }
}
