package gent.timdemey.visuals.demo.loc;

import gent.timdemey.visuals.core.cfg.DefaultConfigKey;
import gent.timdemey.visuals.core.kernel.DefaultAppBuilder;
import gent.timdemey.visuals.core.kernel.LoadingFailedException;
import gent.timdemey.visuals.core.loc.DefaultLanguageKey;
import gent.timdemey.visuals.core.loc.DefaultLocalizatorLoader;
import gent.timdemey.visuals.core.loc.LanguageKey;

/**
 * Attached the {@link DemoLocalizator} to the application.
 * @author Timmos
 */
public class DemoLocalizatorLoader extends DefaultLocalizatorLoader {

    @Override
    public void load(DefaultAppBuilder builder) throws LoadingFailedException {
        LanguageKey language = DefaultConfigKey.LANGUAGE.get();
        builder.setLocalizator(new DemoLocalizator(language, DefaultLanguageKey.values(), DemoLanguageKey.values()));
    }
}
