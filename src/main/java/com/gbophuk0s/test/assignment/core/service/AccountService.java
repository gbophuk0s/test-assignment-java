package com.gbophuk0s.test.assignment.core.service;

import java.util.List;

import com.gbophuk0s.test.assignment.core.model.Account;

public interface AccountService extends CrudService<Account> {

    List<Account> findAllByClientId(String clientId);

}
