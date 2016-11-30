package gent.timdemey.visuals.core.kernel;

import com.google.common.base.Preconditions;

import gent.timdemey.visuals.core.cmd.CommandManager;
import gent.timdemey.visuals.core.loc.Localizator;
import gent.timdemey.visuals.core.log.DefaultLogger;
import gent.timdemey.visuals.core.log.Logger;
import gent.timdemey.visuals.core.util.Unchecked;

/**
 * The application's backbone. An instance of this class, or a subclass of this class,
 * is built by a {@link DefaultAppBuilder} (or a subclass of that builder class) during
 * application load, phase I. Once created, this instance is the sole backbone of the application,
 * providing access to:
 * <ul>
 * <li>Meta configuration
 * <li>Configuration
 * <li>Localizator
 * <li>Logger
 * <li>Command Manager
 * <li>...
 * </ul>
 * All of these backbone tentacles are being attached to the builder by various {@link Loader}s,
 * making application loading a pluggable system.
 * <p>
 * {@link DefaultGui} is the GUI-variant of this class. The GUI is treated as a layer on top of the application, and so
 * a complete different GUI-backbone is built using a {@link DefaultGuiBuilder} (again, subclasses may apply).
 * @see DefaultGui
 * @see DefaultGuiBuilder
 * @see DefaultAppBuilder
 * @author Timmos
 */
public class DefaultApp {

    private final Localizator            loc;
    private final Logger                 logger;
    private final CommandManager         commMan;
    private final InternalExceptionModel excModel;
    private final ExitCallback           callback;


    /**
     * Creates a backbone. This method should only be called from a {@link DefaultAppBuilder} or
     * a subclass constructor.
     * @param loc the localizator
     * @param logger the logger
     * @param commMan the command manager
     * @param callback the exit callback
     */
    protected DefaultApp(
                    Localizator loc,
                    Logger logger,
                    CommandManager commMan,
                    ExitCallback callback) {
        Unchecked.nullPtr("Some application core functionality is unset, indices: ", loc, logger,
                        commMan, callback);

        this.loc = loc;
        this.logger = logger;
        this.commMan = commMan;
        this.excModel = new InternalExceptionModel();
        this.callback = callback;
    }

    // private dummy constructor, used only by InternalNullPlugin. DO NOT USE.
    // I repeat: do not, never ever use this constructor and save yourself from
    // the end of the world.
    private DefaultApp() {
        this.loc = null;
        this.logger = new DefaultLogger();
        this.commMan = null;
        this.excModel = null;
        this.callback = null;
    }


    /**
     * Gets the localizator.
     * @return the localizator
     */
    public Localizator getLocalizator () {
        return loc;
    }

    /**
     * Gets the logger.
     * @return the logger
     */
    public Logger getLogger (){
        return logger;
    }

    /**
     * Gets the command manager.
     * @return the command manager
     */
    public CommandManager getCommandManager () {
        return commMan;
    }

    /**
     * Gets the exception model.
     * @return the exception model
     */
    public InternalExceptionModel getExceptionModel () {
        return excModel;
    }

    /**
     * Gets the exit callback.
     * @return the exit callback
     */
    public ExitCallback getExitCallback () {
        return callback;
    }

    private static DefaultApp app = null;

    private static boolean isSet (){
        return app != null;
    }

    /**
     * Installs the backbone singleton. The backbone cannot be uninstalled.
     * This method must be called only from the main loader.
     * @param backbone the application backbone - an instance of {@link DefaultAppBuilder}
     * or a subclass of it
     * @throws IllegalStateException when attempting to install a backbone more than once
     * @throws NullPointerException when {@code backbone} is {@code null}
     */
    static void install (DefaultApp backbone) {
        Preconditions.checkNotNull(backbone);
        if (isSet ()) {
            throw new IllegalStateException("Application already set up");
        }
        DefaultApp.app = backbone;
    }

    /**
     * Gets the backbone singleton.
     * @return The application backbone
     * @throws IllegalStateException when invoking this method while the backbone
     * singleton is not (yet) set
     */
    public static DefaultApp instance () {
        if (!isSet ()) {
            throw new IllegalStateException("Application not initialized");
        }
        return DefaultApp.app;
    }

    /**
     * Creates a dummy instance. This method should be called only by a {@link InternalNullPlugin}
     * and has no other purpose. DO NOT USE THIS METHOD.
     * @return a dummy {@link DefaultApp} without any application handle.
     */
    static DefaultApp dummy () {
        return new DefaultApp();
    }
}
