package com.crud_biblioteca.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;

public interface TableCrationInterface {
    void createTable(Connection conn) throws SQLException;
}
