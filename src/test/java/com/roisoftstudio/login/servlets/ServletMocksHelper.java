package com.roisoftstudio.login.servlets;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.roisoftstudio.Constants.PARAMETER_PASSWORD;
import static com.roisoftstudio.Constants.PARAMETER_USERNAME;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ServletMocksHelper {
    public static void verifyParameters(HttpServletRequest requestMock) {
        verify(requestMock, atLeast(1)).getParameter(PARAMETER_USERNAME);
        verify(requestMock, atLeast(1)).getParameter(PARAMETER_PASSWORD);
    }

    public static HttpServletRequest prepareRequestMock(String username, String password) {
        HttpSession sessionMock = mock(HttpSession.class);
        return prepareRequestMock(username, password, sessionMock);
    }

    public static HttpServletRequest prepareRequestMock(String username, String password, HttpSession sessionMock) {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameter(PARAMETER_USERNAME)).thenReturn(username);
        when(requestMock.getParameter(PARAMETER_PASSWORD)).thenReturn(password);
        when(requestMock.getSession()).thenReturn(sessionMock);

        return requestMock;
    }
}
