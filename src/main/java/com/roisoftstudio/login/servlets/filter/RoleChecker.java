package com.roisoftstudio.login.servlets.filter;

import com.roisoftstudio.login.users.RolesMap;

import java.util.Set;

public class RoleChecker {

    private final RolesMap rolesMap = new RolesMap();

    public boolean userHasValidRole(Set<String> sessionRoles, String requestURI) {
        for (String sessionRole : sessionRoles) {
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

}
