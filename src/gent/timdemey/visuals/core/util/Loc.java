package gent.timdemey.visuals.core.util;

import gent.timdemey.visuals.core.kernel.DefaultApp;
import gent.timdemey.visuals.core.loc.LocKey;
import gent.timdemey.visuals.core.loc.Localizator;

/**
 * Provides direct access to the installed localizator's {@link Localizator#get(String) get}
 * method.
 * @author Timmos
 * @see Log
 */
public final class Loc {

    private Loc () {}

    /**
     * A direct method for {@link Localizator#get(String)}. Consider the following code snippet:
     * <pre>{@code String text = DefaultApp.instance().getLocalizator().get(key);}</pre>
     * The following code snippet is equivalent:
     * <pre>{@code String text = Loc.get(key);}</pre>
     * or even shorter:
     * <pre>{@code String text = key.get();}</pre>
     * but the latter is depending on the implementation (but the general agreed practice is to implement
     * {@link String#get() get()} by delegation to {@link Loc#get(String) Loc.get(key)}).
     * These shortcuts enable clients to use
     * short code, yet providing a pluggable localization mechanism under
     * the hood as plugins may choose to install a custom localizator during application load.
     *
     * @param key the localization key
     * @return the localized string
     */
    public static String get(LocKey key) {
        return DefaultApp.instance().getLocalizator().get(key);
    }

    /**
     * A direct method for {@link Localizator#get(String, Object...)}. Consider the following code snippet:
     * <pre>{@code String text = DefaultApp.instance().getLocalizator().get(key, args);}</pre>
     * The following code snippet is equivalent:
     * <pre>{@code String text = Loc.get(key, args);}</pre>
     * or even shorter:
     * <pre>{@code String text = key.get(args);}</pre>
     * but the latter is depending on the implementation (but the general agreed practice is to implement
     * {@link String#get(Object...) get(Object ...)} by delegation to {@link Loc#get(String, Object...) Loc.get(key, args)}).
     * These shortcuts enable clients to use
     * short code, yet providing a pluggable localization mechanism under
     * the hood as plugins may choose to install a custom localizator during application load.
     *
     * @param key the localization key
     * @param args the arguments to replace the placeholders in the localized string
     * @return the localized string
     */
    public static String get(LocKey key, Object ... args) {
        return DefaultApp.instance().getLocalizator().get(key, args);
    }
}
