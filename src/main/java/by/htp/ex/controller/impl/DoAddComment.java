package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.Comment;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddComment implements Command {
    private final ICommentService service = ServiceProvider.getInstance().getCommentService();
    private final String IDNEWS = "idNews";
    private final String POSTTEXT = "post-text";
    private final String IDUSER = "idUser";
    private final String EXCEPTION = "exception";
    private final String ACCESS = "access";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idNews = Integer.parseInt(request.getParameter(IDNEWS));
        String postText = request.getParameter(POSTTEXT);
        int idUser = (int)request.getSession().getAttribute(IDUSER);

        if(postText.trim().equals("")){
            request.getSession().setAttribute(ACCESS, "Enter text of comment!");
        } else {

            try {
                service.addComment(new Comment(idNews, idUser, postText));
                request.getSession().setAttribute(ACCESS, "Comment was added!");
            } catch (ServiceException e) {
                request.getSession().setAttribute(EXCEPTION, "Error to adding a comment! ");
                response.sendRedirect("controller?command=go_to_view_news&idNews="+idNews);
            }
        }
        response.sendRedirect("controller?command=go_to_view_news&idNews="+idNews);


        
    }
    
}
