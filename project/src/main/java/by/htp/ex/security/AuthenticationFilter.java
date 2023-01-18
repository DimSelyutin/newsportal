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

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {
            "do_add_comment",
            "do_delete_comment",
            "do_add_news",
            "do_edit_news",
            "do_delete_news"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        System.out.println();
        if (isLoggedIn) {
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
                System.out.println(loginRequiredURL);
                return true;
            }
        }

        return false;
    }

}
