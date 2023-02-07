package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.dao.connectionpool.PoolConnection;

import java.sql.Connection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements IUserDAO {
    private final BCryptVersion bCryptVersion = BCryptVersion.$2A;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bCryptVersion);
    private final String SQL_INSERT_USER = "INSERT INTO `user` (`login`, `password`, `phone`, `email`,`date_register`) VALUES ('%s','%s','%s','%s','%s');";
    private final String SQL_SELECT_LOGIN_PASS = "SELECT * FROM user WHERE `login` = '%s'";
    private final String SQL_SELECT_LOGIN = "SELECT * FROM user WHERE `login`='%s'";
    private final String SQL_SELECT_ID_USER = "SELECT * FROM user where `id`='%s'";
    private final String SQL_SELECT_ROLE = "SELECT * FROM role_user, role WHERE role.id = role_user.role and `user_id`='%s';";
    private final String SQL_SELECT_ALL = "SELECT * FROM user";
    private final String SQL_INSERT_ROLE = "INSERT INTO `role_user` (`role`, `user_id`) VALUES ('2', '%s')";
    private final PoolConnection pool = ConnectionDAO.getConnectionPool();

    public UserDAO(){
    }
    @Override
    public boolean register(User newUser) throws DaoException {
        Connection con = null;
        Statement st = null;
        try {
            con = pool.takeConnection();
            String formatSqlInsert = String.format(SQL_INSERT_USER,
                    newUser.getLogin(),
                    newUser.getPassword(),
                    newUser.getPhone(),
                    newUser.getEmail(),
                    newUser.getDateRegister());

            st = con.prepareStatement(formatSqlInsert);
            if(st.executeUpdate(formatSqlInsert)>0){   
                return setRole(getUser(newUser.getLogin()).getId()+"");
            }

            return false;

         } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to register new user!", e);

        } finally {
            pool.closeConnection(con, st);
        }

    }

    private boolean setRole(String id) throws DaoException {
        Connection con = null;
        Statement st = null;
        try {
            con = pool.takeConnection();
            String sqlLoginRole = String.format(SQL_INSERT_ROLE, id);
            st = con.prepareStatement(sqlLoginRole);
            if (st.executeUpdate(sqlLoginRole)>0) {
                return true;
            }
            return false;
         } catch (ConnectionPoolException | SQLException e) {
            
            throw new DaoException("Error to set new role! Whrite to admin!", e);

        } finally {
            pool.closeConnection(con, st);
        }
    }

    @Override
    public boolean signIn(String login, String password) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            con = pool.takeConnection();
            String sqlLoginPass = String.format(SQL_SELECT_LOGIN_PASS, login);
            boolean ret = false;
            st = con.prepareStatement(sqlLoginPass);
            rs = st.executeQuery(sqlLoginPass);

            if (rs.next()) {
                if (rs.getString(2).equals(login) && passwordEncoder.matches(password, rs.getString(3))) {
                    ret = true;
                }
            }
            return ret;
         } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to signin!", e);

        } finally {
            pool.closeConnection(con, st, rs);
        }

    }

    @Override
    public void signOut() {

    }

    @Override
    public User getUser(String login) throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            String sqlLogin = String.format(SQL_SELECT_LOGIN, login);
            User user = null;

            st = con.prepareStatement(sqlLogin);
            rs = st.executeQuery(sqlLogin);
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6));
            }
            return user;
         } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error get user", e);

        } finally {
            pool.closeConnection(con, st, rs);
        }
    }

    @Override
    public String getRole(int id) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        String sqlUser = String.format(SQL_SELECT_ROLE, id);
        try {
            con = pool.takeConnection();
            String role = "guest";

            st = con.prepareStatement(sqlUser);
            rs = st.executeQuery(sqlUser);
            if (rs.next()) {
                role = rs.getString(5);
            }
            return role;
         } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to get role!", e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
    }

    @Override
    public List<User> findAllUser() throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            con = pool.takeConnection();
            List<User> users = new ArrayList<>();
            st = con.prepareStatement(SQL_SELECT_ALL);
            rs = st.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6)));
            }
            return users;
         } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to find all users!", e);
        } finally {
            pool.closeConnection(con, st, rs);
        }

    }

    @Override
    public User findUserById(String id) throws DaoException {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            con = pool.takeConnection();
            String sqlAllNews = String.format(SQL_SELECT_ID_USER, id);
            st = con.prepareStatement(sqlAllNews);
            rs = st.executeQuery(sqlAllNews);
            User user = null;
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(3),
                        rs.getString(9));
            }
            return user;
         } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error find user!");
        } finally {
            pool.closeConnection(con, st, rs);
        }
    }

}
