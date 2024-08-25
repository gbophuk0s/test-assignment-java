package com.gbophuk0s.test.assignment.core.commandline.client;

import com.gbophuk0s.test.assignment.core.commandline.AbstractCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.service.ClientService;

public abstract class AbstractClientCommandProcessor extends AbstractCommandProcessor {

    protected static final String TYPE_PARAMETER = "type";

    protected final ClientService clientService;

    protected AbstractClientCommandProcessor(ClientService clientService) {
        super();

        this.clientService = clientService;
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
