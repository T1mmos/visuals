package core.kernel;


/**
 * An exit callback that always allows the application to exit.
 * @author Timmos
 */
public class DefaultExitCallback implements ExitCallback {

    @Override
    public boolean isQuitAllowed() {
        return true;
    }

}
