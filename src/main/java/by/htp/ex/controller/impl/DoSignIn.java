package by.htp.ex.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.controller.Command;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.connection.ConnectionPoolException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.validation.ValidException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignIn implements Command{

    private final IUserService service = ServiceProvider.getInstance().getUserService();
    private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException, ConnectionPoolException, SQLException {
        String login = request.getParameter(JSP_LOGIN_PARAM);
        String passsword = request.getParameter(JSP_PASSWORD_PARAM);

       String role = service.signin(login, passsword);

        if (!role.equals("guest")) {

            request.getSession(true).setAttribute("user", "active");
            request.getSession(true).setAttribute("role", role);
            request.getSession(true).setAttribute("login", login);
            response.sendRedirect("controller?command=go_to_news");
            

        } else {
            System.out.println("not guset");
            request.getSession(true).setAttribute("user", "not active");
			request.setAttribute("AuthenticationError", "wrong login or password");
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
        }
        response.getWriter().println("<h2>Do logination</h2>");
    }
    
}
