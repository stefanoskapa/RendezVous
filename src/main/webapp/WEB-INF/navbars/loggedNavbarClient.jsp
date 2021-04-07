<div>
    <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button" style="height: 91px;background-color: #24273a;color: #ffffff;">
        <div class="container-fluid"><i class="fa fa-calendar" data-aos="zoom-in" data-aos-once="true" style="font-size: 57px;padding: 2px;margin: -14px;"></i><a class="navbar-brand" href="${pageContext.request.contextPath}/client/dashboard" style="height: 62px;margin: -6px;font-size: 25px;color: rgb(255,255,255);width: 220px;padding: 13px;">&nbsp;&nbsp;RendezVouz</a>
            <button
                data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1" style="background-color: #24273a;margin: 11px;">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link" style="color: #ffffff;font-size: 17px;padding: 7px;*height: 42px;margin: 2px;" href="${pageContext.request.contextPath}/client/comp-select"><i class="fa fa-clock-o" data-bs-hover-animate="bounce"></i>&nbsp;Book Appointment</a>
                    <li class="nav-item" role="presentation" style="/*width: 132px;*/">
                        <div class="nav-item dropdown" style="color: #ffffff;height: 35px;padding: 7px;"><a class="dropdown-toggle d-table-cell" data-toggle="dropdown" aria-expanded="false" style="color: rgb(255,255,255);font-size: 17px;height: 4px;width: 4px;margin: 5px;">&nbsp;<i class="fa fa-address-card"></i>&nbsp;${username}</a>
                            <div
                                class="dropdown-menu dropdown-menu-right" role="menu" style="background-color: rgb(36,39,58);color: rgb(36,39,58);"><a class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client/dashboard" style="color: rgb(255,255,255);font-size: 16px;background-color: #24273a;"><i class="fa fa-calendar" data-bs-hover-animate="bounce"></i>&nbsp;Dashboard</a>
                                <a
                                    class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client/profile" style="color: rgb(255,255,255);font-size: 16px;background-color: #24273a;"><i class="fa fa-user" data-bs-hover-animate="bounce"></i>&nbsp;Profile Edit</a>
                                <a
                                    class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/logout" style="color: rgb(255,255,255);font-size: 16px;background-color: #24273a;"><i class="fa fa-sign-out" data-bs-hover-animate="bounce"></i>&nbsp;Logout</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Small modal -->
    <div class="modal" id="logoutModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div style="display: none;" class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4  >Log Out <i class="fa fa-mail"></i></h4>
                </div>
                <div class="modal-body">
                    <p><i class="fa fa-question-circle"></i> Are you sure you want to log-out? <br /></p>
                    <div class="actionsBtns">
                        <form action="/logout" method="post">
                            <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                            <input type="submit" class="btn btn-default btn-primary" data-dismiss="modal" value="Logout" href="${pageContext.request.contextPath}/logout" />
                            <button class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>