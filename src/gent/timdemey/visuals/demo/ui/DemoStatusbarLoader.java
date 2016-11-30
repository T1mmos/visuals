package gent.timdemey.visuals.demo.ui;

import javax.swing.Action;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;

import gent.timdemey.visuals.core.action.DefaultActionLocation;
import gent.timdemey.visuals.core.kernel.DefaultInitBuilder;
import gent.timdemey.visuals.core.kernel.InitLoader;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.ui.ImgSize;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.action.DemoActionKey;
import gent.timdemey.visuals.demo.loc.DemoLocKey;

public class DemoStatusbarLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        WebStatusBar statusBar = new WebStatusBar();

        statusBar.add(new WebLabel(DemoLocKey.STATUSBAR_INFO.get()));
        statusBar.addSeparatorToEnd();

        Action cfgAction = Gui.getActionFactory().getAction(DemoActionKey.SETTINGS, DefaultActionLocation.BUTTON_TINY,
                        ImgSize.MENU_ITEM);
        WebButton cfgBut = new WebButton(cfgAction);
        cfgBut.setMargin(2);
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
