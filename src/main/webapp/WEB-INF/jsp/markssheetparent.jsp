<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>Marks Sheet</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<style>
  .headerText, .headerTextLeft, .dataTextBold, .dataTextBoldLeft, .dataTextBoldCenter, .addressLine, .dataText {
    font-family: Tahoma;
    font-size: 14px;
    color: black;
  }

  .dataTextBold {
    font-weight: bold;
  }

  .dataTextBoldCenter {
    font-size: 16px;
  }

  .datatable, .datath, .datatd {
    border: 1px solid #000;
    text-align: left;
    padding: 8px;
  }

  .datatable {
    width: 100%;
    border-collapse: collapse;
  }

  @media print {
    .fontsize {
      font-size: 10px;
      font-weight: bold;
      font-family: 'Times New Roman';
    }
    .header, .hide {
      visibility: hidden;
    }
    .bodymargin {
      margin-left: 0;
      margin-right: 0;
    }
  }

  @page {
    margin-left: 1cm;
    margin-right: 1cm;
    margin-bottom: 1cm;
    margin-top: 1cm;
    size: auto;
  }

  @media screen {
    .fontsize {
      font-size: 10px;
      font-weight: bold;
      font-family: 'Times New Roman';
    }
    .bodymargin {
      margin-left: 0;
      margin-right: 0;
    }
  }

  .subjectdetails {
    border: 1px solid black;
    text-align: left;
    padding: 8px;
  }

  .nosubjectdetails {
    border: 0;
    text-align: left;
    padding: 8px;
  }

  .namedetails {
    border: 0 solid #ddd;
    text-align: left;
    padding: 4px;
  }

  .namedetailscenter {
    border: 0 solid #ddd;
    text-align: right;
    padding: 8px;
  }

  .highlight {
    color: #1E90FF;
  }

  .highlight-orange {
    color: #FF914D;
  }
</style>
</head>
<body style="text-align: center" class="bodymargin">
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<form method="post" class="bodymargin">
  <c:forEach items="${markssheetlist}" var="Parents">
    <div class="card mb-4">
      <div class="card-body">
        <c:forEach items="${Parents.exammarks}" var="exammarks">
          <h3 class="text-center mb-4"><c:out value="${exammarks.examName}"/></h3>
          <table class="datatable table table-bordered">
            <thead>
              <tr>
                <th class="text-center">Subject</th>
                <th class="text-center">Marks</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${exammarks.subMarks}" var="submarks">
                <tr>
                  <td class="text-left"><c:out value="${submarks.key}"/></td>
                  <td class="text-left"><c:out value="${fn:split(submarks.value,'_')[0]}"/> (<c:out value="${fn:split(submarks.value,'_')[1]}"/>)</td>
                </tr>
              </c:forEach>
              <tr>
                <td class="highlight-orange">Total Marks Obtained</td>
                <td class="highlight-orange"><c:out value="${exammarks.totalMarksObtained}"/></td>
              </tr>
              <tr>
                <td class="highlight">Total Marks</td>
                <td class="highlight"><c:out value="${exammarks.totalMarks}"/></td>
              </tr>
              <tr>
                <td class="highlight">Percentage</td>
                <td class="highlight"><fmt:formatNumber value="${exammarks.percentage}" type="number" maxFractionDigits="1"/></td>
              </tr>
              <tr>
                <td class="highlight">Grade</td>
                <td class="highlight"><fmt:formatNumber value="${exammarks.resultclass}" type="number" maxFractionDigits="1"/></td>
              </tr>
              <tr>
                <td class="highlight">Remarks</td>
                <td class="highlight"></td>
              </tr>
            </tbody>
          </table>
        </c:forEach>
        <br><br><br>
      </div>
    </div>
  </c:forEach>
</form>
</body>
</html>
