package gent.timdemey.visuals.core.ui;

/**
 * Enumerates the core's standard set of icon keys.
 * @author Timmos
 */
public enum DefaultIconKey implements IconKey {

    INFO("info_popup-48.png"),
    ERROR("poison-48.png"),
    WARNING("error-48.png"),
    /** Icon that can be used a fallback, when the actual resource of another image is not yet available. */
    NOT_IMPLEMENTED("under_construction.png",false),
    V("V.png",false),
    FLAG_UK("flag_UK.png",false);

    private final String filename;
    private final boolean skinnable;

    private DefaultIconKey (String filename){
        this.filename = filename;
        this.skinnable = true;
    }

    private DefaultIconKey(String filename, boolean skinnable) {
        this.filename = filename;
        this.skinnable = skinnable;
    }

    @Override
    public String getExternalIdentifier() {
        return filename;
    }

    @Override
    public boolean isSkinnable() {
        return skinnable;
    }
}
