package com.gbophuk0s.test.assignment.core.service;

import java.util.List;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.repository.AccountRepository;
import com.gbophuk0s.test.assignment.core.repository.AccountRepositoryImpl;

public class AccountServiceImpl extends AbstractService implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        super();

        this.accountRepository = new AccountRepositoryImpl();
    }

    @Override
    public Account create(Account accountSpec) {
        return newTransaction(connection -> {
            return accountRepository.create(connection, accountSpec);
        });
    }

    @Override
    public Account getById(String id) {
        return newTransaction(connection -> {
            return accountRepository.getById(connection, id);
        });
    }

    @Override
    public Account update(String id, Account accountSpec) {
        return newTransaction(connection -> {
            return accountRepository.update(connection, id, accountSpec);
        });
    }

    @Override
    public void delete(String id) {
        newTransaction(connection -> {
            accountRepository.deleteById(connection, id);

            return null;
        });
    }

    @Override
    public List<Account> findAllByClientId(String clientId) {
        return newTransaction(connection -> {
            return accountRepository.findAllByClientId(connection, clientId);
        });
    }

}
