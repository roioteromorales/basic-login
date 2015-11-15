package com.roisoftstudio.storage.db;

import com.roisoftstudio.login.users.User;

import java.util.*;

import static com.roisoftstudio.storage.db.InMemoryRolesDB.*;

public class InMemoryCredentialsDB {

    Map<String, String> credentialsDB = new HashMap<>();
    Map<String, Set<String>> rolesDB = new HashMap<>();

    public InMemoryCredentialsDB() {
        addInitialUser("admin", "password", ROLE_1, ROLE_2, ROLE_3);
        addInitialUser("user1", "pass1", ROLE_1);
        addInitialUser("user2", "pass2", ROLE_2);
        addInitialUser("user3", "pass3", ROLE_3);
    }

    private void addInitialUser(String username, String password, String... roles) {
        credentialsDB.put(username, password);
        addRoles(username, roles);
    }

    public void addUser(String username, String password) throws DuplicatedKeyException {
        if (credentialsDB.containsKey(username))
            throw new DuplicatedKeyException();
        else
            credentialsDB.put(username, password);
    }

    public void addRoles(String username, String... roles) {
        Set<String> userRoles = rolesDB.get(username);
        if (userRoles == null) {
            userRoles = new HashSet<>();
        }
        Collections.addAll(userRoles, roles);
        rolesDB.put(username, userRoles);
    }

    public boolean contains(String username) {
        return credentialsDB.containsKey(username);
    }

    public String getPassword(String username) {
        return credentialsDB.get(username);
    }

    public boolean hasRole(User user, String role) {
        Set<String> roles = rolesDB.get(user.getUsername());
        return roles != null && roles.contains(role);
    }

    public Set<String> getRoles(String username) {
        return rolesDB.get(username);
    }
}
