package gent.timdemey.visuals.demo.action;

import java.awt.Component;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebListCellRenderer;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextArea;

import gent.timdemey.visuals.core.cfg.DefaultConfigKey;
import gent.timdemey.visuals.core.loc.LanguageKey;
import gent.timdemey.visuals.core.log.LogLevel;
import gent.timdemey.visuals.core.ui.ChoiceDialog;
import gent.timdemey.visuals.core.ui.DialogInput;
import gent.timdemey.visuals.core.ui.ImgSize;
import gent.timdemey.visuals.core.util.App;
import gent.timdemey.visuals.core.util.Gui;
import gent.timdemey.visuals.core.util.Various;
import gent.timdemey.visuals.demo.action.ConfigureAction.ConfigParam;
import gent.timdemey.visuals.demo.loc.DemoLocKey;
import net.miginfocom.swing.MigLayout;

public class ConfigureDialogInput implements DialogInput<ConfigParam> {

    private final WebComboBox langbox;
    private final WebComboBox logbox;
    public ConfigureDialogInput() {
        List<LanguageKey> langKeys = App.get().getLocalizator().getSupportedLanguages();
        this.langbox = new WebComboBox(langKeys.toArray());

        LogLevel[] loglvls = LogLevel.values();
        this.logbox = new WebComboBox(loglvls);
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

        WebLabel labRestReq = new WebLabel(DemoLocKey.CONFIG_RESTART_REQUIRED.get());
        panel.add(labRestReq, "span");

        return panel;
    }

    @Override
    public void onShow() {
        langbox.setSelectedItem(DefaultConfigKey.LANGUAGE.get());
        logbox.setSelectedItem(DefaultConfigKey.LOG_LEVEL.get());

        langbox.setRenderer(new LanguageRenderer());
    }

    private static class LanguageRenderer extends BasicComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                        boolean cellHasFocus) {
            LanguageKey key = (LanguageKey) value;
            BasicComboBoxRenderer renderer = (BasicComboBoxRenderer) super.getListCellRendererComponent(list,
                            key.getName(), index,
                            isSelected,
                            cellHasFocus);

            Icon icon = Gui.getIcon(key.getIconKey(), ImgSize.MENU_ITEM);
            renderer.setIcon(icon);
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
        ConfigParam param = new ConfigParam(key, loglvl);
        return param;
    }
}
