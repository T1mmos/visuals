package core.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import core.log.msg.ErrorMsg;
import core.res.DefaultResourceCategoryKey;
import core.res.ResourceManager;
import core.util.App;
import core.util.Gui;
import core.util.Log;

/**
 * An icon factory that always returns an icon, even if resources are missing.
 * This icon factory works according to the following logic:
 * <ul>
 * <li>It checks whether the icon is skinnable (adjusted to fit the currently installed L&F). If it is, this icon
 * factory tries to load the image resource as pointed to by the currently installed {@link UISkin};
 * <li>If not skinnable, the factory tries to load the base resource as pointed to by an {@link IconKey};
 * <li>If still no image resource is loaded, an image is generated programmatically.
 * </ul>
 * @author Timmos
 */
public class DefaultIconFactory implements IconFactory {

    private final Map<String, Drawable> cache;

    public DefaultIconFactory() {
        this.cache = new HashMap<>();
    }

    @Override
    public Drawable getDrawable(IconKey id) {
        if (id.isSkinnable()) {
            String skinned = null;
            try {
                skinned = getSkinned(id);
            } catch (IOException ex1) {
                Log.error(ErrorMsg.RESOURCE_SKINNED_ICON_NAME, skinned);
            }
            if (skinned != null) {
                try {
                    return read(skinned);
                } catch (IOException ex) {
                    Log.error(ErrorMsg.RESOURCE_SKINNED_ICON_LOAD, skinned);
                    return new RedCrossDrawable(skinned);
                }
            }
        } else {
            try {
                return read(id.getExternalIdentifier());
            } catch (IOException ex) {
                Log.error(ErrorMsg.RESOURCE_ICON_MISSING, id, id.getClass().getSimpleName());
            }
        }

        IconKey defkey = getDefaultKey();
        if (defkey != null) {
            try {
                return read(defkey.getExternalIdentifier());
            } catch (IOException ex) {
                Log.error(ErrorMsg.RESOURCE_FALLBACK_ICON_MISSING, getDefaultKey());
            }
        }
        return new RedCrossDrawable(id);
    }

    /**
     * Returns the path to the skinned version of the given icon, based on the currently installed UI skin.
     * @param key the icon identifier
     * @return the path to the skinned version of the icon
     * @throws IOException when the external identifier isn't fitted to tranformed into a path (e.g. it has no
     * extension) or the current skin didn't return a filename
     */
    protected static String getSkinned(IconKey key) throws IOException {
        String fullstr = null;

        String filename = key.getExternalIdentifier();
        int dot = filename.lastIndexOf(".");
        if (dot <= 0 || dot >= filename.length() - 1) {
            throw new IOException("No filename or extension in this image file: " + filename);
        }

        UISkin skin = Gui.get().getSkinFactory().getSkin();
        String first = filename.substring(0, dot);
        String ext = filename.substring(dot + 1);

        String skinned = skin.getSkinnedIcon(first);
        if (skinned == null) {
            throw new IOException("Skin " + skin.getName() + " did not return a skinned icon for key: " + key);
        }
        fullstr = skinned + "." + ext;

        return fullstr;
    }

    @Override
    public IconKey getDefaultKey() {
        return null;
    }

    /**
     * Consults the currently installed {@link ResourceManager} and requests the input stream
     * for the image identified by the given icon key, and then creates a {@link Drawable} from
     * it.
     * @param external the relative path to the icon resource (whatever the absolute icon
     * resource is - a lookup is done for you)
     * @return a drawable representing the image
     * @throws IOException when the resource manager failed to find or access the resource, or
     * construction of an image out of the resource failed
     */
    protected final Drawable read(String external) throws IOException {
        Drawable drawable = cache.get(external);
        if (drawable == null) {
            InputStream stream = App.getResourceManager().load(DefaultResourceCategoryKey.IMAGE, external);
            try {
                drawable = FileDrawable.create(stream);
            } finally {
                stream.close();
            }
            cache.put(external, drawable);
        }
        return drawable;
    }
}
