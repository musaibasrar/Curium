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

<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
}

.headerTextLeft {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: left;
}

.dataTextBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextBoldLeft {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 14px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}
-->

span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 300px;
    font-weight: normal;
}
</style>
	<script type="text/javascript" src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Bonafide Certificate</title>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/shatabdi/UserProcess/sessionTimeOut");
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
<body style="text-align: center" class="bodymargin">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br>
		<table align="center">
			<tr>
			<td class="dataTextBoldLeft">
			<br><br>
				
			
			</tr>
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					<u>BONAFIDE CERTIFICATE</u>
					<br><br>
				</td>
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Mr./Ms. &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:400px;">&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					Son/Daughter of &nbsp;&nbsp;<span style="font-weight: bold;text-transform: capitalize;width:550px;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.fathersname}" /></span>
				&nbsp;&nbsp;&nbsp;&nbsp;
					</span>
					
					</h3>
				</td>
				
				<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >R/O
					<span style="font-weight: bold;text-transform: capitalize;width:650px;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.addresspermanent}" /></span>
					
					</span>
					
					</h3>
				</td>
			
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
					 a student of this School/College.He/She is/was studied/passed/failed in&nbsp;&nbsp; <span style="font-weight: bold;width: 100px;">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
					</span>
					during the year &nbsp;&nbsp;&nbsp;&nbsp;
					</h3>
					
				</td>
				

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				<span style="font-weight: bold;width: 80px;">${currentAcademicYear}</span>
					 His/ her date of birth as per School/College record is
					<span style="font-weight: bold;text-transform: capitalize;width: 120px;">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy"/></span>
					</h3>
					
				</td>
				

			</tr>
			
			<tr>
			
				<td align="center"  >
				
				<h3 style="font-weight: normal;" >
                   He bears good moral character.
					</h3>
					
				</td>
				

			</tr>
			
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		<tr>
			<td style="padding-left: 10px;">Date&nbsp;
			<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>	
			<td align="right" style="padding-right: 70px;">Principal</td>
		</tr>
		
			<tr>
              <td align="center"><a id="print" href="/shatabdi/DocumentsProcess/printBonafide">Print</a></td>
            </tr>
		</TABLE>
	</form>
</body>
</html>
