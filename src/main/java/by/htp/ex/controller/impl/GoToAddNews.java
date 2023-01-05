package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                if (request.getSession().getAttribute("role").equals("user") || request.getSession().getAttribute("role").equals("admin") ) {
                    request.setAttribute("presentation", "addNews");
                    request.setAttribute("link", "/WEB-INF/pages/tiles/addNews.jsp");
                    
                    request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        
                } else {
                    
                    response.sendRedirect("controller?command=go_to_news");
        
                }
        
    }
    
}
