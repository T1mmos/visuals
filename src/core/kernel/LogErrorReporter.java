package core.kernel;

import core.util.Log;

/**
 * Reports a load error by logging it.
 * @author Timmos
 *
 */
public class LogErrorReporter implements LoadErrorReporter {

    @Override
    public void report(LoadingFailedException e) {
        Log.error(e);
    }

}
