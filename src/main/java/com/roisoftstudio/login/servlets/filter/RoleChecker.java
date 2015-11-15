package com.roisoftstudio.login.servlets.filter;

import com.roisoftstudio.storage.InMemoryRolesDao;
import com.roisoftstudio.storage.RolesDao;
import com.roisoftstudio.storage.db.InMemoryRolesDB;

import java.util.Set;

public class RoleChecker {
    private final RolesDao rolesDao = new InMemoryRolesDao(new InMemoryRolesDB());

    public boolean userHasValidRole(Set<String> sessionRoles, String requestURI) {
        for (String sessionRole : sessionRoles) {
            Set<String> allowedFilesForRole = rolesDao.getPages(sessionRole);
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
