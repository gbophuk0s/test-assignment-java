package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.gbophuk0s.test.assignment.core.ObjectNotFoundException;
import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.Currency;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Account create(Connection connection, Account spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "INSERT INTO account (id, bank_id, client_id, name, currency, balance) " +
            "VALUES (?, ?, ?, ?, ?, ?)"
        );

        UUID newId = UUID.randomUUID();

        statement.setObject(1, newId);
        statement.setObject(2, UUID.fromString(spec.getBankId()));
        statement.setObject(3, UUID.fromString(spec.getClientId()));
        statement.setString(4, spec.getName());
        statement.setString(5, spec.getCurrency().getCode());
        statement.setBigDecimal(6, spec.getBalance());

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
        PreparedStatement statement = connection.prepareStatement("" +
            "SELECT * FROM account " +
            "WHERE id = ?"
        );

        statement.setObject(1, UUID.fromString(id));

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        Account result = new Account();

        result.setId(resultSet.getString("id"));
        result.setBankId(resultSet.getObject("bank_id").toString());
        result.setClientId(resultSet.getObject("client_id").toString());
        result.setCurrency(Currency.create(resultSet.getString("currency")));
        result.setName(resultSet.getString("name"));
        result.setBalance(resultSet.getBigDecimal("balance"));

        return Optional.of(result);
    }

    @Override
    public Account update(Connection connection, String id, Account spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "UPDATE account " +
            "SET name = ?, currency = ?, balance = ?" +
            "WHERE id = ?"
        );

        Account persisted = getById(connection, id);

        statement.setString(1, Objects.requireNonNullElse(spec.getName(), persisted.getName()));
        statement.setString(2, Objects.requireNonNullElse(spec.getCurrency(), persisted.getCurrency()).getCode());
        statement.setBigDecimal(3, Objects.requireNonNullElse(spec.getBalance(), persisted.getBalance()));
        statement.setObject(4, UUID.fromString(id));

        statement.execute();

        return getById(connection, id);
    }

    @Override
    public void deleteById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "DELETE FROM account " +
            "WHERE id = ?"
        );

        statement.setObject(1, UUID.fromString(id));

        statement.execute();
    }

    @Override
    public List<Account> findAllByClientId(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "SELECT * FROM account " +
            "WHERE client_id = ?"
        );

        statement.setObject(1, UUID.fromString(id));

        ResultSet resultSet = statement.executeQuery();

        List<Account> result = new ArrayList<>();
        while (resultSet.next()) {
            Account account = new Account();

            account.setId(resultSet.getString("id"));
            account.setBankId(resultSet.getObject("bank_id").toString());
            account.setClientId(resultSet.getObject("client_id").toString());
            account.setCurrency(Currency.create(resultSet.getString("currency")));
            account.setName(resultSet.getString("name"));
            account.setBalance(resultSet.getBigDecimal("balance"));

            result.add(account);
        }

        return result;
    }

}
