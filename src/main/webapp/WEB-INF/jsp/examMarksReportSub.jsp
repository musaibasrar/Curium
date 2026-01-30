<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exam Marks Report Sub</title>
<style>
table,th,td{
border:1px solid black;
border-collapse:collapse;
}
table {
  width: 100%;
  table-layout: fixed;   /* important */
}
th, td {
  border: 1px solid black;
  padding: 4px;
  text-align: center;
  word-wrap: break-word; /* ensures long text wraps */
}

</style>
</head>
<body onload="window.print();">
<div>
			
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">
				<thead> <tr> <th style="width:12%;">UID</th>
				 <th style="width:12%;">Admission Number</th>
				  <th style="width:20%;">Name</th>
				   <th style="width:12%;">Class</th>
				    <th style="width:10%;">Marks</th>
				     <th style="width:8%;">A1</th> 
				     <th style="width:8%;">A2</th>
				      <th style="width:8%;">A3</th>
				       <th style="width:8%;">A4</th>
				        </tr>
				         </thead>

				<tbody>
					<c:forEach items="${studentmarkmap}" var="Parents" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="display: none;"><input type="checkbox"
								id="<c:out value="${Parents.key.student.sid}"/>" class="chcktbl" checked
								name="studentIDs"
								value="<c:out value="${Parents.key.student.sid}"/>" /></td>
								<td class="dataTextInActive"><a class="dataTextInActive"
								><c:out
										value="${Parents.key.student.studentexternalid}" /></a></td>
								<td class="dataTextInActive"><a class="dataTextInActive"
								><c:out
										value="${Parents.key.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${Parents.key.student.name}" /></td>
							<td class="dataText"><c:out value="${Parents.key.student.classstudying}" /></td>
								
							<c:choose>
    							<c:when test="${not empty Parents.value}">
    								<c:set var="totalColumns" value="5" />
								<c:set var="printedCount" value="0" />
								<c:forEach items="${Parents.value}" var="marksobtained">
							<td class="dataText">
									<c:if test="${marksobtained.value <= 100}">
											<c:set var="marksscored" value="${marksobtained.value}" />
									</c:if>
									<c:if test="${marksobtained.value > 100}">
											<c:set var="marksscored" value="A" />
									</c:if>
							<input type="text" style="border:none;" id="studentMarks" name="studentMarks" value="<c:out value="${marksscored}" />"
								onkeypress="return (event.charCode >= 00 && event.charCode <=57) || event.charCode == 65"
								maxlength="4" style="border-radius:4px;"
							>
								 <input type="hidden" id="marksid" name="marksid" value="<c:out value="${marksobtained.key}" />">
								 
								 </td>
								 <c:set var="printedCount" value="${printedCount + 1}" />
								 </c:forEach>
								 <c:forEach begin="${printedCount + 1}" end="${totalColumns}">
										<td class="dataText">
											<%-- <input type="text"	name="studentMarks" value="" onkeypress="return (event.charCode >= 48 && event.charCode <=57) || event.charCode == 65"
											maxlength="4" style="width: 50px; border-radius: 4px;" /> <input
											type="hidden" name="marksid" value="" /> --%></td>
									</c:forEach>
								 </c:when>
								<c:otherwise>
									<!-- fallback when Parents.value is null -->
									<td class="dataText"><input type="text" style="border:none;"
										name="studentMarks" value="" maxlength="4"
										style="border-radius: 4px;" /> <input 
										type="hidden" name="marksid" value="" /></td>
								</c:otherwise>
								</c:choose>	
							</tr>
					</c:forEach>
				</tbody>
				
			</table>

		</div>


</body>
</html>