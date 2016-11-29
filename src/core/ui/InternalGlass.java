package core.ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.SwingUtilities;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.window.WebFrame;

/**
 * Tries to clean up the setvisible mess with beautiful threadsafe code. It uses a non-blocking algorithm - the first thread
 * that can clean up the "stopping" state does so. The animation is treated as a finite state machine with 4 states: stopped,
 * animating (forwards), animating (backwards), and "stopping". The stopping state is where the background animation thread has
 * exited its continuous loop, which happens when the animation has ended (the user has dropped the dragged stuff or has exited the
 * frame). That is where the "stopping" state is entered. In that state, we must set the glass pane invisible again but that is done
 * on the EDT only, so to make the full thing thread-safe we need to distinguish that situation from any other state, hence the
 * state "stopping". During the lifespan of this state, a user may enter the frame again.
 * @author Timmos
 */
class InternalGlass extends WebPanel {

    /**
     * Defines the different states that a drag&drop animation can be in. Some of the state
     * transitions can only be done by the EDT (based on setting the glass pane visible or invisible),
     * others can only be set from the animation worker thread.
     * @author Timmos
     */
    private enum AnimationState {
        /** The state where no glass pane is showing. */
        stopped,
        /** The state where animation is in progress, with the start frame being the invisible glass pane. */
        animating_up,
        /** The state where animation is in progress, with the end frame being the invisible glass pane. */
        animating_down,
        /** The state where animation has stopped completely and the animation is at frame 0. The background thread
         * must reset the necessary values for a next animation run, so in this state, the EDT can't make progress and is
         * looping. The current animation runner has exited its animation loop and will terminate.
         */
        stopping,
        /** The state where both the animation thread and the EDT can attempt to set the glass pane invisible, which closes the full
         * animation state cycle.
         * <p>One of these two threads will win the race. It is necessary to make this attempt
         * from both threads. If a user doesn't enter the frame again during the stopping state, then the background must do the
         * cleanup using invokeLater. However, if a user enters the frame during the stoppping state, then EDT is running in setVisible,
         * and we would have a deadlock as the code in invokeLater is effectively running "later", so the state has not yet transitioned.
         * Therefore, both threads will attempt to forward the state if this state is encountered, but only one will effectively win
         * (if there is a race in the first place, that is). */
        setting_invisible
    }

    private final DropAnimation animation;
    private final WebFrame                        frame;
    private final ExecutorService service;
    private final AtomicReference<AnimationState> state;

    private volatile int frame_nr = -1;

    private BufferedImage buff = null;

    /**
     * Package-private constructor.
     * @param frame the frame where the drop animation is rendered on
     * @param animation the animation sequence
     */
    InternalGlass(WebFrame frame, DropAnimation animation) {
        this.animation = animation;
        this.frame = frame;
        this.service = new ThreadPoolExecutor(0, 1, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1));
        this.state = new AtomicReference<AnimationState>(AnimationState.stopped);

        doSetVisible(false);
        setBackground(Color.black);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isVisible()) {
            g.translate(-this.getX(), -this.getY());
            animation.renderGlobalDrop((Graphics2D) g, buff, frame_nr);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (!visible && state.get() == AnimationState.stopped) {
            return;
        }

        boolean success = false;
        while (!success) {
            if (state.compareAndSet(AnimationState.setting_invisible, AnimationState.stopped)) {
                doSetVisible(false);
            }
            if (visible) {
                success = state.compareAndSet(AnimationState.stopped, AnimationState.animating_up);
                if (success) {
                    int width = frame.getRootPane().getWidth();
                    int height = frame.getRootPane().getHeight();

                    buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    Graphics2D buffg2 = buff.createGraphics();
                    frame.getRootPane().paint(buffg2);
                    buffg2.dispose();

                    service.submit(new AnimationRunner(animation.getFrameCount() - 1));
                } else {
                    success = state.compareAndSet(AnimationState.animating_down, AnimationState.animating_up);
                }
            } else {
                success = state.compareAndSet(AnimationState.animating_up, AnimationState.animating_down);
            }
        }
    }

    private void doSetVisible(boolean vis) {
        super.setVisible(vis);
    }

    private class AnimationRunner implements Runnable {

        private static final int FRAME_TIME_MS = 16; // 60 FPS
        private final int maxframe;

        private AnimationRunner (int maxframe) {
            this.maxframe = maxframe;
        }

        @Override
        public void run() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    doSetVisible(true);
                }
            });

            AnimationState as = state.get();
            while (as ==  AnimationState.animating_down || as == AnimationState.animating_up) {
                boolean down = as == AnimationState.animating_down && frame_nr != 0;
                boolean up = as == AnimationState.animating_up && frame_nr != maxframe;

                if (up || down) {
                    frame_nr += up ? 1 : -1;
                    repaint();
                    try {
                        Thread.sleep(FRAME_TIME_MS);
                    } catch (InterruptedException ex) {  /* Is never interrupted. */ }
                }
                if (as == AnimationState.animating_down && frame_nr == 0) {
                    state.compareAndSet(AnimationState.animating_down, AnimationState.stopping);
                }
                as = state.get();
            }
            frame_nr = -1;
            state.compareAndSet(AnimationState.stopping, AnimationState.setting_invisible);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (state.compareAndSet(AnimationState.setting_invisible, AnimationState.stopped)) {
                        doSetVisible(false);
                    } // else: user entered frame again and EDT has already set the state to stopped in setVisible.
                }
            });
        }
    }
}
