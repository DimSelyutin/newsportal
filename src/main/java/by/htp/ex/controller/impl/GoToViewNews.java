package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews extends HttpServlet implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoException, ConnectionPoolException, SQLException {
                News news;
                String id;
                
                id = request.getParameter("id");
                

                request.getRequestDispatcher("/WEB-INF/pages/tiles/baselayout.jsp").forward(request, response);
        
    }
    
}
