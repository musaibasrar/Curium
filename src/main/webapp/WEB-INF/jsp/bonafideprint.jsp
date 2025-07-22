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
    width: 250px;
    font-weight: normal;
}
</style>


<!-- <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
            	margin-top: 0px;
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              size: auto;   /* auto is the current printer page size */
           	  margin: 0mm;  /* this affects the margin in the printer settings */ 
            
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style> -->
    
    <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
             margin-left:  0cm;
             margin-right: 0cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
             size: auto;
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style>
        <title>Bonafide Print</title>
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
	response.sendRedirect("/fathima/UserProcess/sessionTimeOut");
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
	<form method="post" class="bodymargin" style="font-family: 'Times New Roman', serif; font-size: 16px;">
	<div style="text-align: center; margin-bottom: 20px;">
		<table width="100%">
			<tr>
				<td width="15%" style="text-align: left; padding-left: 60px;">
					<img src="/fathima/images/fathima.png" alt="logo" style="height: 100px; width: 100px;">
				</td>
				<td style="text-align: center;">
					<h2 style="margin: 0;">${branchname}</h2>
					<h4 style="margin: 0;">${branchaddress}</h4>
					<h4 style="margin: 0;">Contact: ${branchcontact}</h4>
				</td>
				<td width="15%"></td>
			</tr>
		</table>
	</div>
<hr style="border: 1px solid #000; width: 90%; margin: auto; margin-bottom: 30px;">

	<div style="text-align: center; margin-top: 30px;">
		<h2><u>BONAFIDE CERTIFICATE</u></h2>
	</div>

	<div style="padding: 0 50px; line-height: 1.8; text-align: justify;">
		<p>
			This is to certify that Smt./Sri <strong><c:out value="${studentdetailsbonafide.student.name}" /></strong> 
			S/o, D/o <strong><c:out value="${studentdetailsbonafide.fathersname}" /></strong> is/was a student of this 
			School/College.
		</p>

		<p>
			He/She has studied/passed/finalized in the class 
			<strong>
				<c:forEach var="splt" items="${fn:split(studentdetailsbonafide.student.classstudying,'--')}">
					${splt}
				</c:forEach>
			</strong> during the academic year <strong>${currentAcademicYear}</strong>.
		</p>

		<p>
			As per the School/College records, his/her date of birth is 
			<strong><fmt:formatDate value="${studentdetailsbonafide.student.dateofbirth}" pattern="dd/MM/yyyy" /></strong>.
		</p>

		<p>
			He/She bears good moral character.
		</p>
	</div>

	<div style="margin-top: 60px;">
		<table width="100%" style="padding: 0 50px;">
			<tr>
				<td style="text-align: left;">Date: <fmt:formatDate value="${now}" pattern="dd/MM/yyyy" /></td>
				<td style="text-align: right; padding-right: 50px;">Principal</td>
			</tr>
		</table>
	</div>

	<div style="page-break-after: always;"></div>
</form>

	
	
</body>
</html>
