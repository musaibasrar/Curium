<%-- 
    Document   : new certificate Preview 
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
	text-align: center;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 30px;
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
        .card {
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
    width: 5.5cm;
    height: 8.5cm;
}

.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
    padding: 2px 16px;
}
    </style>


</style>
<style type="text/css">

</style>
	<script type="text/javascript" src="/roshan/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/roshan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title> Certificate</title>
</head>
<%-- <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/roshan/UserProcess/sessionTimeOut");
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
%> --%>
<body style="text-align: center" class="bodymargin" onload="window.print();">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
		<br>
		<div align="center" style="height:650px; width:700px; border:4px dotted red;">
		
		<table align="center">
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					Roshan Group of Institutions
					<br><br>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" class="dataTextBoldCenter">
					<br>
					<u>CERTIFICATE OF PARTICIPATION</u>
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
					<h3 style="font-weight: normal;">Presented to &nbsp;&nbsp;
					</h3>
				</td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: ;" >
					${name} 
					
					</h3>
				</td>
			
			</tr>
		<!-- 	<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					${place} 
					
					</h3>
				</td>
			
			</tr>
			<tr>
				
				<td class="dataTextBoldLeft">
					<h3 style="font-weight: normal;" >
					${mobile} 
					
					</h3>
				</td>
			
			</tr>-->
			
			<tr>
			<td></td>
			</tr>
			<tr>
			
				<td class="dataTextBoldLeft"  >
				
				<h3 style="font-weight: normal;" >
					For participating in Science, Arts & Craft Expo 2024
					</h3>
					
				</td>
				

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
		<tr>
		<td></td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ><br>&nbsp;&nbsp;&nbsp;&nbsp;Date</td>	
			<td align="center"><img src="/roshan/images/badge.png" width="126" height="140"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>	
			<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="/roshan/images/roshanhallticketsign.jpg" width="63" height="50"/>&nbsp;&nbsp;&nbsp;&nbsp;<br>
			Signature</td>
		</tr>
		
			<tr>
                                      <td>
                        </td>

            </tr>
		</TABLE>
		
		</div>
                              
	</form>
</body>
</html>