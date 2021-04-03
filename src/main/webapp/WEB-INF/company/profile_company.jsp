<%-- 
    Document   : profile-company
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
        <title>Company Profile</title>

        <!--        <link rel="stylesheet"
                      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
                <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->

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
        <link rel="stylesheet" href="/footer/Dark-Footer.css">

        <!--Current page-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body style="background-color: rgb(47,52,56);">
        <!--URL: company/profile-->
        <jsp:include page="/WEB-INF/navbars/loggedNavbarCompany.jsp"/>
        <br>
        <div class="container profile profile-view" id="profile" style="color: rgb(36,40,43);">
            <form:form action="${pageContext.request.contextPath}/company/profile" 
                       method="post" modelAttribute="company">
                <div class="form-row profile-row">
                    <div class="col-md-8" style="width: 741px;">
                        <h1 style="color: rgb(255,255,255);"><i class="fas fa-building"></i>&nbsp;Profile</h1><c:if test="${company.premium == true}"><h4 style="color: rgb(155,166,200);">Premium : Enabled</h4></c:if>
                            <hr>
                            <div class="form-row">
                                <div class="col-sm-12 col-md-6">
                                    <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="displayName"><i class="fa fa-user-circle" style="font-size: 24px;"></i>&nbsp;Display Name</form:label>
                                    <form:input  path="displayName" placeholder="company name" class="form-control" type="text" name="displayname" style="width:250px;" />
                                    <form:errors path="displayName"/>
                                </div>
                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">

                                    <form:label path="fname"><i class="fas fa-spell-check" style="font-size: 24px;"></i>&nbsp;First Name</form:label>
                                    <form:input path="fname" placeholder="First Name"  class="form-control" type="text" name="firstname" style="width:250px;"/>
                                    <form:errors path="fname"/>
                                </div>

                                <c:if test="${company.premium == true}">
                                    <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                        <form:label path="category"><i class="fas fa-spell-check" style="font-size: 24px;"></i>&nbsp;Category</form:label>
                                        <form:select path="category" class="form-control" style="width:250px;">

                                            <c:if test = "${company.category == null}">
                                                <option disabled="true" selected="true">Select a category</option>
                                            </c:if>
                                            <c:if test = "${company.category != null}">
                                                <option selected="true" value="${company.category.id}">${company.category.category}</option>
                                            </c:if>

                                            <c:forEach items="${listCategory}" var="category">
                                                <c:if test = "${company.category.id != category.id}">
                                                    <option value="${category.id}">${category.category}</option>
                                                </c:if>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </c:if>

                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="tel"><i class="fas fa-phone" style="font-size: 24px;"></i>&nbsp;Telephone</form:label>
                                    <form:input path="tel" placeholder="Telephone" class="form-control" type="text" name="phone" style="width: 120px;"/>
                                    <form:errors path="tel" />
                                </div>

                            </div>
                            <div class="col-sm-12 col-md-6">
                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="afm"><i class="fas fa-address-book" style="font-size: 24px;"></i>&nbsp;ΑΦΜ</form:label>
                                    <form:input path="afm" placeholder="ΑΦΜ" disabled="true" class="form-control" style="width: 110px;"/>
                                </div>
                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="lname"><i class="fas fa-spell-check" style="font-size: 24px;"></i>&nbsp;Last Name</form:label>
                                    <form:input path="lname" placeholder="Last Name" class="form-control" type="text" name="lastname" style="width: 250px;"/>
                                    <form:errors path="lname"/>
                                </div>
                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="addrStr"><i class="fas fa-city" style="font-size: 24px;"></i>&nbsp;Street Name</form:label>
                                    <form:input path="addrStr" placeholder="Street Name" class="form-control" type="text"  name="streetname" style="width: 250px;"/>
                                    <form:errors path="addrStr"/>
                                </div>
                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="addrCity"><i class="fas fa-city" style="font-size: 24px;"></i>&nbsp;City</form:label>
                                    <form:input path="addrCity" placeholder="City" class="form-control" type="text" style="width: 250px;"/>
                                    <form:errors path="addrCity"/>
                                </div>
                                <div style="color: rgb(255,255,255);font-size: 18px;" class="form-group">
                                    <form:label path="addrNo">&nbsp;<i class="fas fa-city" style="font-size: 24px;"></i>&nbsp;Street Number</form:label>
                                    <form:input path="addrNo" placeholder="Number" class="form-control" name="streetnumber"  style="width: 55px;"/>
                                    <form:errors path="addrNo"/>
                                </div>

                            </div>
                        </div>
                        <div class="form-row">
                            <br>
                            <br>

                            <br
                                <div class="form-row">
                            <div class="col-md-12 content-right"> <br><button class="btn btn-primary form-btn" type="submit" style="color: rgb(255,255,255);background-color: #007a3d;">Save</button></div>

                        </div>
                    </div>
                </div>
            </form:form>

        </div>
        <br>
    </body>
    <footer><jsp:include page="/WEB-INF/navbars/footer.jsp"/></footer>

</html>


