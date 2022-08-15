<%-- 
  Document   : Dash Board
  Created on : Jan 13, 2012, 12:21:03 PM
  Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Welcome</title>
        <script src="/divine/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/divine/css/bootstrap.min.css">
        <script src="/divine/js/jquery.min.js"></script>
        <script src="/divine/js/bootstrap.min.js"></script>
        <script src="/divine/js/popper.min.js"></script>
    </head>
    
	<style type="text/css">

		@font-face {
		  font-family: "IBMPlexSans";
  		  src: url("fonts/IBMPlexSans-Regular.ttf");
		}

		#rcorners1 {
		  border-radius: 25px;
		  border: 0px solid;
		  padding: 10px; 
		  width: 340px;
		  height: 160px; 
		  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		}
		
		
		#labelname {
		 	font-family: IBMPlexSans;
		 	font-size: 14px;
		}
		
		#labelnumber {
		 	font-family: IBMPlexSans;
		 	font-size: 14px;
		}
		
			a:link {
                color: black;
                text-decoration: none;
                font-family: arial;
                font-size: 14px;
            }
            a:active {
                color: #ef5b00;
                text-decoration: underline;
            }
            
			a:hover {
				text-decoration: underline;
			}
		
	</style>    
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/divine/UserProcess/sessionTimeOut");
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
    	

	<div style="padding-left: 20px;">
		<table style="align-self: left;">
			<tr>
				<td height="150px;"></td>
				<td>
					<p
						style="text-transform: uppercase; color: #143160; font-weight: bolder;">
						Welcome, <label style="color: #93051f;"><c:out default=""
								value="${username}" /> </label>
					</p>
					<p style="color: #93051f; font-weight: bolder;">${branchname}</p>
				</td>

			</tr>

		</table>
	</div>

	<div class="row" style="padding-left: 20px;">
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1">
        			
        			<table>
        				<tr>
        					<td style="padding-left:90px;">
        						<!-- <label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;padding-left: 50px;"> 6529 </label> --><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Divine School </label><br>
        						<a target="_parent" style="padding-left: 20px;"
        						 href="/divine/UserProcess/multiUser?branchid=2"> <img
									src="/divine/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col" style="padding-bottom: 40px;"> 
        			<div id="rcorners1">
        			<table>
        				<tr>
        					<td style="padding-left:90px;">
        					<!-- <label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;padding-left: 80px;"> 6516 </label> --><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Divine P.U. College</label><br>
        						<a target="_parent" style="padding-left: 40px;"
        						 href="/divine/UserProcess/multiUser?branchid=3"> <img
									src="/divine/images/login.svg" width="25" height="25" alt="Login"/>Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	<!-- <div class="col" style="padding-bottom: 40px;">
        		<div id="rcorners1">
        			<table>
        				<tr>
        					<td style="padding-left:50px;">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;padding-left: 80px;"> 6521 </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Ismail Ideal Urdu High School</label><br>
        						<a target="_parent" style="padding-left: 70px;"
        						 href="/divine/UserProcess/multiUser?branchid=4"> <img
									src="/divine/images/login.svg" width="25" height="25" alt="Login"/>Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	
        	 </div>
        	 
        	<div class="col" style="padding-bottom: 40px;">
        		<div id="rcorners1">
        			<table>
        				<tr>
        					<td style="padding-left:15px;">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;padding-left: 120px;"> 6533 </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Ismail Ideal Higher & Primary School</label><br>
        						<a target="_parent" style="padding-left: 110px;"
        						 href="/divine/UserProcess/multiUser?branchid=5"> <img
									src="/divine/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	 </div>
        	 
        	 
        	 <div class="col" style="padding-bottom: 40px;"> 
        			<div id="rcorners1">
        			<table>
        				<tr>
        					<td style="padding-left:50px;">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;padding-left: 100px;"> 6520 </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> The Roshan Planet High School </label><br>
        						<a target="_parent" style="padding-left: 90px;"
        						 href="/divine/UserProcess/multiUser?branchid=6"> <img
									src="/divine/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	 
        	 <div class="col" style="padding-bottom: 10px;">
        		<div id="rcorners1">
        			<table>
        				<tr>
        					<td style="padding-left:10px;">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;padding-left: 130px;"> 6534 </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> The Roshan Planet P.U. Science College </label><br>
        						<a target="_parent" style="padding-left: 120px;"
        						 href="/divine/UserProcess/multiUser?branchid=7"> <img
									src="/divine/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	 </div> -->
        </div>
</body>    
</html>