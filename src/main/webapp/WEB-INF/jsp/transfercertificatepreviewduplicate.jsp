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

td{
font-size:16px;
}

</style>
</head>
 <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/patriswamy/UserProcess/sessionTimeOut");
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
<body>

<div class="tc-container">

    <!-- Header -->
    <div class="tc-header">
        <h2 style="margin:0px">${trustname}</h2>
        <hr>
        <h3 style="margin:0px">${collegename}</h3>
         
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
            <td style="font-weight:bold;">${collegename}</td>
        </tr>
        <tr>
            <td >2. College Code</td>
            <td style="font-weight:bold;"><c:out value="${collegecode}" /></td>
                   </tr>
        <tr>
            
            <td >3. Admission No</td>
            <td style="font-weight:bold;"><c:out value="${Admissionno}" /></td>
        </tr>
        <tr>
            <td >4. Date of Admission</td>
            <td style="font-weight:bold;"><c:out value="${dateofadmission}" /></td>
             </tr>
        <tr>
            <td >5. Student SATS No</td>
            <td style="font-weight:bold;"> <c:out value="${sts}" /></td>
        </tr>
        <tr>
            <td >6. Name of the Student<br><small>(As entered in Admission Register)</small></td>
            <td style="font-weight:bold;"> <c:out value="${studentname1}" /></td>
        </tr>
        <tr>
            <td >7. Sex</td>
            <td style="font-weight:bold;"> <c:out value="${gender}" /></td>
             </tr>
        <tr>
            <td >8. Name of the Father</td>
            <td style="font-weight:bold;"><c:out value="${fathername1}" /></td>
        </tr>
        <tr>
            <td >9. Name of the Mother</td>
            <td style="font-weight:bold;"><c:out value="${mothername1}" /></td>
        </tr>
        <tr>
            <td >10. Nationality:&nbsp;&nbsp;&nbsp;&nbsp;<label style="font-weight:bold;"><c:out value="${nationality}" /></label></td>
            <td >11. Religion:&nbsp;&nbsp;&nbsp;&nbsp;<label style="font-weight:bold;"><c:out value="${religion}" /></label></td>
        </tr>
        <tr>
            <td >12. Caste:&nbsp;&nbsp;&nbsp;&nbsp;<label style="font-weight:bold;"><c:out value="${caste}" /></label></td>
            <td >13. Category:&nbsp;&nbsp;&nbsp;&nbsp;<label style="font-weight:bold;"><c:out value="${category}" /></label></td>
        </tr>
        <tr>
            <td >
                14. Whether the student belongs to Schedule Cast, <br>
Scheduled Tribe, Nomadic Tribe or Semi Nomadic Tribe 
            </td>
             <td style="font-weight:bold;">
             ${yesno}
            </td>
        </tr>
        <tr>
            <td >15. Date of Birth   in Figures<br>in words</td>
            <td style="font-weight:bold;"><c:out value="${dateofbirth}" /><br> <c:out value="${dateofbirthwords}" /></td>
        </tr>
        <tr>
            <td >
                16.  Class in which the student was studied at the time of <br>
leaving the institution (In words) 
            </td>
            <td style="font-weight:bold;">${leavingclass}</td>
        </tr>
        <tr>
            <td >
                17.  Public Examination appeared with Reg. No. Month & <br>
Year 
             </td>
             <td style="font-weight:bold;">
                a)  EXAMINATION: ${firstsubject}
                <br>b) MONTH & YEAR: ${secondsubject} 
                <br>c) REG. NO: ${thirdsubject}
                <br>d)  RESULT: ${Fourthsubject} 
            </td>
        </tr>
        <tr>
            <td >
                18. a) Language Offered in Part-I 
                </td>
                 <td style="font-weight:bold;">
              ${partone} 
                </td>
        </tr>
         <tr>
            <td >
                18. b) Optional Subjects Offered in Part-II 
                </td>
                 <td style="font-weight:bold;">
              ${parttwo} 
                </td>
        </tr>
        <tr>
            <td >19. a) Total number of working days <br>
b) No of days he/she was present </td>
            <td style="font-weight:bold;"> ${workingdays} <br>
 ${present} </td>
        </tr>
        <tr>
            <td >20. Last date of student attendance in the institution </td>
            <td style="font-weight:bold;"><c:out value="${dateofleaving}" /></td>
        </tr>
<tr>
            <td >21. Date of Application for Certificate </td>
            <td style="font-weight:bold;"><c:out value="${datecert}" /></td>
        </tr>       <tr>
            <td >22. Date of issue of the transfer certificate  </td>
            <td style="font-weight:bold;"><c:out value="${dateoftc}" /></td>
        </tr>
        <tr>
            <td >23. Character & Conduct</td>
            <td style="font-weight:bold;"><b><c:out value="${conduct}" /></b></td>
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
