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

<html>
<head>
<title>Progress Report</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background: #f5f5f5;
}

.container {
	width: 800px;
	margin: auto;
	background: white;
	border: 5px solid #2c8a6b;
	padding: 10px;
	border-style: double;
}

.header {
	border-bottom: 2px solid #000;
}

.topline {
	font-size: 12px;
	display: flex;
	justify-content: space-between;
}

.schoolbox {
	display: flex;
	align-items: center;
	margin-top: 5px;
}

.logo {
	width: 90px;
	height: 90px;
	border: 1px solid #aaa;
}

.schoolname {
	background: #e0a321;
	flex: 1;
	text-align: center;
	height: 90px;
}

.studentphoto {
	width: 110px;
	text-align: right;
}

.schoolname h2 {
	margin: 0;
}

.schoolname h3 {
	margin: 0;
}

.title {
	text-align: center;
	margin-top: 10px;
}

.title h2 {
	border: 2px solid black;
	display: inline-block;
	padding: 5px 20px;
}

.studentinfo {
	margin-top: 10px;
}

.studentinfo table {
	width: 100%;
	border-collapse: collapse;
}

.studentinfo td {
	padding: 5px;
	font-size: 14px;
}

.marks {
	margin-top: 10px;
}

.marks table {
	width: 100%;
	border-collapse: collapse;
}

.marks th, .marks td {
	border: 1px solid #000;
	text-align: center;
	padding: 6px;
	font-size: 19px;
}

.totalbox {
	margin-top: 10px;
	display: flex;
	justify-content: space-between;
}

.words {
	border: 1px solid #000;
	padding: 8px;
	width: 70%;
	background: #f4d5a6;
	border-radius: 5px;
}

.percentage {
	border: 1px solid #000;
	padding: 8px;
	width: 25%;
	background: #e3a83d;
	text-align: center;
	font-weight: bold;
	border-radius: 5px;
}

.partb {
	margin-top: 15px;
}

.partb table {
	width: 100%;
	border-collapse: collapse;
}

.partb th, .partb td {
	border: 1px solid #000;
	padding: 6px;
	font-size: 12px;
	text-align: center;
}

.footer{
    text-align:right;
    margin-top:20px;
    font-weight:bold;
    color: blue;
}

.printbtn {
	text-align: center;
	margin-top: 20px;
}

.cce {
	font-family: "Brush Script MT", cursive;
	font-size: 22px;
	color: #1a4a7a;
	font-weight: bold;
}

.certifyline {
	text-align: center;
	font-size: 14px;
	margin-top: 5px;
	margin-bottom: 8px;
	font-style: italic;
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
	font-weight: bold;
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

@media print {
	.printbtn {
		display: none;
	}
	body {
		background: white;
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
            // Get ALL amount and words elements
            let amounts = document.querySelectorAll(".amount");
            let wordsCells = document.querySelectorAll(".amountWords");

            console.log("Processing " + amounts.length + " amounts...");

            // Loop through each pair
            for (let i = 0; i < amounts.length && i < wordsCells.length; i++) {
                let value = amounts[i].innerText.trim();
                
                if (value && !isNaN(value)) {
                    try {
                        let intValue = Math.floor(Number(value));
                        let words = convertNumberToWords(intValue);
                        wordsCells[i].innerText = words + "";
                        console.log("[" + i + "] " + intValue + " → " + words);
                    } catch (e) {
                        console.error("Error at index " + i + ": " + value);
                        wordsCells[i].innerText = "Error";
                    }
                }
            }
        };
    </script>


</head>

<body>

<c:forEach items="${markssheetlist}" var="Parents" varStatus="studentStatus">
		
	<div style="page-break-inside: avoid;border-style: solid;border-width: thin;">   

	<div class="container">

		<div class="header">

			<div class="topline">
				<div>
					<b>SCHOOL DISE CODE : 29050505406</b>
				</div>
				<div>
					<b style="color: green">Quadri Education Trust®</b>
				</div>
				<div>
					<b>SSLC BOARD CODE : SS0612</b>
				</div>
			</div>

			<div class="schoolbox">

				<div class="logo">
					<img border="0" style="vertical-align: text-bottom;height: 90px;width: 90px;" alt="ideoholic" src="/hamidullah/images/hamidullah.png">
				</div>

				<div class="schoolname">
					<h3 style="font-size: 2cap;">QUADRI GROUP OF INSTITUTIONS</h3>
					<h2 style="font-size: 30px;">HAMIDULLA ENGLISH MEDIUM HIGH
						SCHOOL</h2>
					<div>
						<b>MANGALGI TQ: CHITGUPPA DIST: BIDAR 585329</b>
					</div>
				</div>

			</div>

		</div>


		<div class="title">
			<h2 style="border-radius: 10px; font-size: 25px;">PROGRESS
				REPORT</h2>
			<div style="font-size: 23px;">For the Academic Year ${currentAcademicYear}</div>
			<div class="cce" style="font-size: 25px;">Continuous And
				Comprehensive Evaluation</div>
			<div class="certifyline" style="font-size: 16px;">
				This is to certify that the below mentioned candidate has passed <b>
				<c:set var="dataSubParts" value="${fn:split(Parents.parents.student.classstudying,'--')}" />
						${dataSubParts[0]}
				</b> Examination with the following details.
			</div>
		</div>


		<div class="studentinfo">

			<table>

				<tr style="border: 1px solid black; border-collapse: collapse;">
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 17px;"><b>Enrollment
							No :</b> <c:out value="${Parents.parents.student.admissionnumber}" /></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 17px;"><b>Roll
							No :</b>
					<c:out value="${Parents.parents.student.sts}" /></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 17px;"><b>Class
							:</b>
					<c:out value="${dataSubParts[0]}" /></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 17px;"><b>DOB
							:</b> <c:out value="${Parents.parents.student.dateofbirth}" /></td>
				</tr>
				<tr>
					<br>
				</tr>
				<tr>
					<td colspan="3" style="font-size: 20px;"><b>Student Name :</b>
						<label style="text-transform: uppercase;"><c:out
								value="${Parents.parents.student.name}" /></label></td>
					<td rowspan="3" align="right"><div class="studentphoto">
							<img src="data:image;base64,<c:out value="${Parents.parents.student.studentpic}"/>" width="110" height="110">
						</div></td>
				</tr>

				<tr>
					<td colspan="3" style="font-size: 20px;"><b>Father Name :</b>
						<label style="text-transform: uppercase;"><c:out
								value="${Parents.parents.fathersname}" /></label></td>
				</tr>

				<tr>
					<td colspan="3" style="font-size: 20px;"><b>Mother Name :</b><label
						style="text-transform: uppercase;"><c:out
								value="${Parents.parents.mothersname}" /></label></td>
				</tr>

			</table>

		</div>


		<div class="marks">
			<h4
				style="text-align: center; margin-bottom: 0px; padding-bottom: 0px;">PART
				A</h4>
						<table style="border-collapse: collapse; width: 100%; margin-top: 20px;">
						    <thead>
						    
						    <c:choose>
						        <c:when test="${fn:length(Parents.examSummaries) == 6}">
						        	<tr>
										<th rowspan="2">Scholastic Subjects</th>
										<th colspan="3">Semester 1</th>
										<th colspan="3">Semester 2</th>
										<th colspan="2">TOTAL</th>
										<th rowspan="2">Grade</th>
									</tr>
									<tr>
										<th>FA1</th>
										<th>FA2</th>
										<th>SA1</th>
										<th>FA3</th>
										<th>FA4</th>
										<th>SA2</th>
										<th>Max</th>
										<th>Obt</th>
									</tr>
						        </c:when>
						
						        <c:otherwise>
						        	<tr>
						            <th class="marksTableHeader" style="text-align: center; width: 20%;"> Scholastic Subject</th>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <th class="marksTableHeader"><c:out value="${exam.examName}" /></th>
						            </c:forEach>
						            <!-- Subject-wise Summary Headers -->
						            <th class="marksTableHeader" style="text-align: center; width: 12%;">Total<br>Obt. / Max</th>
						            <!-- <th class="marksTableHeader" style="text-align: center; width: 12%;">Percentage</th> -->
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
						        <c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry" varStatus="status">
						            <tr>
						                <td class="marksTableCellLeft" style="width: 20%;"><c:out value="${subjectEntry.key}" /></td>
						                
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
						                                    <c:when test="${fn:contains(exam.examName, 'FA')}">
						                                        <c:set var="displayMarks" value="${(secured / maxMarks) * 10}" />
						                                        <fmt:formatNumber value="${displayMarks}" maxFractionDigits="2" />
						                                        <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                        <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 10}" />
						                                    </c:when>
						
						                                    <c:when test="${fn:contains(exam.examName, 'SA')}">
						                                        <c:set var="displayMarks" value="${(secured / maxMarks) * 30}" />
						                                        <fmt:formatNumber value="${displayMarks}" maxFractionDigits="2" />
						                                        <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                        <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 30}" />
						                                    </c:when>
						
						                                    <c:otherwise>
						                                        <c:out value="${secured}" />
						                                        <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + secured}" />
						                                        <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + maxMarks}" />
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
						                    <c:when test="${subjectPercentage >= 80}">
						                        <c:set var="subjectGrade" value="A" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 70}">
						                        <c:set var="subjectGrade" value="B+" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 60}">
						                        <c:set var="subjectGrade" value="B" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 50}">
						                        <c:set var="subjectGrade" value="C+" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 40}">
						                        <c:set var="subjectGrade" value="C" />
						                    </c:when>
						                    <c:when test="${subjectPercentage >= 33}">
						                        <c:set var="subjectGrade" value="D" />
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
						                <td class="marksTableCell" style="text-align: center;">
						                    <fmt:formatNumber value="${subjectTotalMarksObtained}" maxFractionDigits="2" />
						                </td>
						                
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
						                <td class="marksTableCell" style="text-align: center;">
						                    ${subjectGrade}
						                </td>
						                
						                <!-- Remarks Column - Empty for now 
						                <td class="marksTableCell" style="text-align: left; vertical-align: top; padding: 8px; width: 15%;"></td>-->
						            </tr>
						        </c:forEach>
						        
						         <!-- Calculate grand percentage and grade -->
							        <c:set var="grandPercentage" value="0" />
							        <c:if test="${grandTotalMaxMarks > 0}">
							            <c:set var="grandPercentage" value="${(grandTotalMarksObtained / grandTotalMaxMarks) * 100}" />
							        </c:if>
							        
							        <c:set var="grandGrade" value="-" />
							        <c:choose>
							            <c:when test="${grandPercentage >= 90}">
							                <c:set var="grandGrade" value="A+" />
							            </c:when>
							            <c:when test="${grandPercentage >= 80}">
							                <c:set var="grandGrade" value="A" />
							            </c:when>
							            <c:when test="${grandPercentage >= 70}">
							                <c:set var="grandGrade" value="B+" />
							            </c:when>
							            <c:when test="${grandPercentage >= 60}">
							                <c:set var="grandGrade" value="B" />
							            </c:when>
							            <c:when test="${grandPercentage >= 50}">
							                <c:set var="grandGrade" value="C+" />
							            </c:when>
							            <c:when test="${grandPercentage >= 40}">
							                <c:set var="grandGrade" value="C" />
							            </c:when>
							            <c:when test="${grandPercentage >= 33}">
							                <c:set var="grandGrade" value="D" />
							            </c:when>
							            <c:otherwise>
							                <c:set var="grandGrade" value="F" />
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
									        <td class="summaryTableHeader" style="width: 20%;">Grand Total</td>
									        <c:forEach items="${Parents.examSummaries}" var="exam">
									            <td class="marksTableCell"></td>
									        </c:forEach>
									        
									        <c:set var="roundedMarks">
									            <fmt:formatNumber value="${grandTotalMarksObtained}" maxFractionDigits="0" />
									        </c:set>
									        <td class="amount" style="display: none;">${roundedMarks}</td>
									        
									        <td class="marksTableCell" style="text-align: center;">
									            <fmt:formatNumber value="${grandTotalMaxMarks}" maxFractionDigits="0" />
									        </td>
									        <td class="marksTableCell" style="text-align: center;">
									            ${roundedMarks}
									        </td>
									        <%-- <td class="marksTableCell" style="text-align: center;">
								                <c:choose>
								                    <c:when test="${grandPercentage > 0}">
								                        <fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentage}" />%
								                    </c:when>
								                    <c:otherwise>-</c:otherwise>
								                </c:choose>
								            </td> --%>
								            
									        <td class="marksTableCell" style="text-align: center;">
									            ${grandGrade}
									        </td>
									    </tr>
						    </tbody>
						</table>

		</div>


		<div class="totalbox">

			<div class="words">
				<b>Total Marks Obtained (In Words) :</b><label class="amountWords"></label>
			</div>

			<div class="percentage">Percentage : <fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentage}" />%</div>

		</div>


		<c:choose>
			<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}">
				<div style="display: flex; gap: 10px;" class="partb">

				    <table style="width: 50%; border-collapse: collapse;">
				        <tr>
				            <th>Co-Scholastic Subjects</th>
				            <th>Grade</th>
				        </tr>
				
				        <tr>
				            <td>Physical & Health Education</td>
				            <td>A</td>
				        </tr>
				
				        <tr>
				            <td>Art Education</td>
				            <td>A</td>
				        </tr>
				    </table>
				
				    <table style="width: 50%; border-collapse: collapse;">
				        <tr>
				            <th>Attendance</th>
				            <th>Semester-1</th>
				            <th>Semester-2</th>
				            <th>Total</th>
				        </tr>
				
				        <tr>
				            <td>Total Working Days</td>
				            <td></td>
				            <td></td>
				            <td></td>
				        </tr>
				
				        <tr>
				            <td>Total Present Days</td>
				            <td></td>
				            <td></td>
				            <td></td>
				        </tr>
				    </table>
				
				</div>
			</c:when>
			<c:otherwise>
			
			<div style="display: flex; gap: 10px;" class="partb">

				    <table style="width: 50%; border-collapse: collapse;">
				        <tr>
				            <th>Co-Scholastic Subjects</th>
				            <th>Grade</th>
				        </tr>
				
				        <tr>
				            <td>Physical & Health Education</td>
				            <td>A</td>
				        </tr>
				        
				        <tr>
				            <td>Attitude & Values</td>
				            <td>A</td>
				        </tr>
				        
				        <tr>
				            <td>Work Experience</td>
				            <td>A</td>
				        </tr>
				
				        <tr>
				            <td>Art Education</td>
				            <td>A</td>
				        </tr>
				    </table>
				
				    <table style="width: 50%; border-collapse: collapse;">
				        <tr>
				            <th>Attendance</th>
				            <th>Semester-1</th>
				            <th>Semester-2</th>
				            <th>Total</th>
				        </tr>
				
				        <tr>
				            <td>Total Working Days</td>
				            <td></td>
				            <td></td>
				            <td></td>
				        </tr>
				
				        <tr>
				            <td>Total Present Days</td>
				            <td></td>
				            <td></td>
				            <td></td>
				        </tr>
				    </table>
				
				</div></c:otherwise>
		</c:choose>

		<div class="footer">
    <br>
    <br>
    <br>
        
Head Master&emsp;&emsp;&emsp;&emsp;&emsp;<br><label style="font-weight: 700;font-size: 11px;">
HAMIDULLA ENGLISH MEDIUM HIGH SCHOOL&nbsp;<br>
MANGALGI,TQ:CHITGUPPA, DIST: BIDAR:556612<br>
UDISE CODE:29050505406,SSLC:SS0612&emsp;&emsp;
</label>
</div>


		<div class="printbtn">
			<button onclick="window.print()">Print</button>
		</div>

	</div>
	</div>
	</c:forEach>
	
	
</body>
</html>