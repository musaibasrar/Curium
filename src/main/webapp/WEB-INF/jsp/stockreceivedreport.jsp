<%--
    Document   : Stock Received Report
    Created on : Nov 24, 2020, 8:42:28 PM
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
<title>Stock Received Report</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">
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
	width: 220px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
}

.textfieldvaluesshorts{

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
	width: 80px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
}


.textfieldvalues{

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
	width: 220px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
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

.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
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
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.dropdownlist{
	width: 220px;
	height:27px;
	border-radius: 5px;
	background-color: white;
}

</style>
<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/abc/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "340px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : true,
			"bAutoWidth" : false
		});
	});
</script>

<script type="text/javascript">
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
	$(function() {
		$("#transactiondatefrom").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#transactiondatefrom").datepicker("option", "showAnim", $(this).val());
		});
		$("#transactiondateto").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#transactiondateto").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function generateStockReceivedReport() {
			var form1 = document.getElementById("form1");
			form1.action = "/abc/MessItemsProcess/generateStockReceivedReport";
			form1.method = "POST";
			form1.submit();
	}
	
 
	 $(function(){
		 
		 $("#generatereport").button({
             icons:{
                 primary: "ui-icon-document"
             }
         }).click(function(){
             generateStockReceivedReport();
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
		 
         
     });
     
	 function printRecords() {
			var form1 = document.getElementById("form1");
			form1.action = "/abc/MessItemsProcess/printStockReceivedReport";
			form1.method = "POST";
			form1.submit();
		}
    
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

	<form id="form1"
		 method="POST">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		
		%>
		<c:set var="itemTotal" value="${0}" />
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div style="height: 28px">
			<button id="add">Parameters</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Filter</a></li>

				</ul>
				<div id="tabs-1">
				
					<table style="margin-left: auto;margin-right: auto;">
					
						<tr>
							<td><br><br></td>
						</tr>
						
						<tr>
						<td class="alignRight">From Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondatefrom"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondatefrom" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							<td class="alignRight">To Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondateto"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondateto" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							<tr>
							<td><br /></td>
	
							</tr>
						
						<tr>
						
						<tr>
							<td class="alignRight">Supplier&nbsp;</td>
							<td ><label>
									<select name="supplier"
									id="supplier" class="dropdownlist" style="font-size: 14px;" >
										<option value="ALL"></option>
										<c:forEach items="${messsupplierslist}" var="messsupplierslist">
											<option value="${messsupplierslist.id}:${messsupplierslist.name}">${messsupplierslist.name}</option>
										</c:forEach>
								</select>
							
							</label></td>
						
							<td class="alignRight">Item&nbsp;</td>
							<td ><label>
									<select name="itemname" id="itemname" class="dropdownlist" style="font-size: 14px;">
											<option></option>
										<c:forEach items="${itemslist}" var="itemslist">
											<option value="${itemslist.id}:${itemslist.name}">${itemslist.name}</option>
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
					
						<div align="center">
						<p>
						<label><button id="generatereport">Generate Report</button></label></p>
						
									
					</div>
					
					</div>
				</div>
				
				
			</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Stock Received Report <br>
					${transactionfromdateselected}&nbsp;&nbsp;${transactiontodateselected}&nbsp;&nbsp;${supplierselected}&nbsp;&nbsp;${itemselected}&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText">Sl.No.</th>
						<th title="click to sort" class="headerText">Date</th>
						<th title="click to sort" class="headerText">Batch#</th>
						<th title="click to sort" class="headerText">Item</th>
						<th title="click to sort" class="headerText">Quantity</th>
						<th title="click to sort" class="headerText">Unit Price</th>
						<th title="click to sort" class="headerText">Total Price</th>
						
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${messstockentrylist}" var="messstockentrylist" varStatus="status">
						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><c:out value="${status.index+1}" /></td>
						  <td class="dataText"><input type="text"  style="background-color: #E3EFFF;border-style: none;color: #4B6A84;text-align: center;"  value="<fmt:formatDate value="${messstockentrylist.messinvoicedetails.entrydate}" pattern="dd/MM/yyyy"/>" readonly></td>
						  <td class="dataText"><c:out value="${messstockentrylist.batchno}" /></td>
						  <c:set var="dataParts" value="${fn:split(messstockentrylist.externalid, '_')}" />
						  <td class="dataText"><c:out value="${dataParts[0]}" /></td>
						  <td class="dataText"><c:out value="${messstockentrylist.quantity}" /></td>
						  <td class="dataText"><c:out value="${messstockentrylist.itemunitprice}" /></td>
						  <td class="dataText">
						  <c:set var="itemTotal" value="${itemTotal + messstockentrylist.itemunitprice * messstockentrylist.quantity}" />
						  <fmt:setLocale value="en_IN" scope="request"/>
							<fmt:formatNumber type="currency"  value="${messstockentrylist.itemunitprice * messstockentrylist.quantity}" />
						  </td>
						</tr>
					</c:forEach>

				</tbody>
					<tfoot>
						<tr>
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>Grand Total:
							<fmt:setLocale value="en_IN" scope="request"/>
							<fmt:formatNumber type="currency"  value="${itemTotal}" /></b>
							</label> 
							</td>
						</tr>
						<tr>
                            <!-- <td  class="footerTD" colspan="2" ><button id="delete" type="submit">Delete</button>  -->
                    		<td class="footerTD"  colspan="8">
                    		<button id="print">Print</button> 
                    		&nbsp;&nbsp;&nbsp;
                    		<!-- <button id="approve">Approve</button>
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="reject">Reject</button> 
                    		&nbsp;&nbsp;&nbsp; -->
                    		<!-- <button id="cancel">Cancel</button> -->
                    		</td>
                        </tr>
                    </tfoot>
			</table>

		</div>
	</form>
	
</body>
</html>
