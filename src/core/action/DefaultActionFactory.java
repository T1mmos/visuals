package core.action;

import javax.swing.AbstractAction;
import javax.swing.Action;

import core.ui.ImgSize;
import core.util.Unchecked;

/**
 * The default action factory, supporting only the base actions.
 * @author Timmos
 */
public class DefaultActionFactory implements ActionFactory {


    @Override
    public Action getAction(ActionKey key, ActionLocation location) {
        return getAction(key, null, location);
    }

    @Override
    public Action getAction(ActionKey key, ActionLocation location, ImgSize size) {
        return getAction(key, null, location, size);
    }

    @Override
    public Action getAction(ActionKey key, Object param, ActionLocation location) {
        return getAction(key, param, location, ImgSize.MENU_ITEM);
    }

    @Override
    public Action getAction(ActionKey key, Object param, ActionLocation location, ImgSize size) {
        Unchecked.nullPtr(key, location, size);
        GuiAction act = getGuiAction(key, param);
        if (act == null)
            throw new IllegalArgumentException("Undefined action: " + key);
        AbstractAction action = DefaultAction.create(act, size);


        if (DefaultActionLocation.MENU.equals(location)) {
            action.putValue(Action.SHORT_DESCRIPTION, act.getTooltip());
            action.putValue(Action.ACCELERATOR_KEY, act.getAccelerator());
        } else if (DefaultActionLocation.BUTTON.equals(location)) {
            action.putValue(Action.SHORT_DESCRIPTION,
                            (act.getTooltip() == null ? "" : act.getTooltip())
                            + (act.getAccelerator() == null ? "" : " (" + act.getAccelerator().toString().replace("pressed ", "") + ")"));
        } else if (DefaultActionLocation.BUTTON_TINY.equals(location)) {
            action.putValue(Action.NAME, null);
            action.putValue(Action.SHORT_DESCRIPTION,
                            act.getTitle()
                            + (act.getTooltip() == null ? "" : " - " + act.getTooltip())
                            + (act.getAccelerator() == null ? "" : " (" + act.getAccelerator().toString().replace("pressed ", "") + ")"));
        }

        return action;
    }

    /**
     * Gets a gui action identified by the given {@link ActionKey}.
     * @param id the key identifying the action
     * @return a GUI action
     */
    protected GuiAction getGuiAction(ActionKey id, Object param) {
        throw new UnsupportedOperationException(
                        "The framework doesn't provide actions, probably a subclass didn't specify an action for id: "
                                        + id);
    }
}
