package com.roisoftstudio.login.servlets;

import com.roisoftstudio.login.users.User;
import com.roisoftstudio.storage.CredentialsDao;
import com.roisoftstudio.storage.InMemoryDBCredentialsDao;
import com.roisoftstudio.storage.db.InMemoryDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.roisoftstudio.Constants.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final CredentialsDao credentialsDao;

    public LoginServlet() {
        credentialsDao = new InMemoryDBCredentialsDao(new InMemoryDB());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        if(username == null && password == null){
            RequestDispatcher rd = request.getRequestDispatcher(LOGIN_PAGE);
            response.getWriter().println(NO_CREDENTIALS_ERROR_MSG);
            rd.include(request, response);
        }else{
            User user = new User(username, password);

            if (credentialsDao.authenticate(user)) {
                createSession(request, user);
                createCookie(response, username);
                response.sendRedirect("MainPage.jsp");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(LOGIN_PAGE);
                response.getWriter().println(INVALID_CREDENTIALS_ERROR_MSG);
                rd.include(request, response);
            }
        }
    }

    private void createCookie(HttpServletResponse response, String user) {
        Cookie userCookie = new Cookie(PARAMETER_USERNAME, user);
        userCookie.setMaxAge(FIVE_MINUTES);
        response.addCookie(userCookie);
    }

    private void createSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(PARAMETER_USERNAME, user.getUsername());
        session.setAttribute(PARAMETER_ROLES, credentialsDao.getRoles(user));
        session.setMaxInactiveInterval(FIVE_MINUTES);
    }

}
