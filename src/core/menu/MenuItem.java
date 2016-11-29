package core.menu;

/**
 * Represents a node in the menu item tree. For example, an action menu item represents
 * a menu item that executes an action when clicked; a separator menu item represents a
 * separator in a menu; a composite menu item represents a submenu (holding other menu
 * items), ...
 * <p>
 * The purpose of menu items is making abstraction of the actual actions behind the menus. This enables to fully
 * separate the order and presentation of menu items from the actual actions behind them - improving the pluggability of
 * the menu system.
 * @author Timmos
 */
public interface MenuItem {

    /**
     * Accepts the given visitor. An implementation should look like this:
     * 
     * <pre>
     * {@code
     * visitor.visit(this);
     * }
     * </pre>
     * @param visitor the menu visitor that is building a menu bar out of the menu item tree
     */
    public void accept(MenuVisitor visitor);
}
