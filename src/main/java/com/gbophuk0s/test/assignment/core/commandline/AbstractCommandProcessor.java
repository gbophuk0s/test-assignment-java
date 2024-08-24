package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractCommandProcessor implements CommandProcessor {

    protected final Logger logger = LogManager.getLogger(getClass());

    private static final int MAX_NAME_LENGTH = 25;

    protected AbstractCommandProcessor() {
    }

    public abstract void process(Map<String, String> values);

    protected String processId(String value) {
        try {
            return UUID.fromString(value).toString();
        }
        catch (Exception e) {
            throw new DataValidationException(String.format("Invalid id: %s", value));
        }
    }

    protected String processName(String value) {
        return processName(value, true);
    }

    protected String processName(String value, boolean required) {
        if (value == null && required) {
            throw new DataValidationException("Name is required");
        }

        if (value == null) {
            return null;
        }

        if (value.length() > MAX_NAME_LENGTH) {
            throw new DataValidationException(String.format("Max length of name is %s", MAX_NAME_LENGTH));
        }

        return value;
    }

}
