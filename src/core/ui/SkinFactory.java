package core.ui;

import java.util.List;

public interface SkinFactory {

    public UISkin getSkin();
    public List<UISkin> getSupportedSkins();
}
