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
    <body>
        <header>
            <jsp:include page="navbars/loginNavbar.jsp"/>
        </header>

        <main>
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col d-flex justify-content-center">
                        <div class="login-dark my-3">
                            <form:form action="${pageContext.request.contextPath}/authenticateTheUser" 
                                       method="POST">

                                <div class="illustration">
                                    <i class="icon ion-ios-calendar"></i>
                                </div> 
                                <c:if test="${param.registration!=null}">
                                    <div class="text-center py-1 text-success">
                                        Your account has been created!
                                    </div>
                                </c:if>
                                <i class="material-icons">mail</i>
                                <div class="form-group">
                                    <input class="form-control" type="email" name="username" placeholder="Email" id="username" required="required" maxlength="45">
                                </div>

                                <i class="material-icons">vpn_key</i>
                                <div class="form-group">
                                    <input class="form-control" type="password" name="password" placeholder="Password" id="password" required="required" maxlength="68">
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


        <footer>
            <jsp:include page="navbars/footer.jsp"/>
        </footer>
    </body>
</html>