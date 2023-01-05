package by.htp.ex.controller.impl;

import java.io.IOException;
import java.io.InputStream;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;

import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class DoAddNews implements Command {
    private final INewsService service = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCategory = request.getParameter("category");
        String title = request.getParameter("title");
        String text = request.getParameter("postText");
        String imageDir = "https://i.pinimg.com/originals/1e/51/34/1e51340e734aa32aeb8f14712dae043d.jpg";

                // Part file = request.getPart("file");
                // String filename = getFilename(file);
                // InputStream filecontent = file.getInputStream();
        // ... Do your file saving job here.

        int userId = (int) request.getSession().getAttribute("idUser");

        News editedNews = new News(title, text, imageDir, idCategory, userId);

        service.save(editedNews);
        response.sendRedirect("controller?command=go_to_main_page");

    }

    // private static String getFilename(Part part) {
    //     for (String cd : part.getHeader("content-disposition").split(";")) {
    //         if (cd.trim().startsWith("filename")) {
    //             String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
    //             return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
    //         }
    //     }
    //     return null;
    // }

}
