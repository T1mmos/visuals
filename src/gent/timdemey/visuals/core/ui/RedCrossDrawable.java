package gent.timdemey.visuals.core.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * An eye-catching, programmatically generated {@link Drawable} that can be used as a dummy, showing "IMAGE NOT FOUND" on a
 * red cross.
 * <p>The intent of this {@link Drawable} implementation is providing a fallback whenever a primary image cannot
 * be rendered due to missing external resources, such as a file. The image represented by this implementation is
 * rendered programmatically, and thus always available. Using the image helps to track missing resources, as
 * the "IMAGE NOT FOUND" text on top of a red cross is intentionally eye-catching.
 * @author Timmos
 */
public final class RedCrossDrawable implements Drawable {

    private final String what;

    /**
     * Creates a new red cross. The rendered image will include the external identifier
     * of the given icon key.
     * @param id the icon key that points to the missing resource, the reason to use a red cross image
     */
    public RedCrossDrawable (IconKey id) {
        this(id.getExternalIdentifier());
    }

    /**
     * Creates a new red cross. The rendered image will include the given text.
     * @param what the reason to use a red cross image
     */
    public RedCrossDrawable (String what) {
        this.what = what;
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getImage());
    }

    @Override
    public Image getImage() {
        return getImage(ImgSize.DIALOG_CONTENT);
    }

    @Override
    public Icon getIcon(ImgSize size) {
        return new ImageIcon(getImage(size));
    }

    private static final int MARGIN = 2;

    @Override
    public Image getImage(ImgSize size) {
        int end = size == ImgSize.ORIGINAL ? 128 : size.getPixels();
        BufferedImage img = new BufferedImage(end, end, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, end, end);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(end / 4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        g.setColor(Color.RED);
        g.drawLine(MARGIN + end / 6, MARGIN + end / 6, end - 1 - MARGIN - end / 6, end - 1 - MARGIN - end / 6);
        g.drawLine(MARGIN + end / 6, end - 1 - MARGIN - end / 6, end - 1 - MARGIN - end / 6, MARGIN + end / 6);

        g.setColor(Color.white);
        g.setStroke(new BasicStroke(2));
        g.drawRect(0, 0, end - 1, end - 1);

        if (size.getPixels() >= ImgSize.DIALOG_CONTENT.getPixels()) {
            g.setColor(Color.white);
            Font font = Font.decode("Arial Bold " + size.getPixels() / 5);
            g.setFont(font);
            int height = g.getFontMetrics(font).getHeight();
            String[] parts = new String[] { what };

            for (int i = 0; i < parts.length; i++) {
                int y = (int) (size.getPixels() / 2 - 1.0 * parts.length / 2 * height + (i + 1) * height - 2);
                int x = size.getPixels() / 2 - g.getFontMetrics(font).stringWidth(parts[i]) / 2;
                g.drawString(parts[i], x, y);
            }
        }
        return img;
    }
}
