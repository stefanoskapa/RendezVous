<%-- 
    Document   : dashboard_client
    Created on : Mar 8, 2021, 3:19:09 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Rendezvouz | Dashboard</title>

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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
        <link href='/calendar/lib/main.min.css' rel='stylesheet' />
        <script src='/calendar/lib/main.min.js'></script>
        <script src="/calendar/calendar-client.js"></script>
        <link rel="stylesheet" href="/chat/styles.css">
        <link rel="stylesheet" href="/css/client/dashboard/dashboard.css">
        <script src="/js/client/dashboard/popover.js"></script>
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarClient.jsp"/>
        </header>

        <main>
            <div class="container h-100" id="loading-container">
                <div class="row h-100 justify-content-center align-items-center">
                    <div class="col-12">
                        <img class="d-block mx-auto" src="/img/loading.gif"/>
                    </div>
                </div>
            </div>

            <div class="container-fluid" id="calendar-container">

                <div class="row mx-0 mt-5 mb-2 mx-md-5">
                    <div class="col-12">
                        <div><p class="h3">My Dashboard</p></div>
                        <div id="popover" class="fa fa-info-circle fa-lg float-right"></div>
                    </div>
                </div>

                <div class="row d-flex  pt-1 mx-0 mx-md-5" id="search-row">
                    <div class="col-12 col-md-2 px-1">
                        <select class="form-control custom-select my-2" id="search-by-category">
                            <option selected="selected" value="-1" >All Categories</option>
                            <c:forEach items="${listCategory}" var="category">         
                                <option value="${category.id}">${category.category}</option>                              
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-12 col-md-4 px-1">
                        <input type="text" id="search-by-name" class="form-control my-2" placeholder="Company name" >
                    </div>
                </div>

                <div class="row mb-4 mx-0 mx-md-5">
                    <div class="col-12 px-1">
                        <p>Filter your appointments by their category and/or the name of the company</p>
                    </div>
                </div>

                <div class="row mx-0 mx-md-4">
                    <div class="col-12">
                        <div class="alert alert-dismissible fade show" role="alert" id="alert"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <div id='calendar' class="mx-0 mx-md-5 mb-5"></div>
                    </div>
                </div>
            </div>

            <!-- Modal content-->
            <div class="modal fade" tabindex="-1" role="dialog" id="myModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"></h4>
                            <button  type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    id="xButton" aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <p></p>
                        </div>
                        <div class="modal-footer">
                            <input type="text" hidden id="hdate" value="">
                            <button type="button" id="deleteDate" class="btn btn-warning mr-auto">Delete</button>
                            <button type="button" id="confirmDeleteDate" class="btn btn-warning mr-auto" data-dismiss="modal">Confirm</button>

                            <button type="button" id="buttonOk" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <jsp:include page="/WEB-INF/chat.jsp"/>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>             
        </footer>
    </body>
</html>
