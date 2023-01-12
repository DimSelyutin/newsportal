package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;



import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.dao.connectionPool.Logger;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final int COUNT_NEWS = 4;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            if (request.getSession().getAttribute("user") == null) {

                request.setAttribute("presentation", "guestInfo");

                request.setAttribute("news", newsService.latestList(COUNT_NEWS));

                request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
            } else {
                response.sendRedirect("controller?command=go_to_news");
            }


        } catch (ServiceException e) {
            request.setAttribute("exception", "Some problems whith server functional.");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }

    }

}
