<%-- 
    Document   : dashboard_client
    Created on : Mar 8, 2021, 3:19:09 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        dashboard client
        <br/>
        <!--arxiki selida tou logarismenou client-->
        <!--URL: client/dashboard-->

        <!--prosopiko imerologio client-->
        <!--ta data tha erxontai me ajax-->


        <!--link kleisimatos rantevou gia to client/comp-select-->
        <!--link alagis stoixeion tou client gia to  URL: client/profile-->  

        welcome <sec:authentication property="name"/>
        
        
    </body>
</html>
