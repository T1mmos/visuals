package demo.ui;

import javax.swing.Action;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.managers.style.StyleId;

import core.action.DefaultActionLocation;
import core.kernel.DefaultInitBuilder;
import core.kernel.InitLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.ui.ImgSize;
import core.util.Gui;
import demo.action.DemoActionKey;
import demo.loc.DemoLocKey;

public class DemoStatusbarLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        WebStatusBar statusBar = new WebStatusBar();

        statusBar.add(new WebLabel(DemoLocKey.STATUSBAR_INFO.get()));
        statusBar.addSeparatorToEnd();

        Action cfgAction = Gui.getActionFactory().getAction(DemoActionKey.SETTINGS, DefaultActionLocation.BUTTON_TINY,
                        ImgSize.MENU_ITEM);
        WebButton cfgBut = new WebButton(StyleId.buttonHover, cfgAction);
        cfgBut.setPadding(2);
        cfgBut.setMargin(null);
        statusBar.addToEnd(cfgBut);

        Gui.getGuiSystem().setStatusBar(statusBar);
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Statusbar";
    }

}
