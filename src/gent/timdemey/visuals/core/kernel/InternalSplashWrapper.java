package gent.timdemey.visuals.core.kernel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gent.timdemey.visuals.core.util.Unchecked;
import gent.timdemey.visuals.core.util.XThreadFactory;

/**
 * Wraps a {@link Splash} in a dialog and actually shows the splash.
 * @author Timmos
 */
public final class InternalSplashWrapper {

    private final Splash splash;
    private final JDialog            dialog;
    private final InternalMainLoader loader;

    /**
     * Creates a splash with the given icon and the background loader
     * of which the loading state can be visually reflected on this splash.
     * @param splash the splash instance, which provides the content and a load progress callback
     * @param loader the background loader of which to reflect the loading state
     */
    InternalSplashWrapper(Splash splash, InternalMainLoader loader) {
        this.splash = splash;
        this.dialog = new JDialog();
        this.loader = loader;

        JPanel content = splash.getContent();
        Unchecked.nullPtr(content, "The splash implementation '" + splash.getClass().getSimpleName() + "' returned no visual content");

        MouseAdapter dragger = new Dragger(dialog);

        dialog.addMouseMotionListener(dragger);
        dialog.addMouseListener(dragger);
        dialog.setUndecorated(true);
        dialog.setContentPane(content);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);
        dialog.setOpacity(0f);

        loader.addLoadingListener(new DoListen());
    }

    private static class Dragger extends MouseAdapter {

        private final JDialog dialog;

        private int xstart_mouse, ystart_mouse;
        private int xstart_dialog, ystart_dialog;

        private Dragger(JDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getXOnScreen() - xstart_mouse;
            int dy = e.getYOnScreen() - ystart_mouse;
            dialog.setLocation(xstart_dialog + dx, ystart_dialog + dy);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            xstart_mouse = e.getXOnScreen();
            ystart_mouse = e.getYOnScreen();
            xstart_dialog = dialog.getLocation().x;
            ystart_dialog = dialog.getLocation().y;
        }
    }

    /**
     * Shows this splash screen.
     */
    void show() {
        loader.addLoadingListener(new DoListen());
        LoadingListener splashListener = splash.getLoadingListener();
        if (splashListener != null) {
            loader.addLoadingListener(splashListener);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog.setVisible(true);
            }
        });

        ExecutorService serv = Executors.newSingleThreadScheduledExecutor(new XThreadFactory("Splash FadeIn"));
        serv.execute(new FadeInTask(dialog, splash.getFadeInDuration()));
    }

    private static class FadeInTask implements Runnable {

        private static final int FPS = 25;

        private final JDialog dialog;
        private final int     duration;

        private FadeInTask(JDialog dialog, int duration) {
            this.dialog = dialog;
            this.duration = duration;
        }

        @Override
        public void run() {
            int frames;
            if (duration < FPS) {
                frames = 1;
            } else {
                frames = duration / FPS;
            }

            for (int i = 1; i <= frames; i++) {
                final float fadein = 1.0f * i / frames;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        dialog.setOpacity(fadein);
                    }
                });

                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * Disposes this splash screen.
     */
    void dispose (){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
    }

    private class DoListen implements LoadingListener {
        @Override
        public void loadingStarted(final LoadingEvent e) {
            //
        }

        @Override
        public void loadingEnded(final LoadingEvent e) {
            //
        }

        @Override
        public void loadingFinished() {
            Timer timer = new Timer();
            timer.schedule(new DoDispose(timer), splash.getTimeout());
        }
    }

    private final class DoDispose extends TimerTask {
        private final Timer timer;

        private DoDispose(Timer t) {
            this.timer = t;
        }

        @Override
        public void run() {
            dispose();
            timer.cancel();
            this.cancel();
        }
    }
}
