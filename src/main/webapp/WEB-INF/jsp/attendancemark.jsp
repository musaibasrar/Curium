<%--
    Document   : View Attendance
    Created on : JAN 22, 2018, 4:14:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mark Attendance</title>
<script src="/abc/js/Chart.js"></script>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">
<link rel="stylesheet" href="/abc/css/graph/jquery.jqplot.css">
<link rel="stylesheet" href="/abc/css/graph/jquery.jqplot.min.css">


  <script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        
        
         <script  type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="/abc/js/graph/jquery.jqplot.js"></script>        
        <script  type="text/javascript" src="/abc/js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/abc/js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="/abc/js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/abc/js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="/abc/js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="/abc/js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="/abc/js/graph/plugins/jqplot.trendline.min.js"></script>
        <script src="/abc/js/jquery.jqplot.min.js" ></script>
        <script src="/abc/js/graph/plugins/jqplot.pieRenderer.min.js" ></script> 
        
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

.alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.alignSearch {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
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

.footerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	text-align: left;
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
	background-image: url(images/close.JPG);
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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
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

</style>
<style>
#button {
	
}
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
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		

	}
	
</script>

<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">

	$(function() {

		$("#search").button().click(function() {
			searchStudentAttendanceDetailsMark();
		});
		
		
		 $("#studentAttendanceStatus").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && e.which != 65 && e.which != 97 && e.which != 72 && e.which != 104 && e.which != 80 && e.which != 112 && e.which != 127) {
		               return false;
		    }
		   });

	});
	
	function searchStudentAttendanceDetailsMonthly() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/AttendanceProcess/searchStudentAttendanceDetailsMonthly";
		form1.method = "POST";
		form1.submit();

	}
	
	function searchStudentAttendanceDetailsMonthlyGraph() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/AttendanceProcess/searchStudentAttendanceDetailsMonthlyGraph";
		form1.method = "POST";
		form1.submit();

	}
	
	function searchStudentAttendanceDetailsMark() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/AttendanceProcess/searchStudentAttendanceDetailsMark";
		form1.method = "POST";
		form1.submit();

	}
	
	$(function() {

		$("#tabs").tabs();

	});
	
	$(function() {
		$("#update").button().click(function() {
			updateRecords();
			return false;

		});
		$('#chckHead').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			if (length > 0) {
				$('.chcktbl:checked').attr('checked', false);
				this.checked = false;

			} else {
				if (this.checked == false) {
					$('.chcktbl:checked').attr('checked', false);
				} else {
					$('.chcktbl:not(:checked)').attr('checked', true);
				}

			}

		});
		$('.chcktbl').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			alert(tdLength);
			if (length > trLength) {

				$('.chcktbl:not(:checked)').attr('disabled', true);
			} else {
				$('.chcktbl:not(:checked)').attr('disabled', false);
			}
		});

	});
	
	function updateRecords(){
		var form1 = document.getElementById("form1");
		form1.action = "/abc/AttendanceProcess/markStudentsAttendance";
		form1.method = "POST";
		form1.submit();
	}
	
	
	
</script>

<script type="text/javascript">
					
					var attendanceresult='<c:out default="" value="${attendanceresult}"/>';
					var splitMessage = attendanceresult.split('-');
					
		            if(attendanceresult.includes("success")){
		            	 $(function(){
		            		 $( "div.success" ).html(splitMessage[1]);
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(attendanceresult.includes("error")){
		            	  $(function(){
		            		 $( "div.failure" ).html(splitMessage[1]);
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }
		            
		            $(function() {
		        		$("#dateofattendance").datepicker({
		        			changeYear : true,
		        			changeMonth : true,
		        			dateFormat: 'dd/mm/yy',
		        			yearRange: "-1:+1"
		        		});
		        		$("#dateofattendance").change(
		        				function() {
		        					$("#dateofattendance").datepicker("option", "showAnim",
		        							$(this).val());
		        				});
		        	});
		            
		            
		            function markabsent(cheese){
		            	if(cheese.value=="P"){
		            		cheese.value="A";
		            	}else if(cheese.value=="A"){
		            		cheese.value="P";
		            	}
		            }
        </script>



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
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form id="form1"  method="POST">
	
		<div class="alert-box success"></div>
		<div class="alert-box failure"></div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Mark Attendance</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						<tr>
							<td class="alignRightFields">Class &nbsp;</td>
							<td><label> <select name="classsearch"
									id="classsearch" style="width: 90px">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label> <label> <select name="secsearch" id="secsearch"
									style="width: 50px">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.section != '')}">
												<option value="${classdetailslist.section}">
													<c:out value="${classdetailslist.section}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label>
							</td>
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<tdclass="alignRight"></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignRightFields">Date &nbsp;</td>
							<td align="left"><label> <input
									name="dateofattendance" type="text" class="textField"
									id="dateofattendance" size="25" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" readonly="readonly" data-validate="validate(required)"/>
							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>


						

					</table>
				</div>
			</div>
		</div>
		
		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Mark Attendance&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Class:&nbsp;${attendanceclass} 
					<input type="hidden" id="attendanceclass" name="attendanceclass" value="${attendanceclasssearch}" />
						</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText" style="display: none;"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Attendance Status&nbsp;</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${StudentListAttendance}" var="attendanceList" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="display: none;"><input type="checkbox" checked="checked" 
								id="<c:out value="${attendanceList.studentexternalid}"/>" class="chcktbl"
								name="externalIDs"
								value="<c:out value="${attendanceList.studentexternalid},${status.index}"/>" /></td>
							<td class="dataTextInActive"><a class="dataTextInActive"><c:out
										value="${attendanceList.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${attendanceList.name}" /></td>
							<td class="dataText">
							<input type="text" id="studentAttendanceStatus" name="studentAttendanceStatus" style="text-transform:uppercase" size="2" readonly="readonly" value="P" maxlength="1" onclick="markabsent(this);">
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
					
						<td class="footerTD" colspan="2">
							<button id="update">Submit</button>
							<!-- <input value="Delete Stamp Fees"
							type="submit" id="deleteStamp" /> --></td>
							

					</tr>
				</tfoot>
			</table>

		</div>

	</form>
</body>
</html>
