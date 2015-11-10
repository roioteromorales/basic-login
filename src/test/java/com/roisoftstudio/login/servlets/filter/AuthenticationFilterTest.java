package com.roisoftstudio.login.servlets.filter;


import com.roisoftstudio.Constants;
import com.roisoftstudio.login.users.RolesMap;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.roisoftstudio.Constants.*;
import static org.mockito.Mockito.*;

public class AuthenticationFilterTest {

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private HttpSession sessionMock;
    private FilterChain filterChainMock;
    private AuthenticationFilter authenticationFilter;

    @Before
    public void setUp() throws Exception {
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        sessionMock = mock(HttpSession.class);
        filterChainMock = mock(FilterChain.class);
        authenticationFilter = new AuthenticationFilter();
    }

    @Test
    public void filterShouldRedirectToLoginPage_whenIsNotLoginPageAndIsNotProtected() throws Exception {
        when(requestMock.getRequestURI()).thenReturn("fadsfasd");

        authenticationFilter.doFilter(requestMock, responseMock, filterChainMock);
        verify(responseMock, times(1)).sendRedirect(LOGIN_PAGE);
    }

    @Test
    public void filterShouldIgnoreFilter_whenRequestUriIsLogin() throws Exception {
        when(requestMock.getRequestURI()).thenReturn(LOGIN_PAGE);

        authenticationFilter.doFilter(requestMock, responseMock, filterChainMock);
        verify(filterChainMock, times(1)).doFilter(requestMock, responseMock);
    }

    @Test
    public void filterShouldRedirectToMainPage_whenIsProtectedAddressButNotLogged() throws Exception {
        when(requestMock.getRequestURI()).thenReturn(MAIN_PAGE);

        authenticationFilter.doFilter(requestMock, responseMock, filterChainMock);
        verify(responseMock, times(1)).sendRedirect(FOLDER_UP + LOGIN_PAGE);
    }

    @Test
    public void filterShouldRedirectToMainPage_whenIsLoggedButWrongAddress() throws Exception {
        when(requestMock.getRequestURI()).thenReturn(MAIN_PAGE + "fasdfas");
        when(requestMock.getSession(false)).thenReturn(sessionMock);

        authenticationFilter.doFilter(requestMock, responseMock, filterChainMock);
        verify(responseMock, times(1)).sendRedirect(MAIN_PAGE);
    }

    @Test
    public void filterShouldRedirectToUnauthorizedPage_whenIsLoggedButHasWrongRole() throws Exception {
        when(requestMock.getRequestURI()).thenReturn("/" + Constants.PROTECTED_PATH + "/" + "page1.jsp");
        when(requestMock.getSession(false)).thenReturn(sessionMock);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(new HashSet<>());

        authenticationFilter.doFilter(requestMock, responseMock, filterChainMock);
        verify(responseMock, times(1)).sendRedirect(UNAUTHORIZED_ROLE_PAGE);
    }

    @Test
    public void filterShouldIgnoreFilter_whenIsLoggedAndHasGoodRole() throws Exception {
        when(requestMock.getRequestURI()).thenReturn("/" + Constants.PROTECTED_PATH + "/" + "page1.jsp");
        when(requestMock.getSession(false)).thenReturn(sessionMock);
        Set<String> sessionRoles = new HashSet<>(Arrays.asList(RolesMap.ROLE_1));
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(sessionRoles);

        authenticationFilter.doFilter(requestMock, responseMock, filterChainMock);
        verify(filterChainMock, times(1)).doFilter(requestMock, responseMock);
    }
}