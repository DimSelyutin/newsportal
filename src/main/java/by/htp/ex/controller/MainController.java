package by.htp.ex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CommandProvider commandProvider = new CommandProvider();

    public MainController() {
        super();
        System.out.println("1. Main-Controller-Constructor");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Main-controller!");
        String commandStr = req.getParameter("command");
        Command command = commandProvider.getCommand(commandStr); // ComandProvider return Comand in
                                                                 // method(getCommand)from String
        try {
            command.execute(req, resp);
        } catch (DaoException | ConnectionPoolException | SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Just error");
            e.printStackTrace();
        }
        

      
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
