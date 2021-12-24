<%-- 
    Document   : Add Employee
    Created on : Jun 17, 2013, 4:17:40 PM
    Author     : CPEDUR1P5
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Employee</title>
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
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="css/datePicker/demos.css">

<style type="text/css">
.myclass {
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
	border-radius: 4px;
}

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
}

.alignRight {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignRightHead {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
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


<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script src="JavaScript/actb.js"></script>
<script src="JavaScript/common.js"></script>





<script>
	function validateFormNo() {
		if (document.getElementById("formNo").value.length == 0)

		{
			document.getElementById("formNo").style.background = 'red';
			alert("Enter The Form Number");
		}
	}

	function validateFormNum(obj) {
		document.getElementById("formNo").style.background = 'white';

		reg = /[^0-9]/g;
		obj.value = obj.value.replace(reg, "");
	}

	function validateName() {

		if (document.getElementById("name").value.length == 0)

		{
			document.getElementById("name").style.background = 'red';
			alert("Enter The Name");
		}

	}

	function validateNameAlpha(obj) {

		document.getElementById("name").style.background = 'white';

		reg = /[^a-z]/g;
		obj.value = obj.value.replace(reg, "");
	}

	function validateContact() {

		if (document.getElementById("contactNO").value.length == 0) {
			document.getElementById("contactNO").style.background = 'red';

			alert("Enter Contact number");

		}

	}

	function validateContactNum(obj) {

		document.getElementById("contactNO").style.background = 'white';

		reg = /[^0-9]/g;
		obj.value = obj.value.replace(reg, "");

	}

	function validateNameContact() {
		if (document.getElementById("name").value.length == 0)

		{
			document.getElementById("name").style.background = 'red';
			alert("Enter The Name ");
		}

	}
</script>


<script>
	function validateWhileSave() {
		if (document.getElementById("name").value.length == 0)

		{
			document.getElementById("name").style.background = 'red';
			alert("Enter The Name");
		}

		if (document.getElementById("contactNO").value.length == 0)

		{
			document.getElementById("contactNO").style.background = 'red';
			alert("Enter The Contact Number");
		}
	}
</script>






<script type="text/javascript">
	$(function() {

		$("#set").button().click(function() {
			addPatientWithAppointment();

		});
		$("#saveAndSetToday").button().click(function() {
			addPatientWithTodaysAppointment();

		});
		$(".save").button().click(function() {
			addEmployee();

		});

		$(".cancel").button().click(function() {
			Cancel();

		});
		
		 $("#contactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     		     
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
		 $("#salary").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     		     
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
	});
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


<script type="text/javascript">
	
	
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
	
	function disableDepartment() {
		document.getElementById('department').disabled = true;
	}
	
	function enableDepartment() {
		document.getElementById('department').disabled = false;
	}

	function CalculateAge(value) {
		var test = document.getElementById('datepicker').value;
		var today = new Date();
		var birthDate = new Date(test);
		var age = today.getFullYear() - birthDate.getFullYear();
		var m = today.getMonth() - birthDate.getMonth();
		if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
			age--;
		}
		//return age;
		document.getElementById('age').value = age;
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
	<form id="form1" action="Controller?process=PersonalProcess&action=add" method="post">
		
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		
		<div>
		
			<div id="tabs">
				
				<ul>
					<li><a href="#tabs-1">Details</a></li>
				</ul>

				<div id="tabs-1">

					<table style="width: auto;height: auto;" border="0" align="center" id="table1">
					
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Name* &nbsp;</td>
							<td><label> <input
									name="name" type="text" class="myclass" id="name" size="36" 
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Module &nbsp;</td>
							<td>&nbsp;Queries<input
								type="checkbox" value="queries" name="gender" id="yes:queries"
								onclick="yesCheck(this.id);enableDepartment()" />&nbsp; &nbsp;Appointments<input
								type="checkbox" value="appointments" name="gender" id="no:queries"
								onclick="noCheck(this.id);disableDepartment()" />
							</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
						
						<tr>
							<td class="alignLeft">Department &nbsp;</td>
							<td><label>
									<select name="department" id="department"
									style="width: 290px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>

										<c:forEach items="${listDepartment}" var="listDepartment">

											<option>
												<c:out value="${listDepartment.departmentname}" />
											</option>


										</c:forEach>

								</select></label></td>
								
								<td class="alignLeft" style="padding-left: 20px;">Current Employee &nbsp;</td>
							<td>&nbsp;Yes<input
								type="checkbox" value="1" name="currentemployee" id="yes:employee"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="currentemployee" id="no:employee"
								onclick="noCheck(this.id)" />
							</td>
								

						</tr>


						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Remarks &nbsp;</td>
							<td><label> <input
									name="remarks" type="text" class="myclass" id="remarks"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
								<tr>
									<td></td><td></td>
									<td>
										<button id="save" class="save" onmouseover="validateNameContact();">Save</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancel" class="cancel">Cancel</button>

									</td>
								</tr>
					</table>
					</div>
					
					
					
					</div>
					
						</div>
						</form>
						<script type="text/javascript">
							function addEmployee() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=EmployeeProcess&action=AddEmployee";
								form1.submit();
							}

							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=EmployeeProcess&action=viewAllEmployee";
								form1.submit();
							}

							function validateEmptyField(elementName) {

								var txtBox = document
										.getElementById(elementName);

								if (txtBox.value == "") {

									txtBox.className = "emptyFieldSet";

								} else if (txtBox.value != "") {
									txtBox.className = "textField";

								}

							}
							function notEmptyField(elementName) {
								alert(elementName);
								var txtBox = document
										.getElementById(elementName);
								if (txtBox.value != "") {
									txtBox.className = "textField";

								} else if (txtBox.value == "") {
									txtBox.className = "emptyFieldSet";

								}

							}
						</script>
</body>
</html>


