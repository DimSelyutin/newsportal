package by.htp.ex.dao.impl;

import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.dao.connectionpool.PoolConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ConnectionDAO implements ServletContextListener {

    public static PoolConnection connectionPool = new PoolConnection();

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        connectionPool.dispose();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletContextListener#contextInitialized(jakarta.servlet.ServletContextEvent)
     * 
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            connectionPool.initPoolData();

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }

    public static PoolConnection getConnectionPool() {
        return connectionPool;
    }

}
