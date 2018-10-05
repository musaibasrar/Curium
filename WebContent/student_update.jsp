<%-- 
    Document   : student update
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
<title>Student Update</title>
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


<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
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
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#datepickeradmn").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateoftc").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateoftc").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateoftcissued").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateoftcissued").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateofadmission").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateofadmission").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateofleaving").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateofleaving").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepickerCD").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>


<script type="text/javascript">
	$(function() {

		$(".set").button().click(function() {

			updateStudent();

		});

		$(".cancel").button().click(function() {
			cancel();

		});
		
		$("#contactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
	});
</script>
<script>
	
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
	
	function cancel() {

		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=viewAll";
		form1.submit();
	}

	function updateStudent() {

		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=updateStudent";
		form1.submit();
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
	<form id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student Details</a></li>
					<li><a href="#tabs-2">Additional Details</a></li>
					<li><a href="#tabs-3">Upload Photo</a></li>
				</ul>



				<div id="tabs-1">
				
				<table align="center">
				
				<tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo">
                    <input type="hidden" value="<c:out value="${student.studentpic}"/>" id="studentpicupdate" name="studentpicupdate">
                    <input type="hidden" value="<c:out value="${student.archive}"/>" id="studentarchiveupdate" name="studentarchiveupdate">
                    <input type="hidden" value="<c:out value="${student.droppedout}"/>" id="studentdroppedoutupdate" name="studentdroppedoutupdate">
                    <input type="hidden" value="<c:out value="${student.passedout}"/>" id="studentpassedoutupdate" name="studentpassedoutupdate">
                    </td>
                    </tr>
				
				</table>
				
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


							<td width="16%" class="alignRight">Admission Number&nbsp;</td>
							<td align="left"><label> <input name="admnno"
									type="text" class="textField"
									value="<c:out default="" value="${student.admissionnumber}" />"
									id="admnno" size="30" data-validate="validate(required)">

							</label></td>
							
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
								value="<c:out value="${student.sid}" />" /><input type="hidden" name="studentexternalid" id="studentexternalid"
								value="<c:out value="${student.studentexternalid}" />" /> <label> <input
									name="name" type="text" style="text-transform:uppercase"
									value="<c:out value="${student.name}" />" class="textField"
									id="name" size="30" data-validate="validate(required)">
							</label></td>

							<td width="16%" class="alignRight">Gender &nbsp;</td>

							<td width="16%" class="alignLeft">Male<input type="checkbox"
								value="Male" name="gender" id="male" onclick="maleCheck();"
								${student.gender == 'Male' ? 'checked' : ''} />&nbsp;
								&nbsp;Female<input type="checkbox" value="Female" name="gender"
								id="female" onclick="femaleCheck()"
								${student.gender == 'Female' ? 'checked' : ''} />

							</td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="30%" class="alignRight">Father's Name* &nbsp;</td>
							<td width="12%" align="left"><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="myclass" id="fathersname"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.fathersname}" />">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td width="30%" class="alignRight">Husband's Name &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="mothersname" type="text" class="myclass" id="mothersname"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.mothersname}" />">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>


						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>

							<td width="16%" class="alignRight">Guardian's name
								&nbsp;</td>

							<td width="28%"><label> <input name="guardian"
									type="text" class="textField" id="guardian" size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${student.guardiandetails}" />">

							</label></td>

							<td width="16%" class="alignRight">Contact Number &nbsp;</td>

							<td width="28%"><label> <input name="contactnumber"
									type="text" class="textField" id="contactnumber" size="36" maxlength="10" minlength="10"
									value="<c:out default="" value="${parents.contactnumber}" />">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						


						<tr>
							<td width="20%" class="alignRight">Date Of Birth &nbsp;</td>
							<td width="28%"><label> <input name="dateofbirth"
									type="text" value="<fmt:formatDate value="${student.dateofbirth}" pattern="yyyy-MM-dd"/>"
									class="textField" id="datepicker" size="30"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
									
									
							</label></td>

							<td width="16%" class="alignRight">Age&nbsp;</td>

							<td align="left"><label> <input name="age"
									type="text" class="textField"
									value="<c:out default="" value="${student.age}" />" id="age"
									size="30">

							</label></td>
						</tr>


<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td width="16%" class="alignRight">Qualification&nbsp;</td>
							<td align="left"><label>
								<select name="qualification" id="qualification"
									style="width: 240px;">
										<option selected>${student.qualification}</option>
										<c:forEach items="${qualificationlist}" var="qualificationlist">
											<option value="${qualificationlist.qualification}" >
												<c:out value="${qualificationlist.qualification}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
							<td width="16%" class="alignRight">Date Of Admission &nbsp;</td>

							<td align="left"><label> <input
									name="dateofadmission" type="text" class="textField"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="yyyy-MM-dd"/>"
									id="dateofadmission" size="30"
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
							<td width="16%" class="alignRight">Examination Level&nbsp;</td>

							<td width="28%">
							
							<label> 
							 <select name="examlevel" id="examlevel"
									style="width: 240px;">
										<option selected>${student.examlevel}</option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}" >
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
											</option>
										</c:forEach>
								</select>
									
							</label> 
							
							</td>
							
							<td width="20%" class="alignRight">Admitted In&nbsp;</td>
							<td width="28%">
							 <label> 
							 <select name="admittedin" id="admittedin"
									style="width: 240px;" required>
										<option selected>${student.admittedin}</option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}" >
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
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


							<td width="16%" class="alignRight">Center Code &nbsp;</td>

							<td><label> <select name="centercode" id="centercode"
									style="width: 240px;">
										<option selected>${student.centercode}</option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode}" >
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>

							</label></td>

							<td width="30%" class="alignRight">District Code
								&nbsp;</td>
							<td width="12%" align="left"><label> <select name="districtcode" id="districtcode"
									style="width: 240px;" onchange="getAdmNo()">
										<option selected>${student.districtcode}</option>
										<c:forEach items="${districtsList}" var="districtsList">
											<option value="${districtsList.districtcode}" >
												<c:out value="${districtsList.districtcode} -- ${districtsList.districtname}" />
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
						
						<tr>
						
						
							<td width="16%" class="alignRight">Language Opted&nbsp;</td>

							<td align="left">
							<label> 
								<select name="languageopted" id="languageopted"
									style="width: 240px;">
										<option selected>${student.languageopted}</option>
										<c:forEach items="${languageslist}" var="languageslist">
											<option value="${languageslist.language}" >
												<c:out value="${languageslist.language}" />
											</option>
										</c:forEach>
								</select>
							</label> 
							
							</td>

							<td width="20%" class="alignRight">Created Date &nbsp;</td>
							<td width="28%"><label> <input name="createddate"
									type="text" value="<fmt:formatDate value="${student.createddate}" pattern="yyyy-MM-dd"/>" class="textField"
									id="datepickerCD" size="30" data-validate="validate(required)">
							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						<tr>
						
						
						<td width="16%" class="alignRight">Remarks&nbsp;</td>

							<td align="left"><label> <input name="remarks"
									type="text" class="textField"
									value="<c:out default="" value="${student.remarks}" />"
									id="remarks" size="30">

							</label></td>
							
							
							<td width="16%" class="alignRight">Religion&nbsp;</td>
							<td align="left"><label>
								<select name="religion" id="religion"
									style="width: 240px;">
										<option selected>${student.religion}</option>
											<option value="ISLAM" >Islam</option>
											<option value="OTHERS" >Others</option>
								</select>
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
					</table>




					<table id="table2" width="30%" border="0" align="center">
<tr>

									<td><br /></td>
								</tr>
								
								<tr>

									<td align="center">
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									</td>
								</tr>

							<tr>

									<td><br /></td>
								</tr>
						<tr>
							<td align="center">

								<button class="set">Update</button>

							</td>
							<td><button class="cancel">Cancel</button></td>
						</tr>


					</table>
				</div>
				
				
				<div id="tabs-3">
					<table width="100%" border="0" align="center" >
						<tr>
							<td><br />
							<input type="file" name="fileToUpload" id="fileToUpload" accept="image/*" >
							</td>
						</tr>
						

</table>



<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								
								<tr>

									<td align="center">
									
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>

							<tr>

									<td><br /></td>
								</tr>
								
								<tr>
									<td align="center">
										
										
										<button class="set" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="cancel">Cancel</button>

									</td>


								</tr>
								<tr>
<td><br/></td>
</tr>
<tr>
<td><br/></td>
</tr>
<tr>
<td><br/></td>
</tr>
<tr>
<td><br/></td>
</tr>
							</table>

						</div>
						
</div>



				<div id="tabs-2">
					<table width="100%" border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<%-- <tr>
							<td width="30%" class="alignRight">Father's Name* &nbsp;</td>
							<td width="12%" align="left"><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.fathersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td width="30%" class="alignRight">Husband's Name &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="mothersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.mothersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>


						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>

							<td width="16%" class="alignRight">Guardian's name
								&nbsp;</td>

							<td width="28%"><label> <input name="guardian"
									type="text" class="textField" id="guardian" size="36"
									value="<c:out default="" value="${student.guardiandetails}" />">

							</label></td>

							<td width="16%" class="alignRight">Contact Number &nbsp;</td>

							<td width="28%"><label> <input name="contactnumber"
									type="text" class="textField" id="contactnumber" size="36"
									value="<c:out default="" value="${parents.contactnumber}" />"">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
 --%>

						<tr>
						
						<td width="16%" class="alignRight">Address &nbsp;</td>
							<td width="28%"><label> <textarea
										name="permanentaddress" type="text" class="textField"
										id="permanentaddress" rows="4" cols="35"
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>
							</label></td>
							
							<td width="16%" class="alignRight">Email
								&nbsp;</td>

							<td width="28%"><label> <input name="email"
									type="email" class="textField" id="email" size="36"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.email}" />">
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						

						<tr>
							
							<td width="20%" class="alignRight">Notes &nbsp;</td>
							<td width="28%"><label> <input name="notes"
									type="text" class="textField" id="notes" size="36"
									value="<c:out default="" value="${parents.remarks}" />">
							</label></td>
							
							<td width="16%" class="alignRight">Education &nbsp;</td>
							<td width="28%"><label> <input name="educationqualification"
									type="text" class="textField" id="educationqualification" size="36"
									value="<c:out default="" value="${student.education}" />">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						
	
								<tr align="center">
									<td width="40%" class="alignRight"> &nbsp;</td>
									<td align="center">
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>
								

						<tr>
									<td><br /></td>
								</tr>	
							<tr align="center">
									
									
									<td width="20%" class="alignRight"> &nbsp;</td>

									<td align="center">
									
										
										<button class="set" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="cancel">Cancel</button>

									</td>


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
</body>
</html>

