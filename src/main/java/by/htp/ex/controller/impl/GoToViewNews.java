package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.ICommentService;
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
    private final ICommentService commentService = ServiceProvider.getInstance().getCommentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter("idNews");
        String userLogin = null;
        News post = null;
        try {

            List<Comment> comments = commentService.findCommentOfPost(idNews);
            System.out.println(comments.toString());

            post = newsService.findById(idNews);

            if (post == null) {
                throw new ServiceException("Post is null");
            } else {
                userLogin = userService.findUserById(post.getUserId() + "").getLogin();
            }
            request.setAttribute("presentation", "viewNews");
            request.setAttribute("comments", comments);
            request.setAttribute("post", post);
    
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        } catch (ConnectionPoolException | SQLException | DaoException | ServiceException e) {
            
            response.sendRedirect("controller?command=go_to_404");
            e.printStackTrace();
        }

    }

}
