package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final String ACCESS = "access";
    private final String LOGIN = "login";
    private final String PRESENTATION = "news";
    private final String CATEGORY = "category";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = null;
        List<News> newsList;
        try {
            category = request.getParameter(CATEGORY);

            if (category == null) {
                newsList = newsService.list();
            } else if (category.equals("sortbydate")) {
                newsList = newsService.sortByDate();
            } else {
                newsList = newsService.sortByCategory(category);
            }

            request.setAttribute(PRESENTATION, newsList);

        } catch (ServiceException e) {
            request.setAttribute(ACCESS, e.getMessage());
        }
        request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
    }

}
