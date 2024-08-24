package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.gbophuk0s.test.assignment.core.ObjectNotFoundException;
import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.Currency;

public class AccountRepositoryImpl implements CrudRepository<Account> {

    @Override
    public Account create(Connection connection, Account spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "INSERT INTO account (id, bank_id, client_id, name, currency) " +
            "VALUES (?, ?, ?, ?, ?)"
        );

        UUID newId = UUID.randomUUID();

        statement.setObject(1, newId);
        statement.setObject(2, UUID.fromString(spec.getBankId()));
        statement.setObject(3, UUID.fromString(spec.getClientId()));
        statement.setString(4, spec.getName());
        statement.setString(5, spec.getCurrency().getCode());

        statement.execute();

        return getById(connection, newId.toString());
    }

    @Override
    public Account getById(Connection connection, String id) throws SQLException {
        return findById(connection, id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Requested account not found: %s", id)));
    }

    @Override
    public Optional<Account> findById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");

        statement.setObject(1, UUID.fromString(id));
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        Account result = new Account();

        result.setId(resultSet.getString("id"));
        result.setBankId(resultSet.getObject("bank_id").toString());
        result.setClientId(resultSet.getObject("client_id").toString());
        result.setName(resultSet.getString("name"));
        result.setCurrency(Currency.create(resultSet.getString("currency")));

        return Optional.of(result);
    }

    @Override
    public Account update(Connection connection, String id, Account spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "UPDATE account " +
            "SET name = ?, currency = ?" +
            "WHERE id = ?"
        );

        Account persisted = getById(connection, id);

        statement.setString(1, Objects.requireNonNullElse(spec.getName(), persisted.getName()));
        statement.setString(2, Objects.requireNonNullElse(spec.getCurrency(), persisted.getCurrency()).getCode());
        statement.setObject(3, UUID.fromString(id));

        statement.execute();

        return getById(connection, id);
    }

    @Override
    public void deleteById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM account " + "WHERE id = ?");

        statement.setObject(1, UUID.fromString(id));

        statement.execute();
    }

}
