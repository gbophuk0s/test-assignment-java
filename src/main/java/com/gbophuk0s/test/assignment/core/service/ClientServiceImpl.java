package com.gbophuk0s.test.assignment.core.service;

import java.sql.Connection;
import java.util.Optional;

import com.gbophuk0s.test.assignment.core.db.ConnectionCallback;
import com.gbophuk0s.test.assignment.core.db.Database;
import com.gbophuk0s.test.assignment.core.db.TransactionTemplate;
import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.repository.ClientRepository;
import com.gbophuk0s.test.assignment.core.repository.ClientRepositoryImpl;

public class ClientServiceImpl implements ClientService {

    private final TransactionTemplate transactionTemplate;

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.transactionTemplate = new TransactionTemplate();
        this.clientRepository = new ClientRepositoryImpl();
    }

    @Override
    public Client create(Client clientSpec) {
        return executeWithinTransaction(connection -> {
            return clientRepository.create(connection, clientSpec);
        });
    }

    @Override
    public Client getById(String id) {
        return executeWithinTransaction(connection -> {
            return clientRepository.getById(connection, id);
        });
    }

    @Override
    public Optional<Client> findById(String id) {
        return executeWithinTransaction(connection -> {
            return clientRepository.findById(connection, id);
        });
    }

    @Override
    public Client update(String id, Client clientSpec) {
        return executeWithinTransaction(connection -> {
            return clientRepository.update(connection, id, clientSpec);
        });
    }

    @Override
    public void deleteById(String id) {
        executeWithinTransaction(connection -> {
            clientRepository.deleteById(connection, id);

            return null;
        });
    }

    private <T> T executeWithinTransaction(ConnectionCallback<T> callback) {
        Connection connection = null;

        try {
            connection = Database.INSTANCE.openConnection();

            return transactionTemplate.execute(connection, callback);
        }
        finally {
            Database.INSTANCE.closeConnection(connection);
        }
    }
}
