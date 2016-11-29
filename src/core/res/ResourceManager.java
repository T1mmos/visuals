package core.res;

import java.io.IOException;
import java.io.InputStream;

/**
 * Responsible for finding resources.
 * @author Timmos
 */
public interface ResourceManager {

    /**
     * Loads the resource identified by the given name.
     * @param key the resource category, used to determine where the resource can be located
     * @param name the resource name, to uniquely identify it
     * @return an {@link InputStream} to the resource
     * @throws IOException if the resource cannot be found or is inaccessible, ...
     */
    public InputStream load(ResourceCategoryKey key, String name) throws IOException;
}
