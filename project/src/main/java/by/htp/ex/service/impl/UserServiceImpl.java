package by.htp.ex.service.impl;

import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService{

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDAO();
    
    
    @Override
    public boolean registration(User newUser) throws DaoException, ConnectionPoolException, SQLException {
        userDAO.register(newUser);
        return true;
    }

    @Override
    public String signin(String login, String password) throws DaoException, ConnectionPoolException, SQLException {
        String role;
        int _id = getUserId(login);
        System.out.println(_id);
        if (userDAO.signIn(login, password)) {
            System.out.println(userDAO.getRole(_id));
            return role = userDAO.getRole(_id);
        } else {
            return role = "guest";
        }
    }

    @Override
    public User findUserById(String id) throws SQLException, ConnectionPoolException {
        User user = userDAO.findUserById(id);
        return user;
    }

    @Override
    public int getUserId(String login) throws ConnectionPoolException, SQLException {
        User user = userDAO.getUser(login);
        int id = user.getId();
        System.out.println(id+":"+login);
        return id;
    }

    

  
    
    
}
