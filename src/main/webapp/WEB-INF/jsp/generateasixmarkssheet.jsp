<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Student Mark Sheet</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, Helvetica, sans-serif;
}

body{
    background:#ececec;
    padding:20px;
}

/******************************
        A4 PAGE
*******************************/

.page{
    width:105mm;
    min-height:148mm;
    margin:auto;
    background:#fff;
    border:1px solid #999;
    box-shadow:0 0 8px rgba(0,0,0,.2);
    padding:6mm;
}


/******************************
        HEADER
*******************************/

.header{

    width:100%;

     border:2px solid #0c5da5;

    border-radius:10px;

    overflow:hidden;
}


/******************************
 BLUE TOP
*******************************/

.headerTop{

    background:#0c5da5;

    color:white;

    display:flex;

    align-items:center;

    padding:6px;
}


/******************************
 LOGO
*******************************/

.logoBox{

width:45px;
    margin-right:8px;

    text-align:center;

}

.logoBox img{

    width:38px;
    height:38px;

    object-fit:contain;
}


/******************************
 SCHOOL DETAIL
*******************************/

.schoolDetail{

    flex:1;

    text-align:center;
}

.schoolName{

    font-size:15px;

    font-weight:bold;

    letter-spacing:1px;

    text-transform:uppercase;
}

.schoolAddress{

    margin-top:4px;
     font-size:8px;
    line-height:11px;

}

.schoolAffiliation{
     font-size:8px;
    margin-top:4px;

}


/******************************
 TITLE
*******************************/

.examTitle{
     font-size:12px;
    padding:4px;
    background:#eaf4ff;

    color:#003366;

    text-align:center;

    font-weight:bold;

    border-top:2px solid #0c5da5;

    border-bottom:2px solid #0c5da5;
}


/******************************
 CONDUCTED DATE
*******************************/

.examDate{
    font-size:8px;
    padding:5px;
    text-align:center;
    font-weight:bold;

}

.examDate span{

    color:#0c5da5;
}


/******************************
 STUDENT DETAILS
*******************************/

.studentBox{
     margin-top:6px;
    width:100%;

    border:2px solid #0c5da5;

    border-collapse:collapse;
}

.studentBox td{
     padding:3px;
    font-size:8px;
    border:1px solid #bbb;
}

.label{

    width:18%;

    background:#eef6ff;

    font-weight:bold;

    color:#003366;
}

.value{

    width:32%;
}


@media print{

body{

background:white;

padding:0;

}

.page{

border:none;

box-shadow:none;

margin:0;

width:100%;

}

}

</style>

<style>

.markTable{
     margin-top:6px;
    font-size:7px;
    width:100%;

    border-collapse:collapse;
}

.markTable th{
padding:3px;
    background:#0c5da5;

    color:#fff;

    border:1px solid #ffffff;
    text-align:center;
    font-weight:bold;

}

.markTable td{
     padding:2px;
    border:1px solid #bfbfbf;

}

.markTable tr:nth-child(even){

    background:#f8fbff;

}

.markTable tr:hover{

    background:#eef7ff;

}

.center{

    text-align:center;

}

.right{

    text-align:right;

}

.subject{

    padding-left:3px;

    font-weight:600;

}

.totalRow{

    background:#dbeeff !important;

    font-weight:bold;

    color:#003366;

}

</style>

<style>

.summaryTable{
    margin-top:6px;
    border-spacing:4px;
    width:100%;
    border-collapse:separate;
}

.summaryTable td{
    padding:4px;
    width:25%;

    border:2px solid #0c5da5;

    border-radius:6px;

    background:#f8fbff;

}

.summaryTitle{
    font-size:7px;

    font-weight:bold;

    color:#003366;

    margin-bottom:6px;

    text-transform:uppercase;

}

.summaryValue{
     font-size:10px;

    font-weight:bold;

    color:#111;

    text-align:center;

}

.remarksBox{

    width:100%;

    margin-top:15px;

    border:2px solid #0c5da5;

    border-radius:6px;

    overflow:hidden;

}

.remarksHeading{

    background:#0c5da5;

    color:#fff;

    padding:8px;

    font-weight:bold;

    text-align:center;

    letter-spacing:1px;

}

.remarksContent{

    min-height:70px;

    padding:12px;

    font-size:15px;

}

 .print-btn{
            text-align: center;
            margin: 10px;
        }

        @media print{
            .print-btn{
                display: none;
            }
        }


</style>

<style>

.signatureTable{

    width:100%;

     margin-top:10px;

    border-collapse:collapse;

}

.signatureTable td{

    width:33.33%;

    text-align:center;

    vertical-align:top;

}

.signLine{
    width:55px;
    margin:18px auto 4px;
    border-top:2px solid #333;
}

.signTitle{
    font-size:8px;

    font-weight:bold;

    color:#003366;

    letter-spacing:0.5px;

}

/********************************************
                FOOTER
*********************************************/

.footer{

 margin-top:8px;
    padding-top:4px;
    font-size:6px;

    padding-top:8px;

    text-align:center;

    color:#666;

}

/********************************************
            OPTIONAL WATERMARK
*********************************************/

.watermark{

    position:absolute;

    top:48%;

    left:50%;

    transform:translate(-50%,-50%);

    font-size:95px;

    color:#0c5da5;

    opacity:.05;

    font-weight:bold;

    letter-spacing:10px;

    pointer-events:none;

    z-index:0;

}

/********************************************
            PRINT SETTINGS
*********************************************/

@page{
    size:A6 portrait;
    margin:5mm;
}

@media print{

html,body{
    width:105mm;
    height:148mm;
    margin:0;
    padding:0;
    background:#fff;
}

body{

    margin:0;

    padding:0;

}

.page{
    width:100%;
    min-height:100%;
    border:none;
    box-shadow:none;
    padding:4mm;
}

.header{

    page-break-inside:avoid;

}

.studentBox{

    page-break-inside:avoid;

}

.markTable{

    page-break-inside:auto;

}

.markTable tr{

    page-break-inside:avoid;

}

.summaryTable{

    page-break-inside:avoid;

}

.signatureTable{

    page-break-inside:avoid;

}

}

</style>


</head>

<body>
<c:forEach items="${markssheetlist}" var="Parents" varStatus="studentStatus">
<div style="page-break-inside: avoid;"> 
<div class="page">

<!-- ========================================================= -->

<div class="header">

<div class="headerTop">

<div class="logoBox">

<img src="images/logo.png">

</div>

<div class="schoolDetail">

<div class="schoolName">
${branchname}

</div>

<div class="schoolAddress">

${branchaddress}<br>

${schooldetails.addressline2}<br>

${schooldetails.city},
${schooldetails.state}
-
${schooldetails.pincode}

</div>

<div class="schoolAffiliation">

Affiliated to CBSE / State Board

</div>

</div>

</div>

<div class="examTitle">

EXAMINATION MARK SHEET

</div>

<div class="examDate">

Conducted From
<span>

<fmt:formatDate
value="${fromDate}"
pattern="dd-MM-yyyy"/>

</span>

&nbsp;&nbsp;&nbsp;&nbsp;

To

<span>

<fmt:formatDate
value="${toDate}"
pattern="dd-MM-yyyy"/>

</span>

</div>

</div>

<!-- ========================================================= -->

<table class="studentBox">

<tr>

<td class="label">

Student Name

</td>

<td class="value">

${Parents.parents.student.name}

</td>

<td class="label">

Roll No.

</td>

<td class="value">

${Parents.parents.student.sts}

</td>

</tr>

<tr>

<td class="label">

Father's Name

</td>

<td class="value">

${Parents.parents.fathersname}

</td>

<td class="label">

Admission No.

</td>

<td class="value">

${Parents.parents.student.admissionnumber}

</td>

</tr>

<tr>

<td class="label">

Class

</td>

<td class="value">

<c:set var="dateClassParts" value="${fn:split(Parents.parents.student.classstudying,'--')}" /> ${dateClassParts[0]}

</td>

<td class="label">

Section

</td>

<td class="value">

${dateClassParts[1]}

</td>

</tr>

</table>

<!-- ===== Marks Table starts in Part-2 ===== -->
<!-- ========================================================= -->
<!--                 MARKS TABLE STARTS                         -->
<!-- ========================================================= -->



<table class="markTable">

<thead>

<tr>

<th style="width:6%">Sl.</th>

<th style="width:42%">Subjects</th>

<th style="width:13%">Min Marks</th>

<th style="width:13%">Max Marks</th>

<th style="width:14%">Obtained</th>

<th style="width:12%">Grade</th>

</tr>

</thead>

<tbody>

<c:set var="sl" value="1"/>

 <c:forEach items="${Parents.subjectExamMarks}" var="subjectEntry" begin="0" end="0" step="1">
 <c:forEach items="${Parents.examSummaries}" var="exam">
  <c:set var="markStr" value="${subjectEntry.value[exam.examName]}" />
   <c:set var="parts" value="${fn:split(markStr, '/')}" />
<tr>

<td class="center">

${sl}

</td>

<td class="subject">

${subjectEntry.key}

</td>
<td class="center">

${parts[2]}

</td>

<td class="center">

${parts[1]}

</td>

<td class="center">

${parts[0]}

</td>

<td class="center">

${parts[3]}

</td>

</tr>
<c:set var="sl" value="${sl+1}"/>



<tr class="totalRow">

<td colspan="2" class="right">

TOTAL

</td>

<td class="center">

${exammarks.totalMinMarks}

</td>

<td class="center">

${exammarks.totalMarks}

</td>

<td class="center">

${exammarks.totalMarksObtained}

</td>

<td class="center">

--

</td>

</tr>
 </c:forEach>
  </c:forEach>
</tbody>

</table>
<%-- <table class="summaryTable">

<tr>

<td>

<div class="summaryTitle">

Total Marks

</div>

<div class="summaryValue">

${exammarks.totalMarks}

</div>

</td>

<td>

<div class="summaryTitle">

Marks Obtained

</div>

<div class="summaryValue">

${exammarks.totalMarksObtained}

</div>

</td>

<td>

<div class="summaryTitle">

Percentage

</div>

<div class="summaryValue">

<fmt:formatNumber
value="${exammarks.percentage}"
pattern="0.00"/>%

</div>

</td>

<td>

<div class="summaryTitle">

Overall Grade

</div>

<div class="summaryValue">

${exammarks.grade}

</div>

</td>

</tr>



</table>
 --%>
<table class="markTable">
<h2> Optional subject </h2>
<thead>

<tr>

<th style="width:6%">Sl.</th>

<th style="width:42%">Subject Name</th>

<th style="width:13%">Min Marks</th>

<th style="width:13%">Max Marks</th>

<th style="width:14%">Obtained</th>

<th style="width:12%">Grade</th>

</tr>

</thead>

<tbody>

<c:set var="sl" value="1"/>

<c:forEach items="${subjectSummaryList}" var="subject">

<tr>

<td class="center">

${sl}

</td>

<td class="subject">

${subject.subjectName}

</td>

<td class="center">

${subject.minimumMarks}

</td>

<td class="center">

${subject.maximumMarks}

</td>

<td class="center">

${subject.obtainedMarks}

</td>

<td class="center">

${subject.grade}

</td>

</tr>

<c:set var="sl" value="${sl+1}"/>

</c:forEach>

<tr class="totalRow">

<td colspan="2" class="right">

TOTAL

</td>

<td class="center">

${exammarks.totalMinMarks}

</td>

<td class="center">

${exammarks.totalMarks}

</td>

<td class="center">

${exammarks.totalMarksObtained}

</td>

<td class="center">

--

</td>

</tr>

</tbody>

</table>



<!-- ========================================================= -->
<!--                 RESULT SUMMARY SECTION                    -->
<!-- ========================================================= -->



<table class="summaryTable">

<tr>

<td>

<div class="summaryTitle">

Total Marks

</div>

<div class="summaryValue">

${exammarks.totalMarks}

</div>

</td>

<td>

<div class="summaryTitle">

Marks Obtained

</div>

<div class="summaryValue">

${exammarks.totalMarksObtained}

</div>

</td>

<td>

<div class="summaryTitle">

Percentage

</div>

<div class="summaryValue">

<fmt:formatNumber
value="${exammarks.percentage}"
pattern="0.00"/>%

</div>

</td>

<td>

<div class="summaryTitle">

Overall Grade

</div>

<div class="summaryValue">

${exammarks.grade}

</div>

</td>

</tr>



</table>

<table class="signatureTable">

<tr>

<td>

<div class="signLine"></div>

<div class="signTitle">

Class Teacher

</div>

</td>

<td>

<div class="signLine"></div>

<div class="signTitle">

Parent

</div>

</td>

<td>

<div class="signLine"></div>

<div class="signTitle">

Principal

</div>

</td>

</tr>

</table>



<!-- ========================================================= -->
<!-- Footer                                                    -->
<!-- ========================================================= -->

<div class="footer">

<strong>

${schooldetails.schoolname}

</strong>

|

${schooldetails.city}

|

Phone :

${schooldetails.phone}

</div>


</div>
</div>
</c:forEach>
 <div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>

</body>

</html>