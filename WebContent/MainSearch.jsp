<%-- 
    Document   : Main Search
    Created on : DEC 11, 2021, 9:16:40 PM
    Author     : Musaib
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Search</title>
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

.alignLeft {
	font-family: Tahoma;
	font-size: 16px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
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
			yearRange: "-50:+0"
		});
		$( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepicker1").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$( "#datepicker1" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$("#anim").change(function() {
			$("#datepicker1").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$( "#datepickerCD" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$("#anim").change(function() {
			$("#datepickerCD").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>



<script>
	

	function validateFormNum(obj) {
		document.getElementById("formNo").style.background = 'white';

		reg = /[^0-9]/g;
		obj.value = obj.value.replace(reg, "");
	}

	

	function validateNameAlpha(obj) {

		document.getElementById("name").style.background = 'white';

		reg = /[^a-z]/g;
		obj.value = obj.value.replace(reg, "");
	}

	

	

	function validateContactNum(obj) {

		document.getElementById("contactNO").style.background = 'white';

		reg = /[^0-9]/g;
		obj.value = obj.value.replace(reg, "");

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
		$("#search").button().click(function() {
			searchStudent();

		});
		
		$("#searchParents").button().click(function() {
			searchByParents();

		});

		$("#cancel").button().click(function() {
			Cancel();

		});
		
		 $("#sts").keypress(function (e) {
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
	<form id="form1" action="Controller?process=UserProcess&action=advanceMainSearch"
		method="post">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"dd/MM/yyyy");
		%>

		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Search</a></li>
					
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
									<td align="center"><h1 style="text-decoration: underline;color: #eb6000">Search Students</h1><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Admission Number &nbsp;</td>
							<td ><label> <input name="admnno"
									type="text" class="myclass" id="admnno" size="36"
									
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
							<td class="alignLeft">Name &nbsp;</td>
							<td ><label> <input
									name="name" type="text" class="myclass" id="name" size="36"
									onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
							</label></td>
							
							
							<td class="alignLeft" style="padding-left: 20px;">Class Studying&nbsp;</td>
							<td> <label> <select name="addclass" id="addclass"  class="dropdown"
									style="width: 186px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>

							</label> <label style="display: none;"> 
									<select name="addsec" id="addsec" class="dropdown" style="width: 70px;border-radius: 4px;background: white;height: 28px;">
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
					</table>
					</div>						

						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								<tr>
									<td align="center">

										<button id="search" >Search</button>

									</td>


								</tr>
							</table>
							
							
							<table style="width: auto;height: auto;" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td align="center"><h1 style="text-decoration: underline;color: #eb6000">Search Parents</h1><br /></td>
						</tr>
								

								<tr>
									<td class="alignLeft">Father Name &nbsp;</td>
									<td ><label> <input
											name="fathersname" type="text" class="myclass" id="name"
											size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Mother Name &nbsp;</td>
									<td ><label> <input
											name="mothersname" type="text" class="myclass" id="name"
											size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


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

										<button id="searchParents" >Search</button>

										

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
							
							

						</div>
						
						</div>


						</form>
						<script type="text/javascript">
							function searchStudent() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=UserProcess&action=advanceMainSearch";
								form1.submit();
							}
							function searchByParents() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=UserProcess&action=advanceSearchByParents";
								form1.submit();
							}
							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=PersonalProcess&action=ViewAll";
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


