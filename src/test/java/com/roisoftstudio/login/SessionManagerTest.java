package com.roisoftstudio.login;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;

import static com.roisoftstudio.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class SessionManagerTest {

    private SessionManager sessionManager;

    @Before
    public void setUp() throws Exception {
        sessionManager = new SessionManager();
    }

    @Test
    public void userWithInvalidCredentials_cannotLogin() throws Exception {
        assertThat(sessionManager.login("invalidUsername", "invalidPassword"), is(false));
    }

    @Test
    public void userWithValidCredentials_canLogin() throws Exception {
        assertThat(sessionManager.login("admin", "password"), is(true));
    }

    @Test
    public void fillSession_shouldAddUsernameRolesAndIntervalToTheSession() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);

        sessionManager.fillSessionParameters(sessionMock, "admin");

        verify(sessionMock, times(1)).setAttribute(PARAMETER_USERNAME, "admin");
        verify(sessionMock, times(1)).setAttribute(PARAMETER_ROLES, new HashSet<>(Arrays.asList("PAG_2", "PAG_1", "PAG_3")));
        verify(sessionMock, times(1)).setMaxInactiveInterval(FIVE_MINUTES);
    }

    @Test
    public void createCookie_returnsCookieWithCorrectValues() throws Exception {
        Cookie cookie = sessionManager.createCookie("admin");
        assertThat(cookie.getName(), is(PARAMETER_USERNAME));
        assertThat(cookie.getValue(), is("admin"));
        assertThat(cookie.getMaxAge(), is(FIVE_MINUTES));
    }
}