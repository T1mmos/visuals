package core.ui;

import core.loc.LocKey;

/**
 * Represents a message dialog type.
 * @author Timmos
 */
public interface MessageType {

    /**
     * The localization key used for a message dialog's title.
     * @return a localization key used for a message dialog's title
     */
    public LocKey getLocKey();

    /**
     * The icon key used for the icon in a message dialog's left area.
     * @return an icon key
     */
    public IconKey getIconKey ();
}
