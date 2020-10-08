<%-- 
    Document   : Employee update
    Created on : Jan 10, 2013, 3:25:59 PM
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
<title>Employee Update</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">

<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="js/datePicker/jquery-1.7.1.js"></script>
<script src="js/datePicker/ui/jquery.ui.core.js"></script>
<script src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="js/datePicker/ui/sliderAccess.js"></script>
<script src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="css/datePicker/demos.css">
<style type="text/css">

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
	color: black;
	text-transform: capitalize;
	text-transform:uppercase;
	height: 30px;
	border-radius: 5px;
}

.alignRight {
	font-family: Tahoma;
	font-size: 14px;
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
</style>

<script>

	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerleaving").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepickerleaving").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>


<script type="text/javascript">
	$(function() {

		$(".set").button().click(function() {

			updateEmployee();

		});

		$(".cancel").button().click(function() {
			cancel();

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
							
							
							function yesCheck(id) {

								if (document.getElementById(id).checked == true) {
									var splitId = id.split(':');
									document.getElementById('no:'+splitId[1]).checked = false;
								}

							}
							function noCheck(id) {

								if (document.getElementById(id).checked == true) {
									var splitId = id.split(':');
									document.getElementById('yes:'+splitId[1]).checked = false;
								}

							}

						</script>





<script>
	$(function() {
		$("#tabs").tabs();
		
		$(".nexttab").click(function() {
		    var selected = $("#tabs").tabs("option", "selected");
		    $("#tabs").tabs("option", "selected", selected + 1);
		});
		
		$(".prevtab").click(function() {
		    var selected = $("#tabs").tabs("option", "selected");
		    $("#tabs").tabs("option", "selected", selected - 1);
		});

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
		id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Employee Details</a></li>
					<li><a href="#tabs-4">Upload Documents</a></li>
					<li><a href="#tabs-2">Bank Details</a></li>
					<li><a href="#tabs-3">Additional Details</a></li>

				</ul>



				<div id="tabs-1">
					<table width="90%" border="0" align="center" id="table1">


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
							<td  class="alignRight">Name &nbsp;</td>
							<td ><input type="hidden" name="id" id="id"
								value="<c:out value="${employee.tid}" />" /> <label> <input type="hidden" name="teacherexternalid" id="teacherexternalid"
								value="<c:out value="${employee.teacherexternalid}" />" /> 
								<input type="hidden" name="branchid" id="branchid"
								value="<c:out value="${employee.branchid}" />" /> 
								<label><input
									name="name" type="text" style="font-size: 16px;"
									value="<c:out value="${employee.teachername}" />"
									class="textField" id="name" size="26"
									>
							</label></td>

							<td  class="alignRight">Gender &nbsp;</td>


							<td  class="alignLeft" style="font-size: 16px;">&nbsp;Male<input
								type="checkbox" value="male" name="gender" id="yes:male" onclick="yesCheck(this.id);" 
								${employee.gender == 'male' ? 'checked' : ''}/>&nbsp; &nbsp;Female<input
								type="checkbox" value="female" name="gender" id="no:male"
								onclick="noCheck(this.id);" ${employee.gender == 'female' ? 'checked' : ''}/>
							</td>

						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td  class="alignRight">Qualification &nbsp;</td>

							<td align="left"><label> <input name="qualification"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.qualification}" />"
									id="qualification" size="26" >

							</label></td>

							<td  class="alignRight">Total Eperience&nbsp;</td>
							<td align="left"><label> <input
									name="totalexperience" type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.totalexperience}" />"
									id="totalexperience" size="26"
									>

							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						<tr>
						
						

							<td width="20%" class="alignRight">Designation &nbsp;</td>

							<td ><label> <select name="designation"
									id="designation" style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white">
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
							
							<td  class="alignRight">Department&nbsp;</td>

							<td ><label> <select name="department"
									id="department" style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white" onchange="dropdownclass()">
										<option selected>${employee.department}</option>

										<c:forEach items="${listDepartment}" var="listDepartment">

											<option>
												<c:out value="${listDepartment.departmentname}" />
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
						
							<td  class="alignRight">Contact Number&nbsp;</td>

							<td align="left"><label> <input name="contactnumber"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.contactnumber}" />"
									id="contactnumber" size="26">

							</label></td>
							
							<td width="20%" class="alignRight">Address &nbsp;</td>
							<td ><label> <input name="address"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.address}" />"
									id="address" size="26">

							</label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						
							<td  class="alignRight">Aadhar Number&nbsp;</td>

							<td align="left"><label> <input name="aadharnumber"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.aadharnumber}" />"
									id="aadharnumber" size="26">

							</label></td>
							
							<td width="20%" class="alignRight">PAN &nbsp;</td>
							<td ><label> <input name="pan"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.pan}" />"
									id="pan" size="26">

							</label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td  class="alignRight">Email&nbsp;</td>

							<td align="left"><label> <input name="email"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.email}" />"
									id="email" size="26">

							</label></td>
							<td width="20%" class="alignRight">Date Of Joining &nbsp;</td>
							<td ><label> 
										<input name="dateofjoining" style="font-size: 16px;"
									type="text" value="<fmt:formatDate value="${employee.dateofjoining}" pattern="dd/MM/yyyy"/>"
									class="textField" id="datepicker" size="26"
									onchange="CalculateAge(this)"
									>
							
						<%-- 	<input name="dateofjoining"
									type="text" value="<fmt:formatDate value="${employee.dateofjoining}" pattern="dd-MM-yyyy"/>" 
									class="textField" id="datepicker" size="26"
									> --%>
							</label></td>


						</tr>

						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>
						
						<tr>

							<td  class="alignRight">Current Employee &nbsp;</td>

							<td  class="alignLeft" style="font-size: 16px;">&nbsp;Yes<input
								type="checkbox" value="1" name="currentemployee" id="yes:employee"
								onclick="yesCheck(this.id);" ${employee.currentemployee == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="currentemployee" id="no:employee"
								onclick="noCheck(this.id);" ${employee.currentemployee == '0' ? 'checked' : ''}/>
							</td>

							<td  class="alignRight">Remarks&nbsp;</td>

							<td align="left"><label> <input name="remarks"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.remarks}" />"
									id="remarks" size="26">

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
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a class="nexttab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Next</a></td>
									</tr>
						<tr>

							<td><br></td>

						</tr>
						<tr>
							<td>

								<button id="set1" class="set">Update</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" id="cancel1" class="cancel">Cancel</button>
							</td>
							<td></td>
						</tr>


					</table>
				</div>

				<div id="tabs-2">
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
							<td  class="alignRight">Bank Name &nbsp;</td>
							<td >
								<label><input
									name="bankname" type="text"  style="font-size: 16px;"
									value="<c:out value="${employee.bankname}" />"
									class="textField" id="bankname" size="26"
									>
							</label></td>

							<td  class="alignRight">Bank IFSC &nbsp;</td>

							<td  class="alignLeft"><label><input
									name="bankifsc" type="text"  style="font-size: 16px;"
									value="<c:out value="${employee.bankifsc}" />"
									class="textField" id="bankifsc" size="26"
									>
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
							<td width="20%" class="alignRight">Account Number &nbsp;</td>
							<td ><label> <input name="accno"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${employee.accno}" />"
									id="accno" size="26">
							</label></td>
						</tr>

						<tr>
							<td></td>
						</tr>
						
					</table>

					<table id="table2" width="30%" border="0" align="center">

						<tr>

							<td><br><br></td>

						</tr>
						<tr>
											
										<td align="center">
										&nbsp;&nbsp;
										
										<a class="nexttab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Next</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a class="prevtab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Previous</a></td>
									</tr>
						<tr>

							<td><br><br></td>

						</tr>
						<tr>
							
							<td align="center">

								<button id="set2" class="set">Update</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" id="cancel" class="cancel">Cancel</button>
							</td>
							<td></td>
						</tr>


					</table>
				</div>
				
				<div id="tabs-3">
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
							<td  class="alignRight">Date of Leaving &nbsp;</td>
							<td >
								<label>
								<input name="leavingdate"
									type="text" 
									value="<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>"
									value="${employee.leavingdate}" style="font-size: 16px;"
									class="textField" id="datepickerleaving" size="26"
									>
							</label></td>

						</tr>
						
						<tr>
							<td></td>
						</tr>
						
					</table>

					<table id="table2" width="30%" border="0" align="center">

						<tr>

							<td><br><br></td>

						</tr>
						
						<tr>
									
										<td align="center">
										<a class="prevtab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Previous</a></td>
									</tr>
									
						<tr>

							<td><br></td>

						</tr>
						<tr>
							<td align="center">

								<button id="set3" class="set">Update</button>
								&nbsp;&nbsp;
								<button type="submit" id="cancel3" class="cancel">Cancel</button>
							</td>
						</tr>


					</table>
				</div>
				
				<div id="tabs-4">
				
							<table width="20%" style="float: left" align="center">
									<tr>
									<td></td>
								</tr>
							</table>
							
							<table width="20%" style="float: left" align="center">
								<tr>
									<td><label style="font-size: 12px;color: #eb6000;font-weight: bold;">Note: Upload only .jpg images</label></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
															
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 1</label><br />
									<img src="data:image;base64,<c:out value="${employee.doc1}"/>" alt="doc1" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc1}"/>" id="employeedoc1update" name="employeedoc1update">
									 <input type="file" name="employeedoc1" id="employeedoc1" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 2</label><br /> 
									<img src="data:image;base64,<c:out value="${employee.doc2}"/>" alt="doc2" style="width: 150px;height: 150px;">
									<input type="hidden" value="<c:out value="${employee.doc2}"/>" id="employeedoc2update" name="employeedoc2update">
									<input type="file" name="employeedoc2" id="employeedoc2" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 3</label><br />
									 <img src="data:image;base64,<c:out value="${employee.doc3}"/>" alt="doc3" style="width: 150px;height: 150px;">
									 <input type="hidden" value="<c:out value="${employee.doc3}"/>" id="employeedoc3update" name="employeedoc3update">
									 <input type="file" name="employeedoc3" id="employeedoc3" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 4</label><br /> 
										<img src="data:image;base64,<c:out value="${employee.doc4}"/>" alt="doc4" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc4}"/>" id="employeedoc4update" name="employeedoc4update">
									<input type="file" name="employeedoc4" id="employeedoc4" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 5</label><br />
										<img src="data:image;base64,<c:out value="${employee.doc5}"/>" alt="doc5" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc5}"/>" id="employeedoc5update" name="employeedoc5update">
									 <input type="file" name="employeedoc5" id="employeedoc5" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								</table>
								
								<table width="30%" align="center">
								
								<tr>
									<td></td>
								</tr>
								
								<tr>
								<td><br><br></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 6</label><br />
										<img src="data:image;base64,<c:out value="${employee.doc6}"/>" alt="doc6" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc6}"/>" id="employeedoc6update" name="employeedoc6update">
									 <input type="file" name="employeedoc6" id="employeedoc6" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 7</label><br /> 
									<img src="data:image;base64,<c:out value="${employee.doc7}"/>" alt="doc7" style="width: 150px;height: 150px;">
									<input type="hidden" value="<c:out value="${employee.doc7}"/>" id="employeedoc7update" name="employeedoc7update">
									<input type="file" name="employeedoc7" id="employeedoc7" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 8</label><br />
										<img src="data:image;base64,<c:out value="${employee.doc8}"/>" alt="doc8" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc8}"/>" id="employeedoc8update" name="employeedoc8update">
									 <input type="file" name="employeedoc8" id="employeedoc8" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 9</label><br /> 
										<img src="data:image;base64,<c:out value="${employee.doc9}"/>" alt="doc9" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc9}"/>" id="employeedoc9update" name="employeedoc9update">
									<input type="file" name="employeedoc9" id="employeedoc9" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Doc 10</label><br /> 
										<img src="data:image;base64,<c:out value="${employee.doc10}"/>" alt="doc10" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${employee.doc10}"/>" id="employeedoc10update" name="employeedoc10update">
									<input type="file" name="employeedoc10" id="employeedoc10" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
								<td><br><br><br></td>
								<td><br><br><br></td>
								</tr>
								
								
								
							</table>

					<table width="50%" align="center">
									<tr>

										<td></td>
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
										<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
									
									</tr>

									<tr>
										<td><br /></td>
									</tr>
									
									<tr>
										<td >


										<button id="set4" class="set">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancel3" class="cancel">Cancel</button>

										</td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td><br><br><br><br><br><br><br><br><br>
										<br><br><br><br><br><br><br><br><br><br>
										<br><br><br><br><br><br><br><br><br><br>
										<br><br><br><br><br><br><br><br><br><br>
										<br><br><br><br><br><br><br><br><br><br></td>
									</tr>
							</table>
						</div>
				</div>
				
			</div>
						</div>




						</form>
						
</body>
</html>

