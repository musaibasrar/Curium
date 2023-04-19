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
        <script src="/alfarooq/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/alfarooq/css/bootstrap.min.css">
        <script src="/alfarooq/js/jquery.min.js"></script>
        <script src="/alfarooq/js/bootstrap.min.js"></script>
        <script src="/alfarooq/js/popper.min.js"></script>
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
		  width: fit-content;
		  height: fit-content; 
		  text-align: center;
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
	response.sendRedirect("/alfarooq/UserProcess/sessionTimeOut");
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
        					<td>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Al-Farooq Urdu PrePrimary School </label><br>
        						<a target="_parent" 
        						 href="/alfarooq/UserProcess/multiUser?branchid=2"> <img
									src="/alfarooq/images/login.svg" width="25" height="25" alt="Login" />Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col" style="padding-bottom: 40px;"> 
        			<div id="rcorners1">
        			<table>
        				<tr>
        					<td>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Al-Farooq Urdu Higher Primary School</label><br>
        						<a target="_parent"
        						 href="/alfarooq/UserProcess/multiUser?branchid=3"> <img
									src="/alfarooq/images/login.svg" width="25" height="25" alt="Login"/>Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	<div class="col" style="padding-bottom: 40px;">
        		<div id="rcorners1">
        			<table>
        				<tr>
        					<td>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Al-Farooq Urdu High School</label><br>
        						<a target="_parent"
        						 href="/alfarooq/UserProcess/multiUser?branchid=4"> <img
									src="/alfarooq/images/login.svg" width="25" height="25" alt="Login"/>Login</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	
        	 </div>
        </div>
</body>    
</html>