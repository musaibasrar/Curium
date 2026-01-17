<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Certificate</title>
<link rel="stylesheet" href="tc.css">
<style>
body {
    font-family: "Times New Roman", serif;
    background: #f5f5f5;
}

.tc-container {
    width: 900px;
    margin: auto;
    background: #fff;
    padding: 20px;
    border: 2px solid #000;
}

.tc-header {
    text-align: center;
    margin-bottom: 15px;
}

.tc-header h1 {
    margin: 10px 0;
    text-decoration: underline;
}

.tc-number {
    margin: 10px 0;
    font-size: 16px;
}

.tc-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 14px;
}

.tc-table td {
    border: 1px solid #000;
    padding: 6px;
    vertical-align: top;
}

.checkbox {
    margin-left: 30px;
}

.tc-footer {
    margin-top: 40px;
    text-align: right;
}

.signature {
    margin-right: 50px;
}

.print-btn {
    text-align: center;
    margin-top: 20px;
}

@media print {
    .print-btn {
        display: none;
    }
}

</style>
</head>
<body>

<div class="tc-container">

    <!-- Header -->
    <div class="tc-header">
        <h2 style="margin:0px">AVV TRUST®</h2>
        <hr>
        <h3 style="margin:0px">${collegename}</h3>
         <hr>
        <h5 style="margin:0px">POONAM EDUCATIONAL INSTITUTION MUNGNAL® </h5>
         <hr>
        <h3 style="margin:0px;color:red;">TRANSFER CERTIFICATE</h3>
        <p style="margin:0px">OR ANNEXURE TO THE TRANSFER CERTIFICATE</p>
        <p style="margin:0px">Two Year Pre-University Course</p>
        <p style="margin:0px">(Under the Board of Pre-University education in Bengaluru Karnataka)</p>
        <hr>
    </div>

    <!-- TC Number -->
    <div class="tc-number">
        <b>T.C. No:</b>&nbsp;&nbsp; <c:out value="${tcno}" />
    </div>

    <!-- Main Table -->
    <table class="tc-table">
        <tr>
            <td >1. Name of the Institution</td>
            <td >${collegename}</td>
        </tr>
        <tr>
            <td >2. College Code</td>
            <td ><c:out value="${studentdetails.student.schoollastattended}" /></td>
                   </tr>
        <tr>
            
            <td >3. Admission No</td>
            <td ><c:out value="${studentdetails.student.admissionnumber}" /></td>
        </tr>
        <tr>
            <td >4. Date of Admission</td>
            <td ><c:out value="${studentdetails.student.admissiondate}" /></td>
             </tr>
        <tr>
            <td >5. Student SATS No</td>
            <td> <c:out value="${studentdetails.student.sts}" /></td>
        </tr>
        <tr>
            <td >6. Name of the Student<br><small>(As entered in Admission Register)</small></td>
            <td > <c:out value="${studentdetails.student.name}" /></td>
        </tr>
        <tr>
            <td >7. Sex</td>
            <td > <c:out value="${studentdetails.student.gender}" /></td>
             </tr>
        <tr>
            <td >8. Name of the Father</td>
            <td ><c:out value="${studentdetails.fathersname}" /></td>
        </tr>
        <tr>
            <td >9. Name of the Mother</td>
            <td ><c:out value="${studentdetails.mothersname}" /></td>
        </tr>
        <tr>
            <td >10. Nationality:INDIAN</td>
            <td >11. Religion:<c:out value="${studentdetails.student.religion}" /></td>
        </tr>
        <tr>
            <td >12. Caste:<c:out value="${studentdetails.student.caste}" /></td>
            <td >13. Category:<c:out value="${studentdetails.student.socialcategory}" /></td>
        </tr>
        <tr>
            <td >
                14. Whether the student belongs to Schedule Cast, <br>
Scheduled Tribe, Nomadic Tribe or Semi Nomadic Tribe 
            </td>
             <td >
             YES<br>NO
            </td>
        </tr>
        <tr>
            <td >15. Date of Birth   in Figures<br>in words</td>
            <td ><c:out value="${studentdetails.student.dateofbirth}" /><br> <c:out value="${dateinword}" /></td>
        </tr>
        <tr>
            <td >
                16. Class studied at the time of leaving (In Words)
                <br>PUC FIRST YEAR SCIENCE PASSED / PUC <br>SECOND YEAR SCIENCE PASSED
            </td>
            <td >PUC FIRST YEAR SCIENCE PASSED<br> 
PUC SECOND YEAR SCIENCE PASSED </td>
        </tr>
        <tr>
            <td >
                17.  Public Examination appeared with Reg. No. Month & <br>
Year 
             </td>
             <td >
                a)  Examination: ${firstsubject}
                <br>b) MONTH & YEAR: ${secondsubject} 
                <br>c) REG. NO: ${thirdsubject}
                <br>d)  RESULT: ${Fourthsubject} 
            </td>
        </tr>
        <tr>
            <td >
                18. a) Language Offered in Part-I 
                </td>
                 <td >
              <c:out value="${studentdetails.student.languagesstudied}" />
                </td>
        </tr>
         <tr>
            <td >
                18. b) Optional Subjects Offered in Part-II 
                </td>
                 <td >
             <c:out value="${studentdetails.student.remarks}" />
                </td>
        </tr>
        <tr>
            <td >19. a) Total number of working days <br>
b) No of days he/she was present </td>
            <td> ${workingdays} <br>
 ${present} </td>
        </tr>
        <tr>
            <td >20. Last date of student attendance in the institution </td>
            <td ><c:out value="${studentdetails.student.dateleaving}" /></td>
        </tr>
<tr>
            <td >21. Date on which the application for the transfer  <br>
Certificate was received  </td>
            <td ><c:out value="${datecert}" /></td>
        </tr>       <tr>
            <td >22. Date of issue of the transfer certificate  </td>
            <td ><input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" ></td>
        </tr>
        <tr>
            <td >23. Character & Conduct</td>
            <td ><b>GOOD</b></td>
        </tr>
    </table>

    <!-- Footer -->
    <div class="tc-footer">
        <div class="signature">
            <p>________________________</p>
            <p><b>Principal</b></p>
        </div>
    </div>

    <!-- Print Button -->
    <div class="print-btn">
        <button onclick="window.print()">Print</button>
    </div>

</div>

</body>
</html>
