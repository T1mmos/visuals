package gent.timdemey.visuals.core.cfg;

import java.util.List;

public interface Configuration {

    public boolean setValue(String raw, String value);

    public String getValue(String raw);

    public List<String> getKeys();


    /**
     * Checks whether keys can be written into this configuration.
     * @return {@code true} when this configuration is writable, {@code false} otherwise
     */
    public boolean isWritable ();

    public void load ();

    public void save () throws ConfigurationStreamException;
}
