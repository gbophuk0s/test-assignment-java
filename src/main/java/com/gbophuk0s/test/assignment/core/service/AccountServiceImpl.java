package com.gbophuk0s.test.assignment.core.service;

import java.sql.Connection;
import java.util.Optional;

import com.gbophuk0s.test.assignment.core.db.ConnectionCallback;
import com.gbophuk0s.test.assignment.core.db.Database;
import com.gbophuk0s.test.assignment.core.db.TransactionTemplate;
import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.repository.AccountRepositoryImpl;
import com.gbophuk0s.test.assignment.core.repository.CrudRepository;

public class AccountServiceImpl implements AccountService {

    private final TransactionTemplate transactionTemplate;

    private final CrudRepository<Account> accountRepository;

    public AccountServiceImpl() {
        this.transactionTemplate = new TransactionTemplate();
        this.accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public Account create(Account accountSpec) {
        return executeWithinTransaction(connection -> {
            return accountRepository.create(connection, accountSpec);
        });
    }

    @Override
    public Account getById(String id) {
        return executeWithinTransaction(connection -> {
            return accountRepository.getById(connection, id);
        });
    }

    @Override
    public Optional<Account> findById(String id) {
        return executeWithinTransaction(connection -> {
            return accountRepository.findById(connection, id);
        });
    }

    @Override
    public Account update(String id, Account accountSpec) {
        return executeWithinTransaction(connection -> {
            return accountRepository.update(connection, id, accountSpec);
        });
    }

    @Override
    public void deleteById(String id) {
        executeWithinTransaction(connection -> {
            accountRepository.deleteById(connection, id);

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
