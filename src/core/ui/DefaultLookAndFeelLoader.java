package core.ui;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.style.StyleManager;
import com.alee.managers.style.XmlSkinExtension;
import com.alee.skin.dark.DarkSkin;
import com.alee.skin.web.WebSkin;

import core.cfg.DefaultConfigKey;
import core.kernel.DefaultGuiBuilder;
import core.kernel.GuiLoader;
import core.kernel.LoadingFailedException;
import core.kernel.LoadingWeight;
import core.res.DefaultResourceFlag;

/**
 * A Look and Feel loader that determines the LAF by looking at the configuration.
 * @author Timmos
 */
public class DefaultLookAndFeelLoader implements GuiLoader {

    static final Object[] SKINS = new Object[] {
        "dark", DarkSkin.class, "DarkSkin.xml",
        "light", WebSkin.class, "LightSkin.xml",
    };

    @Override
    public void load(DefaultGuiBuilder builder) throws LoadingFailedException {
        SkinFactory fact = new DefaultSkinFactory();
        builder.setSkinFactory(fact);

        UISkin skin = DefaultConfigKey.GUI_SKIN.get();
        WebLookAndFeel.install(skin.getSkinClass());

        StyleManager.addExtensions(new XmlSkinExtension(DefaultResourceFlag.class, "style/" + skin.getXmlFile()));
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.BIG;
    }

    @Override
    public String getInternalName() {
        return "Default Look and Feel";
    }
}
