package gent.timdemey.visuals.demo.res;

import gent.timdemey.visuals.core.res.DefaultResourceCategoryKey;
import gent.timdemey.visuals.core.res.DefaultResourceManagerBuilder;
import gent.timdemey.visuals.core.res.DefaultResourceManagerLoader;

/**
 * Adds an extra image resource directory to the resources.
 * @author Timmos
 */
public class DemoResourceManagerLoader extends DefaultResourceManagerLoader {

    @Override
    protected void addResourceLocations(DefaultResourceManagerBuilder builder) {
        super.addResourceLocations(builder);
        builder.addResourceLocation(DefaultResourceCategoryKey.IMAGE, DemoResourceFlag.class, "img/");
        builder.addResourceLocation(DefaultResourceCategoryKey.LOCALIZATION_FILE, DemoResourceFlag.class, "loc/");
    }
}
