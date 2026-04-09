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

span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 300px;
    font-weight: normal;
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

.marksTableHeader {
	background-color: #f2f2f2;
	font-weight: bold;
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

.marksTableCell {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

.marksTableCellLeft {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
	font-weight: bold;
}

.summaryTableHeader {
	background-color: #f9f9f9;
	font-weight: bold;
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}

-->
</style>

<style type="text/css">
	@media print {
		.fontsize { 
			font-size: 10px ;
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
		.fontsize { 
			font-size: 10px;
			font-weight: bold;
			font-family: 'Times New Roman'
		}
		.bodymargin{
			margin-left: 0px ;
			margin-right: 0px;
		}
	}
</style>

<script type="text/javascript">
	window.onload = function(){
		window.print();
	}
</script>
<title>Marks Sheet</title>
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
<form method="post" class="bodymargin">
	<c:forEach items="${markssheetlist}" var="Parents">
		
		<div style="page-break-inside: avoid;border-style: solid;border-width: thin;">   
			
			<!-- HEADER SECTION WITH SCHOOL DETAILS -->
			<table style="page-break-inside: avoid;border-collapse: collapse;width: 100%;">
				<tr>
					<td style="padding-left: 200px;"><img src="/abc/images/abc${branchid}.jpg" width="72" height="80"/></td>
					<td>
						<label class="dataTextBoldCenter">${branchname}</label><br>
						<label class="addressLine">${branchaddress}</label>
					</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
			</table>

			<TABLE width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
				<tr>
					<td colspan="4" ></td>
				</tr>
			</TABLE>

			<!-- STUDENT DETAILS SECTION -->
			<table style="border-collapse: collapse;width: 100%;margin-bottom: 20px;">
				<tr>
					<td style="width: 70%;">
						<table style="border-collapse: collapse;width: 100%;">
							<tr style="border-color:#000000">
								<td class="namedetails" style="width: 50%;"><label>Student Name:&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;text-transform: capitalize;"><c:out value="${Parents.parents.student.name}"/></label></td>
								<td class="namedetails" style="width: 50%;"><label>Class:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<c:forEach var="splt" items="${fn:split(Parents.parents.student.classstudying,'--')}">
										${splt} 
									</c:forEach>
								</td>
							</tr>
							<tr>   
								<td class="namedetails" style="width: 50%;"><label>Father's Name:&nbsp;&nbsp;&nbsp;</label><label style="font-weight: bold;text-transform: capitalize;"><c:out value="${Parents.parents.fathersname}"/></label></td>	
								<td class="namedetails" style="width: 50%;"><label>Exam Reg. No.:&nbsp;&nbsp;&nbsp;</label><c:out value="${Parents.parents.student.admissionnumber}"/></td>
							</tr>
						</table>
					</td>
					<td style="width: 30%;text-align: center;vertical-align: top;">
						<img src="data:image;base64,<c:out value="${Parents.parents.student.studentpic}"/>" alt="Student's Photo" style="width: 60px;height: 60px;border: 1px solid #ccc;"/>
					</td>
				</tr>
			</table>

				<!-- MARKS TABLE - SUBJECTS AS ROWS, EXAMS AS COLUMNS -->
				<table
					style="border-collapse: collapse; width: 100%; margin-top: 20px;">
					<thead>
						<tr>
							<th class="marksTableHeader"
								style="text-align: left; width: 20%;">Subject</th>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<th class="marksTableHeader"><c:out
										value="${exam.examName}" /></th>
							</c:forEach>
							<th class="marksTableHeader"
								style="text-align: center; width: 15%;">Remarks</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry"
							varStatus="status">
							<tr>
								<td class="marksTableCellLeft" style="width: 20%;"><c:out
										value="${subjectEntry.key}" /></td>
								<c:forEach items="${Parents.examSummaries}" var="exam">
									<td class="marksTableCell"><c:out
											value="${subjectEntry.value[exam.examName]}" /></td>
								</c:forEach>
								
								<!-- Subject-wise Summary Columns -->
			                <c:set var="currentSubject" value="" />
			                <c:forEach items="${Parents.subjectSummaries}" var="subjectSummary">
			                    <c:if test="${subjectSummary.subjectName == subjectEntry.key}">
			                        <c:set var="currentSubject" value="${subjectSummary}" />
			                    </c:if>
			                </c:forEach>
			                
			                <!-- Total Marks for Subject -->
			                <td class="marksTableCell" style="text-align: center;">
			                    <c:choose>
			                        <c:when test="${currentSubject != ''}">
			                            <fmt:formatNumber value="${currentSubject.totalMarksObtained}" maxFractionDigits="2" />
			                            / 
			                            <fmt:formatNumber value="${currentSubject.totalMaxMarks}" maxFractionDigits="0" />
			                        </c:when>
			                        <c:otherwise>-</c:otherwise>
			                    </c:choose>
			                </td>
			                
			                <!-- Percentage for Subject -->
			                <td class="marksTableCell" style="text-align: center;">
			                    <c:choose>
			                        <c:when test="${currentSubject != '' && currentSubject.totalPercentage > 0}">
			                            <fmt:formatNumber type="number" maxFractionDigits="1" value="${currentSubject.totalPercentage}" />%
			                        </c:when>
			                        <c:otherwise>-</c:otherwise>
			                    </c:choose>
			                </td>
			                
			                <!-- Grade for Subject -->
			                <td class="marksTableCell" style="text-align: center;">
			                    <c:choose>
			                        <c:when test="${currentSubject != '' && currentSubject.overallGrade != null && currentSubject.overallGrade != ''}">
			                            ${currentSubject.overallGrade}
			                        </c:when>
			                        <c:otherwise>-</c:otherwise>
			                    </c:choose>
			                </td>
			                
								<!-- Remarks Column - Only add rowspan on first row to span both tables -->
								<c:if test="${status.first}">
									<td rowspan="${fn:length(Parents.subjectExamMarks) + 6}"
										class="marksTableCell"
										style="text-align: left; vertical-align: top; padding: 8px; width: 15%;">
										<c:forEach items="${Parents.examSummaries}" var="exam"
											varStatus="examStatus">
											<div
												style="border-bottom: 1px solid #000000; padding: 10px 5px; min-height: 100px; margin-bottom: 5px;">
												<strong style="font-size: 10px;"><c:out value="${exam.examName}" /></strong><br />
											</div>
										</c:forEach>
									</td>
								</c:if>
							</tr>
						</c:forEach>

						<!-- Summary Section Rows within the same table -->
						<tr>
							<td class="summaryTableHeader" style="width: 20%;">Summary</td>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<td class="marksTableCell">${exam.totalMarksObtained}</td>
							</c:forEach>
						</tr>

						<tr>
							<td class="summaryTableHeader">Total Marks Obtained</td>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<td class="marksTableCell">${exam.totalMarksObtained}</td>
							</c:forEach>
						</tr>

						<tr>
							<td class="summaryTableHeader">Total Marks</td>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<td class="marksTableCell">${exam.totalMarks}</td>
							</c:forEach>
						</tr>

						<tr>
							<td class="summaryTableHeader">Percentage</td>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<td class="marksTableCell"><c:choose>
										<c:when test="${exam.percentage > 0}">
											<fmt:formatNumber type="number" maxFractionDigits="1"
												value="${exam.percentage}" />%
						</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose></td>
							</c:forEach>
						</tr>

						<tr>
							<td class="summaryTableHeader">Grade</td>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<td class="marksTableCell"><c:choose>
										<c:when test="${exam.grade != null && exam.grade != ''}">
							${exam.grade}
						</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose></td>
							</c:forEach>
						</tr>

						<tr>
							<td class="summaryTableHeader">Rank</td>
							<c:forEach items="${Parents.examSummaries}" var="exam">
								<td class="marksTableCell"><c:choose>
										<c:when test="${exam.rank > 0}">
							${exam.rank}
						</c:when>
										<c:otherwise>-</c:otherwise>
									</c:choose></td>
							</c:forEach>
						</tr>
					</tbody>
				</table>

				<!-- SIGNATURE SECTION -->
			<table id="dataTable" width="100%" border="0" style="page-break-inside:avoid; border-collapse: collapse;margin-top: 30px;">
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td style="width: 25%;text-align: center;"><br></td>
					<td style="width: 25%;text-align: center;"><br></td>
					<td style="width: 25%;text-align: center;"><br></td>
					<td style="width: 25%;text-align: center;"><br></td>
				</tr>
				<tr>
					<td style="width: 25%;text-align: center;border-top: 1px solid black;">Class Teacher</td>
					<td style="width: 25%;text-align: center;border-top: 1px solid black;">Principal</td>
					<td style="width: 25%;text-align: center;border-top: 1px solid black;">Parent</td>
					<td style="width: 25%;text-align: center;border-top: 1px solid black;">Date</td>
				</tr>
			</table>

		</div>

		<!-- ATTENDANCE SECTION - DISPLAYED ONCE PER STUDENT -->
		<table style="margin-top: 30px;width: 100%;margin-bottom: 40px;">
			<tr>
				<td style="font-weight:bold;">Total Days:&emsp;&emsp;</td>
				<td style="font-weight:bold;">${totaldays}</td>
				<td style="font-weight:bold;">&emsp;&emsp;Total Present:&emsp;&emsp;</td>
				<td style="font-weight:bold;">${totalpresent}</td>
				<td style="font-weight:bold;">&emsp;&emsp;Total Absent:&emsp;&emsp;</td>
				<td style="font-weight:bold;">${totalabsent}</td>
			</tr>
		</table>

	</c:forEach>
	
</form>

</body>
</html>