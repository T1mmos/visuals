package core.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import core.log.Debuggable;
import core.log.elements.DebugBuilder;
import core.log.elements.DebugElement;

public class FileConfigurationStream implements ConfigurationStream, Debuggable {

    private final File file;
    private final boolean writable;

    public FileConfigurationStream(File file, boolean writable) {
        this.file = file;
        this.writable = writable;
    }

    @Override
    public void read(Configuration config) throws ConfigurationStreamException {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(file));
        } catch (IOException ex) {
            throw new ConfigurationStreamException(ex);
        }

        for (String key : props.stringPropertyNames()) {
            config.setValue(key, props.getProperty(key));
        }
    }


    @Override
    public void write(Configuration config) throws ConfigurationStreamException {
        if (!writable) {
            throw new UnsupportedOperationException("This file configuration stream was initialized unwritable for file: " + file);
        }
        List<String> keys = config.getKeys();

        Properties props = new Properties();
        for (String key : keys) {
            String value = config.getValue(key);
            props.put(key, value);
        }
        OutputStream os;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            throw new ConfigurationStreamException(ex);
        }
        try {
            props.store(os, "Stored by FileConfigurationStream");
        } catch (IOException ex) {
            throw new ConfigurationStreamException(ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                // TODO just log this one - cannot recover or upthrow.
            }
        }
    }

    @Override
    public boolean isWritable() {
        return writable;
    }

    @Override
    public String toString() {
        return FileConfigurationStream.class.getSimpleName() + "::[file=" + file.getAbsolutePath() + ",writable="
                        + writable + "]";
    }

    @Override
    public DebugElement getDebugInfo() {
        DebugBuilder builder = new DebugBuilder();

        builder.add("type", "File");
        builder.add("file", file.getAbsolutePath());

        return builder.build();
    }
}
