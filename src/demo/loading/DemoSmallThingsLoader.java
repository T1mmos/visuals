package demo.loading;

import core.kernel.DefaultInitBuilder;
import core.kernel.InitLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.ui.BlurAnimation;
import core.ui.DefaultIconKey;
import core.ui.GuiSystem;
import core.util.Gui;
import demo.loc.DemoLocKey;
import demo.ui.DemoDropHandler;

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
