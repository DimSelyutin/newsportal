package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteNews implements Command {
    private final INewsService service = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idNews = request.getParameter("idNews");
            System.out.println(idNews);
            String idUser = request.getSession().getAttribute("idUser").toString();
            System.out.println(idUser);
            News news = service.findById(idNews);
            if ((news.getUserId()+"").equals(idUser)) {
                service.delete(idNews); 
            } else {
                request.setAttribute("ServerError", "This post does not belong to you!");
            }
            request.setAttribute("access", "Post was deleted!");

            response.sendRedirect("controller?command=go_to_main_page");
        } catch (ServiceException | ConnectionPoolException | DaoException e) {
            request.setAttribute("ServerError", "Failed to delete post, service exception");
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("ServerError", "Failed to delete post");
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

        }

    }

}