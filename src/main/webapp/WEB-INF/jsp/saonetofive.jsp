<%--
    Document   : One to Five S A
    Created on : SEP 23, 2018, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report Card</title>
<style>
td, tr, table {
	border: 2px solid brown;
	border-collapse: collapse;
}
</style>
</head>
<body>

<c:forEach items="${markssheetlist}" var="Parents">
		<c:set var="grandTotal" value="0" scope="page" />
		<c:set var="maxTotal" value="300" scope="page" />

    <div style="border: 2px solid brown; padding: 4px;">
        <table width="100%" height="20px" style="background-color: brown;">
            <tr>
                <td style="color: white; text-align: center; font-weight: bold;">${examname}</td>
            </tr>
        </table>
        
        <table align="center" style="border: 0px;">
         <tr style="border: 0px;">
               <td style=" text-align: left; font-weight: bold;border: 0px;">Name </td>
               <td style=" text-align: left; font-weight: bold;border: 0px;">${Parents.parents.student.name}</td>
         </tr>
         <tr style="border: 0px;">
               <td style=" text-align: left; font-weight: bold;border: 0px;">Father's Name&emsp;&emsp;&emsp;&emsp; </td>
               <td style=" text-align: left; font-weight: bold;border: 0px;">${Parents.parents.fathersname}</td>
         </tr>
         <tr style="border: 0px;">
               <td style=" text-align: left; font-weight: bold;border: 0px;">Class </td>
               <td style=" text-align: left; font-weight: bold;border: 0px;">${Parents.parents.student.classstudying}</td>
         </tr>
          <tr style="border: 0px;">
               <td style=" text-align: left; font-weight: bold;border: 0px;">UID </td>
               <td style=" text-align: left; font-weight: bold;border: 0px;">${Parents.parents.student.studentexternalid}</td>
         </tr>
        </table>
        
        <table width="100%" style="margin-top: 5px; border-collapse: collapse; border: 1px solid #333;">
    <thead>
        <tr style="background-color: #e0e0e0; border-bottom: 2px solid #333;">
            <td style="font-weight: bold; padding: 5px; border: 1px solid #333;">Subject</td>
            <td style="font-weight: bold; padding: 5px; border: 1px solid #333; text-align: center;">Maximum Marks</td>
            <td style="font-weight: bold; padding: 5px; border: 1px solid #333; text-align: center;">Marks Obtained</td>
            <td style="font-weight: bold; padding: 5px; border: 1px solid #333; text-align: center;">R to 20</td>
        </tr>
    </thead>
    <tbody>
        <c:set var="subjectOrder" value="English,Kannada,Urdu/Hindi,Mathematics,E.V.S" />
        <c:set var="subjectList" value="${fn:split(subjectOrder, ',')}" />
        <c:set var="maxMarkPerSubject" value="60" />

        <c:forEach items="${markssheetlist}" var="Parents">
            <c:forEach items="${Parents.exammarks}" var="exammarks">
                <c:forEach var="subject" items="${subjectList}">
                    <c:forEach var="submarks" items="${exammarks.subMarks}">
                        
                        <c:if test="${fn:trim(submarks.key) == fn:trim(subject)}">
                            <c:set var="marksParts" value="${fn:split(submarks.value,'/')}" />
                            
                            <c:set var="numericTotalMark" value="0" />
                            <c:catch var="parseError1"><fmt:parseNumber value="${fn:trim(marksParts[0])}" var="mark1Value" integerOnly="true" /></c:catch>
                            <c:if test="${empty parseError1}"><c:set var="numericTotalMark" value="${numericTotalMark + mark1Value}" /></c:if>

                            <c:set var="totalMark" value="${numericTotalMark}" />
							<c:set var="grandTotal" value="${grandTotal + totalMark}" scope="page" />
                            <c:set var="rTo20Raw" value="${(totalMark / maxMarkPerSubject) * 20}" />
                            
                            <tr style="border-top: 1px solid #ccc;">
                                <td style="padding: 5px; border: 1px solid #333;">${submarks.key}</td>
                                <td style="padding: 5px; border: 1px solid #333; text-align: center;">${maxMarkPerSubject}</td>
                                <td style="padding: 5px; border: 1px solid #333; text-align: center;">
                                    ${totalMark}
                                </td>
                                <td style="padding: 5px; border: 1px solid #333; text-align: center;">
                                    <fmt:formatNumber value="${rTo20Raw}" pattern="#0.0" />
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
        </c:forEach>
    </tbody>
</table>
		<table width="100%" style="margin-top: 5px;">
			<tr>
				<td>Maximum Marks</td>
				<td>${maxTotal}</td>
				<td>Marks Obtained</td>
				<td>${grandTotal}</td>
			</tr>
			<tr>
				<td>Percentage</td>
				<td></td>
				<td>Grade</td>
				<td></td>
			</tr>
			<tr>
				<td>No. of Working Days</td>
				<td></td>
				<td>No. of Days Present</td>
				<td></td>
			</tr>
		</table>

		<div style="display: flex;">
			<table style="width: 50%; margin-top: 5px; margin-right: 2px;">
				<tr>
					<td rowspan="7"
						style="writing-mode: vertical-lr; font-weight: bold; transform: rotate(180deg); text-align: center;">Part - B</td>
					<td>Subject</td>
					<td>Grade</td>
				</tr>
				<tr>
					<td>Computer</td>
					<td></td>
				</tr>
				<tr>
					<td>Project Work</td>
					<td></td>
				</tr>
				<tr>
					<td>GK</td>
					<td></td>
				</tr>
				<tr>
					<td>Fine Work & SUPW</td>
					<td></td>
				</tr>
				<tr>
					<td>Physical Edu</td>
					<td></td>
				</tr>
				<tr>
					<td>Value Education</td>
					<td></td>
				</tr>

			</table>
			<table style="width: 50%; margin-top: 5px; margin-left: 2px;">
				<tr>
					<td rowspan="7"
						style="writing-mode: vertical-lr; font-weight: bold; transform: rotate(180deg); text-align: center;">Part - C</td>
					<td>Attitude Towards</td>
					<td>Grade</td>
				</tr>
				<tr>
					<td>Schoolmates</td>
					<td></td>
				</tr>
				<tr>
					<td>Teachers</td>
					<td></td>
				</tr>
				<tr>
					<td>Punctuality</td>
					<td></td>
				</tr>
				<tr>
					<td>Resposibilty</td>
					<td></td>
				</tr>
				<tr>
					<td>Cleanliness</td>
					<td></td>
				</tr>
				<tr>
					<td>Self discipline</td>
					<td></td>
				</tr>
			</table>
		</div>
		<table width="100%" height="110px" style="margin-top: 5px;">
			<tr>
				<td rowspan="3"
					style="writing-mode: vertical-lr; font-weight: bold; transform: rotate(180deg); text-align: center; background-color: brown; color: white;">
					Remarks</td>
				<td rowspan="3">Conduct</td>
				<td>Excellent(&emsp;&emsp;)</td>
				<td rowspan="3">Performance</td>
				<td>Excellent(&emsp;&emsp;)</td>
			</tr>
			<tr>
				<td>Good(&emsp;&emsp;)</td>
				<td>Good(&emsp;&emsp;)</td>
			</tr>
			<tr>
				<td>Average(&emsp;&emsp;)</td>
				<td>Average(&emsp;&emsp;)</td>
			</tr>


		</table>
		<table width="100%" style="border: 0px">
			<tr style="border: 0px">
				<td style="border: 0px"><br></td>
			</tr>
			<tr style="border: 0px">
				<td style="border: 0px"><br></td>
			</tr>
			<tr style="border: 0px">
				<td style="border: 0px"><br></td>
			<tr style="border: 0px">
				<td style="border: 0px">Class Teacher</td>
				<td style="border: 0px; text-align: center;">Parent's Signature</td>
				<td style="border: 0px; text-align: right">HM signature</td>
			</tr>
		</table>
	</div>
</c:forEach>
	<button id="print" type="button"
		onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'"
		class="hide">print</button>

</body>
</html>
