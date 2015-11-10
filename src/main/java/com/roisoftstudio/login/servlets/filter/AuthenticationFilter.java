package com.roisoftstudio.login.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.roisoftstudio.Constants.*;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    private RoleChecker roleChecker = new RoleChecker();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws java.io.IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath() != null ? request.getContextPath() + "/" : "";


        if (isProtected(request)) {
            if (hasSession(session)) {
                if (isKnownExtension(requestURI)) {
                    if (isDisplayingUnauthorizedPage(requestURI) ||
                            roleChecker.userHasValidRole(session, requestURI)) {
                        chain.doFilter(request, response);
                    } else {
                        response.sendRedirect(UNAUTHORIZED_ROLE_PAGE);
                    }
                } else {
                    response.sendRedirect(MAIN_PAGE);
                }
            } else {
                response.sendRedirect(FOLDER_UP + LOGIN_PAGE);
            }
        } else if (isLoginPath(requestURI) || isResource(requestURI)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(contextPath + LOGIN_PAGE);
        }

    }

    private boolean isDisplayingUnauthorizedPage(String requestURI) {
        return requestURI.endsWith(UNAUTHORIZED_ROLE_PAGE);
    }

    @Override
    public void destroy() {

    }

    private boolean isKnownExtension(String path) {
        return (path.endsWith(".html") ||
                path.endsWith(".jsp") ||
                path.endsWith(".css"));
    }

    private boolean isResource(String requestUri) {
        return requestUri.toLowerCase().endsWith(".css") ||
                requestUri.toLowerCase().endsWith(".jpg") ||
                requestUri.toLowerCase().endsWith(".png");
    }

    private boolean hasSession(HttpSession session) {
        return session != null;
    }

    private boolean isProtected(HttpServletRequest request) {
        String rootPath = request.getRequestURI();
        return rootPath != null && rootPath.contains(PROTECTED_PATH);
    }

    private boolean isLoginPath(String requestURI) {
        return requestURI.endsWith(LOGIN_PAGE) ||
                requestURI.endsWith(LOGIN_SERVLET_PATH) ||
                requestURI.endsWith(LOGOUT_SERVLET_PATH);
    }
}
