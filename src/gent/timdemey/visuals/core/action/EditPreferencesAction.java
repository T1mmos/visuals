package gent.timdemey.visuals.core.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import gent.timdemey.visuals.core.loc.DefaultLocKey;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.util.Gui;

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
