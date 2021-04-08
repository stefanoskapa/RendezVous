<%-- 
    Document   : success_premium
    Created on : Mar 8, 2021, 10:24:54 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RendezVouz | Premium Purchased</title>

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
        <link rel="stylesheet" href="/css/company/purchase/success/success.css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarCompany.jsp"/>
        </header>

        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-sm-12">
                        <img class="mx-auto" src="/img/checked.png" alt="payment successfull" width="125" height="120" />
                        <h1 class="text-white py-2 text-center">Thank You For Your Purchase!</h1>
                        <h3 class="text-white py-2 text-center">You have successfully unlocked the Premium package</h3>
                        <h3 class="text-white py-2 text-center" >You can now select your company's category, and showing up in user's searches of your category</h3>
                        <h5 class="text-white py-2 text-center">Category field have been enabled in your Profile Customization page</h5>
                        <div class="py-4 text-center"><a class="text-white btn btn-lg btn-outline-success" href="${pageContext.request.contextPath}/company/profile">Continue to your Profile</a></div>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>
