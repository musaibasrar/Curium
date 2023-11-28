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
.dataTextBoldRight {
	font-weight: normal;
	font-family: Tahoma;
	color: black;
	font-size: 14px;
	letter-spacing: normal;
	text-align: right;
}
.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	//font-size: 18px;
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
    width: 200px;
    font-weight: normal;
}
</style>
	<script type="text/javascript" src="/littleangel/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/littleangel/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Character Certificate</title>
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
	response.sendRedirect("/littleangel/UserProcess/sessionTimeOut");
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
		
		 <table style="padding-left:150px;">
                        		
			<tr>
				<td ><img src="/littleangel/images/littleangel.jpg" width="117" height="140"/></td>
				<td  class="dataTextBoldCenter">
				<h1 style="margin-bottom:0px;">	SHATABDI PUBLIC SCHOOL</h1>
				<h4 style="margin-top:0px;margin-bottom:0px;">	A SENIOR SECONDARY SCHOOL</h4>
				<h4 style="margin-top:0px;margin-bottom:0px;">	AFFILIATED TO CBSE,DELHI,AFF NO.330113</h4>
				<h4 style="margin-top:0px;margin-bottom:0px;">	KATARY HILL ROAD,GAYA - 823003(BIHAR)</h4>
				
				<h6 style="margin-top:0px; margin-bottom:0px;">Phone No.0631-2226652,2220344&nbsp;&nbsp;&nbsp;
				Fax:031-2221386&nbsp;&nbsp;&nbsp;
				Mobile No. 8936846757</h6>
			    <h6 style="margin-top:0px;">	Email: shatabdipublic@gmail.com</h6>
				</td>
			</tr>
		
			</table>
			<table>
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					<u>CHARACTER CERTIFICATE</u>
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
					<h3 style="font-weight: normal;">This is to certify that Mr./Ms. &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:600px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					Son/Daughter of &nbsp;&nbsp;<span style="font-weight: bold;text-transform: capitalize;width:700px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.fathersname}" /></span>
					</h3></td></tr><tr>
					<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					has passed the AISSE/AISSCE<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;
					<c:set var="splt" value="${fn:split(studentdetailsbonafide.student.classstudying,'--')}"/>
					<c:out value="${splt[0]}" />
					</span>
					bearing Roll Number<span style="font-weight: bold;text-transform: capitalize;width:250px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</h3>
				</td>
			
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			His/Her date of Birth as per our record is<span style="font-weight: bold;text-transform: capitalize;width:520px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="dateofcr" type="text" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy"/>" >
			</span>
			</h3>
			</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			We wish him/her all the best for bright and prosperous future.
			</h3>
			</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			He/She bears<span style="font-weight: bold;text-transform: capitalize;width:520px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${character}" /></span>character
			</h3>
			</td>
			</tr>
			<tr>
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
		<td></td>
			<td align="left">
				Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>	
			<td align="center">Accountant</td>
			<td align="center">Principal</td>
		</tr>
		
		<!-- 	<tr>
              <td align="center"><a id="print" href="/littleangel/DocumentsProcess/printCharacterCertificate">Print</a></td>
            </tr>-->
		</TABLE>
	</form>
</body>
</html>
