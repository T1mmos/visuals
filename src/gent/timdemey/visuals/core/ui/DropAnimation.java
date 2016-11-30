package gent.timdemey.visuals.core.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Defines an animation shown on the glass pane when a user is about to drop an acceptable 'something' on the application.
 * @author Timmos
 */
public interface DropAnimation {
    
    /**
     * Returns the total number of animated frames in this animation. An implementation should not care about an 
     * unanimated screen to start with, as the framework takes care of that.
     * @return the total number of animation frames shown in this animation
     */
    public int getFrameCount ();
    
    /**
     * Renders this animation's <i>n</i>th frame on the glass pane. A buffered image containing a rendering of the
     * application's content panel is provided for animations that may use it. The buffered image should not be altered
     * as it is used throughout the entire duration of animation (including going forward and backwards in the animation
     * sequence). 
     * @param g the graphics context of the glass pane, to draw on it
     * @param content the buffered image that contains a rendering of the content panel, right before the animation
     * started
     * @param frame the frame index in the animation, where frame zero is already part of the animation sequence (so the 
     * implementation shouldn't reserve any index for a non-animated frame). This method will be called with <i>frame</i>
     * being at least 0 and at most {@link #getFrameCount()} - 1. 
     */
    public void renderGlobalDrop (Graphics2D g, BufferedImage content, int frame);
}
