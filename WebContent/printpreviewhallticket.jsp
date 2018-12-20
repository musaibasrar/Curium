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
	font-size: 20px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
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
        
        .subjectdetails{
        	border: 1px solid black;
    		text-align: left;
   		    padding: 8px;
        }
        
        .nosubjectdetails{
        	border: 0px;
    		text-align: left;
   		    padding: 8px;
        }
        
         .namedetails{
        	border: 0px solid #dddddd;
    		text-align: left;
   		    padding: 4px;
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
	
	<script type="text/javascript">
                       
		window.onload = function(){
		window.print();
		}
        </script>
        
       <title>Hall Ticket</title>
        
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
		<c:forEach items="${hallticketmap}" var="Parents">
		
            <div style="page-break-inside: avoid;border-style: solid;border-width: thin;">             	
		<table width="100%" style="page-break-inside: avoid;border-collapse: collapse;">
			<tr>
				<td></td>
				<td></td>
				<td align="center">
				<img src="images/bielogo.png" width="40" height="70"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				BOARD OF ISLAMIC EDUCATION KARNATAKA
				<br><br>
				<label class="addressLine">HALL TICKET<br>
				 </label>
				</td>
			</tr>
			
	</table>
	
<TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
			<table style=" border-collapse: collapse;width: 100%;">
							<tr>   
                                <td class="namedetails"><label>Admission No:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.key.student.admissionnumber}"/></label></td>
                                <td class="namedetails"><label>Language Opted.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.key.student.languageopted}"/></label></td>
                            </tr>
                            
                            <tr>
                            <td></td>
                            </tr>	
                            				
                            <tr style="border-color:#000000">
                                <td class="namedetails">
                                <label>Candidate Name :&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.key.student.name}"/></label>
                            	</td>
                                
                             </tr>
                             
                             <tr style="border-color:#000000">
                                <c:if test="${(Parents.key.student.guardiandetails != '')}">
                                <td class="namedetails"><label>Guardian Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.key.student.guardiandetails}"/></label></td>
                                </c:if>

							<c:if test="${(Parents.key.fathersname != '')}">
                                <td class="namedetails"><label>Father's Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.key.fathersname}"/></label></td>
							</c:if>
							
							<c:if test="${(Parents.key.mothersname != '')}">
                                <td class="namedetails"><label>Husband's Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${Parents.key.mothersname}"/></label></td>
							</c:if>	
                             </tr>
                             
                             <tr>
                            <td></td>
                            </tr>	
                             
                              <tr style="border-color:#000000">
                                <td class="namedetails">
                                <label>Center Code/Name :&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${centercodename}"/></label>
                            	</td>
                             </tr>
                             <tr>
                            <td></td>
                            </tr>	
                             
                             <tr style="border-color:#000000">
                                <td class="namedetails">
                                <label>Exam Code/Name :&nbsp;&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;"><c:out value="${examcodename}"/></label>
                            	</td>
                             </tr>
                            </table>
                            
                            <table style=" border-collapse: collapse;width: 100%;">
                            	<thead>
                            	<tr>
                            	<th class="subjectdetails">Date</th>
                            	<th class="subjectdetails">Subject</th>
                            	<th class="subjectdetails">Reference Books</th>
                            	<th class="subjectdetails">Time</th>
                            	<th class="subjectdetails">Examiner's Sign</th>
                            	</tr>
                            	</thead>
                            	<tbody>
                            	<c:forEach items="${Parents.value}" var="examschedulelist" varStatus = "status">
                             	<tr>
                                <td class="subjectdetails"><fmt:formatDate value="${examschedulelist.date}" pattern="dd-MM-yyyy"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.subject}"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.referencebooks}"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.starttime}"/>&nbsp;-&nbsp;<c:out value="${examschedulelist.endtime}"/></td>
                                <td class="subjectdetails"></td>
                                </tr>
                                 </c:forEach>
                                 <c:if test="${totalpapers < 2}">
						 		<tr>
	                                <td class="nosubjectdetails"></td>
                                	<td class="nosubjectdetails"></td>
                               	    <td class="nosubjectdetails"></td>
                                	<td class="nosubjectdetails"></td>
                                	<td class="nosubjectdetails"></td>
                                </tr>
                                <tr>
	                                <td class="nosubjectdetails"></td>
                                	<td class="nosubjectdetails"></td>
                               	    <td class="nosubjectdetails"></td>
                                	<td class="nosubjectdetails"></td>
                                	<td class="nosubjectdetails"></td>
                                </tr>
								</c:if>
                                 
                    </tbody>
                            </table>
                            
                            
                            <TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">

		
            <tr>
            <td align="left"></td>	
			<td align="left"></td>	
			<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="left"></td>
			</tr>
			<tr>
		<td></td>
		<td align="left"></td>	
			<td align="centre"></td>
			<td align="centre"><img alt="Chief Examiner Signature" src="images/cesignature.jpg" width="150" height="50"></td>
			</tr>
			
		<tr>
		<td></td>
		<td align="left">Candidate's Signature</td>	
			<td align="centre">Seal & Sign of Organiser</td>
			<td align="centre">Signature of Chief Examiner</td>
			</tr>
		</TABLE>
		</div>               
		<br> 
		<br> 
		<br>
		<br> 
                        </c:forEach>
			
	</form>
	
	
</body>
</html>
