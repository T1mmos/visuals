package core.cfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.log.Debuggable;
import core.log.elements.DebugBuilder;
import core.log.elements.DebugElement;
import core.log.msg.ErrorMsg;
import core.log.msg.InfoMsg;
import core.util.Log;

/**
 * A configuration that is loaded from a persistent storage.
 * @author Timmos
 */
public class ConcreteConfiguration implements Configuration, Debuggable {

    private final Map<String, String> cfgMap;
    private final ConfigurationStream stream;

    /**
     * Creates a new concrete configuration that reads and writes its state
     * from and to the given configuration stream.
     * @param stream the abstracted persisted data source
     */
    public ConcreteConfiguration(ConfigurationStream stream) {
        this.stream = stream;
        this.cfgMap = new HashMap<>();
    }

    @Override
    public boolean isWritable() {
        return stream.isWritable();
    }

    @Override
    public void load() {
        try {
            stream.read(this);
        } catch (ConfigurationStreamException e) {
            Log.error(ErrorMsg.CONFIG_STREAM_READ_FAIL, stream);
        }
    }

    @Override
    public void save() throws ConfigurationStreamException {
        stream.write(this);
        Log.info(InfoMsg.CONFIG_PERSISTED, stream);
    }

    @Override
    public boolean setValue(String key, String value) {
        cfgMap.put(key, value);
        return true;
    }

    @Override
    public String getValue(String key) {
        return cfgMap.get(key);
    }

    @Override
    public List<String> getKeys() {
        return new ArrayList<String>(cfgMap.keySet());
    }

    @Override
    public DebugElement getDebugInfo() {
        DebugBuilder builder = new DebugBuilder();

        builder.add("type", "Concrete Configuration");
        builder.add("stream", stream);
        // String keyvals = "{" + Joiner.on(",").withKeyValueSeparator("=").join(cfgMap) + "}";
        // builder.add("entries", keyvals);

        return builder.build();
    }
}
