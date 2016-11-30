package gent.timdemey.visuals.core.cfg;

import java.util.Arrays;
import java.util.List;

import gent.timdemey.visuals.core.log.Debuggable;
import gent.timdemey.visuals.core.log.elements.DebugBuilder;
import gent.timdemey.visuals.core.log.elements.DebugElement;
import gent.timdemey.visuals.core.log.msg.TodoMsg;
import gent.timdemey.visuals.core.util.Log;

/**
 * A configuration implementation that delegates all requests to its delegates.
 * @author Timmos
 *
 */
public class CompositeConfiguration implements Configuration, Debuggable {

    private List<Configuration> delegates;

    /**
     * Creates a new composite configuration of the given type, delegating all
     * operations to the given delegates (in the order they are listed).
     * @param type the type of configuration keys this composite configuration is holding
     * @param delegates the underlying configurations that this configuration is composed of
     */
    public CompositeConfiguration(Configuration ... delegates) {
        this.delegates = Arrays.asList(delegates);
    }

    @Override
    public void load() {
        // constructor does the job
    }

    @Override
    public void save() throws ConfigurationStreamException {
        for (Configuration cfg : delegates){
            if (cfg.isWritable()){
                cfg.save();
            }
        }
    }

    @Override
    public boolean isWritable() {
        for (Configuration cfg : delegates) {
            if (cfg.isWritable()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setValue(String key, String value) {
        for (Configuration cfg : delegates) {
            if (cfg.isWritable()) {
                boolean success = cfg.setValue(key, value);
                if (success) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getKeys() {
        Log.todo(TodoMsg.UNSUPPORTED, "getKeys");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValue(String raw) {
        for (Configuration cfg : delegates) {
            String result = cfg.getValue(raw);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public DebugElement getDebugInfo() {
        DebugBuilder builder = new DebugBuilder();

        builder.add("type", "Composite Configuration");
        for (int i = 0; i < delegates.size(); i++) {
            Configuration cfg = delegates.get(i);
            builder.add("Config #" + i, cfg);
        }

        return builder.build();
    }

}
