package gent.timdemey.visuals.core.ui;

import com.alee.laf.button.WebButton;

import gent.timdemey.visuals.core.action.ActionKey;

public interface WidgetCreator {

    public WebButton createButton(ActionKey key);
}
