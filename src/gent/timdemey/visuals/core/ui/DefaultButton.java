package gent.timdemey.visuals.core.ui;

import java.util.EnumSet;

import gent.timdemey.visuals.core.loc.DefaultLocKey;
import gent.timdemey.visuals.core.loc.LocKey;


/**
 * Enumerates the possible buttons in a {@link ChoiceDialog}. Also provides static fields
 * that hold immutable sets of the most common button configurations.
 * @author Timmos
 */
public enum DefaultButton implements DialogButton {

    /** An OK button. */
    OK(DefaultLocKey.LABEL_OK,0),
    /** A Yes button. */
    YES(DefaultLocKey.LABEL_YES,20),
    /** A No button. */
    NO(DefaultLocKey.LABEL_NO,30),
    /** A Cancel button, or the X-button of the dialog. */
    CANCEL(DefaultLocKey.LABEL_CANCEL,10);

    /** Provides the OK button singleton set. */
    public static final EnumSet<DefaultButton> SET_OK = EnumSet.of(OK);
    /** Provides the set including buttons OK and Cancel. */
    public static final EnumSet<DefaultButton> SET_OK_CANCEL = EnumSet.of(OK, CANCEL);
    /** Provides the set including buttons Yes and No. */
    public static final EnumSet<DefaultButton> SET_YES_NO = EnumSet.of(YES, NO);
    /** Provides the set including buttons Yes, No and Cancel. */
    public static final EnumSet<DefaultButton> SET_YES_NO_CANCEL = EnumSet.of(YES, NO, CANCEL);

    private final LocKey                       locid;
    private final int                          order;

    private DefaultButton(LocKey id, int order) {
        this.locid = id;
        this.order = order;
    }

    @Override
    public LocKey getLocKey() {
        return locid;
    }

    @Override
    public int getOrder () {
        return order;
    }
}
