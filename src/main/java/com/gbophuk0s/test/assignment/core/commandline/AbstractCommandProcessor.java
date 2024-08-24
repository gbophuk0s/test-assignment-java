package com.gbophuk0s.test.assignment.core.commandline;

import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractCommandProcessor implements CommandProcessor {

    protected final Logger logger = LogManager.getLogger(getClass());

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

}
