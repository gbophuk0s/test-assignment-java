package com.gbophuk0s.test.assignment.core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gbophuk0s.test.assignment.support.Args;

public class TransactionTemplate {

    private static final Logger LOGGER = LogManager.getLogger(TransactionTemplate.class);

    public TransactionTemplate() {
    }

    public <T> T execute(Connection connection, ConnectionCallback<T> callback) {
        boolean oldAutoCommit = true;
        Savepoint savepoint = null;
        try {
            oldAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            LOGGER.debug("Creating a savepoint...");
            savepoint = connection.setSavepoint(Thread.currentThread().getName());

            T result = callback.doInConnection(connection);

            LOGGER.debug("Committing transaction...");
            connection.commit();
            LOGGER.debug("Transaction committed");

            return result;
        }
        catch (Exception e) {
            try {
                LOGGER.debug("Rolling back transaction...");

                if (savepoint == null) {
                    connection.rollback();
                }
                else {
                    connection.rollback(savepoint);
                }

                LOGGER.debug("Transaction rolled back");
            }
            catch (SQLException ex) {
                LOGGER.error("Unable to rollback transaction", ex);
            }

            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.setAutoCommit(oldAutoCommit);
            }
            catch (SQLException e) {
                LOGGER.error("Unable to restore autocommit to original value for connection", e);
            }
        }
    }

}
