package by.htp.ex.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;

import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class DoEditNews implements Command {

    private final INewsService service = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter("idNews");
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String text = request.getParameter("postText");
        String imageDir = "";
        ////////////////////////////////////////////////////////////////////////////////
        String login = request.getSession().getAttribute("login")+"";
        
        
        try {
        String contentType = request.getContentType();
        if ((contentType != null) && contentType.startsWith("multipart/form-data")) {
            ServletContext servletContext = request.getServletContext();
            
            // String path = "E:/Tomcat/apache-tomcat-10.0.27/webapps/upload/";
            String path = null;
            try {
                path = new File(DoEditNews.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
                path = path.substring(0,path.indexOf("grot"))+"\\upload\\";
                
            } catch (URISyntaxException e) {
                System.out.println("Exception!!!!!!");
                e.printStackTrace();
            }


            Part filePart = request.getPart("file");

            String fileName = filePart.getSubmittedFileName();

            imageDir = path+login+fileName;
            
            
            InputStream is = filePart.getInputStream();
            Files.copy(is, Paths.get(imageDir),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        String newImageDir = imageDir.substring(imageDir.indexOf("\\upload"), imageDir.length()).replaceAll("\\\\", "/");
        System.out.println(newImageDir);
        //////////////////////////////////////////////////////////////////

        int userId = (int) request.getSession().getAttribute("idUser");


            News editedNews = new News((Integer.parseInt(idNews)), title, text, newImageDir, category, userId);

            service.update(editedNews);
            response.sendRedirect("controller?command=go_to_main_page");
        } catch (SQLException | ConnectionPoolException e) {

            e.printStackTrace();
        }

    }

}
