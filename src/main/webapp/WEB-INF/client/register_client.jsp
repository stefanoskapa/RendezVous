<!DOCTYPE HTML >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Register Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!--Bootstrap-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!--Navbar-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
        <script src="/navbar/bs-init.js"></script>

        <!--Footer-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/footer/Dark-Footer.css">       

        <!--Current page-->
        <link rel="stylesheet" href="/css/login/login.css">
    </head>
    <body style="background-color:#2f3438 ">
        <jsp:include page="/WEB-INF/navbars/loginNavbar.jsp"/>

        <main>
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col d-flex justify-content-center">
                        <div class="login-dark my-3">
                            <form:form action="${pageContext.request.contextPath}/client-register" 
                                       method="POST" modelAttribute="newClient">

                                <div class="illustration">
                                    <i class="icon ion-ios-calendar"></i>
                                </div> 
                                <c:if test="${userExistsError != null}">
                                    ${userExistsError}
                                </c:if>
                                <i class="material-icons">mail</i>
                                <div class="form-group">
                                    <form:input path="user.email" class="form-control" type="email" placeholder="Email"  required="required" maxlength="45"/>
                                    <form:errors path="user.email"/>
                                </div>

                                <i class="material-icons">vpn_key</i>
                                <div class="form-group">
                                    <form:input path="user.password" class="form-control" type="password" name="password" placeholder="Password" id="password" required="required"/>
                                    <form:errors path="user.password"/>
                                </div>

                                <div class="form-group">
                                    <c:if test="${param.error != null}">
                                        <div class="error">
                                            Invalid Username And Password.
                                        </div>
                                    </c:if>
                                    <button class="btn btn-primary btn-block" value="Login" type="submit">Log In</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!--old-->
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

                <button type="submit" class="btn btn-success btn-lg m-2">Register</button>

            </form:form>
        </div>
        <!--old-->
    </body>
    <footer>
        <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
    </footer>
</html>