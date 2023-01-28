package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
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
    private static final String JSP_CONFIRM_PASSWORD_PARAM = "password2";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String login = request.getParameter(JSP_LOGIN_PARAM);
            String passsword = request.getParameter(JSP_PASSWORD_PARAM);
            String confirmPasssword = request.getParameter(JSP_CONFIRM_PASSWORD_PARAM);
            String phone = request.getParameter(JSP_PHONE_PARAM);
            String email = request.getParameter(JSP_EMAIL_PARAM);

            if (passsword.equals(confirmPasssword)) {
                User newUser;
                newUser = new User(login, phone, email, passsword);
                if (service.registration(newUser)) {
                    request.getSession().setAttribute("access", "Register succes!");
                }
            } else {
                request.getSession().setAttribute("exception", "Passwords doesn't match");
            }
            response.sendRedirect("controller?command=go_to_news");

        } catch (ServiceException e) {
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }

    }

}
