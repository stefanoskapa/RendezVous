<%-- 
    Document   : profile-client
    Created on : Mar 8, 2021, 3:31:47 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Rendezvouz | Profile</title>

        <!--Bootstrap-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!--Navbar-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
        <script src="/navbar/bs-init.js"></script>
        <!--        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">ti kanei?-->

        <!--Footer-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/footer/Dark-Footer.css">

        <!--Current page-->
        <link rel="stylesheet" href="/css/company/profile/company-profile.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarClient.jsp"/>
        </header>
        
        <div class="container profile profile-view my-4 p-5" id="profile">
            <form:form action="${pageContext.request.contextPath}/client/profile" method="post" modelAttribute="client">
                <div class="row">
                    <div class="col">
                        <h1 id="h1"><i class="fa fa-user"></i>&nbsp;User Profile</h1>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <form:label path="fname"><i class="fa fa-user-circle"></i>&nbsp;First Name</form:label>
                            <form:input path="fname" placeholder="First Name" class="form-control" type="text" name="firstname" required="required" minlength="1" maxlength="20" />
                            <form:errors path="fname"/>
                        </div>
                        <div class="form-group">
                                <form:label path="tel"><i class="fa fa-phone"></i>&nbsp;Telephone</form:label>
                                <form:input path="tel" placeholder="Telephone" class="form-control" type="phone" name="phone" maxlength="10"/>
                                <form:errors path="tel" />
                            </div>
                    </div>
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <div class="form-group">
                                <form:label path="lname"><i class="fa fa-user-circle"></i>&nbsp;Last Name</form:label>
                                <form:input path="lname" placeholder="Last Name"  class="form-control" type="text" name="lastname" required="required" minlength="1" maxlength="20"/>
                                <form:errors path="lname"/>
                            </div>
                            
                        </div>
                    </div>
                </div>
                            <button class="btn form-btn btn-success" type="submit">Save
                            </button>
            </form:form>
        </div>
        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
        
    </body>


</html>


