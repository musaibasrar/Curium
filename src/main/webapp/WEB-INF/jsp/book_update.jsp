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
<title>Add Book</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/abc/js/datePicker/ui/sliderAccess.js"></script>
<script src="/abc/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/abc/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">





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





<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>

<script src="/abc/JavaScript/actb.js"></script>
<script src="/abc/JavaScript/common.js"></script>






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
	function saveBook() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/LibraryProcess/saveBook";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#addbook").button().click(function() {
			saveBook();
		});
		//$("#effect").hide();

	});
	
	</script>
	<script type="text/javascript">
	$(function() {

		$(".set").button().click(function() {

			updateBookDetail();

		});

		$(".cancel").button().click(function() {
			cancel();

		});
	});
</script>
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
					<li><a href="#tabs-1">Update Books</a></li>
				<!-- 	<li><a href="#tabs-2">Staff</a></li>
					<li><a href="#tabs-3">Selected Numbers</a></li>-->
				</ul>



				<div id="tabs-1">
					<table  border="0" align="center" id="table1" style="font-size:15px;">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						
						<td style="font-weight: bold;color: #325F6D;">Book Name &nbsp;</td>
							<td style="font-weight: bold"><label><input type="hidden" name="bid" value="${book.bid}"/> <input name="bookname"
									type="text" class="myclass"
									value="<c:out default="" value="${book.bookname}" />"
									id="bookname" >
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							
						
						
						<td style="font-weight: bold;color: #325F6D;">Subject &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="subject"
									type="text" class="myclass"
									value="<c:out default="" value="${book.subject}" />"
									id="subject" >
							</label></td>
							
						</tr>
                        
                        <tr>
							<td><br/></td>
						</tr>
						
					<tr>
						
						<td style="font-weight: bold;color: #325F6D;">Author &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="author"
									type="text" class="myclass"
									value="<c:out default="" value="${book.author}" />"
									id="author" >
							</label></td>
							
						
						
						<td style="font-weight: bold;color: #325F6D;">Publisher &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="publisher"
									type="text" class="myclass"
									value="<c:out default="" value="${book.publisher}" />"
									id="publisher" >
							</label></td>
							
						</tr>
						
						 <tr>
							<td><br/></td>
						</tr>
						
						<tr>
						
						<td style="font-weight: bold;color: #325F6D;">ISBN NO. &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="isbn"
									type="text" class="myclass"
									value="<c:out default="" value="${book.isbn}" />"
									id="isbn" >
							</label></td>
						<td style="font-weight: bold;color: #325F6D;">Shelf No. &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="shelf"
									type="text" class="myclass"
									value="<c:out default="" value="${book.shelf}" />"
									id="shelf" >
							</label></td>
						
							
						</tr>

						 <tr>
							<td><br/></td>
						</tr>
						
						<tr>
						
						<td style="font-weight: bold;color: #325F6D;">Status &nbsp;</td>
							<td style="font-weight: bold"> <label> <select name="status" class="myclass" style="width:185px;height:32px;"
									id="status" >
										<option selected></option>
										<option>Available</option>
										<option>Issued</option>
								</select></td>
							
												
						
							
						</tr>
						
						 <tr>
							<td><br/></td>
						</tr>
						
						
						

						<tr>
							<td><br /></td>
						</tr>

					</table>
						<div>
							<table id="table2" width="30%" border="0" align="center">

						<tr>

							<td></td>

						</tr>
						
						
									
						<tr>

							<td></td>

						</tr>
						<tr>
							<td align="center">

								<button id="set3" class="set">Update</button>

							</td>
						</tr>


					</table>

						</div>
						
						</form>
						
						<script type="text/javascript">
							function cancel() {

								var form1 = document.getElementById(form1);
								form1.action = "/abc/LibraryProcess/viewbooks";
								form1.submit();
							}

							function updateBookDetail() {

								var form1 = document.getElementById("form1");
								form1.action = "/abc/LibraryProcess/updateBook";
								form1.submit();
							}
							</script>
							
						
</body>
</html>


