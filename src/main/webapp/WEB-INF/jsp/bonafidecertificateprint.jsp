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
	font-size: 25px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 20px;
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
	<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Bonafide Certificate</title>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
                        		
			<tr><td>
			
			<img  src="/abc/images/alirfan.jpg" alt="alirfan logo" width="120" height="140"></td>
				<td style="font-style:normal;text-align:center;" >
				<label class="addressLine">Al-Hira Educational & Welfare Society`s.</label><br>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">Al-Irfan School</label><br>
				<label class="addressLine">(Secondary and Senior Secondary Residential and day Boarding)</label><br>
				<label class="addressLine"> ${branchaddress}</label><br>
				</td>
				<td><img  src="/abc/images/cbse.png" alt="cbse logo" width="110" height="122"></td>
				</tr>
		
			</table><table align="center" >
			
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					<u>BONAFIDE CERTIFICATE</u>
					<br><br>
				</td>
			</tr>
			
			<tr>
			<td></td>
			<tr>
			<td><p style="font-size:20px;line-height:50px;margin-left:200px;margin-right:200px;text-align:justify;">			
			&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Master &nbsp;&nbsp;
			<b><c:out value="${studentdetailsbonafide.student.name}" /></b>
			Son/Daughter of &nbsp;&nbsp;<b><c:out value="${studentdetailsbonafide.fathersname}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			is a Bonafide student of this school is studying  in Class&nbsp;&nbsp;&nbsp;&nbsp;<b><c:out value="${studentdetailsbonafide.student.classstudying}" /></b>
			Academic Year <b>${currentAcademicYear}</b> as per school record his date of birth is <b><fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy" /></b>
			Place of birth&nbsp;&nbsp;<b> <c:out value="${studentdetailsbonafide.student.placeofbirth}" /> </b>&nbsp;&nbsp;Religion: <b><c:out value="${studentdetailsbonafide.student.religion}" /></b>
			Caste <b><c:out value="${studentdetailsbonafide.student.studentscaste}" /></b> Subcaste&nbsp;&nbsp;<b><c:out value="${studentdetailsbonafide.student.socialcategory}" /></b> and G.R. <b><c:out value="${studentdetailsbonafide.student.sts}" /></b>
			
			</p>
			</td>
			</tr>
		</table>
		

		<TABLE id="dataTable"  border="0" align="center"
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
			<td align="left"><label style="font-size:20px">
				Date:&nbsp;&nbsp;</label>
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;font-size:20px"
					size="30" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>	
			<td align="center"><label style="font-size:20px">Principal</label></td>
		</tr>
		
			
		</TABLE>
		<TABLE>
			<tr>
              <td align="center"><a id="print" href="/abc/DocumentsProcess/printBonafide">Print</a></td>
            </tr>
		</TABLE>
	</form>
</body>
</html>
