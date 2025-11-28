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
	<c:set var="grandTotal" value="0" scope="page" />
	<c:set var="maxTotal" value="300" scope="page" />
	<c:forEach items="${markssheetlist}" var="Parents">
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
        
        <table width="100%" style="margin-top: 5px; border-collapse: collapse;">
            <thead>
                <tr style="background-color: #f0f0f0; border-bottom: 2px solid brown;">
                    <td rowspan="2" style="font-weight: bold; padding: 5px;">SUBJECT</td>
                    <td colspan="4" style="text-align: center; font-weight: bold;"></td>
                    <td rowspan="2" style="font-weight: bold; padding: 5px;">Written Test (20)</td>
                    <td rowspan="2" style="font-weight: bold; padding: 5px;">Total 60</td>
                    <td rowspan="2" style="font-weight: bold; padding: 5px;">R to 15</td>
                </tr>
                <tr>
                    <td style="text-align: center; font-weight: normal; padding: 5px;">Reading (10)</td>
                    <td style="text-align: center; font-weight: normal; padding: 5px;">Writing (10)</td>
                    <td style="text-align: center; font-weight: normal; padding: 5px;">Listening (10)</td>
                    <td style="text-align: center; font-weight: normal; padding: 5px;">Speaking (10)</td>
                </tr>
            </thead>
            
            <tbody>
                <c:set var="subjectOrder" value="English,Kannada,Urdu/Hindi,Mathematics,E.V.S" />
                <c:set var="subjectList" value="${fn:split(subjectOrder, ',')}" />
                
                <c:forEach items="${Parents.exammarks}" var="exammarks">
                    <c:forEach var="subject" items="${subjectList}">
                        <c:forEach var="submarks" items="${exammarks.subMarks}">
                            <c:if test="${fn:trim(submarks.key) == fn:trim(subject)}">
                                <c:set var="marksParts" value="${fn:split(submarks.value,'/')}" />
                                
                               <c:set var="numericTotalMark" value="0" />

                                <c:catch var="parseError1">
                                    <fmt:parseNumber value="${fn:trim(marksParts[0])}" var="mark1Value" integerOnly="true" />
                                </c:catch>
                                <c:if test="${empty parseError1}">
                                    <c:set var="numericTotalMark" value="${numericTotalMark + mark1Value}" />
                                </c:if>

                                <c:catch var="parseError2">
                                    <fmt:parseNumber value="${fn:trim(marksParts[1])}" var="mark2Value" integerOnly="true" />
                                </c:catch>
                                <c:if test="${empty parseError2}">
                                    <c:set var="numericTotalMark" value="${numericTotalMark + mark2Value}" />
                                </c:if>

                                <c:catch var="parseError3">
                                    <fmt:parseNumber value="${fn:trim(marksParts[2])}" var="mark3Value" integerOnly="true" />
                                </c:catch>
                                <c:if test="${empty parseError3}">
                                    <c:set var="numericTotalMark" value="${numericTotalMark + mark3Value}" />
                                </c:if>

                                <c:catch var="parseError4">
                                    <fmt:parseNumber value="${fn:trim(marksParts[3])}" var="mark4Value" integerOnly="true" />
                                </c:catch>
                                <c:if test="${empty parseError4}">
                                    <c:set var="numericTotalMark" value="${numericTotalMark + mark4Value}" />
                                </c:if>

                                <c:catch var="parseError5">
                                    <fmt:parseNumber value="${fn:trim(marksParts[4])}" var="mark5Value" integerOnly="true" />
                                </c:catch>
                                <c:if test="${empty parseError5}">
                                    <c:set var="numericTotalMark" value="${numericTotalMark + mark5Value}" />
                                </c:if>
                                
                                <c:set var="totalMark" value="${numericTotalMark}" />
                                
                                <c:set var="grandTotal" value="${grandTotal + totalMark}" scope="page" />
                                <c:set var="rTo15Raw" value="${(totalMark / 60) * 15}" />
                                
                                <tr style="border-top: 1px solid #ccc;">
                                    <td style="font-weight: bold;">${submarks.key}</td>
                                    
                                    <c:choose>
                                        <c:when test="${submarks.key eq 'Mathematics'}">
                                            <td><div class="col-header-math">Concept (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[0]}</td>
                                            <td><div class="col-header-math">Activity (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[1]}</td>
                                            <td><div class="col-header-math">Tables (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[2]}</td>
                                            <td><div class="col-header-math">Mental Ability (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[3]}</td>
                                        </c:when>
                                        
                                        <c:when test="${submarks.key eq 'E.V.S'}">
                                            <td><div class="col-header-evs">Obser-vation (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[0]}</td>
                                            <td><div class="col-header-evs">Identifi-cation (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[1]}</td>
                                            <td><div class="col-header-evs">Activity (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[2]}</td>
                                            <td><div class="col-header-evs">Project (10)</div><hr style="border-top: 1px solid black; margin: 2px 0;"> ${marksParts[3]}</td>
                                        </c:when>
                                        
                                        <c:otherwise>
                                            <td>${marksParts[0]}</td>
                                            <td>${marksParts[1]}</td>
                                            <td>${marksParts[2]}</td>
                                            <td>${marksParts[3]}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    <td>${marksParts[4]}</td>
                                    
                                    <td style="font-weight: bold;">
                                        ${totalMark}
                                    </td>
                                    
                                    <td style="font-weight: bold;">
                                        <fmt:formatNumber value="${rTo15Raw}" pattern="#0.0" />
                                    </td>
                                </tr>
                            </c:if>
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
                <td>No. of Days Persent</td>
                <td></td>
            </tr>
        </table>

        <table width="100%" height="110px" style="margin-top: 5px;">
            <tr>
                <td rowspan="3"
                    style="writing-mode: vertical-lr; font-weight: bold; transform: rotate(180deg);">
                    REMARKS</td>
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
