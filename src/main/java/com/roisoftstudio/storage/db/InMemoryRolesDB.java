package com.roisoftstudio.storage.db;

import java.util.*;

import static com.roisoftstudio.Constants.MAIN_PAGE;
import static com.roisoftstudio.Constants.UNAUTHORIZED_ROLE_PAGE;

public class InMemoryRolesDB {
    public static final String ROLE_1 = "PAG_1";
    public static final String ROLE_2 = "PAG_2";
    public static final String ROLE_3 = "PAG_3";
    public static final String ROLE_4 = "ALL_PAGES";


    public Map<String, Set<String>> roleToFilesDB = new HashMap<>();

    public InMemoryRolesDB() {
        addPages(ROLE_1, MAIN_PAGE, UNAUTHORIZED_ROLE_PAGE, "page1.jsp");
        addPages(ROLE_2, MAIN_PAGE, UNAUTHORIZED_ROLE_PAGE, "page2.jsp");
        addPages(ROLE_3, MAIN_PAGE, UNAUTHORIZED_ROLE_PAGE, "page3.jsp");
        addPages(ROLE_4, MAIN_PAGE, UNAUTHORIZED_ROLE_PAGE, "page1.jsp", "page2.jsp", "page3.jsp");
    }

    public void addPages(String role, String... pages) {
        Set<String> rolePages = roleToFilesDB.get(role);
        if (rolePages == null) {
            rolePages = new HashSet<>();
        }
        Collections.addAll(rolePages, pages);
        roleToFilesDB.put(role, rolePages);
    }

    public Set<String> getPages(String page){
        return roleToFilesDB.get(page);
    }
}
