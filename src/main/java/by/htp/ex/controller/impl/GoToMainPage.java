package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.dao.connectionpool.Logger;
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
            
            String local = request.getSession().getAttribute("local").toString();
            request.setAttribute(local, local);
            request.setAttribute("news", newsService.latestList(local, COUNT_NEWS));
            request.setAttribute("presentation", "userInfo");
            request.getSession().setAttribute("listCategory", newsService.findAllCategoryes());
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute("exception", "Some problems whith server functional.");
            response.sendRedirect("controller?command=go_to_news");
        }

    }

}
