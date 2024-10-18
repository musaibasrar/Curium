<%-- 
    Document   : bonafide certificate
    Created on : Mar 17 2018, 12:32 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style>
        .borderset{
        border:1px solid black;
        border-collapse:collapse;
        }
        span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 200px;
    font-weight: normal;
    text-align:center;
}
        </style>
	<script type="text/javascript" src="/littleangels/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/littleangels/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Article 371 Certificate</title>
        <script type="text/javascript">
             window.onload = function(){
            	 window.print();
             }
        </script>
        
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/littleangels/UserProcess/sessionTimeOut");
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
<body style="font-size:20px;">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
	<table align="center">
<tr>
<td style="text-align:center;">
<img src="/littleangels/images/Karnatakalogo.png" width="80" height="69"/>
</td>
</tr>
<tr>
<td style="text-align:center;"><h2 style="margin:0px;">
ANNEXURE-C</h2>
</td>
</tr>
<tr>
<td style="text-align:center;"><b>
STUDY CERTIFICATE</b>
</td>
</tr>
<tr>
<td style="text-align:center;"><i>
[Under Article 371(J)]</i>
</td>
</tr>
<tr>
<td style="text-align:center;"><i>
(See Rule 4(c)(ii))</i>
</td>
</tr>
<tr>
<td style="text-align:center;"><b>
[The Karnataka Public Employment(Reservation in appoinment </b>
</td>
</tr>
<tr>
<td style="text-align:center;"><b>
for Hydrabad-Karnataka Region)</b>
</td>
</tr>
<tr>
<td style="text-align:center;"><b>
Rules for Issue Certificate, 2013]</b>
</td>
</tr>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
This is to certified that Sri/Smt  <span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;width:320px;">
<c:out value="${studentdetailsbonafide.student.name}" />
</span>   D/O,S/O</td>
</tr>
<tr>
<td>
<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;width:325px;">
<c:out value="${studentdetailsbonafide.fathersname}" />
</span>  has been studied in<b> THE LITTLE ANGELS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
</td>
</tr>
<tr>
<td>
<b>HIGH SCHOOL,</b>Taluka<b> RAICHUR </b>District <b> RAICHUR </b> During the Period Noted below:-
</td>
</tr>
</table>
<table class="borderset" align="center"  width="740px">
<tr>
<th class="borderset">
SL No.
</th>
<th class="borderset">
Class/Level
</th >
<th class="borderset">
From Which to which date
</th>
<th class="borderset">
Reference of Document
</th>
</tr>
<tr>
<td class="borderset">
&nbsp;&nbsp;&nbsp;1
</td>
<td class="borderset" style="text-align:center;">
<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
</td>
<td class="borderset" style="text-align:center;">
From ${studentdetailsbonafide.student.yearofadmission} to ${studentdetailsbonafide.student.promotedyear}

</td>
<td class="borderset">
</td>
</tr>
</table>
<p align="center">He/She has passed/failed following exams during following year:-</p>
<table class="borderset" align="center"  width="740px">
<tr>
<th class="borderset">
SL No.
</th>
<th class="borderset">
Name of Class/Exam/Level
</th>
<th class="borderset">
Year
</th>

<th class="borderset">
Passed/Failed
</th>
<th class="borderset">
Reference of Document
</th>
</tr>
<tr>
<td class="borderset" style="text-align:center;">
&nbsp;&nbsp;&nbsp;1
</td>
<td class="borderset" style="text-align:center;">
<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
</td>
<td class="borderset" style="text-align:center;">
${currentAcademicYear}
</td>
<td class="borderset" style="text-align:center;">
passed
</td>
<td class="borderset">
</td>
</tr>
</table>	
<table align="center">
<tr><td>
Place: Raichur</td><td>Name:&nbsp;&nbsp;ASMA SUHA
</td></tr>
<tr><td>
<br>
</td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td>
Date:<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" >
</td>
<td>
Head of Institution 
</td>
</tr>
<tr>
<td colspan="2" style="text-align:center">
CERTIFICATE 
</td>
</tr>
<tr>
<td colspan="2">
&emsp;&emsp;&emsp;&emsp;&emsp;This is to certify that I have satisfied myself that the certificate given above is<br> true as per available record
</td>
</tr>
<tr>
<td><br></td>
</tr>
<tr>
<td>
Date:&nbsp;<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;width:300px;">
<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" >
</span> 
&nbsp;&nbsp;&nbsp;</td><td>
Block Education Officer&nbsp;&nbsp;&nbsp;&nbsp;
</td></tr>
<tr>
<td></td>
<td><br></td>
</tr>
<tr>
<td>
</td><td>
<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;width:150px;"></span> Taluka
</td>

</tr>
<tr>
<td></td>
<td><br></td>
</tr>
<tr><td>
</td><td>
<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;width:150px;"></span> District
</td></tr>

</table>
	
	</form>
</body>
</html>
 