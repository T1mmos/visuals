package core.kernel;

import core.cfg.Configuration;
import core.cmd.CommandManager;
import core.loc.Localizator;
import core.log.Logger;

/**
 * The core's default application builder implementation. The following features are supported:
 * <ul>
 * <li>Plugin
 * <li>Program argument configuration
 * <li>Meta configuration
 * <li>Plugin configuration
 * <li>Localizator
 * <li>Logger
 * <li>Command manager
 * <li>Exception model
 * <li>Exit callback
 * </ul>
 * <p>
 * Plugins may want to customize the non-GUI part of building the application, and may therefore need to override
 * {@link Plugin#newAppBuilder()} to return a custom builder.
 * @author Timmos
 */
public class DefaultAppBuilder implements Buildable {

    /** The plugin configuration. */
    protected Configuration          pluginCfg    = null;
    /** The localizator. */
    protected Localizator            loc;
    /** The logger. */
    protected Logger                 logger;
    /** The command manager. */
    protected CommandManager         commMan      = null;
    /** The internal exception model. */
    protected InternalExceptionModel excModel     = null;
    /** The exit callback. */
    protected ExitCallback           exitCallback = null;

    /**
     * Creates a new application builder.
     */
    protected DefaultAppBuilder() {
    }

    /**
     * Sets the logger.
     * @param logger the logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    /**
     * Sets the command manager.
     * @param cmdMan the command manager
     */
    public void setCommandManager(CommandManager cmdMan) {
        this.commMan = cmdMan;
    }

    /**
     * Sets the localizator.
     * @param loc the localizator
     */
    public void setLocalizator(Localizator loc) {
        this.loc = loc;
    }

    /**
     * Sets the exit callback.
     * @param listener the exit callback
     */
    public void setExitCallback(ExitCallback listener) {
        this.exitCallback = listener;
    }


    @Override
    public DefaultApp build() {
        return new DefaultApp(loc, logger, commMan, exitCallback);
    }
}
