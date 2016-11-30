package gent.timdemey.visuals.core.key;


/**
 * Defines an key that is used to identify some property in an external resource.
 * <p>For example, an key for identifying localization entries in a localization
 * file, should hold a string that holds the localization key - that string is the
 * key's external identifier.
 * @author Timmos
 */
public interface ExternalKey extends Key {

    /**
     * Gets the string by which the external resource can be uniquely identified.
     * @return the identifier that can identify the external resource
     */
    public String getExternalIdentifier (); // a string will mostly do, but an Object could also be a good choice
  
}
