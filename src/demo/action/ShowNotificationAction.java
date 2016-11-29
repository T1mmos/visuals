package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.ui.IconKey;
import core.ui.MessageType;
import core.util.Gui;
import demo.loc.DemoLocKey;

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
