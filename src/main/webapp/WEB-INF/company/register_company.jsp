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
        <!--URL: /company-register-->
        
        <!--form register for company, POST, /custregiter-->
        <!--email*, onomasia etairias*, fname*, lname*, afm*, address street, address no, address city, password*--> 
        <!--i epilogi category tha ksekleidonei me tin agora premioum-->

        <!--me tin epitixi dimiourgia redirect sto /login-->

        <div align="center">
            <h3>Registration Form</h3>
            <c:if test="${userExistsError != null}">
                ${userExistsError}
            </c:if>
            <form:form action="${pageContext.request.contextPath}/company-register" 
                       method="post" modelAttribute="newCompany">
                <!--todo: user sucessfully created-->
                <p>
                    <form:input path="user.email" placeholder="email"/>
                    <form:errors path="user.email"/>
                </p>
                <p>
                    <form:input path="user.password" placeholder="password"/>
                    <form:errors path="user.password"/>
                </p>
                <p>
                    <form:input path="displayName" placeholder="company name"/>
                    <form:errors path="displayName"/>
                </p>
                <p>
                    <form:input path="fname" placeholder="first name"/>
                    <form:errors path="fname"/>
                </p>
                <p>
                    <form:input path="lname" placeholder="last name"/>
                     <form:errors path="lname"/>
                </p>
                <p>
                    <form:input path="tel" placeholder="tel"/>
                     <form:errors path="tel"/>
                </p>
                <p>
                    <form:input path="afm" placeholder="afm"/>
                    <form:errors path="afm"/>
                </p>
                <p>
                    <form:input path="addrStr" placeholder="Street"/>
                    <form:errors path="addrStr"/>
                </p>
                <p>
                    <form:input path="addrNo" placeholder="Number"/>
                    <form:errors path="addrNo"/>
                </p>
                 <p>
                    <form:input path="addrCity" placeholder="City"/>
                    <form:errors path="addrCity"/>
                </p>
                
                <input type="submit" value="Register">
            </form:form>
        </div>
    </body>
</html>