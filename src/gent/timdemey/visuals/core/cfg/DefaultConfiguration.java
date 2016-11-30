package gent.timdemey.visuals.core.cfg;

import java.util.List;

import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.elements.DebugBuilder;
import gent.timdemey.visuals.core.log.elements.DebugElement;

/**
 * A configuration with no persistence, returning only default values.
 * @author Timmos
 */
public enum DefaultConfiguration implements Configuration, Debuggable {

    /**
     * The singleton instance.
     */
    INSTANCE;

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public void load() {
        // do nothing
    }

    @Override
    public void save() throws ConfigurationStreamException {
        DefaultConfiguration.throwUnsupported();
    }

    @Override
    public boolean setValue(String raw, String value) {
        DefaultConfiguration.throwUnsupported();
        return false;
    }

    @Override
    public List<String> getKeys() {
        DefaultConfiguration.throwUnsupported();
        return null;
    }

    private static void throwUnsupported (){
        throw new UnsupportedOperationException("Cannot write into the default configuration");
    }

    @Override
    public String getValue(String raw) {
        return null;
    }

    @Override
    public DebugElement getDebugInfo() {
        return new DebugBuilder().add("type", "Default Configuration").build();
    }
}
