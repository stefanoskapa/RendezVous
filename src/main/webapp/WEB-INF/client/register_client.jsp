<!DOCTYPE HTML >
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Register Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

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
        <script src="/js/client/register/register.js"></script>
       
        <link rel="stylesheet" href="/css/client/register/register-client.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/navbars/loginNavbar.jsp"/>

        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-5 justify-content-center">
                        <div class="login-dark my-3">
                            <form:form action="${pageContext.request.contextPath}/client-register" 
                                       method="POST" modelAttribute="newClient" id="register-form">
                                <div class="title mb-4">Client Register</div>

                                <c:if test="${userExistsError != null}">
                                    ${userExistsError}
                                </c:if>

                                <div class="form-group row">
                                    <i class="col-sm-2 material-icons">mail</i>
                                    <div class="col-sm-10">
                                        <form:input path="user.email" class="form-control" type="email" placeholder="Email"  required="required" maxlength="45"/>
                                        <form:errors path="user.email"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <i class="col-sm-2 material-icons">vpn_key</i>
                                    <div class="col-sm-10">
                                        <form:input path="user.password" class="form-control" type="password" name="password" placeholder="Password" id="password" required="required" minlength="5" maxlength="200" />
                                        <i class="fa fa-eye showpwd"></i>
                                        <form:errors path="user.password" />
                                    </div>
                                </div>

                                <div class="form-group row"> 
                                    <i class="col-sm-2 material-icons">spellcheck</i>
                                    <div class="col-sm-10">
                                        <form:input path="fname" class="form-control" type="text" name="fname" placeholder="First Name" id="fname" required="required" maxlength="20"/>
                                        <form:errors path="fname"/>
                                    </div>
                                </div>

                                <div class="form-group row"> 
                                    <i class="col-sm-2 material-icons">spellcheck</i>
                                    <div class="col-sm-10">
                                        <form:input path="lname" class="form-control" type="text" name="lname" placeholder="Last Name" id="lname" required="required" maxlength="20" />
                                        <form:errors path="lname"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <i class="col-sm-2 material-icons">phone</i>
                                    <div class="col-sm-10">
                                        <form:input path="tel" class="form-control" type="text" name="tel" placeholder="Telephone" id="tel" required="required" maxlength="10"/>
                                        <form:errors path="tel"/>
                                    </div>
                                </div>
                                    
                                <div class="form-group row justify-content-center">
                                    <button class="btn btn-primary btn-lg m-3 g-recaptcha" value="Register" type="submit" data-sitekey="6Lfe3aAaAAAAAA2eogoecmMRNClD1euKJHpR6xQB" 
                                            data-callback='onSubmit' 
                                            data-action='registerClient'>Register</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

    </body>
    <footer>
        <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
    </footer>
</html>