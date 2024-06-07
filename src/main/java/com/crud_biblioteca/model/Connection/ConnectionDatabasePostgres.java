package com.crud_biblioteca.model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabasePostgres implements DatabaseConnectionInterface{
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/biblioteca";
            String user = "postgres";
            String password = "senha12345";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
