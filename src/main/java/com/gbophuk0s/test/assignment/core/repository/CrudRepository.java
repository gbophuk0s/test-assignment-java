package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface CrudRepository<T> {

    T create(Connection connection, T spec) throws SQLException;

    T getById(Connection connection, String id) throws SQLException;

    Optional<T> findById(Connection connection, String id) throws SQLException;

    T update(Connection connection, String id, T spec) throws SQLException;

    void deleteById(Connection connection, String id) throws SQLException;

}
