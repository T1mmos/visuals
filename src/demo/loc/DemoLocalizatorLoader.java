package demo.loc;

import core.cfg.DefaultConfigKey;
import core.kernel.DefaultAppBuilder;
import core.kernel.LoadingFailedException;
import core.loc.DefaultLanguageKey;
import core.loc.DefaultLocalizatorLoader;
import core.loc.LanguageKey;

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
