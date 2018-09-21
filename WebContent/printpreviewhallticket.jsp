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
	font-size: 28px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 18px;
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
        
        .subjectdetails{
        	border: 1px solid #dddddd;
    		text-align: left;
   		    padding: 8px;
        }
        
         .namedetails{
        	border: 0px solid #dddddd;
    		text-align: left;
   		    padding: 8px;
        }
         .namedetailscenter{
        	border: 0px solid #dddddd;
    		text-align: right;
   		    padding: 8px;
        }
        
        .datatable {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: left;
    padding: 8px;
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
		<br>
                        <c:forEach items="${studentList}" var="Parents">
                        
                        	<!-- <table width="100%" style="border-collapse: collapse;">
			<tr>
				<td class="dataTextBoldCenter" style="width: 100%">
				
				Divine M.A. English Higher Primary & High School </td>
			</tr>
			<tr>
			<td class="addressLine">Astana Road, Nai Kaman, Bidar. Ph.No- +91-8095248270</td>
			</tr>

			<tr>
			<td></td></tr>
			<tr></tr>
</table> -->
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="images/bielogo.png" width="100" height="150"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION <br>
				KARNATAKA<br><br>
				<label class="addressLine">HALL TICKET<br>
				 </label>
				</td>
			</tr>
			<tr>
			<td></td></tr>
			<tr></tr>
	</table>
	
<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>

			<%-- <table width="100%" style="border-collapse: collapse;">
				<tr>
					<td class="dataTextBoldCenter" style="width: 100%">Hall Ticket (${examname})
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr></tr>
			</table> --%>

			<table style=" border-collapse: collapse;width: 100%;">
							<tr>   
                                <td class="namedetails"><label>Admission No:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.student.admissionnumber}"/></label></td>
                                <td class="namedetails"><label>Language Opted.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.student.languageopted}"/></label></td>
                            </tr>
                            				
                            <tr style="border-color:#000000">
                                <td class="namedetails">
                                <label>Candidate Name :&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.student.name}"/></label>
                            	</td>
                                <td class="namedetails"><label>Center Code/Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${centercodename}"/></label></td>
                             </tr>
                             
                             <tr style="border-color:#000000">
                                <c:if test="${(Parents.student.guardiandetails != '')}">
                                <td class="namedetails"><label>Guardian Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.student.guardiandetails}"/></label></td>
                                </c:if>

							<c:if test="${(Parents.fathersname != '')}">
                                <td class="namedetails"><label>Father's Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.fathersname}"/></label></td>
							</c:if>
							
							<c:if test="${(Parents.mothersname != '')}">
                                <td class="namedetails"><label>Husband's Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.mothersname}"/></label></td>
							</c:if>	
                                <td class="namedetails"><label>Exam Code/Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${examcodename}"/></label></td>
                             </tr>
                             
                            <tr>
                            <td><br></td>
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
		<td align="left">Candidate's Signature</td>	
			<td align="centre">Seal & Sign of Organiser</td>
			<td align="centre">Signature of Chief Examiner</td>
			</tr>
			
			<tr>
                        <td align="center"><br><br><br><br><br></td>
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
