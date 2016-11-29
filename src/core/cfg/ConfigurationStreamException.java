package core.cfg;

public class ConfigurationStreamException extends Exception {
    
    public ConfigurationStreamException (String msg) {
        super(msg);
    }
    
    public ConfigurationStreamException (Throwable cause) {
        super(cause);
    }
}
