package gent.timdemey.visuals.core.ui;


/**
 * Represents a customizable, multithreaded environment-friendly dialog.
 * <p>
 * The dialog is always shown on the Event Dispatching Thread. Based on which thread wants to show a dialog and what is
 * expected from it, there are three methods to get a dialog to show up:
 * <ul>
 * <li>{@link #showEDT()}: must be invoked on the EDT, and blocks until the user closes the dialog. When the user closes
 * the dialog, the caller receives feedback on how the dialog was closed.
 * <li>{@link #showAsync()}: can be invoked on any thread and is non-blocking. The calling thread cannot receive any
 * return value. This method is useful for informative messages, or for dialogs of which the content directly
 * manipulates a data model as the invoking thread will not be blocked, and hence cannot take further action after the
 * dialog was closed. For the same reason, it makes no sense to use multiple dispose buttons on a dialog if it is shown
 * by using this method - the caller will never know with which button the dialog has been closed.
 * <li>{@link #showInterruptably()}: must be invoked on a thread different from the EDT, and blocks until the user
 * closes the dialog. When the user closes the dialog, the caller receives feedback on how the dialog was closed.
 * </ul>
 * <p>
 * A dialog can always be closed with the "X" button in the dialog's window, which is always shown, and this button is
 * mapped to {@link DefaultButton#CANCEL}. Code that uses a dialog should be prepared for this result, even if no button
 * was explicitly added for this result.
 * @author Timmos
 * @param <T> the type of the data that a dialog's input panel can display and possibly allows to edit
 */
public interface ChoiceDialog<T> {

    /**
     * Sets the enabled status of a specific dispose button in this dialog.
     * @param choice the button to enable/disable
     * @param enabled whether the button must be enabled
     * @throws IllegalArgumentException when the indicated button is not found
     */
    public void setButtonEnabled (DefaultButton choice, boolean enabled);

    /**
     * Shows the dialog on the EDT. The thread on which this method is invoked should not
     * be the Event Dispatching Thread. The current thread will block until the dialog is disposed.
     * <p>When the current thread is interrupted, the dialog will be disposed, the current thread's
     * <i>interrupted status</i> is cleared and the {@code InterruptedException} is
     * upthrown to notify the calling thread.
     * @return a {@link DialogOutput}, holding the data entered by the user and the button used to close the dialog
     * @throws InterruptedException when the current thread was interrupted while waiting for the user to close the dialog
     * @throws DialogInputException when an exception occured on the EDT while showing the dialog, it will be wrapped
     * in an {@code InvocationTargetException}, which in turn will be wrapped in a {@code DialogInputException} and as
     * such, upthrown.
     */
    public DialogOutput<T> showInterruptably() throws InterruptedException;

    /**
     * Shows the dialog on the EDT. This method must be invoked on the Event Dispatching Thread. This method blocks
     * until
     * the dialog is closed. For showing a non-blocking dialog, use {@link #showAsync()}.
     * @return a {@link DialogOutput}, holding the data entered by the user and the button used to close the dialog
     * @throws DialogInputException when a runtime exception was thrown while showing the dialog, it will be wrapped in
     * a {@code DialogInputException} and as such, upthrown
     */
    public DialogOutput<T> showEDT();

    /**
     * Shows the dialog on the EDT, thereby not blocking the calling thread. If the calling thread
     * has to wait for a result, use {@link #showInterruptably()}.
     * <p>This method can be invoked on any thread.
     */
    public void showAsync () ;

    /**
     * Disposes the dialog on the EDT.
     */
    public void dispose();
}
