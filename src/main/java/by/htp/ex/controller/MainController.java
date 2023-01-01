package by.htp.ex.controller;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CommandProvider commandProvider = new CommandProvider();

    public MainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String commandStr = req.getParameter("command");
            System.out.println("Command: "+commandStr);
            Command command = commandProvider.getCommand(commandStr); // ComandProvider return Comand in
                                                                      // method(getCommand)from String
            command.execute(req, resp);
        } catch (DaoException | ConnectionPoolException | SQLException | ServiceException e) {
            
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
