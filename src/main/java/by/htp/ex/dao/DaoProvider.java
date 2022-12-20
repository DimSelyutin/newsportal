package by.htp.ex.dao;

import by.htp.ex.dao.impl.ConnectionDAO;
import by.htp.ex.dao.impl.NewsDAO;
import by.htp.ex.dao.impl.UserDAO;

public final class DaoProvider {
    
    private static final DaoProvider instance = new DaoProvider();
    
    private final INewsDAO newsDAO = new NewsDAO();

    private final IUserDAO userDAO = new UserDAO();

    private final IConnectionDAO connectionDAO = new ConnectionDAO();

    private DaoProvider(){}

    public IUserDAO getUserDAO() {
        return userDAO;
    }


    public INewsDAO getNewsDAO() {
        return newsDAO;
    }

    public IConnectionDAO getConnectionDAO(){
        return connectionDAO;
    }

    public static DaoProvider getInstance() {
        return instance;
    }




}
