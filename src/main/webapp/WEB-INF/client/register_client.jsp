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
        <form:form action="${pageContext.request.contextPath}/client-register" 
                   method="POST" modelAttribute="newClient">
            <!--todo: user sucessfully created-->
            <div id="container-register">
                <div id="title">
                    <i class="material-icons lock">calendar_today</i> Client Register
                </div> 
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
                        <form:input path="fname" placeholder="first name"/>
                        <form:errors path="fname"/>
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
                        <br>   
                    <input type="submit" value="Register">
                    <div class="register">
                        <a href="${pageContext.request.contextPath}/company-register" id="register-link">Register as Company</a>
                    </div>
                </form:form>
            </div>
        <br>
        <br>
        <br>
        <br>
        
    </body>
    <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
</html>