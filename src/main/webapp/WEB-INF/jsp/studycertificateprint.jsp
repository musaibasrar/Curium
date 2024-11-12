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
			<img border="0" style="vertical-align: text-bottom;height: 105px;width: 113px;" alt="logo" src="/sneha/images/sneha.png">
			</td>
				<td >
					<br>
					<h2 style="margin-bottom:0px;">${branchname}</h2>
					<h4 style="margin-top:0px;margin-bottom:0px;">${branchaddress}</h4>
					 <h4 style="margin-top:0px;margin-bottom:0px;">Ph: 0824 2244629Email:snehapublicschool@gmail.com</h4>
                     <h4 style="margin-top:0px;margin-bottom:0px;"> Website:www.snehapublicschool.com</h4>
					
					
				</td>
			</tr>
			<%-- ${branchcontact}<br> --%>
			</table>
			
		<table align="center" style="padding-left: 30px;padding-right: 20px;">
			<tr>
			<td class="dataTextBoldLeft">
			<br>
				<%-- Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" > --%></td>
			
			</tr>
			
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					TO WHOMSOEVER IT MAY CONCERN
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
					<h3 style="font-weight: normal;">&emsp;&emsp;&emsp;&emsp;This is to certify that  &nbsp;&nbsp;
					 <c:out value="${studentdetailsbonafide.student.name}" />
					S/o, D/o &nbsp;&nbsp;<c:out value="${studentdetailsbonafide.fathersname}" />	&nbsp;&nbsp;and	
					</h3>
				</td>
			
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" > <c:out value="${studentdetailsbonafide.mothersname}" />	&nbsp;&nbsp;			
					 is studying in our institution in 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Std  in this current year</h3>
				</td></tr>
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" > 
					 
										 <c:out value="${currentAcademicYear}" /></h3>
				</td></tr>
				
				
				
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				Admission No.&nbsp;&nbsp;${studentdetailsbonafide.student.admissionnumber}&nbsp;&nbsp; </h3>
				</td></tr>
				
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				Date of birth&nbsp;&nbsp;${studentdetailsbonafide.student.dateofbirth}&nbsp;&nbsp; </h3>
				</td></tr>
				
								<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				STS No.&nbsp;&nbsp;${studentdetailsbonafide.student.sts}&nbsp;&nbsp; </h3>
				</td></tr>
				<tr>
				
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				The above given information is true to my concern.
				</td></tr>
				<tr>
			
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				&emsp;&emsp;&emsp;&emsp;
				Thanking you</h3>
				</td></tr>
				
				
				<tr><td><br></td></tr>
				<tr><td><br></td></tr>
				<tr><td><br></td></tr>
				<tr><td><br></td></tr>
				
				<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Headmistress</h3>
				</td></tr>

											</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			
			<tr>
              <td align="center"><a id="print" href="/sneha/DocumentsProcess/printStudyCertificate">Print</a></td>
            </tr>
		</TABLE>
	</form>
</body>
</html>
