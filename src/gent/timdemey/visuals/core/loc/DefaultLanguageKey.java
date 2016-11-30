package gent.timdemey.visuals.core.loc;

import gent.timdemey.visuals.core.ui.DefaultIconKey;
import gent.timdemey.visuals.core.ui.IconKey;

/**
 * Default supported languages.
 * @author Timmos
 */
public enum DefaultLanguageKey implements LanguageKey {
    /** English. */
    ENGLISH("English","EN",DefaultIconKey.FLAG_UK),
    ;

    private final String  name;
    private final String key;
    private final IconKey iconKey;

    private DefaultLanguageKey(String name, String key, IconKey iconKey) {
        this.name = name;
        this.key = key;
        this.iconKey = iconKey;
    }

    @Override
    public String getExternalIdentifier() {
        return key;
    }

    @Override
    public IconKey getIconKey() {
        return iconKey;
    }

    @Override
    public String getName() {
        return name;
    }

}
