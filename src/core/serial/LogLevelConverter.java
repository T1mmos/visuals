package core.serial;

import core.log.LogLevel;


public class LogLevelConverter implements Converter<LogLevel, String> {

    @Override
    public Class<LogLevel> getInternalClass() {
        return LogLevel.class;
    }

    @Override
    public Class<String> getExternalClass() {
        return String.class;
    }

    @Override
    public String externalize(LogLevel value) {
        return "" + value.ordinal();
    }

    @Override
    public LogLevel internalize(String s) throws ConversionException {
        int ordinal = 0;
        try {
            ordinal = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            throw new ConversionException(String.class, LogLevel.class, e);
        }

        if (!(0 <= ordinal && ordinal < LogLevel.values().length)) {
            throw new ConversionException(String.class, LogLevel.class);
        }

        return LogLevel.values()[ordinal];
    }

}
