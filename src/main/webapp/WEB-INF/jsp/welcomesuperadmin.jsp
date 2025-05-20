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
        <title>Dash Board</title>
        <script src="/scholargroup/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/scholargroup/css/bootstrap.min.css">
        <script src="/scholargroup/js/jquery.min.js"></script>
        <script src="/scholargroup/js/bootstrap.min.js"></script>
        <script src="/scholargroup/js/popper.min.js"></script>
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
	response.sendRedirect("/scholargroup/UserProcess/sessionTimeOut");
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
				</td>

			</tr>

		</table>
	</div>

	<div class="row" style="padding-left: 20px;">
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1">
        			
        			<table>
        				<tr>
        					<td style="padding-left:80px;">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> N. M. Balwa High School, Pirojpura</label><br>
        						<a target="_parent" style="padding-left: 40px;"
        						 href="/scholargroup/UserProcess/multiUser?branchid=2"> <img
									src="/scholargroup/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col" style="padding-bottom: 40px;"> 
        			<div id="rcorners1">
        			<table>
        				<tr>
        					<td style="padding-left:65px;">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Scholar Education Campus</label><br>
        						<a target="_parent" style="padding-left: 70px;"
        						 href="/scholargroup/UserProcess/multiUser?branchid=3"> <img
									src="/scholargroup/images/login.svg" width="25" height="25" alt="Login"/>Login</a>
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
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Rightway Scholar Institute</label><br>
        						<a target="_parent" style="padding-left: 70px;"
        						 href="/scholargroup/UserProcess/multiUser?branchid=4"> <img
									src="/scholargroup/images/login.svg" width="25" height="25" alt="Login"/>Login</a>
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
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;">Scholar Career Academy</label><br>
        						<a target="_parent" style="padding-left: 110px;"
        						 href="/scholargroup/UserProcess/multiUser?branchid=5"> <img
									src="/scholargroup/images/login.svg" width="25" height="25" alt="Login" />Login</a>
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
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Navabsaheb Talemohammedkhan S.J. EDUCATIONAL Campus</label><br>
        						<a target="_parent" style="padding-left: 90px;"
        						 href="/scholargroup/UserProcess/multiUser?branchid=6"> <img
									src="/scholargroup/images/login.svg" width="25" height="25" alt="Login" />Login</a>
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
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;">SCANIK,Dr Nakadar Institute of Knowledge </label><br>
        						<a target="_parent" style="padding-left: 120px;"
        						 href="/scholargroup/UserProcess/multiUser?branchid=7"> <img
									src="/scholargroup/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	 </div>
        	 
        	 
        </div>
</body>    
</html>