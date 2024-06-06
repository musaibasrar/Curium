<%-- 
    Document   : sendemail
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diary</title>
<link rel="stylesheet" href="/sneha/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/sneha/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/sneha/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/sneha/js/datePicker/jquery-1.7.1.js"></script>
<script src="/sneha/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/sneha/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/sneha/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/sneha/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/sneha/js/datePicker/ui/sliderAccess.js"></script>
<script src="/sneha/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/sneha/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/sneha/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/sneha/css/datePicker/demos.css">





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

.alignCenter {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: center;
	//vertical-align: middle;
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





<script type="text/javascript" src="/sneha/js/datetimepicker_css.js"></script>

<script src="/sneha/JavaScript/actb.js"></script>
<script src="/sneha/JavaScript/common.js"></script>






<script type="text/javascript">
	
	    $(function() {
			$("#datepicker1").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-1:+2"
			});
			$("#anim").change(function() {
				$("#datepicker1").datepicker("option", "showAnim", $(this).val());
			});
		});
	
	    $(function() {
			$("#datepicker2").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-1:+2"
			});
			$("#anim").change(function() {
				$("#datepicker2").datepicker("option", "showAnim", $(this).val());
			});
		});
	    $(function() {
			$("#datepicker3").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-1:+2"
			});
			$("#anim").change(function() {
				$("#datepicker3").datepicker("option", "showAnim", $(this).val());
			});
		});
</script>
<script type="text/javascript">
	function addDiary() {
		var form1 = document.getElementById("form1");
		form1.action = "/sneha/DiaryProcess/addDiary";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#savediary").button().click(function() {
			addDiary();
		});
		//$("#effect").hide();

	});
	
	</script>
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sneha/UserProcess/sessionTimeOut");
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
	<%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Diary</a></li>
				<!-- 	<li><a href="#tabs-2">Staff</a></li>
					<li><a href="#tabs-3">Selected Numbers</a></li>-->
				</ul>



				<div id="tabs-1">
					<table  border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td style="font-weight: bold">Class&nbsp;</td>
							<td style="font-weight: bold" ><label> <select name="addclass"
									id="addclass" style="width: 186px;border-radius: 4px;background: white;height: 28px;" onchange="searchfeecategory()">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label> <label> <select name="addsec" id="addsec" style="width: 70px;border-radius: 4px;background: white;height: 28px;">
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

						
						<tr>
						
						<td style="font-weight: bold">Subject &nbsp;</td>
							<td style="font-weight: bold"><label> <input  name="subject"
											type="text" class="textField" id="subject" 
											/>
							</label></td>
							
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td style="font-weight: bold">Message* &nbsp;</td>
							<td style="font-weight: bold"><label> <textarea  name="messagebody"
											type="text" class="textField" id="messagebody" rows="6" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						
					<tr>	<td style="font-weight: bold">Start Date &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="startdate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepicker1" size="36"
									data-validate="validate(required)">
							</label></td></tr>
					<tr>	<td style="font-weight: bold">End Date &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="enddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepicker2" size="36"
									data-validate="validate(required)">
							</label></td></tr>
						<tr>	<td style="font-weight: bold">Create Date &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="createddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepicker3" size="36"
									data-validate="validate(required)">
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

										<button id="savediary">Save</button>

										
									</td>


								</tr>
							</table>

						</div>
						</form>
						
</body>
</html>


