package gent.timdemey.visuals.core.cfg;


/**
 * Abstracts the source that a {@link Configuration} needs to load or save itself.
 * @author Timmos
 */
public interface ConfigurationStream {
    
    /**
     * Reads the source and initializes the given raw configuration with the
     * found keys.
     * @param config the raw configuration to write the found key-value pairs in
     * @throws ConfigurationStreamException when reading from the source fails
     */
    public void read (Configuration config) throws ConfigurationStreamException;
    
    /**
     * Persists the given raw configuration.
     * @param config the raw configuration to read key-value pairs from
     * @throws ConfigurationStreamException when writing to the source fails
     */
    public void write (Configuration config) throws ConfigurationStreamException;
    
    /**
     * Checks whether the underlying source can be written to. (some sources can
     * be read-only, e.g. program arguments).
     * @return {@code true} when the underlying source is writable, {@code false} otherwise
     */
    public boolean isWritable ();
}
