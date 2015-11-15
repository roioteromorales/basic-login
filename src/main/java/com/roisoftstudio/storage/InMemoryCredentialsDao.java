package com.roisoftstudio.storage;


import com.roisoftstudio.login.users.User;
import com.roisoftstudio.storage.db.DuplicatedKeyException;
import com.roisoftstudio.storage.db.InMemoryCredentialsDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class InMemoryCredentialsDao implements CredentialsDao {
    private final static Logger LOGGER = LogManager.getLogger(InMemoryCredentialsDao.class);
    private InMemoryCredentialsDB inMemoryCredentialsDB;

    public InMemoryCredentialsDao(InMemoryCredentialsDB inMemoryCredentialsDB) {
        this.inMemoryCredentialsDB = inMemoryCredentialsDB;
    }

    @Override
    public void addUser(User user) throws DaoException {
        try {
            inMemoryCredentialsDB.addUser(user.getUsername(), user.getPassword());
            inMemoryCredentialsDB.addRoles(user.getUsername(), user.getRoles().stream().toArray(String[]::new));
        } catch (DuplicatedKeyException e) {
            LOGGER.error("Username already exist in DB", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void addRoles(User user, String... roles) {
        inMemoryCredentialsDB.addRoles(user.getUsername(), roles);
    }

    @Override
    public boolean userExist(User user) {
        return inMemoryCredentialsDB.contains(user.getUsername());
    }

    @Override
    public boolean authenticate(User user) {
        return inMemoryCredentialsDB.contains(user.getUsername()) &&
                inMemoryCredentialsDB.getPassword(user.getUsername()).equals(user.getPassword());
    }

    @Override
    public boolean hasRole(User user, String role) {
        return inMemoryCredentialsDB.hasRole(user, role);
    }

    @Override
    public Set<String> getRoles(User user) {
        return inMemoryCredentialsDB.getRoles(user.getUsername());
    }
}
