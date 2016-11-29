package demo.ui;

import java.awt.Font;

import javax.swing.Action;
import net.miginfocom.swing.MigLayout;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.tabbedpane.WebTabbedPane;

import core.action.ActionFactory;
import core.action.DefaultActionLocation;
import core.kernel.DefaultInitBuilder;
import core.kernel.InitLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.ui.DefaultButton;
import core.ui.DefaultMessageType;
import core.ui.DialogButton;
import core.ui.IconKey;
import core.util.Gui;
import demo.action.DemoActionKey;
import demo.action.ShowButtonDialog.ShowButtonParam;
import demo.loc.DemoLocKey;

public class DemoContentPanelLoader implements InitLoader {

    @Override
    public void load(DefaultInitBuilder builder) throws LoadingFailedException {
        WebPanel panel = new WebPanel(new MigLayout("insets 0"));

        addDropTitle(panel);
        addSeparator(panel);
        addTabs(panel);

        Gui.getGuiSystem().setContentPane(panel);
    }

    private static void addDropTitle(WebPanel panel) {
        WebLabel label = new WebLabel(DemoLocKey.DROP_HERE.get());
        label.setFont(Font.decode("Arial bold 20"));
        panel.add(label, "push, center, wrap");
    }

    private static void addSeparator(WebPanel panel) {
        panel.add(new WebSeparator(), "pushx, growx, wrap");
    }

    private static void addTabs(WebPanel panel) {
        WebTabbedPane tabs = new WebTabbedPane();
        addNotificationPanel(tabs);
        addDialogsPanel(tabs);
        addCustomButtonsPanel(tabs);
        panel.add(tabs, "pushx, grow");
    }

    private static void addNotificationPanel(WebTabbedPane tabs) {
        WebPanel panel = new WebPanel(new MigLayout(""));

        panel.add(new WebLabel(DemoLocKey.NOTIFICATIONS_EXPLANATION.get()), "span, growx, wrap");

        DefaultMessageType[] types = DefaultMessageType.values();
        for (int i = 0; i < types.length; i++) {
            DefaultMessageType type = types[i];
            Action action = Gui.getActionFactory().getAction(DemoActionKey.SHOW_NOTIFICATION, type,
                            DefaultActionLocation.BUTTON);
            WebButton notifBut1 = new WebButton(action);
            panel.add(notifBut1, "sg buts");
        }

        tabs.addTab(DemoLocKey.NOTIFICATION_TAB.get(), panel);
    }

    private static void addDialogsPanel(WebTabbedPane tabs) {
        WebPanel panel = new WebPanel(new MigLayout(""));
        ActionFactory fact = Gui.getActionFactory();

        Action action = fact.getAction(DemoActionKey.SHOW_ASYNC_DIALOG, DefaultActionLocation.BUTTON_TINY);
        WebButton button1 = new WebButton(action);
        Action read = fact.getAction(DemoActionKey.READ_DOC, "ShowAsync", DefaultActionLocation.BUTTON_TINY);
        WebButton button2 = new WebButton(read);

        panel.add(button1, "wrap");
        panel.add(button2, "");


        tabs.addTab(DemoLocKey.DIALOGS_TAB.get(), panel);
    }

    private static void addCustomButtonsPanel(WebTabbedPane tabs) {
        WebPanel panel = new WebPanel(new MigLayout(""));

        {
            DialogButton [] choices = new DialogButton[] {
                            DemoButton.NORTH, DemoButton.EAST, DemoButton.SOUTH, DemoButton.WEST
            };

            String msg = DemoLocKey.DIALOG_BUTTONS_MSG.get();
            String title = DemoLocKey.DIALOG_BUTTONS_TITLE.get();
            String tooltip = DemoLocKey.DIALOG_BUTTONS_TOOLTIP.get();

            addCustomButton(panel, choices, msg, title, tooltip, DemoIconKey.WIND);
        }

        {
            DialogButton [] choices = new DialogButton[] {
                            DefaultButton.OK, DefaultButton.CANCEL, DemoButton.RANDOM
            };

            String msg = DemoLocKey.DIALOG_BUTTONS_MSG2.get(DemoButton.RANDOM.getLocKey().get());
            String title = DemoLocKey.DIALOG_BUTTONS_TITLE2.get();
            String tooltip = DemoLocKey.DIALOG_BUTTONS_TOOLTIP2.get();

            addCustomButton(panel, choices, msg, title, tooltip, DemoIconKey.MULTIPLE_CHOICE);
        }

        tabs.addTab(DemoLocKey.BUTTONS_TAB.get(), panel);
    }

    private static void addCustomButton(WebPanel panel, DialogButton[] choices, String msg, String title, String tooltip, IconKey key) {
        ActionFactory fact = Gui.getActionFactory();

        ShowButtonParam p = new ShowButtonParam(title, tooltip, msg, key, choices);
        Action action = fact.getAction(DemoActionKey.SHOW_BUTTON_DIALOG, p, DefaultActionLocation.BUTTON);
        WebButton button = new WebButton(action);

        panel.add(button, "");
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.SMALL;
    }

    @Override
    public String getInternalName() {
        return "Demo Content Panel";
    }
}
