package com.roisoftstudio.login.servlets.filter;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;

import static com.roisoftstudio.Constants.PARAMETER_ROLES;
import static com.roisoftstudio.login.users.RolesMap.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleCheckerTest {

    private RoleChecker roleChecker;

    @Before
    public void setUp() throws Exception {
        roleChecker = new RoleChecker();
    }

    @Test
    public void userDoesNotHaveValidRole_whenRolesAreEmpty() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(new HashSet<>());
        assertThat(roleChecker.userHasValidRole(sessionMock, "page1.jsp"), is(false));
    }

    @Test
    public void userDoesNotHaveValidRole_whenRolesAreDifferent() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(new HashSet<>(Arrays.asList(ROLE_2)));
        assertThat(roleChecker.userHasValidRole(sessionMock, "page1.jsp"), is(false));
    }

    @Test
    public void userHasValidRole_whenRolesAreTheSame() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(new HashSet<>(Arrays.asList(ROLE_1)));
        assertThat(roleChecker.userHasValidRole(sessionMock, "/protectedPath/page1.jsp"), is(true));
    }
}