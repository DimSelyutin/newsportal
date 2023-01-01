package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DoSignIn implements Command {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final String JSP_LOGIN_PARAM = "login";
    private static final String JSP_PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoException, ConnectionPoolException, SQLException {
        String login = request.getParameter(JSP_LOGIN_PARAM);
        String passsword = request.getParameter(JSP_PASSWORD_PARAM);
        String role = userService.signin(login, passsword);
        int idUser = userService.getUserId(login);
        List<Category> listCategory = newsService.findAllCategoryes();

        if (!role.equals("guest")) {

            request.getSession(true).setAttribute("user", "active");
            request.getSession(true).setAttribute("role", role);
            request.getSession(true).setAttribute("idUser", idUser);
            request.getSession(true).setAttribute("login", login);
            request.getSession().setAttribute("listCategory", listCategory);
            // request.setAttribute("listCategory", listCategory);

            response.sendRedirect("controller?command=go_to_news");

        } else {
            request.getSession(true).setAttribute("user", "not active");
            request.setAttribute("AuthenticationError", "wrong login or password");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }
        response.getWriter().println("<h2>Do logination</h2>");
    }

}
