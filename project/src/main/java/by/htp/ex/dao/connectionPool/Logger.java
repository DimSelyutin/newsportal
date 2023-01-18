package by.htp.ex.dao.connectionpool;


import java.lang.System.Logger.Level;
import java.sql.SQLException;


public class Logger {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("ExceptionLog");


    public Logger() {
    }

    public void log(Level lvl, String str, SQLException e) {
        System.out.println(lvl + ", " + str + ", " + e);
    }

    public void log(Level lvl, String str) {
        System.out.println(lvl + ", " + str);
    }

}
