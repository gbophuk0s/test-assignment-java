package com.gbophuk0s.test.assignment.core.service;

import java.sql.Connection;
import java.util.Optional;

import com.gbophuk0s.test.assignment.core.db.ConnectionCallback;
import com.gbophuk0s.test.assignment.core.db.Database;
import com.gbophuk0s.test.assignment.core.db.TransactionTemplate;
import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.repository.BankRepositoryImpl;
import com.gbophuk0s.test.assignment.core.repository.CrudRepository;

public class BankServiceImpl implements BankService {

    private final TransactionTemplate transactionTemplate;
    private final CrudRepository<Bank> bankRepository;

    public BankServiceImpl() {
        this.transactionTemplate = new TransactionTemplate();
        this.bankRepository = new BankRepositoryImpl();
    }

    @Override
    public Bank create(Bank bankSpec) {
        return executeWithinTransaction(connection -> {
            return bankRepository.create(connection, bankSpec);
        });
    }

    @Override
    public Bank getById(String id) {
        return executeWithinTransaction(connection -> {
            return bankRepository.getById(connection, id);
        });
    }

    @Override
    public Optional<Bank> findById(String id) {
        return executeWithinTransaction(connection -> {
            return bankRepository.findById(connection, id);
        });
    }

    @Override
    public Bank update(String id, Bank bankSpec) {
        return executeWithinTransaction(connection -> {
            return bankRepository.update(connection, id, bankSpec);
        });
    }

    @Override
    public void deleteById(String id) {
        executeWithinTransaction(connection -> {
            bankRepository.deleteById(connection, id);

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
