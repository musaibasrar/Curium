<%--
    Document   : View Diary
    Created on : Mar 09, 2018, 3:05:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Diary Parents</title>
<link rel="stylesheet" href="/abc/css/bootstrap.min.css">
        <script src="/abc/js/jquery.min.js"></script>
        <script src="/abc/js/bootstrap.min.js"></script>
        <script src="/abc/js/popper.min.js"></script>
<style type="text/css">
<!--
</style>

<link rel="stylesheet" href="/abc/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/abc/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/abc/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/ScrollableGridPlugin.js"></script>

<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		

	}
	
</script>

<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">
$(function(){
   /* $("#delete").button({
        icons:{
            primary: "ui-icon-trash"
        }
    }).click(function(){
        deleteRecords();
        return false;

    });
    function deleteRecords(){
        
        var form1=document.getElementById("form1");
        form1.action="/abc/DiaryProcess/deleteRecord";
        form1.method = "POST";
       form1.submit();
        
    }*/

	
	//chechbox
	  $('#chckHead').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             if(length>0){
                 $('.chcktbl:checked').attr('checked', false);
                 this.checked=false;

             }
             else{
                 if (this.checked == false) {
                     $('.chcktbl:checked').attr('checked', false);
                 }
                 else {
                     $('.chcktbl:not(:checked)').attr('checked', true);
                 }

             }

         });
         $('.chcktbl').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             alert(tdLength);
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
});
function viewStudentDiary(sid){
    var form1=document.getElementById("form1");
   form1.action="/abc/DiaryProcess/ViewDiaryDetailsParent?id="+sid+"";
   form1.submit();
}                  

</script>

<style type="text/css">

#myTable {
  border : 2px solid;
  font-size : 12px;
}
.container{
margin-left:0px;
margin-right:0px;
}
</style>

 <style>
        .highlight {
            background-color: #bcffa4;
        }
    </style>

</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
<div class="container" style="max-width:40%;">

<h2 align="center" style="color:#FF914D">Diary</h2>
<table class="table table-striped table-bordered table-hover" id="myTable">
<thead style="background-color:#4b6a84" class=" text-white">
<tr align="center">
<!-- <th>Class</th> -->
<th>Subject</th>
<th>Message</th>
<th>Start Date</th>
<th>End Date</th>
</tr>
</thead>
<tbody>
<c:forEach items="${diaryparents}" var="diary">
<tr class="diaryRow">
<!-- <td><input type="checkbox"
								id="<c:out value="${diary.id}"/>" class="chcktbl"
								name="id"
								value="<c:out value="${diary.id}"/>" /></td>-->
<%-- <td><c:out value="${diary.classsec}" /></td> --%>
<td><c:out value="${diary.subject}" /> </td>
<td><a class="dataText" style="cursor: pointer;" onclick="viewStudentDiary(${diary.id})">Read More...</a></td>
<td class="dateCell"><c:out value="${diary.startdate}" /></td>
<td><c:out value="${diary.enddate}" /></td>

</tr>
</c:forEach>
</tbody>
</table>



</div>
<div class="container" style="width: 40%;padding:0.25rem;">
 <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="/abc/DiaryProcess/viewdiarystudent?page=${currentPage - 1}">Previous</a></td>
                </c:if>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <table class="table">
                <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                                 </c:when>
                                <c:otherwise>
                                <td><a style="color: #4B6A84" href="/abc/DiaryProcess/viewdiarystudent?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </tr>
                </table>
                 <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="/abc/DiaryProcess/viewdiarystudent?page=${currentPage + 1}">Next</a></td>
                </c:if>
</div>
</form>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Get the current date
        const currentDate = new Date().toISOString().split('T')[0];

        // Get all the rows with the class diaryRow
        const diaryRows = document.querySelectorAll(".diaryRow");

        // Loop through each row
        diaryRows.forEach(function(row) {
            const dateCell = row.querySelector(".dateCell");
            const startDate = dateCell.textContent.trim();

            // Compare dates and change the color of the row if they match
            if (currentDate === startDate) {
                row.classList.add("highlight");
            }
        });
    });
</script>
	</body></html>