package core.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.loc.DefaultLocKey;
import core.ui.IconKey;
import core.util.Gui;

/**
 * GUI action to start editing application preferences.
 * @author Timmos
 */
public class EditPreferencesAction implements GuiAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Gui.getGuiSystem().createMessage(DefaultLocKey.X_NOT_IMPLEMENTED.get()).showEDT();
    }

    @Override
    public String getTitle() {
        return DefaultLocKey.TITLE_ACTION_EDIT_PREFERENCES.get();
    }

    @Override
    public IconKey getIconKey() {
        return null;
    }

    @Override
    public KeyStroke getAccelerator() {
        return null;
    }

    @Override
    public String getTooltip() {
        return DefaultLocKey.TOOLTIP_ACTION_EDIT_PREFERENCES.get();
    }
}
