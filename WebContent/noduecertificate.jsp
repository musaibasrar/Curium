<%-- 
    Document   : noduecertificate
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
	border: 1px solid black;
  text-align: left;
  padding: 8px;
}

.dataTextBoldLeftDate {
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
        <title>No Due Certificate</title>
        
        <script type="text/javascript">
        function printNoDue() {
    		var form1 = document.getElementById("formletterhead");
    		form1.action = "Controller?process=DocumentsProcess&action=printNoDue";
    		form1.method = "POST";
    		form1.submit();

    	}
        </script>
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
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin" id="formletterhead">
		<br>
		<table>
			<tr>
			<td class="dataTextBoldLeftDate">
			<br><br>
				Date:&nbsp;&nbsp;
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>
			
			</tr>
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					<u>No Due</u>
					<br><br>
				</td>
			</tr>
			<tr>
			<td>
			
			<table style="border-collapse: collapse;">
			<tr>
			
				<td class="dataTextBoldLeft">
					Student Name: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					 <c:out value="${studentdetailsnodue.student.name}" />
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" >
					UIN: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					<c:out value="${studentdetailsnodue.student.studentexternalid}" />
				</td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft" >
					Registration Number: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					<c:out value="${studentdetailsnodue.student.admissionnumber}" />
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Father Name: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					<c:out value="${studentdetailsnodue.fathersname}" />
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Course: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					
								
								<c:forEach var="splt" items="${fn:split(studentdetailsnodue.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					College Fees: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Exam Fees: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Library Due: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					
				</td>
			</tr>
			<tr>
				<td class="dataTextBoldLeft">
					Laboratory Due: &nbsp;&nbsp;
				</td>
				<td class="dataTextBoldLeft">
					
				</td>
			</tr>
			</table>
			</td>
			
			</tr>
			<tr>
			<td></td>
			</tr>
			
			<tr>
			<td></td>
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
			<td align="left" style="width: 400px;">Accountant Sign</td>	
			<td align="left">Librarian Sign</td>
			<td align="left">Lab Assistant Sign</td>
		</tr>
		
			<tr>
              <td align="center">
              <input type="checkbox" id = "letterhead" name="letterhead"  value="true"/>With Letterhead
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <button onclick="printNoDue();">Print</button>
              </td>
            </tr>
		</TABLE>
	</form>
</body>
</html>
