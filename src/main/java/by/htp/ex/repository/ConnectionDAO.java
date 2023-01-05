package by.htp.ex.repository;

import java.sql.Connection;
import java.sql.SQLException;

import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.dao.connectionPool.PoolConnection;

public class ConnectionDAO implements IConnectionDAO{

    private PoolConnection poolConnection;
    private Connection connection;

    
    public PoolConnection getPoolConnection(){
        return poolConnection;
    }

    
    public ConnectionDAO() {
        this.poolConnection = new PoolConnection();
        try {
            initConnection();
            
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void initConnection() throws ConnectionPoolException, SQLException {
        poolConnection.initPoolData();
        connection = poolConnection.takeConnection();
        


    }


    @Override
    public void closeConnection() {
        System.out.println("CloseConnect");
        poolConnection.dispose();
        
    }
    
}
