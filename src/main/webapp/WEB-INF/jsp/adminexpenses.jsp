<%--
    Document   : index
    Created on : Dec 23, 2011, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
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
	color: black;
	text-transform: capitalize;
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

.alignRightMultiple {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bolder;
	text-align: right;
	vertical-align: middle;
	font-style: normal;
	color: #325F6D;
}
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


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
.alignLeft {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

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

.alignRight {
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
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
		$("#medicineId").val(id)
		$("#medicineName").val(name);

	}
	function getCurrentDate() {
		var today = new Date();
		var day = today.getDate();
		var month = today.getMonth() + 1;
		var year = today.getFullYear();
		if (month < 10) {
			month = "0" + month;

		} else {
			month = month;
		}
		if (day < 10) {
			day = "0" + day;

		} else {
			day = day;
		}
		return month + "/" + day + "/" + year;

	}
</script>
<script type="text/javascript">
	$(function() {
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
	$(function() {
		$("#entrydate").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#entrydate").datepicker("option", "showAnim", $(this).val());
		});
		
		$("#chequedate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#chequedate").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function addExpenses() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=addExpenses";
		form1.method = "POST";
		form1.submit();

	}
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=deleteMultiple";
		form1.method = "POST";
		form1.submit();

	}
	
	function printRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=printVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function approveRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=approveVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function rejectRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=rejectVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			addExpenses();
		});
		$("#effect").hide();

	});
	function calculatestock() {

		var tablet = document.getElementById('tablet').value;
		var strip = document.getElementById('strip').value;

		var stock = Number(tablet) * Number(strip);

		document.getElementById('stockQuantity').value = stock;

	}
	

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
    
	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         
         $("#print").button({
             icons:{
                 primary: "ui-icon-print"
             }
         }).click(function(){
             printRecords();
             return false;

         });
         
         $("#approve").button({
             icons:{
                 primary: "ui-icon-check"
             }
         }).click(function(){
             approveRecords();
             return false;

         });
         
         $("#reject").button({
             icons:{
                 primary: "ui-icon-closethick"
             }
         }).click(function(){
             rejectRecords();
             return false;

         });
         $('#chckHead').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             if(length>0){
                 $('.chcktbl:checked').attr('checked', false);
                 this.checked=false;

             }
             else{
                 if (this.checked == false) {
                     $('.chcktbl:checked').attr('checked', false);
                 }
                 else {
                     $('.chcktbl:not(:checked)').attr('checked', true);
                 }

             }

         });
         $('.chcktbl').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             alert(tdLength);
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
         
         $( "#go" )
         .button()
         

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
	<form id="form1"
		action="Controller?process=DepartmentProcess&action=deleteMultiple" method="POST">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		%>
		<div style="height: 28px">
			<button id="add">Add Expenses</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Details</a></li>

				</ul>
				<div id="tabs-1">
					<table width="50%" border="0"  cellpadding="0"
						cellspacing="0" id="table1" style="float: left;">
						<tr>
							<td><br><br></td>
						</tr>
						<tr>
							<td  class="alignRight">Amount (Rs.) &nbsp;</td>
							<td ><label> <input id="price"
									name="price" type="text" class="textField" 
									style="text-transform:uppercase;height: 30px;font-size: 16px; border-radius: 5px"
									required size="26" onkeyup="numberWithCommas(this);">
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Party Name &nbsp;</td>
							<td ><label> <input id="paidto"
									name="paidto" type="text" class="textField" 
									style="text-transform:uppercase;height: 30px;font-size: 16px; border-radius: 5px"
									required size="26">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Being &nbsp;</td>
							<td ><label> <input id="item"
									name="item" type="text" class="textField" 
									style="text-transform:uppercase;height: 30px;font-size: 16px; border-radius: 5px"
									required size="26">
							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Payment Type &nbsp;</td>
							<td ><label> <select name="paymenttype" 
									id="paymenttype" style="width: 256px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white">
										<option selected>Cash</option>
										<option>Cheque</option>
								</select>
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
						<td class="alignRight">Date&nbsp;</td>
							<td><label> <input name="entrydate"
									style="text-transform:uppercase;height: 30px;font-size: 16px;font-weight: bold;border-radius: 5px"
									type="text" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									class="textField" id="entrydate" size="26"
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							
							
							<tr>
							<td><br /></td>
						</tr>
					</table>
					<table width="50%" border="0"  cellpadding="0"
						cellspacing="0" id="table1" style="float: left;">
						<tr>
							<td><br><br></td>
						</tr>
						<tr>
							<td  class="alignRight">Cheque # &nbsp;</td>
							<td ><label> <input id="chequeno"
									name="chequeno" type="text" class="textField" 
									style="text-transform:uppercase;height: 30px;font-size: 16px; border-radius: 5px"
									required size="26">

							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Cheque Date&nbsp;</td>
							<td><label> <input name="chequedate"
									style="text-transform:uppercase;height: 30px;font-size: 16px;font-weight: bold;border-radius: 5px"
									type="text" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									class="textField" id="chequedate" size="26"
									data-validate="validate(required)">
							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Bank Name &nbsp;</td>
							<td ><label> <input id="bankname"
									name="bankname" type="text" class="textField" 
									style="text-transform:uppercase;height: 30px;font-size: 16px; border-radius: 5px"
									required size="26">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="save">Save</button>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="tabs-2">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td class="alignRight">Date: &nbsp;</td>
							<td><label> <input name="oneday"
									type="text" class="textField" id="datepicker" size="26"
									style="text-transform:uppercase;height: 30px;font-size: 16px;font-weight: bold;border-radius: 5px"
									onfocus="checkFields()" value="${dayone}" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
						<td class="alignRight">&nbsp;Between Dates</td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">From Date:  &nbsp;&nbsp;</td>
							<td ><label> <input name="fromdate"
									type="text" class="textField" id="datepickerfrom" size="26"
									style="text-transform:uppercase;height: 30px;font-size: 16px;font-weight: bold;border-radius: 5px"
									onfocus="checkFieldsFrom()" value="${datefrom}" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
							<td class="alignRight"> &nbsp;&nbsp; &nbsp;&nbsp;To Date:</td>
							<td ><label> <input name="todate"
									type="text" class="textField" id="datepickerto" size="26"
									style="text-transform:uppercase;height: 30px;font-size: 16px;font-weight: bold;border-radius: 5px"
									onfocus="checkFieldsTo()" value="${dateto}" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
						</tr>
						
						<tr>
						<td>&nbsp;</td>
						</tr>
						<tr>
						<td>&nbsp;</td>
						</tr>
						
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button onmouseover="checkFieldsButton();" id="search">Search</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">View All Expenses</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Voucher #</th>
						<th title="click to sort" class="headerText">Date</th>
						<th title="click to sort" class="headerText">Amount (Rs.)</th>
						<th title="click to sort" class="headerText">Party Name</th>
						<th title="click to sort" class="headerText">Being</th>
						<th title="click to sort" class="headerText">Payment Type</th>
						<th title="click to sort" class="headerText">Cheque #</th>
						<th title="click to sort" class="headerText">Cheque Date</th>
						<th title="click to sort" class="headerText">Bank Name</th>
						<th title="click to sort" class="headerText">Status</th>	
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${adminexpenses}" var="expenses">
						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							
							
                          <td class="dataText"><input type="checkbox" id = "<c:out value="${expenses.idAdminExpenses}"/>" class = "chcktbl"  name="expensesIDs"  value="<c:out value="${expenses.idAdminExpenses}"/>"/></td>
						  <td class="dataText"><c:out value="${expenses.idAdminExpenses}" /></td>
						  <td class="dataText"><fmt:formatDate value="${expenses.entrydate}" pattern="dd/MM/yyyy"/></td>
						  <td class="dataText"><c:out value="${expenses.priceofitem}" /></td>
						  <td class="dataText"><c:out value="${expenses.paidto}" /></td>
						  <td class="dataText"><c:out value="${expenses.itemdescription}" /></td>
						  <td class="dataText"><c:out value="${expenses.paymenttype}" /></td>
						  <td class="dataText"><c:out value="${expenses.chequeno}" /></td>
						  <td class="dataText"><fmt:formatDate value="${expenses.chequedate}" pattern="dd/MM/yyyy"/></td>
						  <td class="dataText"><c:out value="${expenses.bankname}" /></td>
						  <td class="dataText"><label style="text-transform: capitalize;"><c:out value="${expenses.voucherstatus}" /></label></td>
						</tr>
					</c:forEach>

				</tbody>
					<tfoot>
						<tr>
                            <!-- <td  class="footerTD" colspan="2" ><button id="delete" type="submit">Delete</button>  -->
                    		<td class="footerTD"  colspan="8">
                    		<button id="print">Print</button> 
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="approve">Approve</button>
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="reject">Reject</button> 
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="delete">Cancel</button>
                    		</td>
                        </tr>
                    </tfoot>
			</table>

		</div>


	</form>

</body>
</html>
