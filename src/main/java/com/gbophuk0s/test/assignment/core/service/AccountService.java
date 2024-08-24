package com.gbophuk0s.test.assignment.core.service;

import java.util.Optional;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.AccountCompoundId;

public interface AccountService {

    Account create(Account accountSpec);

    Account getById(AccountCompoundId id);

    Optional<Account> findById(AccountCompoundId id);

    Account update(AccountCompoundId id, Account accountSpec);

    void deleteById(AccountCompoundId id);

}
