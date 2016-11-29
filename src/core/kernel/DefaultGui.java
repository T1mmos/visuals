package core.kernel;

import core.action.ActionFactory;
import core.ui.GuiSystem;
import core.ui.IconFactory;
import core.ui.RendererFactory;
import core.ui.SkinFactory;
import core.util.Gui;
import core.util.Unchecked;

/**
 * The GUI layer backbone. An instance of this class, or a subclass of this class,
 * is built by a {@link DefaultGuiBuilder} (or a subclass of that builder class) during
 * application load, phase II. Once created, this instance is the sole backbone of the GUI layer
 * of the application, providing access to:
 * <ul>
 * <li>GUI system
 * <li>Action Factory
 * <li>Icon Factory
 * </ul>
 * <p>{@link DefaultApp} is the application core variant of this class. The GUI is treated as a layer
 * on top of the core layer.
 * @see DefaultApp
 * @see DefaultAppBuilder
 * @see DefaultGuiBuilder
 * @author Timmos
 */
public class DefaultGui {

    private final GuiSystem guiSystem;
    private final ActionFactory actFact;
    private final IconFactory iconFact;
    private final RendererFactory renderFact;
    private final SkinFactory     skinFact;

    /**
     * Creates a GUI backbone. This method should only be called from a {@link DefaultGuiBuilder} or
     * a subclass constructor.
     * @param system the GUI system
     * @param actFact the action factory
     * @param iconFact the icon factory
     * @param renderFact the render factory
     * @param skinFact the skin factory
     */
    protected DefaultGui(
                    GuiSystem system,
                    ActionFactory actFact,
                    IconFactory iconFact,
                    RendererFactory renderFact,
                    SkinFactory skinFact) {
        Unchecked.nullPtr(system, "A GUI application must be initialized with a Gui System");
        Unchecked.nullPtr(actFact, "A GUI application must be initialized with an Action Factory");
        Unchecked.nullPtr(iconFact, "A GUI application must be initialized with an Icon Factory");
        Unchecked.nullPtr(renderFact, "A GUI application must be initialized with a Render Factory");
        Unchecked.nullPtr(skinFact, "A GUI application must be initialized with a Skin Factory");
        this.guiSystem = system;
        this.actFact = actFact;
        this.iconFact = iconFact;
        this.renderFact = renderFact;
        this.skinFact = skinFact;
    }

    // private dummy constructor, used only by InternalNullPlugin. DO NOT USE.
    // I repeat: do not, never ever use this constructor and save yourself from
    // the end of the world.
    private DefaultGui(GuiSystem guiSystem) {
        this.guiSystem = guiSystem;
        this.actFact = null;
        this.iconFact = null;
        this.renderFact = null;
        this.skinFact = null;
    }

    /**
     * Gets the GUI system.
     * @return the GUI system
     */
    public GuiSystem getGuiSystem() {
        return guiSystem;
    }

    /**
     * Gets the action factory.
     * @return the action factory
     */
    public ActionFactory getActionFactory() {
        return actFact;
    }

    /**
     * Gets the icon factory.
     * @return the icon factory
     */
    public IconFactory getIconFactory (){
        return iconFact;
    }

    /**
     * Gets the render factory.
     * @return the render factory
     */
    public RendererFactory getRenderFactory() {
        return renderFact;
    }

    /**
     * Gets the skin factory.
     * @return the skin factory
     */
    public SkinFactory getSkinFactory() {
        return skinFact;
    }

    private static DefaultGui gui = null;

    private static boolean isSet (){
        return DefaultGui.gui != null;
    }

    /**
     * Installs the GUI backbone singleton. The backbone cannot be uninstalled.
     * This method must be called only from the main loader during phase II.
     * @param backbone the application backbone - an instance of {@link DefaultAppBuilder}
     * or a subclass of it
     * @throws IllegalStateException when attempting to install a backbone more than once
     * @throws NullPointerException when {@code backbone} is {@code null}
     */
    static void install(DefaultGui backbone) {
        if (isSet()) {
            throw new IllegalStateException("GUI already set up");
        }
        DefaultGui.gui = backbone;
    }

    /**
     * Gets the GUI backbone singleton. The returned object is
     * the only entry-point to GUI-related functionality and is used to get
     * handles to the action factory, icon factory, GUI system, etc.
     * <p>The {@link Gui} class has various utility methods that may lead to
     * less verbose code.
     * @return the GUI backbone singleton.
     * @see Gui
     */
    public static DefaultGui instance() {
        if (!isSet()) {
            throw new IllegalStateException("GUI not initialized");
        }
        return DefaultGui.gui;
    }

    /**
     * Creates a dummy instance. This method should be called only by a {@link InternalNullPlugin}
     * and has no other purpose. DO NOT USE THIS METHOD.
     * @param guiSystem the gui system supplied by {@link InternalNullPlugin} (probably to show
     * some information dialog to inform the user why that plugin is used)
     * @return a dummy {@link DefaultGui} without any GUI handle.
     */
    static DefaultGui dummy (GuiSystem guiSystem) {
        return new DefaultGui(guiSystem);
    }
}
