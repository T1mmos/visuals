package demo.ui;

import core.kernel.AppLoader;
import core.kernel.DefaultAppBuilder;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;


public class DemoExitCallbackLoader implements AppLoader {

    @Override
    public void load(DefaultAppBuilder builder) throws LoadingFailedException {
        builder.setExitCallback(new DemoExitCallback());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Demo Exit Callback";
    }

}
