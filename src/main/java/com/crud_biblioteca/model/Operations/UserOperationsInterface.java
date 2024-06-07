package com.crud_biblioteca.model.Operations;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserOperationsInterface {
    void insertData(Connection conn) throws SQLException;
    void updateData(Connection conn) throws SQLException;
    void deleteData(Connection conn) throws SQLException;
    void selectData(Connection conn) throws SQLException;
}
