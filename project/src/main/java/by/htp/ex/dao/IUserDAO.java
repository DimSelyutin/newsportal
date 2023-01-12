package by.htp.ex.dao;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;

public interface IUserDAO {
    boolean register(User newUser) throws DaoException;
    boolean signIn(String login, String password) throws DaoException, ConnectionPoolException, SQLException;
    void signOut();

    String getRole(int id) throws DaoException;
    User getUser(String user) throws DaoException;
    List<User> findAllUser() throws ConnectionPoolException, SQLException;
    User findUserById(String id) throws SQLException, ConnectionPoolException;

}
