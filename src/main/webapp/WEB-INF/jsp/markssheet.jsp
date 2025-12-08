<%-- 
    Document   : Marks Sheet
    Created on : Nov 29 2021, 09:22 PM
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
	font-size: 14px;
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
            .fontsize { font-size: 10px ;
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
            .fontsize { font-size: 10px;
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
		function printWindow(){
			document.getElementById('printwindow').style.visibility='hidden';
			window.print();
		}
        </script>
        
	<!-- <script type="text/javascript">
		window.onload = function(){
		window.print();
		}
        </script> -->
        
        <style>
    /* Ensure tables don't exceed the page width */
    .marksheet-container {
        width: 100%;
        max-width: 21cm; /* Width of A4 page */
        margin: 0 auto; /* Center the table */
        font-family: Arial, sans-serif;
    }
    /* Styles for all tables */
    table { border-collapse: collapse; border-color: #4b6a84; }
    th, td { border: 1px solid black; padding: 2px 4px; text-align: center; }
    .header-bg { background-color: #b5ffe6; }
</style>
	<title> </title>
        
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
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
                        <c:forEach items="${markssheetlist}" var="Parents">
                        
              <div style="page-break-inside: avoid;border-style: solid;border-width: thin;">
				<table width="100%" align="center">
				
					<tr>
					<td>
					School Code-65109&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						CBSE Affiliation No.-330113</td>
					</tr>
					

						<tr>
						    <td colspan="2" style="text-align: center; padding: 10px 0;">
						        
						        <table style="width: 80%; margin: 0 auto; border-collapse: collapse; border: none;">
						            <tr>
						                <td style="width: 15%; text-align: right; padding-right: 15px; border: none; vertical-align: middle;">
						                    <img src="/shatabdi/images/shatabdi.png" width="58" height="70" style="vertical-align: middle;"/>
						                </td>
						
						                <td style="width: 70%; text-align: center; vertical-align: top; padding: 0; border: none;">
						                    
						                    <label style="font-size: 20px; font-weight: bold; text-transform: uppercase; display: block; margin-bottom: 5px;">
						                        ${branchname},Gaya
						                    </label>
						                    
						                    <label style="font-size: 12px; display: block;">A Senior Secondary School</label>
						                    <label style="font-size: 12px; display: block;">Affiliated to CBSE., Delhi, Affiliation No-330113</label>
						                    <label style="font-size: 12px; display: block;">${branchaddress}</label>
						                    <label style="font-size: 12px; display: block;">Contact: ${branchcontact}</label>
						                    
						                </td>
						
						                <td style="width: 15%; text-align: left; padding-left: 15px; border: none; vertical-align: middle;">
						                    <img src="/shatabdi/images/cbselogo.png" width="63" height="70" style="vertical-align: middle;"/>
						                </td>
						            </tr>
						        </table>
						        
						    </td>
						</tr>
					
					<tr>
						<td  style="font-family: bold;background-color: #b5ffe6;">
						<c:set var="dataSubParts" value="${fn:split(currentAcademicYear,'/')}" />
						<%-- Academic Year: ${dataSubParts[0]}-${dataSubParts[1]} --%>
						Academic Year: ${academicyear}<br>
						REPORT CARD						
						</td>
					</tr>
				</table>

				<table align="center" width="100%" style="border-collapse: collapse; border: 1px solid black; font-family: Arial, sans-serif; font-size: 14px;">
				    <tr>
				        <td style="width: 15%; text-align: left; padding: 4px; border-right: 1px solid black; font-weight: bold; vertical-align: top;">
				            <div style="margin-bottom: 3px;">Scholar No.</div>
				            <div style="margin-bottom: 3px;">Name</div>
				            <div style="margin-bottom: 3px;">Father Name</div>
				            <div style="margin-bottom: 3px;">Mother Name</div>
				        </td>
				        
				        <td style="width: 25%; text-align: left; padding: 4px; border-right: 1px solid black; vertical-align: top;">
				            <div style="margin-bottom: 3px;">${Parents.parents.student.admissionnumber}</div>
				            <div style="margin-bottom: 3px; text-transform: capitalize;">${Parents.parents.student.name}</div>
				            <div style="margin-bottom: 3px; text-transform: capitalize;">${Parents.parents.fathersname}</div>
				            <div style="margin-bottom: 3px; text-transform: capitalize;">${Parents.parents.mothersname}</div>
				        </td>
				        
				        <td style="width: 15%; text-align: left; padding: 4px 10px 4px 10px; border-right: 1px solid black; font-weight: bold; vertical-align: top;">
				            <div style="margin-bottom: 3px;">Roll No.</div>
				            <div style="margin-bottom: 3px;">Class & Sec</div>
				            <div style="margin-bottom: 3px;">DOB</div>
				            <div style="margin-bottom: 3px;">Attendance:</div> 
				        </td>
				        
				        <td style="width: 35%; text-align: left; padding: 4px; border-right: 1px solid black; vertical-align: top;">
				            <div style="margin-bottom: 3px;">${Parents.parents.student.sts}</div>
				            <div style="margin-bottom: 3px;">${Parents.parents.student.classstudying}</div>
				            <div style="margin-bottom: 3px;"><fmt:formatDate type="date" value="${Parents.parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></div>
				            
				            <div style="margin-bottom: 3px; font-size: 12px; line-height: 1.2;">
				                Total Days: ${Parents.totalDays} | Present: ${Parents.totalPresent} | Absent: ${Parents.totalAbsent}
				            </div>
				        </td>
				        
				        <td style="width: 10%; text-align: center; padding: 4px;">
				            <img src="data:image;base64,<c:out value="${Parents.parents.student.studentpic}"/>" alt="Student's Photo" width="80" height="80" style="display: block; margin: 0 auto; border: 1px solid #ccc;"/>
				        </td>
				    </tr>
				</table>
								<c:choose>
									<c:when test="${examterm == 'term1'}">
									
									<table width="100%">
								        <thead>
								            <tr>
								                <th width="15%" class="header-bg" style="font-size: 14px;">Scholastic Areas</th>
								                <th width="85%" class="header-bg" style="font-size: 16px;">Term 1</th>
								            </tr>
								        </thead>
								    </table>
								    
								    <table width="100%" style="border-collapse: collapse; border: 1px solid black; font-family: Arial, sans-serif;">
    
									    <thead>
									        <tr style="background-color: #b5ffe6;">
									            
									            <th width="15%" style="font-size: 12px; border: 1px solid black; padding: 4px; vertical-align: middle;">Subject&nbsp;</th>
									            
									            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="5" step="1">
									                <th width="<c:out value="${85 / 6}"/>%" style="font-size: 12px; border: 1px solid black; padding: 4px; vertical-align: middle;">
									                    <c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
									                    <c:out value="${examParts[1]}"/>
									                </th>
									            </c:forEach>
									            
									        </tr>
									    </thead>
									    
									    <tbody>
									        <c:forEach items="${Parents.exammarks[0].subMarks}" var="subjectEntry" varStatus="loopStatus">
									            <tr <c:if test="${loopStatus.index % 2 == 0}">style="background-color: #f0f0f0;"</c:if>>
									                
									                <td style="text-align: left; font-size: 12px; border: 1px solid black; padding: 4px; background-color: #b5ffe6;">
									                    ${subjectEntry.key}
									                </td>
									                
									                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="5" step="1">
									                    <td style="text-align: center; font-size: 12px; border: 1px solid black; padding: 4px;">
									                        <c:set var="marksValue" value="${exammarks.subMarks[subjectEntry.key]}" />
									                        <c:set var="dateParts" value="${fn:split(marksValue,'_')}" />
									                        <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
									                        ${dataSubParts[0]}
									                    </td>
									                </c:forEach>
									                
									            </tr>
									        </c:forEach>
									    </tbody>
									</table>
								    <%-- <table width="15%" style="float: left; border-collapse: collapse; border: 1px solid black;">
									    <thead>
									        <tr>
									            <th id="subject-header" style="font-size: 12px; background-color: #b5ffe6; border: 1px solid black; padding: 4px; height: 36px; box-sizing: border-box; vertical-align: middle;">Subject&nbsp;</th>
									            </tr>
									    </thead>
									    <tbody>
									        <tr>
									            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="0" step="1">
									                <td>
									                    <table width="100%" style="border-collapse: collapse;">
									                        <c:forEach items="${exammarks.subMarks}" var="submarks" >
									                            <tr style="background-color: #b5ffe6;">
									                                <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
									                                <td style="text-align: left; font-size: 12px; border: 1px solid black; padding: 4px;">${submarks.key}</td>
									                            </tr>
									                        </c:forEach>
									                    </table>
									                </td>
									            </c:forEach>
									        </tr>
									    </tbody>
									</table>
									 
									<table width="85%" style="float: left; border-collapse: collapse; border: 1px solid black;">
									    <thead>
									        <tr>
									            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="5" step="1">
									                <th style="font-size: 12px; width: <c:out value="${100 / 6}"/>%; background-color: #b5ffe6; border: 1px solid black; padding: 4px; vertical-align: middle;">
									                    <c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
									                    <c:out value="${examParts[1]}"/>
									                </th>
									            </c:forEach>
									        </tr>
									    </thead>
									    <tbody>
									        <tr>
									            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="5" step="1">
									                <td>
									                    <table width="100%" style="border-collapse: collapse;">
									                        <c:forEach items="${exammarks.subMarks}" var="submarks" >
									                            <tr>
									                                <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
									                                <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
									                                <td style="text-align: center; font-size: 12px; border: 1px solid black; padding: 4px;">${dataSubParts[0]}</td>
									                            </tr>
									                        </c:forEach>
									                    </table>
									                </td>
									            </c:forEach>
									        </tr>
									    </tbody>
									</table> --%>

    
										<%-- 		<table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								    <thead>
								        <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								            <th style="border: 1px solid black;background-color: #b5ffe6;"><c:out value="Scholastic Areas"/></th>
								        </tr>
								    </thead>
								</table>
										<table width="85%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <th style="border: 1px solid black;font-size: 18px;background-color: #b5ffe6;">Term 1</th>
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								</table> --%>
					
					<%-- <table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								    <thead>
								        <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								            <th style="border: 1px solid black;font-size: 14px;background-color: #b5ffe6;"><c:out value="Subject"/>&nbsp;</th>
								        </tr>
								    </thead>
								    
								    <tbody>
								        <tr>
								            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="0" step="1">
								                <td>
								                    <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                        <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                            <tr style="border: 1px solid black;font-size: 18px;background-color: #b5ffe6;">
								                                <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                <td style="border: 1px solid black;text-align: left;">${submarks.key}</td>
								                            </tr>
								                        </c:forEach>
								                        <!-- <tr style="border-top: 1px solid black;font-size: 18px;">
								                            <td style="border: 1px solid black;text-align: left;">Total</td>
								                        </tr> -->
								                    </table>
								                </td>
								            </c:forEach>
								        </tr>
								    </tbody>
								</table>
								
								<c:set var = "beginloop" value = "0"/>
								<c:set var = "endloop" value = "2"/>
								<c:forEach begin="0" end="${endloop}" step="1">
								    <table width="85%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #b5ffe6">
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end= "5" step="1">
								                    <th style="border: 1px solid black;font-size: 12px;">
								                    <c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
								                    <c:out value="${examParts[1]}"/></th>
								                </c:forEach>
								                <!-- <th style="border: 1px solid black;font-size: 9px;">Obtained<br>Marks<br>(100/40)</th> -->
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								        
								        <tbody>
								            <tr>
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end= "5" step="1">
								                    <td>
								                        <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                            <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                                <tr style="border: 1px solid black;font-size: 18px;">
								                                    <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                    <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
								                                    <td style="border: 1px solid black;text-align: left;">${dataSubParts[0]}</td>
								                                </tr>
								                            </c:forEach>
								                            <tr style="border-top: 1px solid black;font-size: 18px;">
								                                <td style="border: 1px solid black;text-align: left;">${exammarks.totalMarksObtained}</td>
								                            </tr>
								                        </table>
								                    </td>
								                </c:forEach>
								            </tr>
								        </tbody>
								    </table> --%>
									</c:when>
									<c:when test="${examterm == 'term2'}">
										
									
									<table width="100%">
								        <thead>
								            <tr>
								                <th width="15%" class="header-bg" style="font-size: 14px;">Scholastic Areas</th>
								                <th width="85%" class="header-bg" style="font-size: 16px;">Term 2</th>
								            </tr>
								        </thead>
								    </table>
								    
								    
								
					
							<table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								    <thead>
								        <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								            <th style="border: 1px solid black;font-size: 14px;background-color: #b5ffe6;"><c:out value="Subject"/><br/>&nbsp;</th>
								        </tr>
								    </thead>
								    
								    <tbody>
								        <tr>
								            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="0" step="1">
								                <td>
								                    <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                        <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                            <tr style="border: 1px solid black;font-size: 18px;">
								                                <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                <td style="border: 1px solid black;text-align: left;">${submarks.key}</td>
								                            </tr>
								                        </c:forEach>
								                        <!-- <tr style="border-top: 1px solid black;font-size: 18px;">
								                            <td style="border: 1px solid black;text-align: left;">Total</td>
								                        </tr> -->
								                    </table>
								                </td>
								            </c:forEach>
								        </tr>
								    </tbody>
								</table>
								
								<%-- <c:set var = "beginloop" value = "0"/>
								<c:set var = "endloop" value = "2"/>
								<c:forEach begin="0" end="${endloop}" step="1"> --%>
								    <table width="85%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end= "5" step="1">
								                    <th style="border: 1px solid black;font-size: 12px;">
								                    <c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
								                    <c:out value="${examParts[1]}"/></th>
								                </c:forEach>
								                <!-- <th style="border: 1px solid black;font-size: 9px;">Obtained<br>Marks<br>(100/40)</th> -->
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								        
								        <tbody>
								            <tr>
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end= "5" step="1">
								                    <td>
								                        <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                            <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                                <tr style="border: 1px solid black;font-size: 18px;">
								                                    <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                    <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
								                                    <td style="border: 1px solid black;text-align: left;">${dataSubParts[0]}</td>
								                                </tr>
								                            </c:forEach>
								                            <%-- <tr style="border-top: 1px solid black;font-size: 18px;">
								                                <td style="border: 1px solid black;text-align: left;">${exammarks.totalMarksObtained}</td>
								                            </tr> --%>
								                        </table>
								                    </td>
								                </c:forEach>
								            </tr>
								        </tbody>
								    </table>
									</c:when>
									<c:otherwise >
										<table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								    <thead>
								        <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								            <th style="border: 1px solid black;"><c:out value="Scholastic Areas"/></th>
								        </tr>
								    </thead>
								</table>
										<table width="42%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <th style="border: 1px solid black;font-size: 18px;">Term 1</th>
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								</table>
								<table width="42%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <th style="border: 1px solid black;font-size: 18px;">Term 2</th>
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								</table>
								
					
					<table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								    <thead>
								        <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								            <th style="border: 1px solid black;font-size: 14px;background-color: #b5ffe6;"><c:out value="Subject"/><br/>&nbsp;</th>
								        </tr>
								    </thead>
								    
								    <tbody>
								        <tr>
								            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="0" step="1">
								                <td>
								                    <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                        <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                            <tr style="border: 1px solid black;font-size: 18px;">
								                                <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                <td style="border: 1px solid black;text-align: left;">${submarks.key}</td>
								                            </tr>
								                        </c:forEach>
								                        <!-- <tr style="border-top: 1px solid black;font-size: 18px;">
								                            <td style="border: 1px solid black;text-align: left;">Total</td>
								                        </tr> -->
								                    </table>
								                </td>
								            </c:forEach>
								        </tr>
								    </tbody>
								</table>
								
								<%-- <c:set var = "beginloop" value = "0"/>
								<c:set var = "endloop" value = "2"/>
								<c:forEach begin="0" end="${endloop}" step="1"> --%>
								    <table width="42%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end= "5" step="1">
								                    <th style="border: 1px solid black;font-size: 12px;">
								                    <c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
								                    <c:out value="${examParts[1]}"/></th>
								                </c:forEach>
								                <!-- <th style="border: 1px solid black;font-size: 9px;">Obtained<br>Marks<br>(100/40)</th> -->
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								        
								        <tbody>
								            <tr>
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end= "5" step="1">
								                    <td>
								                        <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                            <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                                <tr style="border: 1px solid black;font-size: 18px;">
								                                    <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                    <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
								                                    <td style="border: 1px solid black;text-align: left;">${dataSubParts[0]}</td>
								                                </tr>
								                            </c:forEach>
								                            <%-- <tr style="border-top: 1px solid black;font-size: 18px;">
								                                <td style="border: 1px solid black;text-align: left;">${exammarks.totalMarksObtained}</td>
								                            </tr> --%>
								                        </table>
								                    </td>
								                </c:forEach>
								            </tr>
								        </tbody>
								    </table>
								    
								    <table width="42%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="6" end= "11" step="1">
								                    <th style="border: 1px solid black;font-size: 12px;"><c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
								                    <c:out value="${examParts[1]}"/></th>
								                </c:forEach>
								               <!--  <th style="border: 1px solid black;font-size: 9px;">Obtained<br>Marks<br>(100/60)</th> -->
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								        
								        <tbody>
								            <tr>
								                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="6" end= "11" step="1">
								                    <td>
								                        <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
								                            <c:forEach items="${exammarks.subMarks}" var="submarks" >
								                                <tr style="border: 1px solid black;font-size: 18px;">
								                                    <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
								                                    <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
								                                    <td style="border: 1px solid black;text-align: left;">${dataSubParts[0]}</td>
								                                </tr>
								                            </c:forEach>
								                            <%-- <tr style="border-top: 1px solid black;font-size: 18px;">
								                                <td style="border: 1px solid black;text-align: left;">${exammarks.totalMarksObtained}</td>
								                            </tr> --%>
								                        </table>
								                    </td>
								                </c:forEach>
								            </tr>
								        </tbody>
								    </table>
								    
								    
					<table width="100%" align="center" style="border:1px solid black;margin-top: 90px;">
							
						<tr>	
							<td style="background-color:#A9A9A9;text-align:left"></td>
						</tr>		
					</table>			    
								
						<!-- Over All -->
								    					    
								   <div style="display: flex; justify-content: center;">
    <!-- New Table for Grand Total, Term 1 + Term 2, and Final Grade -->
    <table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								    <thead>
								        <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								            <th style="border: 1px solid black;"><c:out value=""/></th>
								        </tr>
								    </thead>
								</table>
								<table width="27%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <th style="border: 1px solid black;font-size: 18px;">
								                Grand Total<br>
								                Term 1 + Term 2</th>
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								</table>
								<table width="15%" border="0" style="border-color: #4b6a84;float: left;">
								        <thead>
								            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
								                <th style="border: 1px solid black;font-size: 18px;">Final Grade</th>
								                <!-- <th style="border: 1px solid black;">Grade</th> -->
								            </tr>
								        </thead>
								</table>
									</c:otherwise>
								</c:choose>
								
</div>

<c:if test="${examterm == 'term1'}">
				<TABLE id="dataTable" width="100%" border="0"
					style="page-break-inside: avoid; border-collapse: collapse;">

					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					<tr>
						<td style="border: 1px solid black;background-color: #b5ffe6;">Co-Scholastic
							Areas:Term-1[on a 3-point(A-C)grading scale]</td>
							<td 
							style="border: 1px solid black; text-align: center;background-color: #b5ffe6;">Grade</td>
					</tr>
					</tr>
					<tr>
						<td style="border: 1px solid black">Work-education(or
							pre-vocational)</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Art Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Health and
							Physical Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Discipline</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					</table>
					
					<TABLE id="dataTable" width="100%" border="0"
					style="page-break-inside: avoid; border-collapse: collapse;">

				<tr>
					<td style="text-align: left;">
					Place:&nbsp;<br><br>
					Date:&nbsp;</td>	
					<td align="center">Signature<br><br>Class Teacher</td>
					<td align="center">Signature<br><br>Principal</td>
				</tr>
                    
		</TABLE>
					
				
				</c:if>
				
				
				<c:if test="${examterm == 'term2'}">
				<TABLE id="dataTable" width="100%" border="0"
					style="page-break-inside: avoid; border-collapse: collapse;">

					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Co-Scholastic
							Areas:Term-2[on a 3-point(A-C)grading scale]</td>
							<td 
							style="border: 1px solid black; text-align: center;">Grade</td>
					</tr>
					</tr>
					<tr>
						<td style="border: 1px solid black">Work-education(or
							pre-vocational)</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Art Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Health and
							Physical Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="border: 1px solid black">Discipline</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					</table>
					
					<TABLE id="dataTable" width="100%" border="0"
					style="page-break-inside: avoid; border-collapse: collapse;">

				<tr>
					<td style="text-align: left;">
					Place:&nbsp;<br><br>
					Date:&nbsp;</td>	
					<td align="center">Signature<br><br>Class Teacher</td>
					<td align="center">Signature<br><br>Principal</td>
				</tr>
                    
		</TABLE>
					
				
				</c:if>

<c:if test="${examterm == 'term1term2'}">
<div style="display: flex; justify-content: center;">
    <table width="15%" border="0" style="border-color: #4b6a84;">
        <thead>
            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
                <th style="border: 1px solid black;font-size: 14px;background-color: #b5ffe6;"><c:out value="Subject"/>&nbsp;</th>
            </tr>
        </thead>
        
        <tbody>
            <tr>
                <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="0" step="1">
                    <td>
                        <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
                            <c:forEach items="${exammarks.subMarks}" var="submarks" >
                                <tr style="border: 1px solid black;font-size: 18px;">
                                    <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
                                    <td style="border: 1px solid black;text-align: left;">${submarks.key}</td>
                                </tr>
                            </c:forEach>
                            <!-- <tr style="border-top: 1px solid black;font-size: 18px;">
                                <td style="border: 1px solid black;text-align: left;">Total</td>
                            </tr> -->
                        </table>
                    </td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
    
    <table width="42%" border="0" style="border-color: #4b6a84;">
        <thead>
            <tr style="border: 1px solid black;text-align: center;background-color: #A9A9A9">
                <c:forEach items="${Parents.finaltermmarks}" var="exammarks" begin="0" end= "4" step="1">
                    <th style="border: 1px solid black;font-size: 12px;"><c:set var="examParts" value="${fn:split(exammarks.examName,'/')}" />
                    <c:out value="${examParts[1]}"/></th>
                </c:forEach>
               <!--  <th style="border: 1px solid black;font-size: 9px;">Obtained<br>Marks<br>(100/60)</th> -->
                <!-- <th style="border: 1px solid black;">Grade</th> -->
            </tr>
        </thead>
        
        <tbody>
            <tr>
                <c:forEach items="${Parents.finaltermmarks}" var="exammarks" begin="0" end= "4" step="1">
                    <td>
                        <table style="border-collapse: collapse;width: 100%;border: 1px solid black;">
                            <c:forEach items="${exammarks.subMarks}" var="submarks" >
                                <tr style="border: 1px solid black;font-size: 18px;">
                                    <c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
                                    <c:set var="dataSubParts" value="${fn:split(dateParts[0],'/')}" />
                                    <td style="border: 1px solid black;text-align: left;">${dataSubParts[0]}</td>
                                    <c:if test="${not empty dateParts[1]}">
    											<td style="border: 1px solid black;text-align: left;">${dateParts[1]}</td>
									</c:if>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
</div>
				<TABLE id="dataTable" width="100%" border="0"
					style="page-break-inside: avoid; border-collapse: collapse;">

					<!-- <tr>
							<td><br></td>
						</tr>
				<tr>
					<td></td>
					<td align="left">Class Teacher</td>	
					<td align="center">Principal</td>
					<td align="center">Parent</td>
				</tr>
                    
		</TABLE>
		</div>
		<br><br><br> -->

					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					<tr>
						<td colspan="6" style="border: 1px solid black">Co-Scholastic
							Areas:Term-1[on a 3-point(A-C)grading scale]</td>
						<td colspan="7" style="border: 1px solid black">Co-Scholastic
							Areas:Term-2[on a 3-point(A-C)grading scale]</td>
					</tr>
					<tr>
						<td colspan="6"
							style="border: 1px solid black; text-align: center;">Grade</td>
						<td colspan="7"
							style="border: 1px solid black; text-align: center;">Grade</td>
					</tr>
					<tr>
						<td colspan="5" style="border: 1px solid black">Work-education(or
							pre-vocational)</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
						<td colspan="6" style="border: 1px solid black">Work-education(or
							pre-vocational)</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="5" style="border: 1px solid black">Art Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
						<td colspan="6" style="border: 1px solid black">Art Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="5" style="border: 1px solid black">Health and
							Physical Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
						<td colspan="6" style="border: 1px solid black">Health and
							Physical Education</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					<tr>
						<td colspan="6"
							style="border: 1px solid black; text-align: center;">Grade</td>
						<td colspan="7"
							style="border: 1px solid black; text-align: center;">Grade</td>
					</tr>
					<tr>
						<td colspan="5" style="border: 1px solid black">Discipline
							Term-1 [on a 3-point(A-C)grading scale]</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
						<td colspan="6" style="border: 1px solid black">Discipline
							Term-2[on a 3-point(A-C)grading scale]</td>
						<td colspan="1" style="border: 1px solid black">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					</table>
					<table>
					<tr>
						<td style="border-left: solid white;text-align:left;">Class
							Teacher Remarks:&nbsp;${Parents.overallresult}</td>
						<td colspan="3" style="border-left: solid white;text-align:left;font-weight: bold;"></td>
						<td colspan="10" style="border-right: solid white;text-align:left;">
						</td>
					</tr>
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					<tr>
						<td colspan="0" style="border-left: solid white;text-align:left;">Result:&nbsp;Passed
							& Promoted To Next Class</td>
						<td colspan="4" style="border-left: solid white;text-align:left;font-weight: bold;"></td>
						<td colspan="10" style="border-right: solid white;text-align:left;"></td>
					</tr>
					
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					<tr>
						<td colspan="13"
							style="border-left: solid white; border-right: solid white"><br></td>
					</tr>
					</table>
					<TABLE id="dataTable" width="100%" border="0"
					style="page-break-inside: avoid; border-collapse: collapse;">

				<tr>
					<td style="text-align: left;">
					Place:&nbsp;<br><br>
					Date:&nbsp;</td>	
					<td align="center">Signature<br><br>Class Teacher</td>
					<td align="center">Signature<br><br>Principal</td>
				</tr>
                    
		</TABLE>
					<table width="100%" border="1" style="border-color: black;border-collapse: collapse;">
					<tr align="center">
						<td	style="text-align: center;">Instructions</td>
					</tr>
					</table>
					
					<table width="100%" border="1" style="border-color: #4b6a84;float: left;border-collapse: collapse;">
					<tr>
						<td colspan="13" style="text-align: center;">Grading Scale
							for Scholastic Areas: <br>Grades are awarded on a 8-point Grading
							Scale</td>
					</tr>
				</table>
					<table width="50%" border="1" style="border-color: #4b6a84;float: left; border-collapse: collapse;">
					<tr>
						<td style="border: 1px solid black">MARKS RANGE</td>
						<td style="border: 1px solid black">Grade</td>
					</tr>
						<tr>
						<td >91-100</td>
						<td >A1</td>
						</tr>
						<tr>
						<td >81-90</td>
						<td >A2</td>
						</tr>
						<tr>
						<td >71-80</td>
						<td >B1</td>
						</tr>
						<tr>
						<td >61-70</td>
						<td >B2</td>
						</tr>
						<tr>
				</table>
				<table width="50%" border="1" style="border-color: #4b6a84;float: left; border-collapse: collapse;">
						<tr>
							<td style="border: 1px solid black">MARKS RANGE</td>
							<td style="border: 1px solid black">Grade</td>
						</tr>
						<tr>
						<td >51-60</td>
						<td >C1</td>
						</tr>
						<tr>
						<td >41-50</td>
						<td >C2</td>
						</tr>
						<tr>
						<td >33-40</td>
						<td >D</td>
						</tr>
						<tr>
						<td >below 32</td>
						<td >E</td>
						</tr>

				</table>
				<table width="100%" border="1" style="border-color: #4b6a84;float: left;border-collapse: collapse;">
					<tr>
						
						<td colspan="13" style="text-align: center;">
						<br>Grading Scale
							for Co-Scholastic Areas: <br>Grades are awarded on a 3-point Grading
							Scale</td>
					</tr>
				</table>
					
				
				<table width="100%" border="1" style="border-color: #4b6a84;float: left; border-collapse: collapse;">
					<tr>
						<td>A</td>
						<td>OUTSTANDING</td>
						<td>B</td>
						<td>VERY GOOD</td>
						<td>C</td>
						<td>FAIR</td>
						</tr>
				</table>
				</c:if>
				</div>
			</c:forEach>
		
			
			<button value="Print" id="printwindow" name="printwindow" onclick="printWindow();">Print</button>
	</form>
	
	
</body>
</html>
