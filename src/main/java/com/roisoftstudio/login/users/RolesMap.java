package com.roisoftstudio.login.users;

import java.util.*;

public class RolesMap {
    public static final String ROLE_1 = "PAG_1";
    public static final String ROLE_2 = "PAG_2";
    public static final String ROLE_3 = "PAG_3";
    public static final String ROLE_4 = "ALL_PAGES";


    public Map<String, Set<String>> roleToFilesMap = new HashMap<>();

    public RolesMap() {
        roleToFilesMap.put(ROLE_1, new HashSet<>(Arrays.asList("MainPage.jsp", "page1.jsp")));
        roleToFilesMap.put(ROLE_2, new HashSet<>(Arrays.asList("MainPage.jsp", "page2.jsp")));
        roleToFilesMap.put(ROLE_3, new HashSet<>(Arrays.asList("MainPage.jsp", "page3.jsp")));
        roleToFilesMap.put(ROLE_4, new HashSet<>(Arrays.asList("MainPage.jsp", "page1.jsp", "page2.jsp", "page3.jsp")));
    }
}
