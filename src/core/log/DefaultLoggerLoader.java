package core.log;

import core.kernel.AppLoader;
import core.kernel.DefaultAppBuilder;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

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
