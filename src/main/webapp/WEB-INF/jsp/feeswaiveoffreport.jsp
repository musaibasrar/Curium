<%--
    Document   : Fees Waive Off Report
    Created on : Dec 23, 2011, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fees Waive Off Report</title>
<link rel="stylesheet" href="/alirfan/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/alirfan/css/datePicker/demos.css">
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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
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
<link rel="stylesheet" href="/alirfan/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/alirfan/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/alirfan/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/alirfan/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/alirfan/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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

<script type="text/javascript" src="/alirfan/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function searchForFeesWaiveOff() {
		var form1 = document.getElementById("form1");
		form1.action = "/alirfan/FeesProcess/searchFeesWaiveoffReport";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			searchForFeesWaiveOff();
		});
		

	});

	$(function() {

		$("#tabs").tabs();

		
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

	
	function printRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/alirfan/FeesProcess/printFeesWaiveoffReport";
		form1.method = "POST";
		form1.submit();
	}
	
	 $(function(){
		 
         
         $("#print").button({
             icons:{
                 primary: "ui-icon-print"
             }
         }).click(function(){
             printRecords();
             return false;

         });
         
     });
     
</script>




</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/alirfan/UserProcess/sessionTimeOut");
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
	<form id="form1">

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Fees Waive Off Report</a></li>

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
					<td class="headerTD">Fees Waive Off Report</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<!-- <th class="headerText"><input type="checkbox" id="chckHead" /></th> -->
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Class & Sec&nbsp;</th>
						<th title="click to sort" class="headerText">Fees Category</th>
						<th title="click to sort" class="headerText">Fees Amount</th>
						<th title="click to sort" class="headerText">Installment</th>
						<th title="click to sort" class="headerText">Total Fees Amount</th>
						<th title="click to sort" class="headerText">Waive Off Amount</th>

					</tr>
				</thead>

				<tbody>
					<c:forEach items="${studentsfeesstructuredetailswaiveoff}" var="students">

						<c:forEach items="${students.value}" var="fees">
						
							<tr class="trClass" style="border-color: #000000" border="1" cellpadding="1" cellspacing="1">
								<%-- <td class="dataText"><input type="checkbox"
									id="<c:out value="${Parents.student.sid}"/>" class="chcktbl"
									name="studentIDs"
									value="<c:out value="${Parents.student.sid}"/>" /></td> --%>
								<td class="dataTextInActive"><a class="dataTextInActive"
									href="/alirfan/StudentProcess/ViewFeesStructure?id=<c:out value='${students.key.student.sid}'/>"><c:out
											value="${students.key.student.admissionnumber}" /></a></td>
								<td class="dataText"><c:out value="${students.key.student.name}" /></td>
								<td class="dataText"><c:out	value="${students.key.student.classstudying}" /></td>
								<td class="dataText"><c:out value="${fees.feescategory.feescategoryname}"/></td>
                                <td class="dataText"><c:out value="${fees.feescategory.amount}"/></td>
								<td class="dataText"><c:out	value="${fees.totalinstallment}" /></td>
								<td class="dataText"><c:out	value="${fees.feesamount}" /></td>
								<td class="dataText"><c:out	value="${fees.waiveoff}" /></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2"><button id="print">Print</button>
							</td>
					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
