package gent.timdemey.visuals.core.ui;

import com.alee.laf.WebLookAndFeel;

import gent.timdemey.visuals.core.kernel.DefaultGuiBuilder;
import gent.timdemey.visuals.core.kernel.GuiLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;

/**
 * A Look and Feel loader that determines the LAF by looking at the configuration.
 * @author Timmos
 */
public class DefaultLookAndFeelLoader implements GuiLoader {

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        WebLookAndFeel.install();
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.BIG;
    }

    @Override
    public String getInternalName() {
        return "Default Look and Feel";
    }
}
