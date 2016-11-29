package demo.ui;

import core.ui.IconKey;

/**
 * Keys for demo-specific icons/images.
 * @author Timmos
 */
public enum DemoIconKey implements IconKey {
    /** A badminton racket. */
    BADMINTON("badminton_racquet-48.png"),
    NAME("name-48.png"),
    /** Brutus. Don't mess with this guy. */
    BRUTUS("brutus-48.png"),

    BUMBLEBEE("bumblebee-48.png"),
    SHEEP("sheep-48.png"),
    MONKEY("gorilla-48.png"),
    FLY("fly-48.png"),

    WIND("air_element-48.png"),
    MULTIPLE_CHOICE("multiple_choice-48.png"),

    SETTINGS("settings-48.png"),
    KEY("key-48.png"),
    HOURGLASS("hourglass_sand_bottom-48.png"),
    DOCUMENT("document-48.png"),
    EXIT_APPLICATION("exit-48.png"),
    VIKING("viking.png"),
    ;

    private final String id;

    private DemoIconKey(String id) {
        this.id = id;
    }

    @Override
    public String getExternalIdentifier() {
        return id;
    }

    @Override
    public boolean isSkinnable() {
        return true;
    }

}
