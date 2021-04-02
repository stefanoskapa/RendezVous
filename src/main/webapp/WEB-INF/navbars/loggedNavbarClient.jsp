<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Untitled</title>
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css">-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">-->
    </head>

    <body>
        <div>
            <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button" style="height: 91px;background-color: #24273a;color: #ffffff;">
                <div class="container-fluid"><i class="fa fa-calendar" data-aos="zoom-in" data-aos-once="true" style="font-size: 57px;padding: 2px;margin: -14px;"></i><a class="navbar-brand" href="${pageContext.request.contextPath}/" style="height: 62px;margin: -6px;font-size: 25px;color: rgb(255,255,255);width: 220px;padding: 13px;">&nbsp;&nbsp;RendezVouz</a>
                    <button
                        data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navcol-1" style="background-color: #24273a;margin: 11px;">
                        <ul class="nav navbar-nav ml-auto">
                            
                            <li class="nav-item" role="presentation" style="/*width: 132px;*/">
                                <div class="nav-item dropdown" style="color: #ffffff;height: 35px;padding: 7px;"><a class="dropdown-toggle d-table-cell" data-toggle="dropdown" aria-expanded="false" style="color: rgb(255,255,255);font-size: 17px;height: 4px;width: 4px;margin: 5px;">&nbsp;<i class="fa fa-address-card"></i>&nbsp;${username}</a>
                                    <div
                                        class="dropdown-menu" role="menu" style="background-color: rgb(36,39,58);color: rgb(36,39,58);"><a class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client/profile" style="color: rgb(255,255,255);font-size: 16px;background-color: #24273a;"><i class="fa fa-user" data-bs-hover-animate="bounce"></i>&nbsp;Edit Profile</a>
                                        <a
                                            class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client/comp-select" style="color: rgb(255,255,255);font-size: 16px;background-color: #24273a;"><i class="fa fa-clock-o" data-bs-hover-animate="bounce"></i>&nbsp;Close Appointment</a>
                                        <a
                                            class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/logout" style="color: rgb(255,255,255);font-size: 16px;background-color: #24273a;"><i class="fa fa-sign-out" data-bs-hover-animate="bounce"></i>&nbsp;Logout</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>-->
        <!--<script src="${pageContext.request.contextPath}/bs-init.js"></script>-->
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>-->
    </body>

</html>