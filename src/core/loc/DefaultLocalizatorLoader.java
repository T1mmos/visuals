package core.loc;

import core.kernel.AppLoader;
import core.kernel.DefaultAppBuilder;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;

/**
 * The core's default localizator loader, installing {@link DefaultLocalizator#INSTANCE} onto
 * a {@link DefaultAppBuilder}.
 * @author Timmos
 */
public class DefaultLocalizatorLoader implements AppLoader {

    @Override
    public void load(DefaultAppBuilder builder) throws LoadingFailedException {
        // TODO: implement:
        // - check OS settings
        // - check configuration
        // - derive a language
        // - add the defaultlocalizator, given the language.
        builder.setLocalizator(DefaultLocalizator.INSTANCE);
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }
    @Override
    public String getInternalName() {
        return "Default Localizator Loader";
    }

}
