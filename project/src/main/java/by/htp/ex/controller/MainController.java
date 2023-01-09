package by.htp.ex.controller;

import java.io.IOException;
import by.htp.ex.controller.impl.GoToMainPage;

import by.htp.ex.dao.connectionPool.Logger;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Command command = null;
        
        String commandStr = req.getParameter("command");
        if(commandStr == null){
            commandStr = "GO_TO_MAIN_PAGE";
        }


        try {
            
            System.out.println("Command: " + commandStr);
            try {
            
            if (!commandStr.toUpperCase().equals("CHANGE_LOCAL")) {
                req.getSession(true).setAttribute("link", "controller?" + req.getQueryString());
            } 
            
                command = commandProvider.getCommand(commandStr);
            } catch (ServiceException e) {
                command = new GoToMainPage();
                req.getSession().setAttribute("exception", "Page not found. Chek your data.");
            }

            command.execute(req, resp);

        } catch (ServletException | IOException e) {
            e.printStackTrace();
            

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
