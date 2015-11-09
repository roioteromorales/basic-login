package com.roisoftstudio.login.servlets.validators;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static com.roisoftstudio.Constants.LOGIN_PAGE;
import static com.roisoftstudio.Constants.PARAMETER_USERNAME;
import static com.roisoftstudio.Constants.UNAUTHORIZED_ROLE_PAGE;

public class SessionValidator {

    public void validateSession(HttpSession session, HttpServletResponse response, Set<String> allowedRoles) throws IOException {
        if (userIsNotLogged(session)){
            response.sendRedirect(LOGIN_PAGE);
        }else{
            if (new RoleSessionValidator().isRoleInvalid(session,allowedRoles))
                response.sendRedirect(UNAUTHORIZED_ROLE_PAGE);
        }
    }

    private boolean userIsNotLogged(HttpSession session) {
        return session.getAttribute(PARAMETER_USERNAME) == null;
    }

    public String getSessionUsername(HttpSession session){
        return (String) session.getAttribute(PARAMETER_USERNAME);
    }

}
