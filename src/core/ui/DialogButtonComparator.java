package core.ui;

import java.util.Comparator;

/**
 * Compares {@link DialogButton}s, used to order them for a consistent User Experience.
 * @author Timmos
 */
public enum DialogButtonComparator implements Comparator<DialogButton> {

    /** Singleton instance. */
    INSTANCE;

    @Override
    public int compare(DialogButton o1, DialogButton o2) {
        return o1.getOrder() - o2.getOrder();
    }

}
