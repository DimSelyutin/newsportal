package by.htp.ex.repository;


import java.sql.Connection;
import java.sql.SQLException;

import by.htp.ex.dao.connectionPool.ConnectionPoolException;
public interface IConnectionDAO {

    void initConnection() throws SQLException, ConnectionPoolException;
    Connection getConnection();
    void closeConnection();
    
}
