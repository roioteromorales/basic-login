package com.roisoftstudio.login.servlets.validators;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

import static com.roisoftstudio.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.*;

public class SessionValidatorTest {

    private SessionValidator SessionValidator;

    @Before
    public void setUp() throws Exception {
        SessionValidator = new SessionValidator();
    }

    @Test
    public void validatorShouldRedirect_whenSessionIsInvalid() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        SessionValidator.validateSession(sessionMock, responseMock, null);

        verify(responseMock, atLeast(1)).sendRedirect(LOGIN_PAGE);
    }
    @Test
    public void validatorShouldRedirect_whenSessionIsValidButRoleIsInvalid() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
        when(sessionMock.getAttribute(PARAMETER_USERNAME)).thenReturn("username");

        HashSet<String> allowedRoles = new HashSet<>();
        allowedRoles.add("RandomRole");

        SessionValidator.validateSession(sessionMock, responseMock, allowedRoles);

        verify(responseMock, atLeast(1)).sendRedirect(UNAUTHORIZED_ROLE_PAGE);
    }
    @Test
    public void getSessionUsername_returnsTheSessionUsernameValue() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_USERNAME)).thenReturn("username");

        assertThat( SessionValidator.getSessionUsername(sessionMock), is("username"));
    }

    @Test
    public void getSessionUsername_shouldReturnNullIfNoSessionExist() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);

        assertThat( SessionValidator.getSessionUsername(sessionMock), is(nullValue()));
    }

}