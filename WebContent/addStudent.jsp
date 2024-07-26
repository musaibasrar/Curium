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


.alignLeft {
	font-family: Tahoma;
	font-size: 13px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
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
	
	$(function() {
		$("#validfrom").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-2:+2"
		});
		$("#anim").change(
				function() {
					$("#validfrom").datepicker("option", "showAnim",$(this).val());
				});
	});
	
	$(function() {
		$("#validtill").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-2:+2"
		});
		$("#anim").change(
				function() {
					$("#validtill").datepicker("option", "showAnim", $(this).val());
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
	 
	 function calculateValidity(id){
		 
		 var month = document.getElementById(id).value;
		 var startDate = document.getElementById('dateofadmission').value;
		 document.getElementById('validfrom').value = startDate;
		 var dateSplit = startDate.split('/');
		 var month = parseInt(dateSplit[1])+parseInt(month);
		 var endDate = dateSplit[0]+'/'+month+'/'+dateSplit[2];
		 document.getElementById('validtill').value = endDate;
		 
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
					<li><a href="#fragment-1">Student's Details</a></li>
					<!-- <li><a href="#fragment-2">Parent's Details</a></li> -->
					<li><a href="#fragment-3">Upload Photo</a></li>
					<!-- <li><a href="#fragment-5">Previous School Details</a></li> -->
					<!-- <li><a href="#fragment-4">Additional Details</a></li> -->
					<!-- <li><a href="#fragment-6">Bank Details</a></li> -->
				</ul>



				<div id="fragment-1">
					<table  style="width: auto;height: auto;" align="center"   border="0" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td  class="alignLeft">Admission Number &nbsp;</td>
							<td ><label> <input name="admnno" 
									type="text" class="myclass" id="admnno" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">College&nbsp;</td>
							<td ><label> 
									<select name="collegename"	id="collegename" style="width: 256px;">
										<option selected></option>
										<option value="shaheen_sn">Shaheen School</option>
										<option value="maulanaazad_ma">Maulana Azad</option>
										<option value="allamaiqbal_ai">Allama Iqbal</option>
										<option value="neetrepeaters_nr">NEET Academy</option>
										<option value="upsc_upsc">UPSC</option>
										<option value="degree_dg">Shaheen Degree college</option>
										<option value="degree_dgw">Shaheen Women's Degree college</option>
										<option value="degree_dgw">Carrier Master</option>
									</select>
							</label> </td>
							
							<!-- <td width="16%" class="alignRight">STS Number &nbsp;</td>

									<td width="28%"><label> <input
											name="sts" type="text" class="myclass" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="sts" size="30">

									</label></td> -->
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
									name="name" type="text" class="myclass" id="name" size="30" required
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									required>
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Gender &nbsp;</td>
							<td height="30" class="alignLeft">&nbsp;Male<input
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

						<!-- <tr>
							<td width="20%" class="alignRight">Date Of Birth &nbsp;</td>
							<td width="28%"><label> <input name="dateofbirth"
									type="text" class="myclass" id="datepicker" size="30" autocomplete="false"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
							</label></td>

							<td width="30%" class="alignRight">Age &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="age" type="text" class="myclass" id="age" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
 -->

						<!-- <tr>

							<td width="30%" class="alignRight">Place Of Birth, Tq,
								Dist.&nbsp;</td>
							<td width="12%" align="left"><label> <input
							style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									name="place" type="text" class="myclass" id="place" size="30">
							</label></td>
							
							<td width="20%" class="alignRight">Date of admission&nbsp;</td>
							<td width="28%"><label><input name="dateofadmission" autocomplete="false"
									type="text" class="myclass" id="dateofadmission" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									data-validate="validate(required)"> </label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr> -->

						<tr>


							<td class="alignLeft">Class Studying&nbsp;</td>
							<td ><label> <select name="addclass" required
									id="addclass" style="width: 256px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label> <label style="display: none;"> <select name="addsec" id="addsec" 
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
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Meals &nbsp;</td>
							<td height="30" class="alignLeft">&nbsp;Breakfast<input
								type="checkbox" value="breakfast" name="breakfast" id="breakfast"/>
								&nbsp; &nbsp;Lunch<input type="checkbox" value="lunch" name="lunch" id="lunch"/>
								&nbsp; &nbsp;Dinner<input type="checkbox" value="dinner" name="dinner" id="dinner"/>
								&nbsp; &nbsp;Corn Flakes & Milk<input type="checkbox" value="cornflakesandmilk" name="bankifsc" id="cornflakesandmilk"/>
							</td>
							
							<td  class="alignRight" style="display: none;">Admitted in Class &nbsp;
							</td>

							<td  style="display: none;"><label> <select name="admclassE"
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
							</label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						
						<tr>
							<td class="alignLeft">Type&nbsp;</td>
							<td ><label> 
									<select name="staytype"	id="staytype" style="width: 256px;">
										<option selected>Day Scholar</option>
										<option>Day Scholar with Bus</option>
										<option>Residential</option>
										<option>Semi-residential</option>
										</select>
							</label> </td>
							
							<td class="alignLeft" style="padding-left: 20px;">Campus&nbsp;</td>
							<td ><label> 
												<select name="campus" id="campus" style="width: 256px;">
														<option selected value="Shaheen Nagar_SN">Shaheen Nagar</option>
														<option value="Gole Khana_GK">Gole Khana</option>
														<option value="Mailoor_ML">Mailoor</option>
												</select>
							</label> </td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							
							<td   class="alignLeft">Date of admission&nbsp;</td>
							<td ><label><input name="dateofadmission" autocomplete="false"
								value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									type="text" class="myclass" id="dateofadmission" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									data-validate="validate(required)"> </label></td>
									
							<td class="alignLeft" style="padding-left: 20px;">Apply Fees &nbsp;</td>
							<td height="30" class="alignLeft">&nbsp;Non-NRI<input
								type="radio" value="nonnri" name="nationality" id="yes" checked/>
								&nbsp;NRI<input
								type="radio" value="nri" name="nationality" id="nri" />
								<br><br>Months
								<label> 
									<select name="monthfees" id="monthfees" onchange="calculateValidity(this.id)">
										<option></option>
										<option value="1">1 Month</option>
										<option value="2">2 Months</option>
										<option value="3">3 Months</option>
										<option value="4">4 Months</option>
										<option value="5">5 Months</option>
										<option value="6">6 Months</option>
										<option value="7">7 Months</option>
										<option value="8">8 Months</option>
										<option value="9">9 Months</option>
										<option value="10">10 Months</option>
										<option value="11">11 Months</option>
										<option value="12">12 Months</option>
										</select>
							</label>

							</td>
							
						</tr>
						
						<tr>
							
							<td class="alignLeft">&nbsp;</td>
							<td ></td>
									
							<td class="alignLeft" style="padding-left: 20px;">&nbsp;</td>
							<td height="30" class="alignLeft">
							Valid From
							<input name="validfrom" autocomplete="false"
									type="text" class="myclass" id="validfrom" size="6"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									data-validate="validate(required)">
							&nbsp;&nbsp;Valid Till
							<input name="validtill" autocomplete="false"
									type="text" class="myclass" id="validtill" size="6"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									data-validate="validate(required)">
							</td>
							
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						
						<!-- <tr>
							<td width="16%" class="alignRight">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 256px">
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
									id="nationality" style="width: 256px" onchange="dropdown()">
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


							<td width="16%" class="alignRight">Religion &nbsp;</td>

							<td width="28%">
								<label> <input name="religion"
									type="text" class="myclass" id="religion" size="30"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label> <label> <select name="religion"
									id="religion"
									style="width: 256px">
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
									type="text" class="myclass" id="caste" size="30"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label></td>

							<td width="20%" class="alignRight">Students Caste
								Certificate No.&nbsp;</td>
							<td width="28%"><label> <input
									name="studentscastecertno" type="text" class="myclass"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									id="studentscastecertno" size="30">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="20%" class="alignRight">Students Caste &nbsp;</td>
							<td width="28%"><label> <input name="studentscaste"
							style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									type="text" class="myclass" id="studentscaste" size="30">

							</label></td>

							<td width="16%" class="alignRight">Social Category&nbsp;</td>
							<td><label> <select name="socialcategory"
									id="socialcategory" style="width: 256px">
										<option selected></option>
										<option>General</option>
										<option>OBC</option>
										<option>SC</option>
										<option>ST</option>
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
							<td width="30%" class="alignRight">Was in receipt of any scholarship&nbsp;</td>
							<td width="16%" height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="belongtobpl" id="yes:bpl"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="belongtobpl" id="no:bpl"
								onclick="noCheck(this.id);" />

							</td>
							<td width="20%" class="alignRight">Student's adhar Card No.
								&nbsp;</td>
							<td width="28%"><label> <input
									name="bplcardno" type="text" class="myclass"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									id="bplcardno" size="30">

							</label></td>
						</tr>	
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="20%" class="alignRight">Whether Vaccinated
								&nbsp;</td>
								<td width="16%" height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="bhagyalakshmibondnumber" id="yes:vaccinated"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="bhagyalakshmibondnumber" id="no:vaccinated"
								onclick="noCheck(this.id);" />

							</td>
							<td width="16%" class="alignRight">Marks of Identification on Pupil's body&nbsp;</td>
							<td width="28%"><label> <input
									name="disabilitychild" type="text" class="myclass"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									id="disabilitychild" size="30">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td width="20%" class="alignRight">Special Category&nbsp;</td>

							<td id="categoryname"><label> <select
									name="specialcategory" onchange="enterOtherSpecialCategory()"
									id="specialcategory" style="width: 256px">
										<option selected>None</option>
										<option></option>
										<option>None</option>
										<option>Destitute</option>
										<option>HIV Case</option>
										<option>Orphans</option>
										<option>Others (Please Specify)</option>
								</select>

							</label></td>
							<td width="28%" id="newcateg"
								style="display: none;"><label> <input
									name="newcategory" id="newcategory" type="text" class="myclass" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									placeholder="Add Other Category" />
							</label></td>
							
							<td width="16%" class="alignRight">Mother Tongue &nbsp;</td>
							<td width="28%">
								<label> <input name="motherT"
									type="text" class="textField" id="motherT" size="30"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label> <label> <select name="motherT"
									 id="motherT"
									style="width: 256px">
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
						</tr>
						<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

						<tr>
						
							<td width="20%" class="alignRight">RTE
										&nbsp;</td>

									<td width="28%" height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="rte" id="yes:rte"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="rte" id="no:rte"
								onclick="noCheck(this.id);" />
										</td>
										
							<td width="20%" class="alignRight">Remarks &nbsp;</td>
							<td width="28%"><label> <input name="remarks"
									type="text" class="myclass" id="remarks" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
										
							<td width="20%" class="alignRight">Created Date &nbsp;</td>
							<td width="28%"><label> <input name="createddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepickerCD" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"s
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
							<td><br /></td>
						</tr>
						 -->
						 
						 <tr style="display: none;">
										
							<td  class="alignLeft">Created Date &nbsp;</td>
							<td ><label> <input name="createddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepickerCD" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"s
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
									<td align="center"><h3 style="text-decoration: underline;color: #eb6000">Parent's Details:</h3><br /></td>
								</tr>
								
								<tr>
									<td class="alignLeft">Father's Name* &nbsp;</td>
									<td align="left"><label> <input
											name="fathersname" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="fathersname" size="30"
											required> <!-- onkeyup="check(this.value);"  -->
									</label></td>
									
									<td class="alignLeft" style="padding-left: 20px;">Contact Number* &nbsp;</td>

									<td><label> <input
											name="contactnumber" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="contactnumber" size="30" maxlength="10" minlength="10">

									</label></td>

									<!-- <td class="alignLeft">Mother's Name* &nbsp;</td>
									<td align="left"><label> <input
											name="mothersname" type="text" class="myclass" id="name" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> onkeyup="check(this.value);" 
									</label></td> -->


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<!-- <tr>
									<td class="alignRight">Father's Qualification
										&nbsp;</td>
									<td align="left"><label> <input
											name="fathersqualification" type="text" class="myclass"
											id="fathersqualification" 
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> onkeyup="check(this.value);" 
									</label></td>

									<td class="alignRight">Mother's
										Qualification&nbsp;</td>
									<td align="left"><label> <input
											name="mothersqualification" type="text" class="myclass"
											id="mothersqualification"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> onkeyup="check(this.value);" 
									</label></td>


								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td class="alignRight">Fathers Occupation
										&nbsp;</td>
									<td><label> <input
											name="fatherscastecertno" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="fatherscastecertno" size="30">
									</label></td>

									<td class="alignRight">Mothers Occupation
										&nbsp;</td>
									<td ><label> <input
											name="motherscastecertno" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="motherscastecertno" size="30">
									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr> 

								<tr>

									<td class="alignRight">Guardian's Name &
										Address &nbsp;</td>
									<td ><label> <input name="guardian"
											type="text" class="myclass" id="guardian" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											>
									</label></td>



									<td class="alignRight">Annual Income &nbsp;</td>

									<td ><label> <input
											name="parentsannualincome" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="parentsannualincome" size="30"
											onkeyup="numberWithCommas(this);">

									</label></td>

								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td class="alignRight">Contact Number* &nbsp;</td>

									<td><label> <input
											name="contactnumber" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="contactnumber" size="30" maxlength="10" minlength="10">

									</label></td>



									<td class="alignRight">Co-Contact Number
										&nbsp;</td>

									<td><label> <input
											name="cocontactnumber" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="cocontactnumber" size="30" maxlength="10" minlength="10">

									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignRight">Email &nbsp;</td>

									<td ><label> <input name="email"
											type="email" class="myclass" id="email" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											>

									</label></td>

									<td class="alignRight">Number Of Dependents
										&nbsp;</td>

									<td ><label> <input
											name="noofdependents" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
											name="permanentaddress" type="text" 
											id="permanentaddress" rows="4" cols="40"
											></textarea>

								</label></td>


								<td class="alignLeft" style="padding-left: 20px;">Temporary Address &nbsp;</td>
								<td ><label> <textarea
											name="temporaryaddress" type="text" 
											id="temporaryaddress" rows="4" cols="40"></textarea>
								</label></td>
								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignLeft">Notes &nbsp;</td>
									<td ><label> <input name="remarksadditional"
											type="text" class="myclass" id="remarksadditional" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											>
									</label></td>
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

										<td align="center"><!-- <a class="nexttab"
											style="font-weight: bold; color: #325F6D; font-size: 13px"
											href="#">Next</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --> <a
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
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="reasonforleaving" size="30"
											>

									</label></td>



									<td class="alignRight">No. & date of transfer
										certificate issued&nbsp;</td>

									<td ><label> <input name="notcissued"
									style="height: 18px;font-size: 13px;font-weight: bold;" autocomplete="false"
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
											style="height: 18px;font-size: 13px;font-weight: bold;"
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


						<!-- <div id="fragment-2">
							<table width="100%" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignRight">Father's Name* &nbsp;</td>
									<td align="left"><label> <input
											name="fathersname" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="fathersname" size="30"
											required> onkeyup="check(this.value);" 
									</label></td>

									<td class="alignRight">Mother's Name* &nbsp;</td>
									<td align="left"><label> <input
											name="mothersname" type="text" class="myclass" id="name" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> onkeyup="check(this.value);" 
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignRight">Father's Qualification
										&nbsp;</td>
									<td align="left"><label> <input
											name="fathersqualification" type="text" class="myclass"
											id="fathersqualification" 
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> onkeyup="check(this.value);" 
									</label></td>

									<td class="alignRight">Mother's
										Qualification&nbsp;</td>
									<td align="left"><label> <input
											name="mothersqualification" type="text" class="myclass"
											id="mothersqualification"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> onkeyup="check(this.value);" 
									</label></td>


								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td class="alignRight">Fathers Occupation
										&nbsp;</td>
									<td><label> <input
											name="fatherscastecertno" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="fatherscastecertno" size="30">
									</label></td>

									<td class="alignRight">Mothers Occupation
										&nbsp;</td>
									<td ><label> <input
											name="motherscastecertno" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="motherscastecertno" size="30">
									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td class="alignRight">Guardian's Name &
										Address &nbsp;</td>
									<td ><label> <input name="guardian"
											type="text" class="myclass" id="guardian" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											>
									</label></td>



									<td class="alignRight">Annual Income &nbsp;</td>

									<td ><label> <input
											name="parentsannualincome" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="parentsannualincome" size="30"
											onkeyup="numberWithCommas(this);">

									</label></td>

								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td class="alignRight">Contact Number* &nbsp;</td>

									<td><label> <input
											name="contactnumber" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="contactnumber" size="30" maxlength="10" minlength="10">

									</label></td>



									<td class="alignRight">Co-Contact Number
										&nbsp;</td>

									<td><label> <input
											name="cocontactnumber" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="cocontactnumber" size="30" maxlength="10" minlength="10">

									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignRight">Email &nbsp;</td>

									<td ><label> <input name="email"
											type="email" class="myclass" id="email" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											>

									</label></td>

									<td class="alignRight">Number Of Dependents
										&nbsp;</td>

									<td ><label> <input
											name="noofdependents" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="noofdependents" size="30" >

									</label></td>


								</tr>

								<tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<td class="alignRight">Permanent Address &nbsp;</td>

								<td ><label> <textarea
											name="permanentaddress" type="text" 
											id="permanentaddress" rows="4" cols="40"
											></textarea>

								</label></td>


								<td class="alignRight">Temporary Address &nbsp;</td>
								<td ><label> <textarea
											name="temporaryaddress" type="text" 
											id="temporaryaddress" rows="4" cols="40"></textarea>
								</label></td>
								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignRight">Notes &nbsp;</td>
									<td ><label> <input name="remarksadditional"
											type="text" class="myclass" id="remarksadditional" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
									<td class="alignRight">&nbsp;</td>
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
									<td class="alignRight">&nbsp;</td>
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
						</div> -->
						
						<%-- <div id="fragment-5">

							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>

									<tr>
							<td class="alignRight">Transfer
								certificate No.&nbsp;</td>
							<td  align="left"><label> <input
							style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									name="tcno" type="text" class="myclass" id="tcno" size="30">
									
							</label></td>
									<td class="alignRight">Date of Transfer Certificate&nbsp;</td>
							<td ><label >
							<input name="dateoftc" type="text" class="myclass"
									id="dateoftc" size="30" autocomplete="false"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									data-validate="validate(required)"></label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignRight">Previous Class Studied &nbsp;</td>

							<td ><label> <select name="lastclass" id="lastclass"
									style="width: 256px">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<option value="${classdetailslist.classdetails}">
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:forEach>
								</select>
							</label></td>

							<td class="alignRight">Previous School Name
								&nbsp;</td>
							<td  align="left"><label> <input
									name="lastschool" type="text" class="myclass" id="lastschool"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									size="30" >
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignRight">Languages Studied &nbsp;</td>

							<td><label> <input
									name="languagesstudied" type="text" class="myclass"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									id="languagesstudied" size="30">
							</label></td>



							<td class="alignRight">Core Subjects Studied &nbsp;</td>
							<td><label> <input
									name="progress" type="text"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									class="myclass" id="progress" size="30">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignRight">Previous School Medium of
								Instruction&nbsp;</td>

							<td><label> <select name="mediumofinstruction"
									id="mediumofinstruction" style="width: 256px">
										<option selected></option>
										<option>Kannada</option>
										<option>Hindi</option>
										<option>Urdu</option>
										<option>English</option>
										<option>Marathi</option>
										<option>Tamil</option>
										<option>Telgu</option>
								</select>

							</label></td>
							
							<td class="alignRight">Previous School
								Type&nbsp;</td>

							<td><label> <select name="previousschooltype"
									id="previousschooltype" style="width: 256px">
										<option selected></option>
										<option>Government</option>
										<option>Private Aided</option>
										<option>Local Bodies</option>
										<option>Private Unaided School</option>
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
									<td class="alignRight">&nbsp;</td>
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
										<td align="left">


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

						</div> --%>
						
						<!-- <div id="fragment-6">

							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td class="alignRight">Bank Name &nbsp;</td>
							<td><label> <input name="bankname" type="text" class="myclass" id="bankname" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
							</label></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						
						<tr>
							<td class="alignRight">Bank IFSC Code&nbsp;</td>
							<td><label> <input name="bankifsc" type="text" class="myclass" id="bankifsc" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
							</label></td>
						</tr>
								<tr>
										<td><br /></td>
								</tr>
						<tr>
							<td class="alignRight">Account No. &nbsp;</td>
							<td><label> <input name="accno" type="text" class="myclass" id="accno" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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


