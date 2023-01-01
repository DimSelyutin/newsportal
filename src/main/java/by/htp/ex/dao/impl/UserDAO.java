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

public class UserDAO implements IUserDAO{
    
    
    @Override
    public void register(User newUser) throws DaoException, ConnectionPoolException, SQLException{
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlUser = String.format("INSERT INTO `user` (`login`, `password`, `phone`, `email`,`date_register`) VALUES ('%s','%s','%s','%s','%s');", newUser.getLogin(), newUser.getPassword(),newUser.getPhone(),newUser.getEmail(),newUser.getDateRegister());

        con.prepareStatement(sqlUser).execute();
        
    }

    @Override
    public boolean signIn(String login, String password) throws DaoException, ConnectionPoolException, SQLException  {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlUser = String.format("SELECT * FROM user WHERE (`login`, `password`) = ('%s','%s')", login, password);
        boolean ret = false;
        PreparedStatement preparedStatement = con.prepareStatement(sqlUser);
        ResultSet rs = preparedStatement.executeQuery();
       
        while (rs.next()) {
            if(rs.getString(2).equals(login) && rs.getString(3).equals(password)) {
                ret = true;
            } else {  
                ret = false;
            }
        }
        return ret;
 
    }

    @Override
    public void signOut() {
        
        
    }

    @Override
    public User getUser(String login) throws ConnectionPoolException, SQLException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlUser = "SELECT * FROM user WHERE `login`='"+login+"'";
        User user = null;
        ResultSet rs = con.prepareStatement(sqlUser).executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
        }
        return user;
    }


    @Override
    public String getRole(int id) throws ConnectionPoolException, SQLException{

        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        
        String sqlUser = "SELECT * FROM role_user WHERE `user_id`='"+id+"'";            
        String role = "guest";
        ResultSet rs = con.prepareStatement(sqlUser).executeQuery();
        if (rs.next()) {
            int role_id = rs.getInt(2);
            String sqlUserRole = "SELECT * FROM role WHERE `id`='"+role_id+"'";                     
            rs = null;
            rs = con.prepareStatement(sqlUserRole).executeQuery();
            while (rs.next()) {

                if (rs.getInt(1) == role_id){
                    role = rs.getString(2);
                }

            }
        }
            return role;
    }

    
    
    @Override
    public List<User> findAllUser() throws ConnectionPoolException, SQLException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlUser = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        ResultSet rs = con.prepareStatement(sqlUser).executeQuery();
        while (rs.next()) {
            users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
        }
        return users;
        
        
    }

    @Override
    public User findUserById(String id) throws SQLException, ConnectionPoolException {
        Connection con = DaoProvider.getInstance().getConnectionDAO().getConnection();
        String sqlAllNews = "SELECT * FROM user where `id`='"+id+"'";
        ResultSet rs = con.createStatement().executeQuery(sqlAllNews);
        User user = null;
        while(rs.next()){
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5),rs.getString(3), rs.getString(9));
        }
        return user;
    }


    
}
