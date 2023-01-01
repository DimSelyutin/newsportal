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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command {
    
    private final INewsService service = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idNews = request.getParameter("idNews");
        String title = request.getParameter("title");
        String text = request.getParameter("postText");
        String imageDir = "https://i.pinimg.com/originals/1e/51/34/1e51340e734aa32aeb8f14712dae043d.jpg";
        String category = request.getParameter("category");

        int userId = (int) request.getSession().getAttribute("idUser");
        
        News editedNews = new News((Integer.parseInt(idNews)), title, text, imageDir,category, userId);

        try {
            service.update(editedNews);
        } catch (SQLException | ConnectionPoolException e) {
            
            e.printStackTrace();
        }
        response.sendRedirect("controller?command=go_to_main_page");

    }

}
