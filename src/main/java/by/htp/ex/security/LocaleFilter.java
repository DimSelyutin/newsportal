package by.htp.ex.security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("*")
public class LocaleFilter implements Filter {
    private HttpServletRequest httpRequest;
    private String link;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;

        setResponseForUser(httpRequest);

        if (httpRequest.getSession().getAttribute("local") == null) {

            httpRequest.getSession().setAttribute("local", "en");

        }
        String path = httpRequest.getRequestURI() + "/" + httpRequest.getQueryString();
    
        if (!path.contains("change_local") && !path.contains("do_add_like")) {
            // sett last command in session if not change local
            link = "controller?" + httpRequest.getQueryString();
            httpRequest.getSession(true).setAttribute("link", link);
        }
        chain.doFilter(request, response);

    }

    private void setResponseForUser(HttpServletRequest httpsRequest) {
        Object excep = httpRequest.getSession().getAttribute("exception");
        Object acces = httpRequest.getSession().getAttribute("access");
        httpRequest.setAttribute("access", acces);
        httpRequest.setAttribute("exception", excep);
        httpRequest.getSession().removeAttribute("exception");
        httpRequest.getSession().removeAttribute("access");
    }

}
