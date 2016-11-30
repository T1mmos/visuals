package gent.timdemey.visuals.core.res;

import java.util.List;
import java.util.Map.Entry;

import gent.timdemey.visuals.core.kernel.BootLoader;
import gent.timdemey.visuals.core.kernel.DefaultBootBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.kernel.LoadingWeight;
import gent.timdemey.visuals.core.log.msg.InfoMsg;
import gent.timdemey.visuals.core.util.Log;

/**
 * Initializes the resources and attaches the resource manager to the App Builder.
 * @author Timmos
 */
public class DefaultResourceManagerLoader implements BootLoader {

    @Override
    public final void load(DefaultBootBuilder builder) throws LoadingFailedException {
        DefaultResourceManagerBuilder resbuilder = new DefaultResourceManagerBuilder();
        addResourceLocations(resbuilder);

        DefaultResourceManager resMan = resbuilder.build();

        for (Entry<ResourceCategoryKey, List<ResourceLocation>> entry : resMan.getResourceLocations().entrySet()) {
            ResourceCategoryKey key = entry.getKey();
            List<ResourceLocation> locs = entry.getValue();

            Log.info(InfoMsg.RESOURCE_LOCATION_TABLE_TITLE, key);
            Log.info(InfoMsg.RESOURCE_LOCATION_TABLE_HEADER, "Root Flag Class", "Relative Directory");
            Log.info(InfoMsg.RESOURCE_LOCATION_TABLE_SEPARATOR);
            for (int i = 0; i < locs.size(); i++) {
                ResourceLocation loc = locs.get(i);
                String clazzFlagName = loc.getFlagClass().getSimpleName();
                String relpath = loc.getRelativePath();
                Log.info(InfoMsg.RESOURCE_LOCATION_TABLE_ENTRY, i, clazzFlagName, relpath + "/");
            }
        }

        builder.setResourceManager(resMan);
    }

    /**
     * Adds resource locations.
     * @param builder the builder on which resource locations can be attached
     */
    protected void addResourceLocations(DefaultResourceManagerBuilder builder) {
        builder.addResourceLocation(DefaultResourceCategoryKey.IMAGE, DefaultResourceFlag.class, "img");
    }

    @Override
    public LoadingWeight getWeight() {
        return LoadingWeight.MEDIUM;
    }

    @Override
    public String getInternalName() {
        return "Resources";
    }

}
