package gent.timdemey.visuals.core.util;

import com.alee.laf.text.WebTextArea;

/**
 * Bundles various utility methods.
 * @author Timmos
 */
public final class Various {


    /**
     * Performs an actual downcast from supertype {@code Super} to subtype {@code Sub}.
     * @param <Super> the supertype
     * @param <Sub> the subtype
     * @param spr the object that will be downcasted
     * @return the same object, now in type {@code Sub}
     * @throws ClassCastException when downcasting to {@code Sub} fails, meaning that
     * the type isn't actually of type {@code Sub}
     */
    public static <Super, Sub extends Super> Sub downcast (Super spr) {
        @SuppressWarnings("unchecked")
        Sub downcast = (Sub) spr;
        return downcast;
    }

    /**
     * Creates a multiline label. In reality, the component is a {@link WebTextArea} that is modified to appear as it
     * were a label.
     * @param content
     * @return
     */
    public static WebTextArea createMultiLine(String content) {
        WebTextArea txtExpl = new WebTextArea(content);

        // text.setColumns(100);
        txtExpl.setEditable(false);
        txtExpl.setLineWrap(true);
        txtExpl.setWrapStyleWord(true);
        txtExpl.setOpaque(false);
        txtExpl.setMargin(0);
        txtExpl.setFocusable(false);

        return txtExpl;
    }
}
