package core.menu;

import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.MenuElement;

import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.separator.WebSeparator;

import core.action.ActionKey;
import core.action.DefaultActionLocation;
import core.util.Gui;
/**
 * Visits a hierarchical tree of menu items, and builds a menu bar out of it.
 * @author Timmos
 */
public final class DefaultMenuVisitor implements MenuVisitor {

    private final LinkedList<MenuElement> stack;

    /**
     * Creates a new menu visitor.
     */
    public DefaultMenuVisitor() {
        this.stack = new LinkedList<MenuElement>();
        stack.push(new WebMenuBar());
    }

    @Override
    public void visit(CompositeMenuItem comp) {
        List<MenuItem> items = comp.getItems();
        String name = comp.getTitle();

        WebMenu menu = new WebMenu(name);

        if (stack.size() == 1) {
            ((WebMenuBar) stack.peek()).add(menu);
        } else {
            ((WebMenu) stack.peek()).add(menu);
        }

        stack.push(menu);
        for (MenuItem item : items) {
            item.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visit(ActionMenuItem comp) {
        ActionKey id = comp.getActionId();
        Action action = Gui.getActionFactory().getAction(id, DefaultActionLocation.MENU);
        WebMenuItem item = new WebMenuItem(action);
        ((WebMenu) stack.peek()).add(item);
    }

    @Override
    public void visit(SeparatorMenuItem sep) {
        ((WebMenu) stack.peek()).add(new WebSeparator());
    }

    @Override
    public WebMenuBar getMenuBar() {
        if (stack.size() != 1) {
            throw new IllegalStateException("Menu tree not fully traversed");
        }
        return (WebMenuBar) stack.pop();
    }
}
