package core.menu;

import com.alee.laf.menu.WebMenuBar;


/**
 * Visits a hierarchical tree of {@link MenuItem menu items}, and builds a menu bar out of it. The
 * system is based on the Visitor Design Pattern.
 * @author Timmos
 */
public interface MenuVisitor {

    /**
     * Visits a composite menu item, which holds other menu items.
     * @param item the composite menu item
     */
    public void visit(CompositeMenuItem item);

    /**
     * Visits an action menu item.
     * @param item the action menu item
     */
    public void visit(ActionMenuItem item);

    /**
     * Visits an separator menu item.
     * @param item the separator menu item
     */
    public void visit(SeparatorMenuItem item);

    /**
     * Gets the menu bar as created by this visitor after the full
     * menu item tree has been visited. This method may throw
     * unchecked exceptions if called when the menu item tree was
     * not traversed yet.
     * @return the menu bar
     */
    public WebMenuBar getMenuBar();
}
