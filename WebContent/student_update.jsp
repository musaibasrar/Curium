<%-- 
    Document   : updatemember_1
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

		$("#set").button().click(function() {

			updateStudent();

		});

		$("#cancel").button().click(function() {
			cancel();

		});
	});
</script>
<script>
	function issues() {

		var distlistitem = document.getElementById("subscriptionfor");
		var distlistitemtext = distlistitem.options[distlistitem.selectedIndex].text;

		if (distlistitemtext == "1 year") {
			document.getElementById("totalissues").value = "24";
		} else if (distlistitemtext == "2 years") {
			document.getElementById("totalissues").value = "48";
		} else if (distlistitemtext == "3 years") {
			document.getElementById("totalissues").value = "72";
		} else if (distlistitemtext == "5 years") {
			document.getElementById("totalissues").value = "120";
		} else if (distlistitemtext == "Life Time") {
			document.getElementById("totalissues").value = "240";
		}

	}

	function calculateIssues() {

		var totalissues = document.getElementById("totalissues").value;
		var fromissues = document.getElementById("fromkmissueno").value;

		var toissues = parseInt(totalissues, 10) + parseInt(fromissues, 10) - 1;
		document.getElementById("tokmissueno").value = toissues;

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
		
		$("#updatetwo").button().click(function() {
			updateStudent();

		});
		$("#updatethree").button().click(function() {
			updateStudent();

		});

		$("#canceltwo").button().click(function() {
			Cancel();

		});
		$("#cancelthree").button().click(function() {
			Cancel();

		});
	});
	
	


</script>
</head>
<body>
	<form action="Controller?process=PersonalProcess&action=viewAll"
		id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student Details</a></li>
					<li><a href="#tabs-2">Parent's Details</a></li>
					<li><a href="#tabs-3">Upload Photo</a></li>
				</ul>



				<div id="tabs-1">
				
				<table align="center">
				
				<tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo">
                    <input type="hidden" value="<c:out value="${student.studentpic}"/>" id="studentpicupdate" name="studentpicupdate">
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
							<td width="16%" class="alignRight">Studying in Class&nbsp;</td>

							<td width="28%">
							<%-- 
							<label> <input name="classsec"
									type="text" class="textField"
									value="<c:out default="" value="${student.classstudying}" />"
									id="classsec" size="30" style="text-transform: uppercase;">

							</label> --%>
							
							<label> <select name="classsec" id="classsec"
									style="width: 120px">
										<option selected><c:out default="" value="${classstudying}" /></option>
										<option>nursery</option>
										<option>L.K.G</option>
										<option>U.K.G</option>
										<option>I</option>
										<option>II</option>
										<option>III</option>
										<option>IV</option>
										<option>V</option>
										<option>VI</option>
										<option>VII</option>
										<option>VIII</option>
										<option>IX</option>
										<option>X</option>
								</select>

							</label> <label> 
									<select name="secstudying" id="secstudying"
									style=" width: 80px">
										<option selected><c:out default="" value="${secstudying}" /></option>
										<option></option>
										<option>A</option>
										<option>B</option>
										<option>C</option>
										<option>D</option>
										<option>E</option>
										<option>F</option>
										<option>G</option>

								</select>
							</label>
							
							</td>



							<td width="20%" class="alignRight">Admitted in class &nbsp;</td>

							<%-- <td width="28%"><label> <input name="admclass"
									type="text" class="textField"
									value="<c:out default="" value="${student.classadmittedin}" />"
									id="admclass" size="30" style="text-transform: uppercase;">

							</label> --%>
							<td width="28%">
							<label> <select name="admclass" id="admclass"
									style="width: 115px">
										<option selected><c:out default="" value="${classadm}" /></option>
										<option>nursery</option>
										<option>L.K.G</option>
										<option>U.K.G</option>
										<option>I</option>
										<option>II</option>
										<option>III</option>
										<option>IV</option>
										<option>V</option>
										<option>VI</option>
										<option>VII</option>
										<option>VIII</option>
										<option>IX</option>
										<option>X</option>
								</select>

							</label> <label> 
									<select name="admsec" id="admsec"
									style=" width: 80px">
										<option selected><c:out default="" value="${secadm}" /></option>
										<option></option>
										<option>A</option>
										<option>B</option>
										<option>C</option>
										<option>D</option>
										<option>E</option>
										<option>F</option>
										<option>G</option>

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


							<td width="16%" class="alignRight">Last Class Studied &nbsp;</td>

							<td><label> <select name="lastclass" id="lastclass"
									style="width: 200px" onchange="dropdown()">
										<option selected>${student.stdlaststudied}</option>
										<option>nursery</option>
										<option>L.K.G</option>
										<option>U.K.G</option>
										<option>I</option>
										<option>II</option>
										<option>III</option>
										<option>IV</option>
										<option>V</option>
										<option>VI</option>
										<option>VII</option>
										<option>VIII</option>
										<option>IX</option>
										<option>X</option>
								</select>

							</label></td>

							<td width="30%" class="alignRight">Last School Attended
								&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="lastschool" type="text" class="textField"
									value="<c:out default="" value="${student.schoollastattended}" />"
									id="lastschool" size="30" data-validate="validate(required)">
							</label></td>
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
							<td width="16%" class="alignRight">Date Of Admission &nbsp;</td>

							<td align="left"><label> <input
									name="dateofadmission" type="text" class="textField"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="yyyy-MM-dd"/>"
									id="datepickeradmn" size="30"
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
							<td width="16%" class="alignRight">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 200px">
										<option selected>${student.bloodgroup}</option>
										<option>A +ve</option>
										<option>A -ve</option>
										<option>AB +ve</option>
										<option>AB -ve</option>
										<option>O +ve</option>
										<option>O -ve</option>
										<option>B +ve</option>
										<option>B -ve</option>
								</select>

							</label></td>
							<td width="16%" class="alignRight">Nationality &nbsp;</td>

							<td><label> <select name="nationality"
									id="nationality" style="width: 200px" onchange="dropdown()">
										<option selected>${student.nationality }</option>
										<option>Indian</option>
										<option>Other</option>
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


							<td width="16%" class="alignRight">Religion &nbsp;</td>

							<td align="left"><%-- <label> <input name="religion"
									type="text" class="textField"
									value="<c:out default="" value="${student.religion}" />"
									id="religion" size="30">

							</label> --%>
							
							<label> <select name="religion" onblur="validateNameContact();"
									id="religion" style="width: 240px" onkeypress="return validateContactNum(this);">
										<option selected>${student.religion}</option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
								</select>

							</label>
							
							
							</td>


							<td width="16%" class="alignRight">Caste &nbsp;</td>

							<td align="left"><label> <input name="caste"
									type="text" class="textField"
									value="<c:out default="" value="${student.caste}" />"
									id="caste" size="30">

							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>



						<tr>


							<td width="16%" class="alignRight">Mother Tongue &nbsp;</td>

							<td align="left"><%-- <label> <input name="motherT"
									type="text" class="textField"
									value="<c:out default="" value="${student.mothertongue}" />"
									id="motherT" size="30">

							</label> --%>
							
							<label>
							<select name="motherT" onblur="validateNameContact();"
									id="motherT" style="width: 240px" onkeypress="return validateContactNum(this);">
										<option selected>${student.mothertongue}</option>
										<option>Urdu</option>
										<option>Hindi</option>
										<option>English</option>
										<option>Kannada</option>
										<option>Marathi</option>
										<option>Telugu</option>
										<option>Tamil</option>
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
						<tr>

							<td></td>

						</tr>
						<tr>

							<td></td>

						</tr>

						<tr>

							<td></td>

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

								<button id="set">Update</button>

							</td>
							<td><button type="submit" id="cancel">Cancel</button></td>
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
										
										
										<button id="updatethree" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelthree">Cancel</button>

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

						<tr>

							<td width="30%" class="alignRight"><label> <font
									color="red"><div id="mydiv"></div></font>
							</label></td>
							<td width="20%" class="alignRight"></td>
							<td class="alignRight"><font color="red"><div
										id="mydivmobile"></div></font></td>
						</tr>



						<tr>
							<td width="30%" class="alignRight">Father's Name* &nbsp;</td>
							<td width="12%" align="left"><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.fathersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td width="30%" class="alignRight">Mother's Name &nbsp;</td>
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

							<td width="16%" class="alignRight">Father's Profession
								&nbsp;</td>

							<td width="28%"><label> <input name="profession"
									type="text" class="textField" id="profession" size="36"
									value="<c:out default="" value="${parents.professsion}" />">

							</label></td>



							<td width="16%" class="alignRight">Annual Income &nbsp;</td>

							<td width="28%"><label> <input name="annualincome"
									type="text" class="textField" id="annualincome" size="36"
									value="<c:out default="" value="${parents.parentsannualincome}" />">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>

							<td width="16%" class="alignRight">Contact Number &nbsp;</td>

							<td width="28%"><label> <input name="contactnumber"
									type="text" class="textField" id="contactnumber" size="36"
									value="<c:out default="" value="${parents.contactnumber}" />"">

							</label></td>



							<td width="16%" class="alignRight">CO-Contact Number &nbsp;</td>

							<td width="28%"><label> <input
									name="cocontactnumber" type="text" class="textField"
									id="cocontactnumber" size="36"
									value="<c:out default="" value="${parents.cocontactnumber}" />"">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>


						<tr>

							<td width="16%" class="alignRight">Email
								&nbsp;</td>

							<td width="28%"><label> <input name="email"
									type="email" class="textField" id="email" size="36"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.email}" />">
									

							<td width="20%" class="alignRight">Number Of Dependents
								&nbsp;</td>

							<td width="28%"><label> <input name="noofdependents"
									type="text" class="textField" id="noofdependents" size="36"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.noofdependents}" />">

							</label></td>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>


							<td width="16%" class="alignRight">Permanent Address &nbsp;</td>

							<td width="28%"><label> <textarea
										name="permanentaddress" type="text" class="textField"
										id="permanentaddress" rows="4" cols="35"
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>

							</label></td>


							<td width="20%" class="alignRight">Temporary Address &nbsp;</td>
							<td width="28%"><label> <textarea
										name="temporaryaddress"
										value="<c:out default="" value="${parents.addresstemporary}" />"
										type="text" class="textField" id="temporaryaddress" rows="4"
										cols="35">${parents.addresstemporary}</textarea>
							</label></td>
						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>


						<tr>


							<td width="16%" class="alignRight">Number Of Dependents
								&nbsp;</td>

							<td width="28%"><label> <input name="noofdependents"
									type="text" class="textField" id="noofdependents" size="36"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.noofdependents}" />">

							</label></td>


							<td width="20%" class="alignRight">Notes &nbsp;</td>
							<td width="28%"><label> <input name="remarks"
									type="text" class="textField" id="remarks" size="36"
									value="<c:out default="" value="${parents.remarks}" />">
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
									
										
										<button id="updatetwo" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="canceltwo">Cancel</button>

									</td>


<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

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
</body>
</html>

