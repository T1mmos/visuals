package gent.timdemey.visuals.core.ui;

import gent.timdemey.visuals.core.kernel.DefaultGuiBuilder;
import gent.timdemey.visuals.core.kernel.GuiLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;

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
