package demo.loc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import core.loc.LanguageKey;
import core.loc.LocKey;
import core.loc.Localizator;
import core.log.msg.ErrorMsg;
import core.res.DefaultResourceCategoryKey;
import core.util.App;
import core.util.Log;
import demo.log.msg.DemoErrorMsg;

/**
 * Demo localizator, which only supports 1 (English) localization file (statically defined).
 * @author Timmos
 */
public class DemoLocalizator implements Localizator {

    private static final String RESOURCE_FILE = "demo-%s.properties";

    private final LanguageKey       langKey;
    private final String        langAbbr;
    private final List<LanguageKey> supported;

    private Properties          properties;


    public DemoLocalizator(LanguageKey key, LanguageKey[] supported, LanguageKey[] ... others) {
        this.langKey = key;
        this.langAbbr = key.getExternalIdentifier();
        this.supported = new ArrayList<>();
        this.supported.addAll(Arrays.asList(supported));
        for (LanguageKey[] supp : others) {
            this.supported.addAll(Arrays.asList(supp));
        }

        String file = String.format(RESOURCE_FILE, langAbbr);

        InputStream stream = null;
        try {
            stream = App.getResourceManager().load(DefaultResourceCategoryKey.LOCALIZATION_FILE, file);
        } catch (IOException ex) {
            Log.error(DemoErrorMsg.RESOURCE_LOCALIZATION_LOAD, file);
        }
        if (stream != null) {
            Properties tmp = new Properties();
            try {
                tmp.load(stream);
            } catch (IOException ex) {
                Log.error(DemoErrorMsg.RESOURCE_LOCALIZATION_LOAD, file);
                tmp = null;
            }
            try {
                stream.close();
            } catch (IOException ex) {
                Log.error(ErrorMsg.RESOURCE_CLOSE_FAIL, file);
            }
            this.properties = tmp;
        } else {
            this.properties = null;
        }
    }

    @Override
    public String get(LocKey key) {
        return get(key, (Object[]) null);
    }

    @Override
    public String get(LocKey key, Object ... args) {
        if (langKey == DemoLanguageKey.VIKING) {
            return "JAR HAR HARRR!";
        }

        if (properties != null) {
            String firstpass = properties.getProperty(key.getExternalIdentifier());
            if (firstpass == null) {
                return "[ UNTRANSLATED - " + key.getExternalIdentifier() + " ]";
            }
            String secondpass= null;
            try {
                secondpass = String.format(firstpass, args);
            } catch (IllegalArgumentException iae) {
                secondpass = "[ FORMAT ERROR - " + key.getExternalIdentifier() + " ]";
            }
            return secondpass;
        }
        return "[ NOPROPS (" + langAbbr + ") - " + key.getExternalIdentifier() + " ]";
    }

    @Override
    public List<LanguageKey> getSupportedLanguages() {
        return Collections.unmodifiableList(supported);
    }

}
