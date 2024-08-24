package com.gbophuk0s.test.assignment.core.db;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionCallback<T> {

    T doInConnection(Connection connection) throws SQLException;

}
