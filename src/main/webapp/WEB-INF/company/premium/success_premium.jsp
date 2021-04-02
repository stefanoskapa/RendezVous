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
        <title>RendezVouz | Pro Purchased</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="/css/purchase/success/success.css">
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loginNavbar_1.jsp"/>
        </header>

        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-sm-12">
                        <img class="mx-auto" src="/img/checked.png" alt="payment successfull" width="125" height="120" />
                        <h1 class="text-white py-2 text-center">Thank You For Your Purchase!</h1>
                        <h3 class="text-white py-2 text-center">You have successfully unlocked the Pro package</h3>
                        <h3 class="text-white py-2 text-center" >You can now select your company's category, and showing up in user's searches of your category</h3>
                        <h5 class="text-white py-2 text-center">Category field have been enabled in your Profile Customization page</h5>
                        <div class="py-4 text-center"><a class="text-white btn btn-lg btn-outline-success" href="${pageContext.request.contextPath}/company/profile">Continue to your Profile</a></div>
                    </div>
                </div>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
    </body>
</html>
