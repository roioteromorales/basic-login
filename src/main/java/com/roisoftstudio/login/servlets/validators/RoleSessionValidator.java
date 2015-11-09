package com.roisoftstudio.login.servlets.validators;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static com.roisoftstudio.Constants.PARAMETER_ROLES;

public class RoleSessionValidator {

    public boolean isRoleInvalid(HttpSession session, Set<String> allowedRoles) throws IOException {
        if (noRestrictionsAreSpecified(allowedRoles)) {
            return false;
        }
        Set<String> userRoles = getSessionRoles(session);
        if (userRoles == null) {
            return true;
        } else {
            if (!hasUserAValidRole(allowedRoles, userRoles))
                return true;
        }
        return false;
    }

    private boolean noRestrictionsAreSpecified(Set<String> allowedRoles) {
        return allowedRoles == null || allowedRoles.isEmpty();
    }

    @SuppressWarnings("unchecked")
    private Set<String> getSessionRoles(HttpSession session) {
        return (Set<String>) session.getAttribute(PARAMETER_ROLES);
    }

    private boolean hasUserAValidRole(Set<String> allowedRoles, Set<String> userRoles) {
        for (String role : userRoles)
            if (allowedRoles.contains(role))
                return true;
        return false;
    }
}
