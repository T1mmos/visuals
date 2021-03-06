package gent.timdemey.visuals.demo.loc;

import gent.timdemey.visuals.core.loc.LanguageKey;
import gent.timdemey.visuals.core.ui.IconKey;
import gent.timdemey.visuals.demo.ui.DemoIconKey;

public enum DemoLanguageKey implements LanguageKey {
    VIKING("Viking ARRR!","VIKING",DemoIconKey.VIKING);

    private final String  name;
    private final String id;
    private final IconKey iconKey;

    private DemoLanguageKey(String name, String id, IconKey iconKey) {
        this.name = name;
        this.id = id;
        this.iconKey = iconKey;
    }

    @Override
    public String getExternalIdentifier() {
        return id;
    }

    @Override
    public IconKey getIconKey() {
        return iconKey;
    }

    @Override
    public String getName() {
        return name;
    }

}
