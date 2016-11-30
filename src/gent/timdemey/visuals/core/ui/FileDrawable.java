package gent.timdemey.visuals.core.ui;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A {@link Drawable} that is loaded from a file on disk.
 * @author Timmos
 */
public final class FileDrawable implements Drawable {

    private final Map<ImgSize, Image> imgs;

    /**
     * Creates a new image resource from the given input stream.
     * @param stream the input stream delivering the bits and bytes for an image or icon
     * @return an {@code ImgResource}
     * @throws IOException if an I/O error occurs
     */
    public static FileDrawable create(InputStream stream) throws IOException {
        Map<ImgSize, Image> imgs;
        Image img = ImageIO.read(stream);
        imgs = new HashMap<>();
        for (ImgSize size : ImgSize.values()){
            if (size == ImgSize.ORIGINAL){
                imgs.put(size, img);
            } else {
                int width = -1; int height = -1;
                while ((width = img.getWidth(null)) == -1) {/**/}
                while ((height = img.getHeight(null)) == -1)  {/**/}
                double scaleX = 1.0 * size.getPixels() / width;
                double scaleY = 1.0 * size.getPixels() / height;
                double scale = Math.max(scaleX, scaleY);
                Image scaledImg = img.getScaledInstance((int) (scale * width), (int) (scale * height), Image.SCALE_SMOOTH);
                imgs.put(size, scaledImg);
            }
        }
        return new FileDrawable(imgs);
    }

    private FileDrawable (Map<ImgSize, Image> imgs){
        this.imgs = imgs;
    }

    @Override
    public Icon getIcon (){
        return new ImageIcon(getImage());
    }

    @Override
    public Icon getIcon(ImgSize size) {
        return new ImageIcon(getImage(size));
    }

    @Override
    public Image getImage (){
        return imgs.get(ImgSize.ORIGINAL);
    }

    @Override
    public Image getImage(ImgSize size) {
        return imgs.get(size);
    }
}
