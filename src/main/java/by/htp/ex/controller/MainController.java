package by.htp.ex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {


    
    private static final long serialVersionUID = 1L;

    private final CommandProvider commandProvider = new CommandProvider();


    public MainController(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String commandStr = req.getParameter("command");
        Command command = commandProvider.getCommand(commandStr);   //ComandProvider return Comand in method(getCommand)from String          
        try {
            command.execute(req, resp);
        } catch (DaoException | ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }                                 //redirect on command-page
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }













































  
}
