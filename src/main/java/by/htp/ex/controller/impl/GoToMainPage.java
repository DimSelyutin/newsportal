package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.connection.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command{
    
    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<News> latestNews;
        

			try {
                
                request.setAttribute("presentation", "guestInfo");
                latestNews = newsService.latestList(2);
                request.setAttribute("news", latestNews);
                
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch(ConnectionPoolException e) {
                e.printStackTrace();
                
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        request.getRequestDispatcher("WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

        
    }
    
    
}
