package demo.loading;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import core.kernel.LoadingEvent;
import core.kernel.LoadingListener;
import core.kernel.Splash;
import core.res.DefaultResourceFlag;
import core.util.Log;

/**
 * The splash screen for the demo application.
 * @author Timmos
 */
public class DemoSplash implements Splash {

    private static final int   BORDER_THICKNESS             = 3;
    private static final int   TRANSPARENT_BORDER_THICKNESS = 3;
    private static final Color COLOR_PROGRESS_BAR = new Color(0, 0, 100);
    private int progress;
    private int max;
    private final PrivPanel panel = new PrivPanel();

    @Override
    public JPanel getContent() {
        panel.setLayout(new MigLayout("insets 0"));

        panel.setBorder(BorderFactory.createLineBorder(COLOR_PROGRESS_BAR, BORDER_THICKNESS));
        panel.setOpaque(false);
        panel.setBackground(new Color(90, 90, 90));

        try {
            Image img = ImageIO.read(DefaultResourceFlag.class.getResourceAsStream("img/V_splash.png"));
            JLabel label = new JLabel(new ImageIcon(img));
            panel.add(label, "push, grow");
        } catch (IOException ex) {
            Log.error(ex);
        }

        return panel;
    }

    private class PrivPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            float percent = max != 0 ? 1.0f * progress / max : 0f;
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = g2d.getClipBounds().width;
            int h = g2d.getClipBounds().height;


            g2d.setComposite(AlphaComposite.SrcOver.derive(0.85f));
            g2d.setColor(getBackground());
            int sum = BORDER_THICKNESS + TRANSPARENT_BORDER_THICKNESS;
            g2d.fillRect(sum, sum, getWidth() - 2 * sum, getHeight() - 2 * sum);

            /*      g2d.setComposite(AlphaComposite.SrcOver.derive(percent * 1.0f));
            {
                String title = "VISUALS";
                g2d.setColor(COLOR_TEXT);
                g2d.setFont(FONT_TITLE);
                Rectangle2D titRect = getStringBounds(g2d, title, 0, 0);
                g2d.drawString(title, (int) (w - titRect.getWidth()) / 2, h / 2 + (int) titRect.getHeight() / 2);
            }
             */
            /* {
                String subtitle = "Demo";
                g2d.setColor(COLOR_TEXT);
                g2d.setFont(FONT_SUBTITLE);
                FontMetrics met = g2d.getFontMetrics();
                Rectangle2D titRect = met.getStringBounds(subtitle, g2d);
                g2d.drawString(subtitle, (int) (w - titRect.getWidth()) / 2, (int) titRect.getHeight() + 150);
            }*/

            g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));
            int wp = (int) (percent * w);
            int hp = (int) (percent * h);

            g2d.setColor(COLOR_PROGRESS_BAR);
            g2d.fillRect((w - wp) / 2, h - BORDER_THICKNESS - TRANSPARENT_BORDER_THICKNESS, wp, TRANSPARENT_BORDER_THICKNESS);
            g2d.fillRect((w - wp) / 2, BORDER_THICKNESS, wp, TRANSPARENT_BORDER_THICKNESS);
            g2d.fillRect(BORDER_THICKNESS, (h - hp) / 2, TRANSPARENT_BORDER_THICKNESS, hp);
            g2d.fillRect(w - BORDER_THICKNESS - TRANSPARENT_BORDER_THICKNESS, (h - hp) / 2, TRANSPARENT_BORDER_THICKNESS, hp);
        }
    }

    @Override
    public LoadingListener getLoadingListener() {
        return new PrivLoadingListener();
    }

    @Override
    public int getTimeout() {
        return 2500;
    }

    @Override
    public int getFadeInDuration() {
        return 1000;
    }

    private class PrivLoadingListener implements LoadingListener {


        @Override
        public void loadingFinished() {
            //
        }

        @Override
        public void loadingStarted(LoadingEvent e) {
            //
        }

        @Override
        public void loadingEnded(LoadingEvent e) {
            progress = e.getProgress();
            max = e.getMaximum();
            panel.repaint();
        }

    }

}
