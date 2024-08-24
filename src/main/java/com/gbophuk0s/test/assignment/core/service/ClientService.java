package com.gbophuk0s.test.assignment.core.service;

import java.util.Optional;

import com.gbophuk0s.test.assignment.core.model.Client;

public interface ClientService {

    Client create(Client clientSpec);

    Client getById(String id);

    Optional<Client> findById(String id);

    Client update(String id, Client clientSpec);

    void deleteById(String id);

}
