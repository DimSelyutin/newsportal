package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import by.htp.ex.dao.IConnectionDAO;
import by.htp.ex.dao.connection.ConnectionPoolException;
import by.htp.ex.dao.connection.PoolConnection;

public class ConnectionDAO implements IConnectionDAO{

    private PoolConnection poolConnection = new PoolConnection();

    @Override
    public Connection getConnection() throws ConnectionPoolException, SQLException {
        poolConnection.initPoolData();

        Connection con = poolConnection.takeConnection();

        return con;
    }


    @Override
    public void closeConnection() {
        poolConnection.dispose();
        
    }
    
}
