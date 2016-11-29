package core.kernel;

import java.util.Arrays;
import java.util.List;

import core.cfg.MetaConfigKey;
import core.log.msg.ErrorMsg;
import core.log.msg.InfoMsg;
import core.util.Log;

/**
 * The framework core entrypoint, with a main method.
 * <p>
 * A reference to a plugin should be provided in order for the framework to do something useful. Either via the program
 * arguments or the meta configuration file, the (raw) key {@link MetaConfigKey#PLUGIN} must be set. If not set, the
 * framework will use an internal plugin that shows an information dialog on how to set up the framework.
 * @author Timmos
 */
public final class Start {

    /**
     * Starts the framework.
     * @param args the application arguments
     */
    public static void main(String[] args) {
        Thread thread  = new Thread (new DoStart(args), "Application Starter");
        thread.start();
    }

    private static class DoStart implements Runnable {

        private final String[] args;

        private DoStart (String[] args) {
            this.args = args;
        }

        @Override
        public void run() {
            Log.info(InfoMsg.LOAD_STARTED);
            Log.info(InfoMsg.THREAD_STARTED, Thread.currentThread().getName());
            InternalMainLoader loader;
            try {
                loader = new InternalMainLoader(args);
            } catch (RuntimeException rte) {
                onLoadingFailed(new LoadingFailedException(rte));
                return;
            }
            InternalSplashWrapper splash = null;
            try {
                splash = loader.getSplash();
                if (splash != null) {
                    splash.show();
                }
            } catch (RuntimeException rte) {
                Log.error(ErrorMsg.SPLASH_RETRIEVAL_FAIL);
                Log.error(rte);
            }
            try {
                loader.load();
            } catch (LoadingFailedException ex) {
                if (splash != null) {
                    splash.dispose();
                }
                onLoadingFailed(ex);
            }
            Log.info(InfoMsg.THREAD_FINISHED, Thread.currentThread().getName());
            Log.info(InfoMsg.LOAD_FINISHED);
            Log.dropRawLogging();
        }
    }

    private static void onLoadingFailed(final LoadingFailedException ex) {
        // add more reporters if necessary
        List<LoadErrorReporter> reporters = Arrays.asList(new GuiErrorReporter(), new LogErrorReporter());

        for (LoadErrorReporter reporter : reporters){
            reporter.report(ex);
        }
    }
}
