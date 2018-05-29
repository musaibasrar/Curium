<%--
    Document   : Attendance Master
    Created on : Jan 11, 2018, 3:49:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Holiday</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>

<link rel="stylesheet" href="css/validation/jquery.ketchup.css">
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function addHolidays() {

		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addHolidays";
		form1.method = "POST";
		form1.submit();

	}

	function searchForEmployees(staffName, staffDepartment){
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=searchEmployees&staffName="+staffName+"&staffDepartment="+staffDepartment+"";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			var staffName = document.getElementById('staffname').value;
			var staffDepartment = document.getElementById('department').value;
			searchForEmployees(staffName, staffDepartment);
		});

		$("#tabs").tabs();

		$("#save").button().click(function() {
			if (confirm('Are you sure,you want to save these configuration?')) {
				addStaffAttendanceMaster();
			}
			return false;
		});

		$("#savestudentattendance").button().click(function() {
			if (confirm('Are you sure,you want to save these configuration?')) {
				addStudentAttendanceMaster();
			}
			return false;
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
            alert(tdLength);
            if (length > trLength) {

                $('.chcktbl:not(:checked)').attr('disabled', true);
            }
            else {
                $('.chcktbl:not(:checked)').attr('disabled', false);
            }
        });

	});

	function addStudentAttendanceMaster() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addStudentAttendanceMaster";
		form1.method = "POST";
		form1.submit();
	}
	
	function addStaffAttendanceMaster() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addStaffAttendanceMaster";
		form1.method = "POST";
		form1.submit();
	}
</script>


</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
	<form id="form1" action="Controller?process=MarksDetailsProcess&action=updateMarks" method="POST">
		
		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Staff Attendance Configuration</a></li>
					<li><a href="#tabs-2">Student Attendance Configuration</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						</tr>
							<tr>
							<td><br /></td>

						</tr>
						</tr>
							<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td class="alignRightFields">Staff Name &nbsp;</td>
							<td width="12%" align="left"><label  style="font-weight: bold;color:#325F6D"> <input
									name="staffname" type="text" class="myclass" id="staffname"
									size="36">
							</label></td>
							
						</tr>
							<tr>
							<td><br /></td>

						</tr>
						<tr>
						<td class="alignRightFields">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OR </td>
							<td><br /></td>

						</tr>	
						<tr>
							<td><br /></td>

						</tr>					
						<tr>
							<td class="alignRightFields">Department &nbsp;</td>
							<td width="70%"><label> <select name="department" id="department"
									style="width: 240px" ">
										<option selected></option>
										<c:forEach items="${listDepartment}" var="listDepartment">
											<option>
												<c:out value="${listDepartment.departmentname}" />
											</option>
										</c:forEach>
								</select>

							</label> 
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td class="alignRight"></td>

							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="30%" >
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
							<td width="12%" align="left" class="alignRightFields">Weekly Off &nbsp;</td>
							<td width="12%" align="left"><label>
									<select name="weekoffstaff" id="weekoffstaff" multiple="multiple"
									style="width: 200px" >
										

										<c:forEach items="${weekOffList}" var="weekOffList">

											<option value="${weekOffList.wid}">
												<c:out value="${weekOffList.weeklyoffday}" />
											</option>


										</c:forEach>

								</select></label>
							</td>
							
						</tr>
												
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td width="12%" align="left" class="alignRightFields">Holidays &nbsp;</td>
							<td><label>
									<select name="holidaysstaff" id="holidaysstaff" multiple="multiple"
									style="width: 200px" >
										

										<c:forEach items="${holidaysList}" var="holidaysList">

											<option value="${holidaysList.shid}">
												<c:out value="${holidaysList.holidayname}" />
											</option>


										</c:forEach>

								</select></label></td>
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>

							<td width="12%" align="left" class="alignRightFields">In Time &nbsp;</td>
							<td><label> <input name="intime" id="intime" type="time"
							</input>
							
							 </td>
						</tr>
						
						<!-- <tr>

							<td width="12%" align="left" class="alignRightFields">In Time &nbsp;</td>
							<td><label> <select name="intime" id="intime"
									style="width: 40px">
										<option selected>07</option>
										<option>08</option>
										<option>09</option>
										<option>10</option>
										<option>11</option>
										<option>12</option>
										<option>01</option>
										<option>02</option>
										<option>03</option>
										<option>04</option>
										<option>05</option>
										<option>06</option>
								</select>
								

							</label>
							<label style="font-weight: bold;color:#325F6D">:</label>
							 <label> 
									<select name="mininstaff" id="mininstaff"
									style=" width: 40px">
										<option selected>00</option>
										<option>15</option>
										<option>30</option>
										<option>45</option>
								</select>
							</label>
							<label> 
									<select name="ampminstaff" id="ampminstaff"
									style=" width: 40px">
										<option selected>AM</option>
										<option>PM</option>
								</select>
							</label></td>
						</tr> -->
						
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>

							<td width="12%" align="left" class="alignRightFields">Out Time &nbsp;</td>
							<td><label> <input name="outtime" id="outtime" type="time"/>	</label>
							</td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
					</table>
					
					
					<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Staff</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Name<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Contact Number&nbsp;</th>
							<th title="click to sort" class="headerText">Department&nbsp;</th>
						</tr>
				</thead>

				<tbody>

					   <c:forEach items="${employeeList}" var="employee">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${employee.tid}"/>" class = "chcktbl"  name="employeeIDs"  value="<c:out value="${employee.tid}"/>"/></td>
                                <td  class="dataTextInActive" style="text-transform:uppercase"><a class="dataTextInActive" href="Controller?process=EmployeeProcess&action=ViewDetails&id=<c:out value='${employee.tid}'/>"><c:out value="${employee.teachername}"/></a></td>
                                <td class="dataText"><c:out value="${employee.contactnumber}"/></td>
                                <td class="dataText"><c:out value="${employee.department}"/></td>
                                
                                 

                            </tr>
                        </c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="save">Save</button> 
                    
                        </tr></tfoot>
			</table>

		</div>

				</div>
				
				
				
				<div id="tabs-2">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Weekly Off &nbsp;</td>
							<td width="16%" class="alignLeft"><label>
									<select name="weekoff" id="weekoff" multiple="multiple"
									style="width: 200px" >
										

										<c:forEach items="${weekOffList}" var="weekOffList">

											<option value="${weekOffList.wid}">
												<c:out value="${weekOffList.weeklyoffday}" />
											</option>


										</c:forEach>

								</select></label></td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Holidays List&nbsp;</td>
							<td width="16%" class="alignLeft"><label>
									<select name="holidays" id="holidays" multiple="multiple"
									style="width: 200px" >
										

										<c:forEach items="${holidaysList}" var="holidaysList">

											<option value="${holidaysList.shid}">
												<c:out value="${holidaysList.holidayname}" />
											</option>


										</c:forEach>

								</select></label></td>
							
						</tr>


						<tr>
							<td><br /></td>

						</tr>
												<tr>
							<td><br /></td>

						</tr>

						<tr>
							<td class="alignRightFields">Cutoff Time &nbsp;</td>
							<td width="70%"><label> <input name="cutoff" id="cutoff" type="time"
								</input>
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

							<td width="30%" class="alignRight"></td>

							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="30%" class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="savestudentattendance">Save</button>
							</td>
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
							<td><br /></td>
						</tr>
						
						
						<tr>
							<td><br /></td>
						</tr>
					</table>
					
				</div>
			</div>
		</div>

		

	</form>

</body>
</html>
