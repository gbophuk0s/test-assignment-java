package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.gbophuk0s.test.assignment.core.ObjectNotFoundException;
import com.gbophuk0s.test.assignment.core.model.Client;

public class ClientRepositoryImpl implements CrudRepository<Client> {

    @Override
    public Client create(Connection connection, Client spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "INSERT INTO client (id, name, type) " +
            "VALUES (?, ?, ?)"
        );

        UUID newId = UUID.randomUUID();

        statement.setObject(1, newId);
        statement.setString(2, spec.getName());
        statement.setString(3, spec.getType().name());

        statement.execute();

        return getById(connection, newId.toString());
    }

    @Override
    public Client getById(Connection connection, String id) throws SQLException {
        return findById(connection, id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Requested client not found: %s", id)));
    }

    @Override
    public Optional<Client> findById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE id = ?");

        statement.setObject(1, UUID.fromString(id));
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return Optional.empty();
        }

        Client result = new Client();

        result.setId(resultSet.getString("id"));
        result.setName(resultSet.getString("name"));
        result.setType(Client.Type.valueOf(resultSet.getString("type")));

        return Optional.of(result);
    }

    @Override
    public Client update(Connection connection, String id, Client spec) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("" +
            "UPDATE client " +
            "SET name = ?, type = ?" +
            "WHERE id = ?"
        );

        Client persisted = getById(connection, id);

        statement.setString(1, Objects.requireNonNullElse(spec.getName(), persisted.getName()));
        statement.setString(2, Objects.requireNonNullElse(spec.getType(), persisted.getType()).name());
        statement.setObject(3, UUID.fromString(id));

        statement.execute();

        return getById(connection, id);
    }

    @Override
    public void deleteById(Connection connection, String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM client " + "WHERE id = ?");

        statement.setObject(1, UUID.fromString(id));

        statement.execute();
    }

}
