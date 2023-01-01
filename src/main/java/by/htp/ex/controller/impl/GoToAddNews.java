package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            DaoException, ConnectionPoolException, SQLException, ServiceException {
                if (request.getSession().getAttribute("role").equals("user") || request.getSession().getAttribute("role").equals("admin") ) {
                    request.setAttribute("presentation", "addNews");
                    request.setAttribute("link", "/WEB-INF/pages/tiles/addNews.jsp");
                    
                    request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        
                } else {
                    
                    response.sendRedirect("controller?command=go_to_news");
        
                }
        
    }
    
}
