package com.roisoftstudio.login.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.roisoftstudio.Constants.*;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    private ServletContext context;
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
                response.sendRedirect(LOGIN_PAGE);
            }
        } else if (isLoginPath(request) || isResource(requestURI)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(LOGIN_PAGE);
        }
    }

    private boolean isDisplayingUnauthorizedPage(String requestURI) {
        return UNAUTHORIZED_ROLE_PAGE.equals(requestURI);
    }

    @Override
    public void destroy() {

    }

    private boolean isKnownExtension(String path) {
        return (path.endsWith(".html") ||
                path.endsWith(".jsp") ||
                path.endsWith(".css"));
    }

    private boolean isResource(String path) {
        return (path.matches("^/css/.*$"));
    }

    private boolean hasSession(HttpSession session) {
        return session != null;
    }

    private boolean isProtected(HttpServletRequest request) {
        String rootPath = request.getRequestURI();
        return rootPath != null && rootPath.contains(PROTECTED_PATH);
    }

    private boolean isLoginPath(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.equals(LOGIN_PAGE) ||
                requestURI.equals(LOGIN_SERVLET_PATH) ||
                requestURI.equals(LOGOUT_SERVLET_PATH);
    }
}
