<%@ page import="com.roisoftstudio.login.servlets.validators.SessionValidator,com.roisoftstudio.login.servlets.validators.RoleSessionValidator,java.util.Set" %>
<%
    SessionValidator sessionValidator = new SessionValidator();
    sessionValidator.validateSession(session, response, allowedRoles);

    String username = sessionValidator.getSessionUsername(session);
    Set<String> userRoles = new RoleSessionValidator().getSessionRoles(session);

%>
