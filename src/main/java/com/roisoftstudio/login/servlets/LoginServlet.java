package com.roisoftstudio.login.servlets;

import com.roisoftstudio.login.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.roisoftstudio.Constants.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final SessionManager sessionManager;

    public LoginServlet() {
        sessionManager = new SessionManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(LOGIN_PAGE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        if (sessionManager.login(username, password)) {
            sessionManager.createCookie(username);
            sessionManager.fillSessionParameters(request.getSession(),username);
                    response.sendRedirect(PROTECTED_PATH + MAIN_PAGE);
        } else
            showInvalidCredentialsError(request, response);
    }

    private void showInvalidCredentialsError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(LOGIN_PAGE);
        response.getWriter().println(INVALID_CREDENTIALS_ERROR);
        rd.include(request, response);
    }

}
