
package by.htp.ex.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ex.dao.connectionpool.ConnectionPoolException;
public interface IConnectionDAO {

    void initConnection() throws SQLException, ConnectionPoolException;
    Connection getConnection();
    void closeConnection();
    void closeConnection(Connection con, Statement st);
    void closeConnection(Connection con, Statement st, ResultSet rs);
    
}