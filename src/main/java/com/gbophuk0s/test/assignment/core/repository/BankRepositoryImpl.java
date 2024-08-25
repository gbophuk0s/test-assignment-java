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
import com.gbophuk0s.test.assignment.core.model.Bank;

public class BankRepositoryImpl implements BankRepository {

    @Override
    public List<Bank> findAll(Connection connection) throws SQLException {
        String sql = "" +
            "SELECT * FROM bank";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Bank> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(convert(resultSet));
            }

            return result;
        }
    }

    @Override
    public Bank create(Connection connection, Bank spec) throws SQLException {
        String sql = "" +
            "INSERT INTO bank (id, name, legal_entity_charge, individual_charge) " +
            "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            UUID newId = UUID.randomUUID();

            statement.setObject(1, newId);
            statement.setString(2, spec.getName());
            statement.setDouble(3, spec.getLegalEntityCharge());
            statement.setDouble(4, spec.getIndividualCharge());

            statement.execute();

            return getById(connection, newId.toString());
        }
    }

    @Override
    public Bank getById(Connection connection, String id) throws SQLException {
        return findById(connection, id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Requested bank not found: %s", id)));
    }

    @Override
    public Optional<Bank> findById(Connection connection, String id) throws SQLException {
        String sql = "SELECT * FROM bank WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.fromString(id));
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(convert(resultSet));
        }
    }

    @Override
    public Bank update(Connection connection, String id, Bank spec) throws SQLException {
        String sql = "" +
            "UPDATE bank " +
            "SET name = ?, legal_entity_charge = ?, individual_charge = ?" +
            "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Bank persisted = getById(connection, id);

            statement.setString(1, Objects.requireNonNullElse(spec.getName(), persisted.getName()));
            statement.setDouble(2, Objects.requireNonNullElse(spec.getLegalEntityCharge(), persisted.getLegalEntityCharge()));
            statement.setDouble(3, Objects.requireNonNullElse(spec.getIndividualCharge(), persisted.getIndividualCharge()));
            statement.setObject(4, UUID.fromString(id));

            statement.execute();

            return getById(connection, id);
        }
    }

    @Override
    public void deleteById(Connection connection, String id) throws SQLException {
        String sql = "DELETE FROM bank " + "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.fromString(id));

            statement.execute();
        }
    }

    private Bank convert(ResultSet resultSet) throws SQLException {
        Bank bank = new Bank();

        bank.setId(resultSet.getString("id"));
        bank.setName(resultSet.getString("name"));
        bank.setLegalEntityCharge(resultSet.getDouble("legal_entity_charge"));
        bank.setIndividualCharge(resultSet.getDouble("individual_charge"));

        return bank;
    }

}
