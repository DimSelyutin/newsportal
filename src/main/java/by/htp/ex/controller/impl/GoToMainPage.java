package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            try {
                request.setAttribute("presentation", "guestInfo");

                request.setAttribute("news", prepareNews());

            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request,response);
        } else {
            response.sendRedirect("controller?command=go_to_news");
        }
        
    }


    

    private List<News> prepareNews() throws ServiceException, ConnectionPoolException, SQLException {
        int countFirstNews = 2;
        List<News> latestNews = null;
        try {
            latestNews = newsService.latestList(countFirstNews);
        } catch (DaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return latestNews;
    }

}
