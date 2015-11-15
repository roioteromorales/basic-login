package com.roisoftstudio.storage;

import com.roisoftstudio.login.users.User;
import com.roisoftstudio.storage.db.InMemoryCredentialsDB;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InMemoryCredentialsDaoTest {

    private InMemoryCredentialsDao credentialsDao;
    private User testUser;

    @Before
    public void setUp() throws Exception {
        credentialsDao = new InMemoryCredentialsDao(new InMemoryCredentialsDB());
        testUser = new User("testUser", "testPassword");
    }

    @Test
    public void canAddUsers() throws Exception {
        credentialsDao.addUser(testUser);

        assertThat(credentialsDao.userExist(testUser), is(true));
    }

    @Test
    public void userRolesAreAdded_whenCreatingUser() throws Exception {
        testUser.addRoles("ROLE1");
        credentialsDao.addUser(testUser);

        assertThat(credentialsDao.userExist(testUser), is(true));
        assertThat(credentialsDao.hasRole(testUser, "ROLE1"), is(true));
    }

    @Test (expected = DaoException.class)
    public void tryingToAddDuplicateUsers_shouldThrowException() throws Exception {
        credentialsDao.addUser(testUser);
        credentialsDao.addUser(testUser);
    }

    @Test
    public void userCanBeAuthenticatedIfExists() throws Exception {
        credentialsDao.addUser(testUser);

        assertThat(credentialsDao.authenticate(testUser), is(true));
    }

    @Test
    public void rolesCanBeAddedToExistingUsers() throws Exception {
        credentialsDao.addRoles(testUser, "newRole");

        assertThat(credentialsDao.hasRole(testUser, "newRole"), is(true));
    }

    @Test
    public void getRoles_shouldRetrieveAllRolesForAnUser() throws Exception {
        credentialsDao.addRoles(testUser, "role1", "role2");

        assertThat(credentialsDao.getRoles(testUser), is(new HashSet<>(Arrays.asList("role2","role1"))));
    }
}