package com.roisoftstudio.login.users;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();

    public User(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        Collections.addAll(this.roles, roles);
    }

    public void addRoles(String... roles){
        Collections.addAll(this.roles, roles);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
