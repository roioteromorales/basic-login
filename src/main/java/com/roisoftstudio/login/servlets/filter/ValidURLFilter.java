package com.roisoftstudio.login.servlets.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.roisoftstudio.Constants.LOGIN_PAGE;
import static com.roisoftstudio.Constants.LOGIN_SERVLET_PATH;
import static com.roisoftstudio.Constants.LOGOUT_SERVLET_PATH;

@WebFilter(urlPatterns = {"/*"})
public class ValidURLFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isValidUrl(request.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(getAbsolutePath(request.getContextPath()) + LOGIN_PAGE);
        }
    }

    private String getAbsolutePath(String contextPath) {
        if (contextPath != null)
            contextPath = contextPath + "/";
        else {
            contextPath = "";
        }
        return contextPath;
    }

    private boolean isValidUrl(String requestURI) {
        if (isLoginPath(requestURI) || endsWithValidFileType(requestURI)) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
    }

    private boolean endsWithValidFileType(String requestURI) {
        return requestURI.toLowerCase().endsWith(".css") ||
                requestURI.toLowerCase().endsWith(".jpg") ||
                requestURI.toLowerCase().endsWith(".png") ||
                requestURI.toLowerCase().endsWith(".jsp") ||
                requestURI.toLowerCase().endsWith(".html");
    }

    private boolean isLoginPath(String requestURI) {
        return requestURI.endsWith(LOGIN_SERVLET_PATH) ||
                requestURI.endsWith(LOGOUT_SERVLET_PATH);
    }
}
