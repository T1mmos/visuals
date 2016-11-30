package gent.timdemey.visuals.demo.loading;

import gent.timdemey.visuals.core.action.DefaultActionKey;
import gent.timdemey.visuals.core.kernel.DefaultInitBuilder;
import gent.timdemey.visuals.core.kernel.InitLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.menu.ActionMenuItem;
import gent.timdemey.visuals.core.menu.CompositeMenuItem;
import gent.timdemey.visuals.core.menu.DefaultMenuVisitor;
import gent.timdemey.visuals.core.menu.MenuVisitor;
import gent.timdemey.visuals.core.menu.SeparatorMenuItem;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.action.DemoActionKey;
import gent.timdemey.visuals.demo.loc.DemoLocKey;

/** Attaches a menubar to the application. */
public class DemoMenuLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        CompositeMenuItem miFile = new CompositeMenuItem(DemoLocKey.MENU_ITEM_MENU1.get());
        miFile.add(new ActionMenuItem(DemoActionKey.ENTER_NAME));
        miFile.add(new SeparatorMenuItem());
        miFile.add(new ActionMenuItem(DefaultActionKey.EXIT_APPLICATION));

        CompositeMenuItem miEdit = new CompositeMenuItem(DemoLocKey.MENU_ITEM_MENU2.get());
        miEdit.add(new ActionMenuItem(DemoActionKey.SETTINGS));

        MenuVisitor visitor = new DefaultMenuVisitor();
        visitor.visit(miFile);
        visitor.visit(miEdit);
        Gui.getGuiSystem().setMenuBar(visitor.getMenuBar());
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Demo Menu Loader";
    }
}
