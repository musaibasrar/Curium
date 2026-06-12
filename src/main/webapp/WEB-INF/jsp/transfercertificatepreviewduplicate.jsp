<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaving Certificate</title>

<style>
body{
    font-family: Arial, sans-serif;
    background:#f5f5f5;
}

.tc-container{
    width:850px;
    margin:20px auto;
    border:2px solid #000;
    background:#fff;
    padding:15px;
}

.school-header{
    text-align:center;
    line-height:1.4;
}

.school-header h2{
    margin:0;
    font-size:32px;
    font-weight:bold;
}

.school-header p{
    margin:2px 0;
    font-size:14px;
}

.title{
    text-align:center;
    font-size:24px;
    font-weight:bold;
    text-decoration:underline;
    margin:15px 0;
}

.top-info{
    width:100%;
    margin-bottom:10px;
}

.top-info td{
    padding:5px;
    font-weight:bold;
}

.tc-table{
    width:100%;
    border-collapse:collapse;
}

.tc-table td{
    padding:7px 5px;
    vertical-align:top;
    font-size:18px;
}

.sr{
    width:40px;
    font-weight:bold;
}

.field{
    width:320px;
    font-weight:bold;
}

.colon{
    width:20px;
    text-align:center;
}

.value{
    font-weight:normal;
}

.footer-text{
    text-align:center;
    margin-top:25px;
    font-size:16px;
}

.sign-table{
    width:100%;
    margin-top:30px;
}

.sign-table td{
    text-align:center;
    font-weight:bold;
    padding-top:30px;
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

</head>
<body>

<div class="tc-container">

    <div class="school-header">
        <div style="font-size:18px;font-weight:bold;">ORIGINAL</div>

        <h2>SCHOLARS' ENGLISH BOYS HIGH SCHOOL</h2>

        <p>NEHRU NAGAR, KAT-KAT GATE, AURANGABAD</p>

        <p>(Govt. GR No.2009 / (7/17/JR)/SE-1)</p>

        <p>UNDER: AMIABLE CHARITABLE TRUST</p>

        <p>UDISE CODE: 27191002841 &nbsp;&nbsp;&nbsp; MEDIUM: ENGLISH</p>
    </div>

    <div class="title">LEAVING CERTIFICATE</div>

    <table class="top-info">
        <tr>
            <td>Gen Reg. No : 737</td>
            <td align="right">T.C No : <c:out value="${tcno}" /></td>
        </tr>
        <tr>
            <td>IDS : 22469886884</td>
            <td align="right">UID : <c:out value="${studentdetails.student.studentexternalid}" /></td>
        </tr>
    </table>

    <table class="tc-table">

        <tr>
            <td class="sr">1.</td>
            <td class="field">Surname</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${lastexam}" /></td>
        </tr>

        <tr>
            <td class="sr">2.</td>
            <td class="field">Name of the Pupil</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.name}" /></td>
        </tr>

        <tr>
            <td class="sr">3.</td>
            <td class="field">Father's Name</td>
            <td class="colon">:</td>
            <td class="value"> <c:out value="${studentdetails.fathersname}" /></td>
        </tr>

        <tr>
            <td class="sr">4.</td>
            <td class="field">Mother's Name</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.mothersname}" /></td>
        </tr>

        <tr>
            <td class="sr">5.</td>
            <td class="field">Nationality</td>
            <td class="colon">:</td>
            <td class="value"> <c:out value="${studentdetails.student.nationality}" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Mother Tongue : <c:out value="${studentdetails.student.mothertongue}" />
            </td>
        </tr>

        <tr>
            <td class="sr">6.</td>
            <td class="field">Religion</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.religion}" /></td>
        </tr>

        <tr>
            <td class="sr">7.</td>
            <td class="field">Caste</td>
            <td class="colon">:</td>
            <td class="value">
                <c:out value="${studentdetails.student.caste}" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Sub Caste : <c:out value="${failpass}" />
            </td>
        </tr>

        <tr>
            <td class="sr">8.</td>
            <td class="field">Place of Birth</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.placeofbirth}" /></td>
        </tr>

        <tr>
            <td class="sr">9.</td>
            <td class="field">Date of Birth</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.dateofbirth}" /></td>
        </tr>

        <tr>
            <td></td>
            <td class="field">(In Words)</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${dateinword}" /></td>
        </tr>

        <tr>
            <td class="sr">10.</td>
            <td class="field">Last School Attended & Class</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.schoollastattended}" /> - <c:out value="${studentdetails.student.stdlaststudied}" /></td>
        </tr>

        <tr>
            <td class="sr">11.</td>
            <td class="field">Date of Admission & Class</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.admissiondate}" /> &nbsp; STD: <c:out value="${studentdetails.student.classadmittedin}" /></td>
        </tr>

        <tr>
            <td class="sr">12.</td>
            <td class="field">Progress</td>
            <td class="colon">:</td>
            <td class="value">
                <c:out value="${studentdetails.student.subsequentprogress}" /> &nbsp;&nbsp;&nbsp;&nbsp;
                Conduct : <c:out value="${conduct}" />
            </td>
        </tr>

        <tr>
            <td class="sr">13.</td>
            <td class="field">Date of Leaving the School</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.dateleaving}" /></td>
        </tr>

        <tr>
            <td class="sr">14.</td>
            <td class="field">Class of Studying & Since When</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.classstudying}" />, <c:out value="${studentdetails.student.admissiondate}" /></td>
        </tr>

        <tr>
            <td class="sr">15.</td>
            <td class="field">Reason for Leaving the School</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${studentdetails.student.reasonleaving}" /></td>
        </tr>

        <tr>
            <td class="sr">16.</td>
            <td class="field">Remarks</td>
            <td class="colon">:</td>
            <td class="value"><c:out value="${Remarks}" /></td>
        </tr>

    </table>

    <div class="footer-text">
        Certified that the above information is in accordance with the school record.
    </div>

    <div style="margin-top:20px;font-size:18px;">
        Date of Issue : <input
									name="dateoftc" type="text" class="textField" style="border: none;border-color: transparent;"
									 size="7" value="<fmt:formatDate type="date" value="${tcdetails.dateofissues}" pattern="yyyy-MM-dd"/>" >
    </div>

    <table class="sign-table">
    <tr><td><br></td></tr>
        <tr>
            <td>Clerk</td>
            <td>Class Teacher</td>
            <td>H.M</td>
        </tr>
        <tr><td colspan="3">(no change in any entry in this leaving certificate shall be made except by the authority issuing it and any infringement of this requirement<br>
        is liable to involve the imposition of penalty such as that of validation</td></tr>
    </table>

</div>
<div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>

</body>
</html>