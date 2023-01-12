package by.htp.ex.service;



import by.htp.ex.bean.User;


public interface IUserService {
    String signin(String login, String password) throws ServiceException;
    boolean registration(User newUser) throws ServiceException;
    User findUserById(String id) throws ServiceException;
    int getUserId(String login) throws ServiceException;
}
