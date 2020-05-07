<%--
    Document   : balance sheet
    Created on : Mar 07, 2018, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Balance Sheet</title>
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
	function searchForFees() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StampFeesProcess&action=searchForFees";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			searchForFees();
		});
		

	});

	$(function() {

		$("#tabs").tabs();

		$("#save").button().click(function() {
			addDepartment();
		});
		/* $("#effect").hide(); */

	});
	


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
	<form id="form1">

		<div style="overflow: scroll; height: 600px">
		<table width="100%">
                    <tr>
                        <td  class="headerTD">Balance Sheet</td>
                    </tr>

                    

                </table>
                
               <br><br>
                
			<table width="50%" border="0" style="border-color: #4b6a84;float: left;margin-bottom:50px;">

				<thead>
					<tr>
						
						<th title="click to sort" class="headerText" style="font-weight: bold;">Capital & Liabilities</th>
						
						<th title="click to sort" class="headerText" style="font-weight: bold;">Amount</th>
						
						<th ></th>


					</tr>
				</thead>

				<tbody>
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">CAPITAL</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${capital}" /></td>

						</tr>
						
					<c:forEach items="${capitalledgeraccount}" var="capitalledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${capitalledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${capitalledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">LOANS(LIABILITY)</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${loansliabilities}" /></td>

						</tr>
					<c:forEach items="${loansliabilitiesledgeraccount}" var="loansliabilitiesledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${loansliabilitiesledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${loansliabilitiesledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">CURRENT LIABILITIES</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${currentliabilities}" /></td>

						</tr>
					<c:forEach items="${currentliabilitiesledgeraccount}" var="currentliabilitiesledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${currentliabilitiesledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${currentliabilitiesledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">RESERVES</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${reserves}" /></td>

						</tr>
					<c:forEach items="${reservesledgeraccount}" var="reservesledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${reservesledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${reservesledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;"><c:out value="${grouponetotallabel}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${grouponesemitotal}" /></td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;"><c:out value="${differencelabel}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${differenceamount}" /></td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;">TOTAL</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${grouponetotal}" /></td>

						</tr>
					
				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2"><input 
							type="hidden"  id="delete" />
							</td>
							

					</tr>
				</tfoot>
			</table>
			<table width="50%" border="0" style="border-color: #4b6a84;float: left"	>

				<thead>
					<tr>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Property & Assets</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Amount</th>
					</tr>
				</thead>

				<tbody>
					<!-- FIXED ASSETS -->
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">FIXED ASSETS</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${fixedassets}" /></td>

						</tr>
					<c:forEach items="${fixedassetsledgeraccount}" var="fixedassetsledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${fixedassetsledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${fixedassetsledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					<!-- INVESTMENTS -->
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">INVESTMENTS</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${investments}" /></td>

						</tr>
					<c:forEach items="${investmentsledgeraccount}" var="investmentsledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${investmentsledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${investmentsledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					<!-- CURRENT ASSETS -->
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">CURRENT ASSETS</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${currentassets}" /></td>

						</tr>
					<c:forEach items="${currentassetsledgeraccount}" var="currentassetsledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${currentassetsledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${currentassetsledgeraccount.value}" /></td>
								<td class="dataText"></td>

						</tr>
					</c:forEach>

					<!-- LOANS(ASSET)	 -->
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">LOANS(ASSET)</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${loansassets}" /></td>

						</tr>
					<c:forEach items="${loansassetsledgeraccount}" var="loansassetsledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right"><c:out value="${loansassetsledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${loansassetsledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>
					
					<!-- LOANS(ASSET)	 -->
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left">MISCELLANEOUS EXPENSES(ASSET)</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${miscellaneousexpenses}" /></td>

						</tr>
					<c:forEach items="${miscellaneousexpensesledgeraccount}" var="miscellaneousexpensesledgeraccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText"><c:out value="${miscellaneousexpensesledgeraccount.key}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${miscellaneousexpensesledgeraccount.value}" /></td>
							<td class="dataText"></td>

						</tr>
					</c:forEach>

					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;"><c:out value="${grouptwototallabel}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${grouptwosemitotal}" /></td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;"><c:out value="${grouptwodifferencelabel}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${grouptwodifferenceamount}" /></td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;">TOTAL</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${grouptwototal}" /></td>

						</tr>
				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2"><input 
							type="hidden"  id="delete" />
							</td>
							

					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
