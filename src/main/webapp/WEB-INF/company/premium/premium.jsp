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
        <title>JSP Page</title>
    </head>
    <body>
        <!--URL: company/pro-->        
        
        <h3>Unlock our premium services</h3>
        Unlock the option to customize your category and start getting found by more and more clients!
        
        <h4>Order Form</h4>
        <h4>Price: ${amount/100}</h4>
        <form action='${pageContext.request.contextPath}/company/pro/charge' method='POST' id='checkout-form'>
            <!--<input type='hidden' value='${amount/100}' name='amount' />-->
            <script
                src='https://checkout.stripe.com/checkout.js'
                class='stripe-button'
                data-key=${stripePublicKey}
                data-amount=${amount}
                data-name='RendezVous'
                data-description='Unlock Pro package'
                <!--data-image='${pageContext.request.contextPath}/logo.jpg'/>-->
                data-locale='auto'
                data-zip-code='false'
            </script>
        </form>
        
        <!--me tin epitixi agora, provoli selidas epitixous (company/pro/success) agoras kai redirect sto company/profile, opou tha exei ksekleidosei i epilogi category-->
        <!--me tin apotiximeni agora provoli selidas apotyxias (company/pro/rejected), kai redirect sto company/dashboard-->
        
    </body>
</html>
