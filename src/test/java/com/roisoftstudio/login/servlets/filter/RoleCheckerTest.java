package com.roisoftstudio.login.servlets.filter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.roisoftstudio.storage.db.InMemoryRolesDB.ROLE_1;
import static com.roisoftstudio.storage.db.InMemoryRolesDB.ROLE_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoleCheckerTest {

    private RoleChecker roleChecker;

    @Before
    public void setUp() throws Exception {
        roleChecker = new RoleChecker();
    }

    @Test
    public void userDoesNotHaveValidRole_whenRolesAreEmpty() throws Exception {
        assertThat(roleChecker.userHasValidRole(new HashSet<>(), "page1.jsp"), is(false));
    }

    @Test
    public void userDoesNotHaveValidRole_whenRolesAreDifferent() throws Exception {
        assertThat(roleChecker.userHasValidRole(new HashSet<>(Arrays.asList(ROLE_2)), "page1.jsp"), is(false));
    }

    @Test
    public void userHasValidRole_whenRolesAreTheSame() throws Exception {
        assertThat(roleChecker.userHasValidRole(new HashSet<>(Arrays.asList(ROLE_1)), "/protectedPath/page1.jsp"), is(true));
    }
}