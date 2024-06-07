package com.crud_biblioteca.controller;

import com.crud_biblioteca.model.Connection.ConnectionDatabasePostgres;
import com.crud_biblioteca.view.UserOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        UserOperations userOperations = new UserOperations();
        ConnectionDatabasePostgres connectionDatabasePostgres = new ConnectionDatabasePostgres();

        try (Connection conn = connectionDatabasePostgres.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            int option;

            do {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Inserir dados");
                System.out.println("2. Atualizar dados");
                System.out.println("3. Excluir dados");
                System.out.println("4. Selecionar dados");
                System.out.println("0. Sair");
                System.out.print("Opção: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        userOperations.insertData(conn);
                        break;
                    case 2:
                        userOperations.updateData(conn);
                        break;
                    case 3:
                        userOperations.deleteData(conn);
                        break;
                    case 4:
                        userOperations.selectData(conn);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha novamente.");
                        break;
                }
            } while (option != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
