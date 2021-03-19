<!DOCTYPE HTML >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Register Page</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="Dakirby309-Simply-Styled-Calendar.ico"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Reference Bootstrap files -->
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body style="background-color:#2f3438 ">
        <jsp:include page="/WEB-INF/navbars/loginNavbar.jsp"/>
        <!--URL: /client-register-->

        <!--form register for client, POST, /client-register-->
        <!--email*, fname*, lname*, tel, password*-->
        <!--me tin epitixi dimiourgia redirect sto /login-->
        <br>
        <br>
        <br>
        <br>
        <br>   
        <c:if test="${userExistsError != null}">
            ${userExistsError}
        </c:if>
        <form:form action="${pageContext.request.contextPath}/company-register" 
                       method="post" modelAttribute="newCompany">
            <!--todo: user sucessfully created-->
            <div id="container-register-company">
                <div id="title">
                    <i class="material-icons lock">calendar_today</i> Company Register
                </div> 
                <br>
                <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">mail</i>
                    </div>
                    <form:input path="user.email" placeholder="Email" />
                    <form:errors path="user.email"/>
                    </div>
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">vpn_key</i>
                    </div>
                        <form:input path="user.password" placeholder="password"/>
                        <form:errors path="user.password"/>
                    </div>
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">spellcheck</i>
                    </div>
                        <form:input path="displayName" placeholder="company name"/>
                        <form:errors path="displayName"/>
                        </div>
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">spellcheck</i>
                    </div>
                        <form:input path="lname" placeholder="last name"/>
                        <form:errors path="lname"/>
                        </div>
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">phone</i>
                    </div>
                        <form:input path="tel" placeholder="tel"/>
                        <form:errors path="tel"/>
                        </div>  
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">chrome_reader_mode</i>
                    </div>
                        <form:input path="afm" placeholder="afm"/>
                    <form:errors path="afm"/>
                        </div>  
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">location_city</i>
                    </div>
                        <form:input path="addrStr" placeholder="Street"/>
                    <form:errors path="addrStr"/>
                        </div>  
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">location_city</i>
                    </div>
                        <form:input path="addrNo" placeholder="Number"/>
                    <form:errors path="addrNo"/>
                        </div>  
                    <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">location_city</i>
                    </div>
                       <form:input path="addrCity" placeholder="City"/>
                    <form:errors path="addrCity"/>
                        </div>
                        <br>   
                    <input type="submit" value="Register">
                    <div class="register">
                        <a href="${pageContext.request.contextPath}/client-register" id="register-link">Register as Client</a>
                    </div>
                </form:form>
            </div>
        <br>
        <br>
        <br>
        <br>
        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>