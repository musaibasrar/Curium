<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Certificate</title>

<style>
body{
    font-family:"Times New Roman", serif;
    margin:0;
    padding:0;
}
@page {
    size: A4;
    margin: 10mm;
}

.page{
    
     width: 190mm;
    min-height: 277mm;
    border: 1px solid black;
    padding: 10px;
    box-sizing: border-box;
    margin: auto; 
}

.tc-title{
    text-align:center;
    font-size:22px;
    font-weight:bold;
    padding:15px 0;
    border-bottom:1px solid #999;
}

.tc-table{
    width:100%;
    border-collapse:collapse;
}

.tc-table td{
    border:1px solid #999;
    padding:10px;
    vertical-align:middle;
    font-size:16px;
}

.srno{
    width:45px;
    font-weight:bold;
    text-align:center;
}

.label{
    width:380px;
    font-weight:bold;
}

.value{
    width:365px;
    font-weight:bold;
}

.header-row td{
    height:25px;
    font-size:18px;
    font-weight:bold;
}

.footer{
    height:100px;
}

.sign-row td{
    height:60px;
    vertical-align:bottom;
    text-align:center;
    font-weight:bold;
    font-size:16px;
}
</style>
<style type="text/css">
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

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/daralmajd/UserProcess/sessionTimeOut");
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

<div class="page">

    <div class="tc-title">
        TRANSFER CERTIFICATE
    </div>

    <table class="tc-table">

        <tr class="header-row">
            <td colspan="2">TC No. &nbsp;&nbsp; <c:out value="${tcno}" /></td>
            <td>Admission No:  <c:out value="${studentdetails.student.admissionnumber}" /></td>
        </tr>

        <tr>
            <td class="srno">1.</td>
            <td class="label">Name of Student</td>
            <td class="value"><c:out value="${studentdetails.student.name}" /></td>
        </tr>

        <tr>
            <td class="srno">2.</td>
            <td class="label">Father's / Guardian's Name</td>
            <td class="value"><c:out value="${studentdetails.fathersname}" /></td>
        </tr>

        <tr>
            <td class="srno">3.</td>
            <td class="label">Nationality</td>
            <td class="value"><c:out value="${studentdetails.student.nationality}" /></td>
        </tr>

        <tr>
            <td class="srno">4.</td>
            <td class="label">Gender</td>
            <td class="value"><c:out value="${studentdetails.student.gender}" /></td>
        </tr>

        <tr>
            <td class="srno">5.</td>
            <td class="label">Date of Admission in the School with Class</td>
            <td class="value"><c:out value="${studentdetails.student.admissiondate}" />, GRADE : <c:out value="${studentdetails.student.classadmittedin}" /></td>
        </tr>

        <tr>
            <td class="srno">6.</td>
            <td class="label">
                Date of Birth according to Admission Register
                (in words)
            </td>
            <td class="value">
                <c:out value="${dateinword}" /><br>
                (<c:out value="${studentdetails.student.dateofbirth}" />)
            </td>
        </tr>

        <tr>
            <td class="srno">7.</td>
            <td class="label">Place of Birth</td>
            <td class="value"><c:out value="${studentdetails.student.placeofbirth}" /></td>
        </tr>

        <tr>
            <td class="srno">8.</td>
            <td class="label">
                Class in which the Student last studied
                (in figures)
            </td>
            <td class="value">GRADE: <c:out value="${studentdetails.student.classstudying}" /></td>
        </tr>

        <tr>
            <td class="srno">9.</td>
            <td class="label">
                School/Board annual examination last taken
                and result
            </td>
            <td class="value"><c:out value="${lastexam}" /></td>
        </tr>

        <tr>
            <td class="srno">10.</td>
            <td class="label">
                Whether qualified for promotion to higher<br> class,
                if so to which Class (in words)
            </td>
            <td class="value">
                <c:out value="${failpass}" />
            </td>
        </tr>

        <tr>
            <td class="srno">11.</td>
            <td class="label">
                Month upto which the Student has paid the School fees
            </td>
            <td class="value"><c:out value="${dues}" /></td>
        </tr>

        <tr>
            <td class="srno">12.</td>
            <td class="label">Total number of working days</td>
            <td class="value"><c:out value="${workingdays}" /></td>
        </tr>

        <tr>
            <td class="srno">13.</td>
            <td class="label">Total number of working days present</td>
            <td class="value"><c:out value="${present}" /></td>
        </tr>

        <tr>
            <td class="srno">14.</td>
            <td class="label">General conduct</td>
            <td class="value"><c:out value="${conduct}" /></td>
        </tr>

        <tr>
            <td class="srno">15.</td>
            <td class="label">Date of application for TC</td>
            <td class="value"><c:out value="${datecert}" /></td>
        </tr>

        <tr>
            <td class="srno">16.</td>
            <td class="label">Date of issue</td>
            <td class="value"><input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="10" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" > </td>
        </tr>

        <tr>
            <td class="srno">17.</td>
            <td class="label">Reason for leaving school</td>
            <td class="value"><c:out value="${leavingReason}" /></td>
        </tr>

        <tr>
            <td class="srno">18.</td>
            <td class="label">Any other remarks</td>
            <td class="value"> <c:out value="${Remarks}" /></td>
        </tr>

        <tr class="footer">
            <td colspan="3"></td>
        </tr>

        <tr class="sign-row">
            <td colspan="2">Class Teacher</td>
            <td>Academic Principal</td>
        </tr>

    </table>

</div>
 <div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>

</body>
</html>