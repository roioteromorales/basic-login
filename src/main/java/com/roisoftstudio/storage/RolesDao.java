package com.roisoftstudio.storage;

import java.util.Set;

public interface RolesDao {
    void addPages(String role, String... pages);
    Set<String> getPages(String role);
}
