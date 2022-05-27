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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML5>

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

<script src="JavaScript/actb.js"></script>
<script src="JavaScript/common.js"></script>




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
		$("#datepicker1").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
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
		 
		 $("#sts").keypress(function (e) {
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

<script type="text/javascript">
	
    function numberWithCommas(annualincome) {
    	var x=annualincome.value;
    	x = x.replace (/,/g, "");
    	
    	var lastThree = x.substring(x.length-3);
    	var otherNumbers = x.substring(0,x.length-3);
    	if(otherNumbers != '')
    	    lastThree = ',' + lastThree;
    	var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
    	annualincome.value = res;
    }
</script>
</head>
<%
	//allow access only if session exists
	String user = null;
	if (session.getAttribute("userAuth") == null) {
		response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
	} else
		user = (String) session.getAttribute("userAuth");
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user"))
				userName = cookie.getValue();
			if (cookie.getName().equals("JSESSIONID"))
				sessionID = cookie.getValue();
		}
	}
%>
<body>
	<form id="form1" 
		method="post" enctype="multipart/form-data">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Student Details</a></li>
					<li><a href="#fragment-2">Parent Details</a></li>
					<li><a href="#fragment-3">Upload Photo</a></li>
					<li><a href="#fragment-5">Previous School Details</a></li>
					<!-- <li><a href="#fragment-4">Additional Details</a></li> -->
					<!-- <li><a href="#fragment-6">Bank Details</a></li> -->
				</ul>



				<div id="fragment-1">
				
						<br>
							<div align="center"><h3 style="text-decoration: underline;color: #eb6000">Student Details</h3></div>				
					<table style="width: auto;height: auto;" align="center"   border="0" id="table1">
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignLeft">Admission Number* &nbsp;</td>
							<td><label> <input name="admnno" required
									type="text" class="myclass" id="admnno" size="30"
									>

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">Registration No. &nbsp;</td>

									<td ><label> <input
											name="registrationnumber" type="text" class="myclass" size="30" readonly="readonly" value="${registrationno}"
											id="registrationnumber" size="30">

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
							<td align="left"><label> <input
									name="name" type="text" class="myclass fontsize" id="name" size="30" required
									required>
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Gender &nbsp;</td>
							<td class="alignLeft">&nbsp;Male<input
								type="checkbox" value="Male" name="gender" id="yes:male"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;Female<input
								type="checkbox" value="Female" name="gender" id="no:male"
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
						<tr>
							<td class="alignLeft">Date Of Birth &nbsp;</td>
							<td ><label> <input name="dateofbirth"
									type="text" class="myclass" id="datepicker" size="30" autocomplete="false"
									
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Age &nbsp;</td>
							<td><label> <input
									name="age" type="text" class="myclass" id="age" size="30"
									
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

							<td class="alignLeft">Place Of Birth&nbsp;</td>
							<td><label> <input
							
									name="place" type="text" class="myclass" id="place" size="30">
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Date of admission&nbsp;</td>
							<td ><label><input name="dateofadmission" autocomplete="false"
									type="text" class="myclass" id="dateofadmission" size="30"
									
									data-validate="validate(required)"> </label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>


							<td class="alignLeft">Class&nbsp;</td>
							<td><label> <select name="addclass" class="dropdown"
									id="addclass" style="width: 186px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label> <label> <select name="addsec" id="addsec" class="dropdown" style="width: 70px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>

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

							<td><label> <select name="bloodgroup"  class="dropdown"
									id="bloodgroup" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
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

							<%-- <td width="16%" class="alignRight">Admitted in Class &nbsp;
							</td>

							<td width="28%"><label> <select name="admclassE"
									id="admclassE" style="width: 128px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label> <label> <select name="admsecE" id="admsecE"
									style="width: 128px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.section != '')}">
												<option value="${classdetailslist.section}">
													<c:out value="${classdetailslist.section}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label></td> --%>

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
							 <label> <select name="motherT" class="dropdown"
									 id="motherT"
									style="width: 258px;border-radius: 4px;background: white;height: 28px;">
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
							
							
							<td class="alignLeft" style="padding-left: 20px;border-radius: 4px;background: white;height: 28px;">Religion &nbsp;</td>

							<td>
								<label> <select name="religion"
									id="religion" class="dropdown"
									style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
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
							<td ><label> <input name="studentscaste"
							
									type="text" class="myclass" id="studentscaste" size="30">

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Nationality &nbsp;</td>

							<td><label> <select name="nationality" class="dropdown"
									id="nationality" style="width: 258px;border-radius: 4px;background: white;height: 28px;" onchange="dropdown()">
										<option selected>Indian</option>
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
							<td><label> <input
									name="studentscastecertno" type="text" class="myclass"
									
									id="studentscastecertno" size="30">

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Second Language&nbsp;</td>
							<td><label> <select name="secondlanguage" class="dropdown"
									id="secondlanguage" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
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
							<td ><label> <input name="createddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepickerCD" size="30"
									s
									data-validate="validate(required)">
							</label></td>
							
							<td  class="alignLeft" style="padding-left: 20px;">Admission Year:&nbsp;&nbsp;&nbsp;&nbsp; 
                                        <label> <select name="yearofadmission" id="yearofadmission" required
									style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${currentAcademicYear}</option>
										<option>2025/26</option>
										<option>2024/25</option>
										<option>2023/24</option>
										<option>2022/23</option>
										<option>2021/22</option>
										<option>2020/21</option>
										<option>2019/20</option>
										<option>2018/19</option>
										<option>2017/18</option>
										<option>2016/17</option>
										<option>2015/16</option>
										<option>2014/15</option>
										<option>2013/14</option>
										<option>2012/13</option>
										<option>2011/12</option>
										<option>2010/11</option>
										<option>2009/10</option>
										<option>2008/09</option>
										<option>2007/08</option>
										<option>2006/07</option>
										<option>2005/06</option>
										<option>2004/05</option>
										<option>2003/04</option>
										<option>2002/03</option>
										<option>2001/02</option>
										<option>2000/01</option>										
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
							<td><br /></td>
						</tr>


						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>

								<tr>

									<td align="center"><a class="nexttab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Next</a></td>
								</tr>

								<tr>

									<td><br /></td>
								</tr>

								<tr>
									<td align="center">


										<button id="savestudents" class="save" name="savestudent">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancel" class="cancel">Cancel</button>

									</td>


								</tr>
							</table>

						</div>



						<div id="fragment-3">
							<table width="100%" border="0" align="center">
								<tr>
									<td><br /> <input type="file" name="fileToUpload"
										id="fileToUpload" accept="image/*"></td>
								</tr>


							</table>



							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>

									<tr>

										<td align="center"><a class="nexttab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Next</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
											class="prevtab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Previous</a></td>
									</tr>

									<tr>

										<td><br /></td>
									</tr>

									<tr>
										<td align="center">


											<button id="savethree" class="save" name="savestudent">Save</button>

											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="cancelthree" class="cancel">Cancel</button>

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


						<%-- <div id="fragment-4">
							<table width="100%" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignRight">Class on leaving&nbsp;</td>
									<td align="left">
									<label> <select name="classonleaving"
									id="classonleaving" style="width: 260px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
										</select>
									</label>
									<td class="alignRight">Date of leaving the
										school&nbsp;</td>
									<td align="left"><label> <input
											name="dateofleaving" type="text" class="myclass" autocomplete="false"
											id="dateofleaving" size="40"
											data-validate="validate(required)"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignRight">Reason for leaving
										&nbsp;</td>

									<td><label> <input
											name="reasonforleaving" type="text" class="myclass"
											
											id="reasonforleaving" size="30"
											>

									</label></td>



									<td class="alignRight">No. & date of transfer
										certificate issued&nbsp;</td>

									<td ><label> <input name="notcissued"
									style="" autocomplete="false"
											type="text" class="myclass" id="notcissued" size="30" placeholder="No. of Transfer Certificate">
									</label></td>

								</tr>

								<tr>
								<td></td>
								<td></td>
								<td></td>
									<td><label>
											<input
											name="dateoftcissued" type="text" class="myclass" autocomplete="false"
											id="dateoftcissued" size="30" placeholder="Date of Transfer Certificate"
											style=""
											data-validate="validate(required)">
									</label></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr align="center">

									<td width="20%" class="alignRight">&nbsp;</td>

									<td align="center"><a class="nexttab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Next</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
										class="prevtab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Previous</a></td>
								</tr>
								
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>

								<tr align="center">


									<td width="20%" class="alignRight">&nbsp;</td>

									<td align="center">


										<button id="savefour" class="save" name="savestudent">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelfour" class="cancel">Cancel</button>

									</td>




								</tr>

								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>

							</table>

						</div> --%>


						<div id="fragment-2">
							<br>
							<div align="center"><h3 style="text-decoration: underline;color: #eb6000;margin-left: 4px;">Father's / Guardian's Details</h3></div>
							
							<table style="width: auto;height: auto;" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
															

								<tr>
									<td class="alignLeft">Father Name* &nbsp;</td>
									<td><label> <input
											name="fathersname" type="text" class="myclass" required
											
											id="fathersname" size="30"
											required> <!-- onkeyup="check(this.value);"  -->
									</label></td>
									
										<td class="alignLeft"  style="padding-left: 20px;">Occupation
										&nbsp;</td>
									<td><label> <input
											name="fatheroccupation" type="text" class="myclass"
											
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
									<td ><label> <input
											name="fathersqualification" type="text" class="myclass"
											id="fathersqualification" 
											
											size="30"> <!-- onkeyup="check(this.value);"  -->
									</label></td>
									
									
										<td class="alignLeft" style="padding-left: 20px;">Contact Number* &nbsp;</td>

									<td><label> <input
											name="contactnumber" type="text" class="myclass" required
											
											id="contactnumber" size="30" maxlength="10" minlength="10">

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

									<td ><label> <input
											name="parentsannualincome" type="text" class="myclass"
											
											id="parentsannualincome" size="30"
											onkeyup="numberWithCommas(this);">

									</label></td>
									
									
									
									<td class="alignLeft" style="padding-left: 20px;">Emergency Contact No. &nbsp;</td>

									<td ><label> <input name="emergencycontactno"
											type="text" class="myclass" id="emergencycontactno" size="30"
											maxlength="10" minlength="10"
											>

									</label></td>

								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<!-- <tr>


									<td  class="alignLeft" style="padding-left: 20px;">Number Of Dependents
										&nbsp;</td>

									<td ><label> <input
											name="noofdependents" type="text" class="myclass"
											
											id="noofdependents" size="30" >

									</label></td>


								</tr>

								<tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr> -->

								<tr>
								<td class="alignLeft">Permanent Address &nbsp;</td>

								<td ><label> <textarea
											name="permanentaddress" type="text"  class="textbox"
											id="permanentaddress" rows="4" cols="31"
											></textarea>

								</label></td>


								<td  class="alignLeft" style="padding-left: 20px;">Temporary Address &nbsp;</td>
								<td ><label> <textarea
											name="temporaryaddress" type="text" class="textbox"
											id="temporaryaddress" rows="4" cols="31"></textarea>
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
									<td ><label> <input name="guardian"
											type="text" class="myclass" id="guardian" size="30"
											
											>
									</label></td>
									
									<td class="alignLeft" style="padding-left: 20px;">Notes &nbsp;</td>
									<td ><label> <input name="remarksadditional"
											type="text" class="myclass" id="remarksadditional" size="30"
											
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
									<td></td>
									
									<td></td>
									<td align="left"><h3 style="text-decoration: underline;color: #eb6000">Mother's Details</h3><br /></td>
								</tr>
								
								
								<tr>

									<td  class="alignLeft">Mother Name* &nbsp;</td>
									<td><label> <input
											name="mothersname" type="text" class="myclass" id="name" required
											
											size="30"> <!-- onkeyup="check(this.value);"  -->
									</label></td>
									
									
									<td  class="alignLeft" style="padding-left: 20px;">Occupation
										&nbsp;</td>
									<td ><label> <input
											name="profession" type="text" class="myclass"
											
											id="profession" size="30">
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
									<td ><label> <input
											name="mothersqualification" type="text" class="myclass"
											id="mothersqualification"
											
											size="30"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td  class="alignLeft" style="padding-left: 20px;">Contact Number
										&nbsp;</td>

									<td><label> <input
											name="cocontactnumber" type="text" class="myclass"
											
											id="cocontactnumber" size="30" maxlength="10" minlength="10">

									</label></td>

								</tr>
								
								
								
								
								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td><br /></td>
									<td><br /></td>
								</tr>

								<tr align="center">
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td align="center"><a class="nexttab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Next</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
										class="prevtab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Previous</a></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>
								<tr align="center">
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td align="center">
										<button id="savetwo"  class="save" name="savestudent">Save</button>

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
							</table>
						</div>
						
						<div id="fragment-5">

							<div>
								<table style="width: auto;height: auto;" border="0" align="center" id="table2">
<tr>
							<td><br /></td>
						</tr>
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
									name="lastschool" type="text" class="myclass" id="lastschool"
									
									size="30" >
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Address&nbsp;</td>
							<td ><label> <input
							
									name="lastschooladdress" type="text" class="myclass" id="lastschooladdress" size="30">
									
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
							<input name="totalmarks" type="text" class="myclass" id="totalmarks" size="30">
							
							
<!-- 							<select name="mediumofinstruction"
									id="mediumofinstruction" style="width: 258px">
										<option selected></option>
										<option>Kannada</option>
										<option>Hindi</option>
										<option>Urdu</option>
										<option>English</option>
										<option>Marathi</option>
										<option>Tamil</option>
										<option>Telgu</option>
								</select> -->

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">GPA / Percentage(%)&nbsp;</td>

							<td><label>
									
									<input name="gpa" type="text" class="myclass" id="gpa" size="30">						
							
							<!--  <select name="previousschooltype"
									id="previousschooltype" style="width: 258px">
										<option selected></option>
										<option>Government</option>
										<option>Private Aided</option>
										<option>Local Bodies</option>
										<option>Private Unaided School</option>
								</select>
 -->
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

							<td ><label> <select name="lastclass" id="lastclass" class="dropdown"
									style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<option value="${classdetailslist.classdetails}">
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Course &nbsp;</td>
							<td><label> <input name="lastcourse" type="text" class="myclass" id="lastcourse" size="30"
									
									>
							</label></td>
							
							<!-- 		<td class="alignLeft">Date of Transfer Certificate&nbsp;</td>
							<td ><label >
							<input name="dateoftc" type="text" class="myclass"
									id="dateoftc" size="30" autocomplete="false"
									
									data-validate="validate(required)"></label></td> -->

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
										<option selected></option>
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
										<option selected></option>
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
						
						
								<tr align="center">
									<td >&nbsp;</td>
									<td >&nbsp;</td>
									<td align="center"><a class="nexttab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Next</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
										class="prevtab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Previous</a></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>
						

									<tr>
										<td></td>
										<td></td>
										<td align="center">


											<button id="savefive" class="save" name="savestudent">Save</button>

											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="cancelthree" class="cancel">Cancel</button>

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
						
						<!-- <div id="fragment-6">

							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td class="alignRight">Bank Name &nbsp;</td>
							<td><label> <input name="bankname" type="text" class="myclass" id="bankname" size="30"
									
									>
							</label></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						
						<tr>
							<td class="alignRight">Bank IFSC Code&nbsp;</td>
							<td><label> <input name="bankifsc" type="text" class="myclass" id="bankifsc" size="30"
									
									>
							</label></td>
						</tr>
								<tr>
										<td><br /></td>
								</tr>
						<tr>
							<td class="alignRight">Account No. &nbsp;</td>
							<td><label> <input name="accno" type="text" class="myclass" id="accno" size="30"
									
									>
							</label></td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr align="left">

								<tr>
								<td></td>
								<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 <a
										class="prevtab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Previous</a></td>
								</tr>
								<tr><td><br></td></tr>
									<tr>
										<td></td>
										<td align="left">


											<button id="savesix" class="save" name="savestudent">Save</button>

											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="cancelthree" class="cancel">Cancel</button>

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

						</div> -->
					</table>
				</div>


			</div>
		</div>





	</form>
	<script type="text/javascript">
							function addStudent() {
								var form1 = document.getElementById("form1");
								if(form1.checkValidity()) {
									form1.savestudent.disabled = true;
									form1.action = "Controller?process=StudentProcess&action=AddStudent";
									form1.submit();
								  }
							}

							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=StudentProcess&action=viewAll";
								form1.submit();
							}

							
						</script>
</body>
</html>


