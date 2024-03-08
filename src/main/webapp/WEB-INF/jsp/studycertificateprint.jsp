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
    text-align:center;
}
</style>
<style>
.rightside{
float:right;
}
</style>
	<script type="text/javascript" src="/alfarooq/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/alfarooq/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Study Certificate</title>
</head>
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
<body style="text-align: center" class="bodymargin">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br>
		<table align="center">
			
			
			<tr>
			<td>
			<img border="0" style="vertical-align: text-bottom;height: 100px;width: 100px;" alt="logo" src="/noblewisdom/images/noblewisdom.png">
			</td>
				<td >
					<br>
					<h2 style="margin-bottom:0px;">${branchname}</h2>
					<h3 style="margin-top:0px;">${branchaddress}<br>${branchcontact}</h3>
					
				</td>
			</tr>
			<tr><td></td><td>
					<h2><u>STUDY CERTIFICATE</u></h2></td></tr>
			</table>
			<table align="center">
			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Admission No. &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.admissionnumber}" /></span>
					</h3>
				</td>
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Mr./Ms. &nbsp;&nbsp;
					<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:540px;"> <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">Son/Daughter of &nbsp;&nbsp;
					<span  style="font-weight: bold;text-transform: capitalize;width:540px;"> <c:out value="${studentdetailsbonafide.fathersname}" /></span>
					&nbsp;&nbsp;Was a bonafide
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;"> student of this institute/college during the year from &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.yearofadmission}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:130px;"> <c:out value="${studentdetailsbonafide.student.promotedyear}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;
					studying</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">
					 from<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.classadmittedin}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px;"> <c:out value="${studentdetailsbonafide.student.classstudying}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">During the period his character is found to be Good/Satisfactory. 
					</h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;His Date of Birth is &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;"> <c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span>
					and Religion is<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:200px;"> <c:out value="${studentdetailsbonafide.student.religion}" /></span></h3>
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;"> 
					caste is <span style="font-weight: bold;text-transform: capitalize;"> <c:out value="${studentdetailsbonafide.student.caste}" /></span>
					as per his/her admission Register No. <span style="font-weight: bold;text-transform: capitalize;width:190px;"></span></h3>
				</td>
			</tr>
			
			<tr>
			<td class="dataTextBoldLeft">
			<br><br>
				Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">place &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;width:120px">&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<span style="font-weight: bold;text-transform: capitalize;width:400px;border-bottom:0px solid black;">&nbsp;&nbsp;&nbsp;&nbsp;</span>Headmaster/principal
					</h3>
				</td>
			</tr>
			
		</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			
			<tr>
              <td align="center"><a id="print" href="/noblewisdom/DocumentsProcess/printStudyCertificate">Print</a></td>
            </tr>
		</TABLE>
	</form>
</body>
</html>
