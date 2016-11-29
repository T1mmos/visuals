package demo.loading;

import core.action.DefaultActionKey;
import core.kernel.DefaultInitBuilder;
import core.kernel.InitLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.menu.ActionMenuItem;
import core.menu.CompositeMenuItem;
import core.menu.DefaultMenuVisitor;
import core.menu.MenuVisitor;
import core.menu.SeparatorMenuItem;
import core.util.Gui;
import demo.action.DemoActionKey;
import demo.loc.DemoLocKey;

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
