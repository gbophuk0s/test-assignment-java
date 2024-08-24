package com.gbophuk0s.test.assignment.core.repository;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.AccountCompoundId;

public interface AccountRepository extends CrudRepository<Account, AccountCompoundId> {
}
