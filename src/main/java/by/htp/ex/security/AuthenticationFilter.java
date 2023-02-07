package by.htp.ex.security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/controller")
public class AuthenticationFilter implements Filter {
    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {
            "do_add_comment",
            "do_delete_comment",
            "do_add_news",
            "do_edit_news",
            "do_delete_news",
            "do_add_like",
            "do_add_translate",
            "go_to_edit_news",
            "go_to_translate_news"

    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoggedAdmin = (session != null && session.getAttribute("role") == "admin");
        if (isLoggedIn || isLoggedAdmin) {
            httpRequest.setAttribute("presentation", "userInfo");
            chain.doFilter(request, response);

        } else if (!isLoggedIn && isLoginRequired()) {
            httpRequest.setAttribute("presentation", "guestInfo");
            httpRequest.setAttribute("exception", "LogIn or Register!");
            request.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
            
        } else {
            httpRequest.setAttribute("presentation", "guestInfo");
            chain.doFilter(request, response);
        }
        

    }

    private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString() + "/" + httpRequest.getQueryString();

        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }

        return false;
    }

}
