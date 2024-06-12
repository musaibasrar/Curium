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
	font-size: 20px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 17px;
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
	<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script>
        $(function() {

    		
    		$(".printtcstudent").button().click(function() {
    			printtc();

    		});

    		
    					
    	});
        function printtc(){
            	var form1 = document.getElementById("form1");
        		form1.action = "/abc/DocumentsProcess/printCharacterCertificate";
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
	<form id="form1" method="post" class="bodymargin">
		<br>
		 <table align="center">
                        		
			<tr><td>
			
			<img  src="/abc/images/alirfan.jpg" alt="Brainy Stars" width="120" height="140">&emsp;&emsp;</td>
				<td style="font-style:normal;text-align:center;" >
				<label class="addressLine">Al-Hira Educational & Welfare Society`s.</label><br>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">Al-Irfan School</label><br>
				<label class="addressLine">(Secondary and Senior Secondary Residential and day Boarding)</label><br>
				<label class="addressLine"> ${branchaddress}</label><br>
				</td>
				<td>&emsp;&emsp;<img  src="/abc/images/cbse.png" alt="cbse logo" width="110" height="122"></td>
				</tr>
		
			</table><table align="center" >
			
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					<u>CHARACTER CERTIFICATE</u>
					<br><br>
				</td>
			</tr>
			<tr>
			<td><label class="addressLine">TO WHOM IT MAY CONCERN</label></td>
			
			</tr>
			<tr>
			<td></td>
			<tr>
			<td><p style="font-size:20px;line-height:20px;margin-left:250px;margin-right:250px;text-align:justify;">			
			&nbsp;&nbsp;&nbsp;&nbsp;This is to certify that Master &nbsp;&nbsp;
			<b><c:out value="${studentdetailsbonafide.student.name}" /></b>
			Son/Daughter of &nbsp;&nbsp;<b><c:out value="${studentdetailsbonafide.fathersname}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Class&nbsp;&nbsp;&nbsp;&nbsp;<b><c:out value="${studentdetailsbonafide.student.classstudying}" /></b>&nbsp;&nbsp;&nbsp;&nbsp;year
			of passing &nbsp;&nbsp;&nbsp;&nbsp;<b> <c:out value="${studentdetailsbonafide.student.promotedyear}" /></b>
			&nbsp;&nbsp;&nbsp;&nbsp; was a Bonafide student of
			this school. As per school record his Date of Birth is
			&nbsp;&nbsp;&nbsp;&nbsp;<b><fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy" /></b>
			and G.R. No. is &nbsp;&nbsp;&nbsp;&nbsp;<b><c:out value="${studentdetailsbonafide.student.sts}" /></b>.
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To the best of my knowledge, he bears a 
			good moral character. I wish all success and
			prosperity in his life.</p>
			</td>
			</tr>
		</table>
		
		<TABLE id="dataTable" width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;margin-left:250px;margin-right:250px;">

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
			<td align="left"><label class="addressLine">
				Date:&nbsp;&nbsp;</label>
				<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;font-size:20px"
					size="30" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" >	
			&emsp;&emsp;&emsp;<label class="addressLine">Principal</label></td>
		</tr>
		
			<tr>
              <td><button class="printtcstudent"  >Print</button></td>
              <!-- <a id="print" href="/abc/DocumentsProcess/printCharacterCertificate">Print</a> -->
            </tr>
		</TABLE>
	</form>
</body>
</html>
