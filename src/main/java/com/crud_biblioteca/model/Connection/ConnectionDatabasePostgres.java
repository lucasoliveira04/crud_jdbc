package com.crud_biblioteca.model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabasePostgres implements DatabaseConnectionInterface{
    @Override
    public Connection getConnection() throws SQLException {
        // Variável para armazenar a conexão com o banco de dados
        Connection connection = null;

        try{
            // Carrega o driver JDBC do PostgreSQL
            Class.forName("org.postgresql.Driver");

            // URL de conexão com o banco de dados PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/biblioteca";

            // Credenciais de acesso ao banco de dados
            String user = "postgres";
            String password = "senha12345";

            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            // Captura e imprime qualquer exceção lançada durante o carregamento do driver JDBC
            e.printStackTrace();
        }

        // Retorna a conexão estabelecida
        return connection;
    }
}
