package com.gbophuk0s.test.assignment.core.service;

public interface CrudService<T> {

    T create(T objectSpec);

    T getById(String id);

    T update(String id, T objectSpec);

    void delete(String id);

}
