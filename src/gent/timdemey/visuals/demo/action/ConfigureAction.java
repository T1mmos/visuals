package gent.timdemey.visuals.demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import gent.timdemey.visuals.core.action.GuiAction;
import gent.timdemey.visuals.core.loc.LanguageKey;
import gent.timdemey.visuals.core.log.LogLevel;
import gent.timdemey.visuals.core.ui.ChoiceDialog;
import gent.timdemey.visuals.core.ui.DefaultButton;
import gent.timdemey.visuals.core.ui.DialogInput;
import gent.timdemey.visuals.core.ui.DialogOutput;
import gent.timdemey.visuals.core.ui.GuiSystem;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.demo.cmd.StorePrefsCommand;
import gent.timdemey.visuals.demo.loc.DemoLocKey;
import gent.timdemey.visuals.demo.ui.DemoIconKey;

public class ConfigureAction implements GuiAction {

    public static class ConfigParam {

        public final LanguageKey lang;
        public final LogLevel    loglvl;

        public ConfigParam(LanguageKey key, LogLevel loglvl) {
            this.lang = key;
            this.loglvl = loglvl;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GuiSystem gs = Gui.getGuiSystem();

        DialogInput<ConfigParam> input = new ConfigureDialogInput();
        String title = DemoLocKey.DIALOG_CONFIGURE_TITLE.get();

        ChoiceDialog<ConfigParam> dialog = gs.createCustomDialog(input, title, DemoIconKey.SETTINGS,
                        DefaultButton.SET_OK_CANCEL);
        DialogOutput<ConfigParam> output = dialog.showEDT();

        if (output.getChoice() == DefaultButton.CANCEL) {
            return;
        }

        ConfigParam param = output.getInput();
        new StorePrefsCommand(param).post();
    }

    @Override
    public String getTitle() {
        return DemoLocKey.STATUSBAR_SETTINGS.get();
    }

    @Override
    public IconKey getIconKey() {
        return DemoIconKey.SETTINGS;
    }

    @Override
    public KeyStroke getAccelerator() {
        return KeyStroke.getKeyStroke("F5");
    }

    @Override
    public String getTooltip() {
        return DemoLocKey.CONFIGURE_TOOLTIP.get();
    }
}
