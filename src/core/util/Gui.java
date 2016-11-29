package core.util;

import javax.swing.Icon;

import core.action.ActionFactory;
import core.kernel.DefaultGui;
import core.ui.GuiSystem;
import core.ui.IconFactory;
import core.ui.IconKey;
import core.ui.ImgSize;

/**
 * Bundles various utility methods related to GUI handlers, and provides shortcut methods.
 * @author Timmos
 * @see App
 */
public final class Gui {

    private Gui () {}

    /**
     * Returns the {@link GuiSystem} handle as set in {@link DefaultGui}.
     * This method downcasts to the requested subtype internally by type inference,
     * so callers may use the method as follows:
     * <pre>
     * {@code
     *     FooGuiSystem sys = Gui.getGuiSystem();
     * }
     * </pre>
     * Of course, the plugin should have installed a {@code FooGuiSystem} (or subtype) during application load,
     * otherwise a {@code ClassCastException} will be thrown.
     * @param <T> the actual subtype of {@link GuiSystem}
     * @return A {@code GuiSystem} downcasted to {@code T}
     * @throws ClassCastException when downcasting to {@code T} fails, which may be the result of the plugin
     * not having installed an object of type {@code T} during application load
     */
    public static <T extends GuiSystem> T getGuiSystem (){
        return Various.downcast(DefaultGui.instance().getGuiSystem());
    }

    /**
     * Returns the {@link ActionFactory} handle as set in {@link DefaultGui}.
     * This method downcasts to the requested subtype internally by type inference,
     * so callers may use the method as follows:
     * <pre>
     * {@code
     *     FooActionFactory fact = Gui.getActionFactory();
     * }
     * </pre>
     * Of course, the plugin should have installed a {@code FooActionFactory} (or subtype) during application load,
     * otherwise a {@code ClassCastException} will be thrown.
     * @param <T> the actual subtype of {@link ActionFactory}
     * @return An {@code ActionFactory} downcasted to {@code T}
     * @throws ClassCastException when downcasting to {@code T} fails, which may be the result of the plugin
     * not having installed an object of type {@code T} during application load
     */
    public static <T extends ActionFactory> T getActionFactory () {
        return Various.downcast(DefaultGui.instance().getActionFactory());
    }

    /**
     * Returns the {@link IconFactory} handle as set in {@link DefaultGui}.
     * This method downcasts to the requested subtype internally by type inference,
     * so callers may use the method as follows:
     * <pre>
     * {@code
     *     FooIconFactory fact = Gui.getIconFactory();
     * }
     * </pre>
     * Of course, the plugin should have installed a {@code FooIconFactory} (or subtype) during application load,
     * otherwise a {@code ClassCastException} will be thrown.
     * @param <T> the actual subtype of {@link IconFactory}
     * @return An {@code IconFactory} downcasted to {@code T}
     * @throws ClassCastException when downcasting to {@code T} fails, which may be the result of the plugin
     * not having installed an object of type {@code T} during application load
     */
    public static <T extends IconFactory> T getIconFactory () {
        return Various.downcast(DefaultGui.instance().getIconFactory());
    }

    /**
     * Returns the GUI root handle as set in {@link DefaultGui}.
     * This method downcasts to the requested subtype internally by type inference,
     * so callers may use the method as follows:
     * <pre>
     * {@code
     *     FooDefaultGui foo = Gui.get();
     * }
     * </pre>
     * Of course, the plugin should have installed a {@code FooDefaultGui} (or subtype) during application load,
     * otherwise a {@code ClassCastException} will be thrown.
     * @param <T> the actual subtype of {@link DefaultGui}
     * @return A {@code DefaultGui} downcasted to {@code T}
     * @throws ClassCastException when downcasting to {@code T} fails, which may be the result of the plugin
     * not having installed an object of type {@code T} during application load
     */
    @SuppressWarnings("unchecked")
    public static <T extends DefaultGui> T get (){
        return (T) DefaultGui.instance();
    }

    /**
     * Shortcut for {@code Gui.getIconFactory().getDrawable(key).getIcon()}.
     * @param key the key identifying the icon
     * @return the icon
     */
    public static Icon getIcon(IconKey key) {
        return Gui.getIconFactory().getDrawable(key).getIcon();
    }

    /**
     * Shortcut for {@code Gui.getIconFactory().getDrawable(key).getIcon(size)}.
     * @param key the key identifying the icon
     * @param size the indication for the icon size
     * @return the icon
     */
    public static Icon getIcon(IconKey key, ImgSize size) {
        return Gui.getIconFactory().getDrawable(key).getIcon(size);
    }
}
