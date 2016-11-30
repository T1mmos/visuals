package gent.timdemey.visuals.core.res;

/**
 * A class implementing this interface can be used to locate a resource root folder. It has no actual code-wise
 * purpose, besides calling {@code SomeResourceFlag.class.getResourceAsStream("resfolder/something.txt")} on it.
 */
public interface ResourceFlag {
    // marker interface (and also its implementing classes)
}
