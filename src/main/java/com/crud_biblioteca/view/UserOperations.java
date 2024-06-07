package com.crud_biblioteca.view;

import com.crud_biblioteca.model.Operations.UserOperationsInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations implements UserOperationsInterface {
    @Override
    public void insertData(Connection conn) throws SQLException {
        String nome = "lucas";
        String cpf = "123.456.789-00";
        String email = "lucas@gmail.com";
        String telefone = "(00) 1234-5678";
        String endereco = "Rua ABC, 123";

        String sql = "INSERT INTO users (nome, cpf, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, endereco);

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        }
    }

    @Override
    public void updateData(Connection conn) throws SQLException {
        long idDoUsuarioQueDesejaAtualizar = 1; // ID fornecido pelo usuário
        String sqlExistenceCheck = "SELECT id FROM users WHERE id > ? ORDER BY id ASC LIMIT 1";
        String sqlUpdate = "UPDATE users SET nome = ?, cpf = ?, email = ?, telefone = ?, endereco = ? WHERE id = ?";

        try (PreparedStatement existenceStmt = conn.prepareStatement(sqlExistenceCheck)) {
            existenceStmt.setLong(1, idDoUsuarioQueDesejaAtualizar);
            try (ResultSet existenceRs = existenceStmt.executeQuery()) {
                if (existenceRs.next()) {
                    long idExistente = existenceRs.getLong("id");

                    String nomeNovo = "lucas Novo";
                    String cpfNovo = "123.456.789-11";
                    String emailNovo = "lucas@gmail";
                    String telefoneNovo = "(00) 1234-5621";
                    String enderecoNovo = "Rua ABC, 233";

                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                        updateStmt.setString(1, nomeNovo);
                        updateStmt.setString(2, cpfNovo);
                        updateStmt.setString(3, emailNovo);
                        updateStmt.setString(4, telefoneNovo);
                        updateStmt.setString(5, enderecoNovo);
                        updateStmt.setLong(6, idExistente);

                        int rowAffected = updateStmt.executeUpdate();
                        if (rowAffected > 0) {
                            System.out.println("Dados alterados com sucesso");
                        } else {
                            System.out.println("Nenhum registro atualizado");
                        }
                    }
                } else {
                    System.out.println("Não há registros com ID maior que " + idDoUsuarioQueDesejaAtualizar);
                }
            }
        }
    }


    @Override
    public void deleteData(Connection conn) throws SQLException {
        long idDoUsuarioQueDesejaExcluir = 1;
        String sqlExistenceCheck = "SELECT id FROM users WHERE id > ? ORDER BY id ASC LIMIT 1";
        String sqlDelete = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement existenceStmt = conn.prepareStatement(sqlExistenceCheck)) {
            existenceStmt.setLong(1, idDoUsuarioQueDesejaExcluir);
            try (ResultSet existenceRs = existenceStmt.executeQuery()) {
                if (existenceRs.next()) {
                    long idExistente = existenceRs.getLong("id");
                    try (PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete)) {
                        deleteStmt.setLong(1, idExistente);

                        int rowAffected = deleteStmt.executeUpdate();
                        if (rowAffected > 0) {
                            System.out.println("Dados deletados com sucesso");
                        } else {
                            System.out.println("Nenhum registro deletado");
                        }
                    }
                } else {
                    System.out.println("Não há registros com ID maior que " + idDoUsuarioQueDesejaExcluir);
                }
            }
        }
    }


    @Override
    public void selectData(Connection conn) throws SQLException {
        String sql = "SELECT * FROM users";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                   for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                       String columnName = rs.getMetaData().getColumnName(i);
                       String value = rs.getString(i);
                       System.out.println(columnName + ": " + value);
                   }
                    System.out.println();
                }
            }
        }
    }
}
