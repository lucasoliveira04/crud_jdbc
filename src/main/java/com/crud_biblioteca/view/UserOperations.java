package com.crud_biblioteca.view;

import com.crud_biblioteca.model.Operations.UserOperationsInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations implements UserOperationsInterface {
    // Método para inserir dados de usuário
    @Override
    public void insertData(Connection conn) throws SQLException {
        // Dados do usuário a serem inseridos
        String nome = "lucas";
        String cpf = "123.456.789-00";
        String email = "lucas@gmail.com";
        String telefone = "(00) 1234-5678";
        String endereco = "Rua ABC, 123";

        // Consulta SQL para inserção de dados
        String sql = "INSERT INTO users (nome, cpf, email, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Define os parâmetros da consulta SQL com os valores do usuário
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            stmt.setString(5, endereco);

            // Executa a consulta SQL para inserção
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        }
    }

    // Método para atualizar dados do usuário
    @Override
    public void updateData(Connection conn) throws SQLException {
        // ID do usuário que deseja atualizar (pode ser fornecido pelo usuário)
        long idDoUsuarioQueDesejaAtualizar = 1;

        // Consulta SQL para verificar a existência do usuário a ser atualizado
        String sqlExistenceCheck = "SELECT id FROM users WHERE id > ? ORDER BY id ASC LIMIT 1";

        // Consulta SQL para atualização dos dados do usuário
        String sqlUpdate = "UPDATE users SET nome = ?, cpf = ?, email = ?, telefone = ?, endereco = ? WHERE id = ?";

        try (PreparedStatement existenceStmt = conn.prepareStatement(sqlExistenceCheck)) {
            existenceStmt.setLong(1, idDoUsuarioQueDesejaAtualizar);
            try (ResultSet existenceRs = existenceStmt.executeQuery()) {
                if (existenceRs.next()) {
                    // Se o usuário existir, obtém seu ID
                    long idExistente = existenceRs.getLong("id");

                    // Novos dados do usuário
                    String nomeNovo = "lucas Novo";
                    String cpfNovo = "123.456.789-11";
                    String emailNovo = "lucas@gmail";
                    String telefoneNovo = "(00) 1234-5621";
                    String enderecoNovo = "Rua ABC, 233";

                    // Executa a consulta SQL para atualização dos dados do usuário
                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate)) {
                        updateStmt.setString(1, nomeNovo);
                        updateStmt.setString(2, cpfNovo);
                        updateStmt.setString(3, emailNovo);
                        updateStmt.setString(4, telefoneNovo);
                        updateStmt.setString(5, enderecoNovo);
                        updateStmt.setLong(6, idExistente);

                        // Executa a consulta SQL de atualização
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

    // Método para excluir dados do usuário
    @Override
    public void deleteData(Connection conn) throws SQLException {
        // ID do usuário que deseja excluir
        long idDoUsuarioQueDesejaExcluir = 1;

        // Consulta SQL para verificar a existência do usuário a ser excluído
        String sqlExistenceCheck = "SELECT id FROM users WHERE id > ? ORDER BY id ASC LIMIT 1";

        // Consulta SQL para exclusão dos dados do usuário
        String sqlDelete = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement existenceStmt = conn.prepareStatement(sqlExistenceCheck)) {
            existenceStmt.setLong(1, idDoUsuarioQueDesejaExcluir);
            try (ResultSet existenceRs = existenceStmt.executeQuery()) {
                if (existenceRs.next()) {
                    // Se o usuário existir, obtém seu ID
                    long idExistente = existenceRs.getLong("id");
                    // Executa a consulta SQL para exclusão dos dados do usuário
                    try (PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete)) {
                        deleteStmt.setLong(1, idExistente);

                        // Executa a consulta SQL de exclusão
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

    // Método para selecionar e exibir todos os dados dos usuários
    @Override
    public void selectData(Connection conn) throws SQLException {
        // Consulta SQL para selecionar todos os dados dos usuários
        String sql = "SELECT * FROM users";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            try (ResultSet rs = stmt.executeQuery()){
                // Itera sobre o resultado da consulta e exibe os dados de cada usuário
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
