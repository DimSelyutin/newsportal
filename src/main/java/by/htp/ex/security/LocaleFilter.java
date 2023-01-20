package by.htp.ex.security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class LocaleFilter implements Filter {
    private HttpServletRequest httpRequest;
    private String link;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;

        String path = httpRequest.getRequestURI()+"/"+httpRequest.getQueryString();

        if (!path.contains("change_local")) {
            // sett last command in session if not change local
            link = "controller?" + httpRequest.getQueryString();
            httpRequest.getSession(true).setAttribute("link", link);
        }
        chain.doFilter(request, response);

    }

}
