<%-- 
    Document   : Welcome 
    Created on : Apr 08, 2019, 09:38:53 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CURIUM</title>
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/popper.min.js"></script>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
}else user = (String) session.getAttribute("userAuth");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
    <body>
    		<br>
    		<div class="container">
    		
    			<div class="row">
    					
    				<div class="col" align="center">
    				<label style="font-family: Tahoma;font-weight: bolder;color: #143160;font-size: 20px;"> ${branchname} </label>
    				</div>
    						
    			</div>
    		
    			<div class="row">
    					
    				<div class="col">
    				<p style="text-transform: uppercase;color: #143160;font-weight: bolder;">Welcome, <label style="color: #93051f;"><c:out default="" value="${username}"/> </label></p>
    				</div>
    						
    			</div>
    			<br>
    			<div class="row">
    				<div class="col">
    				<p style="color: #143160;font-weight: bolder;">You can navigate your way through CURIUM using: </p>
    				</div>
    						
    			</div>
    			<br>
    			
    			<div class="row">
    				<div class="col">
    				<i class="fa fa-arrow-up" style="font-size:28px;color:#1b5605;"></i>&nbsp;&nbsp;<label style="color: #1b5605;">The shortcuts at the top</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				</div>
    				<div class="col">
    				<i class="fa fa-arrow-left" style="font-size:28px;color:#086d7c;"></i>&nbsp;&nbsp;<label style="color: #086d7c;">The menu to the left</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			</div>
    			
    			<div class="col">
					<i class="fa fa-bar-chart" style="font-size:28px;color:#93051f;"></i>&nbsp;&nbsp;<label style="color: #93051f;">The DashBoard at the top right</label>
    			</div>
    			
    			</div>
    			
    			<div class="row">
    				<div class="col" align="center">
					<img src="images/welcomelogo.jpg" width="250" height="250">
    				</div>
    				
    			</div>
    			
    	</div>		
    </body>
</html>
