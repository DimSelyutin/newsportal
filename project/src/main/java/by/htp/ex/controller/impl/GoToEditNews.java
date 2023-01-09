package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.dao.connectionPool.Logger;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idNews = request.getParameter("idNews");
        News post = null;
        String userLogin = "null";
        try {
            post = newsService.findById(idNews);
            userLogin = userService.findUserById(post.getUserId() + "").getLogin();
            if (request.getSession().getAttribute("role").equals("admin") || request.getSession().getAttribute("login").equals(userLogin)) {
                request.setAttribute("presentation", "postId");
                request.setAttribute("link", "/WEB-INF/pages/tiles/editNews.jsp");
                request.setAttribute("post", post);
                request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

            } else {

                response.sendRedirect("controller?command=go_to_news");

            }
        } catch (NullPointerException | ServiceException | ConnectionPoolException | SQLException | DaoException e) {
            response.sendRedirect("controller?command=go_to_news");
            // Logger.writeLog(e.getStackTrace().toString());
        }

    }

}
