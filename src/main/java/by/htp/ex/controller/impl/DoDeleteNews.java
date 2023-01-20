package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

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

public class DoDeleteNews implements Command {

    private final INewsService service = ServiceProvider.getInstance().getNewsService();
    private final String IDUSER = "idUser";
    private final String IDNEWS = "idNews";
    private final String EXCEPTION = "exception";
    private final String ACCESS = "access";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idNews = request.getParameter(IDNEWS);
            boolean execRes = false;
            String idUser = request.getSession().getAttribute(IDUSER).toString();
            News news = service.findById(idNews);
            if ((news.getUserId() + "").equals(idUser)) {
                
                execRes = service.delete(idNews);
             
            } else {
                request.setAttribute(EXCEPTION, "This post does not belong to you!");
            }

            request.setAttribute(ACCESS, "Post was deleted!");

            response.sendRedirect("controller?command=go_to_main_page");
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, "Failed to delete post, service exception");
           
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

        }

    }

}
