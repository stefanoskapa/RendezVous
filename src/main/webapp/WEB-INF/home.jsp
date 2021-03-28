<%-- 
    Document   : newjsp
    Created on : Mar 7, 2021, 12:42:47 AM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <!--URL: / (xoris tipota)-->
        <h1>Hello World!!</h1>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <a href="${pageContext.request.contextPath}/client-register">Register as user</a>
        <a href="${pageContext.request.contextPath}/company-register">Company register</a>    
    </body>
</html>
