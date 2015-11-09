package com.roisoftstudio.login.servlets.validators;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;

import static com.roisoftstudio.Constants.PARAMETER_ROLES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleSessionValidatorTest {
    private RoleSessionValidator roleSessionValidator;

    @Before
    public void setUp() throws Exception {
        roleSessionValidator = new RoleSessionValidator();
    }

    @Test
    public void roleShouldBeValid_ifNotRestrictionsAreSpecified() throws Exception {
        assertThat(roleSessionValidator.isRoleInvalid(mock(HttpSession.class), null), is(false));
    }

    @Test
    public void roleShouldBeValid_ifSessionContainsAValidRole() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(new HashSet<>(Arrays.asList("allowedRole", "anotherRole")));

        HashSet<String> allowedRoles = new HashSet<>(Arrays.asList("allowedRole", "allowedRole2"));

        assertThat(roleSessionValidator.isRoleInvalid(sessionMock, allowedRoles), is(false));
    }

    @Test
    public void roleShouldBeInvalid_ifSessionRolesAreNull() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(null);

        HashSet<String> allowedRoles = new HashSet<>(Arrays.asList("allowedRole", "allowedRole2"));

        assertThat(roleSessionValidator.isRoleInvalid(sessionMock, allowedRoles), is(true));
    }

    @Test
    public void roleShouldBeInvalid_ifSessionDoesNotContainAValidRole() throws Exception {
        HttpSession sessionMock = mock(HttpSession.class);
        when(sessionMock.getAttribute(PARAMETER_ROLES)).thenReturn(new HashSet<>(Arrays.asList("invalidRole", "anotherRole")));

        HashSet<String> allowedRoles = new HashSet<>(Arrays.asList("allowedRole", "allowedRole2"));

        assertThat(roleSessionValidator.isRoleInvalid(sessionMock, allowedRoles), is(true));
    }
}