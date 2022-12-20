package by.htp.ex.dao;


import java.sql.Connection;
import java.sql.SQLException;

import by.htp.ex.dao.connection.ConnectionPoolException;
public interface IConnectionDAO {

    Connection getConnection() throws ConnectionPoolException, SQLException;

    void closeConnection();
    
}
