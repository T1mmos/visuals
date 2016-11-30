package gent.timdemey.visuals.core.loc;

import gent.timdemey.visuals.core.util.Loc;

/**
 * Enumerates the core's localization keys.
 * @author Timmos
 */
@SuppressWarnings("javadoc")
public enum DefaultLocKey implements LocKey {


    LABEL_OK("label.ok"),
    LABEL_CANCEL("label.cancel"),
    LABEL_YES("label.yes"),
    LABEL_NO("label.no"),

    TITLE_ACTION_EDIT_PREFERENCES("title.action.edit_preferences"),

    TOOLTIP_ACTION_EDIT_PREFERENCES("tooltip.action.edit_prefences"),

    TITLE_ACTION_EXIT("title.action.exit"),
    TOOLTIP_ACTION_EXIT("tooltip.action.exit"),

    TITLE_DIALOG_ERROR("title.dialog.error"),
    TITLE_DIALOG_INFO("title.dialog.info"),
    TITLE_DIALOG_WARN("title.dialog.warn"),

    TITLE_MENU_FILE("title.menu.file"),
    TITLE_MENU_EDIT("title.menu.edit"),

    TITLE_WINDOW_MAIN("title.window.main"),

    X_NOT_IMPLEMENTED("core.not_implemented");

    private final String locKey;

    private DefaultLocKey (String locKey) {
        this.locKey = locKey;
    }

    @Override
    public String getExternalIdentifier() {
        return locKey;
    }

    @Override
    public String get() {
        return Loc.get(this);
    }

    @Override
    public String get(Object ... args) {
        return Loc.get(this, args);
    }
}
