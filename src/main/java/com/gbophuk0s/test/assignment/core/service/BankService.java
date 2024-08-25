package com.gbophuk0s.test.assignment.core.service;

import java.math.BigDecimal;

import com.gbophuk0s.test.assignment.core.model.Bank;

public interface BankService extends CrudService<Bank> {

    void transfer(String fromAccountId, String toAccountId, BigDecimal amount);

}
