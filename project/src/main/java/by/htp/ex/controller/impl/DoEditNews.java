package by.htp.ex.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;

import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;

import jakarta.servlet.ServletContext;
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
    private final String IDNEWS = "idUser";
    private final String EXCEPTION = "exception";
    private static final String JSP_LOGIN_PARAM = "login";

    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter(IDNEWS);
        String title = request.getParameter(TITLE);
        String category = request.getParameter(CATEGORY);
        String text = request.getParameter(POSTTEXT);
        
        String imageDir = "";
        ////////////////////////////////////////////////////////////////////////////////
        String login = request.getSession().getAttribute(JSP_LOGIN_PARAM) + "";

        try {
            String contentType = request.getContentType();
            if ((contentType != null) && contentType.startsWith("multipart/form-data")) {
                ServletContext servletContext = request.getServletContext();

                // String path = "E:/Tomcat/apache-tomcat-10.0.27/webapps/upload/";
                String path = null;
                try {
                    path = new File(DoEditNews.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                            .getPath();
                    path = path.substring(0, path.indexOf("grot") - 1) + "\\upload\\";

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                Part filePart = request.getPart("file");

                String fileName = filePart.getSubmittedFileName();

                imageDir = path + login + fileName;

                InputStream is = filePart.getInputStream();
                Files.copy(is, Paths.get(imageDir),
                        StandardCopyOption.REPLACE_EXISTING);
            }
                 String newImageDir = imageDir.substring(imageDir.indexOf("\\upload"), imageDir.length()).replaceAll("\\\\","/");
           
            //////////////////////////////////////////////////////////////////

            int userId = (int) request.getSession().getAttribute(IDUSER);

            News editedNews = new News((Integer.parseInt(idNews)), title, text, newImageDir, category, userId);

            service.update(editedNews);
            response.sendRedirect("controller?command=go_to_main_page");
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, "Error server, pls try again later");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }

    }

}
