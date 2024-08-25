package com.gbophuk0s.test.assignment.core.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import com.gbophuk0s.test.assignment.support.Args;

class DataSourceImpl implements DataSource {

    private final Driver driver;

    private final String url;

    private final String username;

    private final String password;

    DataSourceImpl(String driverClass, String url, String username, String password) {
        Args.checkNotNull(driverClass, "driverClass");
        Args.checkNotNull(url, "url");
        Args.checkNotNull(username, "username");
        Args.checkNotNull(password, "password");

        this.driver = createDriver(driverClass);
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnectionFromDriver(username, password);
    }

    private Driver createDriver(String driverClass) {
        try {
            Class<?> clazz = Class.forName(driverClass);
            return (Driver) clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new IllegalArgumentException(String.format("Unknown driver class: %s", driverClass));
        }
    }

    private Connection getConnectionFromDriver(String username, String password) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        return driver.connect(url, props);
    }

}
