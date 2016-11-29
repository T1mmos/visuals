package demo.res;

import core.res.DefaultResourceCategoryKey;
import core.res.DefaultResourceManagerBuilder;
import core.res.DefaultResourceManagerLoader;

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
