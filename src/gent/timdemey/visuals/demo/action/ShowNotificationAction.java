package gent.timdemey.visuals.demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import gent.timdemey.visuals.core.action.GuiAction;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.ui.MessageType;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.loc.DemoLocKey;

public class ShowNotificationAction implements GuiAction {

    private final MessageType type;

    public ShowNotificationAction(MessageType type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Gui.getGuiSystem().showNotification(DemoLocKey.NOTIFICATION_EXAMPLE.get(type), type);
    }

    @Override
    public String getTitle() {
        return type.getLocKey().get();
    }

    @Override
    public IconKey getIconKey() {
        return type.getIconKey();
    }

    @Override
    public KeyStroke getAccelerator() {
        return null;
    }

    @Override
    public String getTooltip() {
        return DemoLocKey.TOOLTIP_SHOW_NOTIFICATION.get(type.toString());
    }

}
