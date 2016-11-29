package core.loc;

import java.util.Arrays;
import java.util.List;

/**
 * The default localizator implementation, which translates each localization
 * key into its external key. Thus, this localizator can be used in
 * untranslated applications, but then the external keys must be human readable.
 * @author Timmos
 */
public enum DefaultLocalizator implements Localizator {

    /** Singleton instance. */
    INSTANCE;

    @Override
    public String get(LocKey key) {
        return key.getExternalIdentifier();
    }

    @Override
    public String get(LocKey key, Object ... args) {
        return key.getExternalIdentifier();
    }

    @Override
    public List<LanguageKey> getSupportedLanguages() {
        return Arrays.<LanguageKey> asList(DefaultLanguageKey.values());
    }
}
