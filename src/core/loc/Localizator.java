package core.loc;

import java.util.List;

/**
 * A system that translates a {@link String localization key}
 * into a localized string. It is up to the implementation to choose the
 * external resource for the translations.
 * @author Timmos
 */
public interface Localizator {

    /**
     * Searches a localization entry for the given key, and returns a localized
     * string for usage in a user interface.
     * <p>Implementations should return
     * a default string in case the key cannot be found in external resources,
     * but should never return {@code null} nor throw unchecked exceptions.
     * @param key the key to identify the localization entry
     * @return a localized string, or a default string when the no entry could
     * be found for the given localization key
     */
    public String get(LocKey key);

    /**
     * Searches a localization entry for the given key, and returns a localized
     * string for usage in a user interface with the placeholders filled in.
     * <p>
     * Implementations should return a default string in case the key cannot be found in external resources, but should
     * never return {@code null} nor throw unchecked exceptions.
     * @param key the key to identify the localization entry
     * @param args the objects (likely Strings) that fill in the parameterized localization entry
     * @return a localized string, or a default string when the no entry could
     * be found for the given localization key
     */
    public String get(LocKey key, Object ... args);

    /**
     * Gets the list of supported languages.
     * @return the list of supported languages
     */
    public List<LanguageKey> getSupportedLanguages();
}
