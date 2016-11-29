package core.ui;

import com.alee.managers.style.Skin;

/**
 * Represents a skin of the application, used to apply different look and feels.
 * @author Timmos
 */
public interface UISkin {

    /**
     * Gets the name of this skin.
     * @return the skin name
     */
    public String getName();

    /**
     * Gets the underlying Web Look and Feel class that must be installed in order to have a
     * working XML styling file.
     * @return the base class necessary for the XML style file
     */
    public Class<? extends Skin> getSkinClass();

    /**
     * Gets the
     * @return
     */
    public String getXmlFile();

    /**
     * Asks this skin to return a customized filename for the given base icon name. For example, a dark skin may wish
     * to use bright icons instead of the defaults; if the base name is "open" then the dark skin may return
     * "open-bright" or "open-white", or even "white/open" if using subdirectories to store skinned icons,
     * whatever the actual image file is called and wherever it is located.
     * <p>
     * The extension is not passed to this method by the {@link DefaultIconFactory default icon factory} - it expects
     * that a customized image file uses the same extension as the base file.
     * @param basename the external identifier of the {@link IconKey} for the icon, but without extension and dot
     * @return a file name of an image that depicts the same as the base image, but fits this UI skin better
     */
    public String getSkinnedIcon(String basename);
}
