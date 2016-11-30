package gent.timdemey.visuals.core.kernel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import gent.timdemey.visuals.core.ui.IconFactory;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.util.Unchecked;

/**
 * A splash with an image and a loading bar at the bottom, showing the load progress.
 * @author Timmos
 */
public class DefaultSplash implements Splash {

    private final JProgressBar progressBar;
    private final JLabel       label;
    private final Icon icon;

    /**
     * Creates a splash using the given file name to display an image.
     * No {@link IconKey} can be used, as an {@link IconFactory} has not yet been installed - the splash is
     * shown during application load.
     * @param iconFile an image's file name
     */
    public DefaultSplash (String iconFile) {
        this(new ImageIcon(iconFile));
    }

    /**
     * Creates a splash using the given icon to display an image.
     * No {@link IconKey} can be used, as an {@link IconFactory} has not yet been installed - the splash is
     * shown during application load.
     * @param icon a
     */
    public DefaultSplash (Icon icon) {
        Unchecked.nullPtr(icon);

        this.progressBar = new JProgressBar();
        this.label = new JLabel(" ");
        this.icon = icon;

        progressBar.setIndeterminate(true);
        progressBar.setUI(new LoadProgressBarUI());
    }

    @Override
    public JPanel getContent() {
        JPanel content = new JPanel(new MigLayout("insets 0, pack"));
        content.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setFont(Font.decode("Arial 10"));

        JLabel lab = new JLabel(icon);

        content.add(lab, "wrap");
        content.add(label, "gap 5 0 0 0, growx, wrap");
        content.add(progressBar, "growx");
        return content;
    }

    @Override
    public LoadingListener getLoadingListener() {
        return new DoListen();
    }

    private class DoListen implements LoadingListener {
        @Override
        public void loadingStarted(final LoadingEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (e.getMaximum() == 0) {
                        progressBar.setIndeterminate(true);
                    } else {
                        //      progressBar.setIndeterminate(false);
                        progressBar.setMaximum(e.getMaximum());
                        progressBar.setValue(e.getProgress());
                    }
                    label.setText(e.getSource().getInternalName() + "...");
                }
            });
        }

        @Override
        public void loadingEnded(final LoadingEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.setMaximum(e.getMaximum());
                    progressBar.setValue(e.getProgress());
                }
            });
        }

        @Override
        public void loadingFinished() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    label.setText("Done");
                }
            });
        }
    }

    @Override
    public int getTimeout() {
        return 4;
    }

    @Override
    public int getFadeInDuration() {
        return 0;
    }
}
