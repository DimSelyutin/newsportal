package by.htp.ex.dao.connectionPool;

import java.io.IOException;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

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

    // public static void writeLog(String textLog) {
    //     FileHandler fh;

    //     try {
           
    //         // This block configure the logger with handler and formatter
            
    //         fh = new FileHandler("../logs/webapplogs/ExceptionLog.log");
    //         logger.addHandler(fh);
    //         SimpleFormatter formatter = new SimpleFormatter();
    //         fh.setFormatter(formatter);

    //         // the following statement is used to log any messages
    //         logger.info(textLog);

    //     } catch (SecurityException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     logger.info("Hi How r u?");

    // }
}
