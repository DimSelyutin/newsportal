package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
    private final String SQL_INSERT_USER = "INSERT INTO `user` (`login`, `password`, `phone`, `email`,`date_register`) VALUES ('%s','%s','%s','%s','%s');";
    private final String SQL_SELECT_LOGIN_PASS = "SELECT * FROM user WHERE (`login`, `password`) = ('%s','%s')";
    private final String SQL_SELECT_LOGIN = "SELECT * FROM user WHERE `login`='%s'";
    private final String SQL_SELECT_ID_USER = "SELECT * FROM user where `id`='%s'";
    private final String SQL_SELECT_ROLE = "SELECT * FROM role_user WHERE `user_id`='%s'";
    private final String SQL_SELECT_ALL = "SELECT * FROM user";
    private final String ERR_MSG = "Some problems with database. Please, try again";

    @Override
    public boolean register(User newUser) throws DaoException {
        try {

            Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String formatSqlInsert = String.format(SQL_INSERT_USER,
                    newUser.getLogin(),
                    newUser.getPassword(),
                    newUser.getPhone(),
                    newUser.getEmail(),
                    newUser.getDateRegister());

            return con.prepareStatement(formatSqlInsert).execute();
        } catch (SQLException e) {
            throw new DaoException(ERR_MSG, e);

        }

    }

    @Override
    public boolean signIn(String login, String password) throws DaoException {
        try {
            Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlLoginPass = String.format(SQL_SELECT_LOGIN_PASS, login, password);
            boolean ret = false;
            PreparedStatement preparedStatement = con.prepareStatement(sqlLoginPass);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                if (rs.getString(2).equals(login) && rs.getString(3).equals(password)) {
                    ret = true;
                }
            }
            return ret;
        } catch (SQLException e) {
            throw new DaoException(ERR_MSG, e);

        }

    }

    @Override
    public void signOut() {

    }

    @Override
    public User getUser(String login) throws DaoException {
        try{
            Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
            String sqlLogin = String.format(SQL_SELECT_LOGIN, login);
            User user = null;
            ResultSet rs = con.prepareStatement(sqlLogin).executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6));
            }
            return user;
        } catch(SQLException e){
            throw new DaoException(ERR_MSG, e);

        }
    }

    @Override
    public String getRole(int id) throws DaoException {

        try {
            Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();

        String sqlUser = String.format(SQL_SELECT_ROLE, id);
        String role = "guest";
        ResultSet rs = con.prepareStatement(sqlUser).executeQuery();
        if (rs.next()) {
            int role_id = rs.getInt(2);
            String sqlUserRole = "SELECT * FROM role WHERE `id`='" + role_id + "'";
            rs = null;
            rs = con.prepareStatement(sqlUserRole).executeQuery();
            while (rs.next()) {

                if (rs.getInt(1) == role_id) {
                    role = rs.getString(2);
                }

            }
        }
        return role;
        } catch (SQLException e) {
            throw new DaoException(ERR_MSG, e);
        }
    }

    @Override
    public List<User> findAllUser() throws ConnectionPoolException, SQLException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        List<User> users = new ArrayList<>();
        ResultSet rs = con.prepareStatement(SQL_SELECT_ALL).executeQuery();
        while (rs.next()) {
            users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6)));
        }
        return users;

    }

    @Override
    public User findUserById(String id) throws SQLException, ConnectionPoolException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlAllNews = String.format(SQL_SELECT_ID_USER, id);
        ResultSet rs = con.createStatement().executeQuery(sqlAllNews);
        User user = null;
        while (rs.next()) {
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(3),
                    rs.getString(9));
        }
        return user;
    }

}
