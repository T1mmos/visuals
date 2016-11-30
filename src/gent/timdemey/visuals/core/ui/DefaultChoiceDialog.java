package gent.timdemey.visuals.core.ui;

import java.awt.Dialog.ModalityType;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.separator.WebSeparator;

import gent.timdemey.visuals.core.kernel.DefaultGui;
import net.miginfocom.swing.MigLayout;

/**
 * A thread-safe, customizable dialog.
 * @author Timmos
 *
 * @param <T> the type of the data that a dialog's input panel can display and possibly allows to edit
 */
public class DefaultChoiceDialog<T> implements ChoiceDialog<T> {

    private static void checkEDT(boolean expected) {
        boolean edt = SwingUtilities.isEventDispatchThread();
        if (expected == edt) {
            return;
        }
        String thrstr = edt ? "a thread different from the EDT" : "the EDT";
        throw new IllegalThreadStateException("Expected this method invocation on " + thrstr);
    }

    private final WebFrame                      parent;
    private final DialogInput<T> input;
    private final IconKey iconKey;
    private final WebPanel buttonPanel;
    private final Map<DialogButton, WebButton>  buttons;

    private final AtomicReference<DialogButton> choiceHolder = new AtomicReference<DialogButton>(DefaultButton.CANCEL);
    private final WebDialog                     dialog;

    private DefaultChoiceDialog(
                    final WebFrame parent,
                    final DialogInput<T> input,
                    final String title,
                    IconKey iconKey, final Set<? extends DialogButton> choices) {
        this.parent = parent;
        this.input = input;
        this.iconKey = iconKey;
        this.dialog = new WebDialog(parent, title, ModalityType.DOCUMENT_MODAL);
        this.buttons = new HashMap<>();
        this.buttonPanel = new WebPanel(new MigLayout("center, insets 0"));

        WebButton defButton = null;
        DialogButton defDialogButton = null;

        List<DialogButton> sorted = new ArrayList<>(choices);
        Collections.sort(sorted, DialogButtonComparator.INSTANCE);
        for (DialogButton choice : sorted) {
            DisposeWindowAction action = new DisposeWindowAction(choiceHolder, choice, this);

            WebButton button = new WebButton(action);
            if (defDialogButton == null || choice.getOrder() < defDialogButton.getOrder()) {
                defDialogButton = choice;
                defButton = button;
            }

            buttonPanel.add(button, "wmin 60, sg buts");
            buttons.put(choice, button);
        }
        dialog.getRootPane().setDefaultButton(defButton);
    }

    /**
     * Creates a new {@link DefaultChoiceDialog}.
     * @param <X> the type of the data that a dialog's input panel can display and possibly allows to edit
     * @param parent the dialog's parent window
     * @param input the custom content of the dialog
     * @param title the dialog's title
     * @param iconKey the icon to display
     * @param choices the buttons that a user can use to dispose the dialog
     * @return a {@link DefaultChoiceDialog}
     */
    public static <X> DefaultChoiceDialog<X> create(WebFrame parent, DialogInput<X> input, final String title,
                    IconKey iconKey, final Set<? extends DialogButton> choices) {
        return new DefaultChoiceDialog<>(parent, input, title, iconKey, choices);
    }

    @Override
    public void setButtonEnabled(DefaultButton choice, boolean enabled) {
        if (!buttons.containsKey(choice)) {
            throw new IllegalArgumentException("This dialog has no button of type: " + choice);
        }
        WebButton button = buttons.get(choice);
        SwingUtilities.invokeLater(new DoEnableButton(button, enabled));
    }

    @Override
    public DialogOutput<T> showInterruptably() throws InterruptedException {
        DefaultChoiceDialog.checkEDT(false);
        try {
            SwingUtilities.invokeAndWait(new DoShowPriv());
        } catch (InterruptedException e) {
            SwingUtilities.invokeLater(new DoDisposePriv());
            throw e;
        } catch (InvocationTargetException ex) {
            throw new DialogInputException(ex);
        }

        DialogButton choice = choiceHolder.get();
        T values = input.getInput();
        return new DialogOutput<>(choice, values);
    }

    @Override
    public DialogOutput<T> showEDT() {
        DefaultChoiceDialog.checkEDT(true);
        try {
            new DoShowPriv().run();
        } catch (RuntimeException rte) {
            throw new DialogInputException(rte);
        }

        DialogButton choice = choiceHolder.get();
        T values = input.getInput();
        return new DialogOutput<>(choice, values);
    }

    @Override
    public void showAsync() {
        SwingUtilities.invokeLater(new DoShowPriv());
    }

    @Override
    public void dispose() {
        SwingUtilities.invokeLater(new DoDisposePriv());
    }

    private void showPriv() {
        WebPanel fullContent = new WebPanel(new MigLayout("insets 10 20 0 20", "", "[center]unrel[]unrel[]"));
        if (iconKey != null) {
            WebLabel lab_icon = new WebLabel(DefaultGui.instance().getIconFactory().getDrawable(iconKey)
                            .getIcon(ImgSize.DIALOG_CONTENT));
            fullContent.add(lab_icon, "split 2, gapright 20");
        }
        WebPanel content = input.getContent(this);
        fullContent.add(content, "pushx, growx, wrap");
        fullContent.add(new WebSeparator(), "spanx, pushx, growx, wrap");
        fullContent.add(buttonPanel, "pushx, growx, wrap");

        dialog.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
        dialog.getRootPane().getActionMap().put("ESCAPE", new EscapeKeyAction());
        dialog.setContentPane(fullContent);
        dialog.pack();

        dialog.setResizable(false);
        dialog.setLocationRelativeTo((parent.getExtendedState() & Frame.ICONIFIED) != Frame.ICONIFIED ? parent : null);

        input.onShow();
        dialog.pack(); // second pack to solve clipped buttons
        dialog.setVisible(true);
        input.onHide();
    }

    private final static class DoEnableButton implements Runnable {

        private final WebButton button;
        private final boolean enable;

        private DoEnableButton(WebButton button, boolean enable) {
            this.button = button;
            this.enable = enable;
        }

        @Override
        public void run() {
            button.setEnabled(enable);
        }
    }

    private class DoShowPriv implements Runnable {
        @Override
        public void run() {
            showPriv();
        }
    }

    private class EscapeKeyAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            new DoDisposePriv().run();
        }
    }

    private class DoDisposePriv implements Runnable {
        @Override
        public void run() {
            dialog.dispose();
        }
    }
}
