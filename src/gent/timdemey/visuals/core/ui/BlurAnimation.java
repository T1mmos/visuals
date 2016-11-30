package gent.timdemey.visuals.core.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.Arrays;

/**
 * A {@link DropAnimation} that blurs the background and covers it with a semi-transparent gray layer with a
 * drop zone painted on it.
 * @author Timmos
 */
public class BlurAnimation implements DropAnimation {

    // any higher value introduces blur calculation slowness, and would need background thread pre-rendering.
    // this would require that DropAnimation should declare a preAnimation() and postAnimation() method where
    // prerendered images are calculated and removed from memory, respectively.
    // when that is implemented, we can make this static field an instance field.
    private static final int KERNEL_COUNT = 3;
    private static final BufferedImageOp[] OPERATIONS = new BufferedImageOp[KERNEL_COUNT];

    static {
        for (int i = 0; i < KERNEL_COUNT; i++) {
            int odd = 2 * i + 1;
            int quad = odd * odd;
            float val = 1.0f / quad;
            float[] mask = new float[quad];
            Arrays.fill(mask, val);
            Kernel kernel = new Kernel(odd, odd, mask);
            BufferedImageOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            OPERATIONS[i] = op;
        }
    }

    /**
     * Creates a new blurring animation.
     */
    public BlurAnimation () {
    }

    @Override
    public int getFrameCount () {
        return 16;
    }

    @Override
    public void renderGlobalDrop (Graphics2D g, BufferedImage content, int frame) {
        int opIdx = frame / (getFrameCount() / KERNEL_COUNT + 1);
        BufferedImageOp op = OPERATIONS[opIdx];

        g.drawImage(content, op, 0, 0);

        int w = content.getWidth();
        int h = content.getHeight();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f * (1 + frame) / getFrameCount()));
        g.setPaint(Color.DARK_GRAY);
        g.fillRect(0,0,w,h);

        Stroke stroke = new BasicStroke(8.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, new float[] {20.0f}, 0.0f);
        g.setStroke(stroke);
        g.setPaint(Color.WHITE);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f *  (1 + frame) / getFrameCount()));

        int w_s = (1 + frame) * 50 / getFrameCount();
        int h_s = (1 + frame) * 50 / getFrameCount();

        g.drawRect(w_s, h_s, w - 2*w_s, h - 2*h_s);
    }
}
