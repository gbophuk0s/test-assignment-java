package com.gbophuk0s.test.assignment.core.db;

class DatabaseConfig {

    public static final String DRIVER_CLASS = "org.postgresql.Driver";

    public static final String URL = "jdbc:postgresql://localhost:5432/surtapg";

    public static final String USERNAME = "surtapg";

    public static final String PASSWORD = "12345678";

    public static final String SCRIPTS_DIR = "db/";

    public static final String CREATE_TABLES_SCRIPT_LOCATION = SCRIPTS_DIR + "create_tables.sql";

    public static final String CREATE_CONSTRAINTS_SCRIPT_LOCATION = SCRIPTS_DIR + "create_constraints.sql";

    private DatabaseConfig() {
    }

}
