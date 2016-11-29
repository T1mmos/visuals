package core.ui;

/**
 * Standard implementation of {@link DialogInput} where only {@link #getContent(ChoiceDialog)} is to be implemented by
 * subclasses.
 * @author Timmos
 * @param <T> the type of the data that a dialog's input panel can display and possibly allows to edit
 */
public abstract class AbstractDialogInput<T> implements DialogInput<T> {

    @Override
    public T getInput() {
        return null;
    }

    @Override
    public void onShow() {
        // by default
    }

    @Override
    public void onHide() {
        // by default
    }
}
