package gent.timdemey.visuals.demo.ui;

import gent.timdemey.visuals.core.kernel.AppLoader;
import gent.timdemey.visuals.core.kernel.DefaultAppBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;


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
