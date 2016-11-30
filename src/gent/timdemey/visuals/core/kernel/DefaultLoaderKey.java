package gent.timdemey.visuals.core.kernel;

/**
 * Lists the core's default set of loader keys.
 * @author Timmos
 */
public enum DefaultLoaderKey implements LoaderKey {

    /** Points to the command manager loader handle. */
    COMMAND_MANAGER,
    /** Points to the exit callback loader handle. */
    EXIT_CALLBACK,
    /** Points to the application configuration loader handle. */
    CONFIGURATION,
    /** Points to the localizator loader handle. */
    LOCALIZATOR,
    /** Points to the logger loader handle. */
    LOGGER,
    /** Points to the plugin configuration loader handle. */
    PLUGIN_CONFIGURATION,
    /** Points to the converter loader handle. */
    CONVERTERS,
    /** Points to the resource manager handle. */
    RESOURCE_MANAGER,

    /** Points to the GUI system loader handle. */
    GUI_SYSTEM,
    /** Points to the action factory loader handle. */
    ACTION_FACTORY,
    /** Points to the look and feel loader handle. */
    LOOK_AND_FEEL,
    /** Points to the icon factory loader handle. */
    ICON_FACTORY,
    /** Points to the UI Readable factory. */
    UI_RENDERERS,
}
