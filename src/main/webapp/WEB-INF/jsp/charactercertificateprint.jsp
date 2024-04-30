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
    font-weight: normal;
    text-align:center;
}
</style>
<style>
 .rightside{
        float:right;
        }
/* .save{
 height:15px;
 width:40px;
 } */      
</style>
	<script type="text/javascript" src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script>
        $(function() {

        	 $(".printdoc").button({
                 icons:{
                     primary: "ui-icon-print"
                 }
             }).click(function(){
            	 printdoc();
                 return false;

             });
        	 
    	});
        function printdoc(){
            	var form1 = document.getElementById("form1");
        		form1.action = "/shatabdi/DocumentsProcess/printCharacterCertificate";
        		form1.method = "POST";
        		form1.submit();
            }
        </script>
	<script type="text/javascript" src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Character Certificate</title>
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
	<form id="form1" method="post" class="bodymargin">
		<br>
		 <table align="center">
                        		
			<!-- <tr>
				<td ><img src="/shatabdi/images/shatabdi.jpg" width="117" height="140"/></td>
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
			</tr> -->
		
			</table><table>
			
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
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
					has passed the AISSE/AISSCE<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.student.classstudying}" /></span>
					bearing Roll Number<span style="font-weight: bold;text-transform: capitalize;width:250px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</h3>
				</td>
			
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			His/Her date of Birth as per our record is<span style="font-weight: bold;text-transform: capitalize;width:520px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span>
			</h3>
			</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			He/She bears<span style="font-weight: bold;text-transform: capitalize;width:520px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" name="characterstudent" id="characterstudent" style="width: 200px" /></span>character
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
			<br></td>
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
		</tr>
		
			<tr>
              <td align="center">
              <td><button class="printdoc">Print</button></td>
              <!-- <a id="print" href="/shatabdi/DocumentsProcess/printCharacterCertificate">Print</a> --></td>
            </tr>
		</TABLE>
	</form>
</body>
</html>