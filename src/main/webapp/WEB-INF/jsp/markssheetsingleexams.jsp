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
<link href="https://fonts.googleapis.com/css2?family=Alex+Brush&display=swap" rel="stylesheet">
<style>
body {
	font-family: "Times New Roman", Times, serif;
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

/* .header {
	border-bottom: 1px solid #000;
} */

.topline {
	font-size: 12px;
	display: flex;
	justify-content: space-between;
}

.schoolbox {
	display: flex;
	align-items: center;
	margin-top: 5px;
	height:100px;
}

.logo {
	width: 90px;
	height: 95px;
	border: 2px solid #538261;
}

.schoolname {
	background: #fec000;
	flex: 1;
	text-align: center;
	height: 90px;
	padding-top: 5px;
	border: 1px solid #000;
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
}

.title h2 {
	border: 2px solid black;
	display: inline-block;
	padding: 5px 20px;
	border-radius: 10px; 
	font-size: 25px;
	margin-bottom: 10px;
	margin-top: 10px;
  	outline: 2px solid black;       /* outer border */
  	outline-offset: 6px;            /* gap between borders */

  	border-radius: 10px;
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
	margin-top: 50px;
	display: flex;
	justify-content: space-between;
}

.words {
	border: 1px solid #000;
	padding: 8px;
	width: 70%;
	background: #f4d5a6;
	border-radius: 5px;
	text-transform: uppercase;
	font-size: 14px;
}

.percentage {
	border: 1px solid #000;
	padding: 8px;
	width: 25%;
	background: #e3a83d;
	text-align: center;
	font-weight: bold;
	border-radius: 5px;
	text-transform: uppercase;
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
	padding: 6px;
	font-size: 12px;
	text-align: center;
}

.footer {
    text-align: right;
    margin-top: 20px;
    font-weight: bold;
    color: blue;
}

.signature {
    display: block;
    margin-left: auto;
    margin-bottom: -10px;
}

.headmaster {
    padding-right: 90px;  /* 👈 required spacing */
}

.school-info {
    font-weight: 700;
    font-size: 11px;
}
.printbtn {
	text-align: center;
	margin-top: 20px;
}

.cce {
	font-family: "Alex Brush", cursive;
	font-size: 22px;
	color: #1975d0;
	font-weight: bold;
}

.certifyline {
	text-align: center;
	font-size: 14px;
	margin-top: 5px;
	margin-bottom: 8px;
	font-style: italic;
	padding-left:0px;
	padding-right:0px;
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
		
	<div style="page-break-inside: avoid;">   

	<div class="container">

		<div class="header">

			<div class="topline">
				<div>
					<b>SCHOOL DISE CODE : 29050505406</b>
				</div>
				<div>
					<b style="color: green"><label style="font-size:12px;text-transform: uppercase;">Quadri Educational Trust®</label></b>
				</div>
				<div>
					<b>SSLC BOARD (KSEAB) CODE : SS0612</b>
				</div>
			</div>

			<div class="schoolbox">

				<div class="logo">
					<img border="0" style="vertical-align: text-bottom;height: 90px;width: 90px;" alt="ideoholic" src="/hamidullah/images/hamidullah.png">
				</div>

				<div class="schoolname">
					<h3 style="font-size: 19px;">QUADRI GROUP OF INSTITUTIONS</h3>
					<h2 style="font-size: 28px;">HAMIDULLA ENGLISH MEDIUM HIGH SCHOOL</h2>
					<div>
						<b><label style="font-size:19px;text-transform: uppercase;">MANGALGI TQ: CHITGUPPA DIST: BIDAR 585329</label></b>
					</div>
				</div>

			</div>

		</div>


		<div class="title">
			<h2><label style="font-size:29px;text-transform: uppercase;">PROGRESS
				REPORT</label></h2>
			<div style="font-size: 21px;font-weight:bold;">
			<c:set var="yearParts" value="${fn:split(currentAcademicYear, '/')}" />
			<c:set var="startYear" value="${yearParts[0]}" />
			<c:set var="endYear" value="${startYear + 1}" />

			For the Academic Year ${startYear}-${endYear}</div>
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
		</div>


		<div class="studentinfo">

			<table>

				<tr style="border: 1px solid black; border-collapse: collapse;">
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Enrollment (SATS)
							No : <c:out value="${Parents.parents.student.sts}" /></b></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Roll
							No :
					<c:out value="${Parents.parents.student.admissionnumber}" /></b></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Class
							:
						<c:choose>
						<c:when test="${dataSubParts[0]=='Nursery' || dataSubParts[0]=='L.K.G' || dataSubParts[0]=='U.K.G'}">${dataSubParts[0]}</c:when>
						    <c:when test="${dataSubParts[0] == '1'}">1<sup>st</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '2'}">2<sup>nd</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '3'}">3<sup>rd</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '4'}">4<sup>th</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '5'}">5<sup>th</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '6'}">6<sup>th</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '7'}">7<sup>th</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '8'}">8<sup>th</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '9'}">9<sup>th</sup> Std.</c:when>
						    <c:when test="${dataSubParts[0] == '10'}">10<sup>th</sup> Std.</c:when>
						</c:choose>
					</b></td>
					<td
						style="border: 1px solid black; border-collapse: collapse; font-size: 18px;"><b>Date of Birth
							: <fmt:formatDate value="${Parents.parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></b></td>
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


		<div class="marks">
			<h4
				style="text-align: center; margin-bottom: 0px; padding-bottom: 0px;">PART-A</h4>
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
						            <th class="marksTableHeader" style="text-align: center; width: 20%;text-transform: uppercase;"> Scholastic Subject</th>
						            <c:forEach items="${Parents.examSummaries}" var="exam">
						                <th class="marksTableHeader" style="text-transform: uppercase;"><c:out value="${exam.examName}" /></th>
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
								        <c:set var="englishexam" value="" />
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
						                                        <c:set var="displayMarks" value="${(secured / 20) * 10}" />
						                                        <fmt:formatNumber value="${displayMarks}" maxFractionDigits="1" />
						                                        <c:set var="subjectTotalMarksObtained" value="${subjectTotalMarksObtained + displayMarks}" />
						                                        <c:set var="subjectTotalMaxMarks" value="${subjectTotalMaxMarks + 10}" />
						                                    </c:when>
						
						                                    <c:when test="${fn:contains(exam.examName, 'SA')}">
						                                        <c:set var="displayMarks" value="${(secured / 50) * 30}" />
						                                        <fmt:formatNumber value="${displayMarks}" maxFractionDigits="1" />
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
						                <td class="marksTableCell" style="text-align: center;">
						                    <fmt:formatNumber value="${subjectTotalMarksObtained}" maxFractionDigits="1" />
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
						                <td class="marksTableCell" style="text-align: left;">&emsp;
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
							         
							         <c:set var="roundedMarks">
									            <fmt:formatNumber value="${grandTotalMarksObtained}" maxFractionDigits="0" />
									        </c:set>
									        
									        
									       <c:choose>
						                    <c:when test="${grandPercentage >= 90}">
						                        <c:set var="grandGrade" value="A+" />
						                    </c:when>
						                    <c:when test="${roundedMarks >= 70}">
						                        <c:set var="grandGrade" value="A" />
						                    </c:when>
						                    <c:when test="${roundedMarks >= 50}">
						                        <c:set var="grandGrade" value="B+" />
						                    </c:when>
						                    <c:when test="${roundedMarks >= 30}">
						                        <c:set var="grandGrade" value="B" />
						                    </c:when>
						                    <c:otherwise>
						                        <c:set var="grandGrade" value="C" />
						                    </c:otherwise>
						                </c:choose>
						                
									        
							        
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
						                                    <c:when test="${fn:contains(exam.examName, 'FA')}">
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
						
						                                    <c:when test="${fn:contains(exam.examName, 'SA')}">
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
								            
									        <td class="marksTableCell" style="text-align: left;">
									            &emsp;&nbsp;${grandGrade}
									        </td>
									    </tr>
						    </tbody>
						</table>

		</div>


		<div class="totalbox">

			<div class="words">
				<b>Total Marks Obtained (In Words) :<label class="amountWords"></label>&nbsp; only</b>
			</div>

			<div class="percentage">Percentage : <fmt:formatNumber type="number" maxFractionDigits="1" value="${grandPercentage}" />%</div>

		</div>

<div class="marks" style="font-size: 19px;">
		<c:choose>
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
	<div class="footer">
    <img src="/hamidullah/images/hmsign.png" class="signature" width="200" height="60"/>

    <div class="headmaster">Head Master</div>

    <label class="school-info">
        HAMIDULLA ENGLISH MEDIUM HIGH SCHOOL<br>
        MANGALGI,TQ:CHITGUPPA, DIST: BIDAR-585329<br>
        DISE CODE:29050505406,SSLC CODE:SS0612&nbsp;&nbsp;&nbsp;
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
