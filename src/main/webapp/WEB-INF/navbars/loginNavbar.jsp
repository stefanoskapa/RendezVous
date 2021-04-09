<link rel="stylesheet" href="/navbar/navbar.css">
<div>
    <nav id="nav" class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button">
        <div class="container-fluid">
            <a id="calendar-logo-container" href="${pageContext.request.contextPath}/">
                <i id="calendar-logo" class="fa fa-calendar" data-aos="zoom-in" data-aos-once="true"></i>
            </a>
            <a id="navbar-brand1" class="navbar-brand" href="${pageContext.request.contextPath}/">&nbsp;&nbsp;RendezVouz</a>
            <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                        <a id="navlink1" class="nav-link" href="${pageContext.request.contextPath}/login">
                            <i id="user-circle" class="fa fa-user-circle"></i>
                            &nbsp;Login
                        </a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <div id="nav-item-dropdown1" class="nav-item dropdown">
                            <a id="dropdown-toggle1" class="dropdown-toggle d-table-cell" data-toggle="dropdown" aria-expanded="false">
                                &nbsp;
                                <i id="adress-card" class="fa fa-address-card"></i>
                                &nbsp;Register
                            </a>
                            <div id="dropdown-menu1" class="dropdown-menu dropdown-menu-right" role="menu">
                                <a id="dropdownitem1" class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/client-register">
                                    <i class="fa fa-user" data-bs-hover-animate="bounce"></i>
                                    &nbsp;As User
                                </a>
                                <a id="dropdown-item2" class="dropdown-item" role="presentation" href="${pageContext.request.contextPath}/company-register">
                                    <i class="fa fa-building-o" data-bs-hover-animate="bounce"></i>
                                    &nbsp;As Partner
                                </a>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>