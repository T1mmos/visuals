package gent.timdemey.visuals.core.kernel;

import javax.swing.JPanel;

/**
 * An interface that allows to customize a splash screen shown during application load.
 * @author Timmos
 */
public interface Splash {

    /**
     * Gets the content of this splash. For example, a standard splash screen would return a panel
     * that contains an image. This method should not return {@code null}. The panel should preferably
     * not contain Swing components with a visible look & feel as the final L&F is not set yet. (e.g. a
     * button is not OK, but a label is OK, especially with a fixed font).
     * @return a panel with the splash screen content
     */
    public JPanel getContent();

    /**
     * Returns a {@link LoadingListener} via which the kernel will inform this splash about load changes.
     * A splash can change its visuals dynamically based on load progress. For example, a standard
     * splash screen may show a progress bar indicating the load progress.
     * @return a loading listener if this splash wants to be informed about load progress, or {@code null}
     * otherwise
     */
    public LoadingListener getLoadingListener ();

    /**
     * Returns how long the splash screen should remain visible after loading has finished, in milliseconds.
     * @return the splash timeout period
     */
    public int getTimeout();

    /**
     * Returns how long the splash screen should be fading in until fully opaque, expressed in milliseconds.
     * @return how long the splash screen will be fading in - a negative value is treated as zero
     */
    public int getFadeInDuration();
}
