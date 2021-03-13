<%-- 
    Document   : profile_client
    Created on : Mar 8, 2021, 3:31:35 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Client edit profile    

        <!--URL: client/profile-->

        <form:form action="${pageContext.request.contextPath}/client/profile" 
                   method="post" modelAttribute="client">
            <p>
                <form:label path="fname">Last Name:</form:label><br/>
                <form:input path="fname" placeholder="first name"/>
                <form:errors path="fname"/>
            </p>
            <p>
                <form:label path="lname">First Name:</form:label><br/>
                <form:input path="lname" placeholder="last name"/>
                <form:errors path="lname"/>
            </p>
            <p>
                <form:label path="tel">Telephone:</form:label><br/>
                <form:input path="tel" placeholder="tel"/>
                <form:errors path="tel"/>
            </p>
             <p><a href="">Update Credentials</a></p>
            
            <input type="submit" value="Update">
        </form:form>



    </body>
</html>
