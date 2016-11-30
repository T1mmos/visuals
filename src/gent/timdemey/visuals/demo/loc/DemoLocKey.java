package gent.timdemey.visuals.demo.loc;

import gent.timdemey.visuals.core.loc.LocKey;
import gent.timdemey.visuals.core.util.Loc;

/**
 * Demo-specific localization keys.
 * @author Timmos
 */
public enum DemoLocKey implements LocKey {

    APP_TITLE("gent.timdemey.visuals.demo.app.title"),
    MENU_ITEM_MENU1("gent.timdemey.visuals.demo.menu.file"),
    MENU_ITEM_MENU2("gent.timdemey.visuals.demo.menu.edit"),

    CONFIG_CHOOSE_LANGUAGE("gent.timdemey.visuals.demo.label.config.chooselang"),
    CONFIG_CHOOSE_LOGLEVEL("gent.timdemey.visuals.demo.label.config.chooseloglvl"),
    CONFIG_CHOOSE_UISKIN("gent.timdemey.visuals.demo.label.config.chooseuiskin"),
    CONFIG_RESTART_REQUIRED("gent.timdemey.visuals.demo.label.config.restart"),
    CONFIG_LANG_EXPLANATION("gent.timdemey.visuals.demo.label.config.expl"),
    CONFIG_LOGGING_EXPLANATION("gent.timdemey.visuals.demo.label.config.logging.expl"),

    /** Enter a name. */
    MENU_ITEM_ENTERNAME("gent.timdemey.visuals.demo.action.enter_name"),
    /** 'Button Choice' dialog content. */
    DIALOG_CONTENT_BUTTONCHOICE("gent.timdemey.visuals.demo.dialog.content.button-choice"),
    /** 'Enter Name' dialog content. */
    DIALOG_CONTENT_ENTERNAME("gent.timdemey.visuals.demo.dialog.content.enter_name"),
    /** 'Enter Name' dialog title. */
    DIALOG_TITLE_ENTERNAME("gent.timdemey.visuals.demo.dialog.title.enter_name"),
    /** Demo example. */
    DEMO_EXAMPLE("gent.timdemey.visuals.demo.example"),
    DROP_HERE ("gent.timdemey.visuals.demo.dnd.drop_file_here"),
    DROP_SUCCESS("gent.timdemey.visuals.demo.dnd.file_dropped"),

    STATUSBAR_SETTINGS("gent.timdemey.visuals.demo.statusbar.settings"),
    STATUSBAR_INFO("gent.timdemey.visuals.demo.statusbar.info"),
    STATUSBAR_SETTINGS_TOOLTIP("gent.timdemey.visuals.demo.statusbar.settings.tooltip"),
    SETTINGS_CHECK1("gent.timdemey.visuals.demo.dialog.settings.check1"),
    SETTINGS_CHECK2("gent.timdemey.visuals.demo.dialog.settings.check2"),
    SETTINGS_CHECK3("gent.timdemey.visuals.demo.dialog.settings.check3"),

    SHOW_ANIMAL("gent.timdemey.visuals.demo.show_animal"),
    SHOW_ANIMAL_MSG("gent.timdemey.visuals.demo.show_animal_msg"),
    ANIMAL_MONKEY("gent.timdemey.visuals.demo.animals.monkey"),
    ANIMAL_FLY("gent.timdemey.visuals.demo.animals.fly"),
    ANIMAL_BUMBLEBEE("gent.timdemey.visuals.demo.animals.bumblebee"),
    ANIMAL_SHEEP("gent.timdemey.visuals.demo.animals.sheep"),

    DIALOG_CONFIGURE_TITLE("gent.timdemey.visuals.demo.dialog.settings.title"),
    CONFIGURE_TOOLTIP("gent.timdemey.visuals.demo.dialog.settings.tooltip"),

    NOTIFICATION_TAB("gent.timdemey.visuals.demo.tabs.notifications.title"),
    DIALOGS_TAB("gent.timdemey.visuals.demo.tabs.dialogs.title"),
    BUTTONS_TAB("gent.timdemey.visuals.demo.tabs.buttons.title"),
    NOTIFICATIONS_EXPLANATION("gent.timdemey.visuals.demo.tabs.notifications.explanation"),

    DROP_NOT_ACCEPTED("gent.timdemey.visuals.demo.drop_not_accepted"),
    TOOLTIP_SHOW_ANIMAL("gent.timdemey.visuals.demo.tooltip.show_animal"),
    TOOLTIP_SHOW_NOTIFICATION("gent.timdemey.visuals.demo.tooltip.show_notification"),
    NOTIFICATION_EXAMPLE("gent.timdemey.visuals.demo.notification.example"),
    TOOLTIP_ENTERNAME("gent.timdemey.visuals.demo.tooltip.entername"),

    DIALOG_ASYNC_RESULT("gent.timdemey.visuals.demo.dialog.async.result"),
    DIALOG_ASYNC_TITLE("gent.timdemey.visuals.demo.dialog.async.title"),
    DIALOG_ASYNC_TOOLTIP("gent.timdemey.visuals.demo.dialog.async.tooltip"),
    DIALOG_ASYNC_INPUT("gent.timdemey.visuals.demo.dialog.async.seconds"),

    NOT_A_NUMBER("gent.timdemey.visuals.demo.not_a_number"),
    NUMBER_BETWEEN_X_AND_Y("gent.timdemey.visuals.demo.number_between"),
    WAITING_FOR("gent.timdemey.visuals.demo.waiting_for"),

    READ_DOC("gent.timdemey.visuals.demo.read_doc"),

    NORTH("gent.timdemey.visuals.demo.north"),
    EAST("gent.timdemey.visuals.demo.east"),
    SOUTH("gent.timdemey.visuals.demo.south"),
    WEST("gent.timdemey.visuals.demo.west"),
    RANDOM("gent.timdemey.visuals.demo.random"),
    NOTIFICATION_BUTTONCHOICE("gent.timdemey.visuals.demo.notification.button-choice"),
    DIALOG_BUTTONS_MSG("gent.timdemey.visuals.demo.buttons.msg"),
    DIALOG_BUTTONS_TITLE("gent.timdemey.visuals.demo.buttons.title"),
    DIALOG_BUTTONS_TOOLTIP("gent.timdemey.visuals.demo.buttons.tooltip"),
    DIALOG_BUTTONS_MSG2("gent.timdemey.visuals.demo.buttons.msg2"),
    DIALOG_BUTTONS_TITLE2("gent.timdemey.visuals.demo.buttons.title2"),
    DIALOG_BUTTONS_TOOLTIP2("gent.timdemey.visuals.demo.buttons.tooltip2"),

    QUIT_SURE("gent.timdemey.visuals.demo.quit.areyousure"),
    QUIT_TITLE("gent.timdemey.visuals.demo.quit.title"),

    DIALOG_DROP_TITLE("gent.timdemey.visuals.demo.dialog.drop_success");

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
