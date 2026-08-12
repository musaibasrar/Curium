<%-- 
    Document   : Marks Sheet
    Created on : Nov 29 2021, 09:22 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ArrayList,java.util.Collections,java.util.Comparator,java.util.List,java.util.regex.Matcher,java.util.regex.Pattern,java.lang.reflect.Method" %>

<%!
private int extractFirstNumber(String value) {
	if (value == null) {
		return Integer.MAX_VALUE;
	}
	Matcher matcher = Pattern.compile("(\\d+)").matcher(value);
	return matcher.find() ? Integer.parseInt(matcher.group(1)) : Integer.MAX_VALUE;
}

private String examNameOf(Object examSummary) {
	if (examSummary == null) {
		return "";
	}
	try {
		Method method = examSummary.getClass().getMethod("getExamName");
		Object examName = method.invoke(examSummary);
		return examName == null ? "" : examName.toString().trim();
	} catch (Exception e) {
		return "";
	}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Progress Report</title>
<link href="https://fonts.googleapis.com/css2?family=Alex+Brush&display=swap" rel="stylesheet">
<script src="/patriswamy/js/echarts/echarts.min.js"></script>
<style>
@page {
	size: A4 portrait;
	margin: 8mm;
}

body {
	font-family: "Times New Roman", Times, serif;
	background: #f5f5f5;
	margin: 0;
	padding: 0;
}

.card-wrapper {
	box-sizing: border-box;
	page-break-inside: avoid;
	margin-bottom: 10px;
}

.card-wrapper.page-break {
	page-break-after: always;
	margin-bottom: 0;
}

.container {
	width: 100%;
	max-width: 780px;
	margin: auto;
	background: white;
	border: 3px solid #2c8a6b;
	padding: 6px;
	border-style: double;
	box-sizing: border-box;
}

.topline {
	font-size: 10px;
	display: flex;
	justify-content: space-between;
}

.header {
	margin-bottom: 4px;
}

.schoolbox {
	display: flex;
	align-items: center;
	justify-content: center;
	gap: 12px;
	margin-top: 2px;
}

.logo-img {
	height: 50px;
	width: auto;
}

.schoolname {
	background: #ffffff;
	text-align: center;
}

.logo {
	width: 60px;
	height: 65px;
}

.studentphoto {
	width: 75px;
	text-align: right;
}

.schoolname h2, .schoolname h3 {
	margin: 0;
}

.title {
	text-align: center;
}

.title h2 {
	border: 1.5px solid black;
	display: inline-block;
	padding: 2px 10px;
	border-radius: 6px; 
	font-size: 16px;
	margin-bottom: 4px;
	margin-top: 4px;
  	outline: 1.5px solid black;
  	outline-offset: 3px;
}

.studentinfo {
	margin-top: 4px;
}

.studentinfo table {
	width: 100%;
	border-collapse: collapse;
}

.studentinfo td {
	padding: 2px 4px;
	font-size: 11px;
}

.marks {
	margin-top: 4px;
}

.marks table {
	width: 100%;
	border-collapse: collapse;
}

.marks th, .marks td {
	border: 1px solid #000;
	text-align: center;
	padding: 3px;
	font-size: 11px;
}

.totalbox {
	margin-top: 8px;
	display: flex;
	justify-content: space-between;
}

.words {
	border: 1px solid #000;
	padding: 4px 6px;
	width: 70%;
	background: #f4d5a6;
	border-radius: 4px;
	text-transform: uppercase;
	font-size: 10px;
}

.percentage {
	border: 1px solid #000;
	padding: 4px 6px;
	width: 25%;
	background: #e3a83d;
	text-align: center;
	font-weight: bold;
	border-radius: 4px;
	text-transform: uppercase;
	font-size: 10px;
}

.partb {
	margin-top: 0px;
}

.partb table {
	width: 100%;
	border-collapse: collapse;
}

.partb th, .partb td {
	border: 1px solid #000;
	padding: 3px;
	font-size: 10px;
	text-align: center;
}

.footer {
    text-align: right;
    margin-top: 10px;
    font-weight: bold;
    color: blue;
}

.signature {
    display: block;
    margin-left: auto;
    margin-bottom: -5px;
}

.headmaster {
    padding-right: 60px;
}

.school-info {
    font-weight: 700;
    font-size: 9px;
}

.printbtn {
	text-align: center;
	margin-top: 15px;
	margin-bottom: 15px;
}

.cce {
	font-family: "Alex Brush", cursive;
	font-size: 16px;
	color: #1975d0;
	font-weight: bold;
}

.certifyline {
	text-align: center;
	font-size: 11px;
	margin-top: 3px;
	margin-bottom: 4px;
	font-style: italic;
	padding-left: 0px;
	padding-right: 0px;
}

.marksTableHeader {
	background-color: #f2f2f2;
	font-weight: bold;
	border: 1px solid black;
	padding: 4px;
	text-align: center;
	font-size: 11px;
}

.marksTableCell {
	border: 1px solid black;
	padding: 3px 4px;
	text-align: center;
	font-weight: bold;
	font-size: 11px;
}

.marksTableCellLeft {
	border: 1px solid black;
	padding: 3px 4px;
	text-align: left;
	font-weight: bold;
	font-size: 11px;
}

.summaryTableHeader {
	background-color: #f9f9f9;
	font-weight: bold;
	border: 1px solid black;
	padding: 4px;
	text-align: left;
	font-size: 11px;
}

.flex-row-container {
    display: flex;
    gap: 8px;
    align-items: flex-start;
    justify-content: space-between;
}

.marks-left-column {
    flex: 1;
}

.graph-right-column {
    width: 220px;
    flex-shrink: 0;
}

.sig-table td {
	border: 1px solid #000;
	padding: 20px 5px 5px 5px;
	text-align: center;
	width: 33.33%;
	font-weight: bold;
	font-size: 11px;
}

@media print {
	.printbtn {
		display: none;
	}
	body {
		background: white;
	}
	.container {
		border-width: 2px;
	}
}
</style>

<script type="text/javascript">
        function convertNumberToWords(num) {
            const ones = ['', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
            const teens = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
            const tens = ['', '', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];
            
            if (num === 0) return 'Zero';
            
            function convertBelowThousand(n) {
                let result = '';
                if (Math.floor(n / 100) > 0) {
                    result = ones[Math.floor(n / 100)] + ' Hundred';
                }
                n = n % 100;
                if (n >= 10 && n < 20) {
                    if (result) result += ' ';
                    result += teens[n - 10];
                } else {
                    if (Math.floor(n / 10) > 0) {
                        if (result) result += ' ';
                        result += tens[Math.floor(n / 10)];
                    }
                    if (n % 10 > 0) {
                        if (result) result += ' ';
                        result += ones[n % 10];
                    }
                }
                return result;
            }
            
            let crore = Math.floor(num / 10000000);
            let croreRemainder = num % 10000000;
            let lakh = Math.floor(croreRemainder / 100000);
            let lakhRemainder = croreRemainder % 100000;
            let thousand = Math.floor(lakhRemainder / 1000);
            let remainder = lakhRemainder % 1000;
            
            let result = '';
            if (crore > 0) result += convertBelowThousand(crore) + ' Crore';
            if (lakh > 0) {
                if (result) result += ' ';
                result += convertBelowThousand(lakh) + ' Lakh';
            }
            if (thousand > 0) {
                if (result) result += ' ';
                result += convertBelowThousand(thousand) + ' Thousand';
            }
            if (remainder > 0) {
                if (result) result += ' ';
                result += convertBelowThousand(remainder);
            }
            return result.trim();
        }

        window.onload = function () {
            let amounts = document.querySelectorAll(".amount");
            let wordsCells = document.querySelectorAll(".amountWords");

            for (let i = 0; i < amounts.length && i < wordsCells.length; i++) {
                let value = amounts[i].innerText.trim();
                
                if (value && !isNaN(value)) {
                    try {
                        let intValue = Math.floor(Number(value));
                        let words = convertNumberToWords(intValue);
                        wordsCells[i].innerText = words + "";
                    } catch (e) {
                        wordsCells[i].innerText = "Error";
                    }
                }
            }
        };
</script>

</head>

<body>

<c:forEach items="${markssheetlist}" var="Parents" varStatus="studentStatus">
	<c:set var="studentGraphKey" value="s${studentStatus.index}_${Parents.parents.student.sid}" />
	<%
	Object parentBean = pageContext.getAttribute("Parents");
	List examSummariesRaw = Collections.emptyList();
	try {
		if (parentBean != null) {
			Method getExamSummaries = parentBean.getClass().getMethod("getExamSummaries");
			Object examValue = getExamSummaries.invoke(parentBean);
			if (examValue instanceof List) {
				examSummariesRaw = (List) examValue;
			}
		}
	} catch (Exception ignored) {
	}

	List sortedExamSummaries = new ArrayList(examSummariesRaw);
	Collections.sort(sortedExamSummaries, new Comparator() {
		@Override
		public int compare(Object left, Object right) {
			String leftName = examNameOf(left);
			String rightName = examNameOf(right);
			int leftNumber = extractFirstNumber(leftName);
			int rightNumber = extractFirstNumber(rightName);
			if (leftNumber != rightNumber) {
				return Integer.compare(leftNumber, rightNumber);
			}
			return leftName.compareToIgnoreCase(rightName);
		}
	});
	pageContext.setAttribute("sortedExamSummaries", sortedExamSummaries);
	%>
		
	<div class="card-wrapper ${studentStatus.index % 2 == 1 ? 'page-break' : ''}">   

	<div class="container">

		<div class="header">
	<div class="schoolbox">
		<img border="0" class="logo-img" alt="logo" src="/patriswamy/images/patriswamy.png">
		<div class="schoolname">
			<h3 style="font-size: 24px;color: #971d1d;">${branchname}</h3>
			<div>
				<b><label style="font-size:12px;text-transform: uppercase;">${branchaddress}</label></b>
			</div>
		</div>
	</div>
</div>

		<div class="title">
			<h2><label style="font-size:16px;text-transform: uppercase;">Achievement Record</label></h2>
			<div style="font-size: 13px;font-weight:bold;">
			<c:set var="yearParts" value="${fn:split(currentAcademicYear, '/')}" />
			<c:set var="startYear" value="${yearParts[0]}" />
			<c:set var="endYear" value="${startYear + 1}" />

			Academic Year ${startYear}-${endYear}</div>
		</div>

		<div class="studentinfo">
			<table>
				<tr style="border: 1px solid black; border-collapse: collapse;">
					<td style="border: 1px solid black; border-collapse: collapse; font-size: 11px;"><b>SATS No : <c:out value="${Parents.parents.student.sts}" /></b></td>
					<td style="border: 1px solid black; border-collapse: collapse; font-size: 11px;"><b>Roll No : <c:out value="${Parents.parents.student.admissionnumber}" /></b></td>
					<td style="border: 1px solid black; border-collapse: collapse; font-size: 11px;"><b>Class : <c:set var="dataSubParts11" value="${fn:split(Parents.parents.student.classstudying,'--')}" />${dataSubParts11[0]}</b></td>
					<td style="border: 1px solid black; border-collapse: collapse; font-size: 11px;"><b>Section : <c:set var="dataSubParts112" value="${fn:split(Parents.parents.student.classstudying,'--')}" />${dataSubParts112[1]}</b></td>
				</tr>
				<tr>
					<td colspan="3" style="font-size: 12px; padding-top: 4px;"><b>Student's Name : <label style="text-transform: uppercase;"><c:out value="${Parents.parents.student.name}" /></label></b></td>
					<td rowspan="3" align="center">
						<div class="studentphoto">
							<img src="data:image;base64,<c:out value="${Parents.parents.student.studentpic}"/>" width="70" height="70">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="font-size: 12px;"><b>Father's Name : <label style="text-transform: uppercase;"><c:out value="${Parents.parents.fathersname}" /></label></b></td>
				</tr>
				<tr>
					<td colspan="3" style="font-size: 12px;"><b>Mother's Name : <label style="text-transform: uppercase;"><c:out value="${Parents.parents.mothersname}" /></label></b></td>
				</tr>
			</table>
		</div>

 		<!-- FLEX CONTAINER WRAPPER -->
        <div class="flex-row-container">
        <div class="marks-left-column">
		<div class="marks">
		<c:choose>

			<%-- 2 or more exams -> dynamic transposed table --%>
			<c:when test="${fn:length(Parents.examSummaries) > 1}">

			<c:set var="grandTotalMarksObtainedFromExamSummary" value="0" />
			<c:set var="grandTotalMaxMarksFromExamSummary" value="0" />
			<c:forEach items="${sortedExamSummaries}" var="examSummaryTotal">
				<c:set var="grandTotalMarksObtainedFromExamSummary" value="${grandTotalMarksObtainedFromExamSummary + examSummaryTotal.totalMarksObtained}" />
				<c:set var="grandTotalMaxMarksFromExamSummary" value="${grandTotalMaxMarksFromExamSummary + examSummaryTotal.totalMarks}" />
			</c:forEach>

			<c:set var="grandPercentage" value="0" />
			<c:set var="grandPercentagestring" value="0" />
			<c:if test="${grandTotalMaxMarksFromExamSummary > 0}">
				<c:set var="grandPercentage" value="${Math.round(((grandTotalMarksObtainedFromExamSummary / grandTotalMaxMarksFromExamSummary) * 100) * 10) / 10.0}" />
				<c:set var="grandPercentagestring">
					<fmt:formatNumber value="${(grandTotalMarksObtainedFromExamSummary / grandTotalMaxMarksFromExamSummary) * 100}" maxFractionDigits="1" />
				</c:set>
			</c:if>
			<c:set var="roundedMarks">
				<fmt:formatNumber value="${grandTotalMarksObtainedFromExamSummary}" maxFractionDigits="0" />
			</c:set>

			<table style="border-collapse: collapse; width: 100%; margin-top: 8px;">
				<thead>
					<tr>
						<th class="marksTableHeader" style="text-align: center; width: 20%; text-transform: uppercase;">Exams</th>
						<c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry">
							<th class="marksTableHeader" style="text-transform: capitalize;"><c:out value="${subjectEntry.key}" /></th>
						</c:forEach>
						<th class="marksTableHeader" style="text-transform: uppercase;">Total</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach items="${sortedExamSummaries}" var="exam">
						<tr>
							<td class="marksTableCellLeft" style="width: 20%; text-transform: uppercase;"><c:out value="${exam.examName}" /></td>

							<c:set var="examRowTotal" value="0" />
							<c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry">
								<c:set var="markStr" value="${subjectEntry.value[exam.examName]}" />
								<td class="marksTableCell">
									<c:choose>
										<c:when test="${empty markStr || markStr == '-'}">-</c:when>
										<c:otherwise>
											<c:set var="parts" value="${fn:split(markStr, '/')}" />
											<c:set var="secured" value="${parts[0]}" />
											<c:choose>
												<c:when test="${secured == '999'}">A</c:when>
												<c:when test="${secured == null || secured == '' || secured == 'AB'}"><c:out value="${secured}" /></c:when>
												<c:otherwise>
													<c:out value="${secured}" />
													<c:set var="examRowTotal" value="${examRowTotal + secured}" />
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
							</c:forEach>

							<td class="marksTableCell" style="font-weight: bold;">
								<fmt:formatNumber value="${examRowTotal}" maxFractionDigits="1" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<span class="amount" style="display: none;">${roundedMarks}</span>

		</c:when>

		<%-- Standard 1-exam layout --%>
		<c:otherwise>
			 <c:choose>
		        <c:when test="${fn:length(Parents.examSummaries) == 6}">
		        	<h4 style="text-align: center; margin: 2px 0;">PART-A</h4>
		        </c:when>
		     </c:choose>
			
						<table style="border-collapse: collapse; width: 100%; margin-top: 8px;">
						    <thead>
						    <c:choose>
						        <c:when test="${fn:length(Parents.examSummaries) == 6}">
						        	<tr>
										<th rowspan="2" style="text-transform: uppercase;">Subjects</th>
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
										<c:forEach items="${sortedExamSummaries}" var="exam">
						                	<th class="marksTableHeader" style="text-transform: uppercase;" colspan="5"><c:out value="${exam.examName}" /></th>
						            	</c:forEach>
						        	</tr>
						        	<tr>
							            <th class="marksTableHeader" style="text-align: center; width: 20%;text-transform: uppercase;"> Scholastic Subject</th>
							            <th class="marksTableHeader" style="text-align: center; width: 12%;">Obtained Marks</th>
							            <th class="marksTableHeader" style="text-align: center; width: 12%;">Max. Marks</th>
							            <th class="marksTableHeader" style="text-align: center; width: 12%;">Percentage</th>
							            <!-- <th class="marksTableHeader" style="text-align: center; width: 10%;">Grade</th> -->
							        </tr>      
						        </c:otherwise>
						      </c:choose>
						    </thead>
						    <tbody>
								        <c:set var="grandTotalMarksObtained" value="0" />
								        <c:set var="grandTotalMaxMarks" value="0" />
								        <c:set var="englishexam" value="" />
								        <c:set var="grandTotalMarksObtainedFromExamSummary" value="0" />
								        <c:set var="grandTotalMaxMarksFromExamSummary" value="0" />
										<c:forEach items="${sortedExamSummaries}" var="examSummaryTotal">
								        	<c:set var="grandTotalMarksObtainedFromExamSummary" value="${grandTotalMarksObtainedFromExamSummary + examSummaryTotal.totalMarksObtained}" />
								        	<c:set var="grandTotalMaxMarksFromExamSummary" value="${grandTotalMaxMarksFromExamSummary + examSummaryTotal.totalMarks}" />
								        </c:forEach>
						        <c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry" varStatus="status">
						            <tr>
						                <td class="marksTableCellLeft" style="width: 20%;text-transform: capitalize;"><c:out value="${subjectEntry.key}" /></td>
						                
						                <c:set var="subjectTotalMarksObtained" value="0" />
						                <c:set var="subjectTotalMaxMarks" value="0" />
						                
										<c:forEach items="${sortedExamSummaries}" var="exam">
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
						                                <c:set var="maxMarks" value="${fn:trim(fn:substringBefore(maxPart, ' '))}" />
						                                <c:if test="${empty maxMarks}">
						                                    <c:set var="maxMarks" value="${fn:trim(fn:substringBefore(maxPart, '('))}" />
						                                </c:if>
						                                <c:if test="${empty maxMarks}">
						                                    <c:set var="maxMarks" value="${maxPart}" />
						                                </c:if>
						
						                                <c:choose>
															<c:when test="${fn:contains(exam.examName, 'FA') and not showFullMarks}">
						                                        <c:choose>
						                                            <c:when test="${secured != null && secured != '' && secured != 'AB' && secured != '999'}">
						                                                <c:set var="displayMarks" value="${(secured / 20) * 10}" />
						                                                <fmt:formatNumber value="${displayMarks}" maxFractionDigits="1" />
						                                                <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                                <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 10}" />
						                                            </c:when>
						                                            <c:otherwise>
						                                                <c:choose>
						                                                    <c:when test="${secured == '999'}">A</c:when>
						                                                    <c:otherwise><c:out value="${secured}" /></c:otherwise>
						                                                </c:choose>
						                                            </c:otherwise>
						                                        </c:choose>
						                                    </c:when>
						
						                                    <c:when test="${fn:contains(exam.examName, 'SA') and not showFullMarks}">
						                                        <c:choose>
						                                            <c:when test="${secured != null && secured != '' && secured != 'AB' && secured != '999'}">
						                                                <c:set var="displayMarks" value="${(secured / 50) * 30}" />
						                                                <fmt:formatNumber value="${displayMarks}" maxFractionDigits="1" />
						                                                <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                                <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 30}" />
						                                            </c:when>
						                                            <c:otherwise>
						                                                <c:choose>
						                                                    <c:when test="${secured == '999'}">A</c:when>
						                                                    <c:otherwise><c:out value="${secured}" /></c:otherwise>
						                                                </c:choose>
						                                            </c:otherwise>
						                                        </c:choose>
						                                    </c:when>
						
						                                    <c:otherwise>
						                                        <c:choose>
						                                            <c:when test="${secured != null && secured != '' && secured != 'AB' && secured != '999'}">
						                                                <c:out value="${secured}" />
						                                                <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + secured}" />
						                                                <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + maxMarks}" />
						                                            </c:when>
						                                            <c:otherwise>
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
						                
						                <c:set var="subjectPercentage" value="0" />
						                <c:if test="${subjectTotalMaxMarks > 0}">
						                    <c:set var="subjectPercentage" value="${(subjectTotalMarksObtained / subjectTotalMaxMarks) * 100}" />
						                </c:if>
						                
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
						                
                						<c:set var="grandTotalMarksObtained" value="${grandTotalMarksObtained + subjectTotalMarksObtained}" />
                						<c:set var="grandTotalMaxMarks" value="${grandTotalMaxMarks + subjectTotalMaxMarks}" />
                
						                <td class="marksTableCell" style="text-align: center;">
						                    <fmt:formatNumber value="${subjectTotalMaxMarks}" maxFractionDigits="0" />
						                </td>
						                
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
						                
						                <%-- <td class="marksTableCell" style="text-align: center;">
						                    ${subjectGrade}
						                </td> --%>
						            </tr>
						        </c:forEach>
						        
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
						                
								    <tr style="background-color: #f0f0f0; font-weight: bold;">
								        <td class="summaryTableHeader" style="width: 20%;">TOTAL</td>
											<c:forEach items="${sortedExamSummaries}" var="exam">
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
								            ${grandGrade}
								        </td> --%>
								    </tr>
						    </tbody>
						</table>

		</c:otherwise>
		</c:choose>

		</div>

		<c:if test="${fn:length(Parents.examSummaries) == 1}">
		<div class="totalbox">
			<div class="words">
				<b>Total Marks Obtained (In Words): <label class="amountWords"></label>&nbsp; only</b>
			</div>
			<div class="percentage">Percentage: <fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentagestring}" />%</div>
		</div>
		</c:if>
		
		</div>

            <!-- RIGHT COLUMN FOR GRAPH -->
            <c:if test="${fn:length(Parents.examSummaries) == 1}">
                <div class="graph-right-column">
					<div id="graph-scope-${studentGraphKey}" class="marks" style="margin-top: 8px;">
                        <c:forEach items="${Parents.examsDetails}" var="examDetailsGraph" varStatus="status">
			  				<div id="student-chart-${studentGraphKey}-${status.index}" class="student-exam-chart" data-subjects="${fn:escapeXml(examDetailsGraph.subjects)}" data-marks="${fn:escapeXml(examDetailsGraph.marks)}" data-exam="${fn:escapeXml(examDetailsGraph.examName)}" data-max="${maxMarks}" style="height: 220px; width: 100%;"></div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
        <!-- END OF FLEX CONTAINER -->

<!-- Signature Table -->
<div style="margin-top: 15px;">
    <table class="sig-table" style="width: 100%; border-collapse: collapse; border: 1px solid #000;">
        <tr>
            <td>Signature of Principal</td>
            <td>Signature of Class Teacher</td>
            <td>Signature of Parents</td>
        </tr>
        <tr>
            <td colspan="3" style="border: 1px solid #000; padding: 4px; font-weight: bold; font-size: 10px; text-align: left;">
                REMARK : <span style="font-weight: normal; margin-left: 10px;"></span>
            </td>
        </tr>
    </table>
</div>

	</div>
	</div>

	</c:forEach>

	<div class="printbtn">
		<button onclick="window.print()" style="padding: 6px 16px; font-weight: bold; font-size: 14px; cursor: pointer;">Print Marks Cards</button>
	</div>
	
	<script>
	(function () {
	    var chartElements = document.querySelectorAll('.student-exam-chart');
	    if (!chartElements || chartElements.length === 0) {
	        return;
	    }

	    var palettePairs = [
	        ["#ff6b6b", "#c0392b"],
	        ["#4285f4", "#1d4ed8"],
	        ["#34d399", "#0f766e"],
	        ["#fbbf24", "#ca8a04"],
	        ["#a78bfa", "#7c3aed"],
	        ["#38bdf8", "#0369a1"],
	        ["#f472b6", "#db2777"],
	        ["#4ade80", "#16a34a"]
	    ];

	    function parseJsonArray(raw) {
	        var value = (raw || '').trim();
	        if (!value) return [];
	        try {
	            return JSON.parse(value);
	        } catch (e) {
	            return [];
	        }
	    }

	    for (var i = 0; i < chartElements.length; i++) {
	        var container = chartElements[i];
	        if (!container) continue;

	        var subjectNames = parseJsonArray(container.getAttribute('data-subjects'));
	        var marksObtained = parseJsonArray(container.getAttribute('data-marks'));
	        var rawMax = (container.getAttribute('data-max') || '').trim();
	        var maxMarksVal = parseFloat(rawMax) || 100;

	        var rawExam = (container.getAttribute('data-exam') || '').trim();
	        var examName = '';
	        try {
	            examName = JSON.parse(rawExam);
	        } catch (e) {
	            examName = rawExam;
	        }
	        examName = String(examName).toUpperCase();

	        var barData = marksObtained.map(function (val, idx) {
	            var pair = palettePairs[idx % palettePairs.length];
	            var markValue = Number(val);
	            if (isNaN(markValue) || markValue > 700) {
	                markValue = 0;
	            }
	            return {
	                value: markValue,
	                itemStyle: {
	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
	                        { offset: 0, color: pair[0] },
	                        { offset: 1, color: pair[1] }
	                    ]),
	                    shadowBlur: 4,
	                    shadowColor: 'rgba(15,23,42,0.18)',
	                    shadowOffsetY: 3,
	                    borderRadius: [4, 4, 0, 0]
	                }
	            };
	        });

	        var chart = echarts.init(container);
	        chart.setOption({
	            animationDuration: 800,
	            animationEasing: 'cubicOut',
	            title: {
	                text: examName,
	                left: 'center',
	                top: 2,
	                textStyle: {
	                    fontSize: 11,
	                    fontWeight: 'bold',
	                    color: '#1e293b'
	                }
	            },
	            tooltip: {
	                trigger: 'axis',
	                axisPointer: { type: 'shadow' }
	            },
	            grid: {
	                left: 30,
	                right: 10,
	                top: 30,
	                bottom: 40,
	                containLabel: true
	            },
	            xAxis: {
	                type: 'category',
	                data: subjectNames,
	                axisLabel: {
	                    interval: 0,
	                    rotate: subjectNames.length > 5 ? 35 : 0,
	                    color: '#334155',
	                    fontSize: 9
	                },
	                axisLine: { lineStyle: { color: 'rgba(15,23,42,0.20)' } }
	            },
	            yAxis: {
	                type: 'value',
	                min: 0,
	                max: maxMarksVal,
	                axisLabel: { color: '#334155', fontSize: 9 },
	                splitLine: { lineStyle: { color: 'rgba(15,23,42,0.08)' } }
	            },
	            series: [{
	                name: 'Marks',
	                type: 'bar',
	                barMaxWidth: 24,
	                data: barData,
	                label: {
	                    show: true,
	                    position: 'top',
	                    color: '#0f172a',
	                    fontWeight: 'bold',
	                    fontSize: 9
	                }
	            }]
	        });
	    }
	})();
	</script>
	
</body>
</html>