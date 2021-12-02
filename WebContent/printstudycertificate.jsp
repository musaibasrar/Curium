<%-- 
    Document   : Study certificate print
    Created on : DEC 02 2021, 4:48 PM
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
              
             margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 1cm;
             margin-top: 1cm;
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
        <title>Study Certificate</title>
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
<body class="bodymargin">
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br>
		<!-- <table width="100%" style="border-collapse: collapse;">
			<tr>
				<td class="dataTextBoldCenter" style="width: 100%">
				
				Curium Education Society's </td>
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
            </TABLE> -->
	<div style="border-style: solid;border-width: thin;border-radius: 25px;">
	
		<table width="100%">
			<tr align="center">
					<td>
						<h3><u>STUDY CERTIFICATE</u></h3>
					</td>
			</tr>
		
		</table>
		
		<table width="100%">
			
			<tr>
			<td align="left" style="padding-left: 20px;">
			Admission No.: <c:out value="${studentdetailsbonafide.student.admissionnumber}" /><%-- <input
									name="dateofcr" type="text" class="textField" style="border: none;
border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" > --%></td>
			
			</tr>
			<tr>
			
				<td	style="padding-left: 10px;padding-right: 10px;">
					<h3 style="font-weight: normal;">This is to certify that Sri/Smt/Kum &nbsp;
						 <span style="font-weight: bold;text-align: center"> <c:out value="${studentdetailsbonafide.student.name}" /></span><br><br>
					S/o,D/o,W/o&nbsp;<span style="font-weight: bold;text-align: center"><c:out value="${studentdetailsbonafide.fathersname}" /></span>was a student of this institution who<br><br>
					had studied from
					<span style="font-weight: bold;width: 250px;text-align: center">
						<c:set var="classadmitted" value="${fn:split(studentdetailsbonafide.student.classadmittedin,'--')}"></c:set>
						<c:out value="${classadmitted[0]}" /></span> 
					to
					<span style="font-weight: bold;width: 250px;text-align: center">
						<c:set var="classstudying" value="${fn:split(studentdetailsbonafide.student.classstudying,'--')}"></c:set>
						<c:out value="${classstudying[0]}" />
					</span> <br><br>
					Her/His Date of Birth is <span style="font-weight: bold;text-align: center"><c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span>
					as entered in the admission <br><br>register, bearing admission no. <span style="font-weight: bold;text-align: center;"><c:out value="${studentdetailsbonafide.student.admissionnumber}" /></span>			
					Her/His conduct<br><br> during the period of study in this institution was good.
					</h3>
				</td>
			</tr>
		</table>
	
		
		<TABLE width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">
			
		<tr>
		<td><br><br></td>
		</tr>
		<tr>
		<td style="padding-left: 10px;" align="left">
			Date:<br><br>
			Place:
			<label style="padding-left: 530px;">Head Master</label>
			<br><br><br>
		</td>	
			</tr>
	    
		</TABLE>
		</div>
	</form>
	
	
</body>
</html>
