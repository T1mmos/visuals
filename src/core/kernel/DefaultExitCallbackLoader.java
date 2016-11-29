package core.kernel;


/**
 * A Loader that installs an {@link ExitCallback exit callback} on the application builder.
 * @author Timmos
 *
 */
public class DefaultExitCallbackLoader implements AppLoader {

    @Override
    public void load(DefaultAppBuilder builder) throws LoadingFailedException {
        builder.setExitCallback(new DefaultExitCallback());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Exit Callback";
    }

}
