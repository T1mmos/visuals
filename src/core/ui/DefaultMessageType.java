package core.ui;

import core.loc.DefaultLocKey;
import core.loc.LocKey;


/**
 * Enumerates the default set of {@link MessageType message types}.
 * @author Timmos
 */
public enum DefaultMessageType implements MessageType {
    /** Represents a message type for dialogs showing an error. */
    ERROR(DefaultLocKey.TITLE_DIALOG_ERROR,DefaultIconKey.ERROR),
    /** Represents a message type for dialogs showing an information message. */
    INFO(DefaultLocKey.TITLE_DIALOG_INFO,DefaultIconKey.INFO),
    /** Represents a message type for dialogs showing a warning. */
    WARNING(DefaultLocKey.TITLE_DIALOG_WARN,DefaultIconKey.WARNING);

    private final LocKey  locId;
    private final IconKey iconId;

    private DefaultMessageType(LocKey locId, IconKey iconId) {
        this.locId = locId;
        this.iconId = iconId;
    }

    @Override
    public LocKey getLocKey() {
        return locId;
    }

    @Override
    public IconKey getIconKey () {
        return iconId;
    }
}
