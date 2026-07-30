<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assessment Progress Report</title>
<style type="text/css">
body {
    margin: 0;
    padding: 16px;
    background: #f5f7fa;
    font-family: Tahoma, Arial, sans-serif;
    color: #1f2f3d;
}

.toolbar {
    max-width: 1000px;
    margin: 0 auto 14px auto;
    display: flex;
    gap: 8px;
    justify-content: flex-end;
}

.toolbar button {
    border: 1px solid #5d7e9b;
    background: #4b6a84;
    color: #fff;
    border-radius: 4px;
    padding: 8px 14px;
    cursor: pointer;
}

.report-page {
    width: 210mm;
    min-height: 297mm;
    margin: 0 auto 16px auto;
    padding: 12mm;
    background: #ffffff;
    box-sizing: border-box;
    border: 1px solid #d8e0e8;
    page-break-after: always;
}

.report-page:last-child {
    page-break-after: auto;
}

.report-header {
    border-bottom: 2px solid #4b6a84;
    padding-bottom: 10px;
    margin-bottom: 12px;
}

.report-title {
    margin: 0;
    color: #2f4f68;
    font-size: 22px;
    letter-spacing: 0.2px;
}

.report-subtitle {
    margin-top: 6px;
    font-size: 12px;
    color: #4f6477;
}

.school-header {
    width: 100%;
    margin-bottom: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
}

.school-logo {
    height: 60px;
    width: 60px;
    flex: 0 0 auto;
}

.school-info-block {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
}

.school-name {
    margin: 0;
}

.school-contact {
    margin: 2px 0 0 0;
}

.meta-grid {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 10px;
}

.meta-grid td {
    border: 1px solid #d0dce8;
    padding: 6px;
    font-size: 12px;
}

.summary-box {
    border: 1px solid #d5e2ef;
    background: #edf4fb;
    padding: 8px;
    margin-bottom: 12px;
    font-size: 12px;
}

.category-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 12px;
    font-size: 12px;
}

.category-table th,
.category-table td {
    border: 1px solid #d0dce8;
    padding: 6px;
}

.category-table th {
    background: #4b6a84;
    color: #fff;
    text-align: left;
}

.graph-box {
    border: 1px solid #d5e2ef;
    background: #fafcff;
    padding: 8px;
    margin-top: 8px;
}

.graph-title {
    font-size: 12px;
    color: #2f4f68;
    margin: 0 0 6px 0;
    font-weight: bold;
}

.category-title {
    background: #f3f7fb;
    color: #1f2f3d;
    font-weight: bold;
}

.empty-state {
    width: 210mm;
    margin: 40px auto;
    background: #fff;
    border: 1px solid #d8e0e8;
    padding: 18px;
    text-align: center;
}

@media screen and (max-width: 900px) {
    body {
        padding: 8px;
    }

    .report-page,
    .empty-state {
        width: 100%;
        min-height: auto;
        padding: 14px;
    }

    .school-header {
        justify-content: flex-start;
    }
}

@media print {
    body {
        background: #fff;
        padding: 0;
    }

    .toolbar {
        display: none !important;
    }

    .report-page,
    .empty-state {
        border: none;
        margin: 0;
        width: 100%;
        min-height: auto;
        box-shadow: none;
    }
}
</style>
<script src="/sac/js/Chart.min.js"></script>
<script type="text/javascript">
function printReport() {
    window.print();
}
</script>
</head>
<body>
<div class="toolbar no-print">
    <button type="button" onclick="printReport();">Print</button>
    <button type="button" onclick="window.close();">Close</button>
</div>

<c:if test="${not empty progressReportError}">
    <div class="empty-state">
        <strong><c:out value="${progressReportError}" /></strong>
    </div>
</c:if>

<c:if test="${empty progressReportError and empty studentReportCards}">
    <div class="empty-state">
        <strong>No report data found for the selected students.</strong>
    </div>
</c:if>

<c:forEach items="${studentReportCards}" var="card" varStatus="reportStatus">
    <div class="report-page">
        <div class="school-header">
            <img border="0" class="school-logo" alt="logo" src="/sac/images/sac.png">
            <div class="school-info-block">
                <h3 class="school-name">
                    <c:out value="${branchname}" />
                </h3>
                <h4 class="school-contact">
                    <c:out value="${branchaddress}" /><br><c:out value="${branchcontact}" />
                </h4>
            </div>
        </div>

        <div class="report-header">
            <h1 class="report-title">
                <c:out value="${empty assessmentReportTitle ? 'Assessment Progress Report' : assessmentReportTitle}" />
            </h1>
            <div class="report-subtitle">
                Report Date: <c:out value="${empty card.reportGeneratedDate ? '--' : card.reportGeneratedDate}" />
            </div>
        </div>

        <table class="meta-grid">
            <tr>
                <td><strong>Student Name</strong></td>
                <td><c:out value="${empty card.studentInfo.student.name ? '--' : card.studentInfo.student.name}" /></td>
                <td><strong>Class</strong></td>
                <td><c:out value="${empty card.studentInfo.student.classstudying ? '--' : card.studentInfo.student.classstudying}" /></td>
            </tr>
            <tr>
                <td><strong>UID</strong></td>
                <td><c:out value="${empty card.studentInfo.student.studentexternalid ? '--' : card.studentInfo.student.studentexternalid}" /></td>
                <td><strong>Academic Year</strong></td>
                <td><c:out value="${empty card.academicYear ? '--' : card.academicYear}" /></td>
            </tr>
        </table>

        <c:if test="${not empty card.overallSummary}">
            <div class="summary-box">
                <strong>Overall Grade:</strong> <c:out value="${empty card.overallSummary.overallGrade ? '--' : card.overallSummary.overallGrade}" />
                &nbsp; | &nbsp;
                <strong>Overall Percentage:</strong>
                <fmt:formatNumber value="${card.overallSummary.overallPercentage}" maxFractionDigits="2" minFractionDigits="0" />
                &nbsp; | &nbsp;
                <strong>Remarks:</strong> <c:out value="${empty card.overallSummary.teacherOverallRemarks ? '--' : card.overallSummary.teacherOverallRemarks}" />
            </div>
        </c:if>

        <c:forEach items="${card.categorySummaries}" var="category">
            <table class="category-table">
                <thead>
                    <tr>
                        <th colspan="4" class="category-title">
                            <c:out value="${empty category.categoryName ? '--' : category.categoryName}" />
                            &nbsp; (Average:
                            <fmt:formatNumber value="${category.averageScore}" maxFractionDigits="2" minFractionDigits="0" />,
                            Grade: <c:out value="${empty category.categoryGrade ? '--' : category.categoryGrade}" />)
                        </th>
                    </tr>
                    <tr>
                        <th>Subject</th>
                        <th>Grade</th>
                        <th>Score</th>
                        <th>Remark</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${category.subjects}" var="subject">
                        <tr>
                            <td><c:out value="${empty subject.subjectName ? '--' : subject.subjectName}" /></td>
                            <td><c:out value="${empty subject.ratingGrade ? '--' : subject.ratingGrade}" /></td>
                            <td><c:out value="${empty subject.displayScore ? '--' : subject.displayScore}" /></td>
                            <td><c:out value="${empty subject.remark ? '--' : subject.remark}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:forEach>

        <div class="graph-box">
            <p class="graph-title">Category-wise Performance Graph</p>
            <canvas id="category-chart-${reportStatus.index}" height="110"></canvas>
            <input type="hidden" id="category-labels-${reportStatus.index}" value="<c:forEach items='${card.categorySummaries}' var='cat' varStatus='s'><c:out value='${cat.categoryName}'/><c:if test='${!s.last}'>|</c:if></c:forEach>" />
            <input type="hidden" id="category-scores-${reportStatus.index}" value="<c:forEach items='${card.categorySummaries}' var='cat' varStatus='s'><fmt:formatNumber value='${cat.averageScore}' maxFractionDigits='2'/><c:if test='${!s.last}'>|</c:if></c:forEach>" />
        </div>
    </div>
</c:forEach>

<script type="text/javascript">
    (function() {
        var total = ${empty studentReportCards ? 0 : fn:length(studentReportCards)};
        for (var i = 0; i < total; i++) {
            var labelsField = document.getElementById("category-labels-" + i);
            var scoresField = document.getElementById("category-scores-" + i);
            var chartEl = document.getElementById("category-chart-" + i);
            if (!labelsField || !scoresField || !chartEl) {
                continue;
            }

            var labels = labelsField.value ? labelsField.value.split("|") : [];
            var scores = [];
            if (scoresField.value) {
                var scoreParts = scoresField.value.split("|");
                for (var j = 0; j < scoreParts.length; j++) {
                    scores.push(parseFloat(scoreParts[j]) || 0);
                }
            }

            new Chart(chartEl, {
                type: "bar",
                data: {
                    labels: labels,
                    datasets: [{
                        label: "Category Average",
                        data: scores,
                        backgroundColor: "rgba(75,106,132,0.6)",
                        borderColor: "rgba(75,106,132,1)",
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                max: 100
                            }
                        }]
                    }
                }
            });
        }
    })();
</script>

</body>
</html>
