package com.roisoftstudio.storage;


import com.roisoftstudio.login.users.User;
import com.roisoftstudio.storage.db.DuplicatedKeyException;
import com.roisoftstudio.storage.db.InMemoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class InMemoryDBCredentialsDao implements CredentialsDao {
    private final static Logger LOGGER = LogManager.getLogger(InMemoryDBCredentialsDao.class);
    private InMemoryDB inMemoryDB;

    public InMemoryDBCredentialsDao(InMemoryDB inMemoryDB) {
        this.inMemoryDB = inMemoryDB;
    }

    @Override
    public void addUser(User user) throws DaoException {
        try {
            inMemoryDB.addUser(user.getUsername(), user.getPassword());
            inMemoryDB.addRoles(user.getUsername(), user.getRoles().stream().toArray(String[]::new));
        } catch (DuplicatedKeyException e) {
            LOGGER.error("Username already exist in DB", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void addRoles(User user, String... roles) {
        inMemoryDB.addRoles(user.getUsername(), roles);
    }

    @Override
    public boolean userExist(User user) {
        return inMemoryDB.contains(user.getUsername());
    }

    @Override
    public boolean authenticate(User user) {
        return inMemoryDB.contains(user.getUsername()) &&
                inMemoryDB.getPassword(user.getUsername()).equals(user.getPassword());
    }

    @Override
    public boolean hasRole(User user, String role) {
        return inMemoryDB.hasRole(user, role);
    }

    @Override
    public Set<String> getRoles(User user) {
        return inMemoryDB.getRoles(user.getUsername());
    }
}
