<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Marks Card Single Exam (2020-21)</title>
    <link rel="stylesheet" href="styles.css">
    <style>
    body {
    font-family: Arial, sans-serif;
    //background-color: #f4f4f4;
    margin: 0;
    padding: 20px;
}

body::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: url("/alalmas/images/alalmas${branchid}.png") no-repeat center;
    background-size: contain;
    opacity: 0.1; /* Adjust transparency */
    z-index: -1;
}

.marks-card {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    margin: auto;
}

header, .student-info, table, .co-scholastic-areas, footer {
    margin-bottom: 20px;
}

header h1, header h2, header h3 {
    text-align: center;
}

header h1 {
    font-size: 24px;
    margin-bottom: 5px;
}

header h2 {
    font-size: 18px;
    margin-bottom: 10px;
}

header h3 {
    font-size: 16px;
    margin-bottom: 20px;
}

.student-info p {
    margin: 5px 0;
}

table {
    width: 100%;
    border-collapse: collapse;
}

table th, table td {
    border: 1px solid black;
    padding: 8px;
    text-align: left;
}

table th {
     background-color:blue;
     color:white;
}

.co-scholastic-areas h4 {
    margin-bottom: 10px;
}

.co-scholastic-areas p {
    margin: 5px 0;
}

footer p {
    margin: 5px 0;
}
    
    </style>
    <style>
     .student-info {
        display: flex;
        justify-content: space-between;
        align-items: center;
       // border: 1px solid #ccc;
       // padding: 15px;
       // max-width: 500px; /* Adjust width as needed */
    } 

    .student-details {
        flex: 1;
    }

    .student-pic {
        width: 100px; /* Adjust size as needed */
        height: 100px;
        border-radius: 50%; /* Makes it circular */
        object-fit: cover;
        margin-left: 15px; /* Space between text and image */
    }
</style>
        <script>
        function printAndHide() {
            const button = document.getElementById('print');
            button.style.display = 'none'; // Hide the button completely
            window.print();
            button.style.display = 'inline-block'; // Restore the button after printing
        }
    </script>
    
</head>
<body style="border-style:dotted;">
    <div>
     <c:forEach items="${markssheetlist}" var="Parents">
    <table align="center" width="700px;" style="border:none;margin-bottom:0px;">
        <%-- ${branchname} --%>
        <tr><td style="border:none;text-align:right;padding:0px;"><img border="0" style="vertical-align: text-bottom;height: 120px;width: 135px;" alt="ideoholic" src="/alalmas/images/alalmas.png"></td>
        <td style="border:none;text-align:center;padding:0px;"><img border="0" style="height: 180px;width: 600px;" alt="logo" src="/alalmas/images/header.png"></td>
       
        </tr>
       
        <tr><td style="border:none;"></td><td style="font-size:15px;font-weight:bold;text-align:center;border:none;padding:0px;">
        English and Shoba-E-Hifz and IIT Foundation Course
        </td></tr>
        <tr><td style="border:none;"></td><td style="font-size:15px;font-weight:bold;text-align:center;border:none;padding:0px;">
        Marks Card<br> ${examname} (${currentAcademicYear})
        </td></tr>
        </table>
        <section class="student-info" style="margin-bottom:0px;">
    <div class="student-details">
        <p><strong style="color:red">STUDENT NAME:</strong><strong style="color:blue;text-transform:uppercase;"> ${Parents.parents.student.name}</strong></p>
        <p><strong style="color:red">FATHER NAME:</strong><strong style="color:blue;text-transform:uppercase;"> ${Parents.parents.fathersname}</strong></p>
        <p><strong style="color:red">CLASS:</strong><strong style="color:blue"><c:set var="dateClassParts" value="${fn:split(Parents.parents.student.classstudying,'--')}" /> ${dateClassParts[0]}</strong></p>
        <p><strong style="color:red">SECTION:</strong><strong style="color:blue"> ${dateClassParts[1]}</strong></p>
        <p><strong style="color:red">ROLL NO.:</strong> <strong style="color:blue">${Parents.parents.student.sts}</strong></p>
    </div>
</section>        <table>
            <thead>
                <tr>
                    <th>Subjects</th>
                    <th>Max. Marks</th>
                    <th>Min. Marks</th>
                    <th>Marks Obt.</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${Parents.exammarks}" var="exammarks" begin="0" end="0" step="1">
             <c:forEach items="${exammarks.subMarks}" var="submarks" >
                <tr>
                    <td style="color:red;padding:0px;">${submarks.key}</td>
                     <c:set var="dateParts" value="${fn:split(submarks.value,'/')}" />
                    <td style="padding:0px;text-align:center;"><fmt:formatNumber value="${dateParts[0]}" maxFractionDigits="0"/></td>
                    <td style="padding:0px;text-align:center;"><fmt:formatNumber value="${dateParts[1]}" maxFractionDigits="0"/></td>
                    <td style="padding:0px;text-align:center;"><fmt:formatNumber value="${dateParts[2]}" maxFractionDigits="0"/></td>
                </tr>
                </c:forEach>
               
                
                <tr>
                    <td style="padding:0px;"><strong>Total</strong></td>
                    <td style="padding:0px;text-align:center;"><strong><fmt:formatNumber value="${exammarks.totalMarks}" maxFractionDigits="0"/></strong></td>
                    <td style="padding:0px;text-align:center;"><strong><fmt:formatNumber value="${exammarks.totalMinMarks}" maxFractionDigits="0"/></strong></td>
                    <td style="padding:0px;text-align:center;"><strong><fmt:formatNumber value="${exammarks.totalMarksObtained}" maxFractionDigits="0"/></strong></td>
                </tr>
                <tr>
                    <td style="padding:0px;"><strong>Rank</strong></td>
                    <td colspan="3" style="padding:0px;text-align:center;">${exammarks.rank}</td>
                </tr>
                <tr>
                    <td style="padding:0px;"><strong>Percentage</strong></td>
                    <td colspan="3" style="padding:0px;text-align:center;">
                     <fmt:formatNumber value="${exammarks.percentage}" type="number" maxFractionDigits="2" minFractionDigits="2"/></td>
                </tr>
                <tr>
                    <td style="padding:0px;"><strong>Grade</strong></td>
                    <td colspan="3" style="padding:0px;text-align:center;">${exammarks.resultclass}</td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>
        <table>
        <thead>
            <tr>
            <th style="background-color:white;color:black;padding:0px;">
            REMARKS
            </th>
            <th style="background-color:white;color:black;padding:0px;">
            PERFORMANCE
            </th>
            <th style="background-color:white;color:black;padding:0px;">
            CONDUCT
            </th>
            <th style="background-color:white;color:black;padding:0px;">
            ATTENTIVENESS
            </th>
            <th style="background-color:white;color:black;padding:0px;">
            HOMEWORK
            </th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <td style="padding:0px;">&nbsp; </td><td style="padding:0px;"> </td><td style="padding:0px;"> </td>
            <td style="padding:0px;"> </td><td style="padding:0px;"> </td>
            </tr>
            </tbody>
        </table>
         <section class="co-scholastic-areas">
            <h4 style="color:red;">Co-Scholastic Areas</h4>
            <p style="margin:0px;padding-bottom:8px;">1. Discipline in the classroom:</p>
            <p style="margin:0px;padding-bottom:8px;">2. Behavior / Conduct with teachers & classmates:</p>
            <p style="margin:0px;padding-bottom:8px;">3. Regularity & Neatness in doing HW/CW:</p>
            <p style="margin:0px;padding-bottom:8px;">4. Comes to School: On time [&emsp;&emsp;] / Sometime Late [&emsp;&emsp;] / Always Late [&emsp;&emsp;]</p>
        </section>
        <table style="border: 0px solid #ddd;">
        <tr><td style="border: 0px solid #ddd;"><br></td></tr>
        </table>
        <footer>
            <p>Parent's Signature &emsp; &emsp; &emsp; &emsp; &emsp;Class Teacher's Signature &emsp; &emsp; &emsp; &emsp; &emsp;Principal's Signature</p>
        </footer>
                                    </c:forEach>
    </div>
    <button id="print" type="button" onclick="printAndHide()">Print</button>
    
</body>
</html>



