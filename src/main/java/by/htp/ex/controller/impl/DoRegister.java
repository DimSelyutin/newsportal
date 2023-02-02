package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.messageconst.MessageType;
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
                    request.getSession().setAttribute(MessageType.ACCESS.toString(), "Register succes!");
                }
            } else {
                request.getSession().setAttribute(MessageType.EXCEPTION.toString(), "Passwords doesn't match");
            }
            response.sendRedirect("controller?command=go_to_news");

        } catch (ServiceException e) {
            if (e.getMessage().contains("password")) {
                request.setAttribute(MessageType.EXCEPTION.toString(), e.getMessage()+"Password must contain numbers,special characters uppercase and lowercase letters. Minimum length is 6 characters!");
            }
            request.getRequestDispatcher(MessageType.BASELINK.toString()).forward(request, response);
        }

    }

}
