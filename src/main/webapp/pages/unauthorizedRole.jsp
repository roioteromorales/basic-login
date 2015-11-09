<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title>Unauthorized Role</title>
        <style><%@include file="/pages/css/dialogs.css" %></style>

    </head>
    <body>
           <%@include file="/pages/htmlTemplates/pagesMenu.html" %>
        <%@ page import="com.roisoftstudio.Constants" %>

            <div class="isa_error" id="errorBox">
               <i class="fa fa-times-circle"></i>
               <%=Constants.UNAUTHORIZED_ERROR_MESSAGE %>
            </div>

        <div class="error"></div>
        <%@include file="/pages/htmlTemplates/sessionButtons.html" %>

    </body>
</html>