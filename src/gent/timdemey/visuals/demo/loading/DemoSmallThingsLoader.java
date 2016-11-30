package gent.timdemey.visuals.demo.loading;

import gent.timdemey.visuals.core.kernel.DefaultInitBuilder;
import gent.timdemey.visuals.core.kernel.InitLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.ui.BlurAnimation;
import gent.timdemey.visuals.core.ui.DefaultIconKey;
import gent.timdemey.visuals.core.ui.GuiSystem;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.loc.DemoLocKey;
import gent.timdemey.visuals.demo.ui.DemoDropHandler;

/**
 * Initializes the application further by setting some details.
 * @author Timmos
 */
public class DemoSmallThingsLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        GuiSystem gs = Gui.getGuiSystem();
        gs.setMainFrameIcon(DefaultIconKey.V);
        gs.setMainFrameTitle(DemoLocKey.APP_TITLE.get());
        gs.setDropPlugin(new DemoDropHandler(), new BlurAnimation());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Setting up";
    }

}
