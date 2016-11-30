package gent.timdemey.visuals.demo.ui;

import gent.timdemey.visuals.core.loc.LocKey;
import gent.timdemey.visuals.core.ui.DialogButton;
import gent.timdemey.visuals.demo.loc.DemoLocKey;

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
