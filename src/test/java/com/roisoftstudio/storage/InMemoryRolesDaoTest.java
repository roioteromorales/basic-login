package com.roisoftstudio.storage;

import com.roisoftstudio.storage.db.InMemoryRolesDB;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InMemoryRolesDaoTest {

    private RolesDao rolesDao;

    @Before
    public void setUp() throws Exception {
        rolesDao = new InMemoryRolesDao(new InMemoryRolesDB());
    }

    @Test
    public void addPages_shouldAddPageToSpecificRole() throws Exception {
        rolesDao.addPages("role1", "page1");
        assertThat(rolesDao.getPages("role1"), is(new HashSet<>(Arrays.asList("page1"))));
    }

    @Test
    public void addPages_shouldAddVariousPagesToSpecificRole() throws Exception {
        rolesDao.addPages("role1", "page1","page2");
        assertThat(rolesDao.getPages("role1"), is(new HashSet<>(Arrays.asList("page2","page1"))));
    }
}