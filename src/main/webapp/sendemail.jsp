<%-- 
    Document   : sendemail
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
<title>Send Email</title>
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
			changeMonth : true
		});
		$( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepicker1").datepicker({
			changeYear : true,
			changeMonth : true
		});
		$( "#datepicker1" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$("#anim").change(function() {
			$("#datepicker1").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true
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
		
		$("#sendemail").button().click(function() {
			sendEmailAll();

		});

		$("#sendsmsnumbers").button().click(function() {
			sendSMSNumbers();

		});
		
		$("#sendsmsstaff").button().click(function() {
			sendSMSStaff();

		});

		
		$("#reset").button().click(function() {
			 
		});
		
		$("#resetnumbers").button().click(function() {
		
		});
		
		$("#resetstaff").button().click(function() {
			
		});
		
		 $("#numbers").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which != 44) {
		        //display error message
		        $("#errmsg").html("Digits Only").show().fadeOut("slow");
		               return false;
		    }
		   });
	});
</script>
<script>
	$(function() {
		$("#tabs").tabs();

		$("#messagebody").keyup(function(){
			  $("#count").text($(this).val().length);
			  if($(this).val().length==0){
				  $("#messagecount").text("0");
			  }else if($(this).val().length<160){
				  $("#messagecount").text("1");
			  }else if($(this).val().length>159 && $(this).val().length<=306){
				  $("#messagecount").text("2");
			  }else if ($(this).val().length>306 && $(this).val().length<=453){
				  $("#messagecount").text("3");
			  }else if ($(this).val().length>453 && $(this).val().length<=600){
				  $("#messagecount").text("4");
			  }else if ($(this).val().length>600){
				  $("#messagecount").text("5");
			  }
			});
		
		$("#messagebodynumbers").keyup(function(){
			  $("#countnumbers").text($(this).val().length);
			  if($(this).val().length==0){
				  $("#messagecountnumbers").text("0");
			  }else if($(this).val().length<160){
				  $("#messagecountnumbers").text("1");
			  }else if($(this).val().length>159 && $(this).val().length<=306){
				  $("#messagecountnumbers").text("2");
			  }else if ($(this).val().length>306 && $(this).val().length<=453){
				  $("#messagecountnumbers").text("3");
			  }else if ($(this).val().length>453 && $(this).val().length<=600){
				  $("#messagecountnumbers").text("4");
			  }else if ($(this).val().length>600){
				  $("#messagecountnumbers").text("5");
			  }
			});
		
		$("#messagebodystaff").keyup(function(){
			  $("#countstaff").text($(this).val().length);
			  if($(this).val().length==0){
				  $("#messagecountstaff").text("0");
			  }else if($(this).val().length<160){
				  $("#messagecountstaff").text("1");
			  }else if($(this).val().length>159 && $(this).val().length<=306){
				  $("#messagecountstaff").text("2");
			  }else if ($(this).val().length>306 && $(this).val().length<=453){
				  $("#messagecountstaff").text("3");
			  }else if ($(this).val().length>453 && $(this).val().length<=600){
				  $("#messagecountstaff").text("4");
			  }else if ($(this).val().length>600){
				  $("#messagecountstaff").text("5");
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
	<form method="post"  id="form1">
		
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Students</a></li>
					<li><a href="#tabs-2">Staff</a></li>
					<li><a href="#tabs-3">Selected Numbers</a></li>
				</ul>



				<div id="tabs-1">
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


							<td class="alignRight">Select&nbsp;</td>
							<td width="28%"> <label> <select name="addclass" id="addclass"
									style="width: 120px">
										<option selected></option>
										<option>ALL</option>
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
										<option selected></option>
										<option>ALL</option>
										<option>A</option>
										<option>B</option>
										<option>C</option>
										<option>D</option>
										<option>E</option>
										<option>F</option>
										<option>G</option>

								</select>
							</label></td>
							<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						
						<td width="30%" class="alignRight">Subject &nbsp;</td>
							<td width="12%" align="left"><label> <textarea  name="subject"
											type="text" class="textField" id="subject" rows="1" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td width="40%" class="alignRight">Message* &nbsp;</td>
							<td width="12%" align="center"><label> <textarea  name="messagebody"
											type="text" class="textField" id="messagebody" rows="6" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						
						<td width="30%" class="alignRight">Count: &nbsp;</td>
							<td width="12%" align="left"><label name="count" id="count" style="color: #325F6D;font-weight: bold;">
							</label></td>
							
						</tr>

						<tr>
							<td width="30%" class="alignRight">No. Of Messages : &nbsp;</td>
							<td width="12%" align="left"><label name="messagecount" id="messagecount" style="color: #325F6D;font-weight: bold;">
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
									<td align="center">

										<button id="sendemail">Send</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="reset">Reset</button>

									</td>


								</tr>
							</table>

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
							<td width="40%" class="alignRight">Message* &nbsp;</td>
							<td width="12%" align="center"><label> <textarea  name="messagebodystaff"
											type="text" class="textField" id="messagebodystaff" rows="6" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						<tr>
						
						<td width="30%" class="alignRight">Count: &nbsp;</td>
							<td width="12%" align="left"><label name="countstaff" id="countstaff" style="color: #325F6D;font-weight: bold;">
							</label></td>
							
						</tr>

						<tr>
							<td width="30%" class="alignRight">No. Of Messages : &nbsp;</td>
							<td width="12%" align="left"><label name="messagecountstaff" id="messagecountstaff" style="color: #325F6D;font-weight: bold;">
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
									<td align="center">

										<button id="sendsmsstaff">Send</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="resetstaff">Reset</button>

									</td>


								</tr>
							</table>

						</div>
						
					<div id="tabs-3">
					
					<br><br>
					<div align="center">
					<label style="font-weight: bold;color: black;font-family:monospace;color:#EB6000;font-size: 14px;">Note: Enter mobile numbers seperated
													by commas. Ex: 9986XXXXXX,9738XXXXXX
										</label>
						</div>									
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
										id="mydivmobile">
										</div></font></td>
						</tr>

					

						<tr>
										
							<td width="40%" class="alignRight">Numbers* &nbsp;</td>
							<td width="12%" align="center"><label> <textarea  name="numbers"
											type="text" class="textField" id="numbers" rows="6" cols="55"
											></textarea>
							</label></td>
							<td><span id="errmsg" style="color:red;font-weight: bold;"></span></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="40%" class="alignRight">Message* &nbsp;</td>
							<td width="12%" align="center"><label> <textarea  name="messagebodynumbers"
											type="text" class="textField" id="messagebodynumbers" rows="6" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						<tr>
						<tr>
							<td><br /></td>
						</tr>
						<td width="30%" class="alignRight">Count: &nbsp;</td>
							<td width="12%" align="left"><label name="countnumbers" id="countnumbers" style="color: #325F6D;font-weight: bold;">
							</label></td>
							
						</tr>

						<tr>
							<td width="30%" class="alignRight">No. Of Messages : &nbsp;</td>
							<td width="12%" align="left"><label name="messagecountnumbers" id="messagecountnumbers" style="color: #325F6D;font-weight: bold;">
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
									<td align="center">

										<button id="sendsmsnumbers">Send</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="resetnumbers">Reset</button>

									</td>


								</tr>
							</table>

						</div>

						</div>
						</div>


						</form>
						<script type="text/javascript">
							function sendEmailAll() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=EmailProcess&action=sendAllEmail";
								form1.submit();
							}
							
							function sendSMSNumbers() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=SMSProcess&action=sendNumbersSMS";
								form1.submit();
							}
							
							function sendSMSStaff() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=SMSProcess&action=sendStaffSMS";
								form1.submit();
							}
							
						</script>
</body>
</html>


