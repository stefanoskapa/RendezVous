<%-- 
    Document   : newjsp
    Created on : Mar 7, 2021, 12:42:47 AM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>RendezVouz</title>

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
       

    </head>
    <body>
        <jsp:include page="navbars/loginNavbar.jsp"/>

        <div class="container-fluid">
            <div class="row bg-dark justify-content-around">
                <div class="col-md-4 my-5 py-5">                   
                    <h1 class="text-white">All you need to do is show up at the right time!</h1> 
                    <br>
                    <p class="text-white">Make your day easier by booking all your appointments online using the best features in one tool</p>
                    <a href="/client-register" class="btn btn-success btn-lg m-2">Register</a>
                    <a href="/company-register" class="btn btn-success btn-lg m-2">Register as Partner</a>


                </div>
                <div class="col-md-4 my-5 py-5">
                    <br><br>
                    <img src="/img/rendezvous-landing_page-hero.png" class="img-fluid mx-auto d-block" alt="RendezVouz">
                </div>
            </div>

            <div class="row bg-white justify-content-around">               
                <div class="col-md-4 my-5 py-5">
                    <br><br>
                    <img src="/img/dashboard.jpg" class="img-fluid mx-auto d-block" alt="RendezVouz"  style="width:100%; border-radius: 30px; box-shadow:10px 10px 8px #888888;">
                </div>
                <div class="col-md-4 my-5 py-5">
                    <br>
                    <h1 class="text-dark">All you need to know about RendezVouz</h1> 
                    <br>
                    <p class="dark">RendezVouz is an easy and convenient way of booking and organizing appointments. As a partner
                        you can control you availability 24/7 and appear in our category-based search function. As a user you can quickly
                        navigate through a multitude of services, reach out for the company you are interested in and book an appointment.</p>
                </div>         
            </div>

            <div class="row bg-light justify-content-around">                
                <div class="col-md-4 my-5 py-5">
                    <h2 class="text-dark">"It is really refreshing to work with this software which is
                        truly helpful in the client's preferences."</h2>
                    <br>
                    <h5 class="text-dark">Matt Calderon</h5>
                    <p class="text-dark">Head of Marketing, Apple Inc.</p>
                    <br>
                </div>     
                <div class="col-md-4 my-5 py-5">
                    <img src="/img/rendezvous-landing_page-testimonials.png" class="img-fluid mx-auto d-block" alt="RendezVouz2">
                </div>
            </div>
        </div>
        <jsp:include page="navbars/footer.jsp"/>

    </body>
</html>
