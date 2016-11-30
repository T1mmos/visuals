package gent.timdemey.visuals.demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import gent.timdemey.visuals.core.action.GuiAction;
import gent.timdemey.visuals.core.cmd.ExitApplicationCommand;
import gent.timdemey.visuals.core.loc.DefaultLocKey;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.demo.ui.DemoIconKey;

/**
 * GUI action to exit the application. It posts an {@link ExitApplicationCommand} on the command bus.
 * @author Timmos
 */
public class ExitApplicationAction implements GuiAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        new ExitApplicationCommand().post();
    }

    @Override
    public String getTitle() {
        return DefaultLocKey.TITLE_ACTION_EXIT.get();
    }

    @Override
    public IconKey getIconKey() {
        return DemoIconKey.EXIT_APPLICATION;
    }

    @Override
    public KeyStroke getAccelerator() {
        return KeyStroke.getKeyStroke("alt F4");
    }

    @Override
    public String getTooltip() {
        return DefaultLocKey.TOOLTIP_ACTION_EXIT.get();
    }
}
