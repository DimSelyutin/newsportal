package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GoToViewNews extends HttpServlet implements Command {
    
    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoException, ConnectionPoolException, SQLException {
        String idNews = request.getParameter("idNews");
        System.out.println("sdasd");
        News post = null;
        try {
            post = newsService.findById(idNews);
        } catch (ServiceException e) {
            request.setAttribute("somthingWrong", "The post didn't found!");
            response.sendRedirect("controller?command=go_to_news");
            e.printStackTrace();
        }
        String userLogin = userService.findUserById(post.getUserId() + "").getLogin();
        request.setAttribute("presentation", "viewNews");
        request.setAttribute("post", post);


        request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

    }

}
