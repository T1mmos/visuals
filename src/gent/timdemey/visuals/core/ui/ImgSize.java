package gent.timdemey.visuals.core.ui;

/**
 * Defines and makes abstraction of some standard image sizes.
 * @author Timmos
 */
public enum ImgSize {
    /** The size of a menu item icon - 16x16. */
    MENU_ITEM(16),
    /** The size of a button / toolbar / ... icon - 24x24. */
    BUTTON(24),
    /** The size of an icon in the left area of a message dialog - 48x48. */
    DIALOG_CONTENT(48),
    /** Big - 128x128. */
    BIG(128),
    /** An image's original size. */
    ORIGINAL (0);

    /** The size of the square where the image should fit into, in pixels. */
    private final int pixels;

    private ImgSize(int pixels){
        this.pixels = pixels;
    }

    /**
     * Gets the square dimensions of this {@link ImgSize}.
     * @return the number of pixels
     */
    public int getPixels (){
        return pixels;
    }
}
