package com.gbophuk0s.test.assignment.core.commandline.client;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.service.ClientService;

public class GetClientCommandProcessor extends AbstractClientCommandProcessor {

    public GetClientCommandProcessor(ClientService clientService) {
        super(clientService);
    }

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=uuid",
            ID_PARAMETER
        );
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(ID_PARAMETER));
        Client client = clientService.getById(id);
        logger.info("Retrieved: {}", client);
    }

}
