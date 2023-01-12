package by.htp.ex.service.impl;

import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDAO();
    private final String ROLE_GUEST = "guest";

    @Override
    public boolean registration(User newUser) throws ServiceException {
        try {
            return userDAO.register(newUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public String signin(String login, String password) throws ServiceException {
        try {
            String role;
            int _id = getUserId(login);
            System.out.println(_id);
            if (userDAO.signIn(login, password)) {
                System.out.println(userDAO.getRole(_id));
                return role = userDAO.getRole(_id);
            } else {
                return role = ROLE_GUEST;
            }
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public User findUserById(String id) throws ServiceException {
        User user;
        try {
            return user = userDAO.findUserById(id);
        } catch (ConnectionPoolException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getUserId(String login) throws ServiceException {
        try {
            User user;
            user = userDAO.getUser(login);
            return user.getId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
