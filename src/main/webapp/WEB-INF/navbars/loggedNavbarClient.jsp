<link rel="stylesheet" href="/navbar/navbar.css">
<div>
    <nav id="nav" class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button">
        <div class="container-fluid">
            <a id="calendar-logo-container" href="${pageContext.request.contextPath}/client/dashboard">
                <i id="calendar-logo" class="fa fa-calendar" data-aos="zoom-in" data-aos-once="true"></i>
            </a>
            <a id="navbar-brand1" class="navbar-brand" href="${pageContext.request.contextPath}/client/dashboard">&nbsp;&nbsp;RendezVouz</a>
            <button
                data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a id="navlink1" class="nav-link" href="${pageContext.request.contextPath}/client/comp-select"><i class="fa fa-clock-o" data-bs-hover-animate="bounce"></i>&nbsp;Book Appointment</a>
                    <li class="nav-item" role="presentation">
                        <div id="nav-item-dropdown1" class="nav-item dropdown"><a id="dropdown-toggle1" class="dropdown-toggle d-table-cell" data-toggle="dropdown" aria-expanded="false">&nbsp;<i class="fa fa-address-card"></i>&nbsp;${username}</a>
                            <div
                                id="dropdown-menu1" class="dropdown-menu dropdown-menu-right" role="menu">
                                <a
                                  id="dropdown-item2"  class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client/dashboard"><i class="fa fa-calendar" data-bs-hover-animate="bounce"></i>&nbsp;Dashboard</a>
                                <a
                                  id="dropdown-item2"  class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client/profile"><i class="fa fa-user" data-bs-hover-animate="bounce"></i>&nbsp;Profile Edit</a>
                                <a
                                  id="dropdown-item2"  class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/logout" ><i class="fa fa-sign-out" data-bs-hover-animate="bounce"></i>&nbsp;Logout</a>
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
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" class="btn btn-default btn-primary" data-dismiss="modal" value="Logout" href="${pageContext.request.contextPath}/logout" />
                            <button class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
