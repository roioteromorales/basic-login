package com.roisoftstudio.login.servlets.validators;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static com.roisoftstudio.Constants.PARAMETER_ROLES;
import static com.roisoftstudio.Constants.UNAUTHORIZED_ROLE_PAGE;

public class RoleSessionValidator {

    public void validateSessionRole(HttpSession session, HttpServletResponse response, Set<String> allowedRoles) throws IOException {
        if (roleIsNotValid(session, allowedRoles))
            response.sendRedirect(UNAUTHORIZED_ROLE_PAGE);
    }

    public Set<String> getSessionRoles(HttpSession session) {
        return (Set<String>) session.getAttribute(PARAMETER_ROLES);
    }

    private boolean roleIsNotValid(HttpSession session, Set<String> allowedRoles) throws IOException {
        if (allowedRoles == null || allowedRoles.isEmpty()) {
            return false;
        }
        Set<String> userRoles = (Set<String>) session.getAttribute(PARAMETER_ROLES);
        if (userRoles == null) {
            return true;
        } else {
            if (!hasUserAValidRole(allowedRoles, userRoles))
                return true;
        }
        return false;
//todo work this
    }

    private boolean hasUserAValidRole(Set<String> allowedRoles, Set<String> userRoles) {
        for (String role : userRoles)
            if (allowedRoles.contains(role))
                return true;
        return false;
    }
}
