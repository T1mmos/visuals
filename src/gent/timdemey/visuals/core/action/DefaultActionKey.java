package gent.timdemey.visuals.core.action;


/**
 * Enumerates core action keys.
 * @author Timmos
 */
public enum DefaultActionKey implements ActionKey {

    /** Key for an action to edit preferences. */
    EDIT_PREFERENCES,
    /** Key for an action that leads to exiting the application. */
    EXIT_APPLICATION;

    private DefaultActionKey() {
    }
}
