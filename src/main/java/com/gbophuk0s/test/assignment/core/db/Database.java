package com.gbophuk0s.test.assignment.core.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Database {

    private static final Logger LOGGER = LogManager.getLogger(Database.class);

    private final DataSource dataSource;

    public Database() {
        this.dataSource = new DataSourceImpl(
            DatabaseConfig.DRIVER_CLASS,
            DatabaseConfig.URL,
            DatabaseConfig.USERNAME,
            DatabaseConfig.PASSWORD
        );
    }

    public void init() {
        createTables();
        createConstraints();
    }

    private void createTables() {
        execute(DatabaseConfig.CREATE_TABLES_SCRIPT_LOCATION);
    }

    private void createConstraints() {
        execute(DatabaseConfig.CREATE_CONSTRAINTS_SCRIPT_LOCATION);
    }

    private void execute(String scriptLocation) {
        List<String> sqlCommands = parseScript(scriptLocation);

        Connection connection = JdbcUtils.openConnection(dataSource);

        try {
            Statement statement = connection.createStatement();

            for (String sqlCommand : sqlCommands) {
                statement.addBatch(sqlCommand);
            }

            statement.executeBatch();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtils.closeConnection(connection);
        }
    }

    private List<String> parseScript(String scriptLocation) {
        List<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getAbsolutePath(scriptLocation)))) {
            StringBuilder statement = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                statement.append(line).append("\n");

                if (line.endsWith(";")) {
                    String parsedStatement = statement.toString();
                    result.add(parsedStatement);
                    LOGGER.debug("Parsed statement: \n{}", parsedStatement);
                    statement.setLength(0);
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private String getAbsolutePath(String scriptLocation) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String result = classLoader.getResource(scriptLocation).getFile();

        if (result == null || result.length() == 0) {
            throw new IllegalArgumentException(String.format("Script not found: %s", scriptLocation));
        }

        return result;
    }
}
