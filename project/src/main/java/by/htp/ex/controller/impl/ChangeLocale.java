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
        
        if (request.getSession().getAttribute(LOCALE) == null) {
            request.getSession().setAttribute(LOCALE, request.getLocale());
        } else {
            request.getSession().setAttribute(LOCALE, request.getParameter(LOCALE));
        }
        String str = (String)request.getSession().getAttribute(LINK);
        
        response.sendRedirect(str);
        
    }
    
}
