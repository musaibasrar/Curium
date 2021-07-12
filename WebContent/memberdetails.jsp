<%-- 
    Document   : Dash Board
    Created on : Nov 11, 2020, 3:48:17 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html >
    <head >
     <%
            response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
        %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Masjid-e-Ali</title>
        <link href="css/styles.css" rel="stylesheet" />
        <!-- <link href="css/bootstrap.min.css" rel="stylesheet"  />-->
        <link href="css/dataTables.bootstrap4.min.css" rel="stylesheet" /> 
        <script src="js/all.min.js"></script>
        <script src="js/jquery.min.js"></script>
      <!--  <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script> -->

	<script type="text/javascript">
        	
        	function getSubscriber() {
				var form2 = document.getElementById("form2");
				if(form2.checkValidity()) {
					form2.subscribersearch.disabled = true;
					form2.action = "Controller?process=PersonalProcess&action=getSubscribers";
					form2.submit();
				  }
			}
        	
        	
        </script>
        
       
    </head>
    <%
	//allow access only if session exists
	String user = null;
	if (session.getAttribute("userAuth") == null) {
		response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
	} else
		user = (String) session.getAttribute("userAuth");
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user"))
				userName = cookie.getValue();
			if (cookie.getName().equals("JSESSIONID"))
				sessionID = cookie.getValue();
		}
	}
%>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark border-bottom">
            <a class="navbar-brand" href="#"><img src="images/aliwhite.jpg" height="50" width="150"></a>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" id="form2" method="post">
                <div class="input-group">
                </div>
            </form>
            <!-- Navbar-->
            <form id="form3" method="post">
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="Controller?process=UserProcess&action=authenticateUser&loginName=${memberusername}&password=${student.studentexternalid}">Refresh</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="Controller?process=UserProcess&action=logout">Logout</a>
                    </div>
                </li>
            </ul>
            </form>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        	<div class="sb-sidenav-menu-heading">Assalam-u-alaikum,<br>${student.name}</div>
                            <a class="nav-link" href="index_member.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-home"></i></div>
                                Home
                            </a>
                            <a class="nav-link" href="memberdetails.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                               		Member Details
                            </a>
                            <a class="nav-link" href="contributiondetails.jsp" >
                                <div class="sb-nav-link-icon"><i class="fas fa-money-bill-alt"></i></div>
                               		Contribution Details
                            </a>
                             <a class="nav-link" href="paymenthistory.jsp" >
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                               		Payment History
                            </a>
                        </div>
                    </div>
                    <!-- <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Start Bootstrap
                    </div> -->
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                <div class="container-fluid">
                			<br>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Member Details</li>
                        </ol>
                        <div class="container py-5">
    <div class="row">
        <div class="col-md-10 mx-auto">
            <form>
                <div class="form-group row" style="justify-content: flex-start;">
                    <div class="col-sm-6">
                        <label  style="text-transform: capitalize;font-weight: bold;">Member name:</label> <label style="text-transform: capitalize;color: #0b7624"> &nbsp;${student.name}</label>
                    </div>
                    <div class="col-sm-6">
                        <label  style="text-transform: capitalize;font-weight: bold;">Referral name:</label> <label style="text-transform: capitalize;color: #0b7624"> &nbsp;${student.classstudying}</label>
                    </div>
                </div>
                <div class="form-group row"  style="justify-content: flex-start;">
                    <div class="col-sm-6">
                        <label  style="text-transform: capitalize;font-weight: bold;">Contact Number:</label> <label style="text-transform: capitalize;color: #0b7624"> &nbsp;${student.classadmittedin}</label>
                    </div>
                    <div class="col-sm-6">
                        <label  style="text-transform: capitalize;font-weight: bold;">Email:</label> <label style="text-transform: capitalize;color: #0b7624"> &nbsp;${student.bloodgroup}</label>
                    </div>
                </div>
                <div class="form-group row"  style="justify-content: flex-start;">
                    <div class="col-sm-6">
                        <label  style="text-transform: capitalize;font-weight: bold;">Address:</label> <label style="text-transform: capitalize;color: #0b7624"> &nbsp;${student.remarks}</label>
                    </div>
                    <div class="col-sm-3">
                        
                    </div>
                    
            </form>
        </div>
    </div>
</div>
                       
                    </div>
                </main>
               <!--  <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted"></div>
                            <div>
                                <p>Powered By <a href="www.ideoholic.com">IDEOHOLIC</a></p>
                            </div>
                        </div>
                    </div>
                </footer> -->
            </div>
        </div>
        
         <script src="js/jquery-3.5.1.slim.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scripts.js"></script>
		<script src="js/dataTable/jquery.dataTables.min.js"></script>
		<script src="js/dataTables.bootstrap4.min.js" ></script>
		<script src="assets/demo/datatables-demo.js"></script>
    </body>
    
</html>

