package gent.timdemey.visuals.core.menu;

/**
 * A menu item representing a separator.
 * @author Timmos
 */
public final class SeparatorMenuItem implements MenuItem {

    @Override
    public void accept(MenuVisitor visitor) {
        visitor.visit(this);
    }

}
