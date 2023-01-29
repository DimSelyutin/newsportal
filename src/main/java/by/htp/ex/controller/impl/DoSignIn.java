package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.controller.Command;

import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final String JSP_LOGIN_PARAM = "login";
    private static final String JSP_PASSWORD_PARAM = "password";
    private static final String ROLE_GUEST = "guest";
    private final String ACCESS = "access";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter(JSP_LOGIN_PARAM);
            String passsword = request.getParameter(JSP_PASSWORD_PARAM);
            String role = ROLE_GUEST;
            int idUser;
            List<Category> listCategory = null;
            
            role = userService.signin(login, passsword);
            idUser = userService.getUserId(login);
            listCategory = newsService.findAllCategoryes();

            if (!role.equals(ROLE_GUEST)) {
                
                request.getSession(true).setAttribute("user", "active");
                request.getSession().setAttribute("role", role);
                request.getSession().setAttribute("idUser", idUser);
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("listCategory", listCategory);
                request.getSession().setAttribute(ACCESS, "Welcome "+request.getSession().getAttribute(JSP_LOGIN_PARAM));

                response.sendRedirect("controller?command=go_to_news");

            } else {
                request.getSession(true).setAttribute("user", "not active");
                request.setAttribute("exception", "Wrong login or password");
                request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            request.setAttribute("exception", "Error server, pls try again later");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }

        response.getWriter().println("<h2>Do logination</h2>");
    }

}
