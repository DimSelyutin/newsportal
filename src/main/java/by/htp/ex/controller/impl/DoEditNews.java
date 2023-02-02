package by.htp.ex.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;

import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.messageconst.MessageType;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class DoEditNews implements Command {

    private final INewsService service = ServiceProvider.getInstance().getNewsService();
    private final String CATEGORY = "category";
    private final String TITLE = "title";
    private final String POSTTEXT = "postText";
    private final String IMAGEDIR = "imageDir";
    private final String IDUSER = "idUser";
    private final String IDNEWS = "idNews";
    private static final String JSP_LOGIN_PARAM = "login";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter(IDNEWS);
        String title = request.getParameter(TITLE);
        String category = request.getParameter(CATEGORY);
        String text = request.getParameter(POSTTEXT);
        String newDir = request.getParameter(IMAGEDIR);
        
        String login = request.getSession().getAttribute(JSP_LOGIN_PARAM) + "";
        String local = request.getSession().getAttribute("local").toString();

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

            News editedNews = new News((Integer.parseInt(idNews)), title, text, newDir, category, userId, local);
            
            if (service.update(editedNews)) {
                request.getSession().setAttribute(MessageType.ACCESS.toString(), "Post saved!");
            }
            
    

            response.sendRedirect("controller?command=go_to_news");

        } catch (ServiceException e) {
            request.setAttribute(MessageType.EXCEPTION.toString(), "Error server, pls try again later");
            request.getRequestDispatcher(MessageType.BASELINK.toString()).forward(request, response);
        }

    }

}
