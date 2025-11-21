<%--
    Document   : One to Five F A
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
		<div style="border: 2px solid brown; padding: 4px;">
			<table width="100%" height="20px" style="background-color: brown;">
				<tr>
					<td style="color: white; text-align: center;">Formative
						Assessments</td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td>Subject</td>
					<td>Reading<br>(10)
					</td>
					<td>Writing<br>(10)
					</td>
					<td>Listening<br>(10)
					</td>
					<td>Speaking<br>(10)
					</td>
					<td>Written test<br>(20)
					</td>
					<td>Total 60</td>
					<td>R to 15</td>
				</tr>
				<c:forEach items="${Parents.exammarks}" var="exammarks" begin="0"
					end="0" step="1">

					<c:set var="subjectOrder"
						value="English,Kannada,Urdu/Hindi,Mathematics,E.V.S" />
					<c:set var="subjectList" value="${fn:split(subjectOrder, ',')}" />

					<c:forEach var="subject" items="${subjectList}">
						<c:forEach var="submarks" items="${exammarks.subMarks}">
									
							<c:if test="${fn:trim(submarks.key) == fn:trim(subject)}">
								<c:set var="marksParts"
											value="${fn:split(submarks.value,'/')}" />
								<tr>
									<td>${submarks.key}
									</td>
									<td>
										<c:catch var="parseError">
											<fmt:parseNumber value="${fn:trim(marksParts[0])}"
												var="parsedNumber" />
										</c:catch> <c:choose>
											<c:when test="${empty parseError}">
												<fmt:formatNumber value="${parsedNumber}"
													maxFractionDigits="0" />
											</c:when>
											<c:otherwise>
								   				 ${marksParts[0]}
								  			</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:catch var="parseError">
											<fmt:parseNumber value="${fn:trim(marksParts[1])}"
												var="parsedNumber" />
										</c:catch> <c:choose>
											<c:when test="${empty parseError}">
												<fmt:formatNumber value="${parsedNumber}"
													maxFractionDigits="0" />
											</c:when>
											<c:otherwise>
								   				 ${marksParts[1]}
								  			</c:otherwise>
										</c:choose>
									
									</td>
									<td>
										<c:catch var="parseError">
											<fmt:parseNumber value="${fn:trim(marksParts[2])}"
												var="parsedNumber" />
										</c:catch> <c:choose>
											<c:when test="${empty parseError}">
												<fmt:formatNumber value="${parsedNumber}"
													maxFractionDigits="0" />
											</c:when>
											<c:otherwise>
								   				 ${marksParts[2]}
								  			</c:otherwise>
										</c:choose>
									
									</td>
									<td>
										<c:catch var="parseError">
											<fmt:parseNumber value="${fn:trim(marksParts[3])}"
												var="parsedNumber" />
										</c:catch> <c:choose>
											<c:when test="${empty parseError}">
												<fmt:formatNumber value="${parsedNumber}"
													maxFractionDigits="0" />
											</c:when>
											<c:otherwise>
								   				 ${marksParts[3]}
								  			</c:otherwise>
										</c:choose>
									
									</td>
									<td>
										<c:catch var="parseError">
											<fmt:parseNumber value="${fn:trim(marksParts[4])}"
												var="parsedNumber" />
										</c:catch> <c:choose>
											<c:when test="${empty parseError}">
												<fmt:formatNumber value="${parsedNumber}"
													maxFractionDigits="0" />
											</c:when>
											<c:otherwise>
								   				 ${marksParts[4]}
								  			</c:otherwise>
										</c:choose>
									</td>
									<td></td>
									<td></td>
								</tr>
							</c:if>
							<tr>
								<td>Kannada</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>Urdu/Hindi</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td rowspan="2">Mathematics</td>
								<td>Concept<br>(10)
								</td>
								<td>Activity<br>(10)
								</td>
								<td>Table<br>(10)
								</td>
								<td>Mental<br>(10)
								</td>
								<td>Written test<br>(20)
								</td>
								<td>Total 60</td>
								<td>R to 15</td>
							</tr>
							<tr>
								<td>&emsp;</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td rowspan="2">EVS</td>
								<td>Observation<br>(10)
								</td>
								<td>Identification<br>(10)
								</td>
								<td>Activity<br>(10)
								</td>
								<td>Project<br>(10)
								</td>
								<td>Written test<br>(20)
								</td>
								<td>Total 60</td>
								<td>R to 15</td>
							</tr>
							<tr>
								<td>&emsp;</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							</c:forEach>
							</c:forEach>
							</c:forEach>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td>Maximum Marks</td>
					<td>300</td>
					<td>Marks Obtained</td>
					<td>&emsp;&emsp;</td>
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
					<td>No. of Days Persent</td>
					<td></td>
				</tr>
			</table>

			<table width="100%" height="110px" style="margin-top: 5px;">
				<tr>
					<td rowspan="3"
						style="writing-mode: vertical-lr; font-weight: bold; transform: rotate(180deg);">
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
					<td style="border: 0px; text-align: center;">Parent's
						Signature</td>
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
