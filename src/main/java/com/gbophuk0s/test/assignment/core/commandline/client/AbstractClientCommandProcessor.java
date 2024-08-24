package com.gbophuk0s.test.assignment.core.commandline.client;

import com.gbophuk0s.test.assignment.core.commandline.AbstractCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.service.ClientService;

public abstract class AbstractClientCommandProcessor extends AbstractCommandProcessor {

    protected static final String ID_PARAMETER = "id";
    protected static final String NAME_PARAMETER = "name";
    protected static final String TYPE_PARAMETER = "type";

    private static final int MAX_NAME_LENGTH = 25;

    protected final ClientService clientService;

    protected AbstractClientCommandProcessor(ClientService clientService) {
        this.clientService = clientService;
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

    protected Client.Type processType(String type) {
        return processType(type, true);
    }

    protected Client.Type processType(String value, boolean required) {
        if (value == null && required) {
            throw new DataValidationException("Type is required");
        }

        if (value == null) {
            return null;
        }

        return Client.Type.valueOf(value);
    }

}
