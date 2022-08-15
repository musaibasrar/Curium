<%-- 
    Document   : login
    Created on : Jan 9, 2012, 5:44:56 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	 <title>Curium - School ERP</title>
	<link rel="stylesheet" href="/rauzamission/css/bootstrap3.min.css">
	<script type="text/javascript" src="/rauzamission/js/openWindow.js"></script>
	
	<style type="text/css">
	.main-content{
	width: 50%;
	border-radius: 20px;
	box-shadow: 0 5px 5px rgba(0,0,0,.4);
	margin: 5em auto;
	display: flex;
}
.company__info{
	background-color: #1786b4;
	border-top-left-radius: 20px;
	border-bottom-left-radius: 20px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	color: #fff;
}
.fa-android{
	font-size:3em;
}
@media screen and (max-width: 640px) {
	.main-content{width: 90%;}
	.company__info{
		display: none;
	}
	.login_form{
		border-top-left-radius:20px;
		border-bottom-left-radius:20px;
	}
}
@media screen and (min-width: 642px) and (max-width:800px){
	.main-content{width: 70%;}
}
.row > h2{
	color:#1786b4;
}
.row > h3{
	color:#1786b4;
}
.login_form{
	background-color: #fff;
	border-top-right-radius:20px;
	border-bottom-right-radius:20px;
	border-top:1px solid #ccc;
	border-right:1px solid #ccc;
}
form{
	padding: 0 2em;
}
.form__input{
	width: 70%;
	border:0px solid transparent;
	border-radius: 0;
	border-bottom: 1px solid #aaa;
	padding: 1em .5em .5em;
	padding-left: 2em;
	outline:none;
	margin:1.5em auto;
	transition: all .5s ease;
}
.form__input:focus{
	border-bottom-color: #1786b4;
	box-shadow: 0 0 5px rgba(0,80,80,.4); 
	border-radius: 4px;
}
.btn{
	transition: all .5s ease;
	width: 70%;
	border-radius: 30px;
	color:#1786b4;
	font-weight: 600;
	background-color: #fff;
	border: 1px solid #1786b4;
	margin-top: 1.5em;
	margin-bottom: 1em;
}
.btn:hover, .btn:focus{
	background-color: #1786b4;
	color:#fff;
}
	
	</style>
	
	     <script type="text/javascript">
            

            function redirect(){
                
                var form1=document.getElementById("form1");
                form1.action="/rauzamission/UserProcess/authenticate";
                form1.submit();
            }
        </script>
        
</head>
   
      
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <script type="text/javascript">
            var flag1=<c:out default="" value="${login_success}"/>;
            var type='<c:out default="" value="${userType}"/>';


            if(flag1){
            	
            	if(type=='superadmin'){
                    window.open('/index_superadmin','_self');
                }else if(type=='admin'){
                    window.open('/rauzamission/index_admin','_self');
                }else if(type=='feescollector'){
                    window.open('/index_feescollector','_self');
                }else if(type=='officeadmin'){
                	window.open('/rauzamission/index_officeadmin','_self');
                }
            }
            else if(!flag1){
            	window.open('/rauzamission/loginFail','_self');
            }
        </script>
        
        
       <!-- Main Content -->
	<div class="container-fluid">
		<div class="row main-content bg-success text-center">
			<div class="col-md-4 text-center company__info">
			<span class="company__logo">
			<h2><img border="0" style="vertical-align: text-bottom;height: 136px;width: 120px;" alt="rauzamission" src="/rauzamission/images/logo.png"></h2>
			</span>
				<!-- <h3 class="company_title">Zaiqa<br> Enterprises</h3> -->
			</div>
			<div class="col-md-8 col-xs-12 col-sm-12 login_form ">
				<div class="container-fluid">
					<div class="row">
						<!-- <span class="company__logo"><h2><img border="0" style="vertical-align: text-bottom;height: 50px;width: 73px;" alt="ideoholic" src="/rauzamission/images/shaheenlogo.png"></h2></span> -->
						<h3>Log In</h3>
					</div>
					<div class="row">
						<form action="/rauzamission/UserProcess/authenticateUser"  method="post" class="form-group">
							<div class="row">
								<input type="text" name="loginName" id="loginName" class="form__input" placeholder="Username">
							</div>
							<div class="row">
								<!-- <span class="fa fa-lock"></span> -->
								<input type="password" name="password" id="password" class="form__input" placeholder="Password">
							</div>
							
							<div class="row">
								<input type="submit" value="Login" class="btn">
							</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<div class="container-fluid text-center footer">
		<p>
			<!-- <p>An <img border="0" style="vertical-align: text-bottom;" alt="ideoholic" src="/rauzamission/images/ideoholic.png"> image with a default alignment.</p> --> 
			
			<a href="http://www.ideoholic.com" >
				Powered by <img border="0" style="vertical-align: text-bottom;" alt="ideoholic" src="/rauzamission/images/ideoholic.png">
			</a>
			
	</div>
    </body>
</html>