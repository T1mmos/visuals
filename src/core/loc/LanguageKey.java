package core.loc;

import core.key.ExternalKey;
import core.ui.IconKey;


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
