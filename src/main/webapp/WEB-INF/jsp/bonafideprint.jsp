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
    text-align: center;
}
</style>
	<script type="text/javascript" src="/alfalahschool/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/alfalahschool/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Bonafide Certificate</title>
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
	response.sendRedirect("/alfalahschool/UserProcess/sessionTimeOut");
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
			<img border="0" style="vertical-align: text-bottom;height: 100px;width: 100px;" alt="logo" src="/alfalahschool/images/alfalahschool.png">
			</td>
				<td >
					<h1 style="margin-bottom:0px;">Al-Falah Islamic School</h1>
					<h6 style="margin-bottom:0px;margin-top:0px;">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;(Inspiring the Generation)</h6>
					<h4 style="margin-top:0px;">Managed by : Al-Falah Educational Trust Ahmedabad</h4>
					<%-- ${branchname},${branchaddress},${branchcontact} --%>
				</td>
			</tr>
			<tr>
			<td colspan="2">Near BaagBaan Residency, Oppt. Reliance Petrol pump, Rakhial, Ahmedabaad - 23</td>
			</tr>
			<tr>
			<td colspan="2">Email: admin@alfalah.co.in&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Mob:8347475751</td>
			</tr>
			</table>
			<hr>
			<table align="center">
			<tr>
			<td> Sr. No. 2024-25/104 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
			<td> Date:&emsp; <input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></td>
			</tr>
			<tr>
			<td> School Dise: 24071200248 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
			<td> GR 860</td>
			</tr>
			</table>
		<table align="center">
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
					<h3 style="font-weight: normal;">&emsp;&emsp;&emsp;&emsp;&emsp;This is to certify that Mr./Ms. &nbsp;&nbsp;
					<span style="font-weight: bold;text-transform: capitalize;">&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >Son/Daughter of
					<span style="font-weight: bold;text-transform: capitalize;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.fathersname}" /></span>
					is/ was a bonafide student of 
					</span>
					
					</h3>
				</td>
			
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >class <span style="font-weight: bold;text-transform: capitalize;width:50px;">	<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>

								</span> of this 
					 School/College for academic year <span style="font-weight: bold;width: 80px;">${currentAcademicYear}</span> His date of birth as per 
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
					  School recorded in the General Register is 
					<span style="font-weight: bold;text-transform: capitalize;width: 120px;">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy"/></span>
					in words</h3>
					
				</td>
				

			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				<span style="font-weight: bold;text-transform: capitalize;width: 400px;">${dobinword}</span>
			    </h3>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
				Address: <c:out value="${studentdetailsbonafide.addresspermanent}" />
				</h3>
		</table>
		

		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td>
			 <img src="data:image;base64,<c:out value="${studentdetailsbonafide.student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
			<td>&nbsp;
			<br>
			&nbsp;
			<br>
			&nbsp;
			<br>
			
			&nbsp;
			<br>
			&nbsp;Al-Falah Islamic School
			<br>
			&nbsp;
			<br>
			&nbsp;
			<br>
			Authorised Signotry</td>
			</tr>
		
		
			
		</TABLE>
	</form>
</body>
</html>
