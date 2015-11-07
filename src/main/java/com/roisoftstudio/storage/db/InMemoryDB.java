package com.roisoftstudio.storage.db;

import com.roisoftstudio.login.users.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.roisoftstudio.login.users.Roles.*;

public class InMemoryDB {

    Map<String, String> credentialsDB = new HashMap<>();
    Map<String, Set<String>> rolesDB = new HashMap<>();

    public InMemoryDB() {
        credentialsDB.put("admin", "password");
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add(ROLE_1);
        adminRoles.add(ROLE_2);
        adminRoles.add(ROLE_3);
        addRoles("admin", adminRoles);
        credentialsDB.put("aaa", "aaa");
        addRole("aaa", ROLE_1);
        credentialsDB.put("bbb", "bbb");
        addRole("bbb", ROLE_2);
        credentialsDB.put("ccc", "ccc");
        addRole("ccc", ROLE_3);
    }



    public void addUser(String username, String password) throws DuplicatedKeyException {
        if (credentialsDB.containsKey(username))
            throw new DuplicatedKeyException();
        else
            credentialsDB.put(username, password);
    }

    public void addRoles(String username, Set<String> roles) {//to do make it compatible String...
        Set<String> userRoles = rolesDB.get(username);
        if(userRoles == null){
            userRoles = new HashSet<>();
        }
        userRoles.addAll(roles);
        rolesDB.put(username, userRoles);
    }

    public void addRole(String username, String role) {
        Set<String> userRoles = rolesDB.get(username);
        if(userRoles == null){
            userRoles = new HashSet<>();
        }
        userRoles.add(role);
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
