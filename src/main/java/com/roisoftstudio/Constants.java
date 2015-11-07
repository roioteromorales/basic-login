package com.roisoftstudio;

public class Constants {
    public static final String INVALID_CREDENTIALS_ERROR_MSG = "<div class=\"isa_error\">\n" +
                                                                "<i class=\"fa fa-times-circle\"></i>\n" +
                                                                "Either user name or password is wrong\n" +
                                                                "</div>";
    public static final String NO_CREDENTIALS_ERROR_MSG = "<font color=red>You need to login.</font>";
    public static final int FIVE_MINUTES = 300;
    public static final String LOGIN_PAGE = "/login.html";
    public static final String UNAUTHORIZED_ROLE_PAGE = "/pages/unauthorizedRole.jsp";

    public static final String PARAMETER_ROLES = "roles";
    public static final String PARAMETER_USERNAME = "username";
    public static final String PARAMETER_PASSWORD = "password";
}
