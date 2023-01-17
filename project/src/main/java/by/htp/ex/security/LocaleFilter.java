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

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        System.out.println(httpRequest.getQueryString());
        if (!path.contains("change_local")) {
            // просто записываем линк, если должна выполниться ЛЮБАЯ(из любого места)
            // команда кроме change_local
            link = "controller?" + httpRequest.getQueryString();
            httpRequest.getSession(true).setAttribute("link", link);
        }
        chain.doFilter(request, response);

    }

}
