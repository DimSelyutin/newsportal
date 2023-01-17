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

        changeLocale();

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (isLoggedIn) {
            httpRequest.setAttribute("presentation", "userInfo");
            httpRequest.getRequestDispatcher(path).forward(request, response);
        } else if (!isLoggedIn && isLoginRequired()) {
            httpRequest.setAttribute("exception", "LogIn or Register!");
            httpRequest.getRequestDispatcher("/WEB-INF/pages/layouts/baselayout.jsp").forward(request, response);
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

    private boolean changeLocale() {
        if (httpRequest.getQueryString() != null) {
            String link = httpRequest.getRequestURI();
            System.out.println(link);

            if (!httpRequest.getQueryString().contains("change_local")) {

                // просто записываем линк, если должна выполниться ЛЮБАЯ(из любого места)
                // команда кроме change_local
                link = httpRequest.getRequestURI() +"?"+ httpRequest.getQueryString();
                System.out.println(link);
                httpRequest.getSession(true).setAttribute("link", link);

            }

        }
        return false;
    }
}
