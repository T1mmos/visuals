package gent.timdemey.visuals.core.log;

import gent.timdemey.visuals.core.kernel.AppLoader;
import gent.timdemey.visuals.core.kernel.DefaultAppBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;

/**
 * The core's default logger loader, installing a {@link DefaultLogger} onto
 * a {@link DefaultAppBuilder}.
 * @author Timmos
 */
public class DefaultLoggerLoader implements AppLoader {

    @Override
    public void load(DefaultAppBuilder builder) throws LoadingFailedException {
        builder.setLogger(new DefaultLogger());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }
    @Override
    public String getInternalName() {
        return "Default Logger Loader";
    }

}
