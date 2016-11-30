package gent.timdemey.visuals.core.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds a {@link DefaultResourceManager}.
 * @author Timmos
 */
public final class DefaultResourceManagerBuilder {

    private final Map<ResourceCategoryKey, List<ResourceLocation>> locMap;

    /**
     * Creates a builder to build a {@link DefaultResourceManager}.
     */
    public DefaultResourceManagerBuilder() {
        this.locMap = new HashMap<>();
    }

    /**
     * Adds a resource location for a given resource category.
     * @param key identifies the resource category
     * @param clazz points to the class in the resource root directory
     * @param relpath the path to the resource location, relative to the location of the flag class
     */
    public void addResourceLocation(ResourceCategoryKey key, Class<? extends ResourceFlag> clazz, String relpath) {
        List<ResourceLocation> locs = locMap.get(key);
        if (locs == null) {
            locMap.put(key, locs = new ArrayList<>());
        }
        ResourceLocation loc = new ResourceLocation(clazz, relpath);
        if (locs.contains(loc)) {
            throw new IllegalArgumentException("Resource location already added: " + loc);
        }
        locs.add(loc);
    }

    /**
     * Builds a {@link DefaultResourceManager} with all the added resource locations set to this builder.
     * @return a new {@link DefaultResourceManager}
     */
    public DefaultResourceManager build() {
        return new DefaultResourceManager(locMap);
    }
}
