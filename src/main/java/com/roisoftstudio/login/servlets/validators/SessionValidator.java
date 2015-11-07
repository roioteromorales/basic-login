package com.roisoftstudio.login.servlets.validators;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static com.roisoftstudio.Constants.LOGIN_PAGE;
import static com.roisoftstudio.Constants.PARAMETER_USERNAME;

public class SessionValidator {

    public void validateSession(HttpSession session, HttpServletResponse response, Set<String> allowedRoles) throws IOException {
        if (userIsNotLogged(session)){
            response.sendRedirect(LOGIN_PAGE);
        }else{
            new RoleSessionValidator().validateSessionRole(session,response,allowedRoles);
        }
    }

    private boolean userIsNotLogged(HttpSession session) {
        return session.getAttribute(PARAMETER_USERNAME) == null;
    }

    public String getSessionUsername(HttpSession session){
        return (String) session.getAttribute(PARAMETER_USERNAME);
    }

}
