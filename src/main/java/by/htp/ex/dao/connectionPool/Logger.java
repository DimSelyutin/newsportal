package by.htp.ex.dao.connectionPool;


import java.lang.System.Logger.Level;
import java.sql.SQLException;




public class Logger {
    public Logger(){}
    public void log(Level lvl, String str, SQLException e){
        System.out.println(lvl +", "+str+", "+e);
    }

    public void log(Level lvl, String str){
        System.out.println(lvl +", "+str);
    }
}