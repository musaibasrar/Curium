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
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dash Board</title>
        <script src="/alirfan/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/alirfan/css/bootstrap.min.css">
        <script src="/alirfan/js/jquery.min.js"></script>
        <script src="/alirfan/js/bootstrap.min.js"></script>
        <script src="/alirfan/js/popper.min.js"></script>
    </head>
    
	<style type="text/css">

		@font-face {
		  font-family: "IBMPlexSans";
  		  src: url("/alirfan/fonts/IBMPlexSans-Regular.ttf");
		}

		#rcorners1 {
		  border-radius: 25px;
		  border: 0px solid;
		  padding: 10px; 
		  width: 340px;
		  height: 80px; 
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
	response.sendRedirect("/alirfan/UserProcess/sessionTimeOut");
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
    	

	<div style="padding-left: 20px;height: 50px;">
		<table style="align-self: left;">
			<tr>
				<td height="150px;"></td>
			</tr>

		</table>
	</div>

	<div class="row" style="padding-left: 20px;">
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1" style="background-color:#243664">
        			
        			<table>
        				<tr>
        					<td style="padding-left:10px;padding-top:1px;">
        					<a target="mainFrame" style="color:#ffffff;font-size:34px;"
								href="/alirfan/StudentProcess/ViewDetailsbyexternalid?id=${username}&urlbranchid=${Parents.student.branchid}"> <img
									src="/alirfan/images/studentprofile.svg" width="50" height="50"
									alt="Student Profile" style="vertical-align: bottom;" />Student Profile
							</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<br>
        	
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1" style="background-color:#FF914D">
        			
        			<table>
        				<tr>
        					<td style="padding-left:10px;padding-top:1px;">
        					<a target="mainFrame" style="color:#ffffff;font-size: 34px;"
								href="/alirfan/DiaryProcess/viewDiaryStudentParent?id=${username}&urlbranchid=${Parents.student.branchid}"> <img
									src="/alirfan/images/diary.svg" width="50" height="50"
									alt="Student Profile" style="vertical-align: bottom;" />Class Diary
							</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<br>
        	
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1" style="background-color:#FFCC00">
        			
        			<table>
        				<tr>
        					<td style="padding-left:10px;padding-top:1px;">
        					<a target="mainFrame" style="color:#ffffff;font-size:34px;"
								href="/alirfan/StudentProcess/ViewFeesDetailsbyexternalid?id=${username}&urlbranchid=${Parents.student.branchid}"> <img
									src="/alirfan/images/fees.svg" width="50" height="50"
									alt="Student Profile" style="vertical-align: bottom;" />Fees
							</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<br>
        	
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1" style="background-color:#90CCB8">
        			
        			<table>
        				<tr>
        					<td style="padding-left:10px;padding-top:1px;">
        					<a target="mainFrame" style="color:#ffffff;font-size:34px;"
								href="/alirfan/MarksDetailsProcess/generateReportParent?id=${username}"> <img
									src="/alirfan/images/progressreport.svg" width="50" height="50"
									alt="Student Profile" style="vertical-align: bottom;" />Progress Report
							</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<br>
        	
        	<div class="col" style="padding-bottom: 40px;">
        			<div id="rcorners1" style="background-color:#9D0176">
        			
        			<table>
        				<tr>
        					<td style="padding-left:10px;padding-top:1px;">
        					<a target="mainFrame" style="color:#ffffff;font-size:34px;"
								href="/alirfan/StudentDiaryProcess/viewDiaryStudentParent?id=${username}&urlbranchid=${Parents.student.branchid}"> <img
									src="/alirfan/images/logbook.svg" width="50" height="50"
									alt="Logbook" style="vertical-align: bottom;" />Logbook
							</a>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<br>
        	 
        	 
        </div>
</body>    
</html>