package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.connection.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNews implements Command{

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> newsList = new ArrayList<>();
        try {
            try {
                newsList = newsService.latestList(5);
            } catch (ConnectionPoolException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            request.setAttribute("presentation", "newsList");
            request.setAttribute("news", newsList);
            System.out.println("GoToNews");
                
            } catch (ServiceException e) {
                System.out.println("Eroor goToNews");
                e.printStackTrace();
                
            }
            
            
            request.getRequestDispatcher("WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
    }
    
}
