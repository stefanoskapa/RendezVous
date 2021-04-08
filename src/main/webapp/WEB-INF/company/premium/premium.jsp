<%-- 
    Document   : premium
    Created on : Mar 8, 2021, 10:19:10 PM
    Author     : Leyteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RendezVouz | Premium</title>
<!--        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        
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
        <link rel="stylesheet" href="/css/company/purchase/pro/pro.css">
        
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loggedNavbarCompany.jsp"/>
        </header>

        <main class="vertical-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-12">
                        <p class="text-white display-4 py-5 py-md-2">Expand your<br/>customer base</p>
                        <h3 class="text-white lead py-2">Unlock the Premium package and let our users find your business when searching for a your specific category</h3>
                        <h5 class="text-white lead py-2">
                            <ul>
                                <li>One time purchase</li>
                                <li>No recurring fees</li>
                            </ul>
                        </h5>
                    </div>
                    <div class="col-md-6 col-sm-12 my-auto">
                        <div class="widget">
                            <h4 class="widget-title">Order Summary</h4>
                            <div class="summary-block">
                                <div class="summary-content">
                                    <div class="summary-head"><h5 class="summary-title">Premium Package</h5></div>
                                    <div class="summary-price">
                                        <h4 class="summary-text py-1">${amount/100} &euro;</h4>
                                        <span class="summary-small-text pull-right">Lifetime</span>
                                    </div>
                                    <form action='${pageContext.request.contextPath}/company/pro/charge' method='POST' id='checkout-form'>
                                        <script
                                            src='https://checkout.stripe.com/checkout.js'
                                            class='stripe-button'
                                            data-key=${stripePublicKey}
                                            data-amount=${amount}
                                            data-currency=eur
                                            data-name='RendezVous'
                                            data-description='Unlock Premium'
                                            data-image='/favicon.ico'
                                            data-locale='auto'
                                            data-zip-code='false'
                                            >
                                        </script>
                                    </form>
                                </div>
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
