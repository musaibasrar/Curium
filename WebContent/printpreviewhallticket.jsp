<%-- 
    Document   : Print Hall Ticket
    Created on : Apr 04 2018, 04:32 PM
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

.hallTicket {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 16px;
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
             margin-bottom: 0cm;
             margin-top: 0cm;
        }

        
        .subjectdetails{
        	border: 1px solid #dddddd;
    		text-align: center;
   		    padding: 8px;
        }
        
         .namedetails{
        	border: 0px solid #dddddd;
    		text-align: center;
   		    padding: 2px;
   		    
        }
         .namedetailsheader{
        	border: 0px solid #dddddd;
    		text-align: left;
   		    padding: 2px;
   		    
        }
    </style>
	
        <title>Print Hall Ticket</title>
        
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
	<form method="post" class="bodymargin">
                        <c:forEach items="${studentList}" var="Parents">
                        <div align="center">
                        	<table>
			<tr>
				<td></td>
				<td>
				<img src="images/curiumlogin.jpg" width="180" height="60"/>
				</td>
				<td class="dataTextBoldCenter">
						Brainy Stars<br>
					<label class="addressLine">
						International Holistic Montessori (School)<br>
			Plot No.62, Sector No.2, Shiv Basav Nagar, Belgavi-590 010, Karnataka State.<br>Ph: 083 - 12477371
					</label>
				</td>
			</tr>
			<tr>
			
			</tr>

</table>
</div>

		    <TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
                
            </TABLE>

			<table width="100%" style="border-collapse: collapse;">
				<tr>
					<td class="hallTicket" style="width: 100%">Hall Ticket (${examname})
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr></tr>
			</table>

			<table style="border-collapse: collapse;width: 100%;float: left">
											
                            <tr style="border-color:#000000">
                                <td class="namedetailsheader"><label>Student Name:&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.student.name}"/></label></td>
                                <td class="namedetailsheader"><label>Class:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
						    ${splt} 
							</c:forEach>
                                </td>
                                
                             </tr>
                             
                             
                             <tr>   
                                <td class="namedetailsheader"><label>Admission No:&nbsp;&nbsp;&nbsp;</label><c:out value="${Parents.student.admissionnumber}"/></td>
                                <td class="namedetailsheader"><label>Date Of Issue:&nbsp;&nbsp;</label><input
									name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"/>" ></td>
                            </tr>
                            
                            </table>
                            
                            <table style=" border-collapse: collapse;width: 100%;">
                            	<thead>
                            	<tr>
                            	<th class="subjectdetails">Date</th>
                            	<th class="subjectdetails">Subject</th>
                            	<th class="subjectdetails">Time</th>
                            	<th class="subjectdetails">Examiner's Sign</th>
                            	</tr>
                            	
                            	<tbody>
                            	<c:forEach items="${examschedulelist}" var="examschedulelist">
                             	<tr>
                                <td class="subjectdetails"><fmt:formatDate value="${examschedulelist.date}" pattern="dd-MM-yyyy"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.subject}"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.starttime}"/>&nbsp;-&nbsp;<c:out value="${examschedulelist.endtime}"/></td>
                                <td class="subjectdetails"></td>
                                </tr>
                                 </c:forEach>
                       
                    </tbody>
                            	</thead>
                            	
                            
                            </table>
                            
                            
                            <TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">

			<tr>
			<td>
			<br><br><br></td>
			</tr>
		<tr>
		<td></td>
		<td align="left">Accountant</td>	
			<td align="centre">Class Teacher</td>
			<td align="centre">Head Master</td>
			</tr>
			
			<tr>
                        <td align="center"><br><br></td>
                    </tr>
             
             <tr>
            	<td>
            		
                   <button id="print" onclick="window.print();" 
                   this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide">Print</button>     
                </td>
             </tr>
                    
		</TABLE>
                                 
                        </c:forEach>
			
	</form>
	
	
</body>
</html>
