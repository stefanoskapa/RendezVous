<%-- 
    Document   : company_date_pick
    Created on : Mar 8, 2021, 10:12:42 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Rendezvouz | Availability</title>        

        <!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
                <link href='${pageContext.request.contextPath}/calendar/lib/main.css' rel='stylesheet' />
                <script src='${pageContext.request.contextPath}/calendar/lib/main.js'></script>
                <script src="${pageContext.request.contextPath}/calendar/calendar-date-pick.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
                <link rel="stylesheet" href="/chat/styles.css">
                <style>
                    .fc-timegrid-slot:hover {
                        cursor: pointer;
                    }
                    #calendar .fc-non-business {
                        background: #706b6b56;
                    }
                </style>-->

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
        <link href='${pageContext.request.contextPath}/calendar/lib/main.min.css' rel='stylesheet' />
        <script src='${pageContext.request.contextPath}/calendar/lib/main.min.js'></script>
        <script src="${pageContext.request.contextPath}/calendar/calendar-date-pick.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
        <link rel="stylesheet" href="/chat/styles.css">
        <link rel="stylesheet" href="/css/client/date-pick/availability.css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarClient.jsp"/>
        </header>

        <h1>Pick a date for your appointment</h1>
        <h2>${comp_name}</h2>
        <div class="alert alert-dismissible fade show" role="alert" id="alert">
        </div>

        <input type="text" id="comp-id" value="${comp_id}" hidden  />
        <input type="text" id="fname" value="${fname}" hidden  />
        <input type="text" id="lname" value="${lname}" hidden  />
        <input type="text" id="displayName" value="${comp_name}" hidden  />
        <input type="text" id="compEmail" value="${comp_email}" hidden />

        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div id='calendar' class="mx-0 mx-md-5 my-5"></div>
                </div>
            </div>
        </div>
        
        <!-- Modal content-->
        <div class="modal fade" tabindex="-1" role="dialog" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Modal title</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                    </div>

                    <div class="modal-body">
                        <p>One fine body&hellip;</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="cancelModalBtn" data-dismiss="modal">Cancel</button>
                        <form>
                            <input type="text" hidden id="hdate" value="">
                            <button type="button" class="btn btn-primary" id="submitDateToServer" data-dismiss="modal">
                                Confirm
                            </button>
                        </form>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <jsp:include page="/WEB-INF/chat.jsp"/>
        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>
