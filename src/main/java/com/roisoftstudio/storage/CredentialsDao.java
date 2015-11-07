package com.roisoftstudio.storage;

import com.roisoftstudio.login.users.User;

import java.util.Set;

public interface CredentialsDao {
    void addUser(User user) throws DaoException;
    void addRole(User user, String role);
    boolean userExist(User user);
    boolean authenticate(User user);
    boolean hasRole(User user, String role);

    Set<String> getRoles(User user);
}
