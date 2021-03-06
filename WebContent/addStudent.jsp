<%-- 
    Document   : add Student
    Created on : Jun 17, 2013, 4:17:40 PM
    Author     : Musaib
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
<title>Add Student</title>
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
.myclass {
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
</style>

<script type="text/javascript">
	function dropdown() {
		var listitem = document.getElementById("city");
		var listitemtext = listitem.options[listitem.selectedIndex].text;

		if (listitemtext == "Add New") {
			document.getElementById("city").style.display = "none";
			document.getElementById("addcity").style.display = '';
		}
	}

	

	function dropdownadmclass() {

		var classlistitem = document.getElementById("admclass");
		var classlistitemtext = classlistitem.options[classlistitem.selectedIndex].text;

		if (classlistitemtext == "Add New") {
			document.getElementById("admclass").style.display = "none";
			document.getElementById("admclassE").style.display = '';
			document.getElementById("addsecE").style.display = '';
		}

	}

	function issues() {

		var distlistitem = document.getElementById("subscriptionfor");
		var distlistitemtext = distlistitem.options[distlistitem.selectedIndex].text;

		if (distlistitemtext == "1 year") {
			document.getElementById("noofissues").value = "24";
		} else if (distlistitemtext == "2 years") {
			document.getElementById("noofissues").value = "48";
		} else if (distlistitemtext == "3 years") {
			document.getElementById("noofissues").value = "72";
		} else if (distlistitemtext == "5 years") {
			document.getElementById("noofissues").value = "120";
		} else if (distlistitemtext == "Life Time") {
			document.getElementById("noofissues").value = "240";
		}

	}

	function calculateIssues() {

		var totalissues = document.getElementById("noofissues").value;
		var fromissues = document.getElementById("fromkmissueno").value;

		var toissues = parseInt(totalissues, 10) + parseInt(fromissues, 10) - 1;
		document.getElementById("tokmissueno").value = toissues;

	}
</script>




<script type="text/javascript" src="js/datetimepicker_css.js"></script>

<script src="JavaScript/actb.js"></script>
<script src="JavaScript/common.js"></script>




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
		$("#datepicker1").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepicker1").datepicker("option", "showAnim", $(this).val());
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
	
	function validateFatherName() {

		if (document.getElementById("fathersname").value.length == 0)

		{
			document.getElementById("fathersname").style.background = 'red';
			alert("Enter The Father's Name");
		}

	}

	function validateNameAlpha(obj) {

		document.getElementById("name").style.background = 'white';

		reg = /[^a-z]/g;
		obj.value = obj.value.replace(reg, "");
	}

	function noofissues() {
		var issues = document.getElementById("noofissues");
		var issuestext = issues.options[issues.selectedIndex].text;

		if (issuestext == "1 year") {

		}

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
	function validateAdmissionNumber() {
		if (document.getElementById("admnno").value.length == 0)

		{
			document.getElementById("admnno").style.background = 'red';
			alert("Enter The Admission Number ");
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
		
		$("#save").button().click(function() {
			addStudent();

		});
		$("#savetwo").button().click(function() {
			addStudent();

		});
		$("#savetwo").button().click(function() {
			addStudent();

		});
		$("#savethree").button().click(function() {
			addStudent();

		});
		$("#savefour").button().click(function() {
			addStudent();

		});

		$("#cancel").button().click(function() {
			Cancel();

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
		 
		 $("#cocontactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
		 $("#noofdependents").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
	});


</script>


<script type="text/javascript">
	function check(value) {

		xmlHttp = GetXmlHttpObject()
		var url = "check.jsp";
		url = url + "?name=" + value;
		xmlHttp.onreadystatechange = stateChanged
		xmlHttp.open("GET", url, true)
		xmlHttp.send(null)
	}
	function stateChanged() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			var showdata = xmlHttp.responseText;
			document.getElementById("mydiv").innerHTML = showdata;
		}
	}
	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
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

<script type="text/javascript">
	function checkmobile(value) {

		xmlHttp = GetXmlHttpObject()
		var url = "mobilecheck.jsp";
		url = url + "?contactNO=" + value;
		xmlHttp.onreadystatechange = stateChangedmobile
		xmlHttp.open("GET", url, true)
		xmlHttp.send(null)
	}
	function stateChangedmobile() {

		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			var showdata = xmlHttp.responseText;
			document.getElementById("mydivmobile").innerHTML = showdata;
		}
	}
	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}

	function watermark(inputId, text) {
		var inputBox = document.getElementById(inputId);
		if (inputBox.value.length > 0) {
			if (inputBox.value == text)
				inputBox.value = '';
		} else
			inputBox.value = text;
	}
	function watermark2(inputId, text) {
		var inputBox = document.getElementById(inputId);
		if (inputBox.value.length > 0) {
			if (inputBox.value == text)
				inputBox.value = '';
		} else
			inputBox.value = text;
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
	<form id="form1" action="Controller?process=PersonalProcess&action=add"
		method="post"  enctype="multipart/form-data">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"dd/MM/yyyy");
		%>
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Student's Details</a></li>
					<li><a href="#fragment-2">Parent's Details</a></li>
					<li><a href="#fragment-3">Upload Photo</a></li>
					<li><a href="#fragment-4">Additional Details</a></li>
				</ul>



				<div id="fragment-1">
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


							<td width="20%" class="alignRight">Admission Number* &nbsp;</td>
							<td width="28%"><label> <input name="admnno"
									type="text" class="textField" id="admnno" size="36"
									onblur="validateAdmissionNumber();"
									onkeypress="return validateContactNum(this);">

							</label></td>


							<td width="20%" class="alignRight">Cumulative Record No. With date of opening&nbsp;
							</td>
							<td width="28%"><label> <input name="crecord"
									type="text" class="textField" id="crecord" size="36"
									><input
									name="dateofcr" type="text" class="textField"
									id="datepicker1" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)">
							</label></td>

						</tr>
						
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="30%" class="alignRight">Name* &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="name" type="text" class="myclass" id="name" size="36" style="text-transform:uppercase"
									onblur="validateName();" required> <!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td width="30%" class="alignRight">Gender &nbsp;</td>
							<td width="16%" height="30" class="alignLeft">&nbsp;Male<input
								type="checkbox" value="Male" name="gender" id="male"
								onclick="maleCheck();" />&nbsp; &nbsp;Female<input
								type="checkbox" value="Female" name="gender" id="female"
								onclick="femaleCheck()" />

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
							<td width="20%" class="alignRight">Date Of Birth &nbsp;</td>
							<td width="28%"><label> <input name="dateofbirth"
									type="text" class="textField" id="datepicker" size="36"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
							</label></td>

							<td width="30%" class="alignRight">Age &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="age" type="text" class="myclass" id="age" size="36"
									onblur="validateName();">
							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
				
				
						<tr>
							
							<td width="30%" class="alignRight">Place Of Birth, Tq, Dist.&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="place" type="text" class="myclass" id="place" size="36"
									>
							</label></td>
							
							<td width="30%" class="alignRight">No. & date of transfer certificate&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="tcno" type="text" class="myclass" id="tcno" size="36"
									>
									<input
									name="dateoftc" type="text" class="textField"
									id="dateoftc" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)">
							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td width="20%" class="alignRight">Date of admission&nbsp;
							</td>
							<td width="28%"><label><input
									name="dateofadmission" type="text" class="textField"
									id="dateofadmission" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)">
							</label></td>
							
							<td width="20%" class="alignRight">Subsequent progress of the student&nbsp;</td>
							<td width="28%"><label> <input name="progress"
									type="text" class="textField" id="progress" size="36"
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


							<td class="alignRight">Studying in Class&nbsp;</td>
							<td width="28%"> <label> <select name="addclass" id="addclass"
									style="width: 120px">
										<option selected>Class</option>
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
									<select name="addsec" id="addsec"
									style=" width: 120px">
										<option selected>Sec</option>
										<option>A</option>
										<option>B</option>
										<option>C</option>
										<option>D</option>
										<option>E</option>
										<option>F</option>
										<option>G</option>

								</select>
							</label></td>

							<td width="16%" class="alignRight">Admitted in Class &nbsp;
							</td>

							<td width="28%">
							 <label> <select name="admclassE" id="admclassE"
									style="width: 120px">
										<option selected>Class</option>
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

									<select name="admsecE" id="admsecE"
									style="width: 100px">
										<option selected>Sec</option>
										<option>A</option>
										<option>B</option>
										<option>C</option>
										<option>D</option>
										<option>E</option>
										<option>F</option>
										<option>G</option>

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


							<td width="16%" class="alignRight">Last Class Studied &nbsp;</td>

							<td><label> <select name="lastclass" id="lastclass"
									style="width: 240px" onchange="dropdown()">
										<option selected></option>
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



							<td width="20%" class="alignRight">Last School Attended
								&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="lastschool" type="text" class="myclass" id="lastschool"
									size="36" onblur="validateName();">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<%-- <tr>


							<td width="20%" class="alignRight">Admission Number* &nbsp;</td>
							<td width="28%"><label> <input name="admnno"
									type="text" class="textField" id="admnno" size="36"
									onblur="validateAdmissionNumber();"
									onkeypress="return validateContactNum(this);">

							</label></td>


							<td width="20%" class="alignRight">Date Of Admission &nbsp;
							</td>
							<td width="28%"><label> <input
									name="dateofadmission" type="text" class="textField"
									id="datepicker1" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)">
							</label></td>

						</tr>


						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr> --%>




							<td width="16%" class="alignRight">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 240px">
										<option selected></option>
										<option>A +ve</option>
										<option>B +ve</option>
										<option>AB +ve</option>
										<option>O +ve</option>
										<option>A -ve</option>
										<option>B -ve</option>
										<option>AB -ve</option>
										<option>O -ve</option>
										
										
								</select>

							</label></td>


							<td width="16%" class="alignRight">Nationality &nbsp;</td>

							<td><label> <select name="nationality"
									id="nationality" style="width: 240px" onchange="dropdown()">
										<option selected></option>
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

							<td width="28%"><!-- <label> <input name="religion"
									type="text" class="textField" id="religion" size="36"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label> -->
							
							<label> <select name="religion" onblur="validateNameContact();"
									id="religion" style="width: 240px" onkeypress="return validateContactNum(this);">
										<option selected></option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
								</select>

							</label>
							
							</td>



							<td width="20%" class="alignRight">Caste &nbsp;</td>
							<td width="28%"><label> <input name="caste"
									type="text" class="textField" id="caste" size="36"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

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

							<td width="28%"><!-- <label> <input name="motherT"
									type="text" class="textField" id="motherT" size="36"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label> -->
							<label>
							<select name="motherT" onblur="validateNameContact();"
									id="motherT" style="width: 240px" onkeypress="return validateContactNum(this);">
										<option selected></option>
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
									type="text" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>"
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
							<td width="20%" class="alignRight">Remarks &nbsp;</td>
							<td width="28%"><label> <input name="remarks"
									type="text" class="textField" id="remarks" size="36"
									onclick="validateNameContact();">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>







						<tr>
							<td><br /></td>
						</tr>


						<div>
							<table width="100%">
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
										
										
										<button id="save" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancel">Cancel</button>

									</td>


								</tr>
							</table>

						</div>



<div id="fragment-3">
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
										
										
										<button id="savethree" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Save</button>

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


<div id="fragment-4">
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
									<td width="30%" class="alignRight">Class on leaving&nbsp;</td>
									<td width="12%" align="left"><label> <input
											name="classonleaving" type="text" class="myclass" id="classonleaving" style="text-transform:uppercase"
											size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td width="30%" class="alignRight">Date of leaving the school&nbsp;</td>
									<td width="12%" align="left"><label> <input
									name="dateofleaving" type="text" class="textField"
									id="dateofleaving" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)"><!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td width="16%" class="alignRight">Reason for leaving &nbsp;</td>

									<td width="28%"><label> <input name="reasonforleaving"
											type="text" class="textField" id="reasonforleaving" size="36"
											
											onkeypress="return validateContactNum(this);">

									</label></td>



									<td width="16%" class="alignRight">No. & date of transfer certificate issued&nbsp;</td>

									<td width="28%"><label> <input name="notcissued"
									type="text" class="textField" id="notcissued" size="36"
									><input
									name="dateoftcissued" type="text" class="textField"
									id="dateoftcissued" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)">
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
									<td><br /></td>
								</tr>
							<tr>
									<td><br /></td>
								</tr>	
							<tr align="center">
									
									
									<td width="20%" class="alignRight"> &nbsp;</td>

									<td align="center">
									
										
										<button id="savefour" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Save</button>

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


						<div id="fragment-2">
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
									<td width="30%" class="alignRight">Father's Name & Occupation* &nbsp;</td>
									<td width="12%" align="left"><label> <input
											name="fathersname" type="text" class="myclass" id="fathersname" style="text-transform:uppercase"
											size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td width="30%" class="alignRight">Mother's Name & Occupation*&nbsp;</td>
									<td width="12%" align="left"><label> <input
											name="mothersname" type="text" class="myclass" id="name" style="text-transform:uppercase"
											size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td width="16%" class="alignRight">Guardian's Name & Address &nbsp;</td>
									<td width="28%"><label> <input name="guardian"
											type="text" class="textField" id="guardian" size="36"
											onclick="validateNameContact();">
									</label></td>



									<td width="16%" class="alignRight">Annual Income &nbsp;</td>

									<td width="28%"><label> <input name="parentsannualincome"
											type="text" class="textField" id="parentsannualincome" size="36"
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

									<td width="16%" class="alignRight">Contact Number &nbsp;</td>

									<td width="28%"><label> <input name="contactnumber"
											type="text" class="textField" id="contactnumber" size="36"
											maxlength="10" minlength="10"
											>

									</label></td>


										
									<td width="16%" class="alignRight">CO-Contact Number &nbsp;</td>

									<td width="28%"><label> <input name="cocontactnumber"
											type="text" class="textField" id="cocontactnumber" size="36"
											
											maxlength="10" minlength="10">

									</label></td>
								</tr>
								
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								
								
								<tr>
									
									<td width="16%" class="alignRight">Email &nbsp;</td>

									<td width="28%"><label> <input name="email"
											type="email" class="textField" id="email" size="36"
											onblur="validateNameContact();"
											>

									</label></td>
									
									<td width="20%" class="alignRight">Number Of Dependents &nbsp;</td>

									<td width="28%"><label> <input name="noofdependents"
											type="text" class="textField" id="noofdependents" size="36"
											onblur="validateNameContact();"
											>

									</label></td>
									
									
									</tr>
								
								<tr>
								
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>	
									
									<td width="16%" class="alignRight">Permanent Address &nbsp;</td>

									<td width="28%"><label> <textarea  name="permanentaddress"
											type="text" class="textField" id="permanentaddress" rows="4" cols="35"
											
											onkeypress="return validateContactNum(this);"></textarea>

									</label></td>
								
								
									<td width="20%" class="alignRight">Temporary Address &nbsp;</td>
									<td width="28%"><label> <textarea name="temporaryaddress"
											type="text" class="textField" id="temporaryaddress" rows="4" cols="35"
											></textarea>
									</label></td>
								</tr>

							

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>
									
									<td width="16%" class="alignRight">Notes &nbsp;</td>
									<td width="28%"><label> <input name="remarks"
											type="text" class="textField" id="remarks" size="36"
											onclick="validateNameContact();">
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
									
										
										<button id="savetwo" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="canceltwo">Cancel</button>

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
							</table>
							</div>
							
							
							</div>
							</div>
							
													
						
						

						</form>
						<script type="text/javascript">
							function addStudent() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=StudentProcess&action=AddStudent";
								form1.submit();
							}

							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=StudentProcess&action=viewAll";
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


