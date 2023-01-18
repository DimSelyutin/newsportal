
package by.htp.ex.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IConnectionDAO {

    Connection getConnection() throws DaoException;

    void closeConnection(Connection con, Statement st);

    void closeConnection(Connection con, Statement st, ResultSet rs);

}