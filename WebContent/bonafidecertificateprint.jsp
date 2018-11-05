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

<html moznomarginboxes >
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
	font-size: 12px;
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
	<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Bonafide Certificate</title>
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
<body style="text-align: center" class="bodymargin">
	<form method="post" class="bodymargin">
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td class="dataTextBoldCenter" style="width: 100%">
				
				Al-Ameen Education Society's </td>
			</tr>
			<tr>
			<td class="addressLine">Bidar- 585401</td>
			</tr>

			<tr>
			<td></td></tr>
			<tr></tr>
</table>

<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

		<table>
		<tr>
			
			<td colspan="2">
			
			<h3><u>BONAFIDE CERTIFICATE</u></h3>
			</td>
			
			</tr>
			<tr>
			<td></td>
			
			</tr>
			<tr>
			<td></td>
			<td></td>
			
			<td >
			Date:&nbsp;&nbsp;<input
									name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" ></td>
			
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;">This is to certify that Ms. &nbsp;&nbsp;
					<span style="font-weight: bold;"> <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
				<!-- <td>
				
				</td> -->
				
				<td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >
					
					Son of &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetailsbonafide.fathersname}" /></span></h3>
				</td>
				
			
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<%-- <td class="dataTextBoldLeft" style="width: 40%;">
					 &nbsp;&nbsp;
					<h3 style="font-weight: normal;" >
					
					Son of &nbsp;&nbsp;<span style="font-weight: bold;"><c:out value="${studentdetails.fathersname}" /></span></h3>
				</td> --%>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
					
					is presently studying in Grade &nbsp;&nbsp; <span style="font-weight: bold;"><c:out value="${studentdetailsbonafide.student.classstudying}     " /></span></h3>
				
				
				</td>
				
				<td class="dataTextBoldLeft">as residential student / day student in our school.</td>

			</tr>

			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		</table>
		<TABLE width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">
			
		<tr>
		<td><br></td>
		</tr>
		<tr>
		<td class="dataTextBoldLeft" >
					His/Her date of birth as per our school record is&nbsp;&nbsp;<span style="font-weight: bold;">
					<c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span></td>	
	    </tr>
		</TABLE>

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
		<td align="left">Clerk</td>	
			<td align="centre">School Seal</td>
			<td align="centre">Principal</td>
			</tr>
			
			<tr>
                        <td align="center"><a id="print" href="Controller?process=DocumentsProcess&action=printBonafide">Print</a></td>
                    </tr>
		</TABLE>
		
		<%-- <a id="print" href="Controller?process=StudentProcess&action=GenerateBonafide&id=<c:out value="${studentdetails.student.sid}" />">Print</a> --%>
	</form>
	
	
</body>
</html>
