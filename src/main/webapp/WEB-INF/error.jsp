<%-- 
    Document   : error
    Created on : Mar 28, 2021, 1:29:33 AM
    Author     : Stefanos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body>
        <div class="page-wrap d-flex flex-row align-items-center ">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12 text-center">
                        <br><br><br><br><br><br>
                        <span class="display-1 d-block">${status}</span>
                        <div class="mb-4 lead">${msg}</div>                      
                        <a href="javascript:history.back()">Go Back</a><br>
                        <a href="/">Home</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>