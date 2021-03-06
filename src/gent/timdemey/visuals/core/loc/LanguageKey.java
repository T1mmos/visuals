package gent.timdemey.visuals.core.loc;

import gent.timdemey.visuals.core.key.ExternalKey;
import gent.timdemey.visuals.core.ui.IconKey;


/**
 * Identifier for languages.
 * @author Timmos
 */
public interface LanguageKey extends ExternalKey {

    /**
     * Gets the icon key for an image representing this language.
     * @return an icon key
     */
    public IconKey getIconKey();

    /**
     * Gets the human readable name of this language.
     * @return the human readable language name
     */
    public String getName();
}
