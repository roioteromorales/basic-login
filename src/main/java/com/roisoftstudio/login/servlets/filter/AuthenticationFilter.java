package com.roisoftstudio.login.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

import static com.roisoftstudio.Constants.*;

@WebFilter(urlPatterns = {"/protectedPath/*"})
public class AuthenticationFilter implements Filter {

    private final RoleChecker roleChecker = new RoleChecker();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws java.io.IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        if (hasSession(session)) {
            if (roleChecker.userHasValidRole((Set<String>) session.getAttribute(PARAMETER_ROLES), request.getRequestURI())) {
                chain.doFilter(req, res);
            } else {
                response.sendRedirect(UNAUTHORIZED_ROLE_PAGE);
            }
        } else {
            response.sendRedirect(FOLDER_UP + LOGIN_PAGE);
        }
    }

    private boolean hasSession(HttpSession session) {
        return session != null;
    }

    @Override
    public void destroy() {
    }
}
