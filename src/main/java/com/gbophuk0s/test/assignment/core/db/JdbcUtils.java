package com.gbophuk0s.test.assignment.core.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class JdbcUtils {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUtils.class);

    public static Connection openConnection(DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new IllegalStateException("Unable to obtain Jdbc connection from DataSource");
            }
            return connection;
        }
        catch (SQLException e) {
            throw new IllegalStateException("Unable to obtain Jdbc connection from DataSource", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        }
        catch (SQLException e) {
            LOGGER.error("Error while closing Jdbc connection", e);
        }
    }

    private JdbcUtils() {
    }

}
