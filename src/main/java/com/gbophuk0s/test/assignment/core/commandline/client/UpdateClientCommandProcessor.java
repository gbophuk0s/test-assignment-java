package com.gbophuk0s.test.assignment.core.commandline.client;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.service.ClientService;

public class UpdateClientCommandProcessor extends AbstractClientCommandProcessor {

    public UpdateClientCommandProcessor(ClientService clientService) {
        super(clientService);
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(ID_PARAMETER));
        Client spec = processValues(values);

        Client client = clientService.update(id, spec);
        logger.info("Updated: {}", client);
    }

    private Client processValues(Map<String, String> values) {
        Client result = new Client();

        result.setName(processName(values.get(NAME_PARAMETER), false));
        result.setType(processType(values.get(TYPE_PARAMETER), false));

        return result;
    }

}
