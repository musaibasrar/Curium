<%--
    Document   : incomevsexpense
    Created on : JAN 12, 2024, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Income v/s Expense</title>
<link rel="stylesheet" href="/jih/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/jih/css/datePicker/demos.css">
<link rel="stylesheet" href="/jih/css/font-awesome.css">
<style type="text/css">
<!--
.divCSS {
	overflow: scroll;
	height: 100%;
	width: 100%;
}

#myTable tr:nth-child(even) td {
  background-color: #e5e5e5; 
}

#myTable tr:nth-child(odd) td {
  background-color: #ffffff;
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
	font-size: 16px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
	/* vertical-align: text-top; */
	text-align: center;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #000000;
	font-size: 14px;
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
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/jih/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/jih/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/jih/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/jih/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/jih/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/jih/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/jih/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/jih/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
</script>
<script type="text/javascript" src="/jih/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function searchIncomeExpense() {
		var form1 = document.getElementById("form1");
		form1.action = "/jih/AccountProcess/searchIncomeExpense";
		form1.method = "POST";
		form1.submit();

	}
	
	function printRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/jih/AccountProcess/printIncomevsExpense";
		form1.method = "POST";
		form1.submit();
}
	
	$(function() {

		$("#tabs").tabs();
		$("#search").button().click(function() {
			searchIncomeExpense();
		});
		 $("#print").button({
             icons:{
                 primary: "ui-icon-print"
             }
         }).click(function(){
             printRecords();
             return false;

         });
		$("#effect").hide();

	});
	
	 $(function(){
		 $("#export").button({
				
			});
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
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
	 

	 function checkFieldsButton() {
		 
			
			var oneday = document.getElementById('datepicker').value;
			var fromdate = document.getElementById('datepickerfrom').value;
			var todate = document.getElementById('datepickerto').value;
			
			if(oneday == "" && fromdate == "" && todate == ""){
				alert("Please enter the search criteria");
			}
		
			if(fromdate > todate ){
				alert('"To date" should be greater than "From date"');
			}
			
		}

	 
	 function checkFieldsTo() {
		 
			
				document.getElementById('datepicker').value = "";
				
				
			}

	 
	 function checkFieldsFrom() {
		 
		 document.getElementById('datepicker').value = "";
		 document.getElementById('datepickerto').value = "";
		}
	 
	 
	 function checkFields() {

			document.getElementById('datepickerfrom').value = "";
			document.getElementById('datepickerto').value = "";
			
			
		}
</script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerfrom").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy'
		});
		$("#anim").change(function() {
			$("#datepickerfrom").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerto").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy'
		});
		$("#anim").change(function() {
			$("#datepickerto").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>


	<script type="text/javascript">
					
					var cancelreceipt = '<c:out default="" value="${cancelreceiptresult}"/>';
		            
		            if(cancelreceipt == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(cancelreceipt == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }
            
        </script>
        
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/jih/UserProcess/sessionTimeOut");
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
	<form id="form1" action="/jih/AccountProcess/exportDataForIncomeVsExpense" method="POST">
		
		<div style="height: 28px">
			<button id="add">Parameter</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Dates</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						
						<tr>
						<td width="20%" class="alignRight">From Date&nbsp;</td>
							<td width="28%"><label> <input name="fromdate" autocomplete="off"
									type="text" value="${fromdate}"
									class="textField" id="datepickerfrom" size="36" 
									data-validate="validate(required)">
							</label></td>
							
							<td width="20%" class="alignRight">To Date&nbsp;</td>
							<td width="28%"><label> <input name="todate" autocomplete="off"
									type="text"  value="${todate}"
									class="textField" id="datepickerto" size="36"
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
					<td class="headerTD"><label style="color: #EB6000;">${branchname} </label></td>
				</tr> 
							</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
                        <tr>
                            <th class="headerText">Category</th>
                            <th class="headerText">Income</th>
                            <th class="headerText">Expense</th>
                        </tr>
                    </thead>

                    <tbody>
                   		 <fmt:setLocale value="en_IN" scope="session"/>
                    	<c:set var="income" value="${0}" />
						<c:set var="expense" value="${0}" />
                    	<c:forEach items="${incomeexpensescategorytotal}" var="incomeexpensescategorytotal">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: left" width="20%"><c:out value="${incomeexpensescategorytotal.key}" /></td>
							<c:set var="values" value="${incomeexpensescategorytotal.value}" />	
							<td class="dataText" style="text-align: right" width="50%">
							<c:set var="income" value="${income + values[0]}" />
							<fmt:formatNumber type="currency" pattern="#,##0.00;" value="${values[0]}" />
							</td>
							<td class="dataText" style="text-align: right" width="30%">
							<c:set var="expense" value="${expense + values[1]}" />
							<fmt:formatNumber type="currency" pattern="#,##0.00;" value="${values[1]}" />
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
							<fmt:formatNumber type="currency"  value="${income}" /></b>
							</label> 
							</td>
							<td class="dataTextRight">
							<label style="color: #eb6000"><b>
							<fmt:formatNumber type="currency"  value="${expense}" /></b>
							</label>
							</td>
					</tr>
					<tr align="right">
					
							<td class="dataTextRight" >
								<label style="color: #eb6000"><b>
									Balance</b>
							</label> 
							</td>
							
							<td class="dataTextRight">
								<label style="color: #eb6000"><b>
							<c:choose>
                                <c:when test="${income > expense}">
									<fmt:formatNumber type="currency"  value="${income-expense}" />                                    
                                </c:when>
                                <c:otherwise>
                                   <fmt:formatNumber type="currency"  value="${expense-income}" />
                                </c:otherwise>
                            </c:choose>
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
						<!-- <label style="font-weight: bold;font-size: 14px;font-family: cursive;color: white;">File Name</label>
						<label> <input
									name="fileName" type="text" class="myclass" id="fileName"
									size="20">
							</label> -->
						<input value="Export"
							type="submit" id="export"/>&nbsp;&nbsp;&nbsp;<button id="print">Print</button></td>
					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
