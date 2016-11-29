package core.ui;

import java.awt.Image;

import javax.swing.Icon;

/**
 * Represents a drawable resource for usage in a GUI.
 * @author Timmos
 */
public interface Drawable {

    /**
     * Gets this drawable in the form of an Swing icon, in its original size.
     * @return an {@link Icon}
     */
    public Icon getIcon ();

    /**
     * Gets this drawable in the form of an Swing icon in the given
     * size.
     * @param size the maximum width and height of the icon
     * @return an {@link Icon}
     */
    public Icon getIcon (ImgSize size);

    /**
     * Gets this drawable in the form of an Swing image.
     * @return an {@link Image}
     */
    public Image getImage ();

    /**
     * Gets this drawable in the form of an Swing image in the given
     * size.
     * @param size the maximum width and height of the icon
     * @return an {@link Image}
     */
    public Image getImage (ImgSize size);
}
