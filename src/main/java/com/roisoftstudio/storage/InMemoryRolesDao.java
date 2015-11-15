package com.roisoftstudio.storage;

import com.roisoftstudio.storage.db.InMemoryRolesDB;

import java.util.Set;

public class InMemoryRolesDao implements RolesDao {
    private InMemoryRolesDB rolesDB;

    public InMemoryRolesDao(InMemoryRolesDB rolesDB) {
        this.rolesDB = rolesDB;
    }

    @Override
    public void addPages(String role, String... pages) {
        rolesDB.addPages(role, pages);
    }

    @Override
    public Set<String> getPages(String role) {
        return rolesDB.getPages(role);
    }
}
