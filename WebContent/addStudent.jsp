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
<script src="js/datePicker/jquery-1.7.1.js"></script>
<script src="js/datePicker/ui/jquery.ui.core.js"></script>
<script src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="js/datePicker/ui/jquery.ui.tabs.js"></script>

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
</script>




<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script>
	$(function() {
		$("#dateofadmission").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+10"
		});
		$("#anim").change(
				function() {
					$("#dateofadmission").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd',
			yearRange: "-50:+10"
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
			document.getElementById("name").style.background = 'white';
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

</script>


<script>
	function validateWhileSave() {
		if (document.getElementById("name").value.length == 0)

		{
			document.getElementById("name").style.background = 'white';
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

		$(".save").button().click(function() {
			addStudent();

		});

		$(".cancel").button().click(function() {
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
		
		
		 
		 $("#contactnumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
		
	});


</script>


<script type="text/javascript">

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

<script type="text/javascript">

	   function getAdmNo() {

			var examLevel=document.getElementById('examlevel').value;
			var centerCode = document.getElementById('centercode').value;
			var admissionYear = document.getElementById('admissionyear').value;
			
				var xmlHttp = new XMLHttpRequest();
	            
	        
			xmlHttp.onreadystatechange = function(){

				if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
					var admNoDis =  xmlHttp.responseText;
					var splitAdmDist = admNoDis.split("$");
					var admNo = splitAdmDist[0];
					var distName = splitAdmDist[1];
					document.getElementById("admnno").value = admNo;
					document.getElementById("districtcode").value = distName;
				}
			};
			xmlHttp.open("GET", "AjaxController?process=LevelProcess&action=getAdmissionNo&examlevel="+examLevel+"&centercode="+centerCode+"&admissionyear="+admissionYear,true);
			xmlHttp.send(null);
		}
	   
	   function getAdmittedIn(){
		   var exLevel =document.getElementById("examlevel").value;
			document.getElementById("admittedin").value = exLevel;
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
<body onload="getAdmNo();getAdmittedIn()">
	<form id="form1" 
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
					<li><a href="#fragment-2">Additional Details</a></li>
					<li><a href="#fragment-3">Upload Photo</a></li>
				</ul>



				<div id="fragment-1">
					<table width="100%" border="0" align="left" id="table1">
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
							<td width="30%" class="alignRight">Name* &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="name" type="text" class="myclass" id="name" size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									required>
							</label></td>
								<td width="30%" class="alignRight">Father's Name&nbsp;</td>
									<td width="12%" align="left"><label> <input
											name="fathersname" type="text" class="myclass" id="fathersname" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
									<td width="30%" class="alignRight">Husband's Name&nbsp;</td>
									
									<td width="12%" align="left"><label> <input
											name="mothersname" type="text" class="myclass" id="mothersname" size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											 onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>
									
									
									<td width="16%" class="alignRight">Guardian's Name&nbsp;</td>
									<td width="28%"><label> <input name="guardian"
											type="text" class="textField" id="guardian" size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											onclick="validateNameContact();">
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								

								<tr>
								<td width="30%" class="alignRight">Gender &nbsp;</td>
								<td width="16%" height="30" class="alignLeft">&nbsp;Male<input 
								type="checkbox" value="Male" name="gender" id="male" ${genderadd == 'Male' ? 'checked' : ''} 
								onclick="maleCheck();" />&nbsp; &nbsp;Female<input
								type="checkbox" value="Female" name="gender" id="female" ${genderadd == 'Female' ? 'checked' : ''} 
								onclick="femaleCheck()" />

							</td>
									
										
							<td width="16%" class="alignRight">Date of admission&nbsp;
							</td>
							<td width="28%"><label><input
									name="dateofadmission" type="text" class="textField" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									id="dateofadmission" size="36" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" data-validate="validate(required)">
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

							<td width="30%" class="alignRight">Age &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="age" type="text" class="myclass" id="age" size="36" value="${ageadd}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									onblur="validateName();">
							</label></td>


							<td width="16%" class="alignRight">Qualification&nbsp;</td>

							<td width="28%"><label>
							
							<select name="qualification" id="qualification" 
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
										<option selected style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">${qualificationadd}</option>
										<c:forEach items="${qualificationlist}" var="qualificationlist">
											<option value="${qualificationlist.qualification}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
												<c:out value="${qualificationlist.qualification}" />
											</option>
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
						
							<td width="16%" class="alignRight">Examination Level*&nbsp;</td>
							<td width="28%">
							 <label> 
							 <select name="examlevel" id="examlevel" onchange="getAdmittedIn();getAdmNo();"
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required>
										<option selected style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">${examleveladd}</option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
							
							<td width="16%" class="alignRight">Admitted In&nbsp;</td>
							<td width="28%">
							 <label> 
							 <select name="admittedin" id="admittedin"
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required>
										<option selected></option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
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


							<td width="16%" class="alignRight">Center Code*&nbsp;</td>

							<td width="28%"><label> 
							<select name="centercode" id="centercode" onchange="getAdmNo();"
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required>
										<option selected>${centercodeadd}</option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>
							</label> 
							</td>



							<td width="20%" class="alignRight">District Code &nbsp;</td>
							<td width="28%"><label> 
									<input type="text" name="districtcode" id="districtcode" size="36"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"  required>
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						
						<tr>

						<td width="16%" class="alignRight">Language Opted* &nbsp;</td>
							<td><label>
								<select name="languageopted" id="languageopted"
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required>
										<option selected style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">${languageadd}</option>
										<c:forEach items="${languageslist}" var="languageslist">
											<option value="${languageslist.language}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
												<c:out value="${languageslist.language}" />
											</option>
										</c:forEach>
								</select>
							</label></td>

							<td width="20%" class="alignRight">Created Date &nbsp;</td>
							<td width="28%"><label> <input name="createddate" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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

							<td width="20%" class="alignRight">Admission Number&nbsp;</td>
							<td width="28%" id="getAdmNumber"><label><input name="admnno" type="text" class="textField" id="admnno" size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required></label></td>
							
							<td width="16%" class="alignRight">Religion&nbsp;</td>
							<td width="28%">
							 <label> 
							 <select name="religion" id="religion" 
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required>
										<option selected value="ISLAM" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">Islam</option>
											<option value="OTHERS" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">Others</option>
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
							<td width="20%" class="alignRight">Admission Year&nbsp;</td>
							<td width="28%"><label>
										<select name="admissionyear" id="admissionyear" onchange="getAdmNo();"
									style="width: 280px;text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
											<option selected style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">${admissionyear}</option>
											<option value="2000/01" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2000/01</option>
											<option value="2001/02" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2001/02</option>
											<option value="2002/03" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2002/03</option>
											<option value="2003/04" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2003/04</option>
											<option value="2004/05" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2004/05</option>
											<option value="2005/06" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2005/06</option>
											<option value="2006/07" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2006/07</option>
											<option value="2007/08" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2007/08</option>
											<option value="2008/09" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2008/09</option>
											<option value="2009/10" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2009/10</option>
											<option value="2010/11" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2010/11</option>
											<option value="2011/12" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2011/12</option>
											<option value="2012/13" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2012/13</option>
											<option value="2013/14" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2013/14</option>
											<option value="2014/15" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2014/15</option>
											<option value="2015/16" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2015/16</option>
											<option value="2016/17" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2016/17</option>
											<option value="2017/18" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2017/18</option>
											<option value="2018/19" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2018/19</option>
											<option value="2019/20" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2019/20</option>
											<option value="2020/21" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2020/21</option>
											<option value="2021/22" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2021/22</option>
											<option value="2022/23" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">2022/23</option>
											
								</select>	
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
										<button id="saveone" class="save">Save</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelone" class="cancel">Cancel</button>

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
										
										
										<button id="savethree" class="save">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelthree" class="cancel">Cancel</button>

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


						<div id="fragment-2">
							<table width="100%" border="0" align="center" id="table1">
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
									
									<td width="16%" class="alignRight">Email &nbsp;</td>

									<td width="28%"><label> <input name="email"
											type="email" class="textField" id="email" size="36"
											
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


								<tr>
									<td width="16%" class="alignRight">Education &nbsp;</td>

									<td width="28%"><label> <input name="educationqualification"
											type="text" class="textField" id="educationqualification" size="36"
											
											>
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
									
										
										<button id="savetwo" class="save">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="canceltwo" class="cancel">Cancel</button>

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
								  if(form1.checkValidity()) {
									  form1.action = "Controller?process=StudentProcess&action=AddStudent";
										form1.submit();
								  }
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


