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

.textbox {
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
	height: auto;
	color: black;
	text-transform: capitalize;
	border-radius: 4px;
}

.dropdown {
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
	text-transform: capitalize;
	border-radius: 4px;
	background: white;
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
<script type="text/javascript" src="js/datetimepicker_css.js"></script>

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
	
	 function enterOtherSpecialCategory() {
	        var distlistitem = document.getElementById("specialcategory");
	        var distlistitemtext = distlistitem.options[distlistitem.selectedIndex].text;

	        if (distlistitemtext == "Others (Please Specify)") {
	            document.getElementById("specialcategory").style.display = "none";
	            document.getElementById("categoryname").style.display = "none";
	            document.getElementById("newcateg").style.display = '';
	        }
	    }
</script>


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
		$("#datepickeradmn").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
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
			dateFormat: 'dd/mm/yy',
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
			dateFormat: 'dd/mm/yy',
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
			dateFormat: 'dd/mm/yy',
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
			dateFormat: 'dd/mm/yy',
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
			dateFormat: 'dd/mm/yy',
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
	
	function CalculateAge(value) {
		var dateOfBirth = document.getElementById('datepicker').value;
		var from = dateOfBirth.split("/");
		var today = new Date();
		var birthDate = new Date(from[2],from[1] - 1,from[0]);
		var month = birthDate.getMonth();
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
		$("#updatefour").button().click(function() {
			updateStudent();

		});
		$("#updatefive").button().click(function() {
			updateStudent();

		});

		$("#canceltwo").button().click(function() {
			Cancel();

		});
		$("#cancelthree").button().click(function() {
			Cancel();

		});
		$("#cancelfour").button().click(function() {
			Cancel();

		});
		$("#cancelfive").button().click(function() {
			Cancel();

		});
		 $("#parentsannualincome").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
		 $("#contactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
		 $("#emergencycontactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
		 $("#cocontactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
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
	<form action="Controller?process=PersonalProcess&action=viewAll"
		id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student Details</a></li>
					<li><a href="#tabs-2">Parent's Details</a></li>
					<li><a href="#tabs-3">Upload Photo</a></li>
					<li><a href="#tabs-4">Additional Details</a></li>
					<li><a href="#tabs-5">Previous School Details</a></li>
					<!-- <li><a href="#tabs-6">Bank Details</a></li> -->
				</ul>



				<div id="tabs-1">
				
				<table align="center">
				
				<tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
                    <input type="hidden" value="<c:out value="${student.studentpic}"/>" id="studentpicupdate" name="studentpicupdate">
                    <input type="hidden" value="<c:out value="${student.passedout}"/>" id="passedout" name="passedout">
                    <input type="hidden" value="<c:out value="${student.droppedout}"/>" id="droppedout" name="droppedout">
                    <input type="hidden" value="<c:out value="${student.leftout}"/>" id="leftout" name="leftout">
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
							<td class="alignLeft">Admission Number* &nbsp;</td>
							<td><label> <input name="admnno" required value="${student.admissionnumber}"
									type="text" class="myclass" id="admnno" size="30"
									>

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">Registration No. &nbsp;</td>

									<td ><label> <input
											name="registrationnumber" type="text" class="myclass" size="30" readonly="readonly"
											id="registrationnumber" size="30" value="${student.registrationnumber}">

									</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Student Name* &nbsp;</td>
							<td align="left"><label>
							
										<input type="hidden" name="id" id="id"
								value="<c:out value="${student.sid}" />" /><input type="hidden" name="studentexternalid" id="studentexternalid"
								value="<c:out value="${student.studentexternalid}" />" /> <label> <input
									name="name" type="text" required
									value="<c:out value="${student.name}" />" class="myclass"
									id="name" size="30" data-validate="validate(required)">
							
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Gender &nbsp;</td>
							<td class="alignLeft">&nbsp;
							
									Male<input type="checkbox"
								value="Male" name="gender" id="yes:male" onclick="yesCheck(this.id);"
								${student.gender == 'Male' ? 'checked' : ''} />&nbsp;
								&nbsp;Female<input type="checkbox" value="Female" name="gender"
								id="no:male" onclick="noCheck(this.id);"
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
						<tr>
							<td class="alignLeft">Date Of Birth &nbsp;</td>
							<td ><label>
							
									 <input name="dateofbirth" autocomplete="false"
									type="text" value="<fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepicker" size="30"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
									
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Age &nbsp;</td>
							<td><label>
								<input name="age"
									type="text" class="myclass"
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

							<td class="alignLeft">Place Of Birth&nbsp;</td>
							<td><label> 
								 <input name="place"
									type="text" class="myclass"
									value="<c:out default="" value="${student.placeofbirth}" />"
									id="place" size="30" >
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Date of admission&nbsp;</td>
							<td ><label>
							
							<input
									name="dateofadmission" type="text" class="myclass" autocomplete="false"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>"
									id="dateofadmission" size="30">
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>


							<td class="alignLeft">Course&nbsp;</td>
							<td><label> 
							
								<select name="classsec" id="classsec" class="dropdown"
									style="width: 186px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${classstudying}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							
							</label> <label> 
							
							
							<select name="secstudying" id="secstudying" class="dropdown"
									style="width: 70px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${secstudying}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<option value="${classdetailslist.section}">
												<c:out value="${classdetailslist.section}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Blood Group &nbsp;</td>

							<td><label> 
							
									<select name="bloodgroup" class="dropdown"
									id="bloodgroup" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
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

						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
													
							<td class="alignLeft">Mother Tongue &nbsp;</td>
							<td>
							 <label> 
							 	<select name="motherT" class="dropdown"
									id="motherT" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
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
							
							
							<td class="alignLeft" style="padding-left: 20px;border-radius: 4px;background: white;height: 28px;">Religion &nbsp;</td>

							<td>
								<label> 
								
									<select name="religion" 
									id="religion"class="dropdown"
									style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.religion}</option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
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

						<tr>
						
							<td class="alignLeft">Caste/Category &nbsp;</td>
							<td ><label> 
							
							<input name="studentscaste" class="myclass" 
									type="text" class="textField" id="studentscaste" value="${student.studentscaste}" size="30">

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Nationality &nbsp;</td>

							<td><label> 
							
								<select name="nationality" class="dropdown"
									id="nationality" style="width: 258px;border-radius: 4px;background: white;height: 28px;" >
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

							<td class="alignLeft">Aadhaar Card No.&nbsp;</td>
							<td><label> 
							
							<input
									name="studentscastecertno" type="text" class="myclass" value="${student.studentscastecertno}"
									id="studentscastecertno" size="30">

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Second Language&nbsp;</td>
							<td><label> 
							
							
							<select name="secondlanguage" class="dropdown"
									id="secondlanguage" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.secondlanguage}</option>
										<option>Urdu</option>
										<option>Telugu</option>
										<option>Hindi</option>
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
							<td class="alignLeft">Created Date &nbsp;</td>
							<td ><label>
									
									<input name="createddate"
									type="text" value="<fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/>" class="myclass"
									id="datepickerCD" size="30">
									
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
						
						<!-- End here -->
						
						



					</table>




					<table id="table2" style="width: auto;height: auto;" border="0" align="center">
<tr>

									<td><br /></td>
								</tr>
								
								<tr>

									<td align="center" style="padding-left: 150px;">
									
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									<!-- &nbsp;&nbsp;&nbsp;
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px;pointer-events: none; cursor: default;" href="#">Previous</a> -->
									</td>
								</tr>

							<tr>

									<td><br><br></td>
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
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;
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


						<div id="tabs-4">
							<table style="width: auto;height: auto;" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td class="alignLeft">Class on leaving&nbsp;</td>
									<td ><label> <input
											name="classonleaving" type="text"
											value="<c:out default="" value="${student.classonleaving}" />" class="myclass" id="classonleaving" style="text-transform:uppercase"
											size="30" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Date of leaving the school&nbsp;</td>
									<td ><label> <input
									name="dateofleaving" type="text" class="textField" autocomplete="false"
									id="dateofleaving" size="30" value="<fmt:formatDate type="date" value="${student.dateleaving}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)"><!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td  class="alignLeft">Reason for leaving &nbsp;</td>

									<td><label> <input name="reasonforleaving"
											type="text" class="textField" id="reasonforleaving" size="30"
											value="<c:out default="" value="${student.reasonleaving}" />"
											onkeypress="return validateContactNum(this);">

									</label></td>



									<td class="alignLeft" style="padding-left: 20px;">No. & date of transfer certificate issued&nbsp;</td>

									<td><label> <input name="notcissued"type="text" class="textField" id="notcissued" size="36" value="<c:out default="" value="${student.notcissued}" />"
									><br><input	name="dateoftcissued" type="text" class="textField"  autocomplete="false"
									id="dateoftcissued" size="30" value="<fmt:formatDate type="date" value="${student.datetcissued}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)">
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
							<td></td>
							<td></td>
								<td align="center">
								<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="prevtab"
									style="font-weight: bold; color: #325F6D; font-size: 13px"
									href="#">Previous</a></td>
							</tr>
							
							<tr>
									<td><br /></td>
								</tr>
									
							<tr align="center">
									
									
									<td class="alignRight"> &nbsp;</td>
									<td></td>
									<td align="center">
									
										
										<button id="updatefour" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelfour">Cancel</button>

									</td>

								
								
									
								</tr>

								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>
								

						</div>
						</div>
							
							</table>
							
							</div>
							
							
							

				<div id="tabs-2">
				
						<br>
							<div align="center"><h3 style="text-decoration: underline;color: #eb6000;margin-left: 4px;">Father's / Guardian's Details</h3></div>
					<table style="width: auto;height: auto;" border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
									<td class="alignLeft">Father Name* &nbsp;</td>
									<td><label> 
											<input type="hidden" name="idparents" id="idparents" value="<c:out value="${parents.pid}" />" /></label> 
											<label> <input name="fathersname" type="text" class="myclass" id="fathersname" size="30"  required
														value="<c:out default="" value="${parents.fathersname}" />">
									</label></td>
									
										<td class="alignLeft"  style="padding-left: 20px;">Occupation
										&nbsp;</td>
									<td><label> 
									
									<input
									name="fatheroccupation" type="text" class="myclass" value="${parents.fatheroccupation}"
									id="fatheroccupation" size="30">
									
									</label></td>

								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignLeft">Qualification
										&nbsp;</td>
									<td ><label> 
									
										<input	name="fathersqualification" type="text" class="myclass" id="fathersqualification"
									size="30" value="<c:out default="" value="${parents.fathersqualification}" />">
									</label></td>
									
									
										<td class="alignLeft" style="padding-left: 20px;">Contact Number* &nbsp;</td>

									<td><label> 
									
									<input name="contactnumber"
									type="text" class="textField" id="contactnumber" size="30" required
									value="<c:out default="" value="${parents.contactnumber}" />"   maxlength="10" minlength="10">

									</label></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td  class="alignLeft" >Annual Income &nbsp;</td>

									<td ><label> 
											
											 <input name="parentsannualincome" type="text" class="myclass" id="parentsannualincome" size="30"
									value="<c:out default="" value="${parents.parentsannualincome}" />">
									
											
									</label></td>
									
									
									
									<td class="alignLeft" style="padding-left: 20px;">Emergency Contact No. &nbsp;</td>

									<td ><label> 
									<input name="emergencycontactno" type="text" class="myclass" id="emergencycontactno" size="30"
									value="<c:out default="" value="${parents.emergencycontactno}"/>" maxlength="10" minlength="10">

									</label></td>

								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
								<td class="alignLeft">Permanent Address &nbsp;</td>

								<td ><label> 
								
										<textarea
										name="permanentaddress" type="text" class="textbox"
										id="permanentaddress" rows="4" cols="31"
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>

								</label></td>


								<td  class="alignLeft" style="padding-left: 20px;">Temporary Address &nbsp;</td>
								<td ><label> 											
											<textarea
										name="temporaryaddress"
										value="<c:out default="" value="${parents.addresstemporary}" />"
										type="text" class="textbox" id="temporaryaddress" rows="4"
										cols="31">${parents.addresstemporary}</textarea>
										
								</label></td>
								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								
								
								<tr>
									
									<td class="alignLeft">Guardian's Name &<br>
										Address &nbsp;</td>
									<td ><label> 									
											
											<input name="guardian"
									type="text" class="myclass" id="guardian" size="30"
									value="<c:out default="" value="${student.guardiandetails}" />">
											
									</label></td>
									
									<td class="alignLeft" style="padding-left: 20px;">Notes &nbsp;</td>
									<td ><label> 
											<input name="remarksadditional"
									type="text" class="myclass" id="remarksadditional" size="36"
									value="<c:out default="" value="${parents.remarks}" />">
									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td></td>
									
									<td></td>
									<td align="left"><h3 style="text-decoration: underline;color: #eb6000">Mother's Details</h3><br /></td>
								</tr>
								
								
								<tr>

									<td  class="alignLeft">Mother Name* &nbsp;</td>
									<td><label> 
									
										 <input name="mothersname" type="text" class="myclass" id="mothersname" size="30" required
												value="<c:out default="" value="${parents.mothersname}" />"" >
									
									</label></td>
									
									
									<td  class="alignLeft" style="padding-left: 20px;">Occupation
										&nbsp;</td>
									<td ><label> 
												<input name="profession" type="text" class="myclass" id="profession" size="30" required
												value="<c:out default="" value="${parents.profession}" />"" >
									
									</label></td>


								</tr>
								
								
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td  class="alignLeft">Qualification&nbsp;</td>
									<td ><label> 
									
											<input name="mothersqualification" type="text" class="myclass" id="mothersqualification"
													size="30" value="<c:out default="" value="${parents.mothersqualification}" />">
													
									</label></td>

									<td  class="alignLeft" style="padding-left: 20px;">Contact Number
										&nbsp;</td>

									<td>
											<label> 
												<input	name="cocontactnumber" type="text" class="myclass" id="cocontactnumber" size="30"
													value="<c:out default="" value="${parents.cocontactnumber}" />" maxlength="10" minlength="10">
											</label>
									</td>

								</tr>
								

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						
	
								<tr align="center">
									<td> &nbsp;</td>
									<td> &nbsp;</td>
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
									
									
									<td> &nbsp;</td>
									<td> &nbsp;</td>

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
						</label>
						</td>
						</tr>
						</table>

						</div>

							<div id="tabs-5">
				
						<table style="width: auto;height: auto;" border="0" align="center">
								<tr>

										<td><br /></td>
									</tr>
									
						<tr>
									<td></td>
									<td></td>
									<td align="left"><h3 style="text-decoration: underline;color: #eb6000">Previous School Details</h3><br /></td>
								</tr>
									<tr>
							<td class="alignLeft">Previous School Name
								&nbsp;</td>
							<td><label> <input
									name="lastschool" type="text" class="myclass" id="lastschool" value="<c:out default="" value="${student.schoollastattended}"/>"
									
									size="30" >
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Address&nbsp;</td>
							<td ><label> <input
							
									name="lastschooladdress" type="text" class="myclass" value="<c:out default="" value="${student.lastschooladdress}"/>" id="lastschooladdress" size="30" >
									
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Total Marks&nbsp;</td>

							<td><label> 
									<input name="totalmarks" type="text" class="myclass" value="<c:out default="" value="${student.totalmarks}"/>" id="totalmarks" size="30">
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">GPA / Percentage(%)&nbsp;</td>

							<td><label>
									
									<input name="gpa" type="text" class="myclass" value="<c:out default="" value="${student.percentage}"/>" id="gpa" size="30">						
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

									<tr>
									
									
										<td class="alignLeft">Admission in Class &nbsp;</td>

							<td ><label>
							
									<select name="lastclass" id="lastclass" class="dropdown" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.stdlaststudied}</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Course &nbsp;</td>
							<td><label> <input name="lastcourse" type="text" value="<c:out default="" value="${student.lastcourse}"/>" class="myclass" id="lastcourse" size="30">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						

						<tr>
							<td class="alignLeft">First Language &nbsp;</td>

							<td><label> 
									
									<select name="lastfirstlanguage" class="dropdown"
									id="lastfirstlanguage" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.lastfirstlanguage}</option>
										<option>English</option>
										<option>Telugu</option>
										<option>Urdu</option>
										<option>Hindi</option>
								</select>
									
							</label></td>



							<td class="alignLeft" style="padding-left: 20px;">Second Language &nbsp;</td>
							<td><label> 
							
										<select name="lastsecondlanguage" class="dropdown"
									id="lastsecondlanguage" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.lastsecondlanguage}</option>
										<option>Urdu</option>
										<option>Telugu</option>
										<option>Hindi</option>
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



					<div>
						<table width="100%">
							<tr>

								<td><br /></td>
							</tr>

							<tr>

								<td align="center">
								<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="prevtab"
									style="font-weight: bold; color: #325F6D; font-size: 13px"
									href="#">Previous</a></td>
							</tr>

							<tr>

								<td><br /></td>
							</tr>

							<tr>
								<td align="center">


									<button id="updatefive" class="update"
										onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();"
										onfocus="validateNameContact();validateFatherName();">Update</button>

									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="cancelfive" class="cancel">Cancel</button>

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
						</table>

					</div>

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
								if(form1.checkValidity()) {
									form1.action = "Controller?process=StudentProcess&action=updateStudent";
									form1.submit();
								  }
							}
						</script>
</body>
</html>

