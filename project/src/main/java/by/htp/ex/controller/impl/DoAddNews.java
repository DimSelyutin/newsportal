package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddNews implements Command {
    private final INewsService service = ServiceProvider.getInstance().getNewsService();
    private final String CATEGORY = "category";
    private final String TITLE = "title";
    private final String POSTTEXT = "postText";
    private final String IMAGEDIR = "imageDir";
    private final String IDUSER = "idUser";
    private final String EXCEPTION = "exception";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCategory = request.getParameter(CATEGORY);
        String title = request.getParameter(TITLE);
        String text = request.getParameter(POSTTEXT);
        String imageDir2 = request.getParameter(IMAGEDIR);
        String imageDir = "https://i.pinimg.com/originals/1e/51/34/1e51340e734aa32aeb8f14712dae043d.jpg";

        try {
            int userId = (int) request.getSession().getAttribute(IDUSER);
            News editedNews = new News(title, text, imageDir, idCategory, userId);
            if(!service.save(editedNews)) {
                throw new ServiceException("Error save post!");
            }
            response.sendRedirect("controller?command=go_to_main_page");
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, "Error to add news!");
            response.sendRedirect("controller?command=go_to_main_page");

        }

    }

}
