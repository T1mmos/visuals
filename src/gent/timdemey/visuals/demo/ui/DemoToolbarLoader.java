package gent.timdemey.visuals.demo.ui;

import javax.swing.Action;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.toolbar.WebToolBar;

import gent.timdemey.visuals.core.action.DefaultActionLocation;
import gent.timdemey.visuals.core.kernel.DefaultInitBuilder;
import gent.timdemey.visuals.core.kernel.InitLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.action.DemoActionKey;
import gent.timdemey.visuals.demo.action.ShowAnimalAction.AnimalParam;
import gent.timdemey.visuals.demo.loc.DemoLocKey;
import net.miginfocom.swing.MigLayout;

public class DemoToolbarLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        final WebToolBar bar = new WebToolBar();

        bar.setLayout(new MigLayout("insets 0, gap 0"));

        addButton(bar, DemoLocKey.ANIMAL_MONKEY, DemoIconKey.MONKEY);
        addButton(bar, DemoLocKey.ANIMAL_SHEEP, DemoIconKey.SHEEP);
        addButton(bar, DemoLocKey.ANIMAL_FLY, DemoIconKey.FLY);
        addButton(bar, DemoLocKey.ANIMAL_BUMBLEBEE, DemoIconKey.BUMBLEBEE);

        bar.add(new WebSeparator(WebSeparator.VERTICAL), "pushy, growy, gapleft 5, gapright 5");
        bar.add(new WebComboBox(new String[] { "foo", "bar" }), "");
        bar.add(new WebSeparator(WebSeparator.VERTICAL), "pushy, growy, gapleft 5, gapright 5");

        bar.setFloatable(false);
        Gui.getGuiSystem().setToolBar(bar);
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Toolbar";
    }

    private static void addButton(WebToolBar bar, DemoLocKey locKey, IconKey iconKey) {
        AnimalParam param = new AnimalParam(locKey.get(), iconKey);
        Action action = Gui.getActionFactory()
                        .getAction(DemoActionKey.SHOW_ANIMAL, param, DefaultActionLocation.BUTTON);
        WebButton but = new WebButton(action);
        but.setHideActionText(true);
        but.setFocusable(false);

        bar.add(but, "gap 0");
    }
}
