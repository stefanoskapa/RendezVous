<!DOCTYPE HTML >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Register Page</title>
    </head>
    <body>
        <!--URL: /client-register-->

        <!--form register for client, POST, /client-register-->
        <!--email*, fname*, lname*, tel, password*-->
        <!--me tin epitixi dimiourgia redirect sto /login-->

        <div align="center">
            <h3>Registration Form</h3>
            <c:if test="${userExistsError != null}">
                ${userExistsError}
            </c:if>
            <form:form action="${pageContext.request.contextPath}/client-register" 
                       method="post" modelAttribute="newClient">
                
                <p>
                    <form:input path="userId.email" placeholder="email"/>
                </p>
                <p>
                    <form:input path="userId.password" placeholder="password"/>
                </p>
                <p>
                    <form:input path="fname" placeholder="first name"/>
                </p>
                <p>
                    <form:input path="lname" placeholder="last name"/>
                </p>
                <p>
                    <form:input path="tel" placeholder="tel"/>
                </p>
                
                <input type="submit" value="Register">
            </form:form>
        </div>
    </body>
</html>