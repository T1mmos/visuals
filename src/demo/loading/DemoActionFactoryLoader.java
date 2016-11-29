package demo.loading;

import core.kernel.DefaultGuiBuilder;
import core.kernel.GuiLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import demo.action.DemoActionFactory;

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
