package gent.timdemey.visuals.core.kernel;

/**
 * A virtual builder that allows {@link Loader}s during Loading Phase III to further
 * initialize the system.
 * <p>Loaders may use methods to interact with subsequent Loaders, by setting
 * properties that other Loaders may use, using getter methods.
 * <p>The default init builder has no methods.
 * @author Timmos
 */
public class DefaultInitBuilder implements Buildable {

    @Override
    public Void build() {
        // this builder doesn't actually build
        return null;
    }
}
