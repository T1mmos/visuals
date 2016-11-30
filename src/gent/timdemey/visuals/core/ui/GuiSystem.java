package gent.timdemey.visuals.core.ui;

import java.util.Set;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.toolbar.WebToolBar;

/**
 * Provides an access point to different GUI interactions.
 * @author Timmos
 */
public interface GuiSystem {

    /**
     * Starts the GUI system on the Event Dispatching Thread.
     */
    public void start();

    /**
     * Sets the title of the main frame.
     * @param locKey the localization key
     */
    public void setMainFrameTitle(String title);

    public void showNotification(String msg, MessageType type);

    /**
     * Installs the drop plugin.
     * @param handler the subsystem deciding whether incoming data can be accepted, and handles that data when actually
     * dropped
     * @param animation the animation that plays in the application window while a user is dragging data onto the
     * application
     */
    public void setDropPlugin (DropHandler handler, DropAnimation animation);

    /**
     * Sets the icon of the main frame.
     * @param iconKey the icon key
     */
    public void setMainFrameIcon(IconKey iconKey);

    /**
     * Sets the menu bar on the root frame.
     * @param menubar the menu bar
     */
    public void setMenuBar(WebMenuBar menubar);

    /**
     * Sets the toolbar.
     * @param toolbar the toolbar
     */
    public void setToolBar(WebToolBar toolbar);

    /**
     * Sets the root frame's content panel.
     * @param content the content panel
     */
    public void setContentPane(WebPanel content);

    /**
     * Sets the root frame's status bar, which will always stick to the
     * bottom of the frame.
     * @param content the content of the status bar.
     */
    public void setStatusBar(WebStatusBar content);

    /**
     * Creates an information dialog with the given message and default title. The dialog will have a single OK button.
     * @param msg the message to show
     * @return the dialog
     */
    public ChoiceDialog<Void> createMessage(String msg);

    /**
     * Creates a dialog with the given message and uses the title and icon
     * of the given message type. The dialog will have a single OK button.
     * @param msg the message to show
     * @param type customizes the dialog with a message type (information, error, ...)
     * @return the dialog
     */
    public ChoiceDialog<Void> createMessage(String msg, MessageType type);

    /**
     * Creates a dialog with the given message, title and icon.
     * The dialog will have a single OK button.
     * @param msg the message to show
     * @param title the dialog title
     * @param iconKey the icon shown in the dialog's body
     * @return the dialog
     */
    public ChoiceDialog<Void> createMessage(String msg, String title, IconKey iconKey);

    /**
     * Creates a question dialog with the given question, dialog title and default Yes/No buttons.
     * @param question the question to ask
     * @param title the dialog title
     * @return the dialog
     */
    public ChoiceDialog<Void> createYesNoQuestion(String question, String title);

    /**
     * Creates a question dialog with the given question, dialog title and default Yes/No buttons.
     * @param question the question to ask
     * @param title the dialog title
     * @return the dialog
     */
    public ChoiceDialog<Void> createYesNoQuestion(String question, String title, IconKey iconKey);

    /**
     * Creates a question dialog with the given question, dialog title and default Yes/No buttons.
     * @param question the question to ask
     * @param title the dialog title
     * @return the dialog
     */
    public ChoiceDialog<Void> createYesNoCancelQuestion(String question, String title);

    /**
     * Creates a question dialog with the given question, dialog title and dialog content icon.
     * @param question the question to ask
     * @param title the dialog title
     * @param iconKey the key of the icon used in the dialog content
     * @param buttons the available buttons to close the dialog
     * @return the dialog
     */
    public ChoiceDialog<Void> createCustomQuestion(String question, String title, IconKey iconKey,
                    Set<? extends DialogButton> buttons);

    /**
     * Creates a global question dialog with the given question, dialog title and an input field, in combination
     * with an OK and Cancel button.
     * @param question the question to ask
     * @param title the dialog title
     * @return the dialog
     */
    public ChoiceDialog<String> createInputQuestion(String question, String title);

    /**
     * Creates a global question dialog with the given question, dialog title and an input field, in combination
     * with an OK and Cancel button.
     * @param question the question to ask
     * @param title the dialog title
     * @param iconKey the icon shown in the dialog's body
     * @return the dialog
     */
    public ChoiceDialog<String> createInputQuestion(String question, String title, IconKey iconKey);

    /**
     * Creates a global dialog with custom content and close buttons.
     * @param <T> the type of the backing data
     * @param input the custom content to show in the dialog
     * @param title the dialog title
     * @param iconKey the icon shown in the dialog's body
     * @param buttons the available buttons to close the dialog
     * @return the dialog
     */
    public <T> ChoiceDialog<T> createCustomDialog(DialogInput<T> input, String title, IconKey iconKey,
                    Set<? extends DialogButton> buttons);
}
