package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.ui.IconKey;
import core.util.Gui;
import demo.loc.DemoLocKey;

public class ShowAnimalAction implements GuiAction {



    public static class AnimalParam {
        private final String name;
        private final IconKey key;

        public AnimalParam (String name, IconKey key) {
            this.name = name;
            this.key = key;
        }
    }

    private final AnimalParam param;

    public ShowAnimalAction(AnimalParam param) {
        this.param = param;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = DemoLocKey.SHOW_ANIMAL_MSG.get(param.name);
        Gui.getGuiSystem().createMessage(msg, "Animal click", param.key).showAsync();
    }

    @Override
    public String getTitle() {
        return DemoLocKey.SHOW_ANIMAL.get(param.name);
    }

    @Override
    public IconKey getIconKey() {
        return param.key;
    }

    @Override
    public KeyStroke getAccelerator() {
        return null;
    }

    @Override
    public String getTooltip() {
        return DemoLocKey.TOOLTIP_SHOW_ANIMAL.get(param.name);
    }

}
