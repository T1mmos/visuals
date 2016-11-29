package core.kernel;

import core.action.ActionFactory;
import core.ui.GuiSystem;
import core.ui.IconFactory;
import core.ui.RendererFactory;
import core.ui.SkinFactory;

/**
 * The core's default GUI builder implementation. The following features are supported:
 * <ul>
 * <li>Action factory:
 * <li>Icon factory
 * <li>GUI system: an abstraction framework on top of Swing, to allow uniform user experience
 * throughout the application. It provides thread-safe, extensible choice dialogs, easy access
 * to the windowing system, and makes working with the GUI easy.
 * </ul>
 * <p>Plugins may want to customize the GUI part of building the application, and may
 * therefore need to override {@link Plugin#newGuiBuilder()} to return a custom
 * builder.
 * @author Timmos
 */
public class DefaultGuiBuilder implements Buildable {

    /** The Swing action factory. */
    protected ActionFactory actFact;
    /** The GUI system. */
    protected GuiSystem guiSystem;
    /** The icon factory. */
    protected IconFactory iconFact;
    /** The render factory. */
    protected RendererFactory renderFact;

    /** The skin factory. */
    protected SkinFactory     skinFact;

    /**
     * Constructor.
     */
    protected DefaultGuiBuilder () {
    }

    /**
     * Sets the Swing action factory.
     * @param actFact the action factory
     */
    public void setActionFactory (ActionFactory actFact){
        this.actFact = actFact;
    }

    /**
     * Sets the icon factory.
     * @param iconFact the icon factory
     */
    public void setIconFactory (IconFactory iconFact){
        this.iconFact = iconFact;
    }

    /**
     * Sets the GUI system.
     * @param guiSystem the GUI system
     */
    public void setGuiSystem (GuiSystem guiSystem){
        this.guiSystem = guiSystem;
    }

    public void setSkinFactory(SkinFactory skinFact) {
        this.skinFact = skinFact;
    }

    /**
     * Sets the render factory.
     * @param renderFact the render factory
     */
    public void setRenderFactory(RendererFactory renderFact) {
        this.renderFact = renderFact;
    }

    @Override
    public DefaultGui build (){
        return new DefaultGui(guiSystem, actFact, iconFact, renderFact, skinFact);
    }
}
