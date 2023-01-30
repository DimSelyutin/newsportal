package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command{
    private final String LOCALE = "local";
    private final String LINK = "link";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute(LOCALE, request.getParameter(LOCALE));
        
        String str = request.getSession().getAttribute(LINK).toString();
        response.sendRedirect(str);

    }
    
}
