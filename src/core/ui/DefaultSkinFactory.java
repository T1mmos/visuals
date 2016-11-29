package core.ui;

import java.util.Arrays;
import java.util.List;

import core.cfg.DefaultConfigKey;

public class DefaultSkinFactory implements SkinFactory {

    @Override
    public UISkin getSkin() {
        return DefaultConfigKey.GUI_SKIN.get();
    }

    @Override
    public List<UISkin> getSupportedSkins() {
        return Arrays.<UISkin> asList(DefaultUISkin.values());
    }
}
