<%--
    Document   : receipt details
    Created on : Mar 09, 2018, 3:05:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Receipt Details</title>
<link rel="stylesheet" href="/noblewisdom/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/noblewisdom/css/datePicker/demos.css">
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
<link rel="stylesheet" href="/noblewisdom/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/noblewisdom/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/noblewisdom/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/noblewisdom/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/noblewisdom/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/noblewisdom/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/noblewisdom/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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

<script type="text/javascript" src="/noblewisdom/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function search() {
		var form1 = document.getElementById("form1");
		form1.action = "/noblewisdom/AccountProcess/viewNextVoucher";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			search();
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
		

	});

	$(function() {

		$("#tabs").tabs();

		$("#cancel").button().click(function() {
			cancelVoucher();
		});
		/* $("#effect").hide(); */

	});
	
	$(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button({
			icons : {
				primary : "ui-icon-arrowthick-1-s"
			}
		}).click(function() {
			runEffect();
			return false;
		});
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
		
		 $(function(){
	         $("#print").button({
	             icons:{
	                 primary: "ui-icon-print"
	             }
	         }).click(function() {
	 			printVoucher();
				return false;
			});
	         
	     });
		 
			$("#export").button({
				icons : {
					primary : "ui-icon-arrowthickstop-1-s"
				}
			}).click(function() {
				exportVoucher();
				return false;
			});
	});
	
	$(function() {

		$("#tabs").tabs();

		$("#effect").hide();
	});
	
	function exportVoucher(){
		var form1 = document.getElementById("form1");
		form1.action = "/noblewisdom/AccountProcess/exportVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function cancelVoucher(){
		
		if(confirm('Are you sure, you want to cancel the Receipt Voucher?')){
			var form1 = document.getElementById("form1");
			form1.action="/noblewisdom/AccountProcess/cancelVoucher";
			form1.method = "POST";
			form1.submit();	
		}
		
	}
	
	function printVoucher(){
		
			var form1 = document.getElementById("form1");
			form1.action="/noblewisdom/AccountProcess/voucherPrint";
			form1.method = "POST";
			form1.submit();	
	}
	

</script>





</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/noblewisdom/UserProcess/sessionTimeOut");
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

			<div style="height: 28px">
			<button id="add">Parameters</button>
			<br />
		</div>
		
		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Search Criteria</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
						<td width="20%" class="alignRight">Voucher Type&nbsp;</td>
							<td width="28%"><label> <select name="voucher"
									id="voucher" style="width: 150px">
										<option selected>${vouchertype}</option>
										<option>Receipt</option>
										<option>Payment</option>
										<option>Contra</option>
										<option>Journal</option>
								</select>
							</label></td>
							</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						<tr>
						<td width="20%" class="alignRight">From Date&nbsp;</td>
							<td width="28%"><label> <input name="fromdate" autocomplete="off"
									type="text"
									class="textField" id="fromdate" size="36" 
									data-validate="validate(required)">
									
									<input name="fromdateselected" 
									type="hidden" value="${fromdateselected}"
									 id="fromdateselected" size="36" 
									>
							</label></td>
							
							<td width="20%" class="alignRight">To Date&nbsp;</td>
							<td width="28%"><label> <input name="todate" autocomplete="off"
									type="text" value="${todateselected}"
									class="textField" id="todate" size="36"
									data-validate="validate(required)">
									
									<input name="todateselected" 
									type="hidden" value="${todateselected}"
									 id="todateselected" size="36" 
									>
							</label></td>
							
							</tr>
							
							
							<tr>
							<td><br /></td>
						</tr>
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="search">Search</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
			<!-- <div id="tabs">
				<ul>
					<li><a href="#tabs-1">Search Voucher</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						<tr>
							<td><br /></td>

						</tr>

						<tr>
						<td></td>
						<td></td>
						<td width="40%"></td>
							<td width="70%"><label> <select name="voucher"
									id="voucher" style="width: 150px">
										<option selected>Receipt</option>
										<option>Payment</option>
										<option>Contra</option>
										<option>Journal</option>
								</select>
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td width="30%" class="alignRight"></td>
							<td></td>
							<td><br></td>
							<td width="30%" class="alignRight">&nbsp;</td>
							<td width="20%" class="alignRight">
								<button id="search">Search</button>
							</td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

					</table>
					
					

				</div>
			</div> -->
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">${vouchertype}</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Voucher Number</th>
						<th title="click to sort" class="headerText">Date</th>
						<th title="click to sort" class="headerText">Dr Account -- Cr Account&nbsp;</th>
						<th title="click to sort" class="headerText">Narration</th>
						<th title="click to sort" class="headerText">Amount&nbsp;</th>
					</tr>
				</thead>

				<tbody>
				 <fmt:setLocale value="en_IN" scope="session"/>
                    	<c:set var="vouchertotal" value="${0}" />
					<c:forEach items="${vouchertransactions}" var="vouchertransactions">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${vouchertransactions.key.transactionsid}"/>" class="chcktbl"
								name="transactionids"
								value="<c:out value="${vouchertransactions.key.transactionsid}"/>" />
								
							</td>
							<td class="dataTextInActive"><c:out value="${vouchertransactions.key.transactionsid}" />
							</td>
							<td class="dataText"><c:out
									value="${vouchertransactions.key.transactiondate}" /></td>
							<td class="dataText"><c:out value="${vouchertransactions.value}" /></td>
							<td class="dataText"><c:out
									value="${vouchertransactions.key.narration}" /></td>
							<td class="dataText" style="text-align: right;">
								<c:set var="vouchertotal" value="${vouchertotal + vouchertransactions.key.dramount}" />
								<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${vouchertransactions.key.dramount}"/>
								<%-- <c:out value=" -- " />
								<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${vouchertransactions.key.cramount}"/> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
				
				<table width="13%" border="0" style="border-color: #4b6a84;float: right;padding-right: 15px;"
				id="myTable">

				<tbody>
					<tr align="right">
					
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Total</b>
							</label> 
							</td>
							
							<td class="dataTextRight">
								<label style="color: #eb6000"><b>
									<fmt:formatNumber type="currency"  value="${vouchertotal}" />
								</b>
								</label>
							</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;">
                    <tfoot>
					<tr>
													
						<td class="footerTD" colspan="2"> &nbsp;
						<button id="cancel">Cancel Voucher</button>
						<%-- <a id="print" href="/noblewisdom/AccountProcess/voucherPrint?fromdate=<c:out value="${fromdate}"/>&todate=<c:out value="${todate}"/>&vouchertype=<c:out value="${vouchertype}"/>">Print</a> --%>
						&nbsp;&nbsp;&nbsp;<button id="print">Print</button>&nbsp;&nbsp;&nbsp;<button id="export">Export</button>
						
						</td>
						
					</tr>
				</tfoot>
			</table>
				

		</div>


	</form>

</body>
</html>
