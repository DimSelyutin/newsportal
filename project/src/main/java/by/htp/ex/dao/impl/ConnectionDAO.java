package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ex.dao.IConnectionDAO;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.dao.connectionpool.PoolConnection;

public class ConnectionDAO implements IConnectionDAO{

    private PoolConnection poolConnection;
    private Connection connection;


    
    public ConnectionDAO() {
        this.poolConnection = new PoolConnection();
        try {
            initConnection();
            
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        try {
            connection = poolConnection.takeConnection();
        } catch (ConnectionPoolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void initConnection() throws SQLException, ConnectionPoolException {
        poolConnection.initPoolData();
    }


    @Override
    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        
            poolConnection.closeConnection(connection, st, rs);
        
       
        
    }
    

    

    @Override
    public void closeConnection() {
        
        
    }

    @Override
    public void closeConnection(Connection con, Statement st) {
        poolConnection.closeConnection(con, st);
        
    }

    



    
    
}
