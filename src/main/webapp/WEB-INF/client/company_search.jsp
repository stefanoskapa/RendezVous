<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Rendezvouz | Search</title>

        <!--Bootstrap-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
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
        <script	src="/js/client/search-company/pagination.js"></script>
        <script	src="/js/client/search-company/search-company.js"></script>
        <link rel="stylesheet" href="/css/client/search-company/pagination.css">
        <link rel="stylesheet" href="/css/client/search-company/search-company.css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarClient.jsp"/>
        </header>

        <main>
            <div class="container">
                
                <div class="row ">
                    <div class="col-12 p-5 mx-auto text-white text-center">
                        <h1>Find a company that suits your needs</h1>
                    </div>
                </div>

                <div class="row py-5 px-2">
                    
                    <div class="col-12 col-md-2 px-1">
                        <select class="form-control custom-select my-2" id="category">
                            <option selected="selected" value="All">All Categories</option>

                            <c:forEach items="${listCategory}" var="category">         
                                    <option value="${category.id}">${category.category}</option>                              
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-12 col-md-2 px-1">
                        <select class="form-control custom-select my-2" id="city">
                            <option selected="selected" value="All">All Cities</option>
                            <c:forEach items="${listCities}" var="city">         
                                    <option value="${city}">${city}</option>                              
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-12 col-md-8 px-1">
                        <input type="text" id="searchbar" class="form-control my-2" placeholder="Search for..." maxlength="100">
                        <div id="searchInfo" class="fa fa-info-circle float-right"></div>
                    </div>
                    <div class="col-12 col-md-3 my-2 px-1">
                        <input type="button" class="btn btn-success btn-md rounded" value="search" id="srcBtn"/>
                    </div>                  
                </div>


                <div class="row py-4 px-2 my-1" id="results-area">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <p class="text-center text-light" id="results-number"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="container" id="results"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col d-flex justify-content-center">
                                <div id="pagination"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>
