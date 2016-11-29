package demo.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import core.action.GuiAction;
import core.loc.LanguageKey;
import core.log.LogLevel;
import core.ui.ChoiceDialog;
import core.ui.DefaultButton;
import core.ui.DialogInput;
import core.ui.DialogOutput;
import core.ui.GuiSystem;
import core.ui.IconKey;
import core.ui.UISkin;
import core.util.Gui;
import demo.cmd.StorePrefsCommand;
import demo.loc.DemoLocKey;
import demo.ui.DemoIconKey;

public class ConfigureAction implements GuiAction {

    public static class ConfigParam {

        public final LanguageKey lang;
        public final LogLevel    loglvl;
        public final UISkin      uiskin;

        public ConfigParam(LanguageKey key, LogLevel loglvl, UISkin skin) {
            this.lang = key;
            this.loglvl = loglvl;
            this.uiskin = skin;
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
