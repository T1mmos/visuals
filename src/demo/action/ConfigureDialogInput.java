package demo.action;

import java.awt.Component;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JList;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.combobox.WebComboBoxRenderer;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebListCellRenderer;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextArea;

import core.cfg.DefaultConfigKey;
import core.loc.LanguageKey;
import core.log.LogLevel;
import core.ui.ChoiceDialog;
import core.ui.DialogInput;
import core.ui.ImgSize;
import core.ui.UISkin;
import core.util.App;
import core.util.Gui;
import core.util.Various;
import demo.action.ConfigureAction.ConfigParam;
import demo.loc.DemoLocKey;

public class ConfigureDialogInput implements DialogInput<ConfigParam> {

    private final WebComboBox langbox;
    private final WebComboBox logbox;
    private final WebComboBox uibox;

    public ConfigureDialogInput() {
        List<LanguageKey> langKeys = App.get().getLocalizator().getSupportedLanguages();
        this.langbox = new WebComboBox(langKeys);

        LogLevel[] loglvls = LogLevel.values();
        this.logbox = new WebComboBox(loglvls);

        this.uibox = new WebComboBox(Gui.get().getSkinFactory().getSupportedSkins());
    }

    @Override
    public WebPanel getContent(ChoiceDialog<ConfigParam> dialog) {
        WebPanel panel = new WebPanel(new MigLayout("insets 0", "[20][]20[]", "[][][][]20[]"));


        WebTextArea txtLangExpl = Various.createMultiLine(DemoLocKey.CONFIG_LANG_EXPLANATION.get());
        panel.add(txtLangExpl, "span, growx, wrap");

        WebLabel labChsLang = new WebLabel(DemoLocKey.CONFIG_CHOOSE_LANGUAGE.get());
        panel.add(labChsLang, "skip, right");
        panel.add(langbox, "pushx, sg comp, wrap");

        WebLabel labChsLog = new WebLabel(DemoLocKey.CONFIG_CHOOSE_LOGLEVEL.get());
        panel.add(labChsLog, "skip, right");
        panel.add(logbox, "pushx, sg comp, wrap");

        WebLabel labChsSkin = new WebLabel(DemoLocKey.CONFIG_CHOOSE_UISKIN.get());
        panel.add(labChsSkin, "skip, right");
        panel.add(uibox, "pushx, sg comp, wrap");

        WebLabel labRestReq = new WebLabel(DemoLocKey.CONFIG_RESTART_REQUIRED.get());
        panel.add(labRestReq, "span");

        return panel;
    }

    @Override
    public void onShow() {
        langbox.setSelectedItem(DefaultConfigKey.LANGUAGE.get());
        logbox.setSelectedItem(DefaultConfigKey.LOG_LEVEL.get());
        uibox.setSelectedItem(DefaultConfigKey.GUI_SKIN.get());

        langbox.setRenderer(new LanguageRenderer());
        uibox.setRenderer(new SkinRenderer());
    }

    private static class LanguageRenderer extends WebComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                        boolean cellHasFocus) {
            LanguageKey key = (LanguageKey) value;
            WebListCellRenderer renderer = (WebListCellRenderer) super.getListCellRendererComponent(list,
                            key.getName(), index,
                            isSelected,
                            cellHasFocus);

            Icon icon = Gui.getIcon(key.getIconKey(), ImgSize.MENU_ITEM);
            renderer.setIcon(icon);
            return renderer;
        }
    }

    private static class SkinRenderer extends WebComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                        boolean cellHasFocus) {
            UISkin key = (UISkin) value;
            WebListCellRenderer renderer = (WebListCellRenderer) super.getListCellRendererComponent(list,
                            key.getName(), index,
                            isSelected, cellHasFocus);

            return renderer;
        }
    }

    @Override
    public void onHide() {
        // do nothing
    }

    @Override
    public ConfigParam getInput() {
        LanguageKey key = (LanguageKey) langbox.getSelectedItem();
        LogLevel loglvl = (LogLevel) logbox.getSelectedItem();
        UISkin skin = (UISkin) uibox.getSelectedItem();
        ConfigParam param = new ConfigParam(key, loglvl, skin);
        return param;
    }
}
