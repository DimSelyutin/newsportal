package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegister implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();
    private static final String JSP_LOGIN_PARAM = "login";
    private static final String JSP_PASSWORD_PARAM = "password";
    private static final String JSP_EMAIL_PARAM = "email";
    private static final String JSP_PHONE_PARAM = "phone";
    private static final String JSP_CONFIRM_PASSWORD_PARAM = "confirm_phone";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String login = request.getParameter(JSP_LOGIN_PARAM);
            String passsword = request.getParameter(JSP_PASSWORD_PARAM);
            String confirmPasssword = request.getParameter(JSP_CONFIRM_PASSWORD_PARAM);
            String phone = request.getParameter(JSP_PHONE_PARAM);
            String email = request.getParameter(JSP_EMAIL_PARAM);

            User newUser;
            newUser = new User(login, phone, email, passsword);
            service.registration(newUser);
        } catch (DaoException | ConnectionPoolException | SQLException e) {
            request.setAttribute("AuthenticationError", "Error server, pls try again later");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

        }

        request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);

    }

}