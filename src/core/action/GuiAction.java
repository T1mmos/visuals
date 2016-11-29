package core.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import core.ui.IconKey;

/**
 * Bundles various properties useful for a Swing action, such as title, icon, accelerator, etc.
 * @author Timmos
 */
public interface GuiAction {

    /**
     * Executes the action. See {@link Action#actionPerformed(ActionEvent)}.
     * @param e the action event
     */
    public void actionPerformed (ActionEvent e);

    /**
     * Gets a localization identifier for this action, used to provide a
     * localized human-readable string to the GUI system.
     * @return a localization identifier
     */
    public String getTitle();

    public String getTooltip();

    /**
     * Gets an icon identifier for this action, used to provide an icon
     * to the GUI system. {@code null} is allowed - no icon will be shown.
     * @return an icon identifier
     */
    public IconKey getIconKey ();

    /**
     * Gets the accelerator keystroke for this action. Returning
     * {@code null} is allowed.
     * @return the accelerator keystroke
     */
    public KeyStroke getAccelerator ();
}
