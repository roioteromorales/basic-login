package com.roisoftstudio.login.servlets;


import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

import static com.roisoftstudio.Constants.*;
import static com.roisoftstudio.login.servlets.ServletMocksHelper.prepareRequestMock;
import static com.roisoftstudio.login.servlets.ServletMocksHelper.verifyParameters;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class LoginServletTest {

    public static final String OUTPUT_FILE = "output";
    public static final String ADMIN = "admin";
    public static final String PASSWORD = "password";

    @Test
    public void servletDoGet_shouldRedirectToLoginPage() throws Exception {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        new LoginServlet().doGet(requestMock, responseMock);

        verify(responseMock, atLeast(1)).sendRedirect(LOGIN_PAGE);
    }

    @Test
    public void servletShouldRedirectToSuccess_whenSuccessfulLogin() throws Exception {
        HttpServletRequest requestMock = prepareRequestMock(ADMIN, PASSWORD);

        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        new LoginServlet().doPost(requestMock, responseMock);

        verifyParameters(requestMock);
        verify(responseMock, atLeast(1)).sendRedirect(PROTECTED_PATH + MAIN_PAGE);
    }

    @Test
    public void servletShouldPrintCredentialsError_whenUserInputsWrongCredentials() throws Exception {
        HttpServletRequest requestMock = prepareRequestMock("InvalidUser", "InvalidPassword");
        RequestDispatcher rdMock = mock(RequestDispatcher.class);
        when(requestMock.getRequestDispatcher(LOGIN_PAGE)).thenReturn(rdMock);

        PrintWriter writer = new PrintWriter(OUTPUT_FILE);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
        when(responseMock.getWriter()).thenReturn(writer);

        new LoginServlet().doPost(requestMock, responseMock);

        verifyParameters(requestMock);
        writer.flush();
        String servletOutput = FileUtils.readFileToString(new File(OUTPUT_FILE), "UTF-8");
        assertThat(servletOutput, containsString(INVALID_CREDENTIALS_ERROR));
    }
}