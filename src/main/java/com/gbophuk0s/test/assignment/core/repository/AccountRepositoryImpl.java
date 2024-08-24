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
import com.gbophuk0s.test.assignment.core.model.AccountCompoundId;
import com.gbophuk0s.test.assignment.core.model.Currency;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Account create(Connection connection, Account spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "INSERT INTO account (bank_id, client_id, name, currency) " +
            "VALUES (?, ?, ?, ?)"
        );

        statement.setObject(1, UUID.fromString(spec.getBankId()));
        statement.setObject(2, UUID.fromString(spec.getClientId()));
        statement.setString(3, spec.getName());
        statement.setString(4, spec.getCurrency().getCode());

        statement.execute();

        return getById(connection, spec.getId());
    }

    @Override
    public Account getById(Connection connection, AccountCompoundId id) throws SQLException {
        return findById(connection, id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Requested account not found: %s", id)));
    }

    @Override
    public Optional<Account> findById(Connection connection, AccountCompoundId id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "SELECT * FROM account " +
            "WHERE bank_id = ? AND client_id = ? AND currency = ?"
        );

        statement.setObject(1, UUID.fromString(id.getBankId()));
        statement.setObject(2, UUID.fromString(id.getClientId()));
        statement.setObject(3, id.getCurrency().getCode());
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        Account result = new Account();

        result.setBankId(resultSet.getObject("bank_id").toString());
        result.setClientId(resultSet.getObject("client_id").toString());
        result.setName(resultSet.getString("name"));
        result.setCurrency(Currency.create(resultSet.getString("currency")));

        return Optional.of(result);
    }

    @Override
    public Account update(Connection connection, AccountCompoundId id, Account spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "UPDATE account " +
            "SET name = ?, currency = ?" +
            "WHERE bank_id = ? AND client_id = ? AND currency = ?"
        );

        Account persisted = getById(connection, id);

        statement.setString(1, Objects.requireNonNullElse(spec.getName(), persisted.getName()));
        statement.setString(2, Objects.requireNonNullElse(spec.getCurrency(), persisted.getCurrency()).getCode());
        statement.setObject(3, UUID.fromString(id.getBankId()));
        statement.setObject(4, UUID.fromString(id.getClientId()));
        statement.setObject(5, id.getCurrency().getCode());

        statement.execute();

        return getById(connection, id);
    }

    @Override
    public void deleteById(Connection connection, AccountCompoundId id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "DELETE FROM account " +
            "WHERE bank_id = ? AND client_id = ? AND currency = ?"
        );

        statement.setObject(1, UUID.fromString(id.getBankId()));
        statement.setObject(2, UUID.fromString(id.getClientId()));
        statement.setObject(3, id.getCurrency().getCode());

        statement.execute();
    }

}
