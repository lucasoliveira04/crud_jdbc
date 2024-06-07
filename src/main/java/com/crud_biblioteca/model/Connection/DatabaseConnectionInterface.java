package com.crud_biblioteca.model.Connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionInterface {
    Connection getConnection() throws SQLException;
}
