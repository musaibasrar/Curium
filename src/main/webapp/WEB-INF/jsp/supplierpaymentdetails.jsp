<%--
    Document   : Supplier Paymnet Details
    Created on : Jan 19, 2021, 11:22:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Supplier Payment Details</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<style type="text/css">
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


.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
	background-color: #E3EFFF;
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
	function search() {
		var form1 = document.getElementById("form1");
		if(form1.checkValidity()) {
			form1.action = "Controller?process=MessSuppliersProcess&action=searchSupplierPaymentDetails";
			form1.method = "POST";
			form1.submit();
		  }
	}

	
	function printRecords() {
			var form1 = document.getElementById("form1");
			form1.action = "Controller?process=MessSuppliersProcess&action=printSearchSupplierPaymentDetails";
			form1.method = "POST";
			form1.submit();
	}

	
	$(function() {

		$("#search").button().click(function() {
			search();
		});
		
		 $("#print").button({
             icons:{
                 primary: "ui-icon-print"
             }
         }).click(function(){
             printRecords();
             return false;

         });
		

	});

	$(function() {

		$("#tabs").tabs();
	});
	
	
	$(function() {
		$("#fromdate").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+10"
		});
		$( "#fromdate" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
		$("#anim").change(function() {
			$("#fromdate").datepicker("option", "showAnim", $(this).val());
		});
		
		$("#todate").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+10"
		});
		$( "#todate" ).datepicker( "option", "dateFormat", "dd/mm/yy" );
		$("#anim").change(function() {
			$("#todate").datepicker("option", "showAnim", $(this).val());
		});
	});

	$(function() {
		$("#tabs").tabs();
		$("#effect").hide();
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button().click(function() {
			runEffect();
			return false;
		});
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
		<c:set var="crtotal" value="${0}" />
		<c:set var="drtotal" value="${0}" />
	<form id="form1">
	
		<div style="height: 28px">
			<button id="add">Parameters</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Search Supplier Payment Details</a></li>

				</ul>
				<div id="tabs-1">
					<table style="margin-left: auto;margin-right: auto;">

						<tr>
							<td><br /></td>

						</tr>

						<tr>
						
						<td class="alignRight">Select Account</td>
							<td><label> <select name="accountid" id="accountid" style="width: 230px" required>
										<option selected></option>
									
									  <c:forEach items="${messsupplierslist}" var="ledger">

											<option value="${ledger.linkedledgerid}:${ledger.name}">
												<c:out value="${ledger.name}" />
											</option>

										</c:forEach>
										
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
						<td class="alignRight">From Date&nbsp;</td>
							<td><label> <input name="fromdate" autocomplete="off" type="text" class="textField" id="fromdate" size="36" required>
							</label></td>
							
							<td  class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;To Date&nbsp;</td>
							<td ><label> <input name="todate" autocomplete="off" type="text" class="textField" id="todate" size="36" required>
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
							<td><br></td>
							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td>
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
					<td class="headerTD">
						<label style="text-decoration: underline;">Supplier Payment Details</label><br>
						<label style="text-transform: capitalize;">From:</label>  
					<input type="text" name="fromdateselected" style="background: transparent;border: none;color: white;font-weight: bold;width: 90px;" value="${fromdate}" >
					<label style="text-transform: capitalize;">To:</label>  
					<input type="text" name="todateselected" style="background: transparent;border: none;color: white;font-weight: bold;" value="${todate}" >
					<label style="text-transform: capitalize;">Supplier Name:</label>
					<input type="text" style="background: transparent;border: none;color: white;font-weight: bold;width: 260px;" value="${ledgername}" >
					<input type="hidden" name="accountidselected" style="background: transparent;border: none;color: white;font-weight: bold;width: 260px;" value="${accountid}" >
					</td>
				</tr>
				
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"></th>
						<th title="click to sort" class="headerText">Voucher Number</th>
						<th title="click to sort" class="headerText">Date</th>
						<th title="click to sort" class="headerText">Account Description&nbsp;</th>
						<th title="click to sort" class="headerText">Narration</th>
						<th title="click to sort" class="headerText">Payments&nbsp;</th>
						<th title="click to sort" class="headerText">Receipts&nbsp;</th>
					</tr>
				</thead>

				<tbody>
				<fmt:setLocale value="en_IN" scope="request"/>
					<c:forEach items="${ledgertransactions}" var="ledgertransactions">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataText"></td>
							<td class="dataTextInActive"><c:out value="${ledgertransactions.key.transactionsid}" />
							</td>
							<td class="dataText">
								<fmt:formatDate value="${ledgertransactions.key.transactiondate}" pattern="dd/MM/yyyy"/>
							</td>
							<td class="dataText">
							<c:set var="ledgername" value="${fn:split(ledgertransactions.value,':')}"></c:set>
							${ledgername[0]}<%-- <c:out value="${ledgertransactions.value}" /> --%></td>
							 <td class="dataText"><c:out	value="${ledgertransactions.key.narration}" /></td>
							
							<c:if test="${ledgername[1] == 'Dr'}">
								
								<td class="dataText"></td>
								<c:set var="crtotal" value="${crtotal + ledgertransactions.key.cramount}" />
								<td class="dataTextRight">
									<fmt:formatNumber type="number"  maxFractionDigits = "2" value="${ledgertransactions.key.cramount}" />
								</td>
								
							</c:if>
							
							<c:if test="${ledgername[1] == 'Cr'}">
								
								<td class="dataTextRight">
									<c:set var="drtotal" value="${drtotal + ledgertransactions.key.dramount}" />
									<fmt:formatNumber type="number"  maxFractionDigits = "2"  value="${ledgertransactions.key.dramount}" />
								</td>
								
								<td class="dataText"></td>
							</c:if>
						</tr>
					</c:forEach>
					<tr>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
					<td class="dataText"></td>
						<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
							<fmt:formatNumber type="currency"  value="${drtotal}" /></b>
							</label> 
							</td>
							<td class="dataTextRight">
							<label style="color: #eb6000"><b>
							<fmt:formatNumber type="currency"  value="${crtotal}" /></b>
							</label>
							</td>
					</tr>
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
