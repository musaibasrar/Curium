<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exam Marks Report</title>
<style>
table,th,td{
border:1px solid black;
border-collapse:collapse;
}
</style>
</head>
<body onload="window.print();">
<div>
			
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText" style="display: none;"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">UID</th>
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Class</th>
						<th title="click to sort" class="headerText">Marks</th>



					</tr>
				</thead>

				<tbody>
					<c:forEach items="${newStudentList}" var="Parents" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="display: none;"><input type="checkbox"
								id="<c:out value="${Parents.student.sid}"/>" class="chcktbl" checked
								name="studentIDs"
								value="<c:out value="${Parents.student.sid}"/>" /></td>
								<td class="dataTextInActive"><a class="dataTextInActive"
								><c:out
										value="${Parents.student.studentexternalid}" /></a></td>
								<td class="dataTextInActive"><a class="dataTextInActive"
								><c:out
										value="${Parents.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${Parents.student.name}" /></td>
							<td class="dataText"><c:out value="${Parents.student.classstudying}" /></td>
							<td class="dataText">
									<c:if test="${newMarksDetails[status.index].marksobtained <= 100}">
											<c:set var="marksobtained" value="${newMarksDetails[status.index].marksobtained}" />
									</c:if>
									<c:if test="${newMarksDetails[status.index].marksobtained > 100}">
											<c:set var="marksobtained" value="A" />
									</c:if>
							<input type="text" id="studentMarks" style="border:none;" name="studentMarks" value="<c:out value="${marksobtained}" />"
								onkeypress="return (event.charCode >= 00 && event.charCode <=57) || event.charCode == 65"
								maxlength="4"
							><%-- <input type="text"
								id="studentMarks" 
								name="studentMarks"
								onkeyup="checkMandatory();"
								onkeypress="return event.charCode >= 00 && event.charCode <=57"
								maxlength="3"
								 /> --%>
								 <input type="hidden" id="marksid" name="marksid" value="<c:out value="${newMarksDetails[status.index].marksid}" />">
								 
								 </td>


						</tr>
					</c:forEach>
				</tbody>
				
			</table>

		</div>

</body>
</html>