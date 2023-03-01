package com.example.propertyFromDb.repository.impl;

import com.example.propertyFromDb.domain.DatabaseConnection;
import com.example.propertyFromDb.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.Properties;

@RequiredArgsConstructor
public class PropertyRepositoryImpl implements PropertyRepository {

    private final DatabaseConnection databaseConnection;
    private static final String SQL_QUERY = "SELECT key, value FROM configuration WHERE service = ?";

    @Override
    public Properties getPropertiesFromDBByServiceName(String service) {
        var properties = new Properties();

        try {
            var connection = DriverManager.getConnection(databaseConnection.getUrl(), databaseConnection.getUsername(),
                    databaseConnection.getPassword());

            var preparedStatement = connection.prepareStatement(SQL_QUERY);
            preparedStatement.setString(1, service);

            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                properties.setProperty(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }
}
