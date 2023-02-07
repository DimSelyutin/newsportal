package by.htp.ex.service.impl;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserValidation;
import by.htp.ex.util.validation.UserValidation.ValidationBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;

import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDAO();
    private final String ROLE_GUEST = "guest";
    private UserValidation.ValidationBuilder valid;
    private final BCryptVersion bCryptVersion = BCryptVersion.$2A;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bCryptVersion);

    @Override
    public boolean registration(User newUser) throws ServiceException {
        try {

            if (userDAO.getUser(newUser.getLogin()) != null) {
                throw new ServiceException("User is already registered!");
            }

            valid = new ValidationBuilder(newUser);
            valid.validEmail().validLogin().validPassword().validPhone();
            if (!valid.uncorrectFieldName.isEmpty()) {
                throw new ServiceException(valid.uncorrectFieldName.toString());
            }
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return userDAO.register(newUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public String signin(String login, String password) throws ServiceException {
        try {
            String role = ROLE_GUEST;
            int _id = getUserId(login);

            if (userDAO.signIn(login, password)) {
                return role = userDAO.getRole(_id);
            }

            return role;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public User findUserById(String id) throws ServiceException {
    
        try {
            return userDAO.findUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getUserId(String login) throws ServiceException {
        User user = null;
        try {
            user = userDAO.getUser(login);
            if (user == null) {
                throw new ServiceException("User not found!");
            }
            return user.getId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
