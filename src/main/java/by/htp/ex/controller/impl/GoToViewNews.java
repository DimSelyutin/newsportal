package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.messageconst.MessageType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews extends HttpServlet implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final ICommentService commentService = ServiceProvider.getInstance().getCommentService();
    private final String IDNEWS = "idNews";
    private final String IDUSER = "idUser";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter(IDNEWS);
        String userId = (request.getSession().getAttribute(IDUSER)+"");

        String userLogin;
        News post = null;
        try {
            String local = request.getSession().getAttribute("local")+"";

            List<Comment> comments = commentService.findCommentOfPost(idNews);

            List<String> likedNews = newsService.getLikedNews(userId);
            post = newsService.findById(local, idNews);
            
            if (post == null) {
                throw new ServiceException("News not found!");
            } else {
                userLogin = userService.findUserById(post.getUserId() + "").getLogin();
            }
            request.setAttribute("presentation", "viewNews");
            request.setAttribute("comments", comments);
            request.setAttribute("post", post);
            request.setAttribute("likedNews", likedNews);
            
            
            request.getRequestDispatcher(MessageType.BASELINK.toString()).forward(request, response);
        } catch (ServiceException e) {          
            response.sendRedirect("controller?command=go_to_404");
        }

    }

}
