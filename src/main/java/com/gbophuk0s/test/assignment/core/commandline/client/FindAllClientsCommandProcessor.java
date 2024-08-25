package com.gbophuk0s.test.assignment.core.commandline.client;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.service.ClientService;

public class FindAllClientsCommandProcessor extends AbstractClientCommandProcessor {

    public FindAllClientsCommandProcessor(ClientService clientService) {
        super(clientService);
    }

    @Override
    public void process(Map<String, String> values) {
        clientService.findAll().forEach(it -> {
            logger.info("Client: {}", it);
        });
    }

}
