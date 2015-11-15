package com.roisoftstudio.login;


import com.roisoftstudio.login.users.User;
import com.roisoftstudio.storage.CredentialsDao;
import com.roisoftstudio.storage.InMemoryDBCredentialsDao;
import com.roisoftstudio.storage.db.InMemoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import static com.roisoftstudio.Constants.*;

public class SessionManager {
    private final static Logger LOGGER = LogManager.getLogger(SessionManager.class);
    private final CredentialsDao credentialsDao;

    public SessionManager() {
        credentialsDao = new InMemoryDBCredentialsDao(new InMemoryDB());
    }

    public boolean login(String username, String password) {
        return credentialsDao.authenticate(new User(username, password));
    }

    public void fillSessionParameters(HttpSession session, String username) {
        session.setAttribute(PARAMETER_USERNAME, username);
        session.setAttribute(PARAMETER_ROLES, credentialsDao.getRoles(new User(username, "")));
        session.setMaxInactiveInterval(FIVE_MINUTES);
    }

    public Cookie createCookie(String user) {
        Cookie userCookie = new Cookie(PARAMETER_USERNAME, user);
        userCookie.setMaxAge(FIVE_MINUTES);
        return userCookie;
    }
}
