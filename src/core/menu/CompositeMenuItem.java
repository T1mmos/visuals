package core.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * A menu item holding other menu items (a submenu).
 * @author Timmos
 */
public final class CompositeMenuItem implements MenuItem {

    private final String         title;
    private final List<MenuItem> children;

    /**
     * Creates a composite menu item.
     * @param title the name to appear in the GUI.
     */
    public CompositeMenuItem(String title) {
        this.title = title;
        this.children = new ArrayList<MenuItem>();
    }

    /**
     * Adds a menu item to this composite menu item.
     * @param item the menu item to add
     * @return {@code this}
     */
    public CompositeMenuItem add(MenuItem item) {
        children.add(item);
        return this;
    }

    /**
     * Gets the list of added menu items.
     * @return a list of menu items
     */
    public List<MenuItem> getItems() {
        return children;
    }

    /**
     * Gets the title of this composite menu item.
     * @return this menu's title
     */
    public String getTitle() {
        return title;
    }

    @Override
    public void accept(MenuVisitor visitor) {
        visitor.visit(this);
    }
}
