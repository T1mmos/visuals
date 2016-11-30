package gent.timdemey.visuals.core.ui;

import gent.timdemey.visuals.core.loc.LocKey;

/**
 * Represents a button used in dialogs, used to close the dialog.
 * @author Timmos
 */
public interface DialogButton {

    /**
     * Gets the localization key, to localize the button text.
     * @return the localization key for this button
     */
    LocKey getLocKey();

    /**
     * Gets the order of this button. The button with the lowest order becomes a {@link ChoiceDialog}'s
     * default button.
     * @return the order of this button - lower means higher chance of becoming the default button
     */
    int getOrder ();
}
