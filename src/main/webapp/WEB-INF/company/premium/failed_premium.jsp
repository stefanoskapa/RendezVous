<%-- 
    Document   : failed_premium
    Created on : Mar 8, 2021, 10:25:04 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RendezVouz | Purchased Failed</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="/css/purchase/failed/failed.css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loginNavbar_1.jsp"/>
        </header>

        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-sm-12">
                        <div class="py-4 text-center"><img src="/img/payment-failed.png" alt="payment failed" width="125" height="125" /></div>
                        <h1 class="text-white py-2 text-center">Purchase was unsuccessful!</h1>
                        <h3 class="text-white py-2 text-center">The payment could not be completed</h3>
                        <div class="py-4 text-center"><a class="text-white btn btn-lg btn-outline-success" href="${pageContext.request.contextPath}/company/pro">Try again</a></div>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>
