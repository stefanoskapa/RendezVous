<%-- 
    Document   : profile-company
    Created on : Mar 8, 2021, 3:31:47 PM
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
        <!--URL: company/profile-->

        Company edit profile

        <form:form action="${pageContext.request.contextPath}/company/profile" 
                   method="post" modelAttribute="company">
            <p>
                <form:label path="displayName">Display Name:</form:label><br/>
                <form:input path="displayName" placeholder="company name"/>
                <form:errors path="displayName"/>
            </p>
            <p><form:label path="afm">ΑΦΜ:</form:label><br/>
                <form:input path="afm" placeholder="afm" disabled="true"/>
            </p>
            <p>
                <form:label path="fname">First Name:</form:label><br/>
                <form:input path="fname" placeholder="first name"/>
                <form:errors path="fname"/>
            </p>
            <p>
                <form:label path="lname">Last Name:</form:label><br/>
                <form:input path="lname" placeholder="last name"/>
                <form:errors path="lname"/>
            </p>
            <p>
                <form:label path="tel">Telephone:</form:label><br/>
                <form:input path="tel" placeholder="tel"/>
                <form:errors path="tel"/>
            </p>
            <p>
                <form:label path="addrStr">Street name:</form:label><br/>
                <form:input path="addrStr" placeholder="Street"/>
                <form:errors path="addrStr"/>
            </p>
            <p>
                <form:label path="addrNo">Street number:</form:label><br/>
                <form:input path="addrNo" placeholder="Number"/>
                <form:errors path="addrNo"/>
            </p>
            <p>
                <form:label path="addrCity">City:</form:label><br/>
                <form:input path="addrCity" placeholder="City"/>
                <form:errors path="addrCity"/>
            </p>

            <input type="submit" value="Update Profile">
        </form:form>

    </body>
</html>
