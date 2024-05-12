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
        <script src="/alirfan/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/alirfan/css/bootstrap.min.css">
        <script src="/alirfan/js/jquery.min.js"></script>
        <script src="/alirfan/js/bootstrap.min.js"></script>
        <script src="/alirfan/js/popper.min.js"></script>
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
		  width: 240px;
		  height: 220px; 
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
    	<div class="container-sm" align="center">
   			<!--  <label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 20px;"> DASH BOARD </label> --><br><br>
        </div>
        
               
        <div class="row" align="center">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	
        	<div class="col"> 
        			<div id="rcorners1">
        			<table>
        			
        				<tr>
        				<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        				<td>
        				<label id="labelname" style="font-size: 18px; text-decoration: underline;font-weight: bold;">Fees Summary</label><br>
        				</td>
        				</tr>	
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<img src="/alirfan/images/totalfees.svg" height="20" width="20"/>
        						<label id="labelname">Total Fees&nbsp;&nbsp;&nbsp;</label><br>
        						<img src="/alirfan/images/feespaid.svg" height="20" width="20"/>	
        						<label id="labelname">Total Fees Paid</label><br>
        						<img src="/alirfan/images/feesdue.svg"  height="20" width="20"/>
        						<label id="labelname">Total Fees Due&nbsp;</label><br/>
        						<img src="/alirfan/images/monthlyfees.svg"  height="20" width="20"/>	
        						<label id="labelname">${Currentmonth} Fees</label><br/>
        						<img src="/alirfan/images/todayscollection.svg" height="20" width="20"/>
        						<label id="labelname">Today's Fees</label>
        						<br>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalFeesAmountDashBoard}</label><br>
        						<label id="labelnumber">${totalPaidAmountDashBoard}</label><br>
        						<label id="labelnumber">${totalDueAmountDashBoard}</label><br>
        						<label id="labelnumber">${sumOfFeesMonthly}</label><br>	
        						<label id="labelnumber">${sumOfFeesDaily}</label><br>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<%-- <div class="col">
        		<div id="rcorners1">
        			<table>
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        					<img src="/alirfan/images/feespaid.svg" height="20" width="20"/>	
        						<label id="labelname">Total Fees Paid</label><br>
        						<img src="/alirfan/images/feesdue.svg"  height="20" width="20"/>
        						<label id="labelname">Total Fees Due&nbsp;</label>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalPaidAmountDashBoard}</label><br>
        						<label id="labelnumber">${totalDueAmountDashBoard}</label>
        					</td>
        				</tr>
        			</table>
        			</div>
        	
        	 </div>
        	 
        	 <div class="col">
        		<div id="rcorners1">
        			<table>
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<img src="/alirfan/images/todayscollection.svg" height="20" width="20"/>
        						<label id="labelname">Today's Fees</label>
        						<br>		
        						<label id="labelname">Today's Expenses</label>	
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${sumOfFeesDaily}</label><br>
        						<label id="labelnumber">${dailyexpenses}</label>
        					</td>
        				</tr>
        			</table>
        			</div>
        	
        	 </div> --%>
        	 
        </div>
        
             <div><br><br></div>
             
</body>    
</html>