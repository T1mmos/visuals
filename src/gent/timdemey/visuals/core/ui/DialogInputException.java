package gent.timdemey.visuals.core.ui;

import java.lang.reflect.InvocationTargetException;

/**
 * An uncaught exception that indicates that an unchecked exception was thrown on the EDT while showing the dialog.
 * This exception indicates a programming error in a {@link DialogInput} implementation.
 * <p>A choice dialog is always shown on the EDT. When an uncaught exception occurs, it is
 * wrapped into the checked exception {@link InvocationTargetException} and as such reported to the calling thread.
 * Due to the fact that this happens only when an uncaught exception was thrown, we can rewrap 
 * the {@code InvocationTargetException} in a {@code ChoiceDialogException}.
 * @author Timmos
 *
 */
public final class DialogInputException extends RuntimeException {
    /**
     * Creates a new {@code DialogInputException}, wrapping around the
     * given {@code InvocationTargetException}.
     * @param e the wrapped exception
     */
    DialogInputException (InvocationTargetException e) {
        super (e);
    }
    
    /**
     * Creates a new {@code DialogInputException}, wrapping around the
     * given {@code RuntimeException}.
     * @param e the wrapped exception
     */
    DialogInputException (RuntimeException e) {
        super (e);
    }
}
