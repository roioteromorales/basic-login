package com.roisoftstudio.login.servlets;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.roisoftstudio.Constants.LOGIN_PAGE;
import static com.roisoftstudio.Constants.PARAMETER_USERNAME;
import static org.mockito.Mockito.*;

public class LogoutServletTest {

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;

    @Before
    public void setUp() throws Exception {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
    }

    @Test
    public void servletDoGet_shouldRedirectToLoginPage() throws Exception {
        HttpServletRequest requestMock =  mock(HttpServletRequest.class);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        new LogoutServlet().doGet(requestMock, responseMock);

        verify(responseMock, atLeast(1)).sendRedirect(LOGIN_PAGE);
    }

    @Test
    public void servletShouldDeleteCookie_whenLogout() throws Exception {
        Cookie cookieMock = prepareCookieMock(requestMock);

        new LogoutServlet().doPost(requestMock, responseMock);

        verify(responseMock, atLeast(1)).addCookie(cookieMock);
        verify(cookieMock, atLeast(1)).setMaxAge(0);
    }

    @Test
    public void servletShouldInvalidateSession_whenLogout() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(requestMock.getSession(false)).thenReturn(sessionMock);

        new LogoutServlet().doPost(requestMock, responseMock);

        verify(sessionMock, atLeast(1)).invalidate();
    }

    @Test
    public void servletShouldRedirectToLoginPage_whenLogout() throws Exception {
        new LogoutServlet().doPost(requestMock, responseMock);

        verify(responseMock, atLeast(1)).sendRedirect(LOGIN_PAGE);
    }

    private Cookie prepareCookieMock(HttpServletRequest requestMock) {
        Cookie cookieMock = mock(Cookie.class);
        when(cookieMock.getName()).thenReturn(PARAMETER_USERNAME);
        when(requestMock.getCookies()).thenReturn(new Cookie[]{cookieMock});
        return cookieMock;
    }
}