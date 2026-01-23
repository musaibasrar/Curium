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
        <script src="/daralmajd/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/daralmajd/css/bootstrap.min.css">
        <script src="/daralmajd/js/jquery.min.js"></script>
        <script src="/daralmajd/js/bootstrap.min.js"></script>
        <script src="/daralmajd/js/popper.min.js"></script>
         <link rel="stylesheet" href="/daralmajd/css/font-awesome.css">
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
	
	<style>
  .row {
  display: flex;
  justify-content: center; /* 👈 Center horizontally */
  flex-wrap: wrap;
  gap: 20px;
}

  .col {
    flex: 1; /* Makes columns share space equally */
    min-width: 250px; /* Ensures minimum width for responsiveness */
  }

  #rcorners1 {
    border: 1px solid #ccc;
    padding: 15px;
    border-radius: 10px;
  }
</style>
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/daralmajd/UserProcess/sessionTimeOut");
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
				<br><br>
					<p
						style="text-transform: uppercase; color: #143160; font-weight: bolder;">
						Welcome, <label style="color: #93051f;"><c:out default=""
								value="${username}" /> </label>
					</p>
					<c:set var="branch" value="${branchid - 1}" />
					<p style="color: #93051f; font-weight: bolder;">${branchname}-${branch}</p>
					<br><br>
					<p style="color: #143160;font-weight: bolder;">You can navigate your way through CURIUM using: </p><br><br>
					
					
					<i class="fa fa-arrow-up" style="font-size:28px;color:#1b5605;"></i>&nbsp;&nbsp;<label style="color: #1b5605;">The shortcuts at the top</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<i class="fa fa-arrow-left" style="font-size:28px;color:#086d7c;"></i>&nbsp;&nbsp;<label style="color: #086d7c;">The menu to the left</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<i class="fa fa-bar-chart" style="font-size:28px;color:#93051f;"></i>&nbsp;&nbsp;<label style="color: #93051f;">DashBoard at the top right</label>
				</td>

			</tr>

		</table>
	</div>
</body>    
</html>