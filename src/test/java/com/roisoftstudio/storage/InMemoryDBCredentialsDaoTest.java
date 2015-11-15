package com.roisoftstudio.storage;

import com.roisoftstudio.login.users.User;
import com.roisoftstudio.storage.db.InMemoryDB;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InMemoryDBCredentialsDaoTest {

    private InMemoryDB inMemoryDB;
    private InMemoryDBCredentialsDao credentialsDao;
    private User testUser;

    @Before
    public void setUp() throws Exception {
        inMemoryDB = new InMemoryDB();
        credentialsDao = new InMemoryDBCredentialsDao(inMemoryDB);
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
}