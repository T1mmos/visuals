package demo.ui;

import java.io.File;
import java.util.List;

import core.ui.DefaultMessageType;
import core.ui.DropHandler;
import core.util.Gui;
import demo.loc.DemoLocKey;

public class DemoDropHandler implements DropHandler {

    @Override
    public boolean isFileDragAccepted(List<File> payload) {
        if (payload.size() != 1) {
            notAccepted();
            return false;
        }
        File file = payload.get(0);
        if (!file.getName().toLowerCase().endsWith(".demo")) {
            notAccepted();
            return false;
        }
        return true;
    }

    @Override
    public void onFileDrop(List<File> payload) {
        String msg = DemoLocKey.DROP_SUCCESS.get(payload.get(0).getName());
        String title = DemoLocKey.DIALOG_DROP_TITLE.get();
        Gui.getGuiSystem().createMessage(msg, title, DemoIconKey.DOCUMENT).showAsync();
    }

    private static void notAccepted() {
        Gui.getGuiSystem().showNotification(DemoLocKey.DROP_NOT_ACCEPTED.get(), DefaultMessageType.WARNING);
    }
}
