package gent.timdemey.visuals.core.action;

import javax.swing.Action;

import gent.timdemey.visuals.core.ui.ImgSize;

/**
 * Hands out a Swing action for a given {@link ActionKey}.
 * @author Timmos
 */
public interface ActionFactory {

    public Action getAction(ActionKey key, ActionLocation location);

    public Action getAction(ActionKey key, ActionLocation location, ImgSize size);

    public Action getAction(ActionKey key, Object params, ActionLocation location);

    public Action getAction(ActionKey key, Object param, ActionLocation location, ImgSize size);
}
