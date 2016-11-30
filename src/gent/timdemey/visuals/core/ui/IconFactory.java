package gent.timdemey.visuals.core.ui;

/**
 * Hands out a {@link Drawable} for a given {@link IconKey}.
 * @author Timmos
 */
public interface IconFactory {

    /**
     * Returns a drawable resource given an icon key.
     * @param id the key
     * @return a {@link Drawable}
     */
    public Drawable getDrawable (IconKey id);

    /**
     * Returns the key for the icon used as fallback icon (when an icon resource is missing).
     * If {@code null} is returned, the framework will generate an image programmatically.
     * @return the key for the fallback icon
     */
    public IconKey getDefaultKey();
}
