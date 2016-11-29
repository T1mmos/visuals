package core.ui;


/**
 * Represents the output of an application dialog. An object of this class knows 
 * the button clicked to close the dialog and the data that was entered while the 
 * dialog was open. This input can be of any type and represents 
 * whatever data a user can enter in one dialog.
 * <p>For example, the output of a dialog with one standard textfield should naturally
 * be reflected by a {@code DialogOutput<String>}. If a dialog permits a user to enter
 * more data, e.g. 2 textfields, then we could create a class
 * <pre>
 * <code>
 * public class DoubleStringInput {
 *     private final String input1, input2;
 *     
 *     public DoubleStringInput (String in1, String in2){
 *         input1 = in1;
 *         input2 = in2;
 *     }
 *     public String getInput1 () { return input1; }
 *     public String getInput2 () { return input2; }
 * }
 * </code>
 * </pre>
 * Finally, we could expect such dialog to return a {@code DialogOutput<DoubleStringInput>}.
 * <p>The {@link GuiSystem GUI system} provides methods that take a {@link DialogInput}{@code<T>} parameter and
 * will return a {@code DialogOutput}{@code<T>}.
 * @param <T> the type that bundles the model values of a choice dialog 
 * @author Timmos
 */
public final class DialogOutput<T> {
    private final DialogButton choice;
    private final T input;

    /**
     * Creates a new {@link DialogOutput} with the given button as dialog exit button, and the
     * given model value object.
     * @param choice the button which made the dialog to close
     * @param input the model values of the dialog at the time the dialog closed
     */
    DialogOutput(DialogButton choice, T input) {
        this.choice = choice;
        this.input = input;
    }

    /**
     * Gets the button used to close the dialog.
     * @return the button that made the dialog to close
     */
    public DialogButton getChoice() {
        return choice;
    }

    /**
     * Gets the model values from the closed dialog. The model values
     * represent the GUI state of a dialog.
     * <p>For example, if a dialog shows one {@code JComboBox<String>},
     * then the model values are composed of only one object of the type
     * {@code String}: the value that the user selected. This value is of
     * importance to the code that uses the dialog, and is accessible via
     * a {@code DialogOutput} object.
     * @return the object representing the GUI state of the dialog
     */
    public T getInput() {
        return input;
    }
}
