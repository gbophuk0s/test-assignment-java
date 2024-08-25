package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    List<T> findAll(Connection connection) throws SQLException;

    T create(Connection connection, T spec) throws SQLException;

    T getById(Connection connection, ID id) throws SQLException;

    Optional<T> findById(Connection connection, ID id) throws SQLException;

    T update(Connection connection, ID id, T spec) throws SQLException;

    void deleteById(Connection connection, ID id) throws SQLException;

}
