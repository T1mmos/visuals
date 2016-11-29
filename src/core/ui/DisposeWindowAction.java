package core.ui;

import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.AbstractAction;

import core.util.Loc;

/**
 * Swing action that disposes a {@link ChoiceDialog}, and sets the dialog's exit button before it does.
 * @author Timmos
 */
public class DisposeWindowAction extends AbstractAction {
    
    private final ChoiceDialog<?> dialog;
    private final AtomicReference<DialogButton> choiceHolder;
    private final DialogButton choice;
    
    /**
     * Creates a new dispose action.
     * @param choiceHolder the exit button - an atomic reference as there might be threads different from
     * the EDT that request the exit button from a {@link ChoiceDialog} 
     * @param choice the button tied to this dispose action
     * @param dialog the dialog that must be disposed, and updated of the dialog exit button
     */
    DisposeWindowAction (AtomicReference<DialogButton> choiceHolder, DialogButton choice, ChoiceDialog<?> dialog) {
        super(Loc.get(choice.getLocKey()));
        this.dialog = dialog;
        this.choiceHolder = choiceHolder;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        choiceHolder.set(choice);
        dialog.dispose();
    }
}
