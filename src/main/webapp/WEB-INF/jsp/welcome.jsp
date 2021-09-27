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
        <form id="form1" method="post">
    		<table style="align-self: center;">
      			<tr>
      				<td height="200px;" width="100px;"></td>
          			<td>
          			<p style="text-transform: uppercase;color: #143160;font-weight: bolder;">Welcome, <label style="color: #93051f;"><c:out default="" value="${username}"/> </label></p><br><br>
          			
          			<p style="color: #143160;font-weight: bolder;">You can navigate your way through CURIUM using: </p><br><br>
					
					
					<i class="fa fa-arrow-up" style="font-size:28px;color:#1b5605;"></i>&nbsp;&nbsp;<label style="color: #1b5605;">The shortcuts at the top</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<i class="fa fa-arrow-left" style="font-size:28px;color:#086d7c;"></i>&nbsp;&nbsp;<label style="color: #086d7c;">The menu to the left</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<i class="fa fa-bar-chart" style="font-size:28px;color:#93051f;"></i>&nbsp;&nbsp;<label style="color: #93051f;">DashBoard at the top right</label>
          			
         			</td>
          			
      			</tr>
      			
    		</table>
       </form>
    </body>
</html>
