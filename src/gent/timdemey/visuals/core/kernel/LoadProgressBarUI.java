package gent.timdemey.visuals.core.kernel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ProgressBarUI;

/**
 * A progress bar UI designed for the application's splash screen, to not rely on a 
 * specific look and feel which is unknown at load time.
 * @author Timmos
 */
class LoadProgressBarUI extends ProgressBarUI implements ChangeListener, PropertyChangeListener, ActionListener {

    private static final Color STRIPE_COLOR_2 = new Color(24,50,70).darker();
    private static final Color STRIPE_COLOR_1 = new Color(170,194,51).darker();
    private static final int TIME_UPDATE = 25;
    
    
    private JProgressBar bar;

    private int borderWidth = 1;
    private int STRIPE_WIDTH = 10;
    private int STRIPE_HEIGHT = 6;
    private int STRIPE_OFFSET = 2;
    /**
     * Creates a new UI for a {@link javax.swing.JProgressBar} with default
     * attributes.
     */
    public LoadProgressBarUI() {
    }
    
    @Override
    public void installUI(JComponent c) {
        bar = (JProgressBar) c;
        bar.addChangeListener(this);
        bar.addPropertyChangeListener(this);
        
        setIndeterminate(bar.isIndeterminate());
    }
    
    @Override
    public void paint(final Graphics g, final JComponent component) {
        int max;
        int curShift = shift;
        g.setColor(STRIPE_COLOR_1);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < borderWidth; i++) {
            g.drawRect(i, i, bar.getWidth() - 2*i - 1, bar.getHeight() - 2*i - 1);
        }
        
        if (bar.isIndeterminate()) {
            max = bar.getWidth();
        } else {
            max = bar.getValue() * bar.getWidth() / bar.getMaximum();
        }
        g.setClip(borderWidth, borderWidth, max - 2*borderWidth, bar.getHeight() - 2*borderWidth);
        for (int i = 0; i < max + STRIPE_OFFSET; i++) {
            boolean stripe = ((i + 2 * STRIPE_WIDTH - curShift) / (STRIPE_WIDTH)) % 2 == 0;
            if (stripe) {
                g.setColor(STRIPE_COLOR_1);
            } else {
                g.setColor(STRIPE_COLOR_2);
            }
            g.drawLine(i, borderWidth, i - STRIPE_OFFSET, bar.getHeight() - borderWidth);
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        // return the sum of borders, contents, ...
        return new Dimension(50,  2 * borderWidth + STRIPE_HEIGHT);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        bar.repaint();
    }
    
    private Timer timer = null;
    private int shift = 0;
    

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("indeterminate".equals(evt.getPropertyName())) {
            boolean indeterminate = Boolean.parseBoolean(evt.getNewValue().toString());
            setIndeterminate(indeterminate);
        }
    }

    private void setIndeterminate (boolean indeterminate) {
        if (indeterminate) {
            timer = new Timer(TIME_UPDATE, this);
            timer.start();
        } else {
            timer.stop();
            timer = null;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        shift = ++shift % (2*STRIPE_WIDTH);
        bar.repaint();
    }

}