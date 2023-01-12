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
    private final String COMMAND = "command";
    private final String EXCEPTION = "exception";


    

    
    private static final long serialVersionUID = 1L;

    private final CommandProvider commandProvider = new CommandProvider();

    public MainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    private void doAction(HttpServletRequest req, HttpServletResponse resp){
        Command command = null;
        
        String commandStr = req.getParameter(COMMAND);
        if(commandStr == null){
            commandStr = CommandName.GO_TO_MAIN_PAGE.toString();
        }


        try {
            
            System.out.println(CommandName.GO_TO_MAIN_PAGE.toString()+": " + commandStr);
            try {
            
            if (!commandStr.toUpperCase().equals(CommandName.CHANGE_LOCAL.toString())) {
                req.getSession(true).setAttribute("link", "controller?" + req.getQueryString());
            } 
            
                command = commandProvider.getCommand(commandStr);
            } catch (ServiceException e) {
                command = new GoToMainPage();
                req.getSession(true).setAttribute(EXCEPTION, "Page not found. Chek your data.");
            }

            command.execute(req, resp);

        } catch(RuntimeException runEx) {
            try {
                resp.sendRedirect("/WEB-INF/pages/layouts/404.html");
            } catch (IOException e) {
                //IO EXx
                e.printStackTrace();
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            //IO Ex
        } 
    }

}
