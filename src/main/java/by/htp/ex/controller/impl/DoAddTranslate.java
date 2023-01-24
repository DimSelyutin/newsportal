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

public class DoAddTranslate implements Command {
    private final INewsService service = ServiceProvider.getInstance().getNewsService();
    private final String CATEGORY = "category";
    private final String TITLE = "title";
    private final String POSTTEXT = "postText";
    private final String IMAGEDIR = "imageDir";
    private final String IDUSER = "idUser";
    private final String EXCEPTION = "exception";
    private static final String JSP_LOGIN_PARAM = "login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCategory = request.getParameter(CATEGORY);
        String title = request.getParameter(TITLE);
        String text = request.getParameter(POSTTEXT);
        String category = request.getParameter(CATEGORY);
        String local = request.getSession().getAttribute("local").toString();
        if (local == null) {
            local = "en";
        }
        String imageDir = "";

        String login = request.getSession().getAttribute(JSP_LOGIN_PARAM).toString();
        try {
            int userId = (int) request.getSession().getAttribute(IDUSER);
            News editedNews = new News(title, text, imageDir, category, userId, local);
            if (!service.update(editedNews)) {
                throw new ServiceException("Error save post!");
            }
            request.getSession().setAttribute("access", "Post was added!");
            response.sendRedirect("controller?command=go_to_main_page");
        } catch (ServiceException e) {
            e.printStackTrace();
            request.setAttribute(EXCEPTION, "Error to add news!");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

        }
    }

}
