<%--
    Document   : Attendance Export Success Staff
    Created on : Feb 01, 2018, 4:11:53 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Staff Attendance Report</title>
  <style type="text/css">
    body {
      font-family: Tahoma, Arial, sans-serif;
      background: #f7f9fb url("/images/bg.jpg") repeat;
      margin: 0;
      padding: 0;
    }
    .report-wrapper {
      padding: 18px;
    }
    .report-card {
      background: #fff;
      border: 1px solid #d9e1e8;
      border-radius: 6px;
      padding: 16px;
      box-shadow: 0 1px 3px rgba(0,0,0,.05);
    }
    .report-header {
      text-align: center;
      margin-bottom: 16px;
    }
    .report-logo {
      height: 72px;
      width: auto;
      max-width: 220px;
      object-fit: contain;
    }
    .report-school {
      font-size: 24px;
      font-weight: bold;
      color: #325F6D;
      margin-top: 6px;
      text-transform: uppercase;
    }
    .report-title {
      font-size: 18px;
      font-weight: bold;
      color: #5E87B0;
      margin-top: 4px;
    }
    .report-meta {
      font-size: 13px;
      color: #444;
      margin-top: 6px;
      line-height: 1.6;
    }
    .report-actions {
      text-align: right;
      margin: 10px 0 14px;
    }
    .report-actions button {
      padding: 8px 14px;
      margin-left: 8px;
      border: 1px solid #5E87B0;
      background: #5E87B0;
      color: #fff;
      border-radius: 4px;
      cursor: pointer;
      font-weight: bold;
    }
    .report-actions button:hover {
      opacity: .92;
    }
    .table-scroll {
      width: 100%;
      overflow-x: auto;
      border: 1px solid #d9e1e8;
      position: relative;
    }
    .table-scroll-top {
      width: 100%;
      overflow-x: auto;
      overflow-y: hidden;
      border: 1px solid #d9e1e8;
      border-bottom: none;
      height: 16px;
    }
    .table-scroll-top-inner {
      height: 1px;
    }
    .attendance-table {
      border-collapse: collapse;
      width: max-content;
      min-width: 100%;
      background: #fff;
    }
    .attendance-table th,
    .attendance-table td {
      border: 1px solid #aab7c3;
      padding: 4px 6px;
      font-size: 11px;
      white-space: nowrap;
      text-align: center;
    }
    .attendance-table thead th {
      background: #dfeaf2;
      color: #234;
      font-weight: bold;
    }
    .staff-col {
      min-width: 170px;
      width: 170px;
      text-align: left !important;
    }
    .attendance-table .staff-col {
      position: sticky;
      left: 0;
      z-index: 2;
      background: #fff;
      box-shadow: 1px 0 0 #aab7c3;
    }
    .attendance-table thead .staff-col {
      background: #dfeaf2;
      z-index: 4;
    }
    .attendance-table .time-row .staff-col {
      background: #fafafa;
    }
    .total-col {
      min-width: 90px;
    }
    .status-row {
      background: #fff;
    }
    .time-row {
      background: #fafafa;
      color: #666;
    }
    .hide-on-print {
      display: block;
    }
    @page {
      size: landscape;
      margin: 10mm;
    }
    @media print {
      body {
        background: #fff;
      }
      .report-wrapper {
        padding: 0;
      }
      .report-card {
        border: none;
        box-shadow: none;
        padding: 0;
      }
      .hide-on-print {
        display: none !important;
      }
      .table-scroll {
        overflow: visible;
        border: none;
      }
      .table-scroll-top {
        display: none !important;
      }
      .attendance-table {
        width: 100%;
        font-size: 9px;
      }
      .attendance-table thead {
        display: table-header-group;
      }
      .attendance-table tr {
        page-break-inside: avoid;
      }
      .attendance-table th,
      .attendance-table td {
        padding: 3px 4px;
      }
      .attendance-table .staff-col {
        position: static;
        left: auto;
        z-index: auto;
        box-shadow: none;
      }
    }
  </style>
  <script type="text/javascript">
    function printReport() {
      window.print();
    }
    function exportReport() {
      document.getElementById('exportForm').submit();
    }
    function initTableTopScroll() {
      var topScroll = document.getElementById('tableScrollTop');
      var topInner = document.getElementById('tableScrollTopInner');
      var bottomScroll = document.getElementById('tableScrollBottom');
      var previewTable = document.getElementById('attendancePreviewTable');

      if (!topScroll || !topInner || !bottomScroll || !previewTable) {
        return;
      }

      function syncTopWidth() {
        topInner.style.width = previewTable.scrollWidth + 'px';
      }

      topScroll.addEventListener('scroll', function() {
        if (bottomScroll.scrollLeft !== topScroll.scrollLeft) {
          bottomScroll.scrollLeft = topScroll.scrollLeft;
        }
      });

      bottomScroll.addEventListener('scroll', function() {
        if (topScroll.scrollLeft !== bottomScroll.scrollLeft) {
          topScroll.scrollLeft = bottomScroll.scrollLeft;
        }
      });

      syncTopWidth();
      window.addEventListener('resize', syncTopWidth);
    }

    if (window.addEventListener) {
      window.addEventListener('load', initTableTopScroll);
    } else if (window.attachEvent) {
      window.attachEvent('onload', initTableTopScroll);
    }
  </script>
</head>
<body>
<c:set var="displayPreviewRows" value="${not empty previewRows ? previewRows : sessionScope.staffMonthlyAttendancePreviewRows}" />
<c:set var="displayDayHeaders" value="${not empty dayHeaders ? dayHeaders : sessionScope.staffMonthlyAttendanceDayHeaders}" />
<c:set var="displaySchoolName" value="${not empty schoolName ? schoolName : sessionScope.staffMonthlyAttendanceSchoolName}" />
<c:set var="displaySchoolAddress" value="${not empty schoolAddress ? schoolAddress : sessionScope.staffMonthlyAttendanceSchoolAddress}" />
<c:set var="displayAcademicYear" value="${not empty academicYear ? academicYear : sessionScope.staffMonthlyAttendanceAcademicYear}" />
<c:set var="displayDateRange" value="${not empty dateRange ? dateRange : sessionScope.staffMonthlyAttendanceDateRange}" />
<c:set var="displayReportTitle" value="${not empty reportTitle ? reportTitle : sessionScope.staffMonthlyAttendanceReportTitle}" />
<c:set var="displayBranchId" value="${not empty branchid ? branchid : sessionScope.branchid}" />
<c:set var="displayBranchName" value="${not empty branchname ? branchname : sessionScope.branchname}" />

<div class="report-wrapper">
  <div class="report-card">
    <div class="report-header">
      <img class="report-logo" src="/hwfschools/images/hwfschools${branchid}.png" alt="School Logo" />
      <div class="report-school">${displaySchoolName}</div>
      <div class="report-title">${displayReportTitle}</div>
      <div class="report-meta">
        <c:if test="${not empty displaySchoolAddress}">${displaySchoolAddress}<br/></c:if>
        Academic Year: <strong>${displayAcademicYear}</strong><br/>
        Date Range: <strong>${displayDateRange}</strong>
      </div>
    </div>

    <div class="report-actions hide-on-print">
      <button type="button" onclick="printReport();">Print</button>
      <form id="exportForm" action="/hwfschools/AttendanceProcess/downloadStaffAttendance" method="post" target="downloadFrame" style="display:inline;">
        <button type="button" onclick="exportReport();">Export to Excel</button>
      </form>
    </div>

    <div id="tableScrollTop" class="table-scroll-top hide-on-print">
      <div id="tableScrollTopInner" class="table-scroll-top-inner"></div>
    </div>

    <div id="tableScrollBottom" class="table-scroll">
      <table id="attendancePreviewTable" class="attendance-table">
        <thead>
          <tr>
            <th class="staff-col" rowspan="2">Staff Name</th>
            <c:forEach items="${displayDayHeaders}" var="day">
              <th rowspan="2">${day}</th>
            </c:forEach>
            <th class="total-col" rowspan="2">Total Working Days</th>
            <th class="total-col" rowspan="2">Total Days Present</th>
            <th class="total-col" rowspan="2">Total Days Leave</th>
            <th class="total-col" rowspan="2">Total Days Absent</th>
          </tr>
          <tr></tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty displayPreviewRows}">
              <c:forEach items="${displayPreviewRows}" var="row">
                <tr class="${row.rowKind eq 'STATUS' ? 'status-row' : 'time-row'}">
                  <td class="staff-col">${row.staffName}</td>
                  <c:forEach items="${row.dayValues}" var="value">
                    <td><c:out value="${value}" /></td>
                  </c:forEach>
                  <td>${row.rowKind eq 'STATUS' ? row.totalWorkingDays : ''}</td>
                  <td>${row.rowKind eq 'STATUS' ? row.totalDaysPresent : ''}</td>
                  <td>${row.rowKind eq 'STATUS' ? row.totalDaysLeave : ''}</td>
                  <td>${row.rowKind eq 'STATUS' ? row.totalDaysAbsent : ''}</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="${fn:length(displayDayHeaders) + 5}">No export preview data available.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>

  </div>
</div>

<iframe name="downloadFrame" id="downloadFrame" style="display:none;"></iframe>
</body>
</html>
