package com.gbophuk0s.test.assignment.core.service;

import java.util.List;

public interface CrudService<T> {

    List<T> findAll();

    T create(T objectSpec);

    T getById(String id);

    T update(String id, T objectSpec);

    void delete(String id);

}
