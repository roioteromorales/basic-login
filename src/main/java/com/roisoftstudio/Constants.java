package com.roisoftstudio;

public class Constants {
    public static final int FIVE_MINUTES = 300;
    public static final String ROLE_PROTECTED_MSG = "This Page is role protected.";
    public static final String UNAUTHORIZED_ERROR_MESSAGE = "You dont have access to this page";
    public static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Either user name or password is wrong";
    public static final String INVALID_CREDENTIALS_ERROR = "<div class=\"isa_error\" id=\"errorBox\">\n" +
            "<i class=\"fa fa-times-circle\"></i>\n" +
            INVALID_CREDENTIALS_ERROR_MESSAGE + "\n" +
            "</div>";

    public static final String PARAMETER_ROLES = "roles";
    public static final String PARAMETER_USERNAME = "username";
    public static final String PARAMETER_PASSWORD = "password";

    public static final String LOGIN_SERVLET_PATH = "LoginServlet";
    public static final String LOGOUT_SERVLET_PATH = "LogoutServlet";
    public static final String PROTECTED_PATH = "protectedPath";


    public static final String UNAUTHORIZED_ROLE_PAGE = "unauthorizedRole.jsp";
    public static final String LOGIN_PAGE = "login.html";
    public static final String MAIN_PAGE = PROTECTED_PATH + "/MainPage.jsp";
    public static final String FOLDER_UP = "../";
}
