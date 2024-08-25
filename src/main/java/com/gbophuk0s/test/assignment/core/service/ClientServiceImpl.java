package com.gbophuk0s.test.assignment.core.service;

import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.repository.ClientRepository;
import com.gbophuk0s.test.assignment.core.repository.ClientRepositoryImpl;

public class ClientServiceImpl extends AbstractService implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        super();

        this.clientRepository = new ClientRepositoryImpl();
    }

    @Override
    public Client create(Client clientSpec) {
        return newTransaction(connection -> {
            return clientRepository.create(connection, clientSpec);
        });
    }

    @Override
    public Client getById(String id) {
        return newTransaction(connection -> {
            return clientRepository.getById(connection, id);
        });
    }

    @Override
    public Client update(String id, Client clientSpec) {
        return newTransaction(connection -> {
            return clientRepository.update(connection, id, clientSpec);
        });
    }

    @Override
    public void delete(String id) {
        newTransaction(connection -> {
            clientRepository.deleteById(connection, id);

            return null;
        });
    }

}
