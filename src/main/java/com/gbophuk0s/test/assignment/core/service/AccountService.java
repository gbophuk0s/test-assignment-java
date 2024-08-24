package com.gbophuk0s.test.assignment.core.service;

import java.util.Optional;

import com.gbophuk0s.test.assignment.core.model.Account;

public interface AccountService {

    Account create(Account accountSpec);

    Account getById(String id);

    Optional<Account> findById(String id);

    Account update(String id, Account accountSpec);

    void deleteById(String id);

}
