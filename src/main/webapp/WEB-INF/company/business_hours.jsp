<%-- 
    Document   : business_hours
    Created on : Mar 8, 2021, 3:36:25 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>RendezVouz | Business Hours</title>

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
        <link rel="stylesheet" href="/css/company/business-hours/hours.css">
        <script	src="/js/company/business-hours/hours.js"></script>
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarCompany.jsp"/>
        </header>

        <main>
            <c:if test="${IncorrectWorkingHours != null}">

                <div class="alert alert-info alert-dismissible fade show d-flex justify-content-center">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong >${IncorrectWorkingHours}</strong>
                </div>

            </c:if>

            <div class="container text-white">
                <form:form modelAttribute="weekHours" action="${pageContext.request.contextPath}/company/business-hours" 
                           method="post" >

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Monday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['1'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['1'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['1'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['1'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay1" >
                                <label class="my-auto" for="closedAllDay1">Closed all Day</label>
                            </div>
                        </div>
                    </div>

                    <hr>   

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Tuesday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['2'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['2'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['2'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['2'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay2" >
                                <label class="my-auto" for="closedAllDay2">Closed all Day</label>
                            </div>
                        </div>
                    </div>    

                    <hr>      

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Wednesday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['3'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['3'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['3'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['3'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay3" >
                                <label class="my-auto" for="closedAllDay3">Closed all Day</label>
                            </div>
                        </div>
                    </div>    

                    <hr>

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Thursday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['4'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['4'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['4'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['4'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay4" >
                                <label class="my-auto" for="closedAllDay4">Closed all Day</label>
                            </div>
                        </div>
                    </div>    

                    <hr>

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Friday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['5'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['5'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['5'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['5'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay5" >
                                <label class="my-auto" for="closedAllDay5">Closed all Day</label>
                            </div>
                        </div>
                    </div>  

                    <hr>

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Saturday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['6'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['6'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['6'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['6'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay6" >
                                <label class="my-auto" for="closedAllDay6">Closed all Day</label>
                            </div>
                        </div>
                    </div>    

                    <hr>

                    <div class="row pt-md-3">
                        <div class="col-12 d-flex justify-content-center">
                            <h4 class="py-md-3">Sunday</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['7'].startTime">Opening time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['7'].startTime" type="time" />
                        </div>
                        <div class="col-12 col-md-2 text-md-right justify-content-center pr-md-0">
                            <form:label class="h-100 my-0  py-1" path="week['7'].startTime">Closing time:</form:label>
                            </div>
                            <div class="col-12 col-md-2">
                            <form:input class="form-control"  path="week['7'].closeTime" type="time"/>
                        </div>
                        <div class="col-12 col-md-4 d-flex align-items-center justify-content-center">
                            <div class="py-3 py-md-0">
                                <input class="" type="checkbox" id="closedAllDay7" >
                                <label class="my-auto" for="closedAllDay7">Closed all Day</label>
                            </div>
                        </div>
                    </div>

                    <hr>    

                    <div class="row">
                        <div class="col d-flex justify-content-center">
                            <input type="submit" class="btn btn-success btn-lg px-5 my-3" value="Save">
                        </div>
                    </div>
                </form:form>
            </div>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>
