<%--
    Document   : queries
    Created on : Dec 19, 2021, 07:08:28 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tasks</title>
<link rel="stylesheet" href="/greatindiaacademy/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/greatindiaacademy/css/datePicker/demos.css">
<style type="text/css">
<!--
.divCSS {
	overflow: scroll;
	height: 100%;
	width: 100%;
}

.fiedlSet {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-width: 1;
	width: 100%;
	color: #000000;
	font-size: 16px;
	font-weight: bold;
	font-variant: normal;
	font-stretch: wider;
	background-color: #e2ebf3;
	border-top-color: #5d7e9b;
	border-right-color: #5d7e9b;
	border-bottom-color: #5d7e9b;
	border-left-color: #5d7e9b;
}

.legendCSS {
	color: #666666;
}

.tableCSS {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0px;
	top: 0px;
}

.textAreaCSS {
	height: auto;
	width: auto;
}

.textField {
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #5d7e9b;
	border-right-color: #5d7e9b;
	border-bottom-color: #5d7e9b;
	border-left-color: #5d7e9b;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	width: auto;
	height: auto;
}

.alignRight {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignRightMultiple {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bolder;
	text-align: right;
	vertical-align: middle;
	font-style: normal;
	color: #325F6D;
}

.alignCentreMultiple {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bolder;
	text-align: center;
	vertical-align: middle;
	font-style: normal;
	color: #000000;
}

.autoAdjust {
	height: auto;
	width: auto;
}

.radioSpanCSS {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: left;
	vertical-align: middle;
}

.radioCSS {
	background-position: left center;
}

.spanText {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #000000;
}

.emptyFieldSet {
	border-top-color: #FA7676;
	border-right-color: #FA7676;
	border-bottom-color: #FA7676;
	border-left-color: #FA7676;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-width: thin;
	border-right-width: thin;
	border-bottom-width: thin;
	border-left-width: thin;
	background-image: url(/greatindiaacademy/images/close.JPG);
	background-repeat: repeat-y;
	background-attachment: scroll;
	background-position: right;
	width: auto;
	height: auto;
	display: inline;
}

.style1 {
	font-family: Tahoma;
	font-size: 14px;
}

.style2 {
	color: #666666;
	font-family: Tahoma;
	font-size: 14px;
}

.style4 {
	font-size: 12px;
	font-family: Tahoma;
	text-align: left;
	vertical-align: middle;
	color: #325f6d;
}
.formfield * {
  vertical-align: middle;
}

<!--
.header {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	background-color: #4b6a84;
}

.table {
	background-color: #3399CC;
	text-align: center;
	width: auto;
}

.headerText {
	border-radius: 3px;
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
	vertical-align: text-top;
	text-align: center;
	background-image:
		url("/greatindiaacademy/images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.dataTextInActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: none;
}

.dataTextActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: underline;
	cursor: pointer;
}

.dataTextHidden {
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.headerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	background-image:
		url("/greatindiaacademy/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


            }
            
.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

.success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
}

.buttonred {
  background-color: red; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
} 
 .alignLeft {
				font-family: Tahoma;
				font-size: 14px;
				font-style: normal;
				text-transform: capitalize;
				color: #325F6D;
				text-align: left;
				vertical-align: middle;
				font-weight: bold;
			}           
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/greatindiaacademy/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/greatindiaacademy/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/greatindiaacademy/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/greatindiaacademy/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/greatindiaacademy/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/greatindiaacademy/js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : true,
			"bAutoWidth" : false
		});
	});
</script>
<script type="text/javascript" src="/greatindiaacademy/js/datetimepicker_css.js"></script>


	<script type="text/javascript">
		function completeTasks() {
			var form1 = document.getElementById("form1");
			form1.action = "/greatindiaacademy/JobProcess/completeTasks?display=viewtask";
			form1.method = "POST";
			form1.submit();
		}
		
		function cancelTasks() {
			var form1 = document.getElementById("form1");
			form1.action = "/greatindiaacademy/JobProcess/cancelTasks?display=viewtask";
			form1.method = "POST";
			form1.submit();
		}
		
		function inProgressTasks() {
			var form1 = document.getElementById("form1");
			form1.action = "/greatindiaacademy/JobProcess/inProgressTasks?display=viewtask";
			form1.method = "POST";
			form1.submit();
		}
		
		function toDoTasks() {
			var form1 = document.getElementById("form1");
			form1.action = "/greatindiaacademy/JobProcess/toDoTasks?display=viewtask";
			form1.method = "POST";
			form1.submit();
		}
		
		function updateQueryRemarks(queryRemarksAdded,queryRemarks, queryid, username) {
			var form1 = document.getElementById("form1");
			var remarksAdded = queryRemarks.value;
			var loginusername = username.value;
			var result = " sla" +" "+loginusername+":  "+ remarksAdded;
			form1.action = "/greatindiaacademy/JobProcess/updateTaskRemarks?queryremarks="+result+"&queryid="+queryid.value+"&jobid="+queryid.value+"&display=viewtask";
			form1.method = "POST";
			form1.submit();
		}
		
		function updateQueryResponse(parentQuery, response, queryid) {
			var form1 = document.getElementById("form1");
			form1.action = "/greatindiaacademy/JobProcess/updateQueries&parentquery="+parentQuery.value+"&response="+response.value+"&queryid="+queryid.value+"";
			form1.method = "POST";
			form1.submit();
		}

	</script>
	
	 <script type="text/javascript">
					
					var querystatus = '<c:out default="" value="${querystatus}"/>';
		            
		            if(querystatus == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            	 });
		            	 }else if(querystatus == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 );
		            		 $( "div.success" ).fadeOut("slow");
		            		 });
		            	 }
		            
		        	function closediv(divid){
		        		var x = document.getElementById("div"+divid);
		        		  if (x.style.display === "none") {
		        		    x.style.display = "block";
		        		    return false;
		        		  } else {
		        		    x.style.display = "none";
		        		    var form1 = document.getElementById("form1");
		        			form1.action = "/greatindiaacademy/JobProcess/ViewTaskDetails?jobid="+jobId;
		        			form1.method = "POST";
		        			form1.submit();
		        		  }
		        	}
		        	
        </script>
        
<script type="text/javascript">
	
	$(function() {
		$("#tabs").tabs();
	});
	
	 $(function(){
         $("#completed").button({
             icons:{
                 primary: "ui-icon-check"
             }
         }).click(function(){
        	 completeTasks();
         });
         
         
         $("#cancel").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             cancelTasks();

         });
         
         $("#inprogress").button({
             icons:{
                 primary: "ui-icon-circle-check"
             }
         }).click(function(){
             inProgressTasks();

         });
         
         $("#todo").button({
             icons:{
                 primary: "ui-icon-circle-plus"
             }
         }).click(function(){
             toDoTasks();

         });
         
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
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
              

     });
		
</script>
	
	<script type="text/javascript">

            function openPopup(queryRemarks,queryid){
            	
            	var regex = /greatindiaacademy/g;
            	var queryRemarksadded = queryRemarks.replace(regex,"\n ");
            	document.getElementById("queryremarksadded").value=queryRemarksadded;
            	document.getElementById("queryid").value= queryid;

        		
                $( "#dialog" ).dialog( "open" );
            }
            
        </script>       

 <script type="text/javascript">

            $(function() {
                $( "#dialog" ).dialog({
                    autoOpen: false,
                    height: 300,
                    width: 400,
                    modal: true,
                    buttons: {
                        Update: function() {
                        	updateQueryRemarks(document.getElementById("queryremarksadded"),document.getElementById("queryremarks"),document.getElementById("queryid"),document.getElementById("username"));
                            		$( this ).dialog( "close" );
                     		   }
                    }
                });
            });

            function viewStudentDetails(sid,branchid){
                var form1=document.getElementById("form1");
               form1.action="/greatindiaacademy/StudentProcess/ViewDetails?id="+sid+"&urlbranchid="+branchid+"";
               form1.submit();
               
               //window.location.reload();
           }
            
            function viewOneJobDetails(jobid){
                var form1=document.getElementById("form1");
                form1.action="/greatindiaacademy/JobProcess/viewOneJobDetails?jobid="+jobid+"";
                form1.submit();
            }
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


		<div class="alert-box success" id="div1">Task updated successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failure" id="div2">Task update failed, please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Tasks</td>
					
				</tr>
			</table>
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">UID</th>
                            <th title="click to sort" class="headerText">Job No.</th>
                            <th title="click to sort" class="headerText">Staff</th>
                            <th title="click to sort" class="headerText">Remarks</th>
                            <th title="click to sort" class="headerText">Status</th>
                            <th title="click to sort" class="headerText">Task</th>
                            <th title="click to sort" class="headerText">Description</th>
                            <th title="click to sort" class="headerText">Created Date</th>
                            <th title="click to sort" class="headerText">Updated Date</th>
                            <th title="click to sort" class="headerText">Expected End Date</th>
                            <!-- <th title="click to sort" class="headerText">Details</th> -->
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${taskdetails}" var="task" varStatus="status">
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                            	
                            	<c:choose>
                                <c:when test="${task.status == 'Completed'}">
                                    <td class="dataText"><input type="checkbox" id = "<c:out value="${task.id}"/>" class = "chcktbl"  name="taskids"  value="<c:out value="${task.id}_${task.jobquery.id}"/>" disabled="disabled"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="dataText"><input type="checkbox" id = "<c:out value="${task.id}"/>" class = "chcktbl"  name="taskids"  value="<c:out value="${task.id}_${task.jobquery.id}"/>"/></td>
                                </c:otherwise>
                           		 </c:choose>
                            	
                            	<td class="dataText"><c:out value="${task.id}"/> <input type="hidden" id="jobid" name="jobid" value="${task.jobquery.id}">
                            	<input type="hidden" id="username" name="username" value="${username}"> </td>
                            	<c:if test="${task.status == 'To Do' }">
                                	<td class="dataText" style="color: #cb1b09;font-weight: bold;">
                                		<a class="dataTextInActive" style="color: #cb1b09;font-weight: bold;cursor: pointer;" onclick="viewOneJobDetails(${task.jobquery.id})"><c:out value="${task.jobquery.externalid}"/></a>
                                	</td>
                                </c:if>
                                <c:if test="${task.status == 'In Progress' }">
                                	<td class="dataText" style="color: #0001ff;font-weight: bold;cursor: pointer;">
                                		<a class="dataTextInActive" style="color: #0001ff;font-weight: bold;cursor: pointer;" onclick="viewOneJobDetails(${task.jobquery.id})"><c:out value="${task.jobquery.externalid}"/></a>
                                	</td>
                                </c:if>
                                <c:if test="${task.status == 'Completed' }">
                                	<td class="dataText" style="color: #65a358;font-weight: bold;cursor: pointer;">
                                		<a class="dataTextInActive" style="color: #65a358;font-weight: bold;cursor: pointer;" onclick="viewOneJobDetails(${task.jobquery.id})"><c:out value="${task.jobquery.externalid}"/></a>
                                	</td>
                                </c:if>
                                <c:if test="${task.status == 'Cancelled' }">
                                	<td class="dataText" style="color: grey;font-weight: bold;cursor: pointer;">
                                		<a class="dataTextInActive" style="color: grey;font-weight: bold;cursor: pointer;" onclick="viewOneJobDetails(${task.jobquery.id})"><c:out value="${task.jobquery.externalid}"/></a>
                                	</td>
                                </c:if>
                                <%-- <td class="dataText" style="text-align: left">
                                		<a class="dataTextInActive" style="cursor: pointer;cursor: pointer;" onclick="viewStudentDetails(${task.jobquery.parent.student.sid},${task.jobquery.parent.student.branchid})"><c:out value="${task.jobquery.parent.student.name}"/></a>
                                </td> --%>
                                <td class="dataText"><c:out value="${task.teacher.teachername}"/></td>
                                 <c:if test="${not empty task.jobquery.feedback}">
                                	<td class="dataText"><a href="#" onclick="openPopup('${task.jobquery.feedback}','${task.jobquery.id}')" style="color:#eb6000;">View</a></td>
                                </c:if>
                                <c:if test="${empty task.jobquery.feedback}">
                                	<td class="dataText"><a href="#" onclick="openPopup('${task.jobquery.feedback}','${task.jobquery.id}')" style="color:#4b6a84;">Add</a></td>
                                </c:if>
                                <td class="dataText"><c:out  value="${task.status}"/></td>
                                <td class="dataText"><c:out value="${task.tasks}"/></td>
                               	<td class="dataText"><c:out value="${task.description}"/></td>
                                <td class="dataText"><fmt:formatDate pattern="dd/MM/yyyy" value="${task.jobquery.createddate}"/></td>
                                <td class="dataText"><fmt:formatDate pattern="dd/MM/yyyy" value="${task.updateddate}"/></td>
                                <td class="dataText"><fmt:formatDate pattern="dd/MM/yyyy" value="${task.expecteddeliverydate}"/></td>
                                <%-- <td class="dataText"><a href="#" onclick="openPopup(${query.id})" style="color:#eb6000;">View Details</a></td> --%>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                    <tfoot><tr>
                    
                    <td  class="footerTD" colspan="2" >
                            </td>
                   </tr></tfoot>
                </table>
                
                <table>
                	<tr>
                    	<td  colspan="2" ><button value="completed" id="completed">Completed</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="inprogress">In Progress</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="cancel">Cancel</button> 
                            &nbsp;&nbsp;&nbsp;&nbsp;<button id="todo">To Do</button> 
                    	</td>
                   </tr>
                </table>
                
                <div align="center">
		             <%--For displaying Previous link except for the 1st page --%>
		                <c:if test="${currentPage != 1}">
		                    <td><a style="color: #4B6A84;font-size: 12px" href="/greatindiaacademy/JobProcess/viewAllTasks&page=${currentPage - 1}">Previous</a></td>
		                </c:if>
		
		                <%--For displaying Page numbers.
		                The when condition does not display a link for the current page--%>
		                <table border="0" cellpadding="2" cellspacing="2">
		                	<c:forEach begin="1" end="${noOfPages}" var="i" varStatus="status">
 								   
 								   <c:if test="${status.index % 30 == 1}">
 								   	 <tr>
 								   </c:if>
		                            <c:choose>
		                                <c:when test="${currentPage eq i}">
		                                    <td style="color: #1D599B;font-weight:bolder;font-size: 20px ">${i}</td>
		                                </c:when>
		                                <c:otherwise>
		                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="/greatindiaacademy/JobProcess/viewAllQueries&page=${i}">${i}</a></td>
		                                </c:otherwise>
		                            </c:choose>
		                            
		                             <c:if test="${status.index % 30 == 0 || status.index == noOfPages}">
 								   	 </tr>
 								   </c:if>
		                            
		                    </c:forEach>
		                </table>
		
		                <%--For displaying Next link --%>
		                <c:if test="${currentPage lt noOfPages}">
		                    <td ><a style="color: #4B6A84;font-size: 12px" href="/greatindiaacademy/JobProcess/viewAllTasks&page=${currentPage + 1}">Next</a></td>
		                </c:if>
                </div>
		</div>
		
		<div id="dialog" title="Job Remarks">
                
                
                <table style="width: auto;height: auto;">
								
								<tr>
									<td>
										<p class="formfield">
										<input type="hidden" id="queryid" name="queryid" >
										<label style="font-size: 14px;"> Remarks :</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<textarea  name="queryremarksadded" id="queryremarksadded" cols="40" rows="5" readonly></textarea>
										</p>
									</td>
								</tr>
								
								<tr>
									<td>
										<p class="formfield">
										<label style="font-size: 14px;"> Add Remarks :</label>
										<textarea  name="queryremarks" id="queryremarks" cols="40" rows="5"></textarea>
										</p>
									</td>
								</tr>
								
								<tr>
									<td><br></td>
								</tr>
						</table>
						
						
             	 <!-- <div id="querydetails">
              			
              			
           		 </div> -->
           		 
			</div>
	</form>
</body>
</html>
