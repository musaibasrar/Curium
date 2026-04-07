<%--
    Document   : View Diary
    Created on : Mar 09, 2018, 3:05:28 PM
    Author     : Musaib
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Diary Parents</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/dolphin/css/bootstrap3.min.css">
	<script src="/dolphin/js/jquery.min.js"></script>
    <script src="/dolphin/js/bootstrap.min.js"></script>
    <script src="/dolphin/js/popper.min.js"></script>
<style>
  body {
    font-family: 'Roboto', sans-serif;
    font-size: 14px;
  }
  .highlight {
    background-color: #bcffa4;
  }
  .container {
    margin: 0;
    padding: 1rem;
  }
 
  .table th, .table td {
    text-align: center;
    vertical-align: middle;
    word-wrap: break-word;
    font-size: 12px;
  }
  .table-hover tbody tr:hover {
    background-color: #f1f1f1;
  }
  .pagination {
    justify-content: center;
  }
  .dateCell {
    text-align: center;
  }
  .btn-primary {
    background-color: #FF914D;
    border-color: #FF914D;
  }
  .btn-primary:hover {
    background-color: #FF7A1D;
    border-color: #FF7A1D;
  }
  .company__info {
            background-color: #FF914D;
            border-radius: 20px 20px 0 0;
            color: #fff;
            padding: 1em;
            text-align: center;
        }
        .company__info h2 {
            font-size: 2em;
        }
</style>
<script>
$(function() {
  $('#chckHead').click(function() {
    var isChecked = this.checked;
    $('.chcktbl').prop('checked', isChecked);
  });

  $('.chcktbl').click(function() {
    var allChecked = $('.chcktbl:checked').length === $('.chcktbl').length;
    $('#chckHead').prop('checked', allChecked);
  });
});

function viewStudentDiary(sid) {
  var form1 = document.getElementById("form1");
  form1.action = "/dolphin/DiaryProcess/ViewDiaryDetailsParent?id=" + sid;
  form1.submit();
}

document.addEventListener("DOMContentLoaded", function() {
  const currentDate = new Date().toISOString().split('T')[0];
  const diaryRows = document.querySelectorAll(".diaryRow");
  
  diaryRows.forEach(function(row) {
    const dateCell = row.querySelector(".dateCell");
    const startDate = dateCell.textContent.trim();
    
    if (currentDate === startDate) {
      row.classList.add("highlight");
    }
  });
});
</script>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/greatindiaacademy/UserProcess/sessionTimeOut");
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
<form id="form1" method="post">
<div class="container">
	<div class="company__info">
            <h2>Class Diary</h2>
        </div>
<table class="table table-striped table-bordered table-hover" id="myTable">
<thead class="thead-dark">
<tr>
<th>Subject</th>
<th style="width: 20%;">Message</th>
<th style="width: 10%;">Start Date</th>
<th style="width: 10%;">End Date</th>
<th style="width: 15%;">attachment1</th>
<th style="width: 15%;">attachment2</th>
<th style="width: 15%;">attachment3</th>
</tr>
</thead>
<tbody>
<c:forEach items="${diaryparents}" var="diary">
<tr class="diaryRow">
<td><c:out value="${diary.subject}" /></td>
<td style="width: 30%;"><a class="dataText" style="cursor: pointer;" onclick="viewStudentDiary(${diary.id})">Read More...</a></td>
<td class="dateCell" style="width: 20%;"><fmt:formatDate value="${diary.startdate}" pattern="dd/MM/yyyy"/></td>
<td style="width: 20%;"><fmt:formatDate value="${diary.enddate}" pattern="dd/MM/yyyy"/></td>
<td class="dataText">
                    <a download="attachment1.${fn:split(fn:split(diary.attachment1,'/')[1],';')[0]}" 
                     href="${diary.attachment1}">Download</a>
                    </td>
                     <td class="dataText"><c:set var="attachpart2" value="${fn:split(diary.attachment2, '/')}" />
							 <c:set var="attach2" value="${fn:split(attachpart2[1], ';')}" />
                    <a download="attachment2.${attach2[0]}" href="${diary.attachment2}">Download</a>
                    </td>
                     <td class="dataText"><c:set var="attachpart3" value="${fn:split(diary.attachment3, '/')}" />
							 <c:set var="attach3" value="${fn:split(attachpart3[1], ';')}" />
                    <a download="attachment3.${attach3[0]}" href="${diary.attachment3}">Download</a>
                    </td>
</tr>
</c:forEach>
</tbody>
</table>

<%-- <div class="d-flex justify-content-between mt-4">
  <c:if test="${currentPage != 1}">
    <a class="btn btn-primary" href="/dolphin/DiaryProcess/viewdiarystudent?page=${currentPage - 1}">Previous</a>
  </c:if>
  <ul class="pagination">
    <c:forEach begin="1" end="${noOfPages}" var="i">
      <li class="page-item <c:if test='${currentPage == i}'>active</c:if>">
        <a class="page-link" href="/dolphin/DiaryProcess/viewdiarystudent?page=${i}">${i}</a>
      </li>
    </c:forEach>
  </ul>
  <c:if test="${currentPage lt noOfPages}">
    <a class="btn btn-primary" href="/dolphin/DiaryProcess/viewdiarystudent?page=${currentPage + 1}">Next</a>
  </c:if>
</div> --%>
</div>
</form>
</body>
</html>
