<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="com.roisoftstudio.login.users.Roles, java.util.Set, java.util.HashSet" %>
<%  Set<String> allowedRoles = new HashSet<String>();
    allowedRoles.add(Roles.ROLE_3); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title>Page 3</title>
        <style><%@include file="/pages/css/dialogs.css" %></style>
    </head>
    <body>
        <%@include file="/session/sessionValidator.jsp" %>

        <%@include file="/pages/htmlTemplates/pagesMenu.html" %>

        <%@ page import="com.roisoftstudio.Constants" %>
        <div class="isa_success" id="successBox">
             <i class="fa fa-check"></i>
                <%=Constants.ROLE_PROTECTED_MSG %><br>
                Hi <%=username %>, your role is <%=userRoles %><br>
                Allowed Roles:  <%=allowedRoles %>.<br>
        </div>

        <%@include file="/pages/htmlTemplates/sessionButtons.html" %>

    </body>
</html>