package demo.action;

import core.action.ActionKey;
import core.action.DefaultActionFactory;
import core.action.DefaultActionKey;
import core.action.GuiAction;
import core.ui.MessageType;
import demo.action.ShowAnimalAction.AnimalParam;
import demo.action.ShowButtonDialog.ShowButtonParam;

/**
 * Distributes demo-specific {@link GuiAction}s for {@link DemoActionKey}s.
 * @author Timmos
 */
public class DemoActionFactory extends DefaultActionFactory {

    @Override
    protected GuiAction getGuiAction(ActionKey id, Object param) {
        if (DemoActionKey.ENTER_NAME.equals(id)) {
            return new EnterNameAction();
        }
        if (DemoActionKey.SETTINGS.equals(id)) {
            return new ConfigureAction();
        }
        if (DemoActionKey.SHOW_ANIMAL.equals(id)) {
            AnimalParam p = checkParam(id, param, AnimalParam.class);
            return new ShowAnimalAction(p);
        }
        if (DemoActionKey.SHOW_NOTIFICATION.equals(id)) {
            MessageType p = checkParam(id, param, MessageType.class);
            return new ShowNotificationAction(p);
        }
        if (DemoActionKey.SHOW_ASYNC_DIALOG.equals(id)) {
            return new ShowAsyncDialogAction();
        }
        if (DemoActionKey.READ_DOC.equals(id)) {
            String p = checkParam(id, param, String.class);
            return new ReadDocAction(p);
        }
        if (DemoActionKey.SHOW_BUTTON_DIALOG.equals(id)) {
            ShowButtonParam p = checkParam(id, param, ShowButtonParam.class);
            return new ShowButtonDialog(p);
        }
        if (DefaultActionKey.EXIT_APPLICATION.equals(id)) {
            return new ExitApplicationAction();
        }
        return super.getGuiAction(id, param);
    }

    private static <T> T checkParam(ActionKey key, Object param, Class<T> expCls) {
        if (param == null) {
            String format= "Null parameter for action key '%s'. Expected class: %s";
            String msg = String.format(format, key, expCls.getSimpleName());
            throw new NullPointerException(msg);
        }
        if (!expCls.isAssignableFrom(param.getClass())) {
            String format = "Illegal parameter for action key '%s'. Expected class: %s, but got: %s";
            String msg = String.format(format, key, expCls.getSimpleName(), param.getClass().getSimpleName());
            throw new IllegalArgumentException(msg);
        }
        @SuppressWarnings("unchecked")
        T ret = (T) param;
        return ret;
    }
}
