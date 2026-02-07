<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Marks Sheet</title>

<style>

/* ===== PRINT SETTINGS ===== */
@page {
    size: auto;
    margin: 1cm;
}

@media print {
    body { margin: 0; }
}

/* ===== COMMON ===== */
body {
    font-family: Tahoma;
    font-size: 12px;
}

/* ===== HEADER ===== */
.title {
    font-size: 16px;
    font-weight: bold;
    text-align: center;
}

.address {
    text-align: center;
    font-size: 12px;
}

/* ===== MAIN TABLE ===== */
.main-border {
    width: 100%;
    border: 1px solid #000;
    border-collapse: collapse;
    page-break-inside: avoid;
}

.cell {
    border-right: 1px solid #000;
    border-bottom: 1px solid #000;
    padding: 6px;
    text-align: center;
}

.left {
    text-align: left;
}

.last-col {
    border-right: none;
    }

/* ===== SUBJECT HEADER ===== */
.subject-header {
    transform: rotate(-90deg);
    height: 150px;
    vertical-align: middle;
    font-weight: bold;
    font-size: 11px;
    </style>
	<script type="text/javascript">
		window.onload = function(){
		window.print();
		}
        </script>
	<title> </title>
        
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

</style>

<script>
window.onload = function(){
    window.print();
};
</script>

</head>

<body>

<!-- ===== SCHOOL HEADER ===== -->
<table width="100%">
    <tr>
        <td width="20%" align="center">
            <img src="/daralmajd/images/daralmajd.jpg" width="80" height="80">
        </td>
        <td width="80%">
            <div class="title">${branchname}</div>
            <div class="address">${branchaddress}</div>
        </td>
    </tr>
</table>

<br>

<!-- ===== CLASS / EXAM INFO ===== -->
<table width="100%">
    <tr>
        <td align="left"><b>Class:</b> ${examclass}</td>
        <td align="center">
    <b>Exam:</b>
    <c:forEach items="${markssheetlist}" var="p" varStatus="ps">
        <c:if test="${ps.index == 0}">
            <c:forEach items="${p.exammarks}" var="em" varStatus="es">
                <c:if test="${es.index == 0}">
                    ${em.examName}
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>
</td>

        <td align="right"><b>Academic Year:</b> ${currentAcademicYear}</td>
    </tr>
</table>

<br>

<!-- ===== MARKS TABLE ===== -->
<table class="main-border">

    <!-- HEADER -->
    <tr>
        <td class="cell"><b>Sl.No</b></td>
        <td class="cell"><b>UID</b></td>
        <td class="cell"><b>Student Name</b></td>
        <td class="cell"><b>Father Name</b></td>

       <c:forEach items="${markssheetlist}" var="p" varStatus="ps">
    <c:if test="${ps.index == 0}">
        <c:forEach items="${p.exammarks}" var="em" varStatus="es">
            <c:if test="${es.index == 0}">
                <c:forEach items="${em.subMarks}" var="sub">
                    <td class="cell subject-header">
                        ${sub.key}
                    </td>
                </c:forEach>
            </c:if>
        </c:forEach>
    </c:if>
</c:forEach>


        <td class="cell subject-header">Total Obtained</td>
        <td class="cell subject-header">Total Marks</td>
        <td class="cell subject-header">%</td>
        <td class="cell subject-header last-col">Rank</td>
    </tr>

    <!-- DATA -->
    <c:forEach items="${markssheetlist}" var="Parents" varStatus="s">
        <tr>
            <td class="cell">${s.index + 1}</td>
            <td class="cell">${Parents.parents.student.studentexternalid}</td>
            <td class="cell left">${Parents.parents.student.name}</td>
            <td class="cell left">${Parents.parents.fathersname}</td>

            <c:forEach items="${Parents.exammarks[0].subMarks}" var="sub">
                <c:set var="p" value="${fn:split(sub.value,'_')}" />
                <c:set var="m" value="${fn:split(p[0],'/')}" />
                <td class="cell">${m[0]}/${m[1]}</td>
            </c:forEach>

            <td class="cell">
                <fmt:formatNumber value="${Parents.exammarks[0].totalMarksObtained}" maxFractionDigits="0"/>
            </td>
            <td class="cell">
                <fmt:formatNumber value="${Parents.exammarks[0].totalMarks}" maxFractionDigits="0"/>
            </td>
            <td class="cell">
                <fmt:formatNumber value="${Parents.exammarks[0].percentage}" maxFractionDigits="1"/>
            </td>
            <td class="cell last-col">${Parents.exammarks[0].rank}</td>
        </tr>
    </c:forEach>

</table>

<br><br>

<!-- ===== SIGNATURE ===== -->
<table width="100%">
    <tr>
        <td align="left">Class Teacher</td>
        <td align="center">Parent</td>
        <td align="right">
            <img src="/daralmajd/images/principalsignature.png" width="60" height="28"><br>
            Principal
        </td>
    </tr>
</table>

 <%-- <c:forEach items="${markssheetlist}" var="Parents" begin="0" end="0" step="1">
			<table style="border-collapse: collapse;width: 100%;">
											
                            <tr style="border-color:#000000">
                                <td class="namedetails" style="width: 10%">Student Name</td>
                                
                                <td style="width: 90%">
                                
                                <c:set var = "beginloop" value = "0"/>
           					 	<c:set var = "endloop" value = "0"/>
           					 	<c:forEach begin="0" end="${endloop}" step="1">
           					 		
           					 			<table style=" border-collapse: collapse;width: 100%;">
                            	
                            	<tbody>
                            	
                             	<tr>
                             	<c:forEach items="${Parents.exammarks}" var="exammarks" begin="${beginloop}" end= "${endloop}" step="1">
                                <td>
                                	<table style=" border-collapse: collapse;width: 100%;border: 1px solid black;">
											
	                                	<tr style="border: 1px solid black;">
	                                	<c:forEach items="${exammarks.subMarks}" var="submarks" >
	                                		
	                                		
            										<td style="border: 1px solid black;text-align: left;">
            										
            										<!-- Subject Name -->
            										<c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
	                                				${submarks.key}</br>
	                                				</td>
            										
	                                	</c:forEach>
	                                			<td style="border: 1px solid black;text-align: left;">Total Marks Obtained</td>
                            					<td style="border: 1px solid black;text-align: left;">Total Marks</td>
                            					<td style="border: 1px solid black;text-align: left;">Percentage</td>
                            					<td style="border: 1px solid black;text-align: left;">Grade</td>
                            					<td style="border: 1px solid black;text-align: left;">Rank</td>
                            					
	                                	</tr>
	                                
	                                		                                               	
	                                	</table>
                                </td>
                               </c:forEach>
                                </tr>
                                 
                       
                   				 </tbody>
                            </table>
           					 	</c:forEach>
                                
                                </td>
                                
                             </tr>
                            
                            </table>
                            
                            </c:forEach> --%>
                            
                             <c:forEach items="${markssheetlist}" var="Parents" begin="1" end= "1" step="1">
                            
                        <div style="page-break-inside: avoid;border-style: solid;border-width: thin;">   
                        	

			<TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            

			<table style="border-collapse: collapse;width: 100%;table-layout: fixed">
											
                            <tr style="border-color:#000000">
                                <td class="namedetails" style="width: 20%"><label style="font-weight: bold;text-transform: capitalize;"><c:out value="Student Name"/></label></td>
                                
                                <td style="width: 80%">
                                  <c:set var = "beginloop" value = "0"/>
           					 	<c:set var = "endloop" value = "4"/>
           					 	<c:forEach begin="0" end="${endloop}" step="1">
                                
           					 			<table style=" border-collapse: collapse;width: 100%;">
                            	
                            	<tbody>
                            	
                             	<tr>
                             	<c:forEach items="${Parents.exammarks}" var="exammarks"  begin="${beginloop}" end= "${endloop}" step="1">
                                <td>
                                	<table style=" border-collapse: collapse;width: 100%;border: 1px solid black;table-layout: fixed;">
											
	                                	<tr style="border: 1px solid black;">
	                                	<c:forEach items="${exammarks.subMarks}" var="submarks" >
	                                		
            										<td style="border: 1px solid black;text-align: center;transform: rotate(-90deg);height: 160px;">
                                                			${submarks.key}
	                                				</td>
	                                	</c:forEach>
	                                			<td style="border: 1px solid black;text-align: center;transform: rotate(-90deg);height: 160px;">Total Marks Obtained</td>
                            					<td style="border: 1px solid black;text-align: center;transform: rotate(-90deg);height: 160px;">Total Marks</td>
                            					<td style="border: 1px solid black;text-align: center;transform: rotate(-90deg);height: 160px;">Percentage</td>
                            					<%-- <td style="border: 1px solid black;text-align: left;">Grade</br>${exammarks.resultclass}</td> --%>
                            					<td style="border: 1px solid black;text-align: center;transform: rotate(-90deg);height: 160px;">Rank</td>
                            					
	                                	</tr>
	                                
	                                		                                               	
	                                	</table>
	                                	<c:set var = "beginloop" value = "${beginloop+5}"/>
           					 	<c:set var = "endloop" value = "${endloop+5}"/>
           					 	</c:forEach>
                                </td>
                               </c:forEach>
                                </tr>
                                 
                       
                   				 </tbody>
                            </table>
                                </td>
                                
                             </tr>
                            
                            </table>
		</div>
                                 
                            </c:forEach>
                        <c:forEach items="${markssheetlist}" var="Parents">
                        
                        <div style="page-break-inside: avoid;border-style: solid;border-width: thin;">   
                        	

			<TABLE  width="100%" border="1" style="page-break-inside: avoid;border-collapse:collapse;">
                <tr>

                    <td colspan="4" ></td>

                </tr>
            </TABLE>
            

			<table style="border-collapse: collapse;width: 100%;table-layout: fixed">
											
                            <tr style="border-color:#000000">
                                <td class="namedetails" style="width: 20%"><label style="font-weight: bold;text-transform: capitalize;"><c:out value="${Parents.parents.student.name}"/></label></td>
                                
                                <td style="width: 80%">
                                
                                <c:set var = "beginloop" value = "0"/>
           					 	<c:set var = "endloop" value = "4"/>
           					 	<c:forEach begin="0" end="${endloop}" step="1">
           					 		
           					 			<table style=" border-collapse: collapse;width: 100%;">
                            	
                            	<tbody>
                            	
                             	<tr>
                             	<c:forEach items="${Parents.exammarks}" var="exammarks" begin="${beginloop}" end= "${endloop}" step="1">
                                <td>
                                	<table style=" border-collapse: collapse;width: 100%;border: 1px solid black;table-layout: fixed;">
											
	                                	<tr style="border: 1px solid black;">
	                                	<c:forEach items="${exammarks.subMarks}" var="submarks" >
	                                		
	                                		<c:choose>
											        <c:when test="${submarks.key == 'Drawing'}">
            										<td style="border: 1px solid black;text-align: left;">
            										        <!-- Subject Name -->
            										<c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
	                                				<%-- ${submarks.key}</br>  --%>
            										${dateParts[2]}</td>
        										</c:when>
        										<c:otherwise>
            										<td style="border: 1px solid black;text-align: left;">
            										<!-- Subject Name -->
            										<c:set var="dateParts" value="${fn:split(submarks.value,'_')}" />
	                                				<%-- ${submarks.key}</br> --%>
	                                				<c:set var="marksParts" value="${fn:split(dateParts[0],'/')}" />
	                                				<fmt:formatNumber value="${marksParts[0]}" type="number" minFractionDigits="0" maxFractionDigits="0"/>/<fmt:formatNumber value="${marksParts[1]}" type="number" minFractionDigits="0" maxFractionDigits="0"/><%-- (${dateParts[2]}) (${dateParts[1]})--%></td>
        										</c:otherwise>
    										</c:choose>
	                                	</c:forEach>
	                                			<td style="border: 1px solid black;text-align: left;"><fmt:formatNumber value="${exammarks.totalMarksObtained}" type="number" minFractionDigits="0" maxFractionDigits="0"/></td>
                            					<td style="border: 1px solid black;text-align: left;"><fmt:formatNumber value="${exammarks.totalMarks}" type="number" minFractionDigits="0" maxFractionDigits="0"/></td>
                            					<td style="border: 1px solid black;text-align: left;"><fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${exammarks.percentage}" /></td>
                            					<%-- <td style="border: 1px solid black;text-align: left;">Grade</br>${exammarks.resultclass}</td> --%>
                            					<td style="border: 1px solid black;text-align: left;">${exammarks.rank}</td>
                            					
	                                	</tr>
	                                
	                                		                                               	
	                                	</table>
                                </td>
                               </c:forEach>
                                </tr>
                                 
                       
                   				 </tbody>
                            </table>
                            		<c:set var = "beginloop" value = "${beginloop+5}"/>
           					 	<c:set var = "endloop" value = "${endloop+5}"/>
           					 	</c:forEach>
                                
                                </td>
                                
                             </tr>
                            
                            </table>
		</div>
                                 
                        </c:forEach>
                        
                        <TABLE id="dataTable" width="100%" border="0"
			style="page-break-inside:avoid; border-collapse: collapse;">
						<br>
						<!-- <tr>
							<td><br><br><br></td>
						</tr> -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><img src="/daralmajd/images/principalsignature.png" width="60" height="28"/></td>
						</tr>
				<tr>
				<td></td>
				<td align="left">Class Teacher</td>	
					<td align="centre">Parent</td>
					<td align="centre">Principal</td>
					</tr>
                    
		</TABLE>
	</form>
	
	
</body>
</html>
