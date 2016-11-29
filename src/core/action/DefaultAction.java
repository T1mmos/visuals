package core.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import core.ui.IconKey;
import core.ui.ImgSize;
import core.util.Gui;
import core.util.Unchecked;

/**
 * An implementation of a Swing action, created upon a {@link GuiAction}.
 * @author Timmos
 *
 */
public class DefaultAction extends AbstractAction {

    private final GuiAction action;

    private DefaultAction(GuiAction action, Icon icon) {
        super(action.getTitle(), icon);
        this.action = action;
    }

    /**
     * Creates a Swing action, using the icon, action, accelerator
     * and other properties from the given {@link GuiAction}.
     * @param action the action property bundle
     * @return the Swing action
     */
    public static DefaultAction create(GuiAction action, ImgSize size) {
        Unchecked.nullPtr(action);
        Unchecked.nullPtr(action.getTitle(), "A title ID cannot be null");
        IconKey iconId = action.getIconKey();
        Icon icon = iconId == null ? null : Gui.getIconFactory().getDrawable(iconId).getIcon(size);
        DefaultAction defAction = new DefaultAction(action, icon);
        return defAction;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        action.actionPerformed(e);
    }
}
