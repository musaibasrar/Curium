<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
table,td{
border-collapse:collapse;
}
p{
font-weight:normal;
 margin:0px;
}

</style>

</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/bsr/UserProcess/sessionTimeOut");
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
<jsp:useBean id="now" class="java.util.Date" scope="page" />
 <c:forEach items="${markssheetlist}" var="Parents">
<table align="center">
<tr>
<td style="font-family:bold;">Academic Year: 2024-25</td>
</tr>
<tr>
<td style="font-family:bold;">REPORT CARD</td>
</tr>
</table>


  <table align="center" style="border:1px solid black">
<tr>
<td><p>Scholar No.  </p>
<p>Name  </p>
<p>Father Name  </p>
<p>Mother Name </p> </td>
<td colspan="4"><p>430</p>
<p>Umam Khan</p>
<p>Mr Irfan Khan</p>
<p>Ms Azra Khan</p>
 </td>
 <td colspan="4" style="border-left:1px solid black"><p>Roll No.  </p>
<p>Class & sec  </p>
<p>Attendance </p>
<p>DOB </p> </td>
<td colspan="2"><p>530</p>
<p>V/A</p>
<p>200</p>
<p>12/12/2003</p>
 </td>
 <td colspan="2" style="border-left:1px solid black">  <img border="0" style="vertical-align: text-bottom;height: 160px;width: 160px;" alt="ideoholic" src="/lilyrose/images/lilyrose.png">   </td>
</tr>
<tr style="background-color:#A9A9A9">
<td style="border:1px solid black;">Scholastic Areas:</td>
<td colspan="6" style="border:1px solid black">Term-1</td>
<td colspan="6" style="border:1px solid black">Term-2</td>
</tr>
<tr style="background-color:#A9A9A9">

           					 	
<td style="border:1px solid black">Subject Name</td>
                       
                        <c:set var = "beginloop" value = "0"/>
					<c:set var = "endloop" value = "9"/>
<c:forEach items="${Parents.exammarks}" var="exammarks" begin="${beginloop}" end= "${endloop}" step="1">
<td style="border:1px solid black"><c:out value="${exammarks.examName}"/></td>
</c:forEach>
</tr>
<tr>

<c:forEach items="${markssheetlist}" var="Parents">
<td style="border:1px solid black">English</td>
<c:forEach items="${Parents.exammarks}" var="exammarks" begin="${beginloop}" end= "${endloop}" step="1" varStatus="status">

<td>
<table style=" border-collapse: collapse;width: 100%;border: 1px solid black;">
											<c:forEach items="${exammarks.subMarks}" var="submarks" >
	                                	<tr style="border: 1px solid black;">
	                                		<c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
	                                			<%-- <c:if test="${status.index eq 0 }">
	                                				<td style="border: 1px solid black;text-align: left;">${submarks.key}</td>	
	                                			</c:if> --%>
	                                		
	                                		<td style="border: 1px solid black;text-align: left;">${dateParts[0]}(${dateParts[1]})</td>
	                                	</tr>
	                                </c:forEach>
	                                		                     	
	                                	</table>
	                                	</td>
	                                	
	                                	 </c:forEach>
	                                	 </c:forEach>
	                                	 <c:set var = "beginloop" value = "${beginloop+10}"/>
           					 	<c:set var = "endloop" value = "${endloop+10}"/>
           					 		
           					 	
</tr>
<tr>
<td style="border:1px solid black">Hindi</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>
<tr>
<td style="border:1px solid black">Mathematics</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>
<tr>
<td style="border:1px solid black">Science</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>
<tr>
<td style="border:1px solid black">Social Science</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>
<tr>
<td style="border:1px solid black">Total</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>

</table>

<table>
<tr>
<td colspan="13" style="background-color:#A9A9A9">OTHER</td>
</tr>
<tr>
<td style="border:1px solid black">Computer</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>
<tr>
<td style="border:1px solid black">General Knowledge</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">50</td>
<td style="border:1px solid black">60</td>
<td style="border:1px solid black">10</td>
<td style="border:1px solid black">20</td>
<td style="border:1px solid black">30</td>
<td style="border:1px solid black">40</td>
<td style="border:1px solid black">50</td>
</tr>
<tr><td colspan="13" style="border-left: solid white;border-right: solid white"><br></td></tr>
<tr>
<td colspan="6" style="border:1px solid black">Co-Scholiastic Areas:Term-1[on a 3-point(A-C)grading scale]</td>
<td colspan="7" style="border:1px solid black">Co-Scholiastic Areas:Term-2[on a 3-point(A-C)grading scale]</td>
</tr>
<tr>
<td colspan="6" style="border:1px solid black;text-align:right;">Grade</td>
<td colspan="7" style="border:1px solid black;text-align:right;">Grade</td>
</tr>
<tr>
<td colspan="5" style="border:1px solid black">Work-education(or pre-vocational)</td>
<td colspan="1" style="border:1px solid black">A</td>
<td colspan="6" style="border:1px solid black">Work-education(or pre-vocational)</td>
<td colspan="1" style="border:1px solid black">A</td>
</tr>
<tr>
<td colspan="5" style="border:1px solid black">Art Education</td>
<td colspan="1" style="border:1px solid black">A</td>
<td colspan="6" style="border:1px solid black">Art Education</td>
<td colspan="1" style="border:1px solid black">A</td>
</tr>
<tr>
<td colspan="5" style="border:1px solid black">Health and Physical Education</td>
<td colspan="1" style="border:1px solid black">A</td>
<td colspan="6" style="border:1px solid black">Health and Physical Education</td>
<td colspan="1" style="border:1px solid black">A</td>
</tr>
<tr><td colspan="13" style="border-left: solid white;border-right: solid white"><br></td></tr>
<tr>
<td colspan="6" style="border:1px solid black;text-align:right;">Grade</td>
<td colspan="7" style="border:1px solid black;text-align:right;">Grade</td>
</tr>
<tr>
<td colspan="5" style="border:1px solid black">Discipline Term-1 [on a 3-point(A-C)grading scale]</td>
<td colspan="1" style="border:1px solid black">A</td>
<td colspan="6" style="border:1px solid black">Discipline Term-1[on a 3-point(A-C)grading scale]</td>
<td colspan="1" style="border:1px solid black">A</td>
</tr>
<tr>
<td colspan="13" style="border-left: solid white;border-right: solid white"><br></td></tr><tr>
<td colspan="3" style="border-left: solid white;">Class Teacher Remarks</td>
<td colspan="10" style="border-right: solid white;">Very Good </td>
</tr>
<tr>
<td colspan="13" style="border-left: solid white;border-right: solid white"><br></td></tr>
<tr>
<td colspan="3" style="border-left: solid white;">Result</td>
<td colspan="10" style="border-right: solid white;">Passed and promoted to next class</td>
</tr>
<tr>
<td colspan="13" style="border-left: solid white;border-right: solid white"><br></td></tr>
<tr>
<td colspan="13" style="border-left: solid white;border-right: solid white"><br></td></tr>
<tr>
<td colspan="13" style="border-left: solid white;border-right: solid white">Place:- JAORA</td>
</tr>
<tr>
<td colspan="5" style="border-left: solid white;">Date :- 18/03/2020
</td>
<td colspan="4">Signature<br>Class Teacher
</td>
<td colspan="4" style="border-right: solid white;">Signature<br>Principal
</td>
</tr>
<tr>
<td colspan="13" style="border-top:1px solid black;text-align:center;">Instruction</td>
</tr>
<tr>
<td colspan="13" style="text-align:center;">Grading Scale for Scholiastic Areas: Grades are awarded on B-point Grading Scale as follows</td>
</tr>
<tr>
<td colspan="4" style="border:1px solid black">MARKS RANGE</td>
<td style="border:1px solid black">91-100</td>
<td style="border:1px solid black">81-90</td>
<td style="border:1px solid black">71-80</td>
<td style="border:1px solid black">61-70</td>
<td style="border:1px solid black">51-60</td>
<td style="border:1px solid black">41-50</td>
<td style="border:1px solid black">33-40</td>
<td colspan="2" style="border:1px solid black">below 32</td>
</tr>
<tr>
<td colspan="4" style="border:1px solid black">GRADE</td>
<td style="border:1px solid black">A1</td>
<td style="border:1px solid black">A2</td>
<td style="border:1px solid black">B1</td>
<td style="border:1px solid black">B2</td>
<td style="border:1px solid black">C1</td>
<td style="border:1px solid black">C2</td>
<td style="border:1px solid black">D</td>
<td colspan="2" style="border:1px solid black">E</td>
</tr>

</table>
</c:forEach>
	</body>
</html>
