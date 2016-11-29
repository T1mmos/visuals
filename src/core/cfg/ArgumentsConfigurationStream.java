package core.cfg;

import core.log.Debuggable;
import core.log.elements.DebugBuilder;
import core.log.elements.DebugElement;

/**
 * A configuration stream that takes the program arguments (a {@code String[]}) according to the format {@code key=value}.
 * <p>This configuration stream takes a {@code String[]} as input and splits each string entry on the string {@code "="}.
 * The key-value pair is then used as an entry in a {@link Configuration}. If an entry can't be splitted, or the entry
 * contains more than 2 substrings, the entry is ignored.
 * @author Timmos
 */
public class ArgumentsConfigurationStream implements ConfigurationStream, Debuggable {

    private final String [] args;

    /**
     * Creates a new argument configuration stream, using the given String array.
     * @param args the program arguments as passed to the application's main method
     */
    public ArgumentsConfigurationStream (String[] args) {
        this.args = args;
    }

    @Override
    public void read(Configuration config) throws ConfigurationStreamException {
        for (String str : args) {
            String[] splits = str.split("=", 2);
            if (splits.length == 2) {
                String key = splits[0].trim();
                String value = splits[1].trim();
                if (!key.isEmpty() && !value.isEmpty()) {
                    config.setValue(splits[0], splits[1]);
                }
            }
        }
    }

    @Override
    public void write(Configuration config) throws ConfigurationStreamException {
        throw new ConfigurationStreamException("Cannot write into program arguments!");
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public DebugElement getDebugInfo() {
        DebugBuilder builder = new DebugBuilder();

        builder.add("type", "arguments");

        return builder.build();
    }
}
