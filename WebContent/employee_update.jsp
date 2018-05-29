<%-- 
    Document   : updatemember_1
    Created on : Jan 10, 2013, 3:25:59 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="js/datePicker/jquery-1.7.1.js"></script>
<script src="js/datePicker/ui/jquery.ui.core.js"></script>
<script src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="js/datePicker/ui/sliderAccess.js"></script>
<script src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
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
-->
</style>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>

<script type="text/javascript">
	document.getElementById("UpdateExecutive").style.display = 'none';
</script>


<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickeradmn").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#datepickeradmn").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	$(function() {
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepickerCD").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>


<script type="text/javascript">
	$(function() {

		$("#set").button().click(function() {

			updateEmployee();

		});

		$("#cancel").button().click(function() {
			cancel();

		});
	});
</script>







<script>
	$(function() {
		$("#tabs").tabs();

	});
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
	<form action="Controller?process=EmployeeProcess&action=viewAllEmployee"
		id="form1" method="POST">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Employee Details</a></li>

				</ul>



				<div id="tabs-1">
					<table width="70%" border="0" align="center" id="table1">


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
							<td width="16%" class="alignRight">Name &nbsp;</td>
							<td width="28%"><input type="hidden" name="id" id="id"
								value="<c:out value="${employee.tid}" />" /> <label> <input type="hidden" name="teacherexternalid" id="teacherexternalid"
								value="<c:out value="${employee.teacherexternalid}" />" /> <label><input
									name="name" type="text" style="text-transform:uppercase"
									value="<c:out value="${employee.teachername}" />"
									class="textField" id="name" size="30"
									data-validate="validate(required)">
							</label></td>

							<td width="16%" class="alignRight">Gender &nbsp;</td>

							<td width="16%" class="alignLeft">Male<input type="checkbox"
								value="male" name="gender" id="male" onclick="maleCheck();"
								${employee.gender == 'male' ? 'checked' : ''} />&nbsp;
								&nbsp;Female<input type="checkbox" value="female" name="gender"
								id="female" onclick="femaleCheck()"
								${employee.gender == 'female' ? 'checked' : ''} />

							</td>

						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="20%" class="alignRight">Address &nbsp;</td>
							<td width="28%"><label> <input name="address"
									type="text" class="textField"
									value="<c:out default="" value="${employee.address}" />"
									id="address" size="30">

							</label></td>

							<td width="16%" class="alignRight">Contact Number&nbsp;</td>

							<td align="left"><label> <input name="contactnumber"
									type="text" class="textField"
									value="<c:out default="" value="${employee.contactnumber}" />"
									id="contactnumber" size="30">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="16%" class="alignRight">Email&nbsp;</td>

							<td align="left"><label> <input name="email"
									type="text" class="textField"
									value="<c:out default="" value="${employee.email}" />"
									id="email" size="30">

							</label></td>
							<td width="20%" class="alignRight">Date Of Joining &nbsp;</td>
							<td width="28%"><label> <input name="dateofjoining"
									type="text" value="<c:out value="${employee.dateofjoining}" />"
									class="textField" id="datepicker" size="30"
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


							<td width="16%" class="alignRight">Total Eperience&nbsp;</td>
							<td align="left"><label> <input
									name="totalexperience" type="text" class="textField"
									value="<c:out default="" value="${employee.totalexperience}" />"
									id="totalexperience" size="30"
									data-validate="validate(required)">

							</label></td>
							<td width="16%" class="alignRight">Qualification &nbsp;</td>

							<td align="left"><label> <input name="qualification"
									type="text" class="textField"
									value="<c:out default="" value="${employee.qualification}" />"
									id="qualification" size="30" data-validate="validate(required)">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>


						<tr>
							<td width="16%" class="alignRight">Department&nbsp;</td>

							<td width="28%"><label> <select name="department"
									id="department" style="width: 240px" onchange="dropdownclass()">
										<option selected>${employee.department}</option>

										<c:forEach items="${listDepartment}" var="listDepartment">

											<option>
												<c:out value="${listDepartment.departmentname}" />
											</option>


										</c:forEach>

								</select>

							</label></td>



							<td width="20%" class="alignRight">Designation &nbsp;</td>

							<td width="28%"><label> <select name="designation"
									id="designation" style="width: 240px"">
										<option selected>
											<c:out default="" value="${employee.designation}" />
										</option>

										<c:forEach items="${listPosition}" var="listPosition">

											<option>
												<c:out value="${listPosition.positionname}" />
											</option>


										</c:forEach>

								</select>

							</label></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>




						<tr>

							<td width="16%" class="alignRight">Salary &nbsp;</td>

							<td align="left"><label> <input name="salary"
									type="text" class="textField"
									value="<c:out default="" value="${employee.salary}" />"
									id="salary" size="30" data-validate="validate(required)">

							</label></td>

							<td width="16%" class="alignRight">Remarks&nbsp;</td>

							<td align="left"><label> <input name="remarks"
									type="text" class="textField"
									value="<c:out default="" value="${employee.remarks}" />"
									id="remarks" size="30">

							</label></td>
						<tr>

							<td></td>

						</tr>
						
					</table>




					<table id="table2" width="30%" border="0" align="center">

						<tr>

							<td></td>

						</tr>
						<tr>

							<td></td>

						</tr>
						<tr>
							<td align="center">

								<button id="set">Update</button>

							</td>
							<td><button type="submit" id="cancel">Cancel</button></td>
						</tr>


					</table>
				</div>

										</div>



						</div>




						</form>
						<script>
							$(function() {
								$("#datepicker").datepicker({
									changeYear : true,
									changeMonth : true
								});
								$("#anim").change(
										function() {
											$("#datepicker").datepicker(
													"option", "showAnim",
													$(this).val());
										});
								$("#entryDate").datepicker({
									changeYear : true,
									changeMonth : true
								});
								$("#anim").change(
										function() {
											$("#datepicker").datepicker(
													"option", "showAnim",
													$(this).val());
										});
							});
						</script>

						<script type="text/javascript">
							function cancel() {

								var form1 = document.getElementById(form1);
								form1.action = "Controller?process=PersonalProcess&action=viewAll";
								form1.submit();
							}

							function updateEmployee() {

								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=EmployeeProcess&action=updateEmployee";
								form1.submit();
							}
							
							
							function maleCheck() {

								if (document.getElementById('male').checked == true) {
									document.getElementById('female').checked = false;

								}

							}

							function femaleCheck() {

								if (document.getElementById('female').checked == true) {
									document.getElementById('male').checked = false;

								}

							}

						</script>
</body>
</html>

