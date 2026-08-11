<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Marks Sheet</title>

<style>

/* ===== PRINT SETTINGS ===== */
@page {
    size: A4 portrait;
    margin: 8mm;
}

/* @media print {
    body { margin: 0; }
}
 */
 @media print {
    html, body {
        width: 210mm;
        height: 297mm;
        margin: 0;
        padding: 0;
    }

    body {
        -webkit-print-color-adjust: exact;
        print-color-adjust: exact;
    }
}
/* ===== COMMON ===== */
body {
    font-family: Tahoma;
    font-size: 12px;
}

/* ===== HEADER ===== */
.title {
    font-size: 16px;
    font-weight: bold;
    text-align: center;
}

.address {
    text-align: center;
    font-size: 12px;
}

/* ===== MAIN TABLE ===== */
.main-border {
    width: 100%;
    border: 1px solid #000;
    border-collapse: collapse;
    page-break-inside: avoid;
}

.cell {
    border-right: 1px solid #000;
    border-bottom: 1px solid #000;
    padding: 6px;
    text-align: center;
}

.left {
    text-align: left;
}

.last-col {
    border-right: none;
}

/* ===== SUBJECT HEADER ===== */
.subject-header {
    transform: rotate(-90deg);
    height: 150px;
    vertical-align: middle;
    font-weight: bold;
    font-size: 11px;
}

</style>

<script>
window.onload = function(){
    window.print();
};
</script>

</head>

<body>

<<<<<<< HEAD
<!-- ===== SCHOOL HEADER ===== -->
<table width="100%">
    <tr>
        <td width="20%" align="center">
            <img src="/patriswamy/images/patriswamy.jpg" width="224" height="80">
        </td>
        <td width="80%">
            <div class="title">${branchname}</div>
            <div class="address">${branchaddress}</div>
        </td>
    </tr>
</table>

<br>

<!-- ===== CLASS / EXAM INFO ===== -->
<table width="100%">
    <tr>
        <td align="left"><b>Class:</b> ${examclass}</td>
        <td align="center">
    <b>Exam:</b>
    <c:forEach items="${markssheetlist}" var="p" varStatus="ps">
        <c:if test="${ps.index == 0}">
            <c:forEach items="${p.exammarks}" var="em" varStatus="es">
                <c:if test="${es.index == 0}">
                    ${em.examName}
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>
</td>

        <td align="right"><b>Academic Year:</b> ${currentAcademicYear}</td>
    </tr>
</table>

<br>

<!-- ===== MARKS TABLE ===== -->
<table class="main-border">

    <!-- HEADER -->
    <tr>
        <td class="cell"><b>Sl.No</b></td>
        <td class="cell"><b>UID</b></td>
        <td class="cell"><b>Student Name</b></td>
        <td class="cell"><b>Father Name</b></td>

      <c:forEach items="${markssheetlist[0].subjectSummaries}" var="sub">
        <td class="cell subject-header">
            ${sub.subjectName}
        </td>
    </c:forEach>

        <td class="cell subject-header">Total Obtained</td>
        <td class="cell subject-header">Total Marks</td>
        <td class="cell subject-header">%</td>
        <td class="cell subject-header last-col">Rank</td>
    </tr>

    <!-- DATA -->
    <c:forEach items="${markssheetlist}" var="Parents" varStatus="s">
=======
<c:forEach items="${markssheetlist}" var="Parents" varStatus="studentStatus">
	<c:set var="studentGraphKey" value="s${studentStatus.index}_${Parents.parents.student.sid}" />
		
	<div style="page-break-inside: avoid;">   

	<div class="container">

		<div class="header">

			<!-- <div class="topline">
				<div>
					<b>SCHOOL DISE CODE : 29050505406</b>
				</div>
				<div>
					<b style="color: green"><label style="font-size:12px;text-transform: uppercase;">Educational Trust®</label></b>
				</div>
				<div>
					<b>SSLC BOARD (KSEAB) CODE : SS0612</b>
				</div>
			</div> -->

			<div class="schoolbox">
			
				<div class="logo">
					<img border="0" style="vertical-align: text-bottom;height: 90px;width: 90px;" alt="ideoholic" src="/patriswamy/images/patriswamy.png">
				</div>
		
				<div class="schoolname">
					<h3 style="font-size: 40px;color: #971d1d;">${branchname}</h3>
					<!-- <img border="0" style="vertical-align: text-bottom;height: 30px;width: 200px;" alt="ideoholic" src="/patriswamy/images/patriswamyschoolname.png"> -->
					<div>
						<b><label style="font-size:17px;text-transform: uppercase;">${branchaddress}</label></b>
					</div>
				</div>

			</div>

		</div>


		<div class="title">
			<h2><label style="font-size:29px;text-transform: uppercase;">Achievement Record</label></h2>
			<div style="font-size: 21px;font-weight:bold;">
			<c:set var="yearParts" value="${fn:split(currentAcademicYear, '/')}" />
			<c:set var="startYear" value="${yearParts[0]}" />
			<c:set var="endYear" value="${startYear + 1}" />

			Academic Year ${startYear}-${endYear}</div>
			
			<c:choose>
			<c:when test="${fn:length(Parents.examSummaries) == 6}">
					<div class="cce" style="font-size: 27px;">Continuous And
				Comprehensive Evaluation</div>
			<div class="certifyline" style="font-size: 16px;font-weight:bold;">
				This is to certify that the below mentioned candidate has passed <b>
				<c:set var="dataSubParts" value="${fn:split(Parents.parents.student.classstudying,'--')}" />
						<c:choose>
						<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}"><label style="text-transform: uppercase;">${dataSubParts[0]}</label></c:when>
						    <c:when test="${dataSubParts[0] == '1'}"><label >1<sup>st</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '2'}"><label>2<sup>nd</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '3'}"><label>3<sup>rd</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '4'}"><label>4<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '5'}"><label>5<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '6'}"><label>6<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '7'}"><label>7<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '8'}"><label>8<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '9'}"><label>9<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '10'}"><label>10<sup>th</sup> Std.</label></c:when>
						</c:choose>
				</b> Examination with the following details.
			</div>
			</c:when>
			
			<c:otherwise>
				<div class="cce" style="font-size: 27px;">Continuous And Comprehensive Evaluation</div>
			</c:otherwise>
			
			</c:choose>
			
		</div>


		<div class="studentinfo">

			<table>

				<tr style="border: 1px solid black; border-collapse: collapse;">
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>SATS
							No :<c:out value="${Parents.parents.student.sts}" />&nbsp;&nbsp;&nbsp;</b></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Roll
							No :<c:out value="${Parents.parents.student.admissionnumber}" />
					&nbsp;&nbsp;&nbsp;</b></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Class
							: <%-- ${Parents.parents.student.classstudying} --%><c:set var="dataSubParts11" value="${fn:split(Parents.parents.student.classstudying,'--')}" />${dataSubParts11[0]}
						<%-- <c:choose>
						<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}">${dataSubParts[0]}</c:when>
						    <c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}"><label style="text-transform: uppercase;">${dataSubParts[0]}</label></c:when>
						    <c:when test="${dataSubParts[0] == '1'}"><label >1<sup>st</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '2'}"><label>2<sup>nd</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '3'}"><label>3<sup>rd</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '4'}"><label>4<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '5'}"><label>5<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '6'}"><label>6<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '7'}"><label>7<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '8'}"><label>8<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '9'}"><label>9<sup>th</sup> Std.</label></c:when>
						    <c:when test="${dataSubParts[0] == '10'}"><label>10<sup>th</sup> Std.</label></c:when>
						</c:choose> --%>
					</b></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Section :
							 <c:set var="dataSubParts112" value="${fn:split(Parents.parents.student.classstudying,'--')}" />${dataSubParts112[1]}<%-- <fmt:formatDate value="${Parents.parents.student.dateofbirth}" pattern="dd/MM/yyyy"/> --%></b></td>
					<%-- <td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Date of Birth
							: <fmt:formatDate value="${Parents.parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></b></td> --%>
				</tr>
				<tr>
					<br>
				</tr>
				<tr>
					<td colspan="3" style="font-size: 20px;"><b>Student's Name :
						<label style="text-transform: uppercase;"><c:out
								value="${Parents.parents.student.name}" /></label></b></td>
					<td rowspan="3" align="center"><div class="studentphoto">
							<img src="data:image;base64,<c:out value="${Parents.parents.student.studentpic}"/>" width="120" height="120">
						</div></td>
				</tr>

				<tr>
					<td colspan="3" style="font-size: 20px;"><b>Father's Name :
						<label style="text-transform: uppercase;"><c:out
								value="${Parents.parents.fathersname}" /></label></b></td>
				</tr>

				<tr>
					<td colspan="3" style="font-size: 20px;"><b>Mother's Name :<label
						style="text-transform: uppercase;"><c:out
								value="${Parents.parents.mothersname}" /></label></b></td>
				</tr>

			</table>

		</div>

 		<!-- NEW FLEX CONTAINER WRAPPER -->
        <div class="flex-row-container">
        <div class="marks-left-column">
		<div class="marks">
			<c:set var="subjectCount" value="${fn:length(Parents.subjectExamMarks)}" />
			<c:choose>

			<%-- NEW BRANCH: more than 8 subjects — one plain table per exam, subjects as columns --%>
			<c:when test="${subjectCount > 8}">
				<%-- Compute grand totals from examSummaries so totalbox still works correctly --%>
				<c:set var="grandTotalMarksObtainedFromExamSummary" value="0" />
				<c:set var="grandTotalMaxMarksFromExamSummary" value="0" />
				<c:forEach items="${Parents.examSummaries}" var="examSummaryTotal">
					<c:set var="grandTotalMarksObtainedFromExamSummary" value="${grandTotalMarksObtainedFromExamSummary + examSummaryTotal.totalMarksObtained}" />
					<c:set var="grandTotalMaxMarksFromExamSummary" value="${grandTotalMaxMarksFromExamSummary + examSummaryTotal.totalMarks}" />
				</c:forEach>
				<c:set var="grandPercentage" value="0" />
				<c:if test="${grandTotalMaxMarksFromExamSummary > 0}">
					<c:set var="grandPercentage" value="${Math.round(((grandTotalMarksObtainedFromExamSummary / grandTotalMaxMarksFromExamSummary) * 100) * 10) / 10.0}" />
				</c:if>
				<c:set var="grandPercentagestring" value="0" />
				<c:if test="${grandTotalMaxMarksFromExamSummary > 0}">
					<c:set var="grandPercentagestring"><fmt:formatNumber value="${(grandTotalMarksObtainedFromExamSummary / grandTotalMaxMarksFromExamSummary) * 100}" maxFractionDigits="1" /></c:set>
				</c:if>
				<c:set var="roundedMarks"><fmt:formatNumber value="${grandTotalMarksObtainedFromExamSummary}" maxFractionDigits="0" /></c:set>
				<%-- Hidden cell required by the number-to-words JS --%>
				<span class="amount" style="display: none;">${roundedMarks}</span>

				<%-- Render one table per exam --%>
				<c:forEach items="${Parents.examSummaries}" var="exam">
					<div style="margin-top: 20px; page-break-inside: avoid;">
						<h4 style="text-align: center; text-transform: uppercase; margin-bottom: 4px;"><c:out value="${exam.examName}" /></h4>
						<hr style="border: 1px solid #000; margin-bottom: 4px;" />
						<table style="border-collapse: collapse; width: 100%;">
							<thead>
								<tr>
									<c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry">
										<c:set var="markStr" value="${subjectEntry.value[exam.examName]}" />
										<c:if test="${not empty markStr and markStr != '-'}">
											<th class="marksTableHeader" style="text-transform: capitalize;"><c:out value="${subjectEntry.key}" /></th>
										</c:if>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry">
										<c:set var="markStr" value="${subjectEntry.value[exam.examName]}" />
										<c:if test="${not empty markStr and markStr != '-'}">
											<c:set var="parts" value="${fn:split(markStr, '/')}" />
											<c:set var="secured" value="${parts[0]}" />
											<td class="marksTableCell">
												<c:choose>
													<c:when test="${secured == '999'}">A</c:when>
													<c:otherwise><c:out value="${secured}" /></c:otherwise>
												</c:choose>
											</td>
										</c:if>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</c:when>

			<%-- EXISTING logic unchanged: 6-exam CCE layout and general single-exam layout --%>
			<c:otherwise>
			<c:choose>
						        <c:when test="${fn:length(Parents.examSummaries) == 6}">
						        	<h4	style="text-align: center; margin-bottom: 0px; padding-bottom: 0px;">PART-A</h4>
						        </c:when>
						        
						        <c:otherwise>
						        	
						        </c:otherwise>
						        
						        </c:choose>
			
						<table style="border-collapse: collapse; width: 100%; margin-top: 20px;">
						    <thead>
						    
						    <c:choose>
						        <c:when test="${fn:length(Parents.examSummaries) == 6}">
						        	<tr>
										<th rowspan="2" style="text-transform: uppercase;">Scholastic Subjects</th>
										<th colspan="3" style="text-transform: uppercase;">Semester-1</th>
										<th colspan="3" style="text-transform: uppercase;">Semester-2</th>
										<th colspan="2" style="text-transform: uppercase;">TOTAL</th>
										<th rowspan="2" style="text-transform: uppercase;">OVER ALL Grade</th>
									</tr>
									<tr>
										<th>FA-1<br>(10%)</th>
										<th>FA-2<br>(10%)</th>
										<th>SA-1<br>(30%)</th>
										<th>FA-3<br>(10%)</th>
										<th>FA-4<br>(10%)</th>
										<th>SA-2<br>(30%)</th>
										<th>MAX.<br>MARKS</th>
										<th>OBT.<br>MARKS</th>
									</tr>
						        </c:when>
						
						        <c:otherwise>
						        	<tr>
						        		<c:forEach items="${Parents.examSummaries}" var="exam">
						                <th class="marksTableHeader" style="text-transform: uppercase;" colspan="5"><c:out value="${exam.examName}" /></th>
						            </c:forEach>
						        	</tr>
						        	<tr>
						            <th class="marksTableHeader" style="text-align: center; width: 20%;text-transform: uppercase;"> Scholastic Subject</th>
						            <!-- Subject-wise Summary Headers -->
						            <th class="marksTableHeader" style="text-align: center; width: 12%;">Obtained Marks</th>
						            <th class="marksTableHeader" style="text-align: center; width: 12%;">Max. Marks</th>
						            <th class="marksTableHeader" style="text-align: center; width: 12%;">Percentage</th>
						            <th class="marksTableHeader" style="text-align: center; width: 10%;">Grade</th>
						            <!-- <th class="marksTableHeader" style="text-align: center; width: 15%;">Remarks</th> -->
						        </tr>      
						            
						                
						        </c:otherwise>
						      </c:choose>
						    
						        
						    </thead>
						    <tbody>
									     <!-- Initialize grand totals -->
								        <c:set var="grandTotalMarksObtained" value="0" />
								        <c:set var="grandTotalMaxMarks" value="0" />
								        <c:set var="englishexam" value="" />
								        <%-- Use backend exam summaries for final totals so excluded subjects stay excluded in aggregates. --%>
								        <c:set var="grandTotalMarksObtainedFromExamSummary" value="0" />
								        <c:set var="grandTotalMaxMarksFromExamSummary" value="0" />
								        <c:forEach items="${Parents.examSummaries}" var="examSummaryTotal">
								        	<c:set var="grandTotalMarksObtainedFromExamSummary" value="${grandTotalMarksObtainedFromExamSummary + examSummaryTotal.totalMarksObtained}" />
								        	<c:set var="grandTotalMaxMarksFromExamSummary" value="${grandTotalMaxMarksFromExamSummary + examSummaryTotal.totalMarks}" />
								        </c:forEach>
						        <c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry" varStatus="status">
						            <tr>
						                <td class="marksTableCellLeft" style="width: 20%;text-transform: capitalize;"><c:out value="${subjectEntry.key}" /></td>
						                
						                <!-- Exam-wise marks calculation -->
						                <c:set var="subjectTotalMarksObtained" value="0" />
						                <c:set var="subjectTotalMaxMarks" value="0" />
						                
						                <c:forEach items="${Parents.examSummaries}" var="exam">
						                    <td class="marksTableCell">
						                        <c:set var="markStr" value="${subjectEntry.value[exam.examName]}" />
						
						                        <c:choose>
						                            <c:when test="${markStr == '-' || empty markStr}">
						                                <c:out value="-" />
						                            </c:when>
						
						                            <c:otherwise>
						                                <c:set var="parts" value="${fn:split(markStr, '/')}" />
						                                <c:set var="secured" value="${parts[0]}" />
						                                <c:set var="maxPart" value="${parts[1]}" />
						                                <!-- Extract max marks (before space or parenthesis) -->
						                                <c:set var="maxMarks" value="${fn:trim(fn:substringBefore(maxPart, ' '))}" />
						                                <c:if test="${empty maxMarks}">
						                                    <c:set var="maxMarks" value="${fn:trim(fn:substringBefore(maxPart, '('))}" />
						                                </c:if>
						                                <c:if test="${empty maxMarks}">
						                                    <c:set var="maxMarks" value="${maxPart}" />
						                                </c:if>
						
															                                <c:choose>
																					<c:when test="${fn:contains(exam.examName, 'FA') and not showFullMarks}">
																						<%-- Full marks mode bypasses FA/SA conversion and falls through to c:otherwise raw marks rendering. --%>
						                                        <!-- Check if secured is numeric and NOT 999 (Absent) -->
						                                        <c:choose>
						                                            <c:when test="${secured != null && secured != '' && secured != 'AB' && secured != '999'}">
						                                                <c:set var="displayMarks" value="${(secured / 20) * 10}" />
						                                                <fmt:formatNumber value="${displayMarks}" maxFractionDigits="1" />
						                                                <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                                <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 10}" />
						                                            </c:when>
						                                            <c:otherwise>
						                                                <!-- Non-numeric value like AB or 999 (Absent) - just display it -->
						                                                <c:choose>
						                                                    <c:when test="${secured == '999'}">A</c:when>
						                                                    <c:otherwise><c:out value="${secured}" /></c:otherwise>
						                                                </c:choose>
						                                            </c:otherwise>
						                                        </c:choose>
						                                    </c:when>
						
						                                    <c:when test="${fn:contains(exam.examName, 'SA') and not showFullMarks}">
						                                        <!-- Check if secured is numeric and NOT 999 (Absent) -->
						                                        <c:choose>
						                                            <c:when test="${secured != null && secured != '' && secured != 'AB' && secured != '999'}">
						                                                <c:set var="displayMarks" value="${(secured / 50) * 30}" />
						                                                <fmt:formatNumber value="${displayMarks}" maxFractionDigits="1" />
						                                                <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                                <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 30}" />
						                                            </c:when>
						                                            <c:otherwise>
						                                                <!-- Non-numeric value like AB or 999 (Absent) - just display it -->
						                                                <c:choose>
						                                                    <c:when test="${secured == '999'}">A</c:when>
						                                                    <c:otherwise><c:out value="${secured}" /></c:otherwise>
						                                                </c:choose>
						                                            </c:otherwise>
						                                        </c:choose>
						                                    </c:when>
						
						                                    <c:otherwise>
						                                        <!-- Check if secured is numeric and NOT 999 (Absent) -->
						                                        <c:choose>
						                                            <c:when test="${secured != null && secured != '' && secured != 'AB' && secured != '999'}">
						                                                <c:out value="${secured}" />
						                                                <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + secured}" />
						                                                <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + maxMarks}" />
						                                            </c:when>
						                                            <c:otherwise>
						                                                <!-- Non-numeric value like AB or 999 (Absent) - just display it -->
						                                                <c:choose>
						                                                    <c:when test="${secured == '999'}">A</c:when>
						                                                    <c:otherwise><c:out value="${secured}" /></c:otherwise>
						                                                </c:choose>
						                                            </c:otherwise>
						                                        </c:choose>
						                                    </c:otherwise>
						                                </c:choose>
						                            </c:otherwise>
						                        </c:choose>
						                    </td>
						                </c:forEach>
						                
						                <!-- Calculate percentage for subject -->
						                <c:set var="subjectPercentage" value="0" />
						                <c:if test="${subjectTotalMaxMarks > 0}">
						                    <c:set var="subjectPercentage" value="${(subjectTotalMarksObtained / subjectTotalMaxMarks) * 100}" />
						                </c:if>
						                
						                <!-- Calculate grade based on percentage -->
						                <c:set var="subjectGrade" value="-" />
						                <c:choose>
						                    <c:when test="${subjectPercentage >= 90}">
						                        <c:set var="subjectGrade" value="A+" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 70}">
						                        <c:set var="subjectGrade" value="A" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 50}">
						                        <c:set var="subjectGrade" value="B+" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 30}">
						                        <c:set var="subjectGrade" value="B" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 29}">
						                        <c:set var="subjectGrade" value="C" />
						                    </c:when>
						                    <c:otherwise>
						                        <c:set var="subjectGrade" value="F" />
						                    </c:otherwise>
						                </c:choose>
						                
						                 <!-- Add to grand totals -->
                							<c:set var="grandTotalMarksObtained" value="${grandTotalMarksObtained + subjectTotalMarksObtained}" />
                							<c:set var="grandTotalMaxMarks" value="${grandTotalMaxMarks + subjectTotalMaxMarks}" />
                
						                <!-- Total Marks for Subject -->
						                <td class="marksTableCell" style="text-align: center;">
						                    <fmt:formatNumber value="${subjectTotalMaxMarks}" maxFractionDigits="0" />
						                </td>
						                <!-- Percentage column for non-6-exam layout; Obt. Marks column for 6-exam layout -->
						                <c:choose>
						                    <c:when test="${fn:length(Parents.examSummaries) == 6}">
						                        <td class="marksTableCell" style="text-align: center;">
						                            <fmt:formatNumber value="${subjectTotalMarksObtained}" maxFractionDigits="1" />
						                        </td>
						                    </c:when>
						                    <c:otherwise>
						                        <td class="marksTableCell" style="text-align: center;">
						                            <c:choose>
						                                <c:when test="${subjectPercentage > 0}">
						                                    <fmt:formatNumber type="number" maxFractionDigits="1" value="${subjectPercentage}" />%
						                                </c:when>
						                                <c:otherwise>-</c:otherwise>
						                            </c:choose>
						                        </td>
						                    </c:otherwise>
						                </c:choose>
						                
						                <!-- Percentage for Subject 
						                <td class="marksTableCell" style="text-align: center;">
						                    <c:choose>
						                        <c:when test="${subjectPercentage > 0}">
						                            <fmt:formatNumber type="number" maxFractionDigits="1" value="${subjectPercentage}" />%
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>-->
						                
						                <!-- Grade for Subject (based on percentage) -->
						                <td class="marksTableCell" style="text-align: left;">&emsp;
						                    ${subjectGrade}
						                </td>
						                
						                <!-- Remarks Column - Empty for now 
						                <td class="marksTableCell" style="text-align: left; vertical-align: top; padding: 8px; width: 15%;"></td>-->
						            </tr>
						        </c:forEach>
						        
						         <!-- Calculate grand percentage and grade -->
							        <c:set var="grandPercentagestring" value="0" />
							        
							        <c:set var="grandPercentage" value="0" />
									<c:if test="${grandTotalMaxMarksFromExamSummary > 0}">
									    <c:set var="grandPercentage" 
									           value="${Math.round(((grandTotalMarksObtainedFromExamSummary / grandTotalMaxMarksFromExamSummary) * 100) * 10) / 10.0}" />
									</c:if>
									
							        <c:if test="${grandTotalMaxMarksFromExamSummary > 0}">
							            <c:set var="grandPercentagestring">
									    <fmt:formatNumber value="${(grandTotalMarksObtainedFromExamSummary / grandTotalMaxMarksFromExamSummary) * 100}" maxFractionDigits="1" />
										</c:set>
							        </c:if>
							         
							         <c:set var="roundedMarks">
									            <fmt:formatNumber value="${grandTotalMarksObtainedFromExamSummary}" maxFractionDigits="0" />
									        </c:set>
									        
									        
									       <c:choose>
						                    <c:when test="${grandPercentage >= 90}">
						                        <c:set var="grandGrade" value="A+" />
						                    </c:when>
						                    <c:when test="${grandPercentage >= 75}">
						                        <c:set var="grandGrade" value="A" />
						                    </c:when>
						                    <c:when test="${grandPercentage >= 60}">
						                        <c:set var="grandGrade" value="B+" />
						                    </c:when>
						                    <c:when test="${grandPercentage >= 50}">
						                        <c:set var="grandGrade" value="B" />
						                    </c:when>
						                    <c:when test="${grandPercentage >= 30}">
						                        <c:set var="grandGrade" value="C+" />
						                    </c:when>
						                    <c:otherwise>
						                        <c:set var="grandGrade" value="C" />
						                    </c:otherwise>
						                </c:choose>
						                
									        
							        
							        <c:choose>
						                    <c:when test="${subjectPercentage >= 90}">
						                        <c:set var="subjectGrade" value="A+" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 75}">
						                        <c:set var="subjectGrade" value="A" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 60}">
						                        <c:set var="subjectGrade" value="B+" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 50}">
						                        <c:set var="subjectGrade" value="B" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 30}">
						                        <c:set var="subjectGrade" value="C+" />
						                    </c:when>
						                    <c:otherwise>
						                        <c:set var="subjectGrade" value="C" />
						                    </c:otherwise>
						                </c:choose>
						
						        <!-- Summary Section Rows within the same table 
						        <tr>
						            <td class="summaryTableHeader" style="width: 20%;">Summary</td>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <td class="marksTableCell">${exam.totalMarksObtained}</td>
						            </c:forEach>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						        </tr>
						
						        <tr>
						            <td class="summaryTableHeader">Total Marks Obtained</td>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <td class="marksTableCell">${exam.totalMarksObtained}</td>
						            </c:forEach>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						        </tr>
						
						        <tr>
						            <td class="summaryTableHeader">Total Marks</td>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <td class="marksTableCell">${exam.totalMarks}</td>
						            </c:forEach>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						        </tr>
						
						        <tr>
						            <td class="summaryTableHeader">Percentage</td>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <td class="marksTableCell">
						                    <c:choose>
						                        <c:when test="${exam.percentage > 0}">
						                            <fmt:formatNumber type="number" maxFractionDigits="1" value="${exam.percentage}" />%
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						        </tr>
						
						        <%-- <tr>
						            <td class="summaryTableHeader">Grade</td>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <td class="marksTableCell">
						                    <c:choose>
						                        <c:when test="${exam.grade != null && exam.grade != ''}">
						                            ${exam.grade}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						        </tr>
						
						        <tr>
						            <td class="summaryTableHeader">Rank</td>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <td class="marksTableCell">
						                    <c:choose>
						                        <c:when test="${exam.rank > 0}">
						                            ${exam.rank}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						            <td class="marksTableCell"></td>
						        </tr> --%>
						         -->
						           <!-- Grand Total Row -->
						           
						           		 <tr style="background-color: #f0f0f0; font-weight: bold;">
									        <td class="summaryTableHeader" style="width: 20%;">TOTAL</td>
									        <c:forEach items="${Parents.examSummaries}" var="exam">
									        	<c:choose>
						                                    <c:when test="${fn:contains(exam.examName, 'FA') and not showFullMarks}">
						                                       		 <c:choose>
						                                       		 	<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}">
						                                       		 		<td class="marksTableCell">
																	        <fmt:formatNumber value="${(exam.totalMarksObtained/60) * 30}" maxFractionDigits="1" /></td>
						                                       		 	</c:when>
						                                       		 	<c:when test="${dataSubParts[0]=='1' || dataSubParts[0]=='2' || dataSubParts[0]=='3' || dataSubParts[0]=='4' || dataSubParts[0]=='5'}">
						                                       		 		<td class="marksTableCell">
																	        <fmt:formatNumber value="${(exam.totalMarksObtained/100) * 50}" maxFractionDigits="1" /></td>
						                                       		 	</c:when>
																	    <c:otherwise>
																	        <td class="marksTableCell"><fmt:formatNumber value="${(exam.totalMarksObtained/120) * 60}" maxFractionDigits="1" /></td>
																	    </c:otherwise>
																	</c:choose>
						                                    </c:when>
						
						                                    <c:when test="${fn:contains(exam.examName, 'SA') and not showFullMarks}">
						                                    			<c:choose>
						                                       		 	<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}">
						                                       		 		<td class="marksTableCell">
																	        <fmt:formatNumber value="${(exam.totalMarksObtained/150) * 90}" maxFractionDigits="1" /></td>
						                                       		 	</c:when>
						                                       		 	<c:when test="${dataSubParts[0]=='1' || dataSubParts[0]=='2' || dataSubParts[0]=='3' || dataSubParts[0]=='4' || dataSubParts[0]=='5'}">
						                                       		 		<td class="marksTableCell">
																	        <fmt:formatNumber value="${(exam.totalMarksObtained/250) * 150}" maxFractionDigits="1" /></td>
						                                       		 	</c:when>
																	    <c:otherwise>
																	        <td class="marksTableCell"><fmt:formatNumber value="${(exam.totalMarksObtained/300) * 180}" maxFractionDigits="1" /></td>
																	    </c:otherwise>
																	</c:choose>
						                                    </c:when>
						
						                                    <c:otherwise>
						                                        <td class="marksTableCell">${exam.totalMarksObtained}</td>
						                                    </c:otherwise>
						                                </c:choose>
									        </c:forEach>
									        
									        <td class="amount" style="display: none;">${roundedMarks}</td>
									        
																		        <td class="marksTableCell" style="text-align: center;">
													            <fmt:formatNumber value="${grandTotalMaxMarksFromExamSummary}" maxFractionDigits="0" />
																		        </td>
																		        <!-- Percentage column for non-6-exam layout; Obt. Marks column for 6-exam layout -->
																		        <c:choose>
																		            <c:when test="${fn:length(Parents.examSummaries) == 6}">
																		                <td class="marksTableCell" style="text-align: center;">${roundedMarks}</td>
																		            </c:when>
																		            <c:otherwise>
																		                <td class="marksTableCell" style="text-align: center;">
																		                    <c:choose>
																		                        <c:when test="${grandPercentage > 0}">
																		                            <fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentage}" />%
																		                        </c:when>
																		                        <c:otherwise>-</c:otherwise>
																		                    </c:choose>
																		                </td>
																		            </c:otherwise>
																		        </c:choose>
									        
									        <%-- <td class="marksTableCell" style="text-align: center;">
								                <c:choose>
								                    <c:when test="${grandPercentage > 0}">
								                        <fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentage}" />%
								                    </c:when>
								                    <c:otherwise>-</c:otherwise>
								                </c:choose>
								            </td> --%>
								            
									        <td class="marksTableCell" style="text-align: left;">
									            &emsp;&nbsp;${grandGrade}
									        </td>
									    </tr>
						    </tbody>
						</table>

			</c:otherwise>
			</c:choose>

		</div>


		<div class="totalbox">

			<div class="words">
				<b>Total Marks Obtained (In Words) <br><label class="amountWords"></label>&nbsp; only</b>
			</div>

			<div class="percentage">Percentage <br><fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentagestring}" />%</div>

		</div>

		<c:set var="excludedWithGradeCount" value="0" />
		<c:forEach items="${Parents.excludedSubjectGrades}" var="excludedEntry">
			<c:if test="${not empty excludedEntry.value}">
				<c:set var="excludedWithGradeCount" value="${excludedWithGradeCount + 1}" />
			</c:if>
		</c:forEach>

		<c:if test="${excludedWithGradeCount > 0}">
			<div class="marks" style="margin-top: 15px;">
				<table class="excluded-subjects-table" style="border-collapse: collapse; width: 100%;">
					<thead>
						<tr>
							<th class="marksTableHeader" style="text-transform: uppercase; width: 70%;">Subject Name</th>
							<th class="marksTableHeader" style="text-transform: uppercase; width: 30%;">Grade</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${Parents.excludedSubjectGrades}" var="excludedEntry">
							<c:if test="${not empty excludedEntry.value}">
								<tr>
									<td class="marksTableCellLeft" style="text-transform: capitalize;"><c:out value="${excludedEntry.key}" /></td>
									<td class="marksTableCell"><c:out value="${excludedEntry.value}" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		
		</div>

            <!-- RIGHT COLUMN FOR GRAPH -->
            <c:if test="${fn:length(Parents.examSummaries) == 1}">
                <div class="graph-right-column">
					<div id="graph-scope-${studentGraphKey}" class="marks" style="margin-top: 20px;">
                        <c:forEach items="${Parents.examsDetails}" var="examDetailsGraph" varStatus="status">
			  <div id="student-chart-${studentGraphKey}-${status.index}" class="student-exam-chart" data-subjects="${fn:escapeXml(examDetailsGraph.subjects)}" data-marks="${fn:escapeXml(examDetailsGraph.marks)}" data-exam="${fn:escapeXml(examDetailsGraph.examName)}" data-max="${maxMarks}" style="height: 380px; width: 100%;"></div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
        <!-- END OF FLEX CONTAINER -->

	<div class="marks" style="font-size: 19px;">
		<c:choose>
		
			<c:when test="${fn:length(Parents.examSummaries) == 1}">
                <!-- Graph already handled above in right column -->
			</c:when>
			
			<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}">
				<div style="margin-top: 50px;">
				<h4 style="text-align:center ;margin-bottom:0px ;padding-bottom: 10px;">PART-B</h4>
				</div>
				<div style="display: flex; gap: 10px;" class="partb">
				    <table style="width: 50%; border-collapse: collapse;">
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <th>Co-Scholastic Subjects</th>
				            <th>Grade</th>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td>Physical & Health Education</td>
				            <td>A</td>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td>Art Education</td>
				            <td>A</td>
				        </tr>
				    </table>
				
				    <table style="width: 50%; border-collapse: collapse;font-weight: bold;">
				        <tr style="text-transform: uppercase;">
				        	<c:set var="attendanceParts" value="${fn:split(Parents.parents.student.urbanrural, '/')}" />
							<c:set var="sem1" value="${attendanceParts[0]}" />
							<c:set var="sem2" value="${attendanceParts[1]}" />
				            <th>Attendance</th>
				            <th>Semester-1</th>
				            <th>Semester-2</th>
				            <th>Total</th>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td>Total Working Days</td>
				            <td>118</td>
				            <td>126</td>
				            <td>244</td>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td>Total Present Days</td>
				            <td>${sem1}</td>
				            <td>${sem2}</td>
				            <td>${sem1+sem2}</td>
				        </tr>
				    </table>
				
				</div>
			</c:when>
			<c:otherwise>
			
			<div>
			<h4 style="text-align:center ;margin-bottom:0px ;padding-bottom: 10px;">PART-B</h4>
			</div>
			
			<div style="display: flex; gap: 10px;" class="partb">
				    <table style="width: 50%; border-collapse: collapse;font-weight: bold;">
				        <tr style="text-transform: uppercase;">
				            <th style="font-size:14px;">Co-Scholastic Subjects</th>
				            <th style="font-size:14px;">Grade</th>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td style="font-size:14px;">Physical & Health Education</td>
				            <td style="font-size:14px;">A</td>
				        </tr>
				        
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td style="font-size:14px;">ATTITUDE & VALUES</td>
				            <td style="font-size:14px;">A</td>
				        </tr>
				        
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td style="font-size:14px;">Work Experience</td>
				            <td style="font-size:14px;">A</td>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td style="font-size:14px;">Art Education</td>
				            <td style="font-size:14px;">A</td>
				        </tr>
				    </table>
				
				    <table style="width: 50%; border-collapse: collapse;font-weight: bold;">
				        <tr style="text-transform: uppercase;">
				        	<c:set var="attendanceParts" value="${fn:split(Parents.parents.student.urbanrural, '/')}" />
							<c:set var="sem1" value="${attendanceParts[0]}" />
							<c:set var="sem2" value="${attendanceParts[1]}" />
				            <th style="font-size:14px;">Attendance</th>
				            <th style="font-size:14px;">Semester-1</th>
				            <th style="font-size:14px;">Semester-2</th>
				            <th style="font-size:14px;">Total</th>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td style="font-size:14px;">Total Working Days</td>
				            <td style="font-size:14px;">118</td>
				            <td style="font-size:14px;">126</td>
				            <td style="font-size:14px;">244</td>
				        </tr>
				
				        <tr style="text-transform: uppercase;font-weight: bold;">
				            <td style="font-size:14px;">Total Present Days</td>
				            <td style="font-size:14px;">${sem1}</td>
				            <td style="font-size:14px;">${sem2}</td>
				            <td style="font-size:14px;">${sem1+sem2}</td>
				        </tr>
				    </table>
				
				</div></c:otherwise>
		</c:choose>
</div>
	<!-- <div class="footer">
    <img src="/patriswamy/images/hmsign.png" class="signature" width="200" height="60"/>

    <div class="headmaster">H.M.</div>

    <label class="school-info">
       Little FLower Public School<br>
        Serikar Layout, Shivpur Road, Basavakalyan-585327, DIST. BIDAR<br>
        DISE CODE:00000000000,SSLC CODE:SS0000&nbsp;&nbsp;&nbsp;
    </label>
</div> -->

<!-- Signature Table -->
<div style="margin-top: 50px;">
    <table style="width: 100%; border-collapse: collapse; border: 1px solid #000;">
>>>>>>> 7fc6c7b92... 1. Exclude subjects in graph
        <tr>
            <td class="cell">${s.index + 1}</td>
            <td class="cell">${Parents.parents.student.studentexternalid}</td>
            <td class="cell left">${Parents.parents.student.name}</td>
            <td class="cell left">${Parents.parents.fathersname}</td>

           

            <c:forEach items="${Parents.subjectSummaries}" var="sub">
    <td class="cell">
        <fmt:formatNumber value="${sub.totalMarksObtained}" maxFractionDigits="0"/>
        /
        <fmt:formatNumber value="${sub.maxMarks}" maxFractionDigits="0"/>
    </td>
</c:forEach>
            
            <td class="cell">
            
                <fmt:formatNumber value="${Parents.examSummaries[0].totalMarksObtained}" maxFractionDigits="0"/> 
            </td>
            <td class="cell">
                <fmt:formatNumber value="${Parents.examSummaries[0].totalMarks}" maxFractionDigits="0"/>
            </td>
            <td class="cell">
                <fmt:formatNumber value="${Parents.examSummaries[0].percentage}" maxFractionDigits="1"/>
            </td>
            <td class="cell last-col">${Parents.examSummaries[0].rank}</td>
        </tr>
    </c:forEach>

</table>

<br><br>

<!-- ===== SIGNATURE ===== -->
<table width="100%">
    <tr>
        <td align="left">Class Teacher</td>
        <td align="center">Parent</td>
        <td align="right">
            <img src="/patriswamy/images/principalsignature.png" width="60" height="28"><br>
            Principal
        </td>
    </tr>
</table>

</body>
</html>