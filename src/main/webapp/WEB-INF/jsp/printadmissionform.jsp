<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <style>
     .page{
            width: 210mm;
            min-height: 297mm;
            margin: auto;
            padding: 15px;
            border: 1px solid #000;
            box-sizing: border-box;
        }
        span{
            display:inline-block;
            border-bottom: 1px solid black;
        }
         .print-btn{
            text-align: center;
            margin: 10px;
        }

        .adharbox{
    width:20px;
    height:20px;
    border:1px solid black;
    text-align:center;
    vertical-align:middle;
    font-weight:bold;
}
        @media print{
            .print-btn{
                display: none;
            }
        }
        </style>
    <title>Admission Form</title>
</head>
<body>
    <div class="page">
    <div align="center">
<table align="center" width="84%" style="float: left;text-align: center;" >
    <tr>
        <td rowspan="4">
        <img alt="school logo" src="/awami/images/awami.png" width="100px" height="60">
        </td>
        <td style="font-size: 45px;">
AWAMI HIGH SCHOOL
</td>
</tr>
<tr>
    <td style="font-size: 11px;">
        # Islamic Centre, Khan Mohalla, Lateef Road, Zaheerabad, Dist. Sangareddy-502220, T.G.
    </td>
</tr>
<tr>
    <td>
UDISE CODE 36160902283
    </td>
</tr>
<tr>
    <td>
      <b><u>ADMISSION FORM </u> </b>
    </td>
</tr>
<tr>
    <td align="center" colspan="2">
        <table>
            <tr>
                <td style="font-weight: bold;">Adm. Class</td>
                <td style="border: 1px solid black;width: 120px;">
                <c:set var="classonly" value="${fn:split(parents.student.classstudying, '--')}" />
                ${classonly[0]}
                </td>
                <td style="font-weight: bold;">&emsp;
                    &emsp;&emsp;&emsp;Medium</td>
                <td style="border: 1px solid black; width: 120px;">
                    English
                </td>
            </tr>
             <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
             <tr>
                <td style="font-weight: bold;">Adm. No.</td>
                <td style="border: 1px solid black;width: 120px;">
                ${parents.student.admissionnumber}
                </td>
                <td style="font-weight: bold;">
                    &emsp;
                    &emsp;&emsp;&emsp;Adm. Date</td>
                <td style="border: 1px solid black;width: 120px;">
                <fmt:formatDate value="${parents.student.admissiondate}" pattern="dd/MM/yyyy"/></td>
            </tr>
        </table>
    </td>
</tr>
</table>
<table width="15%" height="150px" style="border: 1px solid black;float: right;">
<tr>
<td>
 <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 100px;height: 120px;">
 </td>
 </tr>
</table>
<table width="100%" style="font-size: larger;">
<tr>
    <td>1. Name of the Student (IN BLOCK LETTERS)</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;text-transform:uppercase">${parents.student.name}</td>
</tr>
<tr>
    <td>2. Aadhaar Number</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 0px solid black;"><%-- ${parents.student.disabilitychild} --%>
    <table cellspacing="0" cellpadding="0">
            <tr>
                <c:forEach var="i" begin="0" end="11">
                    <td class="adharbox">
                        ${fn:substring(parents.student.disabilitychild, i, i+1)}
                    </td>
                </c:forEach>
            </tr>
        </table></td>
</tr>
<tr>
    <td>3. PEN Number</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 0px solid black;"> <table cellspacing="0" cellpadding="0">
            <tr>
                <c:forEach var="i" begin="0" end="11">
                    <td class="adharbox">
                        ${fn:substring(parents.student.pen, i, i+1)}
                    </td>
                </c:forEach>
            </tr>
        </table></td>
</tr>
<tr>
    <td>4. Name as per Aadhaar</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.student.name}</td>
</tr>
<tr>
    <td>5. Date of Birth</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">
    <fmt:formatDate value="${parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></td>
</tr>
<tr>
    <td>7. Name of the Father / Guardian</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.fathersname}</td>
</tr>
<tr>
    <td>&emsp;Qualification</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.fathersqualification}</td>
</tr>
<tr>
    <td>&emsp;Occupation</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.fatherscaste}</td>
</tr>
<tr>
    <td>8. Name of the Mother</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.mothersname}</td>
</tr>
<tr>
    <td>&emsp;Qualification</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.mothersqualification}</td>
</tr>
<tr>
    <td>&emsp;Occupation</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.motherscaste}</td>
</tr>
<tr>
    <td>9. Parents Contact No.</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.contactnumber}</td>
</tr>
<tr>
    <td>10. Caste & Sub-Cast</td>
    <td>:</td>
    <td width="350px" style="border-bottom: 1px solid black;">${parents.student.caste}</td>
</tr>

</table>
<table width="100%" style="font-size: larger;">
    <tr>
        <td>11. Blood Group&emsp;&emsp;<span style="width: 150px;">${parents.student.bloodgroup}</span>
        Height:&nbsp;<span style="width: 150px;">${parents.student.bloodgroup}</span>
    Weight:&nbsp;<span style="width: 150px;">${parents.student.bloodgroup}</span></td>
    </tr>
    <tr>
        <td>
            12. Previous School Details:&nbsp;Name<span style="width: 300px;">&nbsp;${parents.student.name}</span>
            Class:<span style="width: 130px;float:right;">&nbsp;${parents.student.stdlaststudied}</span>
        </td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;Max Marks<span style="width: 80px;">
         <c:set var="marks" value="${fn:split(parents.student.subsequentprogress, '/')}" />:&nbsp;&nbsp;
            ${marks[0]}</span>
        Secured Marks<span style="width: 80px;">:&nbsp;&nbsp;${marks[1]}</span>
    Percentage<span style="width: 80px;">:&nbsp;&nbsp;${marks[2]}</span>
Working Days<span style="width: 80px;float:right;">:&nbsp;&nbsp;</span></td>
    </tr>
    <tr>
        <td>
            13. TC/Record Sheet No. & Date&nbsp;&nbsp;<span style="width: 470px;float:right;">&nbsp;</span>
        </td>
    </tr>
    <tr>
        <td>
            14. Identification Mark&nbsp;:1.<span style="width: 520px;float:right;">
            <c:set var="idnfctn" value="${fn:split(parents.student.crecord, '/')}" />
            ${idnfctn[0]}
            </span>
        </td>
    </tr>
    <tr>
        <td>
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;2.<span style="width: 520px;float:right;"> ${idnfctn[1]}</span>
        </td>
    </tr>
    <tr>
        <td>15. Address :&nbsp;<span style="width: 620px;float:right;">${parents.addresspermanent}</span>
        </td>
    </tr>
    <tr>
        <td>&emsp;&emsp;&emsp;&emsp;&emsp;
        <span style="width: 620px;float:right;">&nbsp;</span>
    </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
</table>
<table>
    <hr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
    <tr>
        <td style="font-weight: bold;font-size: larger;text-align: center;">
            PARENTS	DECLARATION
        </td>
    </tr>
    <tr>
        <td style="font-style: italic;font-size: large;">
            I confirm that the provided	information, including	my	child<sup>'</sup>s	date of	birth	<span style="width: 120px;text-align:center">
            <fmt:formatDate value="${parents.student.dateofbirth}" pattern="dd/MM/yyyy"/></span> is	true.	
        </td>
    </tr>
    <tr>
        <td style="font-style: italic;font-size: large;">
            I agree	to	follow	the	school<sup>'</sup>s rules	and	understand	that misconduct	may	lead	to	admission	cancellation.
        </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
    <tr>
        <td style="font-style: italic;font-size: large;">
            Signature of Parent/Guardian&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            Signature	of	Headmaster
        </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           <br>
        </td>
    </tr>
     <tr>
        <td style="font-style: italic;font-size: large;">
           Note	:Fee once paid will	not	be	refunded under	any	circumstance
        </td>
    </tr>
</table>
</div>
    </div>
    <div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>

</body>
</html>    