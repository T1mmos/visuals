package demo.loc;

import core.loc.LocKey;
import core.util.Loc;

/**
 * Demo-specific localization keys.
 * @author Timmos
 */
public enum DemoLocKey implements LocKey {

    APP_TITLE("demo.app.title"),
    MENU_ITEM_MENU1("demo.menu.file"),
    MENU_ITEM_MENU2("demo.menu.edit"),

    CONFIG_CHOOSE_LANGUAGE("demo.label.config.chooselang"),
    CONFIG_CHOOSE_LOGLEVEL("demo.label.config.chooseloglvl"),
    CONFIG_CHOOSE_UISKIN("demo.label.config.chooseuiskin"),
    CONFIG_RESTART_REQUIRED("demo.label.config.restart"),
    CONFIG_LANG_EXPLANATION("demo.label.config.expl"),
    CONFIG_LOGGING_EXPLANATION("demo.label.config.logging.expl"),

    /** Enter a name. */
    MENU_ITEM_ENTERNAME("demo.action.enter_name"),
    /** 'Button Choice' dialog content. */
    DIALOG_CONTENT_BUTTONCHOICE("demo.dialog.content.button-choice"),
    /** 'Enter Name' dialog content. */
    DIALOG_CONTENT_ENTERNAME("demo.dialog.content.enter_name"),
    /** 'Enter Name' dialog title. */
    DIALOG_TITLE_ENTERNAME("demo.dialog.title.enter_name"),
    /** Demo example. */
    DEMO_EXAMPLE("demo.example"),
    DROP_HERE ("demo.dnd.drop_file_here"),
    DROP_SUCCESS("demo.dnd.file_dropped"),

    STATUSBAR_SETTINGS("demo.statusbar.settings"),
    STATUSBAR_INFO("demo.statusbar.info"),
    STATUSBAR_SETTINGS_TOOLTIP("demo.statusbar.settings.tooltip"),
    SETTINGS_CHECK1("demo.dialog.settings.check1"),
    SETTINGS_CHECK2("demo.dialog.settings.check2"),
    SETTINGS_CHECK3("demo.dialog.settings.check3"),

    SHOW_ANIMAL("demo.show_animal"),
    SHOW_ANIMAL_MSG("demo.show_animal_msg"),
    ANIMAL_MONKEY("demo.animals.monkey"),
    ANIMAL_FLY("demo.animals.fly"),
    ANIMAL_BUMBLEBEE("demo.animals.bumblebee"),
    ANIMAL_SHEEP("demo.animals.sheep"),

    DIALOG_CONFIGURE_TITLE("demo.dialog.settings.title"),
    CONFIGURE_TOOLTIP("demo.dialog.settings.tooltip"),

    NOTIFICATION_TAB("demo.tabs.notifications.title"),
    DIALOGS_TAB("demo.tabs.dialogs.title"),
    BUTTONS_TAB("demo.tabs.buttons.title"),
    NOTIFICATIONS_EXPLANATION("demo.tabs.notifications.explanation"),

    DROP_NOT_ACCEPTED("demo.drop_not_accepted"),
    TOOLTIP_SHOW_ANIMAL("demo.tooltip.show_animal"),
    TOOLTIP_SHOW_NOTIFICATION("demo.tooltip.show_notification"),
    NOTIFICATION_EXAMPLE("demo.notification.example"),
    TOOLTIP_ENTERNAME("demo.tooltip.entername"),

    DIALOG_ASYNC_RESULT("demo.dialog.async.result"),
    DIALOG_ASYNC_TITLE("demo.dialog.async.title"),
    DIALOG_ASYNC_TOOLTIP("demo.dialog.async.tooltip"),
    DIALOG_ASYNC_INPUT("demo.dialog.async.seconds"),

    NOT_A_NUMBER("demo.not_a_number"),
    NUMBER_BETWEEN_X_AND_Y("demo.number_between"),
    WAITING_FOR("demo.waiting_for"),

    READ_DOC("demo.read_doc"),

    NORTH("demo.north"),
    EAST("demo.east"),
    SOUTH("demo.south"),
    WEST("demo.west"),
    RANDOM("demo.random"),
    NOTIFICATION_BUTTONCHOICE("demo.notification.button-choice"),
    DIALOG_BUTTONS_MSG("demo.buttons.msg"),
    DIALOG_BUTTONS_TITLE("demo.buttons.title"),
    DIALOG_BUTTONS_TOOLTIP("demo.buttons.tooltip"),
    DIALOG_BUTTONS_MSG2("demo.buttons.msg2"),
    DIALOG_BUTTONS_TITLE2("demo.buttons.title2"),
    DIALOG_BUTTONS_TOOLTIP2("demo.buttons.tooltip2"),

    QUIT_SURE("demo.quit.areyousure"),
    QUIT_TITLE("demo.quit.title"),

    DIALOG_DROP_TITLE("demo.dialog.drop_success");

    private final String id;

    private DemoLocKey(String id) {
        this.id = id;
    }

    @Override
    public String getExternalIdentifier() {
        return id;
    }

    @Override
    public String get() {
        return Loc.get(this);
    }

    @Override
    public String get(Object ... args) {
        return Loc.get(this, args);
    }
}
