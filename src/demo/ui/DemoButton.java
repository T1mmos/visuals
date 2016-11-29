package demo.ui;

import core.loc.LocKey;
import core.ui.DialogButton;
import demo.loc.DemoLocKey;

public enum DemoButton implements DialogButton {
    NORTH(DemoLocKey.NORTH,0),
    EAST(DemoLocKey.EAST,1),
    SOUTH(DemoLocKey.SOUTH,2),
    WEST(DemoLocKey.WEST,3),
    RANDOM(DemoLocKey.RANDOM,100);

    private final LocKey key;
    private final int    order;

    private DemoButton(LocKey key, int order) {
        this.key = key;
        this.order = order;
    }

    @Override
    public LocKey getLocKey() {
        return key;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
