package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.Comment;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteComment implements Command {
    private final ICommentService serviceComment = ServiceProvider.getInstance().getCommentService();
    private final String IDUSER = "idUser";
    private final String IDCOMMENT = "commentId";
    private final String EXCEPTION = "exception";
    private final String ACCESS = "access";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idComment = request.getParameter(IDCOMMENT);
            boolean execRes = false;
            String idUser = request.getSession().getAttribute(IDUSER).toString();
            Comment comment = serviceComment.findCommentById(idComment);
            if ((comment.getUserId() + "").equals(idUser)) {
               
                execRes = serviceComment.deleteComment(idComment);
                
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
