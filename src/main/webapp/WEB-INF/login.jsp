<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>RendezVouz | Login</title>
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
        <jsp:include page="navbars/loginNavbar.jsp"/>
        <br>
        <br>
        <br>
        <br>
        <br>     
        <!-- Login Form -->
        <form:form action="${pageContext.request.contextPath}/authenticateTheUser" 
                   method="POST">
            <br>
            <!-- User name -->
            <div id="container-login">
                <div id="title">
                    <i class="material-icons lock">calendar_today</i> Login
                </div> 
                <br>
                <!-- Check for login error -->
                <c:if test="${param.error != null}">
                    <div id="error" class="forgot-password">
                        Invalid username and password.
                    </div>
                </c:if>
                <!--		            
                <div class="alert alert-success col-xs-offset-1 col-xs-10">
                        You have been logged out.
                </div>
                -->   
                <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">face</i>
                    </div>
                    <input id="username" type="text" name="username" placeholder="E-Mail" autocomplete="off" required>
                </div>
                <div class="clearfix"></div>
                <!-- Password -->
                <div class="input">
                    <div class="input-addon">
                        <i class="material-icons">vpn_key</i>
                    </div>
                    <input id="password" type="password" name="password" placeholder="Password" autocomplete="off" required >
                </div>
                <br>
                <!-- Login/Submit Button -->
                <button type="submit" class="btn btn-success btn-lg m-2">Login</button>
                <br>
                <a href="/client-register" class="btn btn-secondary btn-lg m-2">Register</a><br>
                <a href="/company-register" class="btn btn-secondary btn-lg m-2">Register as Company</a>
                
            </form:form>
        </div>
        <br>
        <br>
        <br>
        <br>
        <footer>
            <jsp:include page="navbars/footer.jsp"/>
        </footer>
    </body>
</html>