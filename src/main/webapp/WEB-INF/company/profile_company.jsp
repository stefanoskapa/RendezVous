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
        <style>
            body {
                display: grid;
                min-height: 100vh;
                height: 100%;
                grid-template-rows: auto 1fr auto;
                background-color: #2f3438;
            }
            .container profile profile-view{
                color: rgb(36,40,43);
            }

            h1{
                color: rgb(255,255,255);
            }
            h4{
                color: rgb(155,166,200);
            }
            .form-group{
                color: rgb(255,255,255);
                font-size: 18px;
            }
            .fa fa-user-circle{
                font-size: 24px;    
            }
            .fas fa-spell-check{
                font-size: 24px;
            }
            .fas fa-address-book{
                font-size: 24px;
            }
            .fas fa-phone{
                font-size: 24px;
            }

            .fas fa-city{
                font-size: 24px;
            }

            .btn btn-primary form-btn{
                color: rgb(255,255,255);
                background-color: #007a3d;
            }
        </style>
    </head>
    <body>
        <!--URL: company/profile-->
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarCompany.jsp"/>
        </header>
        <br>
        <div class="container profile profile-view" id="profile">
            <form:form action="${pageContext.request.contextPath}/company/profile" 
                       method="post" modelAttribute="company">
                <div class="row">
                    <div class="col">
                        <h1 id="h1"><i class="fas fa-building"></i>&nbsp;Profile</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <c:if test="${company.premium == true}">
                            <h4>Premium : Enabled</h4>
                        </c:if>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <form:label path="displayName"><i class="fa fa-user-circle"></i>&nbsp;Display Name
                            </form:label>
                            <form:input path="displayName" placeholder="company name" class="form-control" type="text"
                                        name="displayname" />
                            <form:errors path="displayName" />
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <div class="form-group">
                                <form:label path="afm"><i class="fas fa-address-book"></i>&nbsp;ΑΦΜ</form:label>
                                <form:input path="afm" placeholder="ΑΦΜ" disabled="true" class="form-control afm" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <form:label path="fname"><i class="fas fa-spell-check"></i>&nbsp;First Name</form:label>
                            <form:input path="fname" placeholder="First Name"  class="form-control" type="text" name="firstname"/>
                            <form:errors path="fname"/>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <div class="form-group">
                                <form:label path="lname"><i class="fas fa-spell-check"></i>&nbsp;Last Name</form:label>
                                <form:input path="lname" placeholder="Last Name" class="form-control" type="text" name="lastname"/>
                                <form:errors path="lname"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <form:label path="tel"><i class="fas fa-phone"></i>&nbsp;Telephone</form:label>
                            <form:input path="tel" placeholder="Telephone" class="form-control tel" type="text" name="phone"/>
                            <form:errors path="tel" />
                        </div>
                    </div>   
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <div class="form-group">
                                <form:label path="addrStr"><i class="fas fa-city"></i>&nbsp;Street Name</form:label>
                                <form:input path="addrStr" placeholder="Street Name" class="form-control" type="text"  name="streetname" />
                                <form:errors path="addrStr"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-sm-6 col-lg-3">

                        <c:if test="${company.premium == true}">
                            <div class="form-group">
                                <form:label path="category"><i class="fas fa-spell-check"></i>&nbsp;Category</form:label>
                                <form:select path="category" class="form-control">
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
                    </div>
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="form-group">
                            <div class="form-group">
                                <form:label path="addrCity"><i class="fas fa-city"></i>&nbsp;City</form:label>
                                <form:input path="addrCity" placeholder="City" class="form-control" type="text"/>
                                <form:errors path="addrCity"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <form:label path="addrNo">&nbsp;<i class="fas fa-city"></i>&nbsp;Street Number</form:label>
                                <form:input path="addrNo" placeholder="Number" class="form-control strnumb" name="streetnumber" />
                                <form:errors path="addrNo"/>
                            </div>
                        </div>    
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="form-group">
                        <div class="form-group">
                            <button class="btn btn-primary form-btn" type="submit">Save
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
        <footer><jsp:include page="/WEB-INF/navbars/footer.jsp"/></footer>
    </body>
</html>


