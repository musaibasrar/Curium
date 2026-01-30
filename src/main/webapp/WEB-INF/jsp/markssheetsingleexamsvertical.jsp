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
}

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
            <img src="/vision/images/vision.jpg" width="80" height="80">
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
            <img src="/vision/images/principalsignature.png" width="60" height="28"><br>
            Principal
        </td>
    </tr>
</table>

</body>
</html>
