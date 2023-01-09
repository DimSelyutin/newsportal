package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getSession().getAttribute("local") == null) {
            request.getSession().setAttribute("local", request.getLocale());
        } else {
            request.getSession().setAttribute("local", request.getParameter("local"));
        }
        String str = (String)request.getSession().getAttribute("link");
        
        response.sendRedirect(str);
        
    }
    
}
