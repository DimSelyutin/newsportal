package by.htp.ex.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.messageconst.MessageType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class DoAddNews implements Command {
    private static final INewsService service = ServiceProvider.getInstance().getNewsService();
    private static final String CATEGORY = "category";
    private static final String TITLE = "title";
    private static final String POSTTEXT = "postText";
    private static final String IMAGEDIR = "imageDir";
    private static final String IDUSER = "idUser";
    private static final String EXCEPTION = "exception";
    private static final String JSP_LOGIN_PARAM = "login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(TITLE);
        String text = request.getParameter(POSTTEXT);
        String category = request.getParameter(CATEGORY);
        String local = request.getSession().getAttribute("local").toString();
        String newDir = request.getParameter(IMAGEDIR);

        String login = request.getSession().getAttribute(JSP_LOGIN_PARAM).toString();
        try {
            String contentType = request.getContentType();
            if ((contentType != null) && contentType.startsWith("multipart/form-data")) {

                String path = "/opt/tomcat/webapps/upload";
                File file = new File(path);
                Part filePart = request.getPart("file");
                String fileName = filePart.getSubmittedFileName();

                
                if (fileName.trim() != "") {
                    newDir = "/upload/" + login + fileName;
                    InputStream is = filePart.getInputStream();
                    
                    Files.copy(is, Paths.get(file.getAbsolutePath() + "/" + login + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
                }
            }

            int userId = (int) request.getSession().getAttribute(IDUSER);
            News editedNews = new News(title, text, newDir, category, userId, local);
    
            if (!service.save(editedNews)) {
                throw new ServiceException("Error save post!");
            }
            request.getSession().setAttribute(MessageType.ACCESS.toString(), "Post was added!");
            response.sendRedirect("controller?command=go_to_main_page");
        } catch (ServiceException e) {
            request.setAttribute(MessageType.EXCEPTION.toString(), "Error to add news!");
            request.getRequestDispatcher(MessageType.BASELINK.toString()).forward(request, response);

        }

    }

}
