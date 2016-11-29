package demo.ui;

import javax.swing.Action;
import net.miginfocom.swing.MigLayout;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.managers.style.StyleId;
import core.action.DefaultActionLocation;
import core.kernel.DefaultInitBuilder;
import core.kernel.InitLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.ui.IconKey;
import core.util.Gui;
import demo.action.DemoActionKey;
import demo.action.ShowAnimalAction.AnimalParam;
import demo.loc.DemoLocKey;

public class DemoToolbarLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        final WebToolBar bar = new WebToolBar(StyleId.toolbarAttached);

        bar.setLayout(new MigLayout("insets 0, gap 0"));

        addButton(bar, DemoLocKey.ANIMAL_MONKEY, DemoIconKey.MONKEY);
        addButton(bar, DemoLocKey.ANIMAL_SHEEP, DemoIconKey.SHEEP);
        addButton(bar, DemoLocKey.ANIMAL_FLY, DemoIconKey.FLY);
        addButton(bar, DemoLocKey.ANIMAL_BUMBLEBEE, DemoIconKey.BUMBLEBEE);

        bar.add(new WebSeparator(StyleId.separatorVertical), "pushy, growy, gapleft 5, gapright 5");
        bar.add(new WebComboBox(new String[] { "foo", "bar" }), "");
        bar.add(new WebSeparator(StyleId.separatorVertical), "pushy, growy, gapleft 5, gapright 5");

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
        WebButton but = new WebButton(StyleId.buttonHover, action);
        but.setHideActionText(true);
        but.setFocusable(false);
        but.setPadding(2);

        bar.add(but, "gap 0");
    }
}
