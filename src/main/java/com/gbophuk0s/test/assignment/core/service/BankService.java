package com.gbophuk0s.test.assignment.core.service;

import java.util.Optional;

import com.gbophuk0s.test.assignment.core.model.Bank;

public interface BankService {

    Bank create(Bank bankSpec);

    Bank getById(String id);

    Optional<Bank> findById(String id);

    Bank update(String id, Bank bankSpec);

    void deleteById(String id);

}
