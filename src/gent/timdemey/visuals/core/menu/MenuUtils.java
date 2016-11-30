package gent.timdemey.visuals.core.menu;

import javax.swing.Action;

import gent.timdemey.visuals.core.action.ActionFactory;
import gent.timdemey.visuals.core.action.ActionKey;
import gent.timdemey.visuals.core.loc.LocKey;
import gent.timdemey.visuals.core.loc.Localizator;
import gent.timdemey.visuals.core.util.App;

/**
 * Bundles some menu item tree building utilities. These utilities are to
 * be used during application load.
 * @author Timmos
 */
public final class MenuUtils {

    private MenuUtils() {
    }

    /**
     * Creates a new {@link CompositeMenuItem} with the title
     * identified by the given localization ID. The actual string
     * is to be defined by the installed localizator.
     * @param id the localization ID
     * @return a new {@link CompositeMenuItem}
     * @see Localizator
     */
    public static CompositeMenuItem comp(LocKey id) {
        String name = App.get().getLocalizator().get(id);
        CompositeMenuItem menu = new CompositeMenuItem(name);

        return menu;
    }

    /**
     * Creates a new {@link SeparatorMenuItem} and attaches it to the given
     * composite menu item. As nothing can be done on the newly created
     * separator item, this method returns nothing.
     * @param menu the composite menu item to add the separator menu item to
     */
    public static void sep(CompositeMenuItem menu) {
        menu.add(new SeparatorMenuItem());
    }

    /**
     * Creates a new {@link ActionMenuItem} with an {@link Action} identified
     * by the given action ID, and attaches it to the given
     * composite menu item. The actual {@link Action} used is to be defined
     * by the installed action factory.
     * @param menu the composite menu item to add the separator menu item to
     * @param id the action ID
     * @see ActionFactory
     */
    public static void action(CompositeMenuItem menu, ActionKey id) {
        menu.add(new ActionMenuItem(id));
    }
}
