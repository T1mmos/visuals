package gent.timdemey.visuals.core.ui;

import com.alee.laf.panel.WebPanel;


/**
 * Visually customizes a {@link ChoiceDialog} and allows for retrieval of the backing data.
 * <p>A {@code DialogInput}'s primary concern is delivering a panel to a {@code ChoiceDialog}
 * which visualizes the custom content. After a user closes the dialog, a call is made to
 * {@link #getInput()} to retrieve the custom content's backing data, which may well have been
 * altered by the user - e.g. by typing some text in a textfield inside the custom panel.
 * The data will then be returned to the code that requested the customized dialog to show up,
 * in the form of a {@link DialogOutput} (which provides the calling code with both the button
 * used to close the dialog, and the backing data). To show dialogs, use the various methods in
 * {@link GuiSystem} - most basic use cases are provided.
 * <p>For a basic implementation example, see {@link TextDialogInput}; it creates a custom
 * panel with a label and a textfield. The textfield's backing data is, unsurprisingly, a
 * {@code String}.
 *
 * @param <T> the type of the data that a dialog's input panel can display and possibly allows to edit
 * @author Timmos
 */
public interface DialogInput<T> {

    /**
     * Gets the panel to show in the dialog. This plugin is provided with a reference to
     * the dialog it is embedded in, to change the dialog's properties dynamically.
     * @param dialog the dialog in which this plugin is embedded
     * @return a panel with the custom content
     */
    public WebPanel getContent (ChoiceDialog<T> dialog);

    /**
     * Allows the implementation to execute some actions just before the dialog is shown. For example, a listener could
     * be registered on some model.
     */
    public void onShow();

    /**
     * Allows the implementation to execute some actions right after before the dialog was closed. For example, a
     * listener could be detached from a model.
     */
    public void onHide();

    /**
     * Extracts the panel's input and bundles it in an object.
     * @return a snapshot of the current input data
     */
    public T getInput ();
}
