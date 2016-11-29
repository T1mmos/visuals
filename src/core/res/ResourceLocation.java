package core.res;

/**
 * Represents a resource location (a directory).
 * @author Timmos
 */
public final class ResourceLocation {

    private final Class<? extends ResourceFlag> clazz;
    private final String   relpath;

    /**
     * Creates a resource location.
     * @param clazz the flag class, used to build the path to a root resource directory
     * @param relpath the path to the resource directory, relative to the location of the flag class
     */
    public ResourceLocation(Class<? extends ResourceFlag> clazz, String relpath) {
        this.clazz = clazz;
        this.relpath = relpath.endsWith("/") ? relpath.substring(0, relpath.length() - 1) : relpath;
    }

    /**
     * Gets the flag class. The flag class is a class with no actual use but its existence. A flag class
     * is located in the directory that is the root of several resources, and can be used to get that
     * root directory.
     * @return the flag class of this resource location
     */
    public Class<?> getFlagClass() {
        return clazz;
    }

    /**
     * Gets the path to the actual resource directory, relative to the directory where the flag class is
     * located.
     * @return the path to the actual resource directory, relative to the flag class directory
     */
    public String getRelativePath() {
        return relpath;
    }
}
