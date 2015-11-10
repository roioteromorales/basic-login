package com.roisoftstudio.login.servlets.filter;

import com.roisoftstudio.login.users.RolesMap;

import javax.servlet.http.HttpSession;
import java.util.Set;

import static com.roisoftstudio.Constants.PARAMETER_ROLES;

public class RoleChecker {

    private final RolesMap rolesMap = new RolesMap();

    public boolean userHasValidRole(HttpSession session, String requestURI) {
        for (String sessionRole : getSessionRoles(session)) {
            Set<String> allowedFilesForRole = rolesMap.roleToFilesMap.get(sessionRole);
            if (allowedFilesForRole.contains(getPage(requestURI))) {
                return true;
            }
        }
        return false;
    }

    private String getPage(String requestURI) {
        return requestURI.substring(requestURI.lastIndexOf("/") + 1);
    }

    @SuppressWarnings("unchecked")
    private Set<String> getSessionRoles(HttpSession session) {
        return (Set<String>) session.getAttribute(PARAMETER_ROLES);
    }
}
