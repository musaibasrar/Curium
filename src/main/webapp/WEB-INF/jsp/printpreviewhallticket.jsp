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
	font-weight: normal;
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
        
        .subjectdetails{
        	border: 1px solid black;
    		text-align: center;
   		    padding: 8px;
   		    font-size: 12px;
        }
        
        .nosubjectdetails{
        	border: 0px;
    		text-align: left;
   		    padding: 8px;
   		    font-weight: normal;
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
	<title> </title>
        
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/littleflower/UserProcess/sessionTimeOut");
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
                        <div class="ticket-container">

<c:forEach items="${studentList}" var="Parents" varStatus="status">

    <div class="ticket">
        <div class="ticket-inner">

            <!-- ===== HEADER ===== -->
            <table width="100%" style="border-collapse: collapse;">
                <tr>
                    <td><img src="/cambridge/images/cambridge${branchid}.jpg" width="30" height="30"/></td>
                    <td align="center">
                        <label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
                    </td>
                </tr>
            </table>

            <hr style="margin-top: 2px;margin-bottom: 2px;">

            <!-- ===== TITLE ===== -->
            <table width="100%">
                <tr>
                	<td width="30%"></td>
                    <td class="dataTextBoldCenter">
                        Hall Ticket<br>${examname}
                    </td>
                    <td align="right">
                        <c:choose>
									<c:when test="${not empty Parents.student.studentpic}">

										<c:choose>
											<%-- New format --%>
											<c:when test="${fn:startsWith(Parents.student.studentpic, 'data:')}">

												<c:choose>
													<%-- PDF --%>
													<c:when
														test="${fn:startsWith(Parents.student.studentpic, 'data:application/pdf')}">
														<a href="${Parents.student.studentpic}" target="_blank"
															rel="noopener noreferrer"> PDF </a>
													</c:when>

													<%-- Image --%>
													<c:otherwise>
														<img src="${Parents.student.studentpic}" alt="Student's Pic"
															style="width: 30px; height: 30px;">
													</c:otherwise>
												</c:choose>

											</c:when>

											<%-- Old raw Base64 format --%>
											<c:otherwise>
												<img src="data:image/jpeg;base64,${Parents.student.studentpic}"
													alt="Student's Pic" style="width: 30px; height: 30px;">
											</c:otherwise>
										</c:choose>

									</c:when>

									<c:otherwise>
                    No document available
                </c:otherwise>
								</c:choose>
                    </td>
                </tr>
            </table>

            <!-- ===== STUDENT DETAILS ===== -->
            <table width="100%">
                <tr>
                    <td style="text-align: left;font-size: 10px;">Student Name: <b>${Parents.student.name}</b></td>
                    <td style="text-align: left;font-size: 10px;">
                        Class:
                        <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
                            ${splt}
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td style="text-align: left;font-size: 10px;">Father's Name: ${Parents.fathersname}</td>
                    <td style="text-align: left;font-size: 10px;">Admission No: ${Parents.student.admissionnumber}</td>
                </tr>
            </table>

            <!-- ===== EXAM TABLE ===== -->
            <table width="100%" class="datatable">
                <thead>
                <tr>
                    <th class="datath">Date</th>
                    <th class="datath">Day</th>
                    <th class="datath">Subject</th>
                    <th class="datath">Time</th>
                    <th class="datath">Sign</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${examschedulelist}" var="schedule">
                    <tr>
                        <td class="datatd">
                            <fmt:formatDate value="${schedule.date}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td class="datatd">
                            <fmt:formatDate value="${schedule.date}" pattern="E"/>
                        </td>
                        <td class="datatd">${schedule.subject}</td>
                        <td class="datatd">${schedule.starttime} - ${schedule.endtime}</td>
                        <td class="datatd"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <br><br>

            <table width="100%">
                <tr>

                    <td colspan="4" ></td>

            <!-- ===== TITLE & STUDENT DETAILS WITH SIDE PHOTO ===== -->
			<table width="100%" style="border-collapse: collapse; margin-bottom: 8px;">
				<tr>
					<!-- Left Section: Exam Title & Student Details -->
					<td style="vertical-align: top; width: 78%; padding-right: 10px;">
						<!-- Exam Title -->
						<div class="dataTextBoldCenter"
     style="text-align: center; margin-bottom: 12px; width: 128.2%; margin-left: 0;">
    Hall Ticket<br>${examname}
</div>

						<!-- Student Details Grid -->
						<table style="border-collapse: collapse; width: 100%;">
							<tr>
								<td class="namedetails" style="width: 55%;">
									<label><strong>Student Name:</strong>&nbsp;</label>
									<label style="text-transform: capitalize;"><c:out value="${Parents.student.name}"/></label>
								</td>
								<td class="namedetails">
									<label><strong>Class:</strong>&nbsp;</label>
									<c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
										${splt}&nbsp;
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td class="namedetails">
									<label><strong>Father's Name:</strong>&nbsp;</label>
									<label style="text-transform: capitalize;"><c:out value="${Parents.fathersname}"/></label>
								</td>
								<td class="namedetails">
									<label><strong>Admission No.:</strong>&nbsp;</label>
									<c:out value="${Parents.student.admissionnumber}"/>
								</td>
							</tr>
						</table>
					</td>

					<!-- Right Section: Photo covering both title and details height -->
					<td align="center" style="vertical-align: middle; width: 22%; border: 0px solid #333; padding: 4px;">
						<c:choose>
							<c:when test="${not empty Parents.student.studentpic}">
								<c:choose>
									<%-- New format --%>
									<c:when test="${fn:startsWith(Parents.student.studentpic, 'data:')}">
										<c:choose>
											<%-- PDF --%>
											<c:when test="${fn:startsWith(Parents.student.studentpic, 'data:application/pdf')}">
												<a href="${Parents.student.studentpic}" target="_blank" rel="noopener noreferrer">PDF Document</a>
											</c:when>
											<%-- Image --%>
											<c:otherwise>
												<img src="${Parents.student.studentpic}" alt="Student Photo" style="width: 80px; height: 100px; object-fit: cover; display: block; margin: 0 auto;">
											</c:otherwise>
										</c:choose>
									</c:when>
									<%-- Old raw Base64 format --%>
									<c:otherwise>
										<img src="data:image/jpeg;base64,${Parents.student.studentpic}" alt="Student Photo" style="width: 80px; height: 100px; object-fit: cover; display: block; margin: 0 auto;">
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div style="width: 100px; height: 120px; border: 1px dashed #888; display: flex; align-items: center; justify-content: center; font-size: 11px; margin: 0 auto;">
									Affix Photo
								</div>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
            
                            <table style=" border-collapse: collapse;width: 100%;">
                            	<thead>
                            	<tr>
                            	<th class="subjectdetails">Date</th>
                            	<th class="subjectdetails">Day</th>
                            	<th class="subjectdetails">Subject</th>
                            	<th class="subjectdetails">Time</th>
                            	<th class="subjectdetails">Invigilator's Sign</th>
                            	</tr>
                            	</thead>
                            	<tbody>
                            	<c:forEach items="${examschedulelist}" var="examschedulelist">
                             	<tr>
                                <td class="subjectdetails"><fmt:formatDate value="${examschedulelist.date}" pattern="dd/MM/yyyy"/></td>
                                <td class="subjectdetails"><fmt:formatDate value="${examschedulelist.date}" pattern="E"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.subject}"/></td>
                                <td class="subjectdetails"><c:out value="${examschedulelist.starttime}"/>&nbsp;-&nbsp;<c:out value="${examschedulelist.endtime}"/></td>
                                <td class="subjectdetails"></td>
                                </tr>
                                 </c:forEach>
                       
                   				 </tbody>
                            </table>
                            <br>
                            
                            <TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">

						<!-- <tr>
							<td><br><br><br></td>
						</tr> -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
				<tr>
				<td></td>
				<td align="left"></td>	
					<td align="centre"></td>
					<c:choose>
					    <c:when test="${branchid == 3}">
						<td align="center">Principal</td>
					    </c:when>
					    <c:otherwise>
						<td align="center">Head Master</td>
					    </c:otherwise>
					</c:choose>
					</tr>
                    
		</TABLE>
		</div>
		<br><br><br><br><br>
                                 
                        </c:forEach>
			
	</form>
	
	
</body>
</html>
