package com.gbophuk0s.test.assignment.core.db;

import java.sql.Connection;
import java.sql.SQLException;

interface DataSource {

    Connection getConnection() throws SQLException;

}
