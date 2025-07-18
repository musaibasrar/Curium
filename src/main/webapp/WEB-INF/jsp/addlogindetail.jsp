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
<title>Add Login</title>
<link rel="stylesheet" href="/scholar/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/scholar/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/scholar/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/scholar/js/datePicker/jquery-1.7.1.js"></script>
<script src="/scholar/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/scholar/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/scholar/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/scholar/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/scholar/js/datePicker/ui/sliderAccess.js"></script>
<script src="/scholar/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/scholar/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/scholar/css/datePicker/demos.css">

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
</style>


<script type="text/javascript" src="/scholar/js/datetimepicker_css.js"></script>
<script src="/scholar/JavaScript/actb.js"></script>
<script src="/scholar/JavaScript/common.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
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
		$( "#datepicker1" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
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
		$( "#datepickerCD" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
		$("#anim").change(function() {
			$("#datepickerCD").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerleaving").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$( "#datepickerleaving" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
		$("#anim").change(function() {
			$("#datepickerleaving").datepicker("option", "showAnim", $(this).val());
		});
	});
	
	
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

		$(".save").button().click(function() {
			addLoginStaff();

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
	function check(value) {

		xmlHttp = GetXmlHttpObject()
		var url = "/check";
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
		var url = "/mobilecheck";
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

</script>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/scholar/UserProcess/sessionTimeOut");
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
	<form id="form1" method="post" enctype="multipart/form-data">
		
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		
		<div>
		
			<div id="tabs">
				
				<ul>
					<li><a href="#tabs-1">Login Details</a></li>
				</ul>

				<div id="tabs-1">

					<table width="100%" border="0" align="center" id="table1">
					
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr><td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>
							<td class="alignRight" >User Name* &nbsp;</td>
							<td align="left"><label> <input
									name="username" type="text" class="myclass" id="name" size="20" 
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									> <!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td class="alignRight">Password &nbsp;</td>
							<td><label> <input
									name="password" type="text" class="myclass"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									id="contactnumber" size="20" maxlength="10" minlength="10"/>
							</label></td><td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
						<tr><td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>

							<td class="alignRight">User Type &nbsp;</td>
							<td><label> <input name="usertype"
							style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									type="text" class="myclass" id="address" size="20">
							</label></td>

							<td class="alignRight"> &nbsp;Branch</td>
							<td><label> <select name="branchid"
									id="admclassE" style="width: 186px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${branchdetail}" var="branchdetaillist">
											<c:if test="${(branchdetaillist.branchname != '')}">
												<option value="${branchdetaillist.idbranch}">
													<c:out value="${branchdetaillist.branchname}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label></td>	<td>&emsp;&emsp;&emsp;&emsp;&emsp;</td>					</tr>
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
						
						<tr>
							<td><br /></td>
						</tr>
						
									
									
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td></td><td></td>
									<td>
										<button id="save" class="save" onmouseover="validateNameContact();">Save</button>

									</td>
								</tr>
					</table>
					</div>
					
					
					
					
					

						</div>
					
					</div>
					
						</div>
						</form>
						<script type="text/javascript">
							function addLoginStaff() {
								var form1 = document.getElementById("form1");
								form1.action = "/scholar/LoginProcess/addLoginStaffDetail";
								form1.submit();
							}

							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "/scholar/EmployeeProcess/viewAllEmployee";
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


