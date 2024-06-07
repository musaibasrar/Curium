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
   // width: 200px;
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
	<script type="text/javascript" src="/noblewisdom/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/noblewisdom/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script>
        $(function() {

    		
    		$(".printtcstudent").button().click(function() {
    			printtc();

    		});

    		
    					
    	});
        function printtc(){
            	var form1 = document.getElementById("form1");
        		form1.action = "/noblewisdom/DocumentsProcess/printCharacterCertificate";
        		form1.method = "POST";
        		form1.submit();
            }
        </script>
        <title>Character Certificate</title>
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
	<form id="form1" method="post" class="bodymargin">
		<br>
		 <table align="center">
                        		
			<tr>
				<td ><img src="/noblewisdom/images/noblewisdom.jpg" width="154" height="140"/></td>
				<td  class="dataTextBoldCenter">
				<h1 style="margin-bottom:0px;">	Al Falah High School (EM)</h1>
				<h4 style="margin-top:0px;margin-bottom:0px;">	Shanti Nagar, Lalapet, Secunderabad-500017</h4>
				<h6 style="margin-top:0px; margin-bottom:0px;">Phone No.8143802598&nbsp;&nbsp;&nbsp;</h6>
			    <h6 style="margin-top:0px;">	Email: alfalahhighschool@gmail.com</h6>
				</td>
			</tr>
		
			</table><table align="center">
			
			
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
					<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:600px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${studentdetailsbonafide.student.name}" /></span>
					</h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					Son/Daughter of &nbsp;&nbsp;<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:700px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.fathersname}" /></span>
					</h3></td></tr><tr>
					<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					has passed the AISSE/AISSCE<span style="font-weight: bold;text-transform: capitalize;border-bottom:dotted;width:200px;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.student.classstudying}" /></span>
					bearing STS<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:250px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.student.sts}" /></span>
					</h3>
				</td>
			
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			His/Her date of Birth as per our record is<span class="rightside" style="font-weight: bold;text-transform: capitalize;width:520px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span>
			</h3>
			</td>
			</tr>
			<tr>
			<td class="dataTextBoldLeft">
			<h3 style="font-weight: normal;" >
			He/She bears<span style="font-weight: bold;text-transform: capitalize;width:500px;border-bottom:dotted;">&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" name="characterstudent" id="characterstudent" style="width: 200px" /></span>character
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
		
			<tr>
              <td><button class="printtcstudent"  >Print</button></td>
              <!-- <a id="print" href="/noblewisdom/DocumentsProcess/printCharacterCertificate">Print</a> -->
            </tr>
		</TABLE>
	</form>
</body>
</html>
