<%--
    Document   : card validity
    Created on : Feb 01, 2021, 8:56:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Card Validity</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
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


.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

.success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}


.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
}

.buttonred {
  background-color: red; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
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

.alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.alignSearch {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
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

.footerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	text-align: left;
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

<!--
.header {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	background-color: #4b6a84;
}

.table {
	background-color: #3399CC;
	text-align: center;
	width: auto;
}

.headerText {
	border-radius: 3px;
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
	vertical-align: text-top;
	text-align: center;
	background-image:
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.dataTextInActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: none;
}

.dataTextActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: underline;
	cursor: pointer;
}

.dataTextHidden {
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.headerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	background-image:
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="css/validation/jquery.ketchup.css">
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		

	}
	
</script>

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function searchDetails() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=printids&action=searchDetailsCardValidity";
		form1.method = "POST";
		form1.submit();

	}

	function updateCardValidity() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=Printids&action=updateCardValidity";
		form1.method = "POST";
		form1.submit();

	}
	
	$(function() {

		$("#search").button().click(function() {
			searchDetails();
		});
		
		$("#cardvalidity").button().click(function() {
			updateCardValidity();
		});
		

	});

	$(function() {

		$("#tabs").tabs();

	});
	
	$(function() {
	
		$('#chckHead').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			if (length > 0) {
				$('.chcktbl:checked').attr('checked', false);
				this.checked = false;

			} else {
				if (this.checked == false) {
					$('.chcktbl:checked').attr('checked', false);
				} else {
					$('.chcktbl:not(:checked)').attr('checked', true);
				}

			}

		});
		$('.chcktbl').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			alert(tdLength);
			if (length > trLength) {

				$('.chcktbl:not(:checked)').attr('disabled', true);
			} else {
				$('.chcktbl:not(:checked)').attr('disabled', false);
			}
		});

	});
	
	  function selectSearch(id){
	    	
	    	
	    	if(id == 'studentnamesearch'){
	    		
	    		document.getElementById('studentsearchtr').style.display = '';
	    		document.getElementById('classsearchtr').style.display = "none";
	    		document.getElementById('classsearch').selectedIndex = -1;
	    			
	    	}else if(id == 'classsearch'){
	    		
	    		document.getElementById('classsearchtr').style.display = '';
	    		document.getElementById('studentsearchtr').style.display = "none";
	    		document.getElementById('namesearch').value = '';
	    		
	    	}
	    	
	    }
	  
	  
	
</script>

	<script type="text/javascript">
					
					var updatecard='<c:out default="" value="${updatecard}"/>';
		            
		            if(updatecard == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 200 );
		            	 });
		            	 }else if(updatecard == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 200 );
		            		 });
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
	<form id="form1" method="POST">
		
		<div class="alert-box success" id="div1">Card validity has been updated successfully!!!&nbsp;&nbsp;&nbsp;<!-- <button class="button" id="1" onclick="closediv(this.id);">OK</button> --></div>
		<div class="alert-box failure" id="div2">Card validity cann't be updated, Please try again!!!&nbsp;&nbsp;&nbsp;<!-- <button class="buttonred" id="2" onclick="closediv(this.id);">OK</button> --></div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Mess Card Validity</a></li>

				</ul>
				<div id="tabs-1">
				
					<table border="0" cellpadding="0"
						cellspacing="0" id="table1" style="width: auto;height: auto">

						<tr>
							<td class="alignRightFields"><h3 style="color: #eb6000">Search:&nbsp;&nbsp;&nbsp;&nbsp;	</h3></td>
							<td class="alignRightFields">
							<input type="radio" id="studentnamesearch" name="advancesearch" value="studentname" onclick="selectSearch(this.id)">
							<label for="studentname">Student Name</label>
							 &nbsp;</td>
							 <td class="alignRightFields">
							<input type="radio" id="classsearch" name="advancesearch" value="class" onclick="selectSearch(this.id)">
							<label for="class">Class</label>
							 &nbsp;</td>
							 
						</tr>
						
						<tr>
							<td>
								<br>
							</td>
						</tr>
						
						</table>
						
					<table border="0" cellpadding="0"
						cellspacing="0" id="table1" style="width: auto;height: auto;">

						<tr style="display: none;" id="studentsearchtr">
							<td class="alignRightFields">Student Name &nbsp;</td>
							<td align="left"><label> <input
									name="namesearch" type="text" class="myclass" id="namesearch"
									size="36">
							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>


						<tr style="display: none;" id="classsearchtr">
							<td class="alignRightFields">Class &nbsp;</td>
							<td><label> <select name="classsearch"
									id="classsearch" style="width: 235px">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label> <label style="display: none;"> <select name="secsearch" id="secsearch"
									style="width: 100px">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.section != '')}">
												<option value="${classdetailslist.section}">
													<c:out value="${classdetailslist.section}" />
												</option>
											</c:if>
										</c:forEach>

								</select>
							</label>
						</tr>

						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td></td>

							<td >&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

					</table>
					
					

				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Search result</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Admission #</th>
						<th title="click to sort" class="headerText">Name</th>
						<th title="click to sort" class="headerText">Class &nbsp;</th>
						<th title="click to sort" class="headerText">Valid From(dd/mm/yyyy)</th>
						<th title="click to sort" class="headerText">Valid Till(dd/mm/yyyy)</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${parentscardlist}" var="parentscardlist" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${parentscardlist.key.student.sid}"/>" class="chcktbl"
								name="studentIDs"
								value="<c:out value="${parentscardlist.key.student.sid}"/>" /></td>
							<td class="dataTextInActive"><c:out	value="${parentscardlist.key.student.admissionnumber}" /></td>
							<td class="dataText"><c:out value="${parentscardlist.key.student.name}" /></td>
							<td class="dataText"><c:out value="${parentscardlist.key.student.classstudying}" /></td>
							<td class="dataText"><input name="validfrom_${parentscardlist.key.student.sid}"  autocomplete="false"
								value="<fmt:formatDate type="date" value="${parentscardlist.value.validfrom}" pattern="dd/MM/yyyy"/>"
									type="text" class="myclass" id="validfrom" size="10"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
							</td>
							<td class="dataText">
								<input name="validto_${parentscardlist.key.student.sid}" autocomplete="false" 
								value="<fmt:formatDate type="date" value="${parentscardlist.value.validto}" pattern="dd/MM/yyyy"/>"
									type="text" class="myclass" id="validto" size="10"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2">
						<button id="cardvalidity">Update</button>
							</td>
							

					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
