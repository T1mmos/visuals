package core.ui;

import com.alee.managers.style.Skin;
import com.alee.skin.dark.DarkSkin;
import com.alee.skin.web.WebSkin;

public enum DefaultUISkin implements UISkin {
    DARK("Dark",DarkSkin.class,"DarkSkin.xml","white/"),
    LIGHT("Light",WebSkin.class,"LightSkin.xml","");

    private final String                name;
    private final Class<? extends Skin> clazz;
    private final String                xmlfile;
    private final String                dir;

    private DefaultUISkin(String name, Class<? extends Skin> clazz, String xmlfile, String dir) {
        this.name = name;
        this.clazz = clazz;
        this.xmlfile = xmlfile;
        this.dir = dir;
    }

    @Override
    public Class<? extends Skin> getSkinClass() {
        return clazz;
    }

    @Override
    public String getXmlFile() {
        return xmlfile;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSkinnedIcon(String basename) {
        return dir + basename;
    }
}
