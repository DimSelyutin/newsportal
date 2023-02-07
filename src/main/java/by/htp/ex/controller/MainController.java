package by.htp.ex.controller;

import java.io.IOException;
import by.htp.ex.service.ServiceException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MainController extends HttpServlet {
    private final String COMMAND = "command";
    private final String EXCEPTION = "exception";

    private final CommandProvider commandProvider = new CommandProvider();

    public MainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doAction(req, resp);
    }


    private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Command command = null;
        String commandStr = req.getParameter(COMMAND);
        try {
            command = commandProvider.getCommand(commandStr);
            command.execute(req, resp);
        } catch ( RuntimeException | ServiceException | ServletException | IOException e) {
            req.setAttribute(EXCEPTION, "Page not found. Pls chek you link!");
        } 

    }


}
