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
	<script type="text/javascript" src="/noblewisdom/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/noblewisdom/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
                 <title>Bonafide Certificate</title>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/noblewisdom/UserProcess/sessionTimeOut");
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
		<div style="border:2px solid black;">
		<table align="center">
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			
			<tr>
			<td>
			<img border="0" style="vertical-align: text-bottom;height: 91px;width: 100px;" alt="logo" src="/noblewisdom/images/noblewisdom.png">
			</td>
				<td >
					<h3 style="margin-bottom:0px;">NOBLE WISDOM ENGLISH MEDIUM SCHOOL<br>${branchaddress}
					<br> Contact No. 7020123332</h3>
					
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr>
			<td>
			<span style="border:1px solid black;width: 100px;height:100px;">
					</span>
			
			<%-- <img src="data:image;base64,<c:out value="${studentdetailsbonafide.student.studentpic}"/>" alt="Student's Photo" style="width: 100px;height: 100px;"> --%>
			</td>
				<td >
					<br>
					<h3 style="margin-bottom:0px;">BONAFIDE & CHARACTER CERTIFICATE</h3>
					<h3 style="margin-top:0px;">G R No.<span style="font-weight: bold;width: 80px;">${studentdetailsbonafide.student.sts}</span>&nbsp;&nbsp;Date <span style="font-weight: bold;width: 80px;"><input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></span> </h3>
					
				</td>
			</tr>
			</table>
			
		<table align="center">
			
			<tr>
			<td><br></td>
			
			</tr>
			<tr><td><br></td></tr>
			<tr><td><br></td></tr>
			<tr>
			
				<td class="dataTextBoldLeft">
					This is to certify that &nbsp;&nbsp;
					</td>
					</tr>
					<tr><td><br></td></tr>
					<tr>
					<td>
					 Mr./Ms.<span style="font-weight: bold;text-transform: capitalize;width: 370px;">&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${studentdetailsbonafide.student.name}" /></span>is/was a
				</td>
			</tr>
			<tr>
			<td><br></td>
			</tr>
			
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
					bonafide student of this high school studying in std &nbsp;&nbsp; 
					
							<c:set var="itempart" value="${fn:split(studentdetailsbonafide.student.classstudying, '--')}" />
					<span style="font-weight: bold;width: 70px;">
								&nbsp;&nbsp;&nbsp;&nbsp;${itempart[0]} 
					</span>Div <span style="font-weight: bold;width: 70px;">
								&nbsp;&nbsp;&nbsp;&nbsp;${itempart[1]} 
					</span>
					in the </td></tr>
					<tr>
			<td></td>
			</tr>
					<tr><td><br></td></tr>
					<tr>
					<td class="dataTextBoldLeft"  >
					year <span style="font-weight: bold;width: 80px;">${currentAcademicYear}</span>&nbsp;&nbsp;&nbsp;&nbsp;His/ her date of birth
					in the general register is <span style="font-weight: bold;width: 120px;">
								&nbsp;&nbsp;&nbsp;&nbsp;${studentdetailsbonafide.student.dateofbirth} 
					</span></td>
					</tr>
					
					<tr>
			<td><br></td>
			</tr>
					
					<tr>
					<td class="dataTextBoldLeft"  >
					in word <span style="font-weight: bold;width: 400px;">
								&nbsp;&nbsp;&nbsp;&nbsp;${dateinword} 
					</span>
					</td></tr>
					<tr><td><br></td></tr>
					<tr><td>
					and he/she belongs to the caste <span style="font-weight: bold;width: 100px;">
								&nbsp;&nbsp;&nbsp;&nbsp;${studentdetailsbonafide.student.caste} 
					</span>and his place of birth
					</td></tr>
					<tr><td><br></td></tr>
					<tr><td>
					 <span style="font-weight: bold;width: 120px;">
								&nbsp;&nbsp;&nbsp;&nbsp;${studentdetailsbonafide.student.placeofbirth} 
					</span> He/She 
					
					is/was regular in attendance. To the best of my knowledge  </td>
					</tr>
					<tr><td><br></td></tr>
					<tr>
					<td class="dataTextBoldLeft"  >
					He/She bears a good moral character</td>
					

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
		<td>&nbsp;</td>
			<td align="center">&emsp;&emsp;&emsp;Headmaster/Principal</td>
		</tr>
		<tr><td><br></td></tr>
		<tr><td><br></td></tr>
		<tr><td><br></td></tr>
		
		
			
		</TABLE>
		</div>
		<table>
		<tr>
			<td>
                            <button id="print" type="button" style="background-image: url(/noblewisdom/images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide"></button>     
                        </td>
<!--               <td align="center"><a id="print" href="/noblewisdom/DocumentsProcess/printBonafide">Print</a></td>
 -->            </tr>
		</table>
	</form>
</body>
</html>
