package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class DoAddLike implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String newsId = request.getParameter("likeCount");
        System.out.println("Id news" +newsId);
        try {
            newsService.addLike(3+"");
        } catch (SQLException e) {
            System.out.println("Inject-boring");
            
            e.printStackTrace();
        }
    }


}
