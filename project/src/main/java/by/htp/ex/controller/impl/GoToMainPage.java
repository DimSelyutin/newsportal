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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            
            if (request.getSession().getAttribute("user") == null) {

                request.setAttribute("presentation", "guestInfo");

                request.setAttribute("news", newsService.latestList(2));

                request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
            } else {
                response.sendRedirect("controller?command=go_to_news");
            }


        } catch (ConnectionPoolException | SQLException | DaoException e) {
            // Logger.writeLog(e.getMessage());
            request.setAttribute("ServerError", "Error to database connection.");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        } catch (ServiceException e) {
            // Logger.writeLog(e.getMessage());
            request.setAttribute("ServerError", "Some problems whith server functional.");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            // Logger.writeLog(e.getMessage());
            request.setAttribute("ServerError", "Some problems whith server. Try again later pls.");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }

    }

}
