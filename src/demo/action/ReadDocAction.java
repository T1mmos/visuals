package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.loc.DefaultLocKey;
import core.ui.IconKey;
import core.util.Gui;
import demo.loc.DemoLocKey;
import demo.ui.DemoIconKey;

class ReadDocAction implements GuiAction {

    private final String doc;

    ReadDocAction(String doc) {
        this.doc = doc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Gui.getGuiSystem().createMessage(DefaultLocKey.X_NOT_IMPLEMENTED.get()).showAsync();
    }

    @Override
    public String getTitle() {
        return DemoLocKey.READ_DOC.get(doc);
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public IconKey getIconKey() {
        return DemoIconKey.DOCUMENT;
    }

    @Override
    public KeyStroke getAccelerator() {
        return null;
    }
}
