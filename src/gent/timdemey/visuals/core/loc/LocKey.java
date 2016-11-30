package gent.timdemey.visuals.core.loc;

import gent.timdemey.visuals.core.key.ExternalKey;

/**
 * A key for identifying localization entries.
 * @author Timmos
 */
public interface LocKey extends ExternalKey {

    /**
     * Gets the localized string for this localization key. As this is
     * an interface method, an implementation is free to implement this
     * as desired, although the recommended practice is to implement this method as a
     * utility method:
     *
     * <pre>
     * {@code return Loc.get(this);}
     * </pre>
     *
     * This allows clients to use
     * the shortest method possible, and prevents cluttering their code. In any case,
     * it is discouraged to bind this interface and its descendants to an external
     * resource; this would be the domain of {@link Localizator}.
     * <p>
     * The design choice was made not to create an abstract class to allow for enum implementations (which make adding a
     * new localization entry easy) - but it requires to reimplement this method in every (enum) implementation.
     * @return the localized string for this localization key
     */
    public String get();

    /**
     * Gets the localized string for this parameterized localization key. As this is
     * an interface method, an implementation is free to implement this
     * as desired, although the recommended practice is to implement this method as a
     * utility method:
     *
     * <pre>
     * {@code return Loc.get(this, args);}
     * </pre>
     *
     * This allows clients to use
     * the shortest method possible, and prevents cluttering their code. In any case,
     * it is discouraged to bind this interface and its descendants to an external
     * resource; this would be the domain of {@link Localizator}.
     * <p>
     * The design choice was made not to create an abstract class to allow for enum implementations (which make adding a
     * new localization entry easy) - but it requires to reimplement this method in every (enum) implementation.
     * @param args the arguments to replace the localization string's placeholders with
     * @return the localized string for this localization key
     */
    public String get(Object ... args);
}
