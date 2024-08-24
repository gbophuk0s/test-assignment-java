package com.gbophuk0s.test.assignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gbophuk0s.test.assignment.core.db.Database;

public class TestAssignmentJavaApplication {

    private static final Logger LOGGER = LogManager.getLogger(TestAssignmentJavaApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Application started.");

        Database database = new Database();

        database.init();
    }

}
