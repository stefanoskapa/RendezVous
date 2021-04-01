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
        <title>RendezVouz | Pro</title>
        <style>
            body {
                display: grid;
                min-height: 100vh;
                height: 100%;
                grid-template-rows: auto 1fr auto;
            }

            main{
               background: linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)), url('/img/pro-bg.png');
                /*background-image: url("/img/pro-bg.png");*/
                /*opacity:*/ 
                background-size: cover;
            }

        </style>
    </head>
    <body>
        <header>
            <jsp:include page="/WEB-INF/navbars/loginNavbar_1.jsp"/>
        </header>

        <main>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h1 class="text-white">Expand with us</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        
                    </div>
                </div>
            </div>
            
            
            <h3>Unlock our premium services</h3>
            Unlock the option to customize your category and start getting found by more and more clients!

            <h4>Order Form</h4>
            <h4>Price: ${amount/100}</h4>

            <form action='${pageContext.request.contextPath}/company/pro/charge' method='POST' id='checkout-form'>
                <script
                    src='https://checkout.stripe.com/checkout.js'
                    class='stripe-button'
                    data-key=${stripePublicKey}
                    data-amount=${amount}
                    data-name='RendezVous'
                    data-description='Unlock Pro'
                    data-image='/favicon.ico'
                    data-locale='auto'
                    data-zip-code='false'
                    >
                </script>
            </form>
        </main>

        <footer>
            <jsp:include page="/WEB-INF/navbars/footer.jsp"/>
        </footer>
        <!--        i agora, provoli selidas epitixous (company/pro/success) agoras kai redirect sto company/profile, opou tha exei ksekleidosei i epilogi category-->
        <!--me tin apotiximeni agora provoli selidas apotyxias (company/pro/rejected), kai redirect sto company/dashboard-->

    </body>
</html>
