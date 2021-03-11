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
        <div align="center">
            <h3>Registration Form</h3>
            <c:if test="${userExistsError != null}">
                ${userExistsError}
            </c:if>
            <form:form action="${pageContext.request.contextPath}/company-register" 
                       method="post" modelAttribute="newCompany">
                
                <p>
                    <form:input path="userId.email" placeholder="email"/>
                </p>
                <p>
                    <form:input path="userId.password" placeholder="password"/>
                </p>
                <p>
                    <form:input path="displayName" placeholder="company name"/>
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
                <p>
                    <form:input path="afm" placeholder="afm"/>
                </p>
                <p>
                    <form:input path="addrStr" placeholder="Street"/>
                </p>
                <p>
                    <form:input path="addrNo" placeholder="Number"/>
                </p>
                 <p>
                    <form:input path="addrCity" placeholder="City"/>
                </p>
                
                <input type="submit" value="Register">
            </form:form>
        </div>
    </body>
</html>