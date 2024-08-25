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
import com.gbophuk0s.test.assignment.core.model.Client;

public class ClientRepositoryImpl implements ClientRepository {

    @Override
    public List<Client> findAll(Connection connection) throws SQLException {
        String sql = "" +
            "SELECT * FROM client";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Client> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(convert(resultSet));
            }

            return result;
        }
    }

    @Override
    public Client create(Connection connection, Client spec) throws SQLException {
        String sql = "" +
            "INSERT INTO client (id, name, type) " +
            "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            UUID newId = UUID.randomUUID();

            statement.setObject(1, newId);
            statement.setString(2, spec.getName());
            statement.setString(3, spec.getType().name());

            statement.execute();

            return getById(connection, newId.toString());
        }
    }

    @Override
    public Client getById(Connection connection, String id) throws SQLException {
        return findById(connection, id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Requested client not found: %s", id)));
    }

    @Override
    public Optional<Client> findById(Connection connection, String id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";

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
    public Client update(Connection connection, String id, Client spec) throws SQLException {
        String sql = "" +
            "UPDATE client " +
            "SET name = ?, type = ?" +
            "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            Client persisted = getById(connection, id);

            statement.setString(1, Objects.requireNonNullElse(spec.getName(), persisted.getName()));
            statement.setString(2, Objects.requireNonNullElse(spec.getType(), persisted.getType()).name());
            statement.setObject(3, UUID.fromString(id));

            statement.execute();

            return getById(connection, id);
        }
    }

    @Override
    public void deleteById(Connection connection, String id) throws SQLException {
        String sql = "DELETE FROM client " + "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, UUID.fromString(id));

            statement.execute();
        }
    }

    private Client convert(ResultSet resultSet) throws SQLException {
        Client client = new Client();

        client.setId(resultSet.getString("id"));
        client.setName(resultSet.getString("name"));
        client.setType(Client.Type.valueOf(resultSet.getString("type")));

        return client;
    }

}
