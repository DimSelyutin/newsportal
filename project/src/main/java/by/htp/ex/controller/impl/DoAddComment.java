package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.Comment;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ICommentService;

import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddComment implements Command {
    private final ICommentService service = ServiceProvider.getInstance().getCommentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // int idNews = Integer.parseInt(request.getParameter("idNews"));
        // String postText = request.getParameter("post-text");
        // int idUser = (int)request.getSession().getAttribute("idUser");

        
        // try {
        //     service.addComment(new Comment(idNews, idUser, postText));
        //     response.sendRedirect("controller?command=go_to_main_page");
        // } catch (SQLException e) {
        //     request.setAttribute("exception", "Error to adding a comment! ");
        //     e.printStackTrace();
        //     response.sendRedirect("controller?command=go_to_main_page");
        // }
    }
    
}
