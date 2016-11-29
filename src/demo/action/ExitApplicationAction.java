package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.cmd.ExitApplicationCommand;
import core.loc.DefaultLocKey;
import core.ui.IconKey;
import demo.ui.DemoIconKey;

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
