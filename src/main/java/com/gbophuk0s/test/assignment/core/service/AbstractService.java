package com.gbophuk0s.test.assignment.core.service;

import java.sql.Connection;

import com.gbophuk0s.test.assignment.core.db.ConnectionCallback;
import com.gbophuk0s.test.assignment.core.db.Database;
import com.gbophuk0s.test.assignment.core.db.TransactionTemplate;

public abstract class AbstractService {

    private final TransactionTemplate transactionTemplate;

    public AbstractService() {
        this.transactionTemplate = new TransactionTemplate();
    }

    protected <I> I newTransaction(ConnectionCallback<I> callback) {
        Connection connection = null;

        try {
            connection = Database.INSTANCE.openConnection();

            return transactionTemplate.execute(connection, callback);
        }
        finally {
            Database.INSTANCE.closeConnection(connection);
        }
    }

}
