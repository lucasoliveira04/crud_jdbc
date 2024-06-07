package com.crud_biblioteca.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables implements TableCrationInterface{

    @Override
    public void createTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS users (%s, %s, %s, %s, %s, %s)",
                    "id SERIAL PRIMARY KEY",
                    "nome VARCHAR(255)",
                    "cpf VARCHAR(14)",
                    "email VARCHAR(255)",
                    "telefone VARCHAR(20)",
                    "endereco VARCHAR(255)");
            stmt.execute(sql);
            System.out.println("Table created with success!");
        }
    }
}


