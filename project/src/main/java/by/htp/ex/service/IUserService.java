package by.htp.ex.service;

import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;

public interface IUserService {
    String signin(String login, String password) throws DaoException, ConnectionPoolException, SQLException;
    boolean registration(User newUser) throws DaoException, ConnectionPoolException, SQLException;
    User findUserById(String id) throws SQLException, ConnectionPoolException ;
    int getUserId(String login) throws ConnectionPoolException, SQLException;
}
