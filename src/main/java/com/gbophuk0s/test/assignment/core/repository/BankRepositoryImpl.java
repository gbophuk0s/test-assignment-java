package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.gbophuk0s.test.assignment.core.ObjectNotFoundException;
import com.gbophuk0s.test.assignment.core.model.Bank;

public class BankRepositoryImpl implements CrudRepository<Bank> {

    @Override
    public Bank create(Connection connection, Bank spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "INSERT INTO bank (id, name, legal_entity_charge, individual_charge) " +
            "VALUES (?, ?, ?, ?)"
        );

        UUID newId = UUID.randomUUID();

        statement.setObject(1, newId);
        statement.setString(2, spec.getName());
        statement.setDouble(3, spec.getLegalEntityCharge());
        statement.setDouble(4, spec.getIndividualCharge());

        statement.execute();

        return getById(connection, newId.toString());
    }

    @Override
    public Bank getById(Connection connection, String id) throws SQLException {
        return findById(connection, id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Requested bank not found: %s", id)));
    }

    @Override
    public Optional<Bank> findById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank WHERE id = ?");

        statement.setObject(1, UUID.fromString(id));
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        Bank result = new Bank();

        result.setId(resultSet.getString("id"));
        result.setName(resultSet.getString("name"));
        result.setLegalEntityCharge(resultSet.getDouble("legal_entity_charge"));
        result.setIndividualCharge(resultSet.getDouble("individual_charge"));

        return Optional.of(result);
    }

    @Override
    public Bank update(Connection connection, String id, Bank spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "UPDATE bank " +
            "SET name = ?, legal_entity_charge = ?, individual_charge = ?" +
            "WHERE id = ?"
        );

        statement.setString(1, spec.getName());
        statement.setDouble(2, spec.getLegalEntityCharge());
        statement.setDouble(3, spec.getIndividualCharge());
        statement.setObject(4, UUID.fromString(id));

        statement.execute();

        return getById(connection, id);
    }

    @Override
    public void deleteById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM bank " + "WHERE id = ?");

        statement.setObject(1, UUID.fromString(id));

        statement.execute();
    }

}
