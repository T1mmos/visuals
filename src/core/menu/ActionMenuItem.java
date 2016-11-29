package core.menu;

import javax.swing.Action;

import core.action.ActionFactory;
import core.action.ActionKey;

/**
 * A menu item that executes an {@link Action} when clicked.
 * @author Timmos
 */
public final class ActionMenuItem implements MenuItem {

    private final ActionKey id;

    /**
     * Creates a new action menu item.
     * @param id the ID that identifies the {@link Action} to execute. (it is up to the installed
     */
    public ActionMenuItem(ActionKey id) {
        this.id = id;
    }

    /**
     * Gets the ID that identifies an {@link Action} in an {@link ActionFactory}.
     * @return an action ID
     */
    public ActionKey getActionId() {
        return id;
    }

    @Override
    public void accept(MenuVisitor visitor) {
        visitor.visit(this);
    }
}
