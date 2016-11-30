package gent.timdemey.visuals.core.ui;

import gent.timdemey.visuals.core.key.ExternalKey;

/**
 * Marker interface for keys with the purpose of identifying
 * {@link Drawable}s in an {@link IconFactory}.
 * @author Timmos
 */
public interface IconKey extends ExternalKey {

    /**
     * Determines whether this icon can be dynamically chosen based on the current UI skin. For example, in a dark UI
     * a bright variant of this icon may be chosen. If this method returns false, the same icon is always used. It is
     * entirely up to the installed {@link IconFactory} whether this property is respected and to locate the variants
     * of the icon.
     * @return {@code true} when this icon can dynamically change based on the UI, {@code false} when the same base icon
     * is to be used for all skins
     */
    public boolean isSkinnable();
}
