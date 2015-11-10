<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title>Page 2</title>
        <style><%@include file="../css/dialogs.css" %></style>
    </head>
    <body>
        <%@include file="/protectedPath/htmlTemplates/pagesMenu.html" %>

        <%@ page import="com.roisoftstudio.Constants" %>
        <div class="isa_success" id="successBox">
             <i class="fa fa-check"></i>
                Hola  <%=session.getAttribute(Constants.PARAMETER_USERNAME) %>.
                <%=Constants.ROLE_PROTECTED_MSG %>
        </div>

        <%@include file="/protectedPath/htmlTemplates/sessionButtons.html" %>

    </body>
</html>