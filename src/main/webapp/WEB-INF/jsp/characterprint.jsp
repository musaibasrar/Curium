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

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	letter-spacing: normal;
	text-align: center;
}

.certParagraph {
	font-family: Tahoma, Arial, sans-serif;
	font-size: 16px;
	line-height: 2.2;
	color: black;
	text-align: justify;
	margin: 0 auto;
	width: 85%;
}

.certValue {
	font-weight: bold;
	text-transform: uppercase;
	border-bottom: 1px dotted black;
	padding: 0 5px;
	display: inline-block;
}
</style>

<script type="text/javascript" src="/alirfan/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
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
<body style="text-align: center" class="bodymargin">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br><br><br><br><br><br>
		
		<table align="center" width="90%">
			<!-- Reference and Date Row -->
			<tr>
				<td align="left" style="font-family: Tahoma; font-size: 14px; font-weight: bold; width: 50%;">
					Ref.: <span style="font-weight: normal; border-bottom: 1px dotted black; min-width: 120px; display: inline-block;"></span>
				</td>
				<td align="right" style="font-family: Tahoma; font-size: 14px; font-weight: bold; width: 50%;">
					Date: <input name="dateofcr" type="text" style="border: none; border-bottom: 1px dotted black; font-family: Tahoma; font-size: 14px;" size="12" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="dataTextBoldCenter">
					<br>
					<h3 style="font-size: 20px; letter-spacing: 1px; margin-bottom: 5px;"><u>CHARACTER CERTIFICATE</u></h3>
					<h4 style="font-size: 16px; margin-top: 5px; margin-bottom: 30px;">TO WHOM IT MAY CONCERN</h4>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="certParagraph">
						This is to certify that Master/Miss 
						<span class="certValue" style="min-width: 250px;">
							<c:out value="${studentdetailsbonafide.student.name}" />
						</span> 
						S/o
						<span class="certValue" style="min-width: 250px;">
							<c:out value="${studentdetailsbonafide.fathersname}" />
						</span>, 
						Class 
						<span class="certValue" style="min-width: 60px;">
							 <c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
				                ${splt}&nbsp;
				            </c:forEach>
						</span> 
						Year of Passing 
						<span class="certValue" style="min-width: 90px;">
							<c:out value="${studentdetailsbonafide.student.promotedyear}" />
						</span>, 
						was Bonafide student of this School. As per school record his/her Date of Birth is 
						<span class="certValue" style="min-width: 110px;">
							<fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy" />
						</span> 
						and G.R. No. is 
						<span class="certValue" style="min-width: 80px;">
							<c:out value="${studentdetailsbonafide.student.sts}" />
						</span>.
					</div>
					<br>
					<div class="certParagraph">
						To the best of my knowledge, he/she bears a 
						<span class="certValue" style="min-width: 120px;">
							<c:out value="${character != null ? character : 'good moral'}" />
						</span> 
						character. I wish all success and prosperity in his life.
					</div>
				</td>
			</tr>
		</table>

		<br><br><br><br><br>

		<table id="dataTable" width="85%" align="center" border="0" style="page-break-after: always; border-collapse: collapse;">
			<tr>
				<td align="left" style="font-family: Tahoma; font-weight: bold; width: 33%;">
					<!-- Additional signatory or seal placeholder if needed -->
				</td>
				<td align="center" style="font-family: Tahoma; font-weight: bold; width: 33%;">
					<!-- Seal placeholder -->
				</td>
				<td align="right" style="font-family: Tahoma; font-weight: bold; font-size: 15px; width: 34%;">
					Principal
				</td>
			</tr>
		</table>
	</form>
</body>
</html>