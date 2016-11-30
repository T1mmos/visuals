package gent.timdemey.visuals.demo.loading;

import gent.timdemey.visuals.core.kernel.DefaultGuiBuilder;
import gent.timdemey.visuals.core.kernel.GuiLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.demo.action.DemoActionFactory;

/**
 * Attaches a {@link DemoActionFactory} on the {@link DefaultGuiBuilder}.
 * @author Timmos
 */
public class DemoActionFactoryLoader implements GuiLoader {

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        builder.setActionFactory(new DemoActionFactory());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Demo Action Factory";
    }

}
