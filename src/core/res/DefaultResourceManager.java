package core.res;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.log.msg.InfoMsg;
import core.log.msg.WarnMsg;
import core.util.Log;

/**
 * Default resource manager which uses local files. This implementation provides the ability to look for a
 * resource in multiple resource locations, which is useful for plugins. When the first resource location
 * doesn't have a 'hit', the second resource location is visited, and so on until no more resource locations
 * are available. One can create an instance by using {@link DefaultResourceManagerBuilder}.
 * @author Timmos
 */
public final class DefaultResourceManager implements ResourceManager {

    private final Map<ResourceCategoryKey, List<ResourceLocation>> locMap;

    /**
     * Creates a default resource manager.
     * @param locMap a map with all resource locations per resource category
     */
    public DefaultResourceManager(Map<ResourceCategoryKey, List<ResourceLocation>> locMap) {
        Map<ResourceCategoryKey, List<ResourceLocation>> tmpMap = new HashMap<>();
        for (ResourceCategoryKey key : locMap.keySet()) {
            tmpMap.put(key, Collections.unmodifiableList(locMap.get(key)));
        }
        this.locMap = Collections.unmodifiableMap(tmpMap);
    }

    @Override
    public InputStream load(ResourceCategoryKey type, String name) throws IOException {
        List<ResourceLocation> locations = locMap.get(type);
        if (locations == null) {
            throw new IOException("No resource locations available for resource category: " + type);
        }
        for (int i = locations.size() - 1; i >= 0; i--) { // latest added resources get priority
            ResourceLocation loc = locations.get(i);
            String simpleClass = loc.getFlagClass().getSimpleName();
            String relpath = getRelativePath(loc, name);

            InputStream stream = loc.getFlagClass().getResourceAsStream(relpath);
            if (stream != null) {
                Log.info(InfoMsg.RESOURCE_SEARCHING, simpleClass, relpath);
                return stream;
            }
        }

        Log.warn(WarnMsg.RESOURCE_NOWHERE_FOUND, name);
        throw new IOException("Missing resource: " + name);
    }

    /**
     * Allows to retrieve the resource location map. The returned map and its values are all unmodifiable
     * structures, so attempting to modify them will result in an unchecked exception (according to the
     * implementations).
     * @return an unmodifiable map containing all resource locations for each resource category
     */
    public Map<ResourceCategoryKey, List<ResourceLocation>> getResourceLocations() {
        return locMap;
    }

    private static String getRelativePath(ResourceLocation loc, String name) {
        String prefix = loc.getRelativePath();
        String relpath = prefix + (prefix.endsWith("/") ? "" : "/") + name;
        return relpath;
    }
}
