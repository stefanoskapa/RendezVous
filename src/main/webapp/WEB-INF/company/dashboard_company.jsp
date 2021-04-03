<%-- 
    Document   : dashboard_company
    Created on : Mar 8, 2021, 3:26:58 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rendezvouz | Dashboard</title>
        <!--<link rel="stylesheet"
                      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
                <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
                <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
                <link href='${pageContext.request.contextPath}/calendar/lib/main.css' rel='stylesheet' />
                <script src='${pageContext.request.contextPath}/calendar/lib/main.js'></script>
                <script src="${pageContext.request.contextPath}/calendar/calendar-company.js"></script>
                <link rel="stylesheet" href="/chat/styles.css">-->

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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
        <link href='${pageContext.request.contextPath}/calendar/lib/main.css' rel='stylesheet' />
        <script src='${pageContext.request.contextPath}/calendar/lib/main.js'></script>
        <script src="${pageContext.request.contextPath}/calendar/calendar-company.js"></script>
        <link rel="stylesheet" href="/chat/styles.css">
        <link rel="stylesheet" href="/css/company/dashboard/dashboard.css">
    </head>
    <body>
        <jsp:include page="/WEB-INF/navbars/loggedNavbarCompany.jsp"/>
        <br/>
        <br/>

        <div id='calendar'></div>

        <!-- Modal content-->
        <div class="modal fade" tabindex="-1" role="dialog" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Modal title</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                    </div>

                    <div class="modal-body">
                        <p>One fine body&hellip;</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <jsp:include page="/WEB-INF/navbars/footer.jsp"/>               
        <jsp:include page="/WEB-INF/chat.jsp"/>
    </body>
</html>
