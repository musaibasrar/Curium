<%--
    Document   : Attendance Export
    Created on : JAN 22, 2018, 4:14:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attendance Export</title>
<script src="/brightschool/js/Chart.js"></script>
<link rel="stylesheet" href="/brightschool/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/brightschool/css/datePicker/demos.css">
<link rel="stylesheet" href="/brightschool/css/graph/jquery.jqplot.css">
<link rel="stylesheet" href="/brightschool/css/graph/jquery.jqplot.min.css">


  <script type="text/javascript" src="/brightschool/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        
        
         <script  type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/jquery.jqplot.js"></script>        
        <script  type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="/brightschool/js/graph/plugins/jqplot.trendline.min.js"></script>
        <script src="/brightschool/js/jquery.jqplot.min.js" ></script>
        <script src="/brightschool/js/graph/plugins/jqplot.pieRenderer.min.js" ></script> 
        
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
	font-size: 1.3em;
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
	height: 28px;
	color: black;
	text-transform: capitalize;
	border-radius: 4px;}

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
	font-size: 14px;
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
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/brightschool/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/brightschool/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/brightschool/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/brightschool/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/brightschool/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/brightschool/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/brightschool/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		 $('#mytable tr').each(function() {
		        // Check if the row is empty
		        if ($(this).find('th,td').text().trim() === '') {
		          // Hide the empty row
		          $(this).hide();
		        }
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

<script type="text/javascript" src="/brightschool/js/datetimepicker_css.js"></script>
<script type="text/javascript">

	$(function() {

		$("#search").button().click(function() {
			attendanceReports();
		});
	});
	
	function attendanceReports() {
		var form1 = document.getElementById("form1");
		form1.action = "/brightschool/AttendanceProcess/attendanceSummaryReport";
		form1.method = "POST";
		form1.submit();

	}
	
	
	
 	$(function() {
 		$("#tabs").tabs();
 		
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepickerCD").datepicker("option", "showAnim", $(this).val());
		});
	});
 	
	/* $(function() {

		$("#tabs").tabs();

		$("#save").button().click(function() {
			addDepartment();
		});
		
		$("#monthof").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$( "#monthof" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
		$("#anim").change(function() {
			$("#dateofattendance").datepicker("option", "showAnim", $(this).val());
		});

	});
 */
	
	/*    function checkDate(){
			  var ofDate = document.getElementById('monthof').value;
			  var currentDate = new Date();
			  var sDate = new Date(ofDate);
			  
			if(ofDate!= '' && sDate > currentDate)
			  {
			    alert("Please ensure that the Date is lesser than or equals to current Date.");
			    document.getElementById('todateofattendance').value = '';
			    return false;
			  }
	   } */
</script>

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/brightschool/UserProcess/sessionTimeOut");
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
	<form id="form1" action="/brightschool/StampFeesProcess/applyFees" method="POST">
		<!-- <div style="height: 28px">
			<button id="add">Add Department</button>
			<br />
		</div> -->

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Attendance Reports</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="padding-left:150px;display: block">

						<tr>
							<td width="20%" class="alignRightFields">Date &nbsp;</td>
							<td width="80%" align="left"><label> <input name="attendancedate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="textField" id="datepickerCD" size="36"
									data-validate="validate(required)">
							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>


												<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td width="20%" class="alignRight"></td>

							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="80%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>
							<tr>
							<td><br /></td>

						</tr>
							<tr>
							<td><br /></td>

						</tr>
							
                        <tr>
							<td width="20%" class="alignRightFields">Present &nbsp;</td>
							<td width="80%" align="left"><label> <input
									name="presentstudent" type="text" class="textField"
									id="presentstudent" size="25" value="${present}" readonly/>
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td width="20%" class="alignRightFields">Absent &nbsp;</td>
							<td width="80%" align="left"><label> <input
									name="absentstudent" type="text" class="textField"
									id="absentstudent" size="25" value="${absent}" readonly/>
							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>
						</tr>

					</table>
								                    <table id="mytable"  style="border-collapse: collapse;width: 50%;border: 2px solid black;margin-left:215px;">
								                    <thead>
								                    <th style="text-align: center;border-collapse: collapse;border: 1px solid black;">Class</td>
								                    <th style="text-align: center;border-collapse: collapse;border: 1px solid black;">Present</td>
								                    <th style="text-align: center;border-collapse: collapse;border: 1px solid black;">Absent</td>
								                    </thead>
								                    <tbody>
								                        <c:forEach items="${studentAttendanceMap}" var="attendance">
								                        <c:set var="itemparts" value="${fn:split(attendance, '/')}" />
								                            <tr>
								                                <td style="text-align: center;border-collapse: collapse;border: 1px solid black;">${itemparts[0]}</td>
								                                <td style="text-align: center;border-collapse: collapse;border: 1px solid black;">${itemparts[1]}</td>
								                                <td style="text-align: center;border-collapse: collapse;border: 1px solid black;">${itemparts[2]}</td>
								                            </tr>
								                        </c:forEach>
								                        </tbody>
								                    </table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
