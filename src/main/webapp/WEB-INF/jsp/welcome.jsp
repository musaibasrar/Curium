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
        <script src="/vasu/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/vasu/css/bootstrap.min.css">
        <script src="/vasu/js/jquery.min.js"></script>
        <script src="/vasu/js/bootstrap.min.js"></script>
        <script src="/vasu/js/popper.min.js"></script>
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
	response.sendRedirect("/vasu/UserProcess/sessionTimeOut");
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
					<p style="color: #93051f; font-weight: bold;">${branchname}</p>
				</td>

			</tr>

		</table>
	</div>

	<div class="container">
  <div class="row justify-content-center text-center py-5">
    <div class="col-12 col-sm-6 col-md-4 mb-4">
      <div id="rcorners1" class="p-3 border rounded shadow">
        <label style="font-family: Tahoma; font-weight: bolder; color: #5E87B0; font-size: 18px;">Vasu College Of Nursing</label><br>
        <a target="_parent" href="/vasu/UserProcess/multiUser?branchid=2">
          <img src="/vasu/images/login.svg" width="25" height="25" alt="Login" /> Login
        </a>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 mb-4">
      <div id="rcorners1" class="p-3 border rounded shadow">
        <label style="font-family: Tahoma; font-weight: bolder; color: #5E87B0; font-size: 18px;">Vasu College Of Physiotherapy</label><br>
        <a target="_parent" href="/vasu/UserProcess/multiUser?branchid=3">
          <img src="/vasu/images/login.svg" width="25" height="25" alt="Login" /> Login
        </a>
      </div>
    </div>
    <div class="col-12 col-sm-6 col-md-4 mb-4">
      <div id="rcorners1" class="p-3 border rounded shadow">
        <label style="font-family: Tahoma; font-weight: bolder; color: #5E87B0; font-size: 18px;">Vasu College Of Paramedical</label><br>
        <a target="_parent" href="/vasu/UserProcess/multiUser?branchid=3">
          <img src="/vasu/images/login.svg" width="25" height="25" alt="Login" /> Login
        </a>
      </div>
    </div>
  </div>
</div>

</body>    
</html>