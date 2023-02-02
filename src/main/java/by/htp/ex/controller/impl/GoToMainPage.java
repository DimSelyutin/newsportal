package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.controller.Command;

import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.messageconst.MessageType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final int COUNT_NEWS = 4;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            String local = request.getSession().getAttribute("local").toString();
            request.setAttribute(local, local);
            request.setAttribute("news", newsService.latestList(local, COUNT_NEWS));
            request.setAttribute("presentation", "userInfo");
            request.getSession().setAttribute("listCategory", newsService.findAllCategoryes());
            request.getRequestDispatcher(MessageType.BASELINK.toString()).forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(MessageType.EXCEPTION.toString(), "Some problems whith server functional.");
            response.sendRedirect("controller?command=go_to_news");
        }

    }

}
