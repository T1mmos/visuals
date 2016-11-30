package gent.timdemey.visuals.core.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;

import gent.timdemey.visuals.core.cmd.ExitApplicationCommand;
import gent.timdemey.visuals.core.util.Gui;
import net.miginfocom.swing.MigLayout;

/**
 * Base plugin's implementation of {@link GuiSystem}.
 * @author Timmos
 */
public class DefaultGuiSystem implements GuiSystem {

    private final WebFrame frame;

    private volatile WebPanel     contentPanel = null;
    private volatile WebToolBar   toolBar      = null;
    private volatile WebStatusBar statusBar    = null;

    /**
     * Creates a new GUI system.
     */
    public DefaultGuiSystem() {
        this.frame = new WebFrame();
    }

    @Override
    public void setMainFrameTitle(final String title) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setTitle(title);
            }
        });
    }

    @Override
    public void setMainFrameIcon(IconKey iconKey) {
        Drawable drawable = Gui.getIconFactory().getDrawable(iconKey);
        final List<Image> icons = new ArrayList<>();
        for (ImgSize size : ImgSize.values()) {
            icons.add(drawable.getImage(size));
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setIconImages(icons);
            }
        });
    }

    @Override
    public void setMenuBar(final WebMenuBar menubar) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setJMenuBar(menubar);
            }
        });
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {


                frame.addWindowListener(new FrameListener());
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                frame.pack();

                frame.setVisible(true);
                frame.toFront();
                frame.pack();

                int minW = Math.max(600, frame.getSize().width);
                int minH = Math.max(400, frame.getSize().height);
                frame.setMinimumSize(new Dimension(minW, minH));
                frame.setLocationRelativeTo(null);
            }
        });
    }

    @Override
    public void setDropPlugin(final DropHandler handler, final DropAnimation animation) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                InternalDropHelper.install(frame, animation, handler);
            }
        });
    }

    private class FrameListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            new ExitApplicationCommand().post();
        }
    }

    @Override
    public void setContentPane(WebPanel content) {
        this.contentPanel = content;
        layoutFrame();
    }

    @Override
    public void setToolBar(WebToolBar toolbar) {
        this.toolBar = toolbar;
        layoutFrame();
    }

    @Override
    public void setStatusBar(WebStatusBar content) {
        this.statusBar = content;
        layoutFrame();
    }

    private void layoutFrame() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                WebPanel all = new WebPanel(new MigLayout("insets 5"));

                if (toolBar != null) {
                    all.add(toolBar, "dock north");
                }

                if (contentPanel != null) {
                    all.add(contentPanel, "dock center");
                }

                if (statusBar != null) {
                    all.add(statusBar, "dock south");
                }

                frame.setContentPane(all);
                frame.validate();
            }
        });
    }

    @Override
    public DefaultChoiceDialog<Void> createMessage(String msg) {
        return createMessage(msg, DefaultMessageType.INFO);
    }

    @Override
    public DefaultChoiceDialog<Void> createMessage(String msg, MessageType type) {
        return createMessage(msg, msg, DefaultMessageType.INFO.getIconKey());
    }

    @Override
    public DefaultChoiceDialog<Void> createMessage(String msg, String title, IconKey iconKey) {
        DialogInput<Void> input = new StaticDialogInput(msg);
        return showCustomDialogPriv(input, title, iconKey, DefaultButton.SET_OK);
    }

    @Override
    public DefaultChoiceDialog<Void> createYesNoQuestion(String question, String title) {
        return createYesNoQuestion(question, title, DefaultMessageType.INFO.getIconKey());
    }

    @Override
    public DefaultChoiceDialog<Void> createYesNoQuestion(String question, String title, IconKey iconKey) {
        DialogInput<Void> input = new StaticDialogInput(question);
        return showCustomDialogPriv(input, title, iconKey, DefaultButton.SET_YES_NO);
    }

    @Override
    public DefaultChoiceDialog<Void> createYesNoCancelQuestion(String question, String title) {
        DialogInput<Void> input = new StaticDialogInput(question);
        return showCustomDialogPriv(input, title, DefaultMessageType.INFO.getIconKey(), DefaultButton.SET_YES_NO_CANCEL);
    }

    @Override
    public DefaultChoiceDialog<String> createInputQuestion(String question, String title) {
        return createInputQuestion(question, title, DefaultMessageType.INFO.getIconKey());
    }

    @Override
    public DefaultChoiceDialog<String> createInputQuestion(String question, String title, IconKey iconKey) {
        DialogInput<String> content = new TextDialogInput(question);
        return showCustomDialogPriv(content, title, iconKey, EnumSet.of(DefaultButton.OK, DefaultButton.CANCEL));
    }

    @Override
    public ChoiceDialog<Void> createCustomQuestion(String question, String title, IconKey iconKey,
                    Set<? extends DialogButton> buttons) {
        DialogInput<Void> input = new StaticDialogInput(question);
        return showCustomDialogPriv(input, title, iconKey, buttons);
    }

    @Override
    public <T> DefaultChoiceDialog<T> createCustomDialog(DialogInput<T> input, String title, IconKey iconKey,
                    Set<? extends DialogButton> buttons) {
        return showCustomDialogPriv(input, title, iconKey, buttons);
    }

    private <T> DefaultChoiceDialog<T> showCustomDialogPriv (DialogInput<T> input, String title, IconKey iconKey, Set<? extends DialogButton> buttons)  {
        return DefaultChoiceDialog.create(frame, input, title, iconKey, buttons);
    }


    @Override
    public void showNotification(final String msg, final MessageType type) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                WebNotification notif = new WebNotification();
                notif.setDisplayTime(3000);
                notif.setIcon(Gui.getIcon(type.getIconKey(), ImgSize.DIALOG_CONTENT));
                notif.setContent(msg);

                // do not use a "normal" WebNotification with a non-null parent.
                // it fucks up the menus when the notification has disappeared
                // see https://github.com/mgarin/weblaf/issues/403
                NotificationManager.showNotification(frame, notif);
            }
        });
    }
}
