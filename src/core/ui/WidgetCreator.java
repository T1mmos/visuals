package core.ui;

import com.alee.laf.button.WebButton;

import core.action.ActionKey;

public interface WidgetCreator {

    public WebButton createButton(ActionKey key);
}
