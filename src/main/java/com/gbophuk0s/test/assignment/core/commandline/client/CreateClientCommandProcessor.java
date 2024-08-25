package com.gbophuk0s.test.assignment.core.commandline.client;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.service.ClientService;

public class CreateClientCommandProcessor extends AbstractClientCommandProcessor {

    public CreateClientCommandProcessor(ClientService clientService) {
        super(clientService);
    }

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=name --%s=[LEGAL_ENTITY|INDIVIDUAL]",
            NAME_PARAMETER,
            TYPE_PARAMETER
        );
    }

    @Override
    public void process(Map<String, String> values) {
        Client client = processValues(values);

        client = clientService.create(client);
        logger.info("Created: {}", client);
    }

    private Client processValues(Map<String, String> values) {
        Client result = new Client();

        result.setName(processName(values.get(NAME_PARAMETER)));
        result.setType(processType(values.get(TYPE_PARAMETER)));

        return result;
    }

}
